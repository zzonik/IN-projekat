package com.INprojekat.WEB.service;

<<<<<<< Updated upstream
=======
    }

import com.INprojekat.WEB.dto.KnjigaAutorDto;
import com.INprojekat.WEB.dto.KnjigaDto;
import com.INprojekat.WEB.dto.UpdateKnjigaDto;
import com.INprojekat.WEB.entity.*;
import com.INprojekat.WEB.repository.AutorRepository;
import com.INprojekat.WEB.repository.KnjigaRepository;
import com.INprojekat.WEB.repository.ZanrRepository;
import jakarta.persistence.GeneratedValue;

>>>>>>> Stashed changes
import com.INprojekat.WEB.dto.*;
import com.INprojekat.WEB.entity.*;
import com.INprojekat.WEB.repository.KnjigaRepository;
import com.INprojekat.WEB.repository.PolicaRepository;
import com.INprojekat.WEB.repository.RecenzijaRepository;
import com.INprojekat.WEB.repository.StavkaPoliceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class KnjigaService {

    @Autowired
    private KnjigaRepository knjigaRepository;

    @Autowired
    private RecenzijaRepository recenzijaRepository;

    @Autowired
    private StavkaPoliceRepository stavkaPoliceRepository;

    @Autowired
    private KorisnikService korisnikService;

    @Autowired
    private PolicaService policaService;

    public KnjigaDto findOne(Long id){
        Optional<Knjiga> foundKnjiga = knjigaRepository.findById(id);
        if (foundKnjiga.isPresent()) {
            return new KnjigaDto(foundKnjiga.get());
        }
        return null;
    }

    public List<KnjigaDto> findAll(){
        List<Knjiga> knjigeList = knjigaRepository.findAll();

        List<KnjigaDto> dtos = new ArrayList<>();
        for(Knjiga knjiga : knjigeList){
            KnjigaDto dto = new KnjigaDto(knjiga);
            dtos.add(dto);
        }
        return dtos;

    }

    public Knjiga create(Long id, KnjigaAutorDto knjigaAutorDto) {
        Korisnik korisnik = korisnikService.findOne(id);
        Autor autor = new Autor();
        autor.setIme(korisnik.getIme());
        autor.setPrezime(korisnik.getPrezime());
        autor.setKorisnickoIme(korisnik.getKorisnickoIme());
        autor.setMail(korisnik.getMail());
        autor.setLozinka(korisnik.getLozinka());
        autor.setProfilnaSlika(korisnik.getProfilnaSlika());
        autor.setUloga(korisnik.getUloga());
        autor.setAktivnost(true);

        Knjiga knjiga = new Knjiga();
        knjiga.setNaslov(knjigaAutorDto.getNaslov());
        knjiga.setNaslovnaFotografija(knjigaAutorDto.getNaslovnaFotografija());
        knjiga.setISBN(knjigaAutorDto.getISBN());
        knjiga.setBrojStrana(knjigaAutorDto.getBrojStrana());
        knjiga.setDatumObjavljivanja(knjigaAutorDto.getDatumObjavljivanja());
        knjiga.setOpis(knjigaAutorDto.getOpis());
        knjiga.setAutor(autor);
        //knjiga.setZanr(knjigaAutorDto.getZanr());

        return save(knjiga);
    }
/*
    public Knjiga add(Long id,Long id2, KnjigaAddDto knjigaAddDto) {
        Korisnik korisnik = korisnikService.findOne(id);
        PolicaDto policaDto = policaService.findOne(id2);
        StavkaPoliceDto stavkaPoliceDto = policaDto.getStavka_Police();

    }
*/
    public void deleteKnjiga(Long id,Long id2) throws ChangeSetPersister.NotFoundException {
        Knjiga knjiga = knjigaRepository.findById(id)
                .orElseThrow(() -> new ChangeSetPersister.NotFoundException());
        PolicaDto policaDto = policaService.findOne(id2);
        if(policaDto.isPrimarna()){
            for(StavkaPolice stavkaPolice: stavkaPoliceRepository.findAll()){
                if(stavkaPolice.getKnjiga().getId() == id);
                knjigaRepository.delete(knjiga);
            }
        }
        knjigaRepository.delete(knjiga);
    }

    public Boolean existsKnjiga(String naziv) { return knjigaRepository.existsByNaziv(naziv); }

    public Knjiga save(Knjiga knjiga) { return knjigaRepository.save(knjiga); }

}

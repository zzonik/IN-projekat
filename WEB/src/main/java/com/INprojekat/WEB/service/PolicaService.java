package com.INprojekat.WEB.service;

import com.INprojekat.WEB.dto.PolicaAddDto;
import com.INprojekat.WEB.dto.PolicaDto;
import com.INprojekat.WEB.dto.RegisterDto;
import com.INprojekat.WEB.dto.StavkaPoliceDto;
import com.INprojekat.WEB.entity.Knjiga;
import com.INprojekat.WEB.entity.Korisnik;
import com.INprojekat.WEB.entity.Polica;
import com.INprojekat.WEB.entity.StavkaPolice;
import com.INprojekat.WEB.repository.KnjigaRepository;
import com.INprojekat.WEB.repository.PolicaRepository;
import com.INprojekat.WEB.repository.StavkaPoliceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class PolicaService {

    @Autowired
    private PolicaRepository policaRepository;
    @Autowired
    private  KnjigaRepository knjigaRepository;
    @Autowired
    private KorisnikService korisnikService;

    @Autowired
    private StavkaPoliceRepository stavkaPoliceRepository;

    public List<Polica> findAll(){
        return policaRepository.findAll();
    }

    public Polica create(PolicaAddDto policaAddDto) {
        Polica polica;
        polica = new Polica();
        polica.setNaziv(policaAddDto.getNaziv());
        polica.setPrimarna(false);

        return save(polica);
    }

    public void main3(){
        Polica WantToRead = new Polica();
        WantToRead.setNaziv("Want To Read");
        WantToRead.setPrimarna(true);
        save(WantToRead);

        Polica CurrentlyReading = new Polica();
        CurrentlyReading.setNaziv("CurrentlyReading");
        CurrentlyReading.setPrimarna(true);
        save(CurrentlyReading);

        Polica Read = new Polica();
        Read.setNaziv("Read");
        Read.setPrimarna(true);
        save(Read);
    }

    public Boolean existsPolica(String naziv) { return policaRepository.existsByNaziv(naziv); }

    public Polica save(Polica polica) { return policaRepository.save(polica);}

    public void deletePolica(Long id) throws ChangeSetPersister.NotFoundException {
        Polica polica = policaRepository.findById(id)
                .orElseThrow(() -> new ChangeSetPersister.NotFoundException());
        policaRepository.delete(polica);
    }
    public void addKnjigaOnPolica(Long policaId, Long knjigaId) throws ChangeSetPersister.NotFoundException {
        Polica polica = policaRepository.findById(policaId).orElse(null);
        Knjiga knjiga = knjigaRepository.findById(knjigaId).orElse(null);
        StavkaPolice stavkaPolice = new StavkaPolice();
        stavkaPolice.setKnjiga(knjiga);
        stavkaPoliceRepository.save(stavkaPolice);
        polica.getStavkePolica().add(stavkaPolice);
        save(polica);

    }
    public Set<PolicaDto> getPolice(Long id) {
        Korisnik korisnik = korisnikService.findOne(id);
        Set<Polica> police = korisnik.getPolice();
        Set<PolicaDto> dtos = new HashSet<>();
        for(Polica polica: police){
            PolicaDto dto = new PolicaDto(polica);
        }
        return dtos;
    }
}

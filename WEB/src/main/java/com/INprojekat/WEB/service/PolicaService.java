package com.INprojekat.WEB.service;

import com.INprojekat.WEB.dto.PolicaAddDto;
import com.INprojekat.WEB.dto.PolicaDto;
import com.INprojekat.WEB.dto.RegisterDto;
import com.INprojekat.WEB.dto.ZanrDto;
import com.INprojekat.WEB.entity.Knjiga;
import com.INprojekat.WEB.entity.Korisnik;
import com.INprojekat.WEB.entity.Polica;
import com.INprojekat.WEB.entity.Zanr;
import com.INprojekat.WEB.repository.KnjigaRepository;
import com.INprojekat.WEB.repository.PolicaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PolicaService {

    @Autowired
    private PolicaRepository policaRepository;

    public PolicaDto findOne(Long id){
        Optional<Polica> foundPolica = policaRepository.findById(id);
        if (foundPolica.isPresent()) {
            return new PolicaDto(foundPolica.get());
        }
        return null;
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

    public List<Polica> findAll(){
        return policaRepository.findAll();
    }

    public Boolean existsPolica(String naziv) { return policaRepository.existsByNaziv(naziv); }

    public Polica save(Polica polica) { return policaRepository.save(polica);}

    public void deletePolica(Long id) throws ChangeSetPersister.NotFoundException {
        Polica polica = policaRepository.findById(id)
                .orElseThrow(() -> new ChangeSetPersister.NotFoundException());
        policaRepository.delete(polica);
    }
}

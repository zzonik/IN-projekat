package com.INprojekat.WEB.service;

import com.INprojekat.WEB.dto.StavkaPoliceAddDto;
import com.INprojekat.WEB.dto.StavkaPoliceDto;
import com.INprojekat.WEB.dto.ZanrDto;
import com.INprojekat.WEB.entity.Korisnik;
import com.INprojekat.WEB.entity.Polica;
import com.INprojekat.WEB.entity.StavkaPolice;
import com.INprojekat.WEB.entity.Zanr;
import com.INprojekat.WEB.repository.StavkaPoliceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class StavkaPoliceService {

    @Autowired
    private StavkaPoliceRepository stavkaPoliceRepository;


    public StavkaPoliceDto findOne(Long id){
        Optional<StavkaPolice> foundStavka = stavkaPoliceRepository.findById(id);
        if (foundStavka.isPresent()) {
            return new StavkaPoliceDto(foundStavka.get());
        }
        return null;
    }

    public List<StavkaPoliceDto> findAll(){
        List<StavkaPolice> stavkeList = stavkaPoliceRepository.findAll();

        List<StavkaPoliceDto> dtos = new ArrayList<>();
        for(StavkaPolice stavkaPolice : stavkeList){
            StavkaPoliceDto dto = new StavkaPoliceDto(stavkaPolice);
            dtos.add(dto);
        }
        return dtos;

    }

    public StavkaPolice create(StavkaPoliceAddDto stavkaPoliceAddDto) {
        StavkaPolice stavkaPolice = new StavkaPolice();

        stavkaPolice.setKnjiga(stavkaPoliceAddDto.getKnjiga());
        stavkaPolice.setRecenzija(stavkaPoliceAddDto.getRecenzija());

        return save(stavkaPolice);
    }

    public void deleteStavkaPolice(Long id) throws ChangeSetPersister.NotFoundException {
        StavkaPolice stavkaPolice = stavkaPoliceRepository.findById(id)
                .orElseThrow(() -> new ChangeSetPersister.NotFoundException());
        stavkaPoliceRepository.delete(stavkaPolice);
    }

    public StavkaPolice save(StavkaPolice stavkaPolice) { return stavkaPoliceRepository.save(stavkaPolice); }
}

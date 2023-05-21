package com.INprojekat.WEB.service;

import com.INprojekat.WEB.dto.KnjigaDto;
import com.INprojekat.WEB.dto.RecenzijaDto;
import com.INprojekat.WEB.dto.UpdateRecDto;
import com.INprojekat.WEB.dto.ZanrDto;
import com.INprojekat.WEB.entity.*;
import com.INprojekat.WEB.repository.RecenzijaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RecenzijaService {

    @Autowired
    private RecenzijaRepository recenzijaRepository;
//isto dto
    public Recenzija findOne(Long id){
        Optional<Recenzija> foundRecenzija = recenzijaRepository.findById(id);
        if (foundRecenzija.isPresent()) {
            return foundRecenzija.get();
        }
        return null;
    }

    public List<RecenzijaDto> findAll(){
        List<Recenzija> recenzijeList = recenzijaRepository.findAll();

        List<RecenzijaDto> dtos = new ArrayList<>();
        for(Recenzija recenzija : recenzijeList){
            RecenzijaDto dto = new RecenzijaDto(recenzija);
            dtos.add(dto);
        }
        return dtos;

    }

    public Recenzija add(RecenzijaDto recenzijaDto) {
        Recenzija recenzija;
        recenzija = new Recenzija();
        recenzija.setOcena(recenzijaDto.getOcena());
        recenzija.setDatum(recenzijaDto.getDatum());
        recenzija.setTekst(recenzijaDto.getTekst());
        recenzija.setKorisnik(recenzijaDto.getKorisnik());

        return save(recenzija);
    }

    public Recenzija updateRecenzija(Long id, UpdateRecDto updateRecDto) {
        Recenzija recenzija = findOne(id);

        recenzija.setOcena(updateRecDto.getOcena());
        recenzija.setDatum(updateRecDto.getDatum());
        recenzija.setTekst(updateRecDto.getTekst());

        return save(recenzija);
    }

    public void deleteRecenzija(Long id) throws ChangeSetPersister.NotFoundException {
        Recenzija recenzija = recenzijaRepository.findById(id)
                .orElseThrow(() -> new ChangeSetPersister.NotFoundException());
        recenzijaRepository.delete(recenzija);
    }

    public Recenzija save(Recenzija recenzija) { return recenzijaRepository.save(recenzija);}

}

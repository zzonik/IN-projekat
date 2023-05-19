package com.INprojekat.WEB.service;

import com.INprojekat.WEB.dto.RecenzijaDto;
import com.INprojekat.WEB.dto.UpdateRecDto;
import com.INprojekat.WEB.entity.Korisnik;
import com.INprojekat.WEB.entity.Polica;
import com.INprojekat.WEB.entity.Recenzija;
import com.INprojekat.WEB.repository.RecenzijaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RecenzijaService {

    @Autowired
    private RecenzijaRepository recenzijaRepository;

    public Recenzija findOne(Long id){
        Optional<Recenzija> foundRecenzija = recenzijaRepository.findById(id);
        if (foundRecenzija.isPresent())
            return foundRecenzija.get();

        return null;
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

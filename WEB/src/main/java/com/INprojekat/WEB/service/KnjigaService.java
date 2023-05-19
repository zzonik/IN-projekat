package com.INprojekat.WEB.service;

import com.INprojekat.WEB.entity.Knjiga;
import com.INprojekat.WEB.repository.KnjigaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class KnjigaService {

    private KnjigaRepository knjigaRepository;

    public List<Knjiga> findAll(){
        return knjigaRepository.findAll();
    }

    public Knjiga findOne(Long id){
        Optional<Knjiga> foundKnjiga = knjigaRepository.findById(id);
        if (foundKnjiga.isPresent())
            return foundKnjiga.get();

        return null;
    }


}

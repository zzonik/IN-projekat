package com.INprojekat.WEB.service;

import com.INprojekat.WEB.entity.Knjiga;
import com.INprojekat.WEB.repository.KnjigaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KnjigaService {

    private KnjigaRepository knjigaRepository;

    public List<Knjiga> findAll(){
        return knjigaRepository.findAll();
    }

}

package com.INprojekat.WEB.service;

import com.INprojekat.WEB.dto.PolicaAddDto;
import com.INprojekat.WEB.dto.ZanrAddDto;
import com.INprojekat.WEB.entity.Polica;
import com.INprojekat.WEB.entity.Zanr;
import com.INprojekat.WEB.repository.ZanrRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ZanrService {

    @Autowired
    private ZanrRepository zanrRepository;

    public Zanr create(ZanrAddDto zanrAddDto) {
        Zanr zanr = new Zanr();
        zanr.setNaziv(zanrAddDto.getNaziv());
        return save(zanr);
    }

    public Zanr save(Zanr zanr) {
        return zanrRepository.save(zanr);
    }

}

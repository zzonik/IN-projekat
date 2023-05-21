package com.INprojekat.WEB.service;

import com.INprojekat.WEB.entity.StavkaPolice;
import com.INprojekat.WEB.repository.StavkaPoliceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StavkaPoliceService {

    @Autowired
    private StavkaPoliceRepository stavkaPoliceRepository;

    public StavkaPolice save(StavkaPolice stavka) { return  stavkaPoliceRepository.save(stavka);}

}

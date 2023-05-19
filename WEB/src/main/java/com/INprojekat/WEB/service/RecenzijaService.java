package com.INprojekat.WEB.service;

import com.INprojekat.WEB.repository.RecenzijaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RecenzijaService {

    @Autowired
    private RecenzijaRepository recenzijaRepository;

}

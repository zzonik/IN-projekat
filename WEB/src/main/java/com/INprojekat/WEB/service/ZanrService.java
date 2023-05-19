package com.INprojekat.WEB.service;

import com.INprojekat.WEB.repository.ZanrRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ZanrService {

    @Autowired
    private ZanrRepository zanrRepository;

}

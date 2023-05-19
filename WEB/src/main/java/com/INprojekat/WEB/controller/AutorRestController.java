package com.INprojekat.WEB.controller;

import com.INprojekat.WEB.service.AutorService;
import com.INprojekat.WEB.service.StavkaPoliceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AutorRestController {

    @Autowired
    private AutorService autorServicee;

}

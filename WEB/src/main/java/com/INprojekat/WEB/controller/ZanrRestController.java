package com.INprojekat.WEB.controller;

import com.INprojekat.WEB.service.ZanrService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ZanrRestController {

    @Autowired
    private ZanrService zanrService;

}

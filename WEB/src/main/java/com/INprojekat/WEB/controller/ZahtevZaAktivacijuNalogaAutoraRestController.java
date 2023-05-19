package com.INprojekat.WEB.controller;

import com.INprojekat.WEB.service.ZahtevZaAktivacijuNalogaAutoraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ZahtevZaAktivacijuNalogaAutoraRestController {

    @Autowired
    private ZahtevZaAktivacijuNalogaAutoraService zahtevZaAktivacijuNalogaAutoraService;

}

package com.INprojekat.WEB.service;

import com.INprojekat.WEB.repository.ZahtevZaAktiavcijuNalogaAutoraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ZahtevZaAktivacijuNalogaAutoraService {

    @Autowired
    private ZahtevZaAktiavcijuNalogaAutoraRepository zahtevZaAktiavcijuNalogaAutoraRepository;

}

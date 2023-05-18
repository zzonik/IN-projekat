package com.INprojekat.WEB.service;

import com.INprojekat.WEB.dto.RegisterDto;
import com.INprojekat.WEB.entity.Korisnik;
import com.INprojekat.WEB.repository.KorisnikRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class KorisnikService {
    @Autowired
    private KorisnikRepository korisnikRepository;
    public Korisnik login(String username, String password) {
        Korisnik korisnik = korisnikRepository.getByMail(username);
        if(korisnik == null || !korisnik.getLozinka().equals(password))
            return null;
        return  korisnik;
    }
    public Korisnik create(RegisterDto registerDto) {
        Korisnik korisnik = new Korisnik();
        korisnik.setIme(registerDto.getIme());
        korisnik.setKorisnickoIme(registerDto.getKorisnickoIme());
        korisnik.setMail(registerDto.getMail());
        korisnik.setLozinka(registerDto.getLozinka());

        korisnik.setUloga(Korisnik.Uloga.CITALAC);

        return save(korisnik);
    }

    public Boolean existsMail(String mail) { return korisnikRepository.existsByMail(mail); }
    public Boolean existsLozinka(String mail) { return korisnikRepository.existsByLozinka(mail); }
    public Boolean existsKorisnickoIme(String mail) { return korisnikRepository.existsByKorisnickoIme(mail); }

    public Korisnik save(Korisnik korisnik) { return korisnikRepository.save(korisnik);}

}

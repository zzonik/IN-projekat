package com.INprojekat.WEB.service;

import com.INprojekat.WEB.dto.AutorRegisterDto;
import com.INprojekat.WEB.dto.KnjigaAutorDto;
import com.INprojekat.WEB.entity.Autor;
import com.INprojekat.WEB.entity.Knjiga;
import com.INprojekat.WEB.entity.Korisnik;
import com.INprojekat.WEB.repository.AutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AutorService {

    @Autowired
    private AutorRepository autorRepository;
    public Autor findOne(Long id) {
        return (Autor) autorRepository.findById(id).orElse(null);
    }

    public Autor create(AutorRegisterDto autorRegisterDto) {
        Autor autor = new Autor();
        autor.setIme(autorRegisterDto.getIme());
        autor.setPrezime(autorRegisterDto.getPrezime());
        autor.setKorisnickoIme(autorRegisterDto.getKorisnickoIme());
        autor.setMail(autorRegisterDto.getMail());
        autor.setLozinka(autorRegisterDto.getLozinka());

        autor.setUloga(Korisnik.Uloga.AUTOR);

        return save(autor);
    }
    public Boolean existsMail(String mail) { return autorRepository.existsByMail(mail); }
    public Boolean existsLozinka(String mail) { return autorRepository.existsByLozinka(mail); }
    public Boolean existsKorisnickoIme(String mail) { return autorRepository.existsByKorisnickoIme(mail); }

    public Autor save(Autor autor) { return autorRepository.save(autor);}

}

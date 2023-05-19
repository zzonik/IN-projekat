package com.INprojekat.WEB.service;

<<<<<<< HEAD
import com.INprojekat.WEB.dto.RegisterDto;
=======
<<<<<<< Updated upstream
=======
import com.INprojekat.WEB.dto.KorisnikDto;
import com.INprojekat.WEB.dto.RegisterDto;
import com.INprojekat.WEB.dto.UpdateDto;
>>>>>>> Stashed changes
>>>>>>> Search-and-view
import com.INprojekat.WEB.entity.Korisnik;
import com.INprojekat.WEB.repository.KorisnikRepository;
import jakarta.persistence.Id;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

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
<<<<<<< HEAD
=======
<<<<<<< Updated upstream
=======
>>>>>>> Search-and-view
    public Korisnik create(RegisterDto registerDto) {
        Korisnik korisnik = new Korisnik();
        korisnik.setIme(registerDto.getIme());
        korisnik.setKorisnickoIme(registerDto.getKorisnickoIme());
        korisnik.setMail(registerDto.getMail());
        korisnik.setLozinka(registerDto.getLozinka());

        korisnik.setUloga(Korisnik.Uloga.CITALAC);

        return save(korisnik);
    }

<<<<<<< HEAD
=======
    public Korisnik findOne(Long id){
        Optional<Korisnik> foundKorisnik = korisnikRepository.findById(id);
        if (foundKorisnik.isPresent())
            return foundKorisnik.get();

        return null;
    }

    public Korisnik updateUser(Long id, UpdateDto updateDto){
        Korisnik korisnik = findOne(id);

        if(updateDto.getLozinka().equals(updateDto.getNewlozinka())){
            throw new IllegalArgumentException("Lozinka se ne podudara");
        }

        korisnik.setIme(updateDto.getIme());
        korisnik.setPrezime(updateDto.getPrezime());
        korisnik.setProfilnaSlika(updateDto.getNaslovnaFotografija());
        korisnik.setDatumRodjenja(updateDto.getDatumRodjenja());
        korisnik.setOpis(updateDto.getOpis());

        if(korisnik.getLozinka() != null && !updateDto.getLozinka().isEmpty()) {

            korisnik.setLozinka(updateDto.getLozinka());
        }

        if(korisnik.getMail() != null && !updateDto.getMail().isEmpty()){

            korisnik.setMail(updateDto.getMail());
        }

        return save(korisnik);
    }

>>>>>>> Search-and-view
    public Boolean existsMail(String mail) { return korisnikRepository.existsByMail(mail); }
    public Boolean existsLozinka(String mail) { return korisnikRepository.existsByLozinka(mail); }
    public Boolean existsKorisnickoIme(String mail) { return korisnikRepository.existsByKorisnickoIme(mail); }

    public Korisnik save(Korisnik korisnik) { return korisnikRepository.save(korisnik);}

<<<<<<< HEAD
=======
>>>>>>> Stashed changes
>>>>>>> Search-and-view
}

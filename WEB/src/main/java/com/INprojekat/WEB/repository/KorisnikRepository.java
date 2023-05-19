package com.INprojekat.WEB.repository;

import com.INprojekat.WEB.entity.Korisnik;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KorisnikRepository extends JpaRepository<Korisnik, Long> {
    Korisnik getByMail(String mail);
<<<<<<< Updated upstream
=======
    Boolean existsByMail(String mail);
    Boolean existsByLozinka(String mail);
    Boolean existsByKorisnickoIme(String mail);


>>>>>>> Stashed changes
}

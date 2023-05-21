package com.INprojekat.WEB.repository;

import com.INprojekat.WEB.entity.Knjiga;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KnjigaRepository extends JpaRepository<Knjiga, Long> {
    Boolean existsByNaziv(String naziv);
}

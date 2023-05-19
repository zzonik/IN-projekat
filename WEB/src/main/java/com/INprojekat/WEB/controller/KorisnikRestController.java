package com.INprojekat.WEB.controller;

import com.INprojekat.WEB.dto.KnjigaDto;
import com.INprojekat.WEB.dto.LoginDto;
import com.INprojekat.WEB.dto.RegisterDto;
import com.INprojekat.WEB.dto.*;
import com.INprojekat.WEB.entity.Knjiga;
import com.INprojekat.WEB.entity.Korisnik;
import com.INprojekat.WEB.repository.KorisnikRepository;
import com.INprojekat.WEB.service.KorisnikService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class KorisnikRestController {
    @Autowired
    private KorisnikService korisnikService;
    @GetMapping("/api/")
    public String welcome(){
        return "Hello from api!";
    }

    @PostMapping("api/login")
    public ResponseEntity<String> login(@RequestBody LoginDto loginDto, HttpSession session){
        if(loginDto.getMail().isEmpty() || loginDto.getLozinka().isEmpty())
            return new ResponseEntity("Invalid login data", HttpStatus.BAD_REQUEST);

        Korisnik loggedKorisnik = korisnikService.login(loginDto.getMail(), loginDto.getLozinka());
        if (loggedKorisnik == null)
            return new ResponseEntity<>("User does not exist!", HttpStatus.NOT_FOUND);

        session.setAttribute("employee", loggedKorisnik);
        return ResponseEntity.ok("Successfully logged in!");
    }

    @PostMapping("api/logout")
    public ResponseEntity Logout(HttpSession session){
        Korisnik loggedKorisnik = (Korisnik) session.getAttribute("employee");

        if (loggedKorisnik == null)
            return new ResponseEntity("Forbidden", HttpStatus.FORBIDDEN);

        session.invalidate();
        return new ResponseEntity("Successfully logged out", HttpStatus.OK);
    }

    @PostMapping("api/register")
    public ResponseEntity<?> registerUser(@RequestBody RegisterDto registerDto){

        if(korisnikService.existsMail(registerDto.getMail())){
            return new ResponseEntity<>("Mail je zauzet!", HttpStatus.BAD_REQUEST);
        }
        if(korisnikService.existsKorisnickoIme(registerDto.getMail())){
            return new ResponseEntity<>("Korisnicko ime je zauzeto!", HttpStatus.BAD_REQUEST);
        }
        if(korisnikService.existsLozinka(registerDto.getLozinka())){
            return new ResponseEntity<>("Loznika je zauzeta!", HttpStatus.BAD_REQUEST);
        }
        korisnikService.create(registerDto);

        return new ResponseEntity<>("User registered successfully", HttpStatus.OK);
    }

    @PutMapping("/update_user")
    public ResponseEntity<?> updateUser(@RequestBody UpdateDto updateDto,HttpSession session) {
        Korisnik loggedKorisnik = (Korisnik) session.getAttribute("employee");

        if (loggedKorisnik == null)
            return new ResponseEntity("Forbidden", HttpStatus.FORBIDDEN);

        korisnikService.updateUser( loggedKorisnik.getId() ,updateDto);

        return new ResponseEntity<>("User updateded successfully", HttpStatus.OK);

    }

}

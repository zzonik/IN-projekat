package com.INprojekat.WEB.controller;

import com.INprojekat.WEB.dto.KnjigaDto;
import com.INprojekat.WEB.dto.LoginDto;
import com.INprojekat.WEB.dto.RegisterDto;
import com.INprojekat.WEB.dto.*;
import com.INprojekat.WEB.entity.Autor;
import com.INprojekat.WEB.entity.Knjiga;
import com.INprojekat.WEB.entity.Korisnik;
import com.INprojekat.WEB.entity.ZahtevZaAktivacijuNalogaAutora;
import com.INprojekat.WEB.repository.KorisnikRepository;
import com.INprojekat.WEB.service.*;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class KorisnikRestController {
    @Autowired
    private KorisnikService korisnikService;
    @Autowired
    private PolicaService policaService;
    @Autowired
    private AutorService autorService;

    @Autowired
    private ZanrService zanrService;
    @Autowired
    private ZahtevZaAktivacijuNalogaAutoraService zahtevZaAktivacijuNalogaAutoraService;

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
        policaService.main3();

        return new ResponseEntity<>("User registered successfully", HttpStatus.OK);
    }

    @PutMapping("api/update_user")
    public ResponseEntity<?> updateUser(@RequestBody UpdateDto updateDto,HttpSession session) {
        Korisnik loggedKorisnik = (Korisnik) session.getAttribute("employee");

        if (loggedKorisnik == null)
            return new ResponseEntity("Forbidden", HttpStatus.FORBIDDEN);

        korisnikService.updateUser( loggedKorisnik.getId() ,updateDto);

        return new ResponseEntity<>("User updateded successfully", HttpStatus.OK);
    }

    @PostMapping("api/zahtev-create")
    public ResponseEntity<?> zahtev(@RequestBody ZahtevDto zahtevDto) {
        if(zahtevZaAktivacijuNalogaAutoraService.create(zahtevDto) == null)
            return new ResponseEntity<>("Ne postoji autor", HttpStatus.BAD_REQUEST);
        return ResponseEntity.ok("Zahtev dodat");
    }
    @PostMapping("/api/admin/zahtev/{id}/accept")
    public ResponseEntity<?> zahtevAccept(@PathVariable("id") Long zahtevId, HttpSession session) {
        Korisnik loggedKorisnik = (Korisnik) session.getAttribute("employee");
        if(loggedKorisnik.getUloga() == Korisnik.Uloga.ADMINISTRATOR){

            ZahtevZaAktivacijuNalogaAutoraDto zanaDto = zahtevZaAktivacijuNalogaAutoraService.findOne(zahtevId);
            String meil = zanaDto.getEmail();
            String meilAutora = zanaDto.getKorisnik().getMail();
            String loznikaAutora = zanaDto.getKorisnik().getLozinka();

            zanaDto.setStatus(ZahtevZaAktivacijuNalogaAutora.Status.odobren);
            zahtevZaAktivacijuNalogaAutoraService.saveDto(zanaDto);

            Long ID = zanaDto.getKorisnik().getId();
            Autor autor = autorService.findOne(ID);
            autor.setAktivnost(true);
            autorService.save(autor);
            policaService.main3();

            Properties properties = new Properties();
            properties.put("mail.smtp.auth", "true");
            properties.put("mail.smtp.starttls.enable", "true");
            properties.put("mail.smtp.host", "localhost");
            properties.put("mail.smtp.port", "25");

            // Email account credentials
            String username = "testtestdsds123@gmail.com";
            String password = "Test!!!123";

            // Email recipient
            //String recipient = "recipient@example.com";
            String recipient = meil;

            // Email content
            String subject = "Mail i lozinka AUTORA";
            String messageContent = "Mail: " + meilAutora + " Loznika: " + loznikaAutora;

            // Create a session with the email server
            Session sessionMail = Session.getInstance(properties, new Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(username, password);
                }
            });

            try {
                // Create a new email message
                MimeMessage message = new MimeMessage(sessionMail);
                message.setFrom(new InternetAddress(username));
                message.addRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
                message.setSubject(subject);
                message.setText(messageContent);

                // Send the email
                Transport.send(message);

                return new ResponseEntity<>("Email sent", HttpStatus.OK);
            } catch (MessagingException e) {
                e.printStackTrace();
                return new ResponseEntity<>("Failed to send email", HttpStatus.INTERNAL_SERVER_ERROR);
            }

        }else {
            return new ResponseEntity<>("Niste administrator", HttpStatus.BAD_REQUEST);
        }
    }
    @PostMapping("/api/admin/zahtev/{id}/decline")
    public ResponseEntity<?> zahtevDecline(@PathVariable("id") Long zahtevId, HttpSession session) {
        Korisnik loggedKorisnik = (Korisnik) session.getAttribute("employee");
        if(loggedKorisnik.getUloga() == Korisnik.Uloga.ADMINISTRATOR){

            ZahtevZaAktivacijuNalogaAutoraDto zanaDto = zahtevZaAktivacijuNalogaAutoraService.findOne(zahtevId);
            String meil = zanaDto.getEmail();

            zanaDto.setStatus(ZahtevZaAktivacijuNalogaAutora.Status.odbijen);
            zahtevZaAktivacijuNalogaAutoraService.saveDto(zanaDto);

            Properties properties = new Properties();
            properties.put("mail.smtp.auth", "true");
            properties.put("mail.smtp.starttls.enable", "true");
            properties.put("mail.smtp.host", "localhost");
            properties.put("mail.smtp.port", "25");

            // Email account credentials
            String username = "testtestdsds123@gmail.com";
            String password = "Test!!!123";

            // Email recipient
            //String recipient = "recipient@example.com";
            String recipient = meil;

            // Email content
            String subject = "Obavestenje";
            String messageContent = "Zao nam je, vas zahtev je odbijen";

            // Create a session with the email server
            Session sessionMail = Session.getInstance(properties, new Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(username, password);
                }
            });

            try {
                // Create a new email message
                MimeMessage message = new MimeMessage(sessionMail);
                message.setFrom(new InternetAddress(username));
                message.addRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
                message.setSubject(subject);
                message.setText(messageContent);

                // Send the email
                Transport.send(message);

                return new ResponseEntity<>("Email sent", HttpStatus.OK);
            } catch (MessagingException e) {
                e.printStackTrace();
                return new ResponseEntity<>("Failed to send email", HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }else {
            return new ResponseEntity<>("Niste administrator", HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping("api/zahtev-getAll")
    public ResponseEntity<?> zahtevGet(HttpSession session) {
        List<ZahtevZaAktivacijuNalogaAutoraDto> dtos = zahtevZaAktivacijuNalogaAutoraService.findAll();
        return ResponseEntity.ok(dtos);
    }
    @PostMapping("/api/admin/zanr-add")
    public ResponseEntity<?> addZanr(@RequestBody ZanrAddDto zanrAddDto, HttpSession session) {
        Korisnik loggedKorisnik = (Korisnik) session.getAttribute("employee");
        if(loggedKorisnik.getUloga() == Korisnik.Uloga.ADMINISTRATOR){
            zanrService.create(zanrAddDto);
            return new ResponseEntity<>("Zanr dodat", HttpStatus.OK);
        }else {
            return new ResponseEntity<>("Niste administrator", HttpStatus.BAD_REQUEST);
        }
    }

}

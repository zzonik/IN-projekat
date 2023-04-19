INSERT INTO AUTOR (aktivnost, knjige) VALUES (1, Knjiga knjige);

INSERT INTO KNJIGA (naslov, IMAGE, ISBN, datumObjavljivanja, brojStrana, opis, ocena) VALUES ('Naruto',SLIKA,'1999-09-27',128);

INSERT INTO KORISNIK (ime, prezime, korisnickoIme, mail, lozinka, datumRodjenja, IMAGE, opis, uloga) VALUES ('Dule','Savic','Dule_Rana','1234crvenkapa','1955-06-01',LOAD_FILE(),'Visok, mlad i lep','Bivsi fudbaler');

INSERT INTO POLICA (naziv, primarna) VALUES ('mange', 1);

INSERT INTO RECENZIJA (ocena, tekst, datum, korisnik) VALUES (5, 'Manga za svaku preporuku', '2019-12-28', );

INSERT INTO STAVKAPOLICE (recenzija, knjiga, polica) VALUES (, , );

INSERT INTO ZAHTEVZAAKTIVACIJUNALOGAAUTORA (email, telefon, poruka, datum, status) VALUES ();

INSERT INTO ZANR (naziv) VALUES ('Drama');
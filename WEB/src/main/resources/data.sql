INSERT INTO KNJIGA (naslov, IMAGE, ISBN, date, pages, opis, ocena) VALUES ('Naruto','slika.jpg','E821932', '1999-09-27', 44, 'Manga',8);

INSERT INTO AUTOR (aktivnost, knjige) VALUES (1, 1);

INSERT INTO KORISNIK (ime, prezime, username, mail, lozinka, date, IMAGE, opis, uloga) VALUES ('Dule','Savic','Dule_Rana','dulesavic55@gmaiil.com','1234crvenkapa','1955-06-01','slika1.jpg','Visok, mlad i lep',1);

INSERT INTO POLICA (naziv, primarna) VALUES ('mange', 1);

INSERT INTO RECENZIJA (ocena, tekst, datum, korisnik_id) VALUES (5, 'Manga za svaku preporuku', '2019-12-28', 1);

INSERT INTO ZAHTEV (email, telefon, poruka, datum, status) VALUES ('dukisuzuki55@gmail.com', '0694201312', 'Zelim da aktiviram nalog za autora. ', '2018-12-12', 1);

INSERT INTO STAVKE (recenzija_id, knjiga_id, polica_id) VALUES (1, 1, 1);

INSERT INTO ZANR VALUES (1, 'Drama');

INSERT INTO KNJIGAZANR (zanr_id, knjiga_id) VALUES (1, 1);

INSERT INTO KNJIGAAUTOR (knjiga_id, autor_id) VALUES (1, 1);
INSERT INTO "banka" ("id", "naziv", "kontakt","pib")
VALUES(nextval('banka_seq'), 'Uni Credit', '+381 11 30 50 505','1659874523');

INSERT INTO "banka" ("id", "naziv", "kontakt","pib")
VALUES(nextval('banka_seq'), 'Erste', '+381 22 85 50 505','1874651235');


INSERT INTO "korisnik_usluga" ("id", "ime", "prezime","maticni_broj")
VALUES(nextval('korisnik_usluga_seq'), 'Jovana', 'Tunic','1107001879512');

INSERT INTO "korisnik_usluga" ("id", "ime", "prezime","maticni_broj")
VALUES(nextval('korisnik_usluga_seq'), 'Stefan', 'Milic','0505005845965');


INSERT INTO "filijala" ("id", "adresa", "broj_pultova","poseduje_sef","banka")
VALUES(nextval('filijala_seq'), 'Vase Stajica 22, Novi Sad', '8','true',1);

INSERT INTO "filijala" ("id", "adresa", "broj_pultova","poseduje_sef","banka")
VALUES(nextval('filijala_seq'), 'Zitni trg 1, Novi Sad', 2,'false',1);

INSERT INTO "usluga" ("id", "naziv", "opis_usluge","datum_ugovora","provizija","filijala","korisnik_usluga")
VALUES(nextval('usluga_seq'), 'Kredit', 'Izdavanje kredita uz kamatnu stopu od 5%', to_date('04.03.2017.', 'dd.mm.yyyy.'),0,1,1);










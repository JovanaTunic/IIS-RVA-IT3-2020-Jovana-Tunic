INSERT INTO "banka" ("id", "naziv", "kontakt","pib")
VALUES(nextval('banka_seq'), 'Uni Credit', '+381 11 30 50 505','1659874523');

INSERT INTO "banka" ("id", "naziv", "kontakt","pib")
VALUES(nextval('banka_seq'), 'Erste', '+381 22 85 50 505','1874651235');

INSERT INTO "banka" ("id", "naziv", "kontakt","pib")
VALUES(nextval('banka_seq'), 'Raiffeisen', '+381 60 48 40 005','1856231548');

INSERT INTO "banka" ("id", "naziv", "kontakt","pib")
VALUES(nextval('banka_seq'), 'OTP', '+381 69 85 40 505','1589569841');


INSERT INTO "korisnik_usluga" ("id", "ime", "prezime","maticni_broj")
VALUES(nextval('korisnik_usluga_seq'), 'Jovana', 'Tunic','1107001879512');

INSERT INTO "korisnik_usluga" ("id", "ime", "prezime","maticni_broj")
VALUES(nextval('korisnik_usluga_seq'), 'Stefan', 'Milic','0505005845965');

INSERT INTO "korisnik_usluga" ("id", "ime", "prezime","maticni_broj")
VALUES(nextval('korisnik_usluga_seq'), 'Ivana', 'Nikolin','2909001845758');

INSERT INTO "korisnik_usluga" ("id", "ime", "prezime","maticni_broj")
VALUES(nextval('korisnik_usluga_seq'), 'Arija', 'Stefanovic','1254896523025');


INSERT INTO "filijala" ("id", "adresa", "broj_pultova","poseduje_sef","banka")
VALUES(nextval('filijala_seq'), 'Vase Stajica 22, Novi Sad', '8','true',3);

INSERT INTO "filijala" ("id", "adresa", "broj_pultova","poseduje_sef","banka")
VALUES(nextval('filijala_seq'), 'Bulevar Oslobođenja 30, Novi Sad', '10','true',1);

INSERT INTO "filijala" ("id", "adresa", "broj_pultova","poseduje_sef","banka")
VALUES(nextval('filijala_seq'), 'Zitni trg 1, Novi Sad', 2,'false',1);

INSERT INTO "filijala" ("id", "adresa", "broj_pultova","poseduje_sef","banka")
VALUES(nextval('filijala_seq'), 'Železnička 3, Novi Sad', 2,'false',4);


INSERT INTO "usluga" ("id", "naziv", "opis_usluge","datum_ugovora","provizija","filijala","korisnik_usluga")
VALUES(nextval('usluga_seq'), 'Kredit', 'Izdavanje kredita uz kamatnu stopu od 5%', to_date('04.03.2017.', 'dd.mm.yyyy.'),0,1,1);

INSERT INTO "usluga" ("id", "naziv", "opis_usluge","datum_ugovora","provizija","filijala","korisnik_usluga")
VALUES(nextval('usluga_seq'), 'Platna transakcija', 'Prenos novcanih sredstava', to_date('01.08.2022.', 'dd.mm.yyyy.'),0.2,1,1);

INSERT INTO "usluga" ("id", "naziv", "opis_usluge","datum_ugovora","provizija","filijala","korisnik_usluga")
VALUES(nextval('usluga_seq'), 'Otvaranje racuna', 'Otvaranje racuna i izrada kartice', to_date('04.04.2023.', 'dd.mm.yyyy.'),1.2,3,4);

INSERT INTO "usluga" ("id", "naziv", "opis_usluge","datum_ugovora","provizija","filijala","korisnik_usluga")
VALUES(nextval('usluga_seq'), 'Poslovni racun', 'Otvaranje poslovnog racuna', to_date('05.05.2021.', 'dd.mm.yyyy.'),0.2,2,3);








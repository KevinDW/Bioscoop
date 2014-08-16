-- -----------------------------------------------------
-- Schema bioscoop
-- -----------------------------------------------------
USE bioscoop ;

-- -----------------------------------------------------
-- Table bioscoop
-- -----------------------------------------------------
INSERT INTO bioscoop(naam,straat,postcode,gemeente) VALUES("Kinepolis Antwerpen", "Groenendaallaan", 2030, "Antwerpen");
INSERT INTO bioscoop(naam,straat,postcode,gemeente) VALUES("Kinepolis Brussel", "Eeuwfeestlaan", 1020, "Brussel");
INSERT INTO bioscoop(naam,straat,postcode,gemeente) VALUES("Utopolis Mechelen", "Spuibeekstraat", 2800, "Mechelen");
INSERT INTO bioscoop(naam,straat,postcode,gemeente) VALUES("Utopolis Antwerpen", "Groenendaallaan", 2030, "Antwerpen");
INSERT INTO bioscoop(naam,straat,postcode,gemeente) VALUES("Kinepolis Gent", "Ter Platen", 9000, "Gent");
INSERT INTO bioscoop(naam,straat,postcode,gemeente) VALUES("Kinepolis Hasselt", "Via Media", 3500, "Hasselt");


-- -----------------------------------------------------
-- Table zaal
-- -----------------------------------------------------

INSERT INTO zaal(zaalNr,capaciteit,maxRij,maxKolom,verdieping,bioscoopId) VALUES(1,224,16,14,1,1);
INSERT INTO zaal(zaalNr,capaciteit,maxRij,maxKolom,verdieping,bioscoopId) VALUES(2,198,18,11,1,1);
INSERT INTO zaal(zaalNr,capaciteit,maxRij,maxKolom,verdieping,bioscoopId) VALUES(1,252,21,12,1,2);
INSERT INTO zaal(zaalNr,capaciteit,maxRij,maxKolom,verdieping,bioscoopId) VALUES(1,480,40,12,2,4);

-- -----------------------------------------------------
-- Table genre
-- -----------------------------------------------------

INSERT INTO genre(naam) VALUES("Actie");
INSERT INTO genre(naam) VALUES("Drama");
INSERT INTO genre(naam) VALUES("Thriller");
INSERT INTO genre(naam) VALUES("Komedie");

-- -----------------------------------------------------
-- Table restrictie
-- -----------------------------------------------------

INSERT INTO restrictie(naam) VALUES(9);
INSERT INTO restrictie(naam) VALUES(12);
INSERT INTO restrictie(naam) VALUES(16);
INSERT INTO restrictie(naam) VALUES(18);
INSERT INTO restrictie(naam) VALUES("ALL");

-- -----------------------------------------------------
-- Table film
-- -----------------------------------------------------

INSERT INTO film(naam,code,duur,beoordeling,datum,restrictieId,genreId) VALUES("Lucy","LUCY",100,7,"2014-07-31",3,2);
INSERT INTO film(naam,code,duur,beoordeling,datum,restrictieId,genreId) VALUES("Dawn of the Planet of The Apes","DAWN",130,7,"2014-07-17",2,1);
INSERT INTO film(naam,code,duur,beoordeling,datum,restrictieId,genreId) VALUES("The Purge: Anarchy","THEP",99,7,"2014-07-24",4,3);

-- -----------------------------------------------------
-- Table programmatie
-- -----------------------------------------------------

INSERT INTO programmatie(datum,beginUur,zaalId,filmId) VALUES("2014-08-04","20:00:00",1,1);
INSERT INTO programmatie(datum,beginUur,zaalId,filmId) VALUES("2014-08-04","22:30:00",2,1);
INSERT INTO programmatie(datum,beginUur,zaalId,filmId) VALUES("2014-08-04","20:00:00",3,2);
INSERT INTO programmatie(datum,beginUur,zaalId,filmId) VALUES("2014-08-04","22:30:00",4,1);
INSERT INTO programmatie(datum,beginUur,zaalId,filmId) VALUES("2014-08-11","20:00:00",1,1);


-- -----------------------------------------------------
-- Table barcode
-- -----------------------------------------------------

INSERT INTO barcode(code,gebruikt) VALUES('201408042000LUCY0604',1);
INSERT INTO barcode(code,gebruikt) VALUES('201408042230LUCY0802',0);
INSERT INTO barcode(code,gebruikt) VALUES('201408042000DAWN0508',1);
INSERT INTO barcode(code,gebruikt) VALUES('201408042000LUCY0605',1);
INSERT INTO barcode(code,gebruikt) VALUES('201408042000LUCY0806',1);
INSERT INTO barcode(code,gebruikt) VALUES('201408042000LUCY0807',1);
INSERT INTO barcode(code,gebruikt) VALUES('201408042000LUCY2603',1);
INSERT INTO barcode(code,gebruikt) VALUES('201408042000LUCY2604',1);

-- -----------------------------------------------------
-- Table ticket
-- -----------------------------------------------------

INSERT INTO ticket(prijs,programmatieId,barcodeId) VALUES(9,1,1);
INSERT INTO ticket(prijs,programmatieId,barcodeId) VALUES(9,2,2);
INSERT INTO ticket(prijs,programmatieId,barcodeId) VALUES(9,3,3);
INSERT INTO ticket(prijs,programmatieId,barcodeId) VALUES(9,1,4);
INSERT INTO ticket(prijs,programmatieId,barcodeId) VALUES(9,1,5);
INSERT INTO ticket(prijs,programmatieId,barcodeId) VALUES(9,1,6);
INSERT INTO ticket(prijs,programmatieId,barcodeId) VALUES(8,4,7);
INSERT INTO ticket(prijs,programmatieId,barcodeId) VALUES(8,4,8);

-- -----------------------------------------------------
-- Table klant
-- -----------------------------------------------------

INSERT INTO klant(naam,email) VALUES("Jos Vermeulen","jos.vermeulen@outlook.com");
INSERT INTO klant(naam,email) VALUES("Patrick Vanderspiegel","patrick.vanderspiegel@telenet.be");
INSERT INTO klant(naam,email) VALUES("Magda Aelvoet","magda.aelvoet@skynet.be");

-- -----------------------------------------------------
-- Table bestelling
-- -----------------------------------------------------

INSERT INTO bestelling(klantId,ticketId) VALUES(1,1);
INSERT INTO bestelling(klantId,ticketId) VALUES(1,2);
INSERT INTO bestelling(klantId,ticketId) VALUES(2,3);
INSERT INTO bestelling(klantId,ticketId) VALUES(2,4);
INSERT INTO bestelling(klantId,ticketId) VALUES(2,5);
INSERT INTO bestelling(klantId,ticketId) VALUES(2,6);
INSERT INTO bestelling(klantId,ticketId) VALUES(3,7);
INSERT INTO bestelling(klantId,ticketId) VALUES(3,8);

-- -----------------------------------------------------
-- Table social
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Table bioscoop
-- -----------------------------------------------------
INSERT INTO bioscoop(naam,postcode,gemeente,straat) VALUES("Kinepolis Antwerpen", 2030, "Antwerpen", "Groenendaallaan");
INSERT INTO bioscoop(naam,postcode,gemeente,straat) VALUES("Kinepolis Brussel", 1020, "Brussel", "Eeuwfeestlaan");
INSERT INTO bioscoop(naam,postcode,gemeente,straat) VALUES("Utopolis Mechelen", 2800, "Mechelen", "Spuibeekstraat");
INSERT INTO bioscoop(naam,postcode,gemeente,straat) VALUES("Utopolis Antwerpen", 2030, "Antwerpen", "Groenendaallaan");
INSERT INTO bioscoop(naam,postcode,gemeente,straat) VALUES("Kinepolis Gent", 9000, "Gent", "Ter Platen");
INSERT INTO bioscoop(naam,postcode,gemeente,straat) VALUES("Kinepolis Hasselt", 3500, "Hasselt", "Via Media");


-- -----------------------------------------------------
-- Table zaal
-- -----------------------------------------------------

INSERT INTO zaal(zaalNr,capaciteit,maxRij,maxKolom,verdieping,bioscoopId) VALUES(1,224,16,14,1,1);
INSERT INTO zaal(zaalNr,capaciteit,maxRij,maxKolom,verdieping,bioscoopId) VALUES(2,198,18,11,1,1);
INSERT INTO zaal(zaalNr,capaciteit,maxRij,maxKolom,verdieping,bioscoopId) VALUES(1,252,21,12,1,2);
INSERT INTO zaal(zaalNr,capaciteit,maxRij,maxKolom,verdieping,bioscoopId) VALUES(1,480,40,12,2,4);

-- -----------------------------------------------------
-- Table restrictie
-- -----------------------------------------------------

INSERT INTO restrictie(naam) VALUES(9);
INSERT INTO restrictie(naam) VALUES(12);
INSERT INTO restrictie(naam) VALUES(16);
INSERT INTO restrictie(naam) VALUES(18);
INSERT INTO restrictie(naam) VALUES(ALL);

-- -----------------------------------------------------
-- Table film
-- -----------------------------------------------------

INSERT INTO film(naam,code,duur,genre,beoordeling,releaseDate,restrictieId) VALUES("Lucy","LUCY",100,"Action",7,"2014-07-31",3);
INSERT INTO film(naam,code,duur,genre,beoordeling,releaseDate,restrictieId) VALUES("Dawn of the Planet of The Apes","DAWN",130,"Action",7,"2014-07-17",2);
INSERT INTO film(naam,code,duur,genre,beoordeling,releaseDate,restrictieId) VALUES("The Purge: Anarchy","THEP",99,"Thriller",7,"2014-07-24",4);


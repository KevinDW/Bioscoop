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


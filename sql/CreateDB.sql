-- -----------------------------------------------------
-- Schema bioscoop
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS bioscoop ;

CREATE SCHEMA IF NOT EXISTS bioscoop DEFAULT CHARACTER SET utf8 ;
USE bioscoop ;

-- -----------------------------------------------------
-- Table bioscoop
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS bioscoop (
  id INT NOT NULL AUTO_INCREMENT,
  naam VARCHAR(45) NOT NULL,
  straat VARCHAR(45) NOT NULL,
  postcode VARCHAR(45) NOT NULL,
  gemeente VARCHAR(45) NOT NULL,
  PRIMARY KEY (id))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table zaal
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS zaal (
  id INT NOT NULL AUTO_INCREMENT,
  zaalNr INT NOT NULL,
  capaciteit INT NOT NULL,
  maxRij INT NOT NULL,
  maxKolom INT NOT NULL,
  verdieping INT NOT NULL,
  bioscoopId INT NOT NULL,
  PRIMARY KEY (id),
  INDEX FK_Zaal_Bioscoop_idx (bioscoopId ASC),
  CONSTRAINT FK_Zaal_Bioscoop
    FOREIGN KEY (bioscoopId)
    REFERENCES bioscoop (id)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table genre
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS genre (
  id INT NOT NULL AUTO_INCREMENT,
  naam VARCHAR(45) NOT NULL,
  PRIMARY KEY (id))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table restrictie
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS restrictie (
  id INT NOT NULL AUTO_INCREMENT,
  naam VARCHAR(45) NOT NULL,
  PRIMARY KEY (id))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table film
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS film (
  id INT NOT NULL AUTO_INCREMENT,
  naam VARCHAR(45) NOT NULL,
  code CHAR(4) NOT NULL,
  jaar INT NOT NULL,
  duur INT NOT NULL,
  beoordeling DECIMAL NULL,
  restrictieId INT NOT NULL,
  genreId INT NOT NULL,
  PRIMARY KEY (id),
  INDEX FK_Film_Restrictie_idx (restrictieId ASC),
  INDEX FK_Film_Genre_idx (genreId ASC),
  CONSTRAINT FK_Film_Restrictie
    FOREIGN KEY (restrictieId)
    REFERENCES restrictie (id)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT FK_Film_Genre
    FOREIGN KEY (genreId)
    REFERENCES genre (id)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table programmatie
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS programmatie (
  id INT NOT NULL AUTO_INCREMENT,
  datum DATE NOT NULL,
  beginUur TIME NOT NULL,
  zaalId INT NOT NULL,
  filmId INT NOT NULL,
  PRIMARY KEY (id),
  INDEX FK_Ticket_idx (zaalId ASC),
  INDEX FK_Programmatie_Film_idx (filmId ASC),
  CONSTRAINT FK_Programmatie_Zaal
    FOREIGN KEY (zaalId)
    REFERENCES zaal (id)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT FK_Programmatie_Film
    FOREIGN KEY (filmId)
    REFERENCES film (id)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table klant
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS klant (
  id INT NOT NULL AUTO_INCREMENT,
  naam VARCHAR(45) NOT NULL,
  email VARCHAR(100) NOT NULL,
  PRIMARY KEY (id))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table ticket
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS ticket (
  id INT NOT NULL AUTO_INCREMENT,
  prijs DECIMAL NOT NULL,
  klantId INT NOT NULL,
  programmatieId INT NOT NULL,
  PRIMARY KEY (id),
  INDEX FK_Ticket_Klant_idx (klantId ASC),
  INDEX FK_Ticket_Programmatie_idx (programmatieId ASC),
  CONSTRAINT FK_Ticket_Klant
    FOREIGN KEY (klantId)
    REFERENCES klant (id)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT FK_Ticket_Programmatie
    FOREIGN KEY (programmatieId)
    REFERENCES programmatie (id)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table barcode
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS barcode (
  id INT NOT NULL AUTO_INCREMENT,
  code VARCHAR(20) NOT NULL,
  gebruikt BIT NOT NULL,
  ticketId INT NOT NULL,
  PRIMARY KEY (id),
  INDEX FK_Barcode_Ticket_idx (ticketId ASC),
  CONSTRAINT FK_Barcode_Ticket
    FOREIGN KEY (ticketId)
    REFERENCES ticket (id)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table social
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS social (
  id INT NOT NULL AUTO_INCREMENT,
  datum DATE NULL,
  type VARCHAR(45) NULL,
  bericht TEXT NULL,
  filmId INT NULL,
  PRIMARY KEY (id),
  INDEX fk_social_film_idx (filmId ASC),
  CONSTRAINT fk_social_film
    FOREIGN KEY (filmId)
    REFERENCES film (id)
    ON DELETE CASCADE
    ON UPDATE CASCADE);

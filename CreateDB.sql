SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema bioscoop
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS bioscoop DEFAULT CHARACTER SET utf8 ;
USE bioscoop ;

-- -----------------------------------------------------
-- Table bioscoop.bioscoop
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS bioscoop.bioscoop (
  id INT NOT NULL AUTO_INCREMENT,
  naam VARCHAR(45) NOT NULL,
  postcode VARCHAR(45) NOT NULL,
  gemeente VARCHAR(45) NOT NULL,
  straat VARCHAR(45) NOT NULL,
  PRIMARY KEY (id))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table bioscoop.zaal
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS bioscoop.zaal (
  id INT NOT NULL AUTO_INCREMENT,
  zaalNr INT NOT NULL,
  capaciteit INT NOT NULL,
  maxRij CHAR(2) NOT NULL,
  maxKolom INT NOT NULL,
  verdieping INT NOT NULL,
  bioscoopId INT NOT NULL,
  PRIMARY KEY (id),
  INDEX FK_Zaal_Bioscoop_idx (bioscoopId ASC),
  CONSTRAINT FK_Zaal_Bioscoop
    FOREIGN KEY (bioscoopId)
    REFERENCES bioscoop.bioscoop (id)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table bioscoop.restrictie
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS bioscoop.restrictie (
  id INT NOT NULL AUTO_INCREMENT,
  naam VARCHAR(45) NOT NULL,
  PRIMARY KEY (id))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table bioscoop.film
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS bioscoop.film (
  id INT NOT NULL AUTO_INCREMENT,
  naam VARCHAR(45) NOT NULL,
  code CHAR(4) NOT NULL,
  duur INT NOT NULL,
  genre VARCHAR(45) NOT NULL,
  beoordeling DECIMAL NULL,
  release DATE NOT NULL,
  restrictieId INT NOT NULL,
  PRIMARY KEY (id),
  INDEX FK_Film_Restrictie_idx (restrictieId ASC),
  CONSTRAINT FK_Film_Restrictie
    FOREIGN KEY (restrictieId)
    REFERENCES bioscoop.restrictie (id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table bioscoop.programmatie
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS bioscoop.programmatie (
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
    REFERENCES bioscoop.zaal (id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT FK_Programmatie_Film
    FOREIGN KEY (filmId)
    REFERENCES bioscoop.film (id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table bioscoop.ticket
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS bioscoop.ticket (
  id INT NOT NULL AUTO_INCREMENT,
  prijs DECIMAL NOT NULL,
  programmatieId INT NOT NULL,
  barcodeId INT NOT NULL,
  PRIMARY KEY (id),
  INDEX FK_Ticket_Programmatie_idx (programmatieId ASC),
  INDEX FK_Ticket_Barcode_idx (barcodeId ASC),
  CONSTRAINT FK_Ticket_Programmatie
    FOREIGN KEY (programmatieId)
    REFERENCES bioscoop.programmatie (id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT FK_Ticket_Barcode
    FOREIGN KEY (barcodeId)
    REFERENCES bioscoop.barcode (id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table bioscoop.klant
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS bioscoop.klant (
  id INT NOT NULL AUTO_INCREMENT,
  naam VARCHAR(45) NOT NULL,
  email VARCHAR(100) NOT NULL,
  PRIMARY KEY (id))
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table bioscoop.bestelling
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS bioscoop.bestelling (
  id INT NOT NULL AUTO_INCREMENT,
  klantId INT NOT NULL,
  ticketId INT NOT NULL,
  PRIMARY KEY (id),
  INDEX FK_Bestelling_Klant_idx (klantId ASC),
  INDEX FK_Bestelling_Ticket_idx (ticketId ASC),
  CONSTRAINT FK_Bestelling_Klant
    FOREIGN KEY (klantId)
    REFERENCES bioscoop.klant (id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT FK_Bestelling_Ticket
    FOREIGN KEY (ticketId)
    REFERENCES bioscoop.ticket (id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table bioscoop.barcode
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS bioscoop.barcode (
  id INT NOT NULL AUTO_INCREMENT,
  code VARCHAR(20) NOT NULL,
  gebruikt BIT NOT NULL,
  PRIMARY KEY (id))
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table bioscoop.social
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS bioscoop.social (
  id INT NOT NULL AUTO_INCREMENT,
  datum DATETIME NULL,
  type VARCHAR(45) NULL,
  bericht TEXT NULL,
  filmId INT NULL,
  PRIMARY KEY (id),
  INDEX fk_social_film_idx (filmId ASC),
  CONSTRAINT fk_social_film
    FOREIGN KEY (filmId)
    REFERENCES bioscoop.film (id)
    ON DELETE CASCADE
    ON UPDATE CASCADE);


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema transcend_id
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema transcend_id
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `transcend_id` ;
USE `transcend_id` ;

-- -----------------------------------------------------
-- Table `transcend_id`.`goverment_officer`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `transcend_id`.`goverment_officer` (
  `officer_id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(150) NOT NULL,
  `surname` VARCHAR(150) NOT NULL,
  `password` VARCHAR(250) NOT NULL,
  PRIMARY KEY (`officer_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `transcend_id`.`immigrant`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `transcend_id`.`immigrant` (
  `immigrant_id` VARCHAR(300) NOT NULL,
  `name_surname` VARCHAR(300) NOT NULL,
  `ethnicity` VARCHAR(150) NOT NULL,
  PRIMARY KEY (`immigrant_id`))
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

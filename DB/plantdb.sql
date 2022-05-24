-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema plantdb
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `plantdb` ;

-- -----------------------------------------------------
-- Schema plantdb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `plantdb` DEFAULT CHARACTER SET utf8 ;
USE `plantdb` ;

-- -----------------------------------------------------
-- Table `care`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `care` ;

CREATE TABLE IF NOT EXISTS `care` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `water_type` VARCHAR(45) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `plant`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `plant` ;

CREATE TABLE IF NOT EXISTS `plant` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `care_id` INT NOT NULL,
  `common_name` VARCHAR(500) NOT NULL,
  `description` VARCHAR(500) NULL,
  `image_url` VARCHAR(500) NULL,
  `botanical_name` VARCHAR(500) NULL,
  `type` VARCHAR(500) NULL,
  `care_difficulty` VARCHAR(45) NULL,
  `is_outdoor` TINYINT NULL,
  `is_indoor` TINYINT NULL,
  `is_toxic` TINYINT NULL,
  `water_cycle` VARCHAR(500) NULL,
  `light_requirement` VARCHAR(500) NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_plant_care1_idx` (`care_id` ASC),
  CONSTRAINT `fk_plant_care1`
    FOREIGN KEY (`care_id`)
    REFERENCES `care` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `user`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `user` ;

CREATE TABLE IF NOT EXISTS `user` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `username` VARCHAR(100) NOT NULL,
  `password` VARCHAR(200) NOT NULL,
  `enabled` TINYINT NOT NULL DEFAULT 1,
  `first_name` VARCHAR(100) NULL,
  `last_name` VARCHAR(100) NULL,
  `email` VARCHAR(100) NULL,
  `image_url` VARCHAR(100) NULL,
  `bio` VARCHAR(100) NULL,
  `role` VARCHAR(100) NULL,
  UNIQUE INDEX `username_UNIQUE` (`username` ASC),
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `user_garden`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `user_garden` ;

CREATE TABLE IF NOT EXISTS `user_garden` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `user_id` INT NOT NULL,
  `height` DOUBLE NULL,
  `spread` DOUBLE NULL,
  `water_type` VARCHAR(100) NULL,
  `nickname` VARCHAR(500) NULL,
  `pot_diameter` DOUBLE NULL,
  `image_url` VARCHAR(1000) NULL,
  `home_location` VARCHAR(500) NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_plant_inventory_user1_idx` (`user_id` ASC),
  CONSTRAINT `fk_plant_inventory_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `forum`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `forum` ;

CREATE TABLE IF NOT EXISTS `forum` (
  `id` INT NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `post`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `post` ;

CREATE TABLE IF NOT EXISTS `post` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(500) NOT NULL,
  `content` TEXT(0) NULL,
  `image_url` VARCHAR(500) NULL,
  `create_date` DATETIME NULL,
  `update_date` DATETIME NULL,
  `user_id` INT NOT NULL,
  `forum_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_post_user1_idx` (`user_id` ASC),
  INDEX `fk_post_forum1_idx` (`forum_id` ASC),
  CONSTRAINT `fk_post_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_post_forum1`
    FOREIGN KEY (`forum_id`)
    REFERENCES `forum` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `comment`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `comment` ;

CREATE TABLE IF NOT EXISTS `comment` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `content` VARCHAR(500) NOT NULL,
  `in_reply` VARCHAR(500) NULL,
  `create_date` DATETIME NULL,
  `in_reply_to_id` VARCHAR(500) NULL,
  `active` TINYINT NULL,
  `post_id` INT NOT NULL,
  `comment_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_comment_post1_idx` (`post_id` ASC),
  INDEX `fk_comment_comment1_idx` (`comment_id` ASC),
  CONSTRAINT `fk_comment_post1`
    FOREIGN KEY (`post_id`)
    REFERENCES `post` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_comment_comment1`
    FOREIGN KEY (`comment_id`)
    REFERENCES `comment` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `category`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `category` ;

CREATE TABLE IF NOT EXISTS `category` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `#hashtag` VARCHAR(45) NULL,
  `post_id` INT NOT NULL,
  `forum_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_post_category_post1_idx` (`post_id` ASC),
  INDEX `fk_category_forum1_idx` (`forum_id` ASC),
  CONSTRAINT `fk_post_category_post1`
    FOREIGN KEY (`post_id`)
    REFERENCES `post` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_category_forum1`
    FOREIGN KEY (`forum_id`)
    REFERENCES `forum` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `base`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `base` ;

CREATE TABLE IF NOT EXISTS `base` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `plant_id` INT NOT NULL,
  `brand` VARCHAR(500) NULL,
  `name` VARCHAR(500) NULL,
  `type` VARCHAR(500) NULL,
  `quantity` INT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_base_plant1_idx` (`plant_id` ASC),
  CONSTRAINT `fk_base_plant1`
    FOREIGN KEY (`plant_id`)
    REFERENCES `plant` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `blog`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `blog` ;

CREATE TABLE IF NOT EXISTS `blog` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(45) NULL,
  `content` VARCHAR(45) NULL,
  `image_url` VARCHAR(45) NULL,
  `create_date` VARCHAR(45) NULL,
  `update_date` VARCHAR(45) NULL,
  `user_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_blog_user1_idx` (`user_id` ASC),
  CONSTRAINT `fk_blog_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `user_garden_has_plant`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `user_garden_has_plant` ;

CREATE TABLE IF NOT EXISTS `user_garden_has_plant` (
  `user_garden_id` INT NOT NULL,
  `plant_id` INT NOT NULL,
  PRIMARY KEY (`user_garden_id`, `plant_id`),
  INDEX `fk_user_garden_has_plant_plant1_idx` (`plant_id` ASC),
  INDEX `fk_user_garden_has_plant_user_garden1_idx` (`user_garden_id` ASC),
  CONSTRAINT `fk_user_garden_has_plant_user_garden1`
    FOREIGN KEY (`user_garden_id`)
    REFERENCES `user_garden` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_user_garden_has_plant_plant1`
    FOREIGN KEY (`plant_id`)
    REFERENCES `plant` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `address`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `address` ;

CREATE TABLE IF NOT EXISTS `address` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `street` VARCHAR(45) NULL,
  `street2` VARCHAR(45) NULL,
  `city` VARCHAR(45) NULL,
  `state` VARCHAR(45) NULL,
  `area_code` VARCHAR(45) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `trade_post`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `trade_post` ;

CREATE TABLE IF NOT EXISTS `trade_post` (
  `id` INT NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `todo`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `todo` ;

CREATE TABLE IF NOT EXISTS `todo` (
  `id` INT NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `store`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `store` ;

CREATE TABLE IF NOT EXISTS `store` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `address_id` INT NOT NULL,
  `image_url` VARCHAR(45) NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_store_address1_idx` (`address_id` ASC),
  CONSTRAINT `fk_store_address1`
    FOREIGN KEY (`address_id`)
    REFERENCES `address` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `album`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `album` ;

CREATE TABLE IF NOT EXISTS `album` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `user_id` INT NOT NULL,
  `image_url` VARCHAR(500) NULL,
  `user_garden_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_album_user1_idx` (`user_id` ASC),
  INDEX `fk_album_user_garden1_idx` (`user_garden_id` ASC),
  CONSTRAINT `fk_album_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_album_user_garden1`
    FOREIGN KEY (`user_garden_id`)
    REFERENCES `user_garden` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

SET SQL_MODE = '';
DROP USER IF EXISTS admin@localhost;
SET SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';
CREATE USER 'admin'@'localhost' IDENTIFIED BY 'admin';

GRANT SELECT, INSERT, TRIGGER, UPDATE, DELETE ON TABLE * TO 'admin'@'localhost';

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

-- -----------------------------------------------------
-- Data for table `care`
-- -----------------------------------------------------
START TRANSACTION;
USE `plantdb`;
INSERT INTO `care` (`id`, `water_type`) VALUES (1, '1');

COMMIT;


-- -----------------------------------------------------
-- Data for table `plant`
-- -----------------------------------------------------
START TRANSACTION;
USE `plantdb`;
INSERT INTO `plant` (`id`, `care_id`, `common_name`, `description`, `image_url`, `botanical_name`, `type`, `care_difficulty`, `is_outdoor`, `is_indoor`, `is_toxic`, `water_cycle`, `light_requirement`) VALUES (1, 1, 'Snake Plant', 'Tall Boi', '', 'Dracaena trifasciata', NULL, 'Easy', NULL, NULL, NULL, NULL, NULL);

COMMIT;


-- -----------------------------------------------------
-- Data for table `user`
-- -----------------------------------------------------
START TRANSACTION;
USE `plantdb`;
INSERT INTO `user` (`id`, `username`, `password`, `enabled`, `first_name`, `last_name`, `email`, `image_url`, `bio`, `role`) VALUES (1, 'admin', 'x', 1, NULL, NULL, NULL, NULL, NULL, 'ROLE_ADMIN');

COMMIT;


-- -----------------------------------------------------
-- Data for table `user_garden`
-- -----------------------------------------------------
START TRANSACTION;
USE `plantdb`;
INSERT INTO `user_garden` (`id`, `user_id`, `height`, `spread`, `water_type`, `nickname`, `pot_diameter`, `image_url`, `home_location`) VALUES (1, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL);

COMMIT;


-- -----------------------------------------------------
-- Data for table `forum`
-- -----------------------------------------------------
START TRANSACTION;
USE `plantdb`;
INSERT INTO `forum` (`id`) VALUES (1);

COMMIT;


-- -----------------------------------------------------
-- Data for table `post`
-- -----------------------------------------------------
START TRANSACTION;
USE `plantdb`;
INSERT INTO `post` (`id`, `title`, `content`, `image_url`, `create_date`, `update_date`, `user_id`, `forum_id`) VALUES (1, 'Our First Post', NULL, NULL, NULL, NULL, 1, 1);

COMMIT;


-- -----------------------------------------------------
-- Data for table `comment`
-- -----------------------------------------------------
START TRANSACTION;
USE `plantdb`;
INSERT INTO `comment` (`id`, `content`, `in_reply`, `create_date`, `in_reply_to_id`, `active`, `post_id`, `comment_id`) VALUES (1, 'Our first comment', NULL, NULL, NULL, NULL, 1, 1);

COMMIT;


-- -----------------------------------------------------
-- Data for table `category`
-- -----------------------------------------------------
START TRANSACTION;
USE `plantdb`;
INSERT INTO `category` (`id`, `name`, `#hashtag`, `post_id`, `forum_id`) VALUES (1, 'Indoor', NULL, 1, 1);
INSERT INTO `category` (`id`, `name`, `#hashtag`, `post_id`, `forum_id`) VALUES (2, 'Health', NULL, 1, 1);

COMMIT;


-- -----------------------------------------------------
-- Data for table `base`
-- -----------------------------------------------------
START TRANSACTION;
USE `plantdb`;
INSERT INTO `base` (`id`, `plant_id`, `brand`, `name`, `type`, `quantity`) VALUES (1, 1, 'Miracle Gro', NULL, NULL, NULL);

COMMIT;


-- -----------------------------------------------------
-- Data for table `blog`
-- -----------------------------------------------------
START TRANSACTION;
USE `plantdb`;
INSERT INTO `blog` (`id`, `title`, `content`, `image_url`, `create_date`, `update_date`, `user_id`) VALUES (1, NULL, NULL, NULL, NULL, NULL, 1);

COMMIT;


-- -----------------------------------------------------
-- Data for table `album`
-- -----------------------------------------------------
START TRANSACTION;
USE `plantdb`;
INSERT INTO `album` (`id`, `user_id`, `image_url`, `user_garden_id`) VALUES (1, 1, NULL, 1);

COMMIT;


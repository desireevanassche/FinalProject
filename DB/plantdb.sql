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
-- Table `user`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `user` ;

CREATE TABLE IF NOT EXISTS `user` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `address_id` INT NULL,
  `username` VARCHAR(100) NOT NULL,
  `password` VARCHAR(200) NOT NULL,
  `enabled` TINYINT NOT NULL DEFAULT 1,
  `first_name` VARCHAR(100) NULL,
  `last_name` VARCHAR(100) NULL,
  `email` VARCHAR(100) NULL,
  `image_url` VARCHAR(1000) NULL,
  `biography` TEXT NULL,
  `role` VARCHAR(100) NULL,
  UNIQUE INDEX `username_UNIQUE` (`username` ASC),
  PRIMARY KEY (`id`),
  INDEX `fk_user_address1_idx` (`address_id` ASC),
  CONSTRAINT `fk_user_address1`
    FOREIGN KEY (`address_id`)
    REFERENCES `address` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `plant`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `plant` ;

CREATE TABLE IF NOT EXISTS `plant` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `created_by_id` INT NOT NULL,
  `common_name` VARCHAR(500) NOT NULL,
  `description` TEXT NULL,
  `image_url` VARCHAR(1000) NULL,
  `botanical_name` VARCHAR(500) NULL,
  `care_difficulty` VARCHAR(200) NULL,
  `water_cycle` VARCHAR(500) NULL,
  `water_type` VARCHAR(100) NULL,
  `light_requirement` VARCHAR(100) NULL,
  `active` TINYINT NOT NULL DEFAULT 1,
  PRIMARY KEY (`id`),
  INDEX `fk_plant_user1_idx` (`created_by_id` ASC),
  CONSTRAINT `fk_plant_user1`
    FOREIGN KEY (`created_by_id`)
    REFERENCES `user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `user_plant`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `user_plant` ;

CREATE TABLE IF NOT EXISTS `user_plant` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `user_id` INT NOT NULL,
  `plant_id` INT NOT NULL,
  `height_inches` DOUBLE NULL,
  `spread_inches` DOUBLE NULL,
  `nickname` VARCHAR(200) NULL,
  `pot_diameter_inches` DOUBLE NULL,
  `image_url` VARCHAR(1000) NULL,
  `home_location` VARCHAR(500) NULL,
  `description` TEXT NULL,
  `active` TINYINT NOT NULL DEFAULT 1,
  PRIMARY KEY (`id`),
  INDEX `fk_plant_inventory_user1_idx` (`user_id` ASC),
  INDEX `fk_user_plant_plant1_idx` (`plant_id` ASC),
  CONSTRAINT `fk_plant_inventory_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_user_plant_plant1`
    FOREIGN KEY (`plant_id`)
    REFERENCES `plant` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `topic`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `topic` ;

CREATE TABLE IF NOT EXISTS `topic` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(100) NOT NULL,
  `description` TEXT NULL,
  `image_url` VARCHAR(1000) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `post`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `post` ;

CREATE TABLE IF NOT EXISTS `post` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `user_id` INT NOT NULL,
  `topic_id` INT NOT NULL,
  `title` VARCHAR(500) NOT NULL,
  `content` TEXT NULL,
  `image_url` VARCHAR(1000) NULL,
  `create_date` DATETIME NULL,
  `update_date` DATETIME NULL,
  `active` TINYINT NOT NULL DEFAULT 1,
  PRIMARY KEY (`id`),
  INDEX `fk_post_user1_idx` (`user_id` ASC),
  INDEX `fk_post_topic1_idx` (`topic_id` ASC),
  CONSTRAINT `fk_post_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_post_topic1`
    FOREIGN KEY (`topic_id`)
    REFERENCES `topic` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `comment`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `comment` ;

CREATE TABLE IF NOT EXISTS `comment` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `post_id` INT NOT NULL,
  `in_reply_to_id` INT NULL,
  `content` TEXT NOT NULL,
  `create_date` DATETIME NULL,
  `active` TINYINT NOT NULL DEFAULT 1,
  PRIMARY KEY (`id`),
  INDEX `fk_comment_post1_idx` (`post_id` ASC),
  INDEX `fk_comment_comment1_idx` (`in_reply_to_id` ASC),
  CONSTRAINT `fk_comment_post1`
    FOREIGN KEY (`post_id`)
    REFERENCES `post` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_comment_comment1`
    FOREIGN KEY (`in_reply_to_id`)
    REFERENCES `comment` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `potting_mix`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `potting_mix` ;

CREATE TABLE IF NOT EXISTS `potting_mix` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `user_id` INT NOT NULL,
  `brand` VARCHAR(500) NULL,
  `name` VARCHAR(500) NULL,
  `type` VARCHAR(500) NULL,
  `quantity` INT NULL,
  `unit` VARCHAR(100) NULL,
  `image_url` VARCHAR(1000) NULL,
  `date_created` DATETIME NULL,
  `active` TINYINT NOT NULL DEFAULT 1,
  PRIMARY KEY (`id`),
  INDEX `fk_potting_mix_user1_idx` (`user_id` ASC),
  CONSTRAINT `fk_potting_mix_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `blog`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `blog` ;

CREATE TABLE IF NOT EXISTS `blog` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `user_id` INT NOT NULL,
  `title` VARCHAR(100) NULL,
  `content` TEXT NULL,
  `image_url` VARCHAR(1000) NULL,
  `create_date` DATETIME NULL,
  `update_date` DATETIME NULL,
  `active` TINYINT NOT NULL DEFAULT 1,
  PRIMARY KEY (`id`),
  INDEX `fk_blog_user1_idx` (`user_id` ASC),
  CONSTRAINT `fk_blog_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `todo`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `todo` ;

CREATE TABLE IF NOT EXISTS `todo` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `user_plant_id` INT NOT NULL,
  `name` VARCHAR(200) NULL,
  `description` TEXT NULL,
  `date_created` DATETIME NULL,
  `due_date` DATE NULL,
  `completion_date` DATETIME NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_todo_user_garden1_idx` (`user_plant_id` ASC),
  CONSTRAINT `fk_todo_user_garden1`
    FOREIGN KEY (`user_plant_id`)
    REFERENCES `user_plant` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `store`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `store` ;

CREATE TABLE IF NOT EXISTS `store` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `address_id` INT NOT NULL,
  `image_url` VARCHAR(1000) NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_store_address1_idx` (`address_id` ASC),
  CONSTRAINT `fk_store_address1`
    FOREIGN KEY (`address_id`)
    REFERENCES `address` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `plant_photo`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `plant_photo` ;

CREATE TABLE IF NOT EXISTS `plant_photo` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `user_plant_id` INT NOT NULL,
  `image_url` VARCHAR(1000) NULL,
  `date_created` DATETIME NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_album_user_garden1_idx` (`user_plant_id` ASC),
  CONSTRAINT `fk_album_user_garden1`
    FOREIGN KEY (`user_plant_id`)
    REFERENCES `user_plant` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `plant_category`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `plant_category` ;

CREATE TABLE IF NOT EXISTS `plant_category` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(100) NULL,
  `description` VARCHAR(500) NULL,
  `image_url` VARCHAR(1000) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `plant_has_plant_category`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `plant_has_plant_category` ;

CREATE TABLE IF NOT EXISTS `plant_has_plant_category` (
  `plant_id` INT NOT NULL,
  `plant_category_id` INT NOT NULL,
  PRIMARY KEY (`plant_id`, `plant_category_id`),
  INDEX `fk_plant_has_plant_category_plant_category1_idx` (`plant_category_id` ASC),
  INDEX `fk_plant_has_plant_category_plant1_idx` (`plant_id` ASC),
  CONSTRAINT `fk_plant_has_plant_category_plant1`
    FOREIGN KEY (`plant_id`)
    REFERENCES `plant` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_plant_has_plant_category_plant_category1`
    FOREIGN KEY (`plant_category_id`)
    REFERENCES `plant_category` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `plant_has_potting_mix`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `plant_has_potting_mix` ;

CREATE TABLE IF NOT EXISTS `plant_has_potting_mix` (
  `plant_id` INT NOT NULL,
  `potting_mix_id` INT NOT NULL,
  PRIMARY KEY (`plant_id`, `potting_mix_id`),
  INDEX `fk_plant_has_potting_mix_potting_mix1_idx` (`potting_mix_id` ASC),
  INDEX `fk_plant_has_potting_mix_plant1_idx` (`plant_id` ASC),
  CONSTRAINT `fk_plant_has_potting_mix_plant1`
    FOREIGN KEY (`plant_id`)
    REFERENCES `plant` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_plant_has_potting_mix_potting_mix1`
    FOREIGN KEY (`potting_mix_id`)
    REFERENCES `potting_mix` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `store_has_plant`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `store_has_plant` ;

CREATE TABLE IF NOT EXISTS `store_has_plant` (
  `store_id` INT NOT NULL,
  `plant_id` INT NOT NULL,
  PRIMARY KEY (`store_id`, `plant_id`),
  INDEX `fk_store_has_plant_plant1_idx` (`plant_id` ASC),
  INDEX `fk_store_has_plant_store1_idx` (`store_id` ASC),
  CONSTRAINT `fk_store_has_plant_store1`
    FOREIGN KEY (`store_id`)
    REFERENCES `store` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_store_has_plant_plant1`
    FOREIGN KEY (`plant_id`)
    REFERENCES `plant` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `friend`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `friend` ;

CREATE TABLE IF NOT EXISTS `friend` (
  `user_id` INT NOT NULL,
  `friend_id` INT NOT NULL,
  PRIMARY KEY (`user_id`, `friend_id`),
  INDEX `fk_user_has_user_user2_idx` (`friend_id` ASC),
  INDEX `fk_user_has_user_user1_idx` (`user_id` ASC),
  CONSTRAINT `fk_user_has_user_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_user_has_user_user2`
    FOREIGN KEY (`friend_id`)
    REFERENCES `user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `hashtag`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `hashtag` ;

CREATE TABLE IF NOT EXISTS `hashtag` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(75) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `hashtag_has_topic`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `hashtag_has_topic` ;

CREATE TABLE IF NOT EXISTS `hashtag_has_topic` (
  `hashtag_id` INT NOT NULL,
  `topic_id` INT NOT NULL,
  PRIMARY KEY (`hashtag_id`, `topic_id`),
  INDEX `fk_hasttag_has_topic_topic1_idx` (`topic_id` ASC),
  INDEX `fk_hasttag_has_topic_hasttag1_idx` (`hashtag_id` ASC),
  CONSTRAINT `fk_hasttag_has_topic_hasttag1`
    FOREIGN KEY (`hashtag_id`)
    REFERENCES `hashtag` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_hasttag_has_topic_topic1`
    FOREIGN KEY (`topic_id`)
    REFERENCES `topic` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `hashtag_has_post`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `hashtag_has_post` ;

CREATE TABLE IF NOT EXISTS `hashtag_has_post` (
  `hashtag_id` INT NOT NULL,
  `post_id` INT NOT NULL,
  PRIMARY KEY (`hashtag_id`, `post_id`),
  INDEX `fk_hasttag_has_post_post1_idx` (`post_id` ASC),
  INDEX `fk_hasttag_has_post_hasttag1_idx` (`hashtag_id` ASC),
  CONSTRAINT `fk_hasttag_has_post_hasttag1`
    FOREIGN KEY (`hashtag_id`)
    REFERENCES `hashtag` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_hasttag_has_post_post1`
    FOREIGN KEY (`post_id`)
    REFERENCES `post` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `hashtag_has_blog`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `hashtag_has_blog` ;

CREATE TABLE IF NOT EXISTS `hashtag_has_blog` (
  `hasttag_id` INT NOT NULL,
  `blog_id` INT NOT NULL,
  PRIMARY KEY (`hasttag_id`, `blog_id`),
  INDEX `fk_hasttag_has_blog_blog1_idx` (`blog_id` ASC),
  INDEX `fk_hasttag_has_blog_hasttag1_idx` (`hasttag_id` ASC),
  CONSTRAINT `fk_hasttag_has_blog_hasttag1`
    FOREIGN KEY (`hasttag_id`)
    REFERENCES `hashtag` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_hasttag_has_blog_blog1`
    FOREIGN KEY (`blog_id`)
    REFERENCES `blog` (`id`)
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
-- Data for table `address`
-- -----------------------------------------------------
START TRANSACTION;
USE `plantdb`;
INSERT INTO `address` (`id`, `street`, `street2`, `city`, `state`, `area_code`) VALUES (1, '1805 Nelson Rd', NULL, 'Longmont', 'Colorado', '80501');
INSERT INTO `address` (`id`, `street`, `street2`, `city`, `state`, `area_code`) VALUES (2, '1234 Fake St', NULL, 'Denver', 'Colorado', '80023');

COMMIT;


-- -----------------------------------------------------
-- Data for table `user`
-- -----------------------------------------------------
START TRANSACTION;
USE `plantdb`;
INSERT INTO `user` (`id`, `address_id`, `username`, `password`, `enabled`, `first_name`, `last_name`, `email`, `image_url`, `biography`, `role`) VALUES (1, 1, 'admin', '$2a$10$XR0stvrxAeiPsPSh0hHruesmB0UETSkbRPjK3fRxibq0DvQ/eoQbm', 1, 'admin', 'admin', 'admin@admin.com', 'https://freesvg.org/img/abstract-user-flat-4.png', 'Look at me, I am the captian now', 'ROLE_ADMIN');
INSERT INTO `user` (`id`, `address_id`, `username`, `password`, `enabled`, `first_name`, `last_name`, `email`, `image_url`, `biography`, `role`) VALUES (2, 2, 'friend', 'friend', 1, 'friend', 'friend', 'friend@friend.com', 'https://freesvg.org/img/abstract-user-flat-4.png', 'I am friend', 'ROLE_ADMIN');

COMMIT;


-- -----------------------------------------------------
-- Data for table `plant`
-- -----------------------------------------------------
START TRANSACTION;
USE `plantdb`;
INSERT INTO `plant` (`id`, `created_by_id`, `common_name`, `description`, `image_url`, `botanical_name`, `care_difficulty`, `water_cycle`, `water_type`, `light_requirement`, `active`) VALUES (1, 1, 'Snake Plant', 'Tall Boi', 'https://h2.commercev3.net/cdn.brecks.com/images/800/76621A.jpg', 'Dracaena trifasciata', 'Easy', '2-8 weeks', 'mineral water', '8 - 10 hours of direct sunlight', 1);

COMMIT;


-- -----------------------------------------------------
-- Data for table `user_plant`
-- -----------------------------------------------------
START TRANSACTION;
USE `plantdb`;
INSERT INTO `user_plant` (`id`, `user_id`, `plant_id`, `height_inches`, `spread_inches`, `nickname`, `pot_diameter_inches`, `image_url`, `home_location`, `description`, `active`) VALUES (1, 1, 1, 6, 6, 'Todd', 4, 'https://www.bybrittanygoldwyn.com/wp-content/uploads/2021/03/Sans-Trifasciata-Snake-Plant-6.jpg', 'Living room', 'My happy boi', 1);

COMMIT;


-- -----------------------------------------------------
-- Data for table `topic`
-- -----------------------------------------------------
START TRANSACTION;
USE `plantdb`;
INSERT INTO `topic` (`id`, `name`, `description`, `image_url`) VALUES (1, 'Indoor', 'Indoor Plant', 'https://media.allure.com/photos/5fdcf516563e46c7d11ee93f/master/pass/AllureBeginnerHouseplants.jpg');
INSERT INTO `topic` (`id`, `name`, `description`, `image_url`) VALUES (2, 'Health', 'Healthy Boi', 'http://westchestertreelife.com/wp-content/uploads/2015/06/Healthy-Plant-WestchesterTreeLife.jpg');

COMMIT;


-- -----------------------------------------------------
-- Data for table `post`
-- -----------------------------------------------------
START TRANSACTION;
USE `plantdb`;
INSERT INTO `post` (`id`, `user_id`, `topic_id`, `title`, `content`, `image_url`, `create_date`, `update_date`, `active`) VALUES (1, 1, 1, 'Our First Post', 'This is a post, about plants, posted by a user.', 'https://www.ikea.com/us/en/images/products/sansevieria-trifasciata-potted-plant-mother-in-laws-tongue__0908898_pe676659_s5.jpg', '2022-05-24 12:25:00', '2022-05-24 13:25:00', 1);

COMMIT;


-- -----------------------------------------------------
-- Data for table `comment`
-- -----------------------------------------------------
START TRANSACTION;
USE `plantdb`;
INSERT INTO `comment` (`id`, `post_id`, `in_reply_to_id`, `content`, `create_date`, `active`) VALUES (1, 1, 1, 'Our first comment', '2022-05-24 12:00:00', 1);

COMMIT;


-- -----------------------------------------------------
-- Data for table `potting_mix`
-- -----------------------------------------------------
START TRANSACTION;
USE `plantdb`;
INSERT INTO `potting_mix` (`id`, `user_id`, `brand`, `name`, `type`, `quantity`, `unit`, `image_url`, `date_created`, `active`) VALUES (1, 1, 'Miracle Gro', 'Potting Mix', 'Soil', 1, 'cup', 'https://images.heb.com/is/image/HEBGrocery/001382808', '2022-05-22 12:00:00', 1);

COMMIT;


-- -----------------------------------------------------
-- Data for table `blog`
-- -----------------------------------------------------
START TRANSACTION;
USE `plantdb`;
INSERT INTO `blog` (`id`, `user_id`, `title`, `content`, `image_url`, `create_date`, `update_date`, `active`) VALUES (1, 1, 'Our First Blog', 'This is a blog, about plants, posted by an admin', 'https://hips.hearstapps.com/hmg-prod.s3.amazonaws.com/images/house-plants-1629187361.jpg?crop=0.288xw:0.577xh;0.0465xw,0.205xh&resize=640:*', '2022-05-24 12:00:00', '2022-05-24 12:00:01', 1);

COMMIT;


-- -----------------------------------------------------
-- Data for table `todo`
-- -----------------------------------------------------
START TRANSACTION;
USE `plantdb`;
INSERT INTO `todo` (`id`, `user_plant_id`, `name`, `description`, `date_created`, `due_date`, `completion_date`) VALUES (1, 1, 'Water Todd', 'water Todd', '2202-05-24 12:00:00', '2022-05-30', '2202-05-24 12:01:01');

COMMIT;


-- -----------------------------------------------------
-- Data for table `store`
-- -----------------------------------------------------
START TRANSACTION;
USE `plantdb`;
INSERT INTO `store` (`id`, `name`, `address_id`, `image_url`) VALUES (1, 'The Flower Bin', 1, 'http://1.bp.blogspot.com/-M4LiJ0qCYhY/UQHvvCxZIYI/AAAAAAAAAB0/QD259KjwM1Q/s400/20100615_FLOWER_BIN-55.JPG');

COMMIT;


-- -----------------------------------------------------
-- Data for table `plant_photo`
-- -----------------------------------------------------
START TRANSACTION;
USE `plantdb`;
INSERT INTO `plant_photo` (`id`, `user_plant_id`, `image_url`, `date_created`) VALUES (1, 1, 'https://i.etsystatic.com/18193121/r/il/872f52/2144887556/il_570xN.2144887556_ynis.jpg', '2022-05-24 12:30:00');

COMMIT;


-- -----------------------------------------------------
-- Data for table `plant_category`
-- -----------------------------------------------------
START TRANSACTION;
USE `plantdb`;
INSERT INTO `plant_category` (`id`, `name`, `description`, `image_url`) VALUES (1, 'flowering plant', 'bears flowers or fruits', 'https://upload.wikimedia.org/wikipedia/commons/thumb/1/1d/Maitohorsma_%28Epilobium_angustifolium%29.JPG/380px-Maitohorsma_%28Epilobium_angustifolium%29.JPG');

COMMIT;


-- -----------------------------------------------------
-- Data for table `plant_has_plant_category`
-- -----------------------------------------------------
START TRANSACTION;
USE `plantdb`;
INSERT INTO `plant_has_plant_category` (`plant_id`, `plant_category_id`) VALUES (1, 1);

COMMIT;


-- -----------------------------------------------------
-- Data for table `plant_has_potting_mix`
-- -----------------------------------------------------
START TRANSACTION;
USE `plantdb`;
INSERT INTO `plant_has_potting_mix` (`plant_id`, `potting_mix_id`) VALUES (1, 1);

COMMIT;


-- -----------------------------------------------------
-- Data for table `store_has_plant`
-- -----------------------------------------------------
START TRANSACTION;
USE `plantdb`;
INSERT INTO `store_has_plant` (`store_id`, `plant_id`) VALUES (1, 1);

COMMIT;


-- -----------------------------------------------------
-- Data for table `friend`
-- -----------------------------------------------------
START TRANSACTION;
USE `plantdb`;
INSERT INTO `friend` (`user_id`, `friend_id`) VALUES (1, 2);

COMMIT;


-- -----------------------------------------------------
-- Data for table `hashtag`
-- -----------------------------------------------------
START TRANSACTION;
USE `plantdb`;
INSERT INTO `hashtag` (`id`, `name`) VALUES (1, 'Plant');

COMMIT;


-- -----------------------------------------------------
-- Data for table `hashtag_has_topic`
-- -----------------------------------------------------
START TRANSACTION;
USE `plantdb`;
INSERT INTO `hashtag_has_topic` (`hashtag_id`, `topic_id`) VALUES (1, 1);

COMMIT;


-- -----------------------------------------------------
-- Data for table `hashtag_has_post`
-- -----------------------------------------------------
START TRANSACTION;
USE `plantdb`;
INSERT INTO `hashtag_has_post` (`hashtag_id`, `post_id`) VALUES (1, 1);

COMMIT;


-- -----------------------------------------------------
-- Data for table `hashtag_has_blog`
-- -----------------------------------------------------
START TRANSACTION;
USE `plantdb`;
INSERT INTO `hashtag_has_blog` (`hasttag_id`, `blog_id`) VALUES (1, 1);

COMMIT;


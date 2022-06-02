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
  `growth_description` TEXT NULL,
  `growth_image` VARCHAR(1000) NULL,
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
  `user_id` INT NOT NULL,
  `post_id` INT NOT NULL,
  `in_reply_to_id` INT NULL,
  `content` TEXT NOT NULL,
  `create_date` DATETIME NULL,
  `active` TINYINT NOT NULL DEFAULT 1,
  PRIMARY KEY (`id`),
  INDEX `fk_comment_post1_idx` (`post_id` ASC),
  INDEX `fk_comment_comment1_idx` (`in_reply_to_id` ASC),
  INDEX `fk_comment_user1_idx` (`user_id` ASC),
  CONSTRAINT `fk_comment_post1`
    FOREIGN KEY (`post_id`)
    REFERENCES `post` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_comment_comment1`
    FOREIGN KEY (`in_reply_to_id`)
    REFERENCES `comment` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_comment_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `user` (`id`)
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
  `completed` TINYINT NOT NULL DEFAULT 1,
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


-- -----------------------------------------------------
-- Table `growth_snapshot`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `growth_snapshot` ;

CREATE TABLE IF NOT EXISTS `growth_snapshot` (
  `growth_id` INT NOT NULL AUTO_INCREMENT,
  `user_plant_id` INT NOT NULL,
  `height_inches` DOUBLE NULL,
  `spread_inches` DOUBLE NULL,
  `pot_diameter` DOUBLE NULL,
  `create_date` DATETIME NULL,
  `growth_description` TEXT NULL,
  `growth_image` VARCHAR(1000) NULL,
  PRIMARY KEY (`growth_id`),
  INDEX `fk_growth_snapshot_user_plant1_idx` (`user_plant_id` ASC),
  CONSTRAINT `fk_growth_snapshot_user_plant1`
    FOREIGN KEY (`user_plant_id`)
    REFERENCES `user_plant` (`id`)
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
INSERT INTO `address` (`id`, `street`, `street2`, `city`, `state`, `area_code`) VALUES (3, '5678 Fake St', NULL, 'Denver', 'Colorado', '80023');
INSERT INTO `address` (`id`, `street`, `street2`, `city`, `state`, `area_code`) VALUES (4, '91011 Fake St', NULL, 'Denver', 'Colorado', '80023');
INSERT INTO `address` (`id`, `street`, `street2`, `city`, `state`, `area_code`) VALUES (5, '121314 Fake St', NULL, 'Denver', 'Colorado', '80023');

COMMIT;


-- -----------------------------------------------------
-- Data for table `user`
-- -----------------------------------------------------
START TRANSACTION;
USE `plantdb`;
INSERT INTO `user` (`id`, `address_id`, `username`, `password`, `enabled`, `first_name`, `last_name`, `email`, `image_url`, `biography`, `role`) VALUES (1, 1, 'admin', '$2a$10$XR0stvrxAeiPsPSh0hHruesmB0UETSkbRPjK3fRxibq0DvQ/eoQbm', 1, 'admin', 'admin', 'admin@admin.com', 'https://freesvg.org/img/abstract-user-flat-4.png', 'Look at me, I am the captian now', 'ROLE_ADMIN');
INSERT INTO `user` (`id`, `address_id`, `username`, `password`, `enabled`, `first_name`, `last_name`, `email`, `image_url`, `biography`, `role`) VALUES (2, 2, 'B0bR0$$', 'friend', 1, 'friend', 'friend', 'friend@friend.com', 'https://freesvg.org/img/abstract-user-flat-4.png', 'I am friend', 'ROLE_ADMIN');
INSERT INTO `user` (`id`, `address_id`, `username`, `password`, `enabled`, `first_name`, `last_name`, `email`, `image_url`, `biography`, `role`) VALUES (3, 3, 'zack', '$2a$10$AeiKPnL31GuBgZBBsMrrludU3mbtalgVsQesY.MCFMFRNGSf6SrDa', 1, 'Zack', 'Gaddy', 'zack.e.gaddy@gmail.com', 'https://media-exp1.licdn.com/dms/image/C4E03AQGTAYsof7KOkg/profile-displayphoto-shrink_800_800/0/1651503160336?e=1659571200&v=beta&t=WJks7Vj8chlbDRcxGcUe17BMhAhk_o29D5TXROybvQg', 'Hello! I\'m Zack', 'ROLE_ADMIN');
INSERT INTO `user` (`id`, `address_id`, `username`, `password`, `enabled`, `first_name`, `last_name`, `email`, `image_url`, `biography`, `role`) VALUES (4, 4, 'lpaladini', '$2a$10$7In5OUTVBWnhioEpHo95/eugXaCerHk29midtYfWBK9qd6uW.Zq1u', 1, 'Lucas', 'Paladini', 'lpaladini@me.com', 'https://freesvg.org/img/abstract-user-flat-4.png', 'Hello! I\'m Lucas', 'ROLE_ADMIN');
INSERT INTO `user` (`id`, `address_id`, `username`, `password`, `enabled`, `first_name`, `last_name`, `email`, `image_url`, `biography`, `role`) VALUES (5, 5, 'PlantDes', '$2a$10$4DToLtRmxBGNe2ZQAgl75OX2xWcp58a40yYzW/YxZpNl.4V.dhSh.', 1, 'Desiree', 'VanAssche', 'Des@me.com', 'https://freesvg.org/img/abstract-user-flat-4.png', 'Hello! I\'m Desiree', 'ROLE_ADMIN');

COMMIT;


-- -----------------------------------------------------
-- Data for table `plant`
-- -----------------------------------------------------
START TRANSACTION;
USE `plantdb`;
INSERT INTO `plant` (`id`, `created_by_id`, `common_name`, `description`, `image_url`, `botanical_name`, `care_difficulty`, `water_cycle`, `water_type`, `light_requirement`, `active`) VALUES (1, 1, 'Snake Plant', 'When young, a Sansevieria plant can be utilized as a table plant, but as it matures, it can be used as a stately floor plant. The leaves form a rosette pattern as they grow. Sansevieria plants evolved thick, leathery leaves to adapt to the dry, arid environments in which they were first discovered. Water is stored in the thick, succulent leaves, and the strong leaf cuticles aid to prevent moisture loss. Sansevieria flowers are often greenish-white and grow on a tall, leafless stem. Flowering takes place in the summer or fall.', 'https://live.staticflickr.com/65535/52097469117_4e2fa16bde_z.jpg', 'Sansevieria trifasciata', 'Easy', 'bi weekly', 'filtered', 'Bright Indirect', 1);
INSERT INTO `plant` (`id`, `created_by_id`, `common_name`, `description`, `image_url`, `botanical_name`, `care_difficulty`, `water_cycle`, `water_type`, `light_requirement`, `active`) VALUES (2, 1, 'Alocasia African Mask', 'Because of its very enormous, glossy, heart-shaped leaves, some with highly wavy edges, an Alocasia plant, endemic to Asia and eastern Australia, is also known as an Elephant Ear plant or African Mask plant. The leaves can range in length from eight to thirty-five inches (20cm-90cm). Although this plant produces flowers, the blossoms are little and inconspicuous in compared to the stunning plant leaves. An alocasia plant grows on the forest floor in its natural habitat, which explains why it prefers strong light, yet direct sunlight burns the lovely leaves.', 'https://live.staticflickr.com/65535/52098495266_173c48c318_z.jpg', 'Alocasia Amazonica', 'Intermediate', 'weekly', 'filtered', 'Bright Indirect', 1);
INSERT INTO `plant` (`id`, `created_by_id`, `common_name`, `description`, `image_url`, `botanical_name`, `care_difficulty`, `water_cycle`, `water_type`, `light_requirement`, `active`) VALUES (3, 1, 'Aloe Vera', 'As a houseplant, an aloe plant thrives well both indoors in a light position and outdoors in warm climates. The aloe genus contains around 500 species of flowering succulent plants. Aloe plants are relatives of the flowering lily plant and belong to the Asphodelaceae (Liliaceae) family. The succulent aloe plant, which has over 500 kinds, is assumed to have originated in Madagascar, the Arabian Peninsula, Southern Europe, Eastern and Southern Africa, and the Canary Islands. Aloe plants can now be found flourishing in dry, warm climates all over the world. The Aloe Vera or \"real aloe\" plant (Aloe barbadensis miller) is the most popular indoor variation.', 'https://live.staticflickr.com/65535/52098522998_0c861023ca_z.jpg', 'Aloe Vera', 'Easy', 'monthly', 'mineral', 'Bright Indirect', 1);
INSERT INTO `plant` (`id`, `created_by_id`, `common_name`, `description`, `image_url`, `botanical_name`, `care_difficulty`, `water_cycle`, `water_type`, `light_requirement`, `active`) VALUES (4, 1, 'Areca Palm', 'A type of cane palm is the Areca palm. An juvenile Areca palm can be tiny enough to fit on a table or desk when cultivated indoors. However, as the plant becomes older, it matures into a medium-sized, exotic-looking palm that can grow to be 6-8 feet tall. The \"Butterfly Palm\" moniker comes from the way the Areca palm\'s long, feathery fronds (leaves) arch upwards from many reed-like stems, resembling butterfly wings. Each frond has 40-60 leaflets and can grow to be 3 feet tall.', 'https://live.staticflickr.com/65535/52098495241_86bab877a7_z.jpg', 'Dypsis lutescens', 'Easy', 'weekly', 'tap', 'Bright Indirect', 1);
INSERT INTO `plant` (`id`, `created_by_id`, `common_name`, `description`, `image_url`, `botanical_name`, `care_difficulty`, `water_cycle`, `water_type`, `light_requirement`, `active`) VALUES (5, 1, 'Boston Fern', 'With its long, arching fronds, the Boston fern makes a lovely addition to hanging baskets. The fronds or leaves can reach a length of 2-3 feet and a width of 4-6 inches. On either side of a midrib, each frond has tiny leaflets (pinnae). The leaflets are deltoid in shape and have somewhat serrated edges.', 'https://live.staticflickr.com/65535/52098495111_c3912c1eaf_z.jpg', 'Nephrolepis exaltata', 'Easy', 'weekly', 'tap', 'Bright Indirect', 1);
INSERT INTO `plant` (`id`, `created_by_id`, `common_name`, `description`, `image_url`, `botanical_name`, `care_difficulty`, `water_cycle`, `water_type`, `light_requirement`, `active`) VALUES (6, 1, 'Peacock Plant', 'Calathea types have purple undersides and brilliant, multicolored designs on the top side of their leaves. It\'s a low-growing table plant or short bush that rarely grows taller than 10 inches indoors.\" It can, however, stretch out to a width of 24\"-26\". Calathea plants have much more impressive foliage than the purple, yellow, or white flowers that bloom in the summer.', 'https://live.staticflickr.com/65535/52098732374_2a7ef9f645_z.jpg', 'Calathea roseopicta', 'Intermediate', 'bi weekly', NULL, 'Low Light', 1);
INSERT INTO `plant` (`id`, `created_by_id`, `common_name`, `description`, `image_url`, `botanical_name`, `care_difficulty`, `water_cycle`, `water_type`, `light_requirement`, `active`) VALUES (7, 1, 'Chinese Evergreen', 'Chinese evergreen plants come in a variety of shapes and sizes, with lustrous, oval, leathery leaves and short stems. The leaves are typically 3′′-5′′ wide and 12′′ long. The early varieties had patterned green and gray leaves. The leaves of the new hybrids are yellow, red, and pink, and they demand more light. In the spring and summer, a Chinese evergreen shrub produces little, insignificant flowers that fade to reveal crimson berries. Flower formation diverts energy from leaf growth. Because the Chinese evergreen\'s leaves are the most appealing feature of the plant, the flowers should be removed as soon as they develop.', 'https://live.staticflickr.com/65535/52097469067_91c10f1516_z.jpg', 'Aglaonema', 'Easy', 'weekly', NULL, 'Full Sun', 1);
INSERT INTO `plant` (`id`, `created_by_id`, `common_name`, `description`, `image_url`, `botanical_name`, `care_difficulty`, `water_cycle`, `water_type`, `light_requirement`, `active`) VALUES (8, 1, 'Croton', 'Croton plants (Codiaeum variegatum pictum) were once an outdoor, beautiful bush that grew in tropical climates where temperatures never dropped below 50°F (1°C). Croton plants are available in over a hundred different kinds and have become a popular houseplant in the last 15-20 years. An upright plant with magnificent, multicolored, rigid, leathery, shiny leaves painted in vibrant patterns of red, yellow, green, orange, purple, and black is known as an indoor croton. Long, short, narrow, wide, oval, curly, twisted, or oak-leaf leaf forms are all possible. Individual croton plant leaves and entire branches can be utilized in flower arrangements.', 'https://live.staticflickr.com/65535/52098997860_4281e67c0d_z.jpg', 'Codiaeum variegatum', 'Intermediate', 'weekly', NULL, 'Bright Indirect', DEFAULT);
INSERT INTO `plant` (`id`, `created_by_id`, `common_name`, `description`, `image_url`, `botanical_name`, `care_difficulty`, `water_cycle`, `water_type`, `light_requirement`, `active`) VALUES (9, 1, 'Ctenanthe', 'The Ctenanthe plant is a member of the Marantaceae family and is related to the Calathea and Prayer plants. It is native to tropical Brazil. The big, oval, colorfully patterned leaves of these evergreen perennials are the main attraction.', 'https://smartgardenguide.com/wp-content/uploads/2019/09/ctenanthe-never-never-plant-care-6.jpg', 'Brazilian Snow Plant', 'Advanced', 'weekly', NULL, 'Partial Sun', DEFAULT);
INSERT INTO `plant` (`id`, `created_by_id`, `common_name`, `description`, `image_url`, `botanical_name`, `care_difficulty`, `water_cycle`, `water_type`, `light_requirement`, `active`) VALUES (10, 1, 'Dumb Cane', 'Dieffenbachia plants have huge, broad, pointed, oblong leaves with straight, stout stalks. The stems bend towards the light source when planted in front of a window and never turned. Outdoor dieffenbachia plants can reach heights of 8 to 10 feet and have massive leaves that measure 15 to 20 inches long. Dieffenbachia leaves are normally 3′′- 6′′ wide and 6′′ or more long when grown indoors. When the plant matures and becomes larger, and if correctly pruned, it develops into a stunning, bushy floor plant. Dieffenbachias are a type of houseplant that grows quickly and becomes top-heavy as it matures.', 'https://live.staticflickr.com/65535/52098732299_1372843ac5_z.jpg', 'Dieffenbachia compacta', 'Easy', 'bi weekly', NULL, 'Partial Sun', DEFAULT);
INSERT INTO `plant` (`id`, `created_by_id`, `common_name`, `description`, `image_url`, `botanical_name`, `care_difficulty`, `water_cycle`, `water_type`, `light_requirement`, `active`) VALUES (11, 1, 'Donkey’s Tail', 'This plant has long trailing stems that are covered in plump, teardrop-shaped blue-green leaves that overlap like the hairs on an animal\'s tail. Because the leaves completely surround the stems, they appear to be braided. The leaves may flatten and wrinkle if the soil is extremely dry. The stems begin to grow upright, but as they become heavy with water-filled leaves, they ultimately drape down. A mature Donkey\'s Tail plant produces little star-shaped pink, red, white, or yellow flowers in terminal clusters at the ends of the stems during the summer.', 'https://live.staticflickr.com/65535/52098997740_dfa5f177ff_z.jpg', 'Sedum Morganianum', 'Intermediate', 'bi weekly', NULL, 'Partial Sun', DEFAULT);
INSERT INTO `plant` (`id`, `created_by_id`, `common_name`, `description`, `image_url`, `botanical_name`, `care_difficulty`, `water_cycle`, `water_type`, `light_requirement`, `active`) VALUES (12, 1, 'Dracaena Corn', 'The tall, majestic Dracaena Corn plant is a popular houseplant that may reach heights of more than 6 feet (1.8 metres). It has one or two yellow or pale green stripes running down the center of its long, green, beautiful lance-shaped leaves. The spiral cluster of 1\"-2\" wide leaves grows at the summit of a stout, central stalk. It, along with the Dracaena warnekii and marginata, are frequently mistaken for palms due to their look. Large white flowers can be found on stems that dangle downwards on a mature plant.', 'https://live.staticflickr.com/65535/52098494991_5d108e3d59_z.jpg', 'Dracaena Massangeana', 'Easy', 'weekly', NULL, 'Bright Indirect', DEFAULT);
INSERT INTO `plant` (`id`, `created_by_id`, `common_name`, `description`, `image_url`, `botanical_name`, `care_difficulty`, `water_cycle`, `water_type`, `light_requirement`, `active`) VALUES (13, 1, 'Silver Queen Pothos', 'The Scindapsus Pictish \'Exotica\' is not a pothos, despite its name suggesting otherwise. They are, however, just as easy to cultivate as a pothos. They have huge heart-shaped leaves and are a vining plant. The leaves have a silky shimmering texture and are variegated dark green with silver grey spots. It comes from South and Southeast Asia and can reach a height of 10 feet outdoors and 3 feet indoors.', 'https://live.staticflickr.com/65535/52098997805_0b046ea695_z.jpg', 'Scindapsus Pictish ‘Exotica’', 'Easy', 'weekly', NULL, 'Low Light', DEFAULT);
INSERT INTO `plant` (`id`, `created_by_id`, `common_name`, `description`, `image_url`, `botanical_name`, `care_difficulty`, `water_cycle`, `water_type`, `light_requirement`, `active`) VALUES (14, 1, 'English Ivy', 'The English Ivy plant is a popular indoor houseplant that comes in a variety of hues and leaf forms, including solid green, variegated green and white, variegated green and yellow, and lobed and unlobed. There is an English Ivy plant for you, whether you want it to sit on a table, grow at the foot of another plant, dangle in a basket, drape down in a wall sconce, or be trained as an amazing topiary plant. When tied to a trellis, stake, or a bark or styrofoam pole, they make great climbers.', 'https://live.staticflickr.com/65535/52098494946_93227546ca_z.jpg', 'Hedera helix', 'Easy', 'weekly', NULL, 'Bright Indirect', DEFAULT);
INSERT INTO `plant` (`id`, `created_by_id`, `common_name`, `description`, `image_url`, `botanical_name`, `care_difficulty`, `water_cycle`, `water_type`, `light_requirement`, `active`) VALUES (15, 1, 'Rubber Tree', 'Rubber tree leaves are thick, leathery, glossy, and oval, measuring around 4\"-14\" long and 2′′-6\" wide. The leaves cannot be trimmed once they have been damaged and must be removed. The leaves of younger plants are noticeably larger than those of older ones. This flexible plant can be utilized as a table plant while young, and as a bushy floor plant or tall tree as it matures. In a light area with high ceilings, the easy-to-care-for rubber tree can reach a height of 10 feet.', 'https://live.staticflickr.com/65535/52098494936_14e75c5547_z.jpg', 'Ficus Elastica', 'Easy', 'bi weekly', NULL, 'Bright Indirect', DEFAULT);
INSERT INTO `plant` (`id`, `created_by_id`, `common_name`, `description`, `image_url`, `botanical_name`, `care_difficulty`, `water_cycle`, `water_type`, `light_requirement`, `active`) VALUES (16, 1, 'Fiddle Leaf Fig', 'The leaves of the Fiddle Leaf Fig are enormous, green, glossy, leathery, and have a wavy edge. The densely veined leaves grow on tall, woody stems and can be 12′′-18′′ long and 8′′-12′′ broad. The plant can be small enough to fit on a table as a baby, but with appropriate care, it grows into a big, stunning indoor tree. This is a striking plant that will stand out wherever it is placed.', 'https://live.staticflickr.com/65535/52098495176_74739296b6_z.jpg', 'Ficus lyrata', 'Intermediate', 'weekly', NULL, 'Bright Indirect', DEFAULT);
INSERT INTO `plant` (`id`, `created_by_id`, `common_name`, `description`, `image_url`, `botanical_name`, `care_difficulty`, `water_cycle`, `water_type`, `light_requirement`, `active`) VALUES (17, 1, 'Swiss Cheese Philodendron', 'The common name for the Swiss cheese plant (Monstera adansonii) comes from its enormous, heart-shaped leaves, which develop holes as the plant grows older (in a process called fenestration). The leaves will resemble Swiss cheese as a result of this. The Swiss cheese plant is a tropical perennial that is commonly grown as a houseplant and is native to Central and South America. Monstera adansonii, like its cousin Monstera deliciosa (commonly known as the Swiss cheese plant), has a quick growth rate and a vining habit. When grown in a container inside, however, it will stay at a reasonable size. Swiss cheese plants are primarily grown as houseplants from young nursery plants and can be potted at any time.', 'https://live.staticflickr.com/65535/52098997900_93c77db015_z.jpg', 'Monstera adansonii', 'Easy', 'weekly', NULL, 'Partial Sun', DEFAULT);
INSERT INTO `plant` (`id`, `created_by_id`, `common_name`, `description`, `image_url`, `botanical_name`, `care_difficulty`, `water_cycle`, `water_type`, `light_requirement`, `active`) VALUES (18, 1, 'Kangaroo Paw Fern', 'A Kangaroo Paw fern (Microsorum diversifolium) is an epiphytic evergreen native to Australia and portions of New Zealand with stiff, leathery, bright green, strangely shaped fronds that vary in size. This is a little fern that grows to be around a foot tall, but its fronds can spread outwards for up to 2-3 feet. A Kangaroo Paw fern requires little fertilizer and like to be kept warm. This is a unique-looking, low-maintenance fern that looks great in a hanging basket or a lovely pot on a table.', 'https://live.staticflickr.com/65535/52098997900_93c77db015_z.jpg', 'Microsorum diversifolium', 'Intermediate', 'weekly', NULL, 'Partial Sun', DEFAULT);
INSERT INTO `plant` (`id`, `created_by_id`, `common_name`, `description`, `image_url`, `botanical_name`, `care_difficulty`, `water_cycle`, `water_type`, `light_requirement`, `active`) VALUES (19, 1, 'Marble Queen Pothos', 'The marble queen pothos, Epipremnum aureum \"Marble Queen,\" is a member of the Araceae family that was first discovered growing on the islands of French Polynesia. It is now found in tropical and subtropical woods all over the world. The marble queen pothos, often known as Devil\'s Ivy,\" is a low-maintenance houseplant that thrives in a variety of environments. Its long, draping vines look lovely on a table, hanging gracefully in a basket, or planted in a wall planter. A marble queen pothos can be taught to climb a peat moss or bark pole.', 'https://live.staticflickr.com/65535/52098997645_4b643fbef9_z.jpg', 'Epipremnum Aureum', 'Easy', 'weekly', NULL, 'Low Light', DEFAULT);
INSERT INTO `plant` (`id`, `created_by_id`, `common_name`, `description`, `image_url`, `botanical_name`, `care_difficulty`, `water_cycle`, `water_type`, `light_requirement`, `active`) VALUES (20, 1, 'Peperomia', 'Heart-shaped, puckered, deeply veined leaves characterize Peperomia Emerald Ripple, also known as Peperomia Caperata. The leaves come in a variety of colors, including green, dark red, and gray. All peperomia plants have tiny, insignificant blooms that grow in groups on upright, conical, red colored spikes. Peperomia Emerald Ripple is a little plant that looks great on a table or shelf. It can also be grown outside as long as the temperature maintains between 50 and 55 degrees Fahrenheit (10 and 12.8 degrees Celsius). Peperomia Obtusifolia, sometimes known as Baby Rubber, is a near relative of the Emerald Ripple variety.', 'https://live.staticflickr.com/65535/52098732164_e90e1454af_z.jpg', 'Peperomia caperata', 'Easy', 'bi weekly', NULL, 'Bright Indirect', DEFAULT);
INSERT INTO `plant` (`id`, `created_by_id`, `common_name`, `description`, `image_url`, `botanical_name`, `care_difficulty`, `water_cycle`, `water_type`, `light_requirement`, `active`) VALUES (21, 1, 'Philodendron Congo', 'Growers have developed various varieties of Philodendron in recent years, including the Philodendron Congo. As long as you keep it warm, this is a plant that can adapt to any situation. Imperial Red, Black Cardinal, Moonlight, Red Emerald, and Autumn are all Philodendron hybrids that can benefit from its care guidelines. This particular philodendron is not a climber. A Philodendron Congo is a self-header that grows upward while spreading outward. On a single stem, the glossy, dark green, thick leaves are spaced quite close together. As a Philodendron Congo matures, the strong, hardly noticeable stem maintains it upright and lovely appearing.', 'https://live.staticflickr.com/65535/52098732164_e90e1454af_z.jpg', 'Philodendron Tatei', 'Intermediate', 'weekly', NULL, 'Partial Sun', DEFAULT);
INSERT INTO `plant` (`id`, `created_by_id`, `common_name`, `description`, `image_url`, `botanical_name`, `care_difficulty`, `water_cycle`, `water_type`, `light_requirement`, `active`) VALUES (22, 1, 'Heartleaf Philodendron', 'A heartleaf philodendron and a pothos plant are frequently confused. Heartleaf philodendron leaves are thinner and softer. Heartleaf philodendron leaves vary in a range of sizes, colors, and patterns, but the most popular type features dark green, glossy, heart-shaped leaves with pointed points. The leaves are bronze when they first emerge, but they swiftly turn green. A heartleaf philodendron has long, vining stems that can easily reach 4 feet in length. The plant will become bushy and full rather than long and lanky if the growing tips at the ends of the stems are pinched back. As a table plant, hanging in a basket, or taught to grow on a trellis or pole, a heartleaf philodendron is lovely.', 'https://live.staticflickr.com/65535/52098494851_14b2951f99_z.jpg', 'Philodendron hederaceum var. oxycardium', 'Easy', 'bi weekly', NULL, 'Bright Indirect', DEFAULT);
INSERT INTO `plant` (`id`, `created_by_id`, `common_name`, `description`, `image_url`, `botanical_name`, `care_difficulty`, `water_cycle`, `water_type`, `light_requirement`, `active`) VALUES (23, 1, 'Polka Dot Plant', 'A polka dot plant is ideal if you\'re searching for a compact, vividly colored, cheerful plant. The Pink Splash plant (Hypoestes phyllostachya) is a lovely plant endemic to Madagascar, Southeast Asia, and South Africa. When grown outside, a polka dot plant can reach a height of 12\" and develop into a short, bushy shrub. In warm, temperate climates, it is commonly planted as an annual.', 'https://live.staticflickr.com/65535/52097468867_c1e02f872f_z.jpg', 'Hypoestes phyllostachya', 'Easy', 'weekly', NULL, 'Bright Indirect', DEFAULT);
INSERT INTO `plant` (`id`, `created_by_id`, `common_name`, `description`, `image_url`, `botanical_name`, `care_difficulty`, `water_cycle`, `water_type`, `light_requirement`, `active`) VALUES (24, 1, 'Golden Pothos', 'A pothos plant is a very adaptable plant with leathery, pointy, heart-shaped leaves and glossy foliage. A pothos plant\'s leaves can grow to be as large as 10\"-12\" in diameter when grown outdoors (25cm-30cm). The leaves are substantially smaller indoors, typically ranging in size from 4\" to 6\" (10cm-15cm). A heart-leaf philodendron and a solid green pothos are frequently confused. In a hanging basket, a pothos plant develops long vines and looks lovely. You may also make a tall, upright plant by attaching the stems to a moss or styrofoam pole.', 'https://live.staticflickr.com/65535/52098997565_4f81188cb4_z.jpg', 'Epipremnum aureum', 'Easy', 'bi weekly', NULL, 'Low Light', DEFAULT);
INSERT INTO `plant` (`id`, `created_by_id`, `common_name`, `description`, `image_url`, `botanical_name`, `care_difficulty`, `water_cycle`, `water_type`, `light_requirement`, `active`) VALUES (25, 1, 'Prayer Plant', 'The prayer plant (Maranta leuconeura) is quite unique. When it grows dark in the evening, the leaves of a prayer plant fold up as if praying. This is made possible by a little joint between the stems and the leaves. The leaves unfold and open anew in the morning as the plant reaches for the sun. A prayer plant is frequently offered as a present during funerals due to its name. Calathea, ctenanthe, stromanthe, and the prayer plant are all members of the Maranta plant family, which includes four closely related plants. Marantas belong to the Marantaceae family and are endemic to tropical Central and South America, as well as the West Indies.', 'https://live.staticflickr.com/65535/52098494761_f13454ae89_z.jpg', 'Maranta leuconeura', 'Easy', 'weekly', NULL, 'Bright Indirect', DEFAULT);
INSERT INTO `plant` (`id`, `created_by_id`, `common_name`, `description`, `image_url`, `botanical_name`, `care_difficulty`, `water_cycle`, `water_type`, `light_requirement`, `active`) VALUES (26, 1, 'Spider Plant', 'Directly from the heart of the plant, grassy-looking, slender leaves approximately 8\"-10\" long and less than 1\" wide appear. A spider plant gives forth multiple long runners (stems) that produce small, fragile, star-shaped white blooms when kept root-bound. Baby plants (plantlets) form after the flowers die and can be easily propagated. The fleshy, tuberous plant roots of a spider plant can also be divided and propagated.', 'https://live.staticflickr.com/65535/52098522618_c63ee2bccf_z.jpg', 'Chlorophytum comosum', 'Easy', 'weekly', NULL, 'Bright Indirect', DEFAULT);
INSERT INTO `plant` (`id`, `created_by_id`, `common_name`, `description`, `image_url`, `botanical_name`, `care_difficulty`, `water_cycle`, `water_type`, `light_requirement`, `active`) VALUES (27, 1, 'Split Leaf Philodendron', 'The tropical Split Leaf philodendron is frequently confused with, and confused with, a Monstera deliciosa or Swiss cheese plant. Despite the fact that both plants belong to the Araceae family, have comparable care requirements, and grow in a similar way, they are not the same. A near relative of the pothos plant is the Split Leaf philodendron. Monstera deliciosa is a close relative of the Peace Lily plant and is grown for its \"breadfruit\" in Mexico (Spathiphyllum).', 'https://live.staticflickr.com/65535/52098495216_2534dbd55e_z.jpg', 'Philodendron bipinnatifidum', 'Advanced', 'weekly', NULL, 'Bright Indirect', DEFAULT);
INSERT INTO `plant` (`id`, `created_by_id`, `common_name`, `description`, `image_url`, `botanical_name`, `care_difficulty`, `water_cycle`, `water_type`, `light_requirement`, `active`) VALUES (28, 1, 'Stromanthe', 'A Stromanthe sanguinea \"Tricolor\" (also known as a Stromanthe \"Triostar\") is a plant endemic to Brazil\'s rain forests, growing up to 5 feet (1.5 meters) tall and 3 feet (.9 meters) broad with leaves 20 inches (50 cm) long and 6 inches (15.2 cm) wide. The Stromanthe, a member of the Marantaceae family, is a near relative of the Prayer plant and the Ctenanthe. Although a Stromanthe plant can be cultivated both indoors and outdoors, it requires temperatures over 60°F (15.6°C) to thrive. The leaves of a Stromanthe fold up at night, much like those of a Prayer plant. then reopen the following morning', 'https://live.staticflickr.com/65535/52098494696_758a5bf9e6_z.jpg', 'Stromanthe sanguinea', 'Advanced', 'weekly', NULL, 'Bright Indirect', DEFAULT);
INSERT INTO `plant` (`id`, `created_by_id`, `common_name`, `description`, `image_url`, `botanical_name`, `care_difficulty`, `water_cycle`, `water_type`, `light_requirement`, `active`) VALUES (29, 1, 'Wandering Dude', 'There are several different varieties of wandering jew plants. Long vines with 2′′-4′′ oval or heart-shaped leaves are common. The color of the leaves varies depending on the variety and might be pure green, green with a purple stripe, green with a white or yellow stripe, green with pink, purple, and cream splashes, or solid purple. The back of the leaf may be purple as well. Some wandering jew plants have hairy leaves, while others have a silvery sheen. Small white, pink, purple, or magenta flowers bloom on the wandering jew plant.', 'https://live.staticflickr.com/65535/52098731994_e780d4e627_z.jpg', 'Tradescantia', 'Intermediate', 'weekly', NULL, 'Bright Indirect', DEFAULT);
INSERT INTO `plant` (`id`, `created_by_id`, `common_name`, `description`, `image_url`, `botanical_name`, `care_difficulty`, `water_cycle`, `water_type`, `light_requirement`, `active`) VALUES (30, 1, 'ZZ Plant', 'Zamioculcas zamiifolia, also known as the Eternity Plant, Emerald Palm, Zanzibar Gem, ZuZu Plant, and Aroid Palm, is a ZZ plant. It was first discovered as an outdoor flowering plant in eastern Africa, but it currently grows in tropical locations all over the world. A Dutch nursery in Africa recognized that a ZZ plant would make an excellent houseplant and began propagating and selling it around the world. A ZZ plant is a near relative of dieffenbachia, anthurium, philodendron monstera, and caladium, and belongs to the Araceae family.', 'https://live.staticflickr.com/65535/52098997465_c2541bc196_z.jpg', 'Zamioculcas Zamiifolia', 'Easy', 'bi weekly', NULL, 'Bright Indirect', DEFAULT);
INSERT INTO `plant` (`id`, `created_by_id`, `common_name`, `description`, `image_url`, `botanical_name`, `care_difficulty`, `water_cycle`, `water_type`, `light_requirement`, `active`) VALUES (31, 1, 'Zebra Plant', 'The Zebra plant (Aphelandra squarrosa) has beautiful foliage and unique flowers. The Zebra plant gets its name from its enormous, dark green, glossy leaves with a prominent white midrib and white veins. The leaves\' stems are purple in color. The vivid yellow flowers of the Zebra plant emerge from bracts at the end of a long stalk, and they require a lot of light to blossom. A Zebra plant is mostly a table plant that grows to be approximately a foot tall and is native to Brazilian jungles. These plants might require a little additional attention, but it\'s definitely worth it for such a lovely, unusual houseplant.', 'https://live.staticflickr.com/65535/52098522553_253c39efcd_z.jpg', 'Aphelandra squarrosa', 'Intermediate', 'bi weekly', NULL, 'Partial Sun', DEFAULT);
INSERT INTO `plant` (`id`, `created_by_id`, `common_name`, `description`, `image_url`, `botanical_name`, `care_difficulty`, `water_cycle`, `water_type`, `light_requirement`, `active`) VALUES (32, 1, 'Tree Philodendron', 'A philodendron selloum (philodendron bipinnatifidum) is a South American native that can also be found growing on the east and gulf coastlines of the United States. Hope Selloum, Horsehead Philodendron, Lacy Tree Philodendron, Philodendron Hope Selloum, and Tree Philodendron are some of its frequent names, and it\'s commonly confused with the philodendron xanadu. This tree philodendron does not climb and instead spreads outward rather than upward. The easy-care, self-heading philodendron selloum takes up a lot of area when planted indoors, spreading out 5 feet or more with dark green, glossy, deeply lobed leaves that can be 2 feet-3 feet (60-90 cm) long. Although this plant does develop a trunk as it ages, the leaves effectively conceal it.', 'https://live.staticflickr.com/65535/52098494846_60ef1340a6_z.jpg', 'Philodendron bipinnatifidum', 'Easy', 'weekly', NULL, 'Bright Indirect', DEFAULT);

COMMIT;


-- -----------------------------------------------------
-- Data for table `user_plant`
-- -----------------------------------------------------
START TRANSACTION;
USE `plantdb`;
INSERT INTO `user_plant` (`id`, `user_id`, `plant_id`, `height_inches`, `spread_inches`, `nickname`, `pot_diameter_inches`, `image_url`, `home_location`, `description`, `active`, `growth_description`, `growth_image`) VALUES (1, 1, 1, 48, 10, 'Todd', 8, 'https://www.bybrittanygoldwyn.com/wp-content/uploads/2021/03/Sans-Trifasciata-Snake-Plant-6.jpg', 'Living room', 'My first ever snake plant!!', 1, 'Todd is a full adult now', 'https://www.thespruce.com/thmb/NTGXGN7hSGboVr60smYYGegtnI4=/3000x2001/filters:no_upscale():max_bytes(150000):strip_icc()/snake-plant-care-overview-1902772-01-11ddab561e604bd086b4fd6bfa6305f3.jpg');
INSERT INTO `user_plant` (`id`, `user_id`, `plant_id`, `height_inches`, `spread_inches`, `nickname`, `pot_diameter_inches`, `image_url`, `home_location`, `description`, `active`, `growth_description`, `growth_image`) VALUES (2, 1, 7, 24, 24, 'Larry', 9, 'https://www.gardendesign.com/pictures/images/900x705Max/site_3/chinese-evergreen-plant-aglaonema-shutterstock-com_15962.jpg', 'Bed room', 'My newest member of the family!!', 1, 'WOW! Larry is an adult now!!', 'https://www.gardendesign.com/pictures/images/900x705Max/site_3/chinese-evergreen-plant-aglaonema-shutterstock-com_15962.jpg');
INSERT INTO `user_plant` (`id`, `user_id`, `plant_id`, `height_inches`, `spread_inches`, `nickname`, `pot_diameter_inches`, `image_url`, `home_location`, `description`, `active`, `growth_description`, `growth_image`) VALUES (3, 1, 27, 30, 12, 'Phil', 5, 'https://cdn.shoplightspeed.com/shops/620787/files/22745656/plant-shop-at-junebug-split-leaf-philodendron-6.jpg', 'Tea room', 'I\'m so excited for my split leaf!!!', 1, 'My plant is getting so tall!!!', 'https://cdn.shoplightspeed.com/shops/620787/files/22745656/plant-shop-at-junebug-split-leaf-philodendron-6.jpg');
INSERT INTO `user_plant` (`id`, `user_id`, `plant_id`, `height_inches`, `spread_inches`, `nickname`, `pot_diameter_inches`, `image_url`, `home_location`, `description`, `active`, `growth_description`, `growth_image`) VALUES (4, 3, 3, 2, 3, 'Rod', 3, 'https://www.thespruce.com/thmb/MyxDIrqMGPWVIYwDuzAR7yWT50s=/3000x2000/filters:fill(auto,1)/how-to-propagate-aloe-vera-5087447-hero-32432ce03913495896fb2fc6295e0a58.jpg', 'Office', 'Treat myself to a new plant to celebrate finals!!!', 1, 'New plant!', 'https://www.thespruce.com/thmb/MyxDIrqMGPWVIYwDuzAR7yWT50s=/3000x2000/filters:fill(auto,1)/how-to-propagate-aloe-vera-5087447-hero-32432ce03913495896fb2fc6295e0a58.jpg');

COMMIT;


-- -----------------------------------------------------
-- Data for table `topic`
-- -----------------------------------------------------
START TRANSACTION;
USE `plantdb`;
INSERT INTO `topic` (`id`, `name`, `description`, `image_url`) VALUES (1, 'Indoor', 'Indoor Plant', 'https://media.allure.com/photos/5fdcf516563e46c7d11ee93f/master/pass/AllureBeginnerHouseplants.jpg');
INSERT INTO `topic` (`id`, `name`, `description`, `image_url`) VALUES (2, 'Plant Health', 'Plant Health', 'http://westchestertreelife.com/wp-content/uploads/2015/06/Healthy-Plant-WestchesterTreeLife.jpg');
INSERT INTO `topic` (`id`, `name`, `description`, `image_url`) VALUES (3, 'Outdoor', 'Outdoor Plant', NULL);
INSERT INTO `topic` (`id`, `name`, `description`, `image_url`) VALUES (4, 'General', 'General', NULL);
INSERT INTO `topic` (`id`, `name`, `description`, `image_url`) VALUES (5, 'Wanted', 'Wanted', NULL);
INSERT INTO `topic` (`id`, `name`, `description`, `image_url`) VALUES (6, 'For Trade', 'For Trade', NULL);
INSERT INTO `topic` (`id`, `name`, `description`, `image_url`) VALUES (7, 'Pests', 'Pests', NULL);
INSERT INTO `topic` (`id`, `name`, `description`, `image_url`) VALUES (8, 'Nutrients', 'Nutrients', NULL);
INSERT INTO `topic` (`id`, `name`, `description`, `image_url`) VALUES (9, 'Plant History', 'Plant History', NULL);
INSERT INTO `topic` (`id`, `name`, `description`, `image_url`) VALUES (10, 'Potting', 'Potting', NULL);
INSERT INTO `topic` (`id`, `name`, `description`, `image_url`) VALUES (11, 'Watering', 'Watering', NULL);
INSERT INTO `topic` (`id`, `name`, `description`, `image_url`) VALUES (12, 'Gardening', 'Gardening', NULL);
INSERT INTO `topic` (`id`, `name`, `description`, `image_url`) VALUES (13, 'Seeds', 'Seeds', NULL);

COMMIT;


-- -----------------------------------------------------
-- Data for table `post`
-- -----------------------------------------------------
START TRANSACTION;
USE `plantdb`;
INSERT INTO `post` (`id`, `user_id`, `topic_id`, `title`, `content`, `image_url`, `create_date`, `update_date`, `active`) VALUES (1, 1, 2, 'Check out Todd!!!', 'Todd is getting a little crazy here. I must have used the right fertilizer', 'https://cdn.shoplightspeed.com/shops/620787/files/22745656/plant-shop-at-junebug-split-leaf-philodendron-6.jpg', '2022-05-24 12:25:00', '2022-05-24 13:25:00', 1);

COMMIT;


-- -----------------------------------------------------
-- Data for table `comment`
-- -----------------------------------------------------
START TRANSACTION;
USE `plantdb`;
INSERT INTO `comment` (`id`, `user_id`, `post_id`, `in_reply_to_id`, `content`, `create_date`, `active`) VALUES (1, 1, 1, NULL, 'Our first comment', '2022-05-24 12:00:00', 1);
INSERT INTO `comment` (`id`, `user_id`, `post_id`, `in_reply_to_id`, `content`, `create_date`, `active`) VALUES (2, 2, 1, 1, 'Reply to comment', '2022-05-24 12:05:01', 1);

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
INSERT INTO `blog` (`id`, `user_id`, `title`, `content`, `image_url`, `create_date`, `update_date`, `active`) VALUES (1, 1, 'Welcome to Plant Daddy :)', 'Hello and welcome to Plant Daddy! We are a group of developers with a green thumb and a passion for watching our babies grow. Together we built this application for fellow plant enthusiast to come together and share their passion. Whether it’s helping develop our library of plants, or sharing posts for all to see and learn. Members of our community can come together and all become plant daddies.', 'http://www.goodnet.org/photos/620x0/29079_hd.jpg  \n', '2022-05-24 12:00:00', '2022-05-24 12:00:01', 1);

COMMIT;


-- -----------------------------------------------------
-- Data for table `todo`
-- -----------------------------------------------------
START TRANSACTION;
USE `plantdb`;
INSERT INTO `todo` (`id`, `user_plant_id`, `name`, `description`, `date_created`, `due_date`, `completion_date`, `completed`) VALUES (1, 1, 'Water Todd', 'need to water Todd', '2022-05-24 12:00:00', '2022-05-30', '2202-05-24 12:01:01', 1);
INSERT INTO `todo` (`id`, `user_plant_id`, `name`, `description`, `date_created`, `due_date`, `completion_date`, `completed`) VALUES (2, 2, 'Repot Larry', 'Larry needs new soil', '2022-06-1 12:00:00', '2022-06-24', '2022-06-02', 1);
INSERT INTO `todo` (`id`, `user_plant_id`, `name`, `description`, `date_created`, `due_date`, `completion_date`, `completed`) VALUES (3, 3, 'Rotate Phil', 'Get Phil a bigger pot', '2022-06-1 12:00:00', '2022-06-5', NULL, 0);

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


-- -----------------------------------------------------
-- Data for table `growth_snapshot`
-- -----------------------------------------------------
START TRANSACTION;
USE `plantdb`;
INSERT INTO `growth_snapshot` (`growth_id`, `user_plant_id`, `height_inches`, `spread_inches`, `pot_diameter`, `create_date`, `growth_description`, `growth_image`) VALUES (1, 1, 1, 1, 1, '2022-05-24 12:00:00', 'Todo is just a baby! Only an inch tall', 'https://media.karousell.com/media/photos/products/2021/5/31/snake_plant_in_terracotta_pot__1622468729_bdf52e4d_progressive.jpg');
INSERT INTO `growth_snapshot` (`growth_id`, `user_plant_id`, `height_inches`, `spread_inches`, `pot_diameter`, `create_date`, `growth_description`, `growth_image`) VALUES (2, 1, 6, 4, 3, '2022-06-1 12:00:00', 'Todd is about 6 inches tall!', 'https://d3gkbidvk2xej.cloudfront.net/images/products/f7d2e056-bb0e-45cc-aa7e-f23056d5cdfd/s/snake-plant-nouvelle-medium-gray-nouvelle-ceramic.jpeg?version=1647962536.49790019800');
INSERT INTO `growth_snapshot` (`growth_id`, `user_plant_id`, `height_inches`, `spread_inches`, `pot_diameter`, `create_date`, `growth_description`, `growth_image`) VALUES (3, 2, 2, 2, 1, '2022-05-24 12:00:00', 'My newest plant Larry!', 'https://www.romeoandsucculent.co.uk/wp-content/uploads/2020/11/IMG_2131.jpg');
INSERT INTO `growth_snapshot` (`growth_id`, `user_plant_id`, `height_inches`, `spread_inches`, `pot_diameter`, `create_date`, `growth_description`, `growth_image`) VALUES (4, 2, 6, 6, 5, '2022-06-1 12:00:00', 'Larry is growing so fast!!', 'https://ii1.pepperfry.com/media/catalog/product/g/o/1100x1210/good-luck-and-air-purifying-green-aglaonema-snow-white-chinese-evergreen-plant-in-white-drip-glazed--f2vp3i.jpg');
INSERT INTO `growth_snapshot` (`growth_id`, `user_plant_id`, `height_inches`, `spread_inches`, `pot_diameter`, `create_date`, `growth_description`, `growth_image`) VALUES (5, 3, 3, 3, 2, '2022-05-24 12:00:00', 'My baby split leaf getting his first split!', 'https://i5.walmartimages.com/asr/5f022118-c101-4569-8270-bf6dcb19a96d.1026409995a8b853e515d08736977bfb.jpeg');
INSERT INTO `growth_snapshot` (`growth_id`, `user_plant_id`, `height_inches`, `spread_inches`, `pot_diameter`, `create_date`, `growth_description`, `growth_image`) VALUES (6, 3, 8, 6, 3, '2022-06-1 12:00:00', 'Phil is getting so tall! ', 'https://cdn.shopify.com/s/files/1/2528/3612/products/4_MONSTERA_DELICIOSA_1.jpg?v=1627692378');
INSERT INTO `growth_snapshot` (`growth_id`, `user_plant_id`, `height_inches`, `spread_inches`, `pot_diameter`, `create_date`, `growth_description`, `growth_image`) VALUES (7, 4, 2, 3, 3, '2022-06-1 12:00:00', 'Rod is just a baby!!', 'https://www.thespruce.com/thmb/MyxDIrqMGPWVIYwDuzAR7yWT50s=/3000x2000/filters:fill(auto,1)/how-to-propagate-aloe-vera-5087447-hero-32432ce03913495896fb2fc6295e0a58.jpg');

COMMIT;


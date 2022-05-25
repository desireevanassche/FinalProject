package com.skilldistillery.plantdaddyapp.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PlantPhotoTest {

	private static EntityManagerFactory emf;
	private EntityManager em;
	private PlantPhoto plantPhoto;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		emf = Persistence.createEntityManagerFactory("PlantDaddyJPA");
		
		
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
		emf.close();
	}

	@BeforeEach
	void setUp() throws Exception {
		em = emf.createEntityManager();
		plantPhoto = em.find(PlantPhoto.class, 1);
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
		plantPhoto = null;
	}

	
	@Test
	@DisplayName("Testing plant photo mapping")
	void test_plant_photo_to_db() {
		
//		mysql> select * from plant_photo where id = 1;
//		+----+---------------+---------------------------------------------------------------------------------------+---------------------+
//		| id | user_plant_id | image_url                                                                             | date_created        |
//		+----+---------------+---------------------------------------------------------------------------------------+---------------------+
//		|  1 |             1 | https://i.etsystatic.com/18193121/r/il/872f52/2144887556/il_570xN.2144887556_ynis.jpg | 2022-05-24 12:30:00 |
//		+----+---------------+---------------------------------------------------------------------------------------+---------------------+
//		1 row in set (0.00 sec)
		
		assertNotNull(plantPhoto);
		assertNotNull(plantPhoto.getId());
		assertEquals("https://i.etsystatic.com/18193121/r/il/872f52/2144887556/il_570xN.2144887556_ynis.jpg", plantPhoto.getImageUrl());
	}

	@Test
	@DisplayName("Testing realtional mapping from plant photo to user plant")
	void test_relational_mapping_from_plant_photo_to_user_plant_db() {
		
//		mysql> select * from plant_photo join user_plant on user_plant.id = plant_photo.user_plant_id where plant_photo.id=1;
//		+----+---------------+---------------------------------------------------------------------------------------+---------------------+----+---------+----------+---------------+---------------+----------+---------------------+-------------------------------------------------------------------------------------------------+---------------+--------------+--------+
//		| id | user_plant_id | image_url                                                                             | date_created        | id | user_id | plant_id | height_inches | spread_inches | nickname | pot_diameter_inches | image_url                                                                                       | home_location | description  | active |
//		+----+---------------+---------------------------------------------------------------------------------------+---------------------+----+---------+----------+---------------+---------------+----------+---------------------+-------------------------------------------------------------------------------------------------+---------------+--------------+--------+
//		|  1 |             1 | https://i.etsystatic.com/18193121/r/il/872f52/2144887556/il_570xN.2144887556_ynis.jpg | 2022-05-24 12:30:00 |  1 |       1 |        1 |             6 |             6 | Todd     |                   4 | https://www.bybrittanygoldwyn.com/wp-content/uploads/2021/03/Sans-Trifasciata-Snake-Plant-6.jpg | Living room   | My happy boi |      1 |
//		+----+---------------+---------------------------------------------------------------------------------------+---------------------+----+---------+----------+---------------+---------------+----------+---------------------+-------------------------------------------------------------------------------------------------+---------------+--------------+--------+
//		1 row in set (0.00 sec)
		
		assertNotNull(plantPhoto);
		assertNotNull(plantPhoto.getId());
		assertEquals("Living room", plantPhoto.getUserPlant().getHomeLocation() );
		
	}
}

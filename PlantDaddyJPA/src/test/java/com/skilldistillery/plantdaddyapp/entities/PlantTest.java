package com.skilldistillery.plantdaddyapp.entities;

import static org.junit.jupiter.api.Assertions.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PlantTest {

	private static EntityManagerFactory emf;
	private EntityManager em;
	private Plant plant;

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
		plant = em.find(Plant.class, 1);
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
		plant = null;
	}

	/* | id | created_by_id | common_name | description | image_url                                                      | botanical_name       | care_difficulty | water_cycle | water_type | light_requirement | active |
+----+---------------+-------------+-------------+----------------------------------------------------------------+----------------------+-----------------+-------------+------------+-------------------+--------+
|  1 |             1 | Snake Plant | Tall Boi    | https://h2.commercev3.net/cdn.brecks.com/images/800/76621A.jpg | Dracaena trifasciata | Easy            | 2-8 weeks */ 
	@Test
	@DisplayName("Testing plant mapping")
	void test() {
		assertNotNull(plant);
		assertEquals("Snake Plant", plant.getCommonName()); 
		assertEquals("Sansevieria trifasciata", plant.getBotanicalName());
		assertEquals("https://live.staticflickr.com/65535/52097469117_4e2fa16bde_z.jpg", plant.getImageUrl());
		
		
		
	}
	
	@Test
	@DisplayName("Testing relational mapping from plant to user db")
	void test2() {
		
//		mysql> select * from plant join user on user.id=plant.created_by_id where plant.id=1;
//		+----+---------------+-------------+-------------+----------------------------------------------------------------+----------------------+-----------------+-------------+---------------+---------------------------------+--------+----+------------+----------+--------------------------------------------------------------+---------+------------+-----------+-----------------+--------------------------------------------------+----------------------------------+------------+
//		| id | created_by_id | common_name | description | image_url                                                      | botanical_name       | care_difficulty | water_cycle | water_type    | light_requirement               | active | id | address_id | username | password                                                     | enabled | first_name | last_name | email           | image_url                                        | biography                        | role       |
//		+----+---------------+-------------+-------------+----------------------------------------------------------------+----------------------+-----------------+-------------+---------------+---------------------------------+--------+----+------------+----------+--------------------------------------------------------------+---------+------------+-----------+-----------------+--------------------------------------------------+----------------------------------+------------+
//		|  1 |             1 | Snake Plant | Tall Boi    | https://h2.commercev3.net/cdn.brecks.com/images/800/76621A.jpg | Dracaena trifasciata | Easy            | 2-8 weeks   | mineral water | 8 - 10 hours of direct sunlight |      1 |  1 |          1 | admin    | $2a$10$XR0stvrxAeiPsPSh0hHruesmB0UETSkbRPjK3fRxibq0DvQ/eoQbm |       1 | admin      | admin     | admin@admin.com | https://freesvg.org/img/abstract-user-flat-4.png | Look at me, I am the captian now | ROLE_ADMIN |
//		+----+---------------+-------------+-------------+----------------------------------------------------------------+----------------------+-----------------+-------------+---------------+---------------------------------+--------+----+------------+----------+--------------------------------------------------------------+---------+------------+-----------+-----------------+--------------------------------------------------+----------------------------------+------------+
//		1 row in set (0.00 sec)
		
		assertNotNull(plant);
		assertNotNull(plant.getId());
		assertEquals("admin",plant.getUser().getUsername());
	}
	
	@Test
	@DisplayName("Testing relational mapping from plant to user plant db")
	void test_relational_mapping_from_plant_to_userplant_in_db() {
		
//		mysql> select * from plant join user_plant on plant.id = user_plant.plant_id where plant.id =1;
//		+----+---------------+-------------+-------------+----------------------------------------------------------------+----------------------+-----------------+-------------+---------------+---------------------------------+--------+----+---------+----------+---------------+---------------+----------+---------------------+-------------------------------------------------------------------------------------------------+---------------+--------------+--------+
//		| id | created_by_id | common_name | description | image_url                                                      | botanical_name       | care_difficulty | water_cycle | water_type    | light_requirement               | active | id | user_id | plant_id | height_inches | spread_inches | nickname | pot_diameter_inches | image_url                                                                                       | home_location | description  | active |
//		+----+---------------+-------------+-------------+----------------------------------------------------------------+----------------------+-----------------+-------------+---------------+---------------------------------+--------+----+---------+----------+---------------+---------------+----------+---------------------+-------------------------------------------------------------------------------------------------+---------------+--------------+--------+
//		|  1 |             1 | Snake Plant | Tall Boi    | https://h2.commercev3.net/cdn.brecks.com/images/800/76621A.jpg | Dracaena trifasciata | Easy            | 2-8 weeks   | mineral water | 8 - 10 hours of direct sunlight |      1 |  1 |       1 |        1 |             6 |             6 | Todd     |                   4 | https://www.bybrittanygoldwyn.com/wp-content/uploads/2021/03/Sans-Trifasciata-Snake-Plant-6.jpg | Living room   | My happy boi |      1 |
//		+----+---------------+-------------+-------------+----------------------------------------------------------------+----------------------+-----------------+-------------+---------------+---------------------------------+--------+----+---------+----------+---------------+---------------+----------+---------------------+-------------------------------------------------------------------------------------------------+---------------+--------------+--------+
//		1 row in set (0.01 sec)
		
		assertNotNull(plant);
		assertNotNull(plant.getId());
		assertTrue( plant.getPlants().size()>0);
	}

	@Test
	@DisplayName("Testing relational mapping from plant category to plant db")
	void test_relational_mapping_from_plant_category_to_plant() {
		
//		mysql> select * from plant join plant_has_plant_category on plant.id=plant_has_plant_category.plant_id join plant_category on plant_has_plant_category.plant_category_id = plant_category.id where plant.id = 1;
//		+----+---------------+-------------+-------------+----------------------------------------------------------------+----------------------+-----------------+-------------+---------------+---------------------------------+--------+----------+-------------------+----+-----------------+-------------------------+-------------------------------------------------------------------------------------------------------------------------------------------------------------+
//		| id | created_by_id | common_name | description | image_url                                                      | botanical_name       | care_difficulty | water_cycle | water_type    | light_requirement               | active | plant_id | plant_category_id | id | name            | description             | image_url                                                                                                                                                   |
//		+----+---------------+-------------+-------------+----------------------------------------------------------------+----------------------+-----------------+-------------+---------------+---------------------------------+--------+----------+-------------------+----+-----------------+-------------------------+-------------------------------------------------------------------------------------------------------------------------------------------------------------+
//		|  1 |             1 | Snake Plant | Tall Boi    | https://h2.commercev3.net/cdn.brecks.com/images/800/76621A.jpg | Dracaena trifasciata | Easy            | 2-8 weeks   | mineral water | 8 - 10 hours of direct sunlight |      1 |        1 |                 1 |  1 | flowering plant | bears flowers or fruits | https://upload.wikimedia.org/wikipedia/commons/thumb/1/1d/Maitohorsma_%28Epilobium_angustifolium%29.JPG/380px-Maitohorsma_%28Epilobium_angustifolium%29.JPG |
//		+----+---------------+-------------+-------------+----------------------------------------------------------------+----------------------+-----------------+-------------+---------------+---------------------------------+--------+----------+-------------------+----+-----------------+-------------------------+-------------------------------------------------------------------------------------------------------------------------------------------------------------+
//		1 row in set (0.01 sec)
		
		assertNotNull(plant);
		assertNotNull(plant.getId());
		assertTrue( plant.getPlantCategories().size()>0);
		
	}

	
	@Test
	@DisplayName("Testing relational mapping from plant to potting mix db")
	void test_relational_mapping_from_plant_to_potting_mix_to_plant() {
		
//		mysql> select * from plant join plant_has_potting_mix jpm on  plant.id = jpm.plant_id join potting_mix pm on jpm.potting_mix_id = pm.id where plant.id=1;
//		+----+---------------+-------------+-------------+----------------------------------------------------------------+----------------------+-----------------+-------------+---------------+---------------------------------+--------+----------+----------------+----+---------+-------------+-------------+------+----------+------+------------------------------------------------------+---------------------+--------+
//		| id | created_by_id | common_name | description | image_url                                                      | botanical_name       | care_difficulty | water_cycle | water_type    | light_requirement               | active | plant_id | potting_mix_id | id | user_id | brand       | name        | type | quantity | unit | image_url                                            | date_created        | active |
//		+----+---------------+-------------+-------------+----------------------------------------------------------------+----------------------+-----------------+-------------+---------------+---------------------------------+--------+----------+----------------+----+---------+-------------+-------------+------+----------+------+------------------------------------------------------+---------------------+--------+
//		|  1 |             1 | Snake Plant | Tall Boi    | https://h2.commercev3.net/cdn.brecks.com/images/800/76621A.jpg | Dracaena trifasciata | Easy            | 2-8 weeks   | mineral water | 8 - 10 hours of direct sunlight |      1 |        1 |              1 |  1 |       1 | Miracle Gro | Potting Mix | Soil |        1 | cup  | https://images.heb.com/is/image/HEBGrocery/001382808 | 2022-05-22 12:00:00 |      1 |
//		+----+---------------+-------------+-------------+----------------------------------------------------------------+----------------------+-----------------+-------------+---------------+---------------------------------+--------+----------+----------------+----+---------+-------------+-------------+------+----------+------+------------------------------------------------------+---------------------+--------+
//		1 row in set (0.00 sec)
		
		
		assertNotNull(plant);
		assertNotNull(plant.getId());
		assertTrue( plant.getPottingMixes().size()>0);
		
	}
	
	@Test
	@DisplayName("Testing relational mapping from plant to store db")
	void test_relational_mapping_from_plant_to_store_to_plant() {
		
//		mysql> select * from plant join store_has_plant phs on plant.id=phs.plant_id join store on store.id = phs.plant_id where plant.id=1;
//		+----+---------------+-------------+-------------+----------------------------------------------------------------+----------------------+-----------------+-------------+---------------+---------------------------------+--------+----------+----------+----+----------------+------------+-----------------------------------------------------------------------------------------------------------+
//		| id | created_by_id | common_name | description | image_url                                                      | botanical_name       | care_difficulty | water_cycle | water_type    | light_requirement               | active | store_id | plant_id | id | name           | address_id | image_url                                                                                                 |
//		+----+---------------+-------------+-------------+----------------------------------------------------------------+----------------------+-----------------+-------------+---------------+---------------------------------+--------+----------+----------+----+----------------+------------+-----------------------------------------------------------------------------------------------------------+
//		|  1 |             1 | Snake Plant | Tall Boi    | https://h2.commercev3.net/cdn.brecks.com/images/800/76621A.jpg | Dracaena trifasciata | Easy            | 2-8 weeks   | mineral water | 8 - 10 hours of direct sunlight |      1 |        1 |        1 |  1 | The Flower Bin |          1 | http://1.bp.blogspot.com/-M4LiJ0qCYhY/UQHvvCxZIYI/AAAAAAAAAB0/QD259KjwM1Q/s400/20100615_FLOWER_BIN-55.JPG |
//		+----+---------------+-------------+-------------+----------------------------------------------------------------+----------------------+-----------------+-------------+---------------+---------------------------------+--------+----------+----------+----+----------------+------------+-----------------------------------------------------------------------------------------------------------+
//		1 row in set (0.01 sec)

		
		assertNotNull(plant);
		assertNotNull(plant.getId());
		assertTrue( plant.getStores().size()>0);
		
	}
	
	
}


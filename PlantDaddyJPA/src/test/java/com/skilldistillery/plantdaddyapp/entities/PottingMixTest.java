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

class PottingMixTest {

	private static EntityManagerFactory emf;
	private EntityManager em;
	private PottingMix pottingMix;

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
		pottingMix = em.find(PottingMix.class, 1);
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
		pottingMix = null;
	}
	/*
	 * +----+---------+-------------+-------------+------+----------+------+------------------------------------------------------+---------------------+--------+
| id | user_id | brand       | name        | type | quantity | unit | image_url                                            | date_created        | active |
+----+---------+-------------+-------------+------+----------+------+------------------------------------------------------+---------------------+--------+
|  1 |       1 | Miracle Gro | Potting Mix | Soil |        1 | cup  | https://images.heb.com/is/image/HEBGrocery/001382808 | 2022-05-22 12:00:00 |      1 |
+----+---------+-------------+-------------+------+----------+------+------------------------------------------------------+---------------------+--------+
	 */

	@Test
	@DisplayName("Test entity mapping for Potting Mix")
	void test() {
		assertNotNull(pottingMix);
		assertEquals("Miracle Gro", pottingMix.getBrand());
		assertEquals("Potting Mix", pottingMix.getName());
		assertEquals("Soil", pottingMix.getType());
		assertEquals(1, pottingMix.getQuantity());
		assertEquals("cup", pottingMix.getUnit());
		assertEquals("https://images.heb.com/is/image/HEBGrocery/001382808", pottingMix.getImageUrl());
		assertEquals("2022-05-22", pottingMix.getDateCreated().toString());
	
	}
	/*
	 * mysql> SELECT * FROM potting_mix pm JOIN user u ON pm.user_id = u.id;
	 */
	@Test
	@DisplayName("Test for pottinf mix to user relationship")
	void test2() {
		assertNotNull(pottingMix);
		assertEquals("admin", pottingMix.getUser().getUsername());
		assertEquals("$2a$10$XR0stvrxAeiPsPSh0hHruesmB0UETSkbRPjK3fRxibq0DvQ/eoQbm", pottingMix.getUser().getPassword());
		assertEquals("admin@admin.com", pottingMix.getUser().getEmail());
		assertEquals("https://freesvg.org/img/abstract-user-flat-4.png", pottingMix.getUser().getImageUrl());
		assertEquals("Look at me, I am the captian now", pottingMix.getUser().getBiography());
		assertEquals("ROLE_ADMIN", pottingMix.getUser().getRole());
	}
	
	
	@Test
	@DisplayName("Testing relational mapping from potting mix to plant db")
	void test_relational_mapping_from_potting_mix_to_plant_to_plant() {
		
//		mysql> select * from plant join plant_has_potting_mix jpm on  plant.id = jpm.plant_id join potting_mix pm on jpm.potting_mix_id = pm.id where plant.id=1;
//		+----+---------------+-------------+-------------+----------------------------------------------------------------+----------------------+-----------------+-------------+---------------+---------------------------------+--------+----------+----------------+----+---------+-------------+-------------+------+----------+------+------------------------------------------------------+---------------------+--------+
//		| id | created_by_id | common_name | description | image_url                                                      | botanical_name       | care_difficulty | water_cycle | water_type    | light_requirement               | active | plant_id | potting_mix_id | id | user_id | brand       | name        | type | quantity | unit | image_url                                            | date_created        | active |
//		+----+---------------+-------------+-------------+----------------------------------------------------------------+----------------------+-----------------+-------------+---------------+---------------------------------+--------+----------+----------------+----+---------+-------------+-------------+------+----------+------+------------------------------------------------------+---------------------+--------+
//		|  1 |             1 | Snake Plant | Tall Boi    | https://h2.commercev3.net/cdn.brecks.com/images/800/76621A.jpg | Dracaena trifasciata | Easy            | 2-8 weeks   | mineral water | 8 - 10 hours of direct sunlight |      1 |        1 |              1 |  1 |       1 | Miracle Gro | Potting Mix | Soil |        1 | cup  | https://images.heb.com/is/image/HEBGrocery/001382808 | 2022-05-22 12:00:00 |      1 |
//		+----+---------------+-------------+-------------+----------------------------------------------------------------+----------------------+-----------------+-------------+---------------+---------------------------------+--------+----------+----------------+----+---------+-------------+-------------+------+----------+------+------------------------------------------------------+---------------------+--------+
//		1 row in set (0.00 sec)
		
		
		assertNotNull(pottingMix);
		assertNotNull(pottingMix.getId());
		assertTrue(pottingMix.getPlants().size()>0);
	}

}

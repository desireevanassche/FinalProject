package com.skilldistillery.plantdaddyapp.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PlantCategoryTest {

	private static EntityManagerFactory emf;
	private EntityManager em;
	private PlantCategory plantCat;

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
		plantCat = em.find(PlantCategory.class, 1);
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
		plantCat = null;
	}

	/*
	 * +----+-----------------+-------------------------+-------------------------------------------------------------------------------------------------------------------------------------------------------------+
| id | name            | description             | image_url                                                                                                                                                   |
+----+-----------------+-------------------------+-------------------------------------------------------------------------------------------------------------------------------------------------------------+
|  1 | flowering plant | bears flowers or fruits | https://upload.wikimedia.org/wikipedia/commons/thumb/1/1d/Maitohorsma_%28Epilobium_angustifolium%29.JPG/380px-Maitohorsma_%28Epilobium_angustifolium%29.JPG |
+----+-----------------+-------------------------+-------------------------------------------------------------------------------------------------------------------------------------------------------------+
	 */
	
	
	@Test
	@DisplayName("test for Plant Category Mapping")
	void test() {
		assertNotNull(plantCat);
		assertEquals("flowering plant", plantCat.getName());
		assertEquals("bears flowers or fruits", plantCat.getDescription());
		assertEquals("https://upload.wikimedia.org/wikipedia/commons/thumb/1/1d/Maitohorsma_%28Epilobium_angustifolium%29.JPG/380px-Maitohorsma_%28Epilobium_angustifolium%29.JPG", plantCat.getImageUrl());
	}

	
	@Test
	@DisplayName("Testing relational mapping of join tables plant to plant category")
	void test_reltaional_mapping_of_join_tables_from_plant_to_plant_category() {
		
//		mysql> select * from plant join plant_has_plant_category on plant.id=plant_has_plant_category.plant_id join plant_category on plant_has_plant_category.plant_category_id = plant_category.id where plant.id = 1;
//		+----+---------------+-------------+-------------+----------------------------------------------------------------+----------------------+-----------------+-------------+---------------+---------------------------------+--------+----------+-------------------+----+-----------------+-------------------------+-------------------------------------------------------------------------------------------------------------------------------------------------------------+
//		| id | created_by_id | common_name | description | image_url                                                      | botanical_name       | care_difficulty | water_cycle | water_type    | light_requirement               | active | plant_id | plant_category_id | id | name            | description             | image_url                                                                                                                                                   |
//		+----+---------------+-------------+-------------+----------------------------------------------------------------+----------------------+-----------------+-------------+---------------+---------------------------------+--------+----------+-------------------+----+-----------------+-------------------------+-------------------------------------------------------------------------------------------------------------------------------------------------------------+
//		|  1 |             1 | Snake Plant | Tall Boi    | https://h2.commercev3.net/cdn.brecks.com/images/800/76621A.jpg | Dracaena trifasciata | Easy            | 2-8 weeks   | mineral water | 8 - 10 hours of direct sunlight |      1 |        1 |                 1 |  1 | flowering plant | bears flowers or fruits | https://upload.wikimedia.org/wikipedia/commons/thumb/1/1d/Maitohorsma_%28Epilobium_angustifolium%29.JPG/380px-Maitohorsma_%28Epilobium_angustifolium%29.JPG |
//		+----+---------------+-------------+-------------+----------------------------------------------------------------+----------------------+-----------------+-------------+---------------+---------------------------------+--------+----------+-------------------+----+-----------------+-------------------------+-------------------------------------------------------------------------------------------------------------------------------------------------------------+
//		1 row in set (0.01 sec)

		
		
		assertNotNull(plantCat);
		assertNotNull(plantCat.getId());
		assertTrue(plantCat.getPlants().size()>0);
		
		
	}
}

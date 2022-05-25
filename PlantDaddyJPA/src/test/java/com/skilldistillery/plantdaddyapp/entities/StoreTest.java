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

class StoreTest {

	private static EntityManagerFactory emf;
	private EntityManager em;
	private Store store;

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
		store = em.find(Store.class, 1);
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
		store = null;
	}
	
	
	@Test
	@DisplayName("testing store to database")
	void test() {
		
//		mysql> select * from store;
//		+----+----------------+------------+-----------------------------------------------------------------------------------------------------------+
//		| id | name           | address_id | image_url                                                                                                 |
//		+----+----------------+------------+-----------------------------------------------------------------------------------------------------------+
//		|  1 | The Flower Bin |          1 | http://1.bp.blogspot.com/-M4LiJ0qCYhY/UQHvvCxZIYI/AAAAAAAAAB0/QD259KjwM1Q/s400/20100615_FLOWER_BIN-55.JPG |
//		+----+----------------+------------+-----------------------------------------------------------------------------------------------------------+
//		1 row in set (0.00 sec)

		assertNotNull(store);
		assertNotNull(store.getId());
		assertEquals("The Flower Bin", store.getName());
		
		
	}
	
	
	@Test
	@DisplayName("testing relational mapping from store to address")
	void test1(){
		
//		mysql> select * from store join address on address.id = store.address_id where store.id = 1;
//		+----+----------------+------------+-----------------------------------------------------------------------------------------------------------+----+----------------+---------+----------+----------+-----------+
//		| id | name           | address_id | image_url                                                                                                 | id | street         | street2 | city     | state    | area_code |
//		+----+----------------+------------+-----------------------------------------------------------------------------------------------------------+----+----------------+---------+----------+----------+-----------+
//		|  1 | The Flower Bin |          1 | http://1.bp.blogspot.com/-M4LiJ0qCYhY/UQHvvCxZIYI/AAAAAAAAAB0/QD259KjwM1Q/s400/20100615_FLOWER_BIN-55.JPG |  1 | 1805 Nelson Rd | NULL    | Longmont | Colorado | 80501     |
//		+----+----------------+------------+-----------------------------------------------------------------------------------------------------------+----+----------------+---------+----------+----------+-----------+
//		1 row in set (0.00 sec)
		
		assertNotNull(store);
		assertNotNull(store.getId());
		assertEquals("Colorado", store.getAddress().getState());
		
	}
	
	
	@Test
	@DisplayName("Testing relational mapping from store to plant db")
	void test_relational_mapping_from_store_to_plant() {
		
//		mysql> select * from plant join store_has_plant phs on plant.id=phs.plant_id join store on store.id = phs.plant_id where plant.id=1;
//		+----+---------------+-------------+-------------+----------------------------------------------------------------+----------------------+-----------------+-------------+---------------+---------------------------------+--------+----------+----------+----+----------------+------------+-----------------------------------------------------------------------------------------------------------+
//		| id | created_by_id | common_name | description | image_url                                                      | botanical_name       | care_difficulty | water_cycle | water_type    | light_requirement               | active | store_id | plant_id | id | name           | address_id | image_url                                                                                                 |
//		+----+---------------+-------------+-------------+----------------------------------------------------------------+----------------------+-----------------+-------------+---------------+---------------------------------+--------+----------+----------+----+----------------+------------+-----------------------------------------------------------------------------------------------------------+
//		|  1 |             1 | Snake Plant | Tall Boi    | https://h2.commercev3.net/cdn.brecks.com/images/800/76621A.jpg | Dracaena trifasciata | Easy            | 2-8 weeks   | mineral water | 8 - 10 hours of direct sunlight |      1 |        1 |        1 |  1 | The Flower Bin |          1 | http://1.bp.blogspot.com/-M4LiJ0qCYhY/UQHvvCxZIYI/AAAAAAAAAB0/QD259KjwM1Q/s400/20100615_FLOWER_BIN-55.JPG |
//		+----+---------------+-------------+-------------+----------------------------------------------------------------+----------------------+-----------------+-------------+---------------+---------------------------------+--------+----------+----------+----+----------------+------------+-----------------------------------------------------------------------------------------------------------+
//		1 row in set (0.01 sec)

		
		assertNotNull(store);
		assertNotNull(store.getId());
		assertTrue( store.getPlants().size()>0);
		
	}


}

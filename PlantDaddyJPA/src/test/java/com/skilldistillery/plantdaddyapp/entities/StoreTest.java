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
	
	


}

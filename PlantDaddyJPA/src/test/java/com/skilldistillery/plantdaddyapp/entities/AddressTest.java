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

class AddressTest {

	private static EntityManagerFactory emf;
	private EntityManager em;
	private Address add;

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
		add = em.find(Address.class, 1);
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
		add = null;
	}

	
	
	@Test
	@DisplayName("Testing entity address to datebase")
	void test() {
//		mysql> select * from address where address.id = 1;
//		+----+----------------+---------+----------+----------+-----------+
//		| id | street         | street2 | city     | state    | area_code |
//		+----+----------------+---------+----------+----------+-----------+
//		|  1 | 1805 Nelson Rd | NULL    | Longmont | Colorado | 80501     |
//		+----+----------------+---------+----------+----------+-----------+
//		1 row in set (0.00 sec)
		
		assertNotNull(add);
		assertNotNull(add.getId());
		assertEquals("Colorado", add.getState());
		
	}
	
	@Test
	@DisplayName("Testing relational mapping from address to store to datebase")
	void test1() {
		
//		mysql> select * from address join store on address.id = store.address_id where address.id = 1;
//		+----+----------------+---------+----------+----------+-----------+----+----------------+------------+-----------------------------------------------------------------------------------------------------------+
//		| id | street         | street2 | city     | state    | area_code | id | name           | address_id | image_url                                                                                                 |
//		+----+----------------+---------+----------+----------+-----------+----+----------------+------------+-----------------------------------------------------------------------------------------------------------+
//		|  1 | 1805 Nelson Rd | NULL    | Longmont | Colorado | 80501     |  1 | The Flower Bin |          1 | http://1.bp.blogspot.com/-M4LiJ0qCYhY/UQHvvCxZIYI/AAAAAAAAAB0/QD259KjwM1Q/s400/20100615_FLOWER_BIN-55.JPG |
//		+----+----------------+---------+----------+----------+-----------+----+----------------+------------+-----------------------------------------------------------------------------------------------------------+
//		1 row in set (0.00 sec)
		
		assertNotNull(add);
		assertNotNull(add.getId());
		assertEquals("The Flower Bin",add.getStore().getName());
		
	}
	
	
}

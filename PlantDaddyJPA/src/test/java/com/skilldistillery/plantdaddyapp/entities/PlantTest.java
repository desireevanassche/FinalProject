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
		assertEquals("Dracaena trifasciata", plant.getBotanicalName());
		assertEquals("https://h2.commercev3.net/cdn.brecks.com/images/800/76621A.jpg", plant.getImageUrl());
		
		
		
	}
	
}


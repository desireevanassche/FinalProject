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

class TopicTest {

	private static EntityManagerFactory emf;
	private EntityManager em;
	private Topic topic;

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
		topic = em.find(Topic.class, 1);
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
		topic = null;
	}

/* +----+--------+--------------+----------------------------------------------------------------------------------------------------+
| id | name   | description  | image_url                                                                                          |
+----+--------+--------------+----------------------------------------------------------------------------------------------------+
|  1 | Indoor | Indoor Plant | https://media.allure.com/photos/5fdcf516563e46c7d11ee93f/master/pass/AllureBeginnerHouseplants.jpg |
+----+--------+--------------+----------------------------------------------------------------------------------------------------+ */ 
	@Test
	@DisplayName("Testing comment mapping")
	void test() {
		assertNotNull(topic);
		assertEquals("Indoor", topic.getName());
		assertEquals("Indoor Plant", topic.getDescription());
	
		
	}
}

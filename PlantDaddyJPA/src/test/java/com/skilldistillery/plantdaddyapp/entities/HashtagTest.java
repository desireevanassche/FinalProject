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

class HashtagTest {
	
	private static EntityManagerFactory emf;
	private EntityManager em;
	private Hashtag hashtag;

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
		hashtag = em.find(Hashtag.class, 1);
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
		hashtag = null;
	}
	
	@Test
	@DisplayName("testing for entity hashtag mapping")
	void test() {
		assertNotNull(hashtag);
		assertEquals("Plant", hashtag.getName());
	}
	
	
	
	
	

}

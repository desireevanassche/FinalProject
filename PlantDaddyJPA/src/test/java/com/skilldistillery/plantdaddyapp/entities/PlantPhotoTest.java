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
	void test() {
		assertNotNull(plantPhoto);

	}

}

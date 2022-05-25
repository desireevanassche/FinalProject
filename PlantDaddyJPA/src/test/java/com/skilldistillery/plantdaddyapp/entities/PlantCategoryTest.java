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

}

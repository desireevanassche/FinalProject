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

class TodoTest {

	private static EntityManagerFactory emf;
	private EntityManager em;
	private Todo todo;

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
		todo = em.find(Todo.class, 1);
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
		todo = null;
	}

	/*
	 * +----+---------------+------------+-------------+---------------------+------------+---------------------+
| id | user_plant_id | name       | description | date_created        | due_date   | completion_date     |
+----+---------------+------------+-------------+---------------------+------------+---------------------+
|  1 |             1 | Water Todd | water Todd  | 2202-05-24 12:00:00 | 2022-05-30 | 2202-05-24 12:01:01 |
+----+---------------+------------+-------------+---------------------+------------+---------------------+ */ 

	@Test
	@DisplayName("Testing comment mapping")
	void test() {
		assertNotNull(todo);
		assertEquals("water Todd", todo.getDescription());
		assertEquals("Water Todd", todo.getName());
	
		
	}

}

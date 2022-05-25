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
	
	@Test
	@DisplayName("Testing relational mapping from todo to user plant in db")
	void test_relation_mapping_todo_to_userplant_in_db() {
		
//		mysql> select * from todo join user_plant on user_plant.id = todo.user_plant_id where todo.id = 1;
//		+----+---------------+------------+-------------+---------------------+------------+---------------------+----+---------+----------+---------------+---------------+----------+---------------------+-------------------------------------------------------------------------------------------------+---------------+--------------+--------+
//		| id | user_plant_id | name       | description | date_created        | due_date   | completion_date     | id | user_id | plant_id | height_inches | spread_inches | nickname | pot_diameter_inches | image_url                                                                                       | home_location | description  | active |
//		+----+---------------+------------+-------------+---------------------+------------+---------------------+----+---------+----------+---------------+---------------+----------+---------------------+-------------------------------------------------------------------------------------------------+---------------+--------------+--------+
//		|  1 |             1 | Water Todd | water Todd  | 2202-05-24 12:00:00 | 2022-05-30 | 2202-05-24 12:01:01 |  1 |       1 |        1 |             6 |             6 | Todd     |                   4 | https://www.bybrittanygoldwyn.com/wp-content/uploads/2021/03/Sans-Trifasciata-Snake-Plant-6.jpg | Living room   | My happy boi |      1 |
//		+----+---------------+------------+-------------+---------------------+------------+---------------------+----+---------+----------+---------------+---------------+----------+---------------------+-------------------------------------------------------------------------------------------------+---------------+--------------+--------+
//		1 row in set (0.00 sec)
		
		assertNotNull(todo); 
		assertNotNull(todo.getId());
		assertEquals("Living room",todo.getUserPlant().getHomeLocation());
		
	}

}

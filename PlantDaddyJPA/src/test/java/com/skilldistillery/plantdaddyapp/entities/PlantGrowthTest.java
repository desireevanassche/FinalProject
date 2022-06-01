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

class PlantGrowthTest {

	private static EntityManagerFactory emf;
	private EntityManager em;
	private PlantGrowth pGrowth;

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
		pGrowth = em.find(PlantGrowth.class, 1);
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
		pGrowth = null;
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
		
		assertNotNull(pGrowth);
		assertNotNull(pGrowth.getId());
		assertEquals(6, pGrowth.getHeight());
		
	}
	
	@Test
	@DisplayName("Testing entity address to datebase")
	void test_relational_mapping_from_growth_to_userplant_in_db() {

//		mysql> select * from user_plant u join growth_snapshot g on u.id = g.user_plant_id where u.id=1;
//		+----+---------+----------+---------------+---------------+----------+---------------------+-------------------------------------------------------------------------------------------------+---------------+--------------+--------+-----------+---------------+---------------+---------------+--------------+---------------------+
//		| id | user_id | plant_id | height_inches | spread_inches | nickname | pot_diameter_inches | image_url                                                                                       | home_location | description  | active | growth_id | user_plant_id | height_inches | spread_inches | pot_diameter | create_date         |
//		+----+---------+----------+---------------+---------------+----------+---------------------+-------------------------------------------------------------------------------------------------+---------------+--------------+--------+-----------+---------------+---------------+---------------+--------------+---------------------+
//		|  1 |       1 |        1 |             6 |             6 | Todd     |                   4 | https://www.bybrittanygoldwyn.com/wp-content/uploads/2021/03/Sans-Trifasciata-Snake-Plant-6.jpg | Living room   | My happy boi |      1 |         1 |             1 |             6 |             6 |            5 | 2022-05-24 12:00:00 |
//		+----+---------+----------+---------------+---------------+----------+---------------------+-------------------------------------------------------------------------------------------------+---------------+--------------+--------+-----------+---------------+---------------+---------------+--------------+---------------------+
//		1 row in set (0.00 sec)


		
		assertNotNull(pGrowth);
		assertNotNull(pGrowth.getId());
		assertEquals("Larry", pGrowth.getUserPlant().getNickname());
		
	}

}

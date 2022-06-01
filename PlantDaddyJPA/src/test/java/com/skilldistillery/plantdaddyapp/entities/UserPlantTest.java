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

class UserPlantTest {

	private static EntityManagerFactory emf;
	private EntityManager em;
	private UserPlant uPlant;

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
		uPlant = em.find(UserPlant.class, 1);
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
		uPlant = null;
	}

	
	@Test
	@DisplayName("Entity testing of User Plant database")
	void test_entity_user_plant_to_database() {
		
//		mysql> select * from user_plant where id = 1;
//		+----+---------+----------+---------------+---------------+----------+---------------------+-------------------------------------------------------------------------------------------------+---------------+--------------+--------+
//		| id | user_id | plant_id | height_inches | spread_inches | nickname | pot_diameter_inches | image_url                                                                                       | home_location | description  | active |
//		+----+---------+----------+---------------+---------------+----------+---------------------+-------------------------------------------------------------------------------------------------+---------------+--------------+--------+
//		|  1 |       1 |        1 |             6 |             6 | Todd     |                   4 | https://www.bybrittanygoldwyn.com/wp-content/uploads/2021/03/Sans-Trifasciata-Snake-Plant-6.jpg | Living room   | My happy boi |      1 |
//		+----+---------+----------+---------------+---------------+----------+---------------------+-------------------------------------------------------------------------------------------------+---------------+--------------+--------+
//		1 row in set (0.00 sec)
		
		
		assertNotNull(uPlant);
		assertNotNull(uPlant.getId());
		assertEquals("Todd", uPlant.getNickname());
		
	}
	
	@Test
	@DisplayName("Entity relational mapping of User Plant to User database")
	void test_relational_mapping_of_user_plant_to_user_in_database() {
		

//		mysql> select * from user_plant join user on user.id = user_plant.user_id where user_plant.user_id = 1;
//		+----+---------+----------+---------------+---------------+----------+---------------------+-------------------------------------------------------------------------------------------------+---------------+--------------+--------+----+------------+----------+--------------------------------------------------------------+---------+------------+-----------+-----------------+--------------------------------------------------+----------------------------------+------------+
//		| id | user_id | plant_id | height_inches | spread_inches | nickname | pot_diameter_inches | image_url                                                                                       | home_location | description  | active | id | address_id | username | password                                                     | enabled | first_name | last_name | email           | image_url                                        | biography                        | role       |
//		+----+---------+----------+---------------+---------------+----------+---------------------+-------------------------------------------------------------------------------------------------+---------------+--------------+--------+----+------------+----------+--------------------------------------------------------------+---------+------------+-----------+-----------------+--------------------------------------------------+----------------------------------+------------+
//		|  1 |       1 |        1 |             6 |             6 | Todd     |                   4 | https://www.bybrittanygoldwyn.com/wp-content/uploads/2021/03/Sans-Trifasciata-Snake-Plant-6.jpg | Living room   | My happy boi |      1 |  1 |          1 | admin    | $2a$10$XR0stvrxAeiPsPSh0hHruesmB0UETSkbRPjK3fRxibq0DvQ/eoQbm |       1 | admin      | admin     | admin@admin.com | https://freesvg.org/img/abstract-user-flat-4.png | Look at me, I am the captian now | ROLE_ADMIN |
//		+----+---------+----------+---------------+---------------+----------+---------------------+-------------------------------------------------------------------------------------------------+---------------+--------------+--------+----+------------+----------+--------------------------------------------------------------+---------+------------+-----------+-----------------+--------------------------------------------------+----------------------------------+------------+
//		1 row in set (0.00 sec)
		
		assertNotNull(uPlant);
		assertNotNull(uPlant.getId());
		
	
	}
	
	@Test
	@DisplayName("Entity relational mapping of User Plant to Plant database")
	void test_relational_mapping_of_user_plant_to_plant_in_database() {
		
//		mysql> select * from user_plant join plant on plant.id = user_plant.plant_id where user_plant.plant_id = 1;
//		+----+---------+----------+---------------+---------------+----------+---------------------+-------------------------------------------------------------------------------------------------+---------------+--------------+--------+----+---------------+-------------+-------------+----------------------------------------------------------------+----------------------+-----------------+-------------+---------------+---------------------------------+--------+
//		| id | user_id | plant_id | height_inches | spread_inches | nickname | pot_diameter_inches | image_url                                                                                       | home_location | description  | active | id | created_by_id | common_name | description | image_url                                                      | botanical_name       | care_difficulty | water_cycle | water_type    | light_requirement               | active |
//		+----+---------+----------+---------------+---------------+----------+---------------------+-------------------------------------------------------------------------------------------------+---------------+--------------+--------+----+---------------+-------------+-------------+----------------------------------------------------------------+----------------------+-----------------+-------------+---------------+---------------------------------+--------+
//		|  1 |       1 |        1 |             6 |             6 | Todd     |                   4 | https://www.bybrittanygoldwyn.com/wp-content/uploads/2021/03/Sans-Trifasciata-Snake-Plant-6.jpg | Living room   | My happy boi |      1 |  1 |             1 | Snake Plant | Tall Boi    | https://h2.commercev3.net/cdn.brecks.com/images/800/76621A.jpg | Dracaena trifasciata | Easy            | 2-8 weeks   | mineral water | 8 - 10 hours of direct sunlight |      1 |
//		+----+---------+----------+---------------+---------------+----------+---------------------+-------------------------------------------------------------------------------------------------+---------------+--------------+--------+----+---------------+-------------+-------------+----------------------------------------------------------------+----------------------+-----------------+-------------+---------------+---------------------------------+--------+
//		1 row in set (0.01 sec)

		
		assertNotNull(uPlant);
		assertNotNull(uPlant.getId());
		assertEquals("Easy", uPlant.getPlant().getCareDifficulty());
	
	}
	
	@Test
	@DisplayName("Entity relational mapping of User Plant to Todo database")
	void test_relational_mapping_of_user_plant_to_todo_in_database() {
		
//		mysql> select * from user_plant join todo on todo.user_plant_id = user_plant.id where user_plant.id = 1;
//		+----+---------+----------+---------------+---------------+----------+---------------------+-------------------------------------------------------------------------------------------------+---------------+--------------+--------+----+---------------+------------+-------------+---------------------+------------+---------------------+
//		| id | user_id | plant_id | height_inches | spread_inches | nickname | pot_diameter_inches | image_url                                                                                       | home_location | description  | active | id | user_plant_id | name       | description | date_created        | due_date   | completion_date     |
//		+----+---------+----------+---------------+---------------+----------+---------------------+-------------------------------------------------------------------------------------------------+---------------+--------------+--------+----+---------------+------------+-------------+---------------------+------------+---------------------+
//		|  1 |       1 |        1 |             6 |             6 | Todd     |                   4 | https://www.bybrittanygoldwyn.com/wp-content/uploads/2021/03/Sans-Trifasciata-Snake-Plant-6.jpg | Living room   | My happy boi |      1 |  1 |             1 | Water Todd | water Todd  | 2202-05-24 12:00:00 | 2022-05-30 | 2202-05-24 12:01:01 |
//		+----+---------+----------+---------------+---------------+----------+---------------------+-------------------------------------------------------------------------------------------------+---------------+--------------+--------+----+---------------+------------+-------------+---------------------+------------+---------------------+
//		1 row in set (0.00 sec)
		
		assertNotNull(uPlant);
		assertNotNull(uPlant.getId());
		assertTrue(uPlant.getTodos().size()>0);
	}
	
	@Test
	@DisplayName("Entity relational mapping of User Plant to Plant Photo database")
	void test_relational_mapping_of_user_plant_to_plant_photo_in_database() {
		
//		mysql> select * from user_plant join plant_photo on plant_photo.user_plant_id = user_plant.id where user_plant.id = 1;
//		+----+---------+----------+---------------+---------------+----------+---------------------+-------------------------------------------------------------------------------------------------+---------------+--------------+--------+----+---------------+---------------------------------------------------------------------------------------+---------------------+
//		| id | user_id | plant_id | height_inches | spread_inches | nickname | pot_diameter_inches | image_url                                                                                       | home_location | description  | active | id | user_plant_id | image_url                                                                             | date_created        |
//		+----+---------+----------+---------------+---------------+----------+---------------------+-------------------------------------------------------------------------------------------------+---------------+--------------+--------+----+---------------+---------------------------------------------------------------------------------------+---------------------+
//		|  1 |       1 |        1 |             6 |             6 | Todd     |                   4 | https://www.bybrittanygoldwyn.com/wp-content/uploads/2021/03/Sans-Trifasciata-Snake-Plant-6.jpg | Living room   | My happy boi |      1 |  1 |             1 | https://i.etsystatic.com/18193121/r/il/872f52/2144887556/il_570xN.2144887556_ynis.jpg | 2022-05-24 12:30:00 |
//		+----+---------+----------+---------------+---------------+----------+---------------------+-------------------------------------------------------------------------------------------------+---------------+--------------+--------+----+---------------+---------------------------------------------------------------------------------------+---------------------+
//		1 row in set (0.00 sec)
		
		assertNotNull(uPlant);
		assertNotNull(uPlant.getId());
		assertTrue(uPlant.getPhotos().size()>0);
	}
	
	@Test
	@DisplayName("Entity relational mapping of User Plant to User Photo database")
	void test() {
		
		
		
/* 
		mysql> select * from user join user_plant on user.id = user_plant.user_id where user.id = 1;
		+----+------------+----------+--------------------------------------------------------------+---------+------------+-----------+-----------------+--------------------------------------------------+----------------------------------+------------+----+---------+----------+---------------+---------------+----------+---------------------+-------------------------------------------------------------------------------------------------+---------------+--------------+--------+
		| id | address_id | username | password                                                     | enabled | first_name | last_name | email           | image_url                                        | biography                        | role       | id | user_id | plant_id | height_inches | spread_inches | nickname | pot_diameter_inches | image_url                                                                                       | home_location | description  | active |
		+----+------------+----------+--------------------------------------------------------------+---------+------------+-----------+-----------------+--------------------------------------------------+----------------------------------+------------+----+---------+----------+---------------+---------------+----------+---------------------+-------------------------------------------------------------------------------------------------+---------------+--------------+--------+
		|  1 |          1 | admin    | $2a$10$XR0stvrxAeiPsPSh0hHruesmB0UETSkbRPjK3fRxibq0DvQ/eoQbm |       1 | admin      | admin     | admin@admin.com | https://freesvg.org/img/abstract-user-flat-4.png | Look at me, I am the captian now | ROLE_ADMIN |  1 |       1 |        1 |             6 |             6 | Todd     |                   4 | https://www.bybrittanygoldwyn.com/wp-content/uploads/2021/03/Sans-Trifasciata-Snake-Plant-6.jpg | Living room   | My happy boi |      1 |
		+----+------------+----------+--------------------------------------------------------------+---------+------------+-----------+-----------------+--------------------------------------------------+----------------------------------+------------+----+---------+----------+---------------+---------------+----------+---------------------+-------------------------------------------------------------------------------------------------+---------------+--------------+--------+*/
		
		assertNotNull(uPlant);
		assertNotNull(uPlant.getId());
		assertEquals("admin" ,uPlant.getUser().getUsername());
	}
	
	
	
	@Test
	@DisplayName("Entity relational mapping of User Plant to growth database")
	void test_relational_mapping_userplant_to_growth_in_db() {
		
//		mysql> select * from user_plant u join growth_snapshot g on u.id = g.user_plant_id where u.id=1;
//		+----+---------+----------+---------------+---------------+----------+---------------------+-------------------------------------------------------------------------------------------------+---------------+--------------+--------+-----------+---------------+---------------+---------------+--------------+---------------------+
//		| id | user_id | plant_id | height_inches | spread_inches | nickname | pot_diameter_inches | image_url                                                                                       | home_location | description  | active | growth_id | user_plant_id | height_inches | spread_inches | pot_diameter | create_date         |
//		+----+---------+----------+---------------+---------------+----------+---------------------+-------------------------------------------------------------------------------------------------+---------------+--------------+--------+-----------+---------------+---------------+---------------+--------------+---------------------+
//		|  1 |       1 |        1 |             6 |             6 | Todd     |                   4 | https://www.bybrittanygoldwyn.com/wp-content/uploads/2021/03/Sans-Trifasciata-Snake-Plant-6.jpg | Living room   | My happy boi |      1 |         1 |             1 |             6 |             6 |            5 | 2022-05-24 12:00:00 |
//		+----+---------+----------+---------------+---------------+----------+---------------------+-------------------------------------------------------------------------------------------------+---------------+--------------+--------+-----------+---------------+---------------+---------------+--------------+---------------------+
//		1 row in set (0.00 sec)

		
		assertNotNull(uPlant);
		assertNotNull(uPlant.getId());
	}
	
	
	
}

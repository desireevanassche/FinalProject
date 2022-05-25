package com.skilldistillery.plantdaddyapp.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import static org.junit.jupiter.api.Assertions.assertTrue;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class UserTest {
	
	private static EntityManagerFactory emf;
	private EntityManager em;
	private User user;

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
		user = em.find(User.class, 1);
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
		user = null;
	}

	@Test
	@DisplayName("testing entity user to database")
	void test() {
		
//		mysql> select * from user where id=1;
//		+----+------------+----------+--------------------------------------------------------------+---------+------------+-----------+-----------------+--------------------------------------------------+----------------------------------+------------+
//		| id | address_id | username | password                                                     | enabled | first_name | last_name | email           | image_url                                        | biography                        | role       |
//		+----+------------+----------+--------------------------------------------------------------+---------+------------+-----------+-----------------+--------------------------------------------------+----------------------------------+------------+
//		|  1 |          1 | admin    | $2a$10$XR0stvrxAeiPsPSh0hHruesmB0UETSkbRPjK3fRxibq0DvQ/eoQbm |       1 | admin      | admin     | admin@admin.com | https://freesvg.org/img/abstract-user-flat-4.png | Look at me, I am the captian now | ROLE_ADMIN |
//		+----+------------+----------+--------------------------------------------------------------+---------+------------+-----------+-----------------+--------------------------------------------------+----------------------------------+------------+
//		1 row in set (0.00 sec)
		
		assertNotNull(user);
		assertNotNull(user.getId());
		assertEquals("admin", user.getUsername());
	}
	
	
	@Test
	@DisplayName("testing relational mapping user to post in db")
	void test2() {
		
//		mysql> select * from user join post on user.id = post.user_id where user.id=1;
//		+----+------------+----------+--------------------------------------------------------------+---------+------------+-----------+-----------------+--------------------------------------------------+----------------------------------+------------+----+---------+----------+----------------+-------------------------------------------------+--------------------------------------------------------------------------------------------------------------------------------+---------------------+---------------------+--------+
//		| id | address_id | username | password                                                     | enabled | first_name | last_name | email           | image_url                                        | biography                        | role       | id | user_id | topic_id | title          | content                                         | image_url                                                                                                                      | create_date         | update_date         | active |
//		+----+------------+----------+--------------------------------------------------------------+---------+------------+-----------+-----------------+--------------------------------------------------+----------------------------------+------------+----+---------+----------+----------------+-------------------------------------------------+--------------------------------------------------------------------------------------------------------------------------------+---------------------+---------------------+--------+
//		|  1 |          1 | admin    | $2a$10$XR0stvrxAeiPsPSh0hHruesmB0UETSkbRPjK3fRxibq0DvQ/eoQbm |       1 | admin      | admin     | admin@admin.com | https://freesvg.org/img/abstract-user-flat-4.png | Look at me, I am the captian now | ROLE_ADMIN |  1 |       1 |        1 | Our First Post | This is a post, about plants, posted by a user. | https://www.ikea.com/us/en/images/products/sansevieria-trifasciata-potted-plant-mother-in-laws-tongue__0908898_pe676659_s5.jpg | 2022-05-24 12:25:00 | 2022-05-24 13:25:00 |      1 |
//		+----+------------+----------+--------------------------------------------------------------+---------+------------+-----------+-----------------+--------------------------------------------------+----------------------------------+------------+----+---------+----------+----------------+-------------------------------------------------+--------------------------------------------------------------------------------------------------------------------------------+---------------------+---------------------+--------+
//		1 row in set (0.00 sec)
		 
		assertNotNull(user);
		assertNotNull(user.getId());
		assertTrue( user.getPosts().size()>0);
	}
	
	@Test
	@DisplayName("testing relational mapping user to blog in db")
	void test3() {
		
//		mysql> select * from user join blog on user.id = blog.user_id where user.id =1;
//		+----+------------+----------+--------------------------------------------------------------+---------+------------+-----------+-----------------+--------------------------------------------------+----------------------------------+------------+----+---------+----------------+--------------------------------------------------+---------------------------------------------------------------------------------------------------------------------------------------------+---------------------+---------------------+--------+
//		| id | address_id | username | password                                                     | enabled | first_name | last_name | email           | image_url                                        | biography                        | role       | id | user_id | title          | content                                          | image_url                                                                                                                                   | create_date         | update_date         | active |
//		+----+------------+----------+--------------------------------------------------------------+---------+------------+-----------+-----------------+--------------------------------------------------+----------------------------------+------------+----+---------+----------------+--------------------------------------------------+---------------------------------------------------------------------------------------------------------------------------------------------+---------------------+---------------------+--------+
//		|  1 |          1 | admin    | $2a$10$XR0stvrxAeiPsPSh0hHruesmB0UETSkbRPjK3fRxibq0DvQ/eoQbm |       1 | admin      | admin     | admin@admin.com | https://freesvg.org/img/abstract-user-flat-4.png | Look at me, I am the captian now | ROLE_ADMIN |  1 |       1 | Our First Blog | This is a blog, about plants, posted by an admin | https://hips.hearstapps.com/hmg-prod.s3.amazonaws.com/images/house-plants-1629187361.jpg?crop=0.288xw:0.577xh;0.0465xw,0.205xh&resize=640:* | 2022-05-24 12:00:00 | 2022-05-24 12:00:01 |      1 |
//		+----+------------+----------+--------------------------------------------------------------+---------+------------+-----------+-----------------+--------------------------------------------------+----------------------------------+------------+----+---------+----------------+--------------------------------------------------+---------------------------------------------------------------------------------------------------------------------------------------------+---------------------+---------------------+--------+
//		1 row in set (0.00 sec)
		
		assertNotNull(user);
		assertNotNull(user.getId());
		assertTrue( user.getBlogs().size()>0);
	}
	
	
	@Test
	@DisplayName("testing relational mapping user to plant in db")
	void test4() {
		
/* mysql> select * from user join plant on user.id = plant.created_by_id where user.id = 1;
 * | id | address_id | username | password                                                     | enabled | first_name | last_name | email           | image_url                                        | biography                        | role       | id | created_by_id | common_name | description | image_url                                                      | botanical_name       | care_difficulty | water_cycle | water_type    | light_requirement               | active |
+----+------------+----------+--------------------------------------------------------------+---------+------------+-----------+-----------------+--------------------------------------------------+----------------------------------+------------+----+---------------+-------------+-------------+----------------------------------------------------------------+----------------------+-----------------+-------------+---------------+---------------------------------+--------+
|  1 |          1 | admin    | $2a$10$XR0stvrxAeiPsPSh0hHruesmB0UETSkbRPjK3fRxibq0DvQ/eoQbm |       1 | admin      | admin     | admin@admin.com | https://freesvg.org/img/abstract-user-flat-4.png | Look at me, I am the captian now | ROLE_ADMIN |  1 |             1 | Snake Plant | Tall Boi    | https://h2.commercev3.net/cdn.brecks.com/images/800/76621A.jpg | Dracaena trifasciata | Easy            | 2-8 weeks   | mineral water | 8 - 10 hours of direct sunlight |      1 |
 */  
		assertNotNull(user);
		assertNotNull(user.getId());
		assertTrue(user.getPlants().size()>0); 
	
	}
	

}

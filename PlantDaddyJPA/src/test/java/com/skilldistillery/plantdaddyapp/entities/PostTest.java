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

class PostTest {

	private static EntityManagerFactory emf;
	private EntityManager em;
	private Post post;

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
		post = em.find(Post.class, 1);
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
		post = null;
	}

	/*| id | user_id | topic_id | title          | content                                         | image_url                                                                                                                      | create_date         | update_date         | active |
+----+---------+----------+----------------+-------------------------------------------------+--------------------------------------------------------------------------------------------------------------------------------+---------------------+---------------------+--------+
|  1 |       1 |        1 | Our First Post | This is a post, about plants, posted by a user. | https://www.ikea.com/us/en/images/products/sansevieria-trifasciata-potted-plant-mother-in-laws-tongue__0908898_pe676659_s5.jpg | 2022-05-24 12:25:00 | 2022-05-24 13:25:00 |      1 |
+----+---------+----------+----------------+-------------------------------------------------+------------------------------ */
	@Test
	@DisplayName("Testing post mapping")
	void test() {
		assertNotNull(post);
		assertEquals("Our First Post", post.getTitle());
		assertEquals("This is a post, about plants, posted by a user.", post.getContent());
		

	}
	
	
	@Test
	@DisplayName("Testing relational mapping from post to user")
	void test1() {
		
//		mysql> select * from post join user on post.user_id = user.id where post.user_id = 1;
//		+----+---------+----------+----------------+-------------------------------------------------+--------------------------------------------------------------------------------------------------------------------------------+---------------------+---------------------+--------+----+------------+----------+--------------------------------------------------------------+---------+------------+-----------+-----------------+--------------------------------------------------+----------------------------------+------------+
//		| id | user_id | topic_id | title          | content                                         | image_url                                                                                                                      | create_date         | update_date         | active | id | address_id | username | password                                                     | enabled | first_name | last_name | email           | image_url                                        | biography                        | role       |
//		+----+---------+----------+----------------+-------------------------------------------------+--------------------------------------------------------------------------------------------------------------------------------+---------------------+---------------------+--------+----+------------+----------+--------------------------------------------------------------+---------+------------+-----------+-----------------+--------------------------------------------------+----------------------------------+------------+
//		|  1 |       1 |        1 | Our First Post | This is a post, about plants, posted by a user. | https://www.ikea.com/us/en/images/products/sansevieria-trifasciata-potted-plant-mother-in-laws-tongue__0908898_pe676659_s5.jpg | 2022-05-24 12:25:00 | 2022-05-24 13:25:00 |      1 |  1 |          1 | admin    | $2a$10$XR0stvrxAeiPsPSh0hHruesmB0UETSkbRPjK3fRxibq0DvQ/eoQbm |       1 | admin      | admin     | admin@admin.com | https://freesvg.org/img/abstract-user-flat-4.png | Look at me, I am the captian now | ROLE_ADMIN |
//		+----+---------+----------+----------------+-------------------------------------------------+--------------------------------------------------------------------------------------------------------------------------------+---------------------+---------------------+--------+----+------------+----------+--------------------------------------------------------------+---------+------------+-----------+-----------------+--------------------------------------------------+----------------------------------+------------+
//		1 row in set (0.00 sec)
		
		assertNotNull(post);
		assertNotNull(post.getId());
		assertEquals(1, post.getUser().getId());
		
		
	}

	
	@Test
	@DisplayName("Testing relational mapping from post to topic")
	void test2() {
		
//		mysql> select * from post join topic on post.topic_id = topic.id where post.topic_id = 1;
//		+----+---------+----------+----------------+-------------------------------------------------+--------------------------------------------------------------------------------------------------------------------------------+---------------------+---------------------+--------+----+--------+--------------+----------------------------------------------------------------------------------------------------+
//		| id | user_id | topic_id | title          | content                                         | image_url                                                                                                                      | create_date         | update_date         | active | id | name   | description  | image_url                                                                                          |
//		+----+---------+----------+----------------+-------------------------------------------------+--------------------------------------------------------------------------------------------------------------------------------+---------------------+---------------------+--------+----+--------+--------------+----------------------------------------------------------------------------------------------------+
//		|  1 |       1 |        1 | Our First Post | This is a post, about plants, posted by a user. | https://www.ikea.com/us/en/images/products/sansevieria-trifasciata-potted-plant-mother-in-laws-tongue__0908898_pe676659_s5.jpg | 2022-05-24 12:25:00 | 2022-05-24 13:25:00 |      1 |  1 | Indoor | Indoor Plant | https://media.allure.com/photos/5fdcf516563e46c7d11ee93f/master/pass/AllureBeginnerHouseplants.jpg |
//		+----+---------+----------+----------------+-------------------------------------------------+--------------------------------------------------------------------------------------------------------------------------------+---------------------+---------------------+--------+----+--------+--------------+----------------------------------------------------------------------------------------------------+
//		1 row in set (0.00 sec)
		
		assertNotNull(post);
		assertNotNull(post.getId());
		assertEquals("Indoor", post.getTopic().getName());
		
		
	}
	
	@Test
	@DisplayName("Testing relational mapping from post to comment")
	void test3() {
		
//		mysql> select * from post join comment on post.id = comment.post_id where post.id= 1;
//		+----+---------+----------+----------------+-------------------------------------------------+--------------------------------------------------------------------------------------------------------------------------------+---------------------+---------------------+--------+----+---------+----------------+-------------------+---------------------+--------+
//		| id | user_id | topic_id | title          | content                                         | image_url                                                                                                                      | create_date         | update_date         | active | id | post_id | in_reply_to_id | content           | create_date         | active |
//		+----+---------+----------+----------------+-------------------------------------------------+--------------------------------------------------------------------------------------------------------------------------------+---------------------+---------------------+--------+----+---------+----------------+-------------------+---------------------+--------+
//		|  1 |       1 |        1 | Our First Post | This is a post, about plants, posted by a user. | https://www.ikea.com/us/en/images/products/sansevieria-trifasciata-potted-plant-mother-in-laws-tongue__0908898_pe676659_s5.jpg | 2022-05-24 12:25:00 | 2022-05-24 13:25:00 |      1 |  1 |       1 |              1 | Our first comment | 2022-05-24 12:00:00 |      1 |
//		+----+---------+----------+----------------+-------------------------------------------------+--------------------------------------------------------------------------------------------------------------------------------+---------------------+---------------------+--------+----+---------+----------------+-------------------+---------------------+--------+
//		1 row in set (0.00 sec)
		
		assertNotNull(post);
		assertNotNull(post.getId());
		assertTrue(post.getComments().size()>0);
		
		
	}

}

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

	@Test
	@DisplayName("Testing comment mapping")
	void test() {
		
		/* +----+--------+--------------+----------------------------------------------------------------------------------------------------+
		| id | name   | description  | image_url                                                                                          |
		+----+--------+--------------+----------------------------------------------------------------------------------------------------+
		|  1 | Indoor | Indoor Plant | https://media.allure.com/photos/5fdcf516563e46c7d11ee93f/master/pass/AllureBeginnerHouseplants.jpg |
		+----+--------+--------------+----------------------------------------------------------------------------------------------------+ */ 

		assertNotNull(topic);
		assertEquals("Indoor", topic.getName());
		assertEquals("Indoor Plant", topic.getDescription());
	
	}

	@Test
	@DisplayName("Testing relational mapping from comment post in db")
	void test1() {
		
//		mysql> select * from comment join post on comment.post_id = post.id where comment.id = 1;
//		+----+---------+----------------+-------------------+---------------------+--------+----+---------+----------+----------------+-------------------------------------------------+--------------------------------------------------------------------------------------------------------------------------------+---------------------+---------------------+--------+
//		| id | post_id | in_reply_to_id | content           | create_date         | active | id | user_id | topic_id | title          | content                                         | image_url                                                                                                                      | create_date         | update_date         | active |
//		+----+---------+----------------+-------------------+---------------------+--------+----+---------+----------+----------------+-------------------------------------------------+--------------------------------------------------------------------------------------------------------------------------------+---------------------+---------------------+--------+
//		|  1 |       1 |              1 | Our first comment | 2022-05-24 12:00:00 |      1 |  1 |       1 |        1 | Our First Post | This is a post, about plants, posted by a user. | https://www.ikea.com/us/en/images/products/sansevieria-trifasciata-potted-plant-mother-in-laws-tongue__0908898_pe676659_s5.jpg | 2022-05-24 12:25:00 | 2022-05-24 13:25:00 |      1 |
//		+----+---------+----------------+-------------------+---------------------+--------+----+---------+----------+----------------+-------------------------------------------------+--------------------------------------------------------------------------------------------------------------------------------+---------------------+---------------------+--------+
//		1 row in set (0.00 sec)

		assertNotNull(topic);
		assertNotNull(topic.getId());
		assertTrue(topic.getPost().size()>0);
	
		
	}
	@Test
	@DisplayName("Testing relational mapping from topic to hashtag")
	void test2() {
		assertNotNull(topic.getHashtag());
		assertEquals("Plant", topic.getHashtag().get(0).getName());
	}
	
		
		
}

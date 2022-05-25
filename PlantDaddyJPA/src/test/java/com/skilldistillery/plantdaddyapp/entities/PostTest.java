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


}

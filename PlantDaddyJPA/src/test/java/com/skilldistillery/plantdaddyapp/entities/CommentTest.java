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

class CommentTest {

	private static EntityManagerFactory emf;
	private EntityManager em;
	private Comment comment;

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
		comment = em.find(Comment.class, 1);
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
		comment = null;
	}

	@Test
	@DisplayName("Testing comment mapping")
	void test() {
		/* +----+---------+----------------+-------------------+----------+-------------+--------+------------+
		| id | post_id | in_reply_to_id | content           | in_reply | create_date | active | commentcol |
		+----+---------+----------------+-------------------+----------+-------------+--------+------------+
		|  1 |       1 |              1 | Our first comment | NULL     | NULL        |      1 | NULL       |
		+----+---------+----------------+-------------------+----------+-------------+--------+------------+*/ 

		assertNotNull(comment);
		assertEquals("Our first comment", comment.getContent());
	
		
	}
	
	@Test
	@DisplayName("Testing relational mapping from comment to post in db")
	void test1() {

//		mysql> select * from post join comment on post.id = comment.post_id where post.id=1;
//		+----+---------+----------+----------------+-------------------------------------------------+--------------------------------------------------------------------------------------------------------------------------------+---------------------+---------------------+--------+----+---------+----------------+-------------------+---------------------+--------+
//		| id | user_id | topic_id | title          | content                                         | image_url                                                                                                                      | create_date         | update_date         | active | id | post_id | in_reply_to_id | content           | create_date         | active |
//		+----+---------+----------+----------------+-------------------------------------------------+--------------------------------------------------------------------------------------------------------------------------------+---------------------+---------------------+--------+----+---------+----------------+-------------------+---------------------+--------+
//		|  1 |       1 |        1 | Our First Post | This is a post, about plants, posted by a user. | https://www.ikea.com/us/en/images/products/sansevieria-trifasciata-potted-plant-mother-in-laws-tongue__0908898_pe676659_s5.jpg | 2022-05-24 12:25:00 | 2022-05-24 13:25:00 |      1 |  1 |       1 |              1 | Our first comment | 2022-05-24 12:00:00 |      1 |
//		+----+---------+----------+----------------+-------------------------------------------------+--------------------------------------------------------------------------------------------------------------------------------+---------------------+---------------------+--------+----+---------+----------------+-------------------+---------------------+--------+
//		1 row in set (0.00 sec)
		
		assertNotNull(comment);
		assertNotNull(comment.getId());
		assertEquals("This is a post, about plants, posted by a user.", comment.getPost().getContent());
	
		
	}
	
	
	@Test
	@DisplayName("Testing relational mapping from comment to user in db")
	void test_relational_mapping_comments_to_user_in_db() {
		
//		mysql> select * from post join comment on post.id = comment.post_id where post.id=1;
//		+----+---------+----------+----------------+-------------------------------------------------+--------------------------------------------------------------------------------------------------------------------------------+---------------------+---------------------+--------+----+---------+----------------+-------------------+---------------------+--------+
//		| id | user_id | topic_id | title          | content                                         | image_url                                                                                                                      | create_date         | update_date         | active | id | post_id | in_reply_to_id | content           | create_date         | active |
//		+----+---------+----------+----------------+-------------------------------------------------+--------------------------------------------------------------------------------------------------------------------------------+---------------------+---------------------+--------+----+---------+----------------+-------------------+---------------------+--------+
//		|  1 |       1 |        1 | Our First Post | This is a post, about plants, posted by a user. | https://www.ikea.com/us/en/images/products/sansevieria-trifasciata-potted-plant-mother-in-laws-tongue__0908898_pe676659_s5.jpg | 2022-05-24 12:25:00 | 2022-05-24 13:25:00 |      1 |  1 |       1 |              1 | Our first comment | 2022-05-24 12:00:00 |      1 |
//		+----+---------+----------+----------------+-------------------------------------------------+--------------------------------------------------------------------------------------------------------------------------------+---------------------+---------------------+--------+----+---------+----------------+-------------------+---------------------+--------+
//		1 row in set (0.00 sec)
		
		assertNotNull(comment);
		assertNotNull(comment.getId());
		assertEquals("admin", comment.getUser().getUsername());
		
		
	}

}




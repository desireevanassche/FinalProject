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

class BlogTest {

	private static EntityManagerFactory emf;
	private EntityManager em;
	private Blog blog;

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
		blog = em.find(Blog.class, 1);
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
		blog = null;
	}

	
	@Test
	@DisplayName("Testing blog mapping to db")
	void test() {
//		mysql> select * from blog where id =1;
//		+----+---------+----------------+--------------------------------------------------+---------------------------------------------------------------------------------------------------------------------------------------------+---------------------+---------------------+--------+
//		| id | user_id | title          | content                                          | image_url                                                                                                                                   | create_date         | update_date         | active |
//		+----+---------+----------------+--------------------------------------------------+---------------------------------------------------------------------------------------------------------------------------------------------+---------------------+---------------------+--------+
//		|  1 |       1 | Our First Blog | This is a blog, about plants, posted by an admin | https://hips.hearstapps.com/hmg-prod.s3.amazonaws.com/images/house-plants-1629187361.jpg?crop=0.288xw:0.577xh;0.0465xw,0.205xh&resize=640:* | 2022-05-24 12:00:00 | 2022-05-24 12:00:01 |      1 |
//		+----+---------+----------------+--------------------------------------------------+---------------------------------------------------------------------------------------------------------------------------------------------+---------------------+---------------------+--------+
//		1 row in set (0.00 sec)
		
		
		assertNotNull(blog);
		assertNotNull(blog.getId());
//		assertEquals("Our First Blog", blog.getTitle());
//		assertEquals("This is a blog, about plants, posted by an admin", blog.getContent()); 
	}
	
	
	@Test
	@DisplayName("Testing relational mapping from blog to user db")
	void test2() {

//		mysql> select * from user join blog on user.id = blog.user_id where user.id=1;
//		+----+------------+----------+--------------------------------------------------------------+---------+------------+-----------+-----------------+--------------------------------------------------+----------------------------------+------------+----+---------+----------------+--------------------------------------------------+---------------------------------------------------------------------------------------------------------------------------------------------+---------------------+---------------------+--------+
//		| id | address_id | username | password                                                     | enabled | first_name | last_name | email           | image_url                                        | biography                        | role       | id | user_id | title          | content                                          | image_url                                                                                                                                   | create_date         | update_date         | active |
//		+----+------------+----------+--------------------------------------------------------------+---------+------------+-----------+-----------------+--------------------------------------------------+----------------------------------+------------+----+---------+----------------+--------------------------------------------------+---------------------------------------------------------------------------------------------------------------------------------------------+---------------------+---------------------+--------+
//		|  1 |          1 | admin    | $2a$10$XR0stvrxAeiPsPSh0hHruesmB0UETSkbRPjK3fRxibq0DvQ/eoQbm |       1 | admin      | admin     | admin@admin.com | https://freesvg.org/img/abstract-user-flat-4.png | Look at me, I am the captian now | ROLE_ADMIN |  1 |       1 | Our First Blog | This is a blog, about plants, posted by an admin | https://hips.hearstapps.com/hmg-prod.s3.amazonaws.com/images/house-plants-1629187361.jpg?crop=0.288xw:0.577xh;0.0465xw,0.205xh&resize=640:* | 2022-05-24 12:00:00 | 2022-05-24 12:00:01 |      1 |
//		+----+------------+----------+--------------------------------------------------------------+---------+------------+-----------+-----------------+--------------------------------------------------+----------------------------------+------------+----+---------+----------------+--------------------------------------------------+---------------------------------------------------------------------------------------------------------------------------------------------+---------------------+---------------------+--------+
//		1 row in set (0.00 sec)
		
		assertNotNull(blog);
		assertNotNull(blog.getId());
		assertEquals("admin",blog.getUser().getUsername());
	}
	
	//mysql> SELECT * FROM blog b JOIN hashtag_has_blog hb ON b.id = hb.blog_id JOIN hashtag h ON h.id = hb.hasttag_id;
	@Test
	@DisplayName("Testing for mapping between blog and hashtag")
	void test3() {
		assertNotNull(blog.getHashtags());
		assertEquals("Plant", blog.getHashtags().get(0).getName());
	}


}

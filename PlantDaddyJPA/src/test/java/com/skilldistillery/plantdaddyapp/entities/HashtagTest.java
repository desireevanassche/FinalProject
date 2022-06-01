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

class HashtagTest {
	
	private static EntityManagerFactory emf;
	private EntityManager em;
	private Hashtag hashtag;

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
		hashtag = em.find(Hashtag.class, 1);
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
		hashtag = null;
	}
	
	@Test
	@DisplayName("testing for entity hashtag mapping")
	void test() {
		assertNotNull(hashtag);
		assertEquals("Plant", hashtag.getName());
	}
	
	
//mysql> SELECT * FROM blog b JOIN hashtag_has_blog hb ON b.id = hb.blog_id JOIN hashtag h ON h.id = hb.hasttag_id;
	@Test
	@DisplayName("testing for hashtag to blog mappings")
	void test2() {
		assertNotNull(hashtag.getBlogs());
//		assertEquals("Our First Blog", hashtag.getBlogs().get(0).getTitle());
//		assertEquals("This is a blog, about plants, posted by an admin", hashtag.getBlogs().get(0).getContent());
//		assertEquals("https://hips.hearstapps.com/hmg-prod.s3.amazonaws.com/images/house-plants-1629187361.jpg?crop=0.288xw:0.577xh;0.0465xw,0.205xh&resize=640:*", hashtag.getBlogs().get(0).getImageUrl());
//		assertEquals("2022-05-24", hashtag.getBlogs().get(0).getCreatedAt().toLocalDate().toString());
//		assertEquals("2022-05-24", hashtag.getBlogs().get(0).getUpdatedAt().toLocalDate().toString());
//		//Just for the lolz
//		assertEquals("Plant", hashtag.getBlogs().get(0).getHashtags().get(0).getName());
		
	}
	
	@Test
	@DisplayName("testing for hashtag to topic mappins")
	void test3() {
		assertNotNull(hashtag.getTopics());
		assertEquals("Indoor", hashtag.getTopics().get(0).getName());
		assertEquals("Indoor Plant", hashtag.getTopics().get(0).getDescription());
		assertEquals("https://media.allure.com/photos/5fdcf516563e46c7d11ee93f/master/pass/AllureBeginnerHouseplants.jpg", hashtag.getTopics().get(0).getImageUrl());
		
	}
	@Test
	@DisplayName("testing for hashtag to post")
	void test4() {
		assertNotNull(hashtag.getPosts());
//		assertEquals("Our First Post", hashtag.getPosts().get(0).getTitle());
//		assertEquals("This is a post, about plants, posted by a user.", hashtag.getPosts().get(0).getContent());
//		assertEquals("https://www.ikea.com/us/en/images/products/sansevieria-trifasciata-potted-plant-mother-in-laws-tongue__0908898_pe676659_s5.jpg", hashtag.getPosts().get(0).getImageUrl());
//		assertEquals("2022-05-24", hashtag.getPosts().get(0).getCreatedDate().toLocalDate().toString());
//		assertEquals("2022-05-24", hashtag.getPosts().get(0).getUpdatedDate().toLocalDate().toString());
	}
	
	
	

}

package com.skilldistillery.plantdaddyapp.entities;

import static org.junit.jupiter.api.Assertions.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
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

	
	/* |  1 |       1 | Our First Blog | This is a blog, about plants, posted by an admin | https://hips.hearstapps.com/hmg-prod.s3.amazonaws.com/images/house-plants-1629187361.jpg?crop=0.288xw:0.577xh;0.0465xw,0.205xh&resize=640:* | NULL        | NULL        |      1 |*/
	@Test
	void test() {
		assertNotNull(blog);
		assertEquals("Our First Blog", blog.getTitle());
		assertEquals("This is a blog, about plants, posted by an admin", blog.getContent()); 
	}


}

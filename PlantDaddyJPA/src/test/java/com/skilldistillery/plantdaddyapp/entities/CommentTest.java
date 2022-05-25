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

	/* +----+---------+----------------+-------------------+----------+-------------+--------+------------+
| id | post_id | in_reply_to_id | content           | in_reply | create_date | active | commentcol |
+----+---------+----------------+-------------------+----------+-------------+--------+------------+
|  1 |       1 |              1 | Our first comment | NULL     | NULL        |      1 | NULL       |
+----+---------+----------------+-------------------+----------+-------------+--------+------------+*/ 
	@Test
	void test() {
		assertNotNull(comment);
		assertEquals("Our first comment", comment.getContent());
	
		
	}

}




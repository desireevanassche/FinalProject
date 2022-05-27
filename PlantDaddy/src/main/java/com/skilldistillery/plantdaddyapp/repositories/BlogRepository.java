package com.skilldistillery.plantdaddyapp.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import com.skilldistillery.plantdaddyapp.entities.Blog;


public interface BlogRepository extends JpaRepository<Blog, Integer> {

	
	List<Blog> findByTitleLike(@Param("keyword") String keyword); 
	List<Blog> findByContentLike(@Param("keyword") String keyword); 
}

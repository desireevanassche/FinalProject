package com.skilldistillery.plantdaddyapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.plantdaddyapp.entities.Comment;

public interface CommentRepository extends JpaRepository<Comment, Integer> {

}

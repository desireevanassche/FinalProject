package com.skilldistillery.plantdaddyapp.services;

import java.util.List;

import com.skilldistillery.plantdaddyapp.entities.Blog;
import com.skilldistillery.plantdaddyapp.entities.Plant;

public interface BlogService {

	public List<Blog> allBlogArticles();

	public Blog findById(String username, int id);

	public boolean deleteBlogArticleById(String username, int id);

	public Blog create(String username, Blog blog);

	public Blog update(String username, Blog blog, int id);

	public List<Blog> findProjectWithTitleLike( String keyword);

	public List<Blog> findProjectWithContentLike( String keyword);
	
	Blog deactivate(Blog blog, int id, String username);

}

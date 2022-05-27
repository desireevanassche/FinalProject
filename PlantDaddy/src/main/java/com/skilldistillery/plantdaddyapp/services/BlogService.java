package com.skilldistillery.plantdaddyapp.services;

import java.util.List;

import com.skilldistillery.plantdaddyapp.entities.Blog;

public interface BlogService {

	public List<Blog> allBlogArticles(String username);

	public Blog findById(String username, int id);

	public boolean deleteBlogArticleById(String username, int id);

	public Blog create(String username, Blog blog);

	public Blog update(String username, Blog blog, int id);

	public List<Blog> findProjectWithTitleLike( String keyword);

	public List<Blog> findProjectWithContentLike( String keyword);

}

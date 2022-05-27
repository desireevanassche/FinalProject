package com.skilldistillery.plantdaddyapp.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.plantdaddyapp.entities.Blog;
import com.skilldistillery.plantdaddyapp.entities.User;
import com.skilldistillery.plantdaddyapp.repositories.BlogRepository;
import com.skilldistillery.plantdaddyapp.repositories.UserRepository;

@Service
public class BlogServiceImpl implements BlogService {

	@Autowired
	BlogRepository blogRepo;

	@Autowired
	UserRepository userRepo;


	@Override
	public List<Blog> allBlogArticles() {
		return blogRepo.findAll();
	}

	@Override
	public List<Blog> findProjectWithTitleLike(String keyword) {
		String titleLike = "%" + keyword + "%";
		return blogRepo.findByTitleLike(titleLike);
	}

	@Override
	public List<Blog> findProjectWithContentLike(String keyword) {
		String contentLike = "%" + keyword + "%";
		return blogRepo.findByContentLike(contentLike);
	}

// Any users can see all blog posts
	@Override
	public Blog findById(String username, int id) {
		Optional<Blog> op = blogRepo.findById(id);
		if (op.isPresent()) {
			Blog result = op.get();
			{
				return result;
			}
		}
		return null;
	}

	@Override
	public boolean deleteBlogArticleById(String username, int id) {

		Optional<Blog> op = blogRepo.findById(id);
		if (op.isPresent()) {
			Blog result = op.get();
			if (result.getUser().getUsername().equals(username) && result.getUser().getUsername().equals("admin")) {
				blogRepo.deleteById(id);
				op = blogRepo.findById(id);
				return !op.isPresent();
			}
		}
		return false;
	}

	@Override
	public Blog create(String username, Blog blog) {
		User user = userRepo.findByUsername(username);
		if (user != null && user.getUsername().equals("admin")) {
			blog.getUser().setUsername(username);
		}
		return blogRepo.saveAndFlush(blog);
	}

	@Override
	public Blog update(String username, Blog blog, int id) {
		Optional<Blog> op = blogRepo.findById(id);
		if (op.isPresent()) {
			Blog result = op.get();
			if (result.getUser().getUsername().equals(username) && result.getUser().getUsername().equals("admin")) {
				blog.setId(id);
				blog.getUser().setUsername(result.getUser().getUsername());
				return blogRepo.saveAndFlush(blog);
			}
		}

		return null;
	}

	@Override
	public Blog deactivate(Blog blog, int id, String username) {
		Blog deactivate = blogRepo.findById(id).get();
		if (deactivate != null) {
			deactivate.setActive(false);
		}
		blogRepo.saveAndFlush(deactivate);
		return deactivate;
	}
	
}

package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.exception.EntityNotFoundException;
import com.example.demo.model.Blog;
import com.example.demo.service.BlogService;


@RestController

public class BlogController {

	@Autowired
	BlogService service;

	@GetMapping("/titleCount")
	public List getTotalBlogs() throws EntityNotFoundException {

		if (service.getTotalBlogs().size() == 0) {
			throw new EntityNotFoundException("sorry the entity does not exist in the database");

		}
		return service.getTotalBlogs();
	}

	@GetMapping("/getBlogsByTitle")
	public List<Blog> getBlogsByTitle(@RequestParam("title") String title){
		return service.getBlogsByTitle(title);
	}

	@PostMapping("/saveBlog")
	public Blog saveBlog(@RequestBody Blog blog) {
		return service.saveBlog(blog);
	}

}

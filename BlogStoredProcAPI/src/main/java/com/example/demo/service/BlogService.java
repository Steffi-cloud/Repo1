package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Blog;

/*import com.example.demo.repository.BlogRepository;*/

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
@Service
public class BlogService {
	
	   @Autowired
	   @PersistenceContext
	    EntityManager em;
	   public List getTotalBlogs(){
	       return em.createNamedStoredProcedureQuery("getAllBlogs").getResultList();
	   }
	   public List getBlogsByTitle(String title) {
	       return em.createNamedStoredProcedureQuery("get_total_blogs_by_title_1").setParameter("tblogTitle",title).getResultList();
	   }
	   
	   @Transactional
	   public Blog saveBlog(Blog blog) {
		   em.persist(blog);
		   return blog;
	   }
}

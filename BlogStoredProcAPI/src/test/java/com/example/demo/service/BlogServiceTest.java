package com.example.demo.service;

import com.example.demo.model.Blog;
import jakarta.persistence.EntityManager;
import jakarta.persistence.StoredProcedureQuery;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class BlogServiceTest {

    private BlogService blogService;
    private EntityManager entityManager;
    private StoredProcedureQuery storedProcedureQuery;

    @BeforeEach
    void setUp() {
        entityManager = mock(EntityManager.class);
        storedProcedureQuery = mock(StoredProcedureQuery.class);

        blogService = new BlogService();
        blogService.em = entityManager;
    }

    @Test
    void testGetTotalBlogs() {
        Blog mockBlog = new Blog();
        List<Blog> mockList = Collections.singletonList(mockBlog);

        when(entityManager.createNamedStoredProcedureQuery("getAllBlogs")).thenReturn(storedProcedureQuery);
        when(storedProcedureQuery.getResultList()).thenReturn(mockList);

        List result = blogService.getTotalBlogs();
        assertEquals(1, result.size());
    }

    @Test
    void testSaveBlog() {
        Blog blog = new Blog();
        blog.setBlogTitle("JUnit Test Blog");
        blog.setYearOfPost(2025);

        // No mocking needed for persist (void method)
        blogService.em = entityManager;
        doNothing().when(entityManager).persist(blog);

        Blog savedBlog = blogService.saveBlog(blog);
        assertEquals("JUnit Test Blog", savedBlog.getBlogTitle());
    }
}

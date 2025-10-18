package com.example.demo.controller;

import com.example.demo.model.Blog;
import com.example.demo.service.BlogService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;
import static org.mockito.Mockito.*;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(BlogController.class)
public class BlogControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BlogService blogService;

    @Test
    void testGetBlogsByTitle() throws Exception {
        Blog blog = new Blog();
        blog.setBlogTitle("Spring Boot Stored Proc");
        blog.setYearOfPost(2025);

        when(blogService.getBlogsByTitle("Spring Boot Stored Proc"))
                .thenReturn(Collections.singletonList(blog));

        mockMvc.perform(get("/getBlogsByTitle")
                .param("title", "Spring Boot Stored Proc"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].blogTitle").value("Spring Boot Stored Proc"));
    }

    @Test
    void testSaveBlog() throws Exception {
        Blog blog = new Blog();
        blog.setBlogTitle("New Blog");
        blog.setYearOfPost(2025);

        when(blogService.saveBlog(any())).thenReturn(blog);

        String json = """
                {
                  "blogTitle": "New Blog",
                  "yearOfPost": 2025
                }
                """;

        mockMvc.perform(post("/saveBlog")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.blogTitle").value("New Blog"));
    }
}

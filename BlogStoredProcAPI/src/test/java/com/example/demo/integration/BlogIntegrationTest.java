
package com.example.demo.integration;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import jakarta.transaction.Transactional;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional  // rollback after test
public class BlogIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testSaveBlogAndGetByTitle_and_titleCount() throws Exception {
        String json = """
            {
                "blogTitle": "Integration Blog",
                "yearOfPost": 2025
            }
            """;

        // Save blog first
        mockMvc.perform(post("/saveBlog")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.blogTitle").value("Integration Blog"));

        // Then fetch blogs by title
        mockMvc.perform(get("/getBlogsByTitle")
                .param("title", "Integration Blog"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0][1]").value("Integration Blog"))

                .andExpect(jsonPath("$.length()").value(1));  // check list size == 1
    }
}

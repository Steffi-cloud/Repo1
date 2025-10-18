package com.example.demo.controller;

import com.example.demo.producer.MessageProducer;
import com.example.demo.repository.MessageRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(KafkaRestController.class)
class KafkaRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MessageProducer producer;

    @MockBean
    private MessageRepository messageRepo;

    @Test
    void testSendMsg() throws Exception {
        mockMvc.perform(get("/send")
                .param("msg", "helloKafka"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("sent successfully!")));

        verify(producer).sendMessage("helloKafka");
    }

    @Test
    void testGetAllMessages() throws Exception {
        when(messageRepo.getAllMessages()).thenReturn("[message1, message2]");

        mockMvc.perform(get("/getAll"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("message1")));
    }
}

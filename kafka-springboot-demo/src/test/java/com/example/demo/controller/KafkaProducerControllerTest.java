package com.example.demo.controller;

import com.example.demo.producer.KafkaProducer;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.beans.factory.annotation.Autowired;

import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(KafkaProducerController.class)
public class KafkaProducerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private KafkaProducer kafkaProducer;

    @Test
    public void testPublishEndpoint() throws Exception {
        String testMessage = "Hello Kafka!";
        doNothing().when(kafkaProducer).sendMessage(testMessage);

        mockMvc.perform(get("/api/v1/kafka/publish")
                        .param("message", testMessage))
                .andExpect(status().isOk());
    }
}

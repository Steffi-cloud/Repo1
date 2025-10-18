package com.example.demo.repository;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MessageRepositoryTest {

    @Test
    void testAddAndRetrieve() {
        MessageRepository repo = new MessageRepository();
        repo.addMessage("test123");
        String result = repo.getAllMessages();
        assertTrue(result.contains("test123"));
    }
}

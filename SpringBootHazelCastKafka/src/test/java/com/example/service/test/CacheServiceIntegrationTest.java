package com.example.service.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.SpringBootHazelCastKafkaApplication;
import com.example.demo.service.CacheService;
@SpringBootTest(classes = SpringBootHazelCastKafkaApplication.class)
public class CacheServiceIntegrationTest {
	  @Autowired
	    private CacheService cacheService;

	    @Test
	    void testPutAndGet() {
	        cacheService.put("springKey", "springValue");
	        String value = cacheService.get("springKey");
	        assertEquals("springValue", value);
	    }

	    @Test
	    void testSize() {
	        cacheService.put("a", "1");
	        cacheService.put("b", "2");
	        int size = cacheService.size();
	        assertTrue(size >= 2); // Could be greater if previous keys remain
	    }

	    @Test
	    void testClear() {
	        cacheService.put("clearKey", "clearValue");
	        cacheService.clear();
	        assertEquals(0, cacheService.size());
	    }
}

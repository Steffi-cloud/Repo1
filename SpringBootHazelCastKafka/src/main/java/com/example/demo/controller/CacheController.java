package com.example.demo.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.service.CacheService;
@RestController
@RequestMapping("/cache")
public class CacheController {
	  private final CacheService cacheService;

	    public CacheController(CacheService cacheService) {
	        this.cacheService = cacheService;
	    }

	    @PostMapping
	    public void put(@RequestParam String key, @RequestParam String value) {
	        cacheService.put(key, value);
	    }

	    @GetMapping
	    public String get(@RequestParam String key) {
	        return cacheService.get(key);
	    }

	    @DeleteMapping
	    public void clear() {
	        cacheService.clear();
	    }

	    @GetMapping("/size")
	    public int size() {
	        return cacheService.size();
	    }

}

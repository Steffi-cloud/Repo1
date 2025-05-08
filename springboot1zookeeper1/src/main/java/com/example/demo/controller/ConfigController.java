package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
public class ConfigController {
	 @Value("${foo}")
	    private String foo;

	    @GetMapping("/config")
	    public String getConfig() {
	        return "Value of 'foo' from ZooKeeper: " + foo;
	    }
}

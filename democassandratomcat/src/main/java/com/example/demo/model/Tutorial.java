package com.example.demo.model;

import java.util.UUID;

import org.springframework.data.annotation.Id;
//import org.springframework.data.annotation.Id;
//import org.springframework.data.annotation.Id;
import org.springframework.data.cassandra.core.mapping.Table;
@Table(value="tutorials")
public class Tutorial {
	@Id
	private UUID id=UUID.randomUUID();
	
	private String description;
	private String templatedesc;
	public UUID getId() {
		return id;
	}
	public void setId(UUID id) {
		this.id = id;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getTemplatedesc() {
		return templatedesc;
	}
	public void setTemplatedesc(String templatedesc) {
		this.templatedesc = templatedesc;
	}
	
	

}

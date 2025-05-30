package com.example.demo.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Entity
@Table(name = "employees")
public class Employee {
	   @Id
	    @GeneratedValue(strategy = GenerationType.AUTO)
	    private long id;

	    @Column(name = "name")
	    private String name;

	    @Column(name = "role")
	    private String role;

		/*
		 * public long getId() { return id; }
		 * 
		 * public void setId(long id) { this.id = id; }
		 * 
		 * public String getName() { return name; }
		 * 
		 * public void setName(String name) { this.name = name; }
		 * 
		 * public String getRole() { return role; }
		 * 
		 * public void setRole(String role) { this.role = role; }
		 */
}

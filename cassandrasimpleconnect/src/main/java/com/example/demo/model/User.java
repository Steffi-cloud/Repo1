package com.example.demo.model;

import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

@Table("users")  // Cassandra table name
public class User {

    @PrimaryKey
    private String id;   // Primary key column

    private String name;
    private int age;

    // Constructors
    public User() {}

    public User(String id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    // Getters & setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }
}

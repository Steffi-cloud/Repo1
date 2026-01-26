package com.example.demo.repository;
import com.example.demo.model.User;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.cassandra.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends CassandraRepository<User, String> {
    // You get basic CRUD methods for free

    @Query("SELECT * FROM users WHERE age = ?0 AND name >= ?1 AND name < ?2 ALLOW FILTERING")
    List<User> findByAgeAndNameStartingWith(int age, String start, String end);
}

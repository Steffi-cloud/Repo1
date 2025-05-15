package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.models.User;
/**
 * https://www.javaguides.net/2021/07/spring-data-rest-tutorial.html
 */
public interface UserRepository extends JpaRepository<User, Long> {

}

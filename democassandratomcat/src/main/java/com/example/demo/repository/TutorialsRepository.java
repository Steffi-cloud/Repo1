package com.example.demo.repository;

import java.util.UUID;

import org.springframework.data.cassandra.repository.CassandraRepository;

import com.example.demo.model.Tutorial;

public interface TutorialsRepository extends CassandraRepository<Tutorial, UUID> {

}

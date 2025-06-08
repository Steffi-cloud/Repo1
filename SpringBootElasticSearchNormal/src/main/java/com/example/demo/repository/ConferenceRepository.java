package com.example.demo.repository;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import com.example.demo.model.Conference;

public interface ConferenceRepository extends ElasticsearchRepository<Conference, String>  {

}

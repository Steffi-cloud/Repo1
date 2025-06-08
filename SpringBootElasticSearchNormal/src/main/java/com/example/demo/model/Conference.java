package com.example.demo.model;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.core.geo.GeoPoint;

import lombok.Builder;
import lombok.Data;
@Data
@Builder
@Document(indexName = "conference-index")
public class Conference {
	private @Id String id;
	private String name;
	private @Field String date;
	private GeoPoint location;
	private List<String> keywords;
	
}

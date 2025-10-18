package com.example.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedStoredProcedureQueries;
import jakarta.persistence.NamedStoredProcedureQuery;
import jakarta.persistence.ParameterMode;
import jakarta.persistence.StoredProcedureParameter;
import jakarta.persistence.Table;

@Entity
@Table(name = "blog")
@NamedStoredProcedureQueries({
@NamedStoredProcedureQuery(name = "getAllBlogs",procedureName = "getAllBlogs"),
@NamedStoredProcedureQuery(name = "get_total_blogs_by_title_1", procedureName = "get_total_blogs_by_title_1", parameters = {@StoredProcedureParameter(mode = ParameterMode.IN,name = "tblogTitle",type=String.class)} )})
public class Blog {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer blogId;
	private String blogTitle;
	private Integer yearOfPost;
	public Integer getBlogId() {
		return blogId;
	}
	public void setBlogId(Integer blogId) {
		this.blogId = blogId;
	}
	public String getBlogTitle() {
		return blogTitle;
	}
	public void setBlogTitle(String blogTitle) {
		this.blogTitle = blogTitle;
	}
	public Integer getYearOfPost() {
		return yearOfPost;
	}
	public void setYearOfPost(Integer yearOfPost) {
		this.yearOfPost = yearOfPost;
	}

}

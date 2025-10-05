package com.example.demo.model;

import java.io.Serializable;
import java.util.List;
import java.util.Map;


//@Entity
public class EmployeeEvents implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	//@Id
	private int eventid;
	
	private String keyMap;
	
	private Employee empList;

	public  String getKeyMap() {
		return keyMap;
	}

	public void setKeyMap( String keyMap) {
		this.keyMap = keyMap;
	}

	public Employee getEmpList() {
		return empList;
	}

	public void setEmpList(Employee empList) {
		this.empList = empList;
	}

	
}

package com.yourorg.dto;

import java.util.List;

public class UserActivityHistoryResponse {

	private String userId;
	private List<ActivityEvent> listOfActivityEvents;

	
	public UserActivityHistoryResponse() {
		
	}
	public UserActivityHistoryResponse(String userId, List<ActivityEvent> listOfActivityEvents) {
		super();
		this.userId = userId;
		this.listOfActivityEvents = listOfActivityEvents;
	}
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public List<ActivityEvent> getListOfActivityEvents() {
		return listOfActivityEvents;
	}

	public void setListOfActivityEvents(List<ActivityEvent> listOfActivityEvents) {
		this.listOfActivityEvents = listOfActivityEvents;
	}
}

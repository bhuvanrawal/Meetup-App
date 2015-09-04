package com.snapdeal.meetup.entity;

public class UserEvent {

	private Integer id;
	
	private String userName;
	
	private Integer userId;
	
	private Integer eventId;
	
	private String eventName;
	
	private String currentLocation;
	
	private String remainingTime;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getEventName() {
		return eventName;
	}

	public void setEventName(String eventName) {
		this.eventName = eventName;
	}

	public String getCurrentLocation() {
		return currentLocation;
	}

	public void setCurrentLocation(String currentLocation) {
		this.currentLocation = currentLocation;
	}

	public String getRemainingTime() {
		return remainingTime;
	}

	public void setRemainingTime(String remainingTime) {
		this.remainingTime = remainingTime;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getEventId() {
		return eventId;
	}

	public void setEventId(Integer eventId) {
		this.eventId = eventId;
	}

	@Override
	public String toString() {
		return "UserEvent [id=" + id + ", userName=" + userName + ", userId="
				+ userId + ", eventId=" + eventId + ", eventName=" + eventName
				+ ", currentLocation=" + currentLocation + ", remainingTime="
				+ remainingTime + "]";
	}
}

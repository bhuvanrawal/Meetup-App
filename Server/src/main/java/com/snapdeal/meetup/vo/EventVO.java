package com.snapdeal.meetup.vo;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class EventVO {

	@Id
	private Integer id;
	private String name;
	private String destination;
	private String timeToArrive;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDestination() {
		return destination;
	}
	public void setDestination(String destination) {
		this.destination = destination;
	}
	public String getTimeToArrive() {
		return timeToArrive;
	}
	public void setTimeToArrive(String timeToArrive) {
		this.timeToArrive = timeToArrive;
	}
	@Override
	public String toString() {
		return "EventVO [id=" + id + ", name=" + name + ", destination="
				+ destination + ", timeToArrive=" + timeToArrive + "]";
	}
	
}

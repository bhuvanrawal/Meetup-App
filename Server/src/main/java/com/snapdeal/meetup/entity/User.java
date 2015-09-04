package com.snapdeal.meetup.entity;

public class User {

	private Integer id;
	
	private String name;
	
	private String email_id;

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

	public String getEmail_id() {
		return email_id;
	}

	public void setEmail_id(String email_id) {
		this.email_id = email_id;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", email_id=" + email_id
				+ "]";
	}

}
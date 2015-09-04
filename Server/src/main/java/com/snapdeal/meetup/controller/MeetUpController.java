package com.snapdeal.meetup.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

<<<<<<< Updated upstream
import com.snapdeal.meetup.entity.Event;
import com.snapdeal.meetup.entity.User;
import com.snapdeal.meetup.entity.UserEvent;
import com.snapdeal.meetup.service.MySqlAccess;

@RestController
public class MeetUpController {

	@Autowired
	MySqlAccess service;
	
	
	@RequestMapping(value="/admin/getAllEvents",method = RequestMethod.GET) 
	public List<Event> getAllEvents() {
		return service.getAllEvents();
	}
	
	@RequestMapping(value="/admin/getAllUser",method = RequestMethod.GET) 
	public List<User> getAllUsers() {
		return service.getAllUsers();
	}
	
	@RequestMapping(value="/admin/getAllUserEvents",method = RequestMethod.GET) 
	public List<UserEvent> getAllUserEvents() {
		return service.getAllUserEvent();
	}
	
	@RequestMapping(value="/admin/getAllUsersForEvent/{eventId}",method = RequestMethod.GET) 
	public List<UserEvent> getAllUsersForEvent(@RequestParam ("eventId") Integer eventId) {
		return service.getAllUsersForEvent(eventId);
	}
	
	@RequestMapping(value="/admin/addUserEvents/{userEvent}",method = RequestMethod.GET) 
	public void addUserEvents(@RequestParam ("userEvent") UserEvent userEvent) {
		 service.addUserEvent(userEvent);
	}
=======
@RestController
public class MeetUpController {

//	@Autowired
//	MeetUpService mus;
//
//	@RequestMapping(value="/admin/saveEvent", method = RequestMethod.GET)
//	public String saveEvent() {
//		return mus.meetupServiceExample();
//	}
//
>>>>>>> Stashed changes
	
	@RequestMapping(value="/admin/addUser/{user}",method = RequestMethod.GET) 
	public void addUser(@RequestParam ("user") User user) {
		 service.addUser(user);
	}
	
	@RequestMapping(value="/admin/addEvents/{event}",method = RequestMethod.GET) 
	public void addEvent(@RequestParam ("event") Event event) {
		 service.addEvent(event);
	}
}

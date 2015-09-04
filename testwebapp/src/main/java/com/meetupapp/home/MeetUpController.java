package com.meetupapp.home;

import com.meetupapp.service.MeetUpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MeetUpController {

	@Autowired
	MeetUpService mus;
	
	@RequestMapping(value="/admin/saveEvent", method = RequestMethod.GET)
	public String saveEvent() {
		return mus.meetupServiceExample();
	}
	
	
//	@RequestMapping(value="/admin/getAllUser",method = RequestMethod.GET)
//	public
//	
//	
//	
//	@RequestMapping(value="/admin/getAllEvents",method = RequestMethod.GET)
//	
}

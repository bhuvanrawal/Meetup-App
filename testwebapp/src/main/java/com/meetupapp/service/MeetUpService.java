package com.meetupapp.service;

import com.meetupapp.vo.EventVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.net.UnknownHostException;

@Service
public class MeetUpService {
	

	@Autowired
	@Qualifier("mongoTemplateCatalog")
	MongoOperations mongoOperations;
//	User user = new User("mkyong", "password123");
	
//	@PostConstruct
//	public void init() throws UnknownHostException {
//		/**** Connect to MongoDB ****/
//		// Since 2.10.0, uses MongoClient
//		MongoClient mongo = new MongoClient("localhost", 27017);
//
//		/**** Get database ****/
//		// if database doesn't exists, MongoDB will create it for you
//		DB db = mongo.getDB("meetingAppDB");
//
//	}

	public static void main(String[] args) {
		
		MeetUpService mus = new MeetUpService();
		mus.meetupServiceExample();
	}
	
	public String meetupServiceExample() {
		EventVO eventVO = new EventVO();
		eventVO.setId(1);
		eventVO.setName("getTogether");
		eventVO.setDestination("India Gate");
		eventVO.setTimeToArrive("17:00:00");

		try {
			setEventDocument(eventVO);
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		/******Get *********/

		EventVO event = getEventDocument();
		return event.toString();

	}
	
	public void setEventDocument(EventVO eventVO) throws UnknownHostException {
		mongoOperations.save(eventVO);
	}	
	
	public EventVO getEventDocument() {
		return  mongoOperations.findOne(new Query(Criteria.where("id").is(1)), EventVO.class);

	}

}

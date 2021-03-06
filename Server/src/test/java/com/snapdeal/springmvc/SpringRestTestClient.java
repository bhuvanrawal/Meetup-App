package com.snapdeal.springmvc;

import java.net.URI;
import java.util.LinkedHashMap;
import java.util.List;

import com.snapdeal.springmvc.map.api.autocomplete.LocationDetails;
import com.snapdeal.springmvc.map.api.distanceMatrix.DistanceMatrices;
import com.snapdeal.springmvc.map.api.distanceMatrix.DistanceMatrixRequest;
import com.snapdeal.springmvc.service.GoogleLocationsService;
import org.springframework.web.client.RestTemplate;

import com.snapdeal.springmvc.model.User;

public class SpringRestTestClient {

	public static final String REST_SERVICE_URI = "http://10.20.67.113:8080/meetatlocation";

	private static void getSuggestedLocations() {

		List<LocationDetails> locations = null;
//		GoogleLocationsService s = new GoogleLocationsService();
//		try {
//			locations = s.getLocations("India");
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
		System.out.println("Testing getSuggestedLocations API-----------");
		RestTemplate restTemplate = new RestTemplate();
		locations = restTemplate.getForObject(REST_SERVICE_URI+"/getSuggestedLocations/India", List.class);
		for (LocationDetails l : locations) {
			System.out.println(l.getLocationDes());
		}
	}

	private static void getDistanceMatrix() {

		System.out.println("Testing getSuggestedLocations API-----------");

		DistanceMatrixRequest request = new DistanceMatrixRequest();
		request.getOrigins().add("Kingdom of Dreams, Gurgaon, Haryana, India");
		request.getOrigins().add("Kingdom of Dreams, Gurgaon, Haryana, India");
		request.getDestinations().add("Cox and Kings, Gurgaon, Haryana, India");
		request.getDestinations().add("Kingston Service Center, New Delhi, India");

//		GoogleLocationsService s = new GoogleLocationsService();
//		List<DistanceMatrices> locations = null;
//		try {
//			locations = s.getDistanceMatrices(request.getOrigins(), request.getDestinations());
//		} catch (Exception e) {
//			e.printStackTrace();
//		}

//		RestTemplate restTemplate = new RestTemplate();
//		List<DistanceMatrices> distanceMatrices = restTemplate.getForObject(REST_SERVICE_URI+"/getDistanceMatrices/", request, DistanceMatrixRequest.class);
//		for (DistanceMatrices dis : distanceMatrices) {
//			System.out.println("Distance is: " + dis.getDistance() + "and time taken will be: " + dis.getTime());
//		}
	}


	/* GET */
	@SuppressWarnings("unchecked")
	private static void listAllUsers(){
		System.out.println("Testing listAllUsers API-----------");

		RestTemplate restTemplate = new RestTemplate();
		List<LinkedHashMap<String, Object>> usersMap = restTemplate.getForObject(REST_SERVICE_URI+"/user/", List.class);

		if(usersMap!=null){
			for(LinkedHashMap<String, Object> map : usersMap){
	            System.out.println("User : id="+map.get("id")+", Name="+map.get("name")+", Age="+map.get("age")+", Salary="+map.get("salary"));;
	        }
		}else{
			System.out.println("No user exist----------");
		}
	}

	/* GET */
	private static void getUser(){
		System.out.println("Testing getUser API----------");
		RestTemplate restTemplate = new RestTemplate();
        User user = restTemplate.getForObject(REST_SERVICE_URI+"/user/1", User.class);
        System.out.println(user);
	}
	
	/* POST */
    private static void createUser() {
		System.out.println("Testing create User API----------");
    	RestTemplate restTemplate = new RestTemplate();
        User user = new User(0,"Sarah",51,134);
        URI uri = restTemplate.postForLocation(REST_SERVICE_URI+"/user/", user, User.class);
        System.out.println("Location : "+uri.toASCIIString());
    }

    /* PUT */
    private static void updateUser() {
		System.out.println("Testing update User API----------");
        RestTemplate restTemplate = new RestTemplate();
        User user  = new User(1,"Tomy",33, 70000);
        restTemplate.put(REST_SERVICE_URI+"/user/1", user);
        System.out.println(user);
    }

    /* DELETE */
    private static void deleteUser() {
		System.out.println("Testing delete User API----------");
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.delete(REST_SERVICE_URI+"/user/3");
    }


    /* DELETE */
    private static void deleteAllUsers() {
		System.out.println("Testing all delete Users API----------");
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.delete(REST_SERVICE_URI+"/user/");
    }

    public static void main(String args[]){
		getDistanceMatrix();
//		getSuggestedLocations();
//		listAllUsers();
//		getUser();
//		createUser();
//		listAllUsers();
//		updateUser();
//		listAllUsers();
//		deleteUser();
//		listAllUsers();
//		deleteAllUsers();
//		listAllUsers();
    }
}
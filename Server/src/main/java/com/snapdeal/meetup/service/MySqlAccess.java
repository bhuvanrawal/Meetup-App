package com.snapdeal.meetup.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import com.snapdeal.meetup.entity.Event;
import com.snapdeal.meetup.entity.User;
import com.snapdeal.meetup.entity.UserEvent;

public class MySqlAccess {

	private Connection connect = null;
	private Statement statement = null;
	private PreparedStatement preparedStatement = null;
	private ResultSet resultSet = null;

	public void setConnection() throws ClassNotFoundException, SQLException {
		// This will load the MySQL driver, each DB has its own driver
		Class.forName("com.mysql.jdbc.Driver");
		// Setup the connection with the DB
		connect = DriverManager
				.getConnection("jdbc:mysql://localhost:3306/meeting_app?"
						+ "user=root&password=root");

		// Statements allow to issue SQL queries to the database
		statement = connect.createStatement();

	}
	public void readDataBase() throws Exception {
		try {
			resultSet = statement
					.executeQuery("select * from meeting_app.user");
			writeResultSet(resultSet);

			preparedStatement = connect
					.prepareStatement("insert into  meeting_app.user(name,event_id,current_location) values ('a',1,'c')");

			resultSet = preparedStatement.executeQuery();

			writeResultSet(resultSet);

			preparedStatement = connect.prepareStatement("insert into meeting_app.event(name,destination,time_to_reach) values ('getTogether','x','5')");

			resultSet = preparedStatement.executeQuery();

			writeEventResultSet(resultSet);
		} catch (Exception e) {
			System.out.println("Exception occured :" + e.getStackTrace());
		}
	}

	private void writeResultSet(ResultSet resultSet) throws SQLException {
		// ResultSet is initially before the first data set
		while (resultSet.next()) {
			// It is possible to get the columns via name
			// also possible to get the columns via the column number
			// which starts at 1
			// e.g. resultSet.getSTring(2);
			String userName = resultSet.getString("name");
			String current_location = resultSet.getString("current_location");
			//			String remaining_time_to_reach = resultSet.getString("remaining_time_to_reach");
			String event_name = resultSet.getString("event_name");
			System.out.println("User Name: " + userName);
			System.out.println("Event Name: " + event_name);
			System.out.println("Current Location: " + current_location);
			//			System.out.println("Remaining time to reach destination: " + remaining_time_to_reach);
		}
	}


	private void writeEventResultSet(ResultSet resultSet) throws SQLException {
		// ResultSet is initially before the first data set
		while (resultSet.next()) {
			// It is possible to get the columns via name
			// also possible to get the columns via the column number
			// which starts at 1
			// e.g. resultSet.getSTring(2);
			String event_name = resultSet.getString("name");
			String destination = resultSet.getString("destination");
			String time_to_reach = resultSet.getString("remaining_time_to_reach");
			System.out.println("time to arrive: " + time_to_reach);
		}
	}


	public List<User> getAllUsers() {
		try {
			preparedStatement = connect
					.prepareStatement("select * from meeting_app.user");

			resultSet = preparedStatement.executeQuery();

			List<User> userList = new ArrayList<User>();
			while (resultSet.next()) {
				User user = new User();
				Integer id = resultSet.getInt("id");
				user.setId(id);
				String name = resultSet.getString("name");
				user.setName(name);
				String emailId = resultSet.getString("email_id");
				user.setEmail_id(emailId);
				userList.add(user);
			}
			return userList;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public List<Event> getAllEvents() {
		try {
			preparedStatement = connect
					.prepareStatement("select * from meeting_app.event");

			resultSet = preparedStatement.executeQuery();

			List<Event> eventList = new ArrayList<Event>();
			while (resultSet.next()) {
				Event event = new Event();
				Integer id = resultSet.getInt("id");
				event.setId(id);
				String name = resultSet.getString("name");
				event.setName(name);
				String destination = resultSet.getString("destination");
				event.setDestination(destination);
				String arrivalTime = resultSet.getString("time_to_reach");
				event.setArrival_time(arrivalTime);
				eventList.add(event);
			}
			return eventList;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public List<UserEvent> getAllUserEvent() {
		try {
			preparedStatement = connect
					.prepareStatement("select * from meeting_app.user_event");

			resultSet = preparedStatement.executeQuery();

			List<UserEvent> userEventList = new ArrayList<UserEvent>();
			while (resultSet.next()) {
				UserEvent event = new UserEvent();
				Integer id = resultSet.getInt("id");
				event.setId(id);
				Integer userId = resultSet.getInt("user_id");
				event.setUserId(userId);
				Integer eventId = resultSet.getInt("event_id");
				event.setEventId(eventId);
				String currentLocation = resultSet.getString("current_location");
				event.setCurrentLocation(currentLocation);
				String remainingTime = resultSet.getString("remaining_time");
				event.setRemainingTime(remainingTime);
				userEventList.add(event);
			}
			
			return userEventList;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public List<UserEvent> getAllUsersForEvent(Integer eventId) {
		try {
			preparedStatement = connect.prepareStatement("select * from meeting_app.user_event where event_id = " + eventId );

			resultSet = preparedStatement.executeQuery();
			List<UserEvent> userEventList = new ArrayList<UserEvent>();
			while (resultSet.next()) {
				UserEvent event = new UserEvent();
				Integer id = resultSet.getInt("id");
				event.setId(id);
				Integer userId = resultSet.getInt("user_id");
				event.setUserId(userId);
				Integer e = resultSet.getInt("event_id");
				event.setEventId(e);
				String currentLocation = resultSet.getString("current_location");
				event.setCurrentLocation(currentLocation);
				String remainingTime = resultSet.getString("remaining_time");
				event.setRemainingTime(remainingTime);
				userEventList.add(event);
				return userEventList;
			}
		} catch (Exception e) {
			e.getStackTrace();
		}
		return null;
	}

	public void addUser(User user) {

		try {
			String query = "insert into meeting_app.user(name,email_id) values ('"+user.getName() + "\',\'" + user.getEmail_id()+"')";
			preparedStatement = connect.prepareStatement(query);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void addEvent(Event event) {

		try {
			preparedStatement = connect.prepareStatement("insert into meeting_app.event(name,destination,time_to_reach) values ('"+event.getName() + "\',\'" + event.getDestination()+ "\',\'" + event.getArrival_time()+ "')");
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void addUserEvent(UserEvent userEvent) {
		try {
			preparedStatement = connect.prepareStatement("insert into meeting_app.user_event(user_id,event_id,current_location,remaining_time) values ('"+userEvent.getUserId() + "\',\'" + userEvent.getEventId()+ "\',\'" + userEvent.getCurrentLocation()+ "\',\'" + userEvent.getRemainingTime()+ "')");
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void main(String[] args) throws Exception {

		MySqlAccess dao = new MySqlAccess();

		dao.setConnection();
		//		User user = new User();
		//		user.setName("divya");
		//		user.setEmail_id("divya.narang@snapdeal.com");
		//		dao.addUser(user);
		//		
		//		Event event = new Event();
		//		event.setName("getTogether");
		//		event.setDestination("Gurgaon");
		//		event.setArrival_time("17:00:00");
		//		dao.addEvent(event);

		//		List<User> userList = dao.getAllUsers();
		//		for (User user : userList) {
		//			System.out.println(user.toString());
		//		}
		//		
		//		List<Event> eL = dao.getAllEvents();
		//		for(Event e: eL) {
		//			System.out.println(e.toString());
		//		}

		//		UserEvent ue = new UserEvent();
		//		ue.set
		//		dao.readDataBase();
//
//		UserEvent ue = new UserEvent();
//		ue.setUserId(969);
//		ue.setEventId(968);
//		ue.setCurrentLocation("New Delhi");
//		ue.setRemainingTime(null);
//
//		dao.addUserEvent(ue);
//
//		List<UserEvent> ueList = dao.getAllUserEvent();
//
//		for(UserEvent ue1 : ueList) {
//			System.out.println(ue1.toString());
//		}
	 
		List<UserEvent> ue2 = dao.getAllUsersForEvent(968);
		
		for(UserEvent u : ue2)
			System.out.println(u.toString());

	}

}


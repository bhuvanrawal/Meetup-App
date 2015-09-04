package com.meetupapp.service;

import com.meetupapp.entity.Event;
import com.meetupapp.entity.User;

import javax.annotation.PostConstruct;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySqlAccess {

	private Connection connect = null;
	private Statement statement = null;
	private PreparedStatement preparedStatement = null;
	private ResultSet resultSet = null;
	
	@PostConstruct
	public void init() throws ClassNotFoundException, SQLException {
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


	public static void main(String[] args) throws Exception {
		MySqlAccess dao = new MySqlAccess();
		dao.readDataBase();
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
				return userList;
			}
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
				return eventList;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
				}
		return null;
	}


}


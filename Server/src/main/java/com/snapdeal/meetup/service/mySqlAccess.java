package com.snapdeal.meetup.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class mySqlAccess {

	private Connection connect = null;
	private Statement statement = null;
	private PreparedStatement preparedStatement = null;
	private ResultSet resultSet = null;

	public void readDataBase() throws Exception {
		try {
			// This will load the MySQL driver, each DB has its own driver
			Class.forName("com.mysql.jdbc.Driver");
			// Setup the connection with the DB
			connect = DriverManager
					.getConnection("jdbc:mysql://localhost:3306/meeting_app?"
							+ "user=root&password=root");

			// Statements allow to issue SQL queries to the database
			statement = connect.createStatement();
			// Result set get the result of the SQL query
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
		mySqlAccess dao = new mySqlAccess();
		dao.readDataBase();
	}
}


package database;

import java.sql.*;
import java.util.*;

import guis.StudentFrame;

public class Database {

	
	//for testing purposes
	private static final String CONNECTION_ARG = "jdbc:mysql://localhost:3306/test?user=root&password=04be7a6d";
	//private static final String CONNECTION_ARG = "jdbc:mysql://stusql.dcs.shef.ac.uk/team052?user=team052&password=04be7a6d";
	
	public static void testConnection() {
		System.out.println("\nDrivers loaded as properties:");
		System.out.println(System.getProperty("jdbc.drivers"));
		System.out.println("\nDrivers loaded by DriverManager:");
		Enumeration<Driver> list = DriverManager.getDrivers();
		while (list.hasMoreElements()) {
			System.out.println(list.nextElement());
		}
	}
	
	
	public static void authenticateUsername(String username, String password) {
		
		//TODO
		
		
		
		
	}
	
	
	
	
	public static void viewStudent(String name) {
		//StudentFrame.reset();
		String[] searchName = name.split(" ");
		Connection con = null;
		try {
			con = DriverManager.getConnection(CONNECTION_ARG);
			PreparedStatement stmt = null;
			try {
				stmt = con.prepareStatement("SELECT * FROM Student WHERE forename=? AND surname=?");
				stmt.setString(1, searchName[0]); //forename
				stmt.setString(2, searchName[1]); //surname

				ResultSet res = stmt.executeQuery();
				while(res.next()) {
					String title = res.getString(1);
					String forename = res.getString(2);
					String surname = res.getString(3);
					String email = res.getString(4);

					StudentFrame.appendStudent(title + " " + forename + " " + surname , email,"");
				}
				res.close();
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public static int update() {
		System.out.println("Running update");
		Connection con = null;
		int count = 0;

		try {
			con = DriverManager.getConnection(CONNECTION_ARG);
			Statement stmt = null;
			try {
				stmt = con.createStatement();
				count = stmt.executeUpdate("UPDATE lecturer SET office = 119" + " WHERE name = ‘A Simons’");
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return count;

	}

}

package database;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Enumeration;

import guis.StudentFrame;

public class DatabaseTest {

	  static final String CONNECTION_ARG = "jdbc:mysql://stusql.dcs.shef.ac.uk:3306/team052?user=team052&password=04be7a6d";

		public static void testConnection() {
			System.out.println("\nDrivers loaded as properties:");
			System.out.println(System.getProperty("jdbc.drivers"));
			System.out.println("\nDrivers loaded by DriverManager:");
			Enumeration<Driver> list = DriverManager.getDrivers();
			while (list.hasMoreElements()) {
				System.out.println(list.nextElement());
			}
		}
		
		
		public static void main(String[] args) {
			Connection con = null;
			try {
				con = DriverManager.getConnection(CONNECTION_ARG);
				PreparedStatement stmt = con.prepareStatement("SELECT * FROM Users WHERE username = ?");
				ResultSet set = null;
				stmt.setString(1, "James");
				set = stmt.executeQuery();
				String output = "";
				while (set.next()) {
					output += set.getString(2) +" "+set.getString(3)+" "+set.getString(4);
				}
				System.out.println(output);
				
				}
			catch (SQLException e) {
				e.printStackTrace();
			
			}
			
		}

}

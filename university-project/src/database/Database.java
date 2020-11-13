package database;

import java.sql.*;
import java.util.*;

import guis.StudentFrame;

public class Database {

	// for testing purposes
	static final String CONNECTION_ARG = "jdbc:mysql://localhost:3306/test?user=root&password=04be7a6d";
	// private static final String CONNECTION_ARG =
	// "jdbc:mysql://stusql.dcs.shef.ac.uk/team052?user=team052&password=04be7a6d";

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

		// TODO

	}

	public static String selectStudentInfo(String name) {
		String info = "";
		String[] searchName = name.toLowerCase().split(" ");
		System.out.println(searchName[0]);

		Connection con = null;
		try {
			con = DriverManager.getConnection(CONNECTION_ARG);
			PreparedStatement studentInfo = null;
			PreparedStatement tutorInfo = null;

			ResultSet studentRes = null;
			ResultSet tutorRes = null;
			studentInfo = con.prepareStatement("SELECT * FROM Student WHERE registrationNumber=?");
			
			studentInfo.setString(1, searchName[0]); // id
			studentRes = studentInfo.executeQuery();

			while (studentRes.next()) 
			{
				info += studentRes.getString(1) + " "; // reg number
				info += studentRes.getString(2) + " "; // title
				info += studentRes.getString(3) + " "; // forename
				info += studentRes.getString(4) + " "; // surname
				info += studentRes.getString(5) + " "; // email

				
			}
			

			// find tutor for student
			tutorInfo = con.prepareStatement("SELECT * FROM Tutor WHERE tutorId=?");
			tutorInfo.setString(1, info.split(" ")[0]); // supply tutorId
			tutorRes = tutorInfo.executeQuery();

		
			// point to tutor name
			while (tutorRes.next()) info += tutorRes.getString(2); //tutor name
			

			

			tutorRes.close();
			studentRes.close();
			con.close();

		} catch (SQLException ex) {
			ex.printStackTrace();
		}

		return info;

	}

	public static List<String> studentSearch(String name) {
		// StudentFrame.reset();
		List<String> names = new ArrayList<>();

		String[] searchName = name.toLowerCase().split(" ");
		Connection con = null;
		try {
			con = DriverManager.getConnection(CONNECTION_ARG);
			PreparedStatement forenameStmt = null;
			PreparedStatement surnameStmt = null;
			ResultSet fres = null;
			ResultSet sres = null;
			try {
				if (searchName.length == 1) {
					forenameStmt = con.prepareStatement("SELECT * FROM Student WHERE forename=LOWER(?)");
					forenameStmt.setString(1, searchName[0]); // forename
					fres = forenameStmt.executeQuery();
					while (fres.next()) {
						String result = "";
						result += " " + fres.getString(1); //registration number
						result += " " + fres.getString(2); // title
						result += " " + fres.getString(3); // forename
						result += " " + fres.getString(4); // surname
						result += " " + fres.getString(5); //email
						names.add(result);
					}
					fres.close();
				} else {
					surnameStmt = con
							.prepareStatement("SELECT * FROM Student WHERE forename=LOWER(?) AND surname =LOWER(?)");
					surnameStmt.setString(1, searchName[0]);
					surnameStmt.setString(2, searchName[1]);
					sres = surnameStmt.executeQuery();
					while (sres.next()) {
						String result = "";
						result += " " + sres.getString(1); //registration number
						result += " " + sres.getString(2); // title
						result += " " + sres.getString(3); // forename
						result += " " + sres.getString(4); // surname
						result += " " + sres.getString(5); //email

						names.add(result);
					}
					sres.close();
				}

			} catch (SQLException ex) {
				ex.printStackTrace();
			}
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return names;
	}

}

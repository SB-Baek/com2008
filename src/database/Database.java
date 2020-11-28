package database;

import java.sql.*;
import java.sql.Date;
import java.util.*;

import guis.StudentFrame;

/**
 * 
 * 
 * 
 * @author James Horn
 *
 *         For all information retrieved from database, separate results from
 *         sets using " " delimiter
 *
 */
public class Database {

	// for testing purposes
	//static final String CONNECTION_ARG = "jdbc:mysql://localhost:3306/test?user=root&password=04be7a6d";
	private static final String CONNECTION_ARG = "jdbc:mysql://stusql.dcs.shef.ac.uk:3306/team052?user=team052&password=04be7a6d";

	public static void initConnection() {

		System.out.println("\nDrivers loaded as properties:");
		System.out.println(System.getProperty("jdbc.drivers"));
		System.out.println("\nDrivers loaded by DriverManager:");
		Enumeration<Driver> list = DriverManager.getDrivers();
		while (list.hasMoreElements()) {
			System.out.println(list.nextElement());
		}
		
		Connection con = null;
		try {
			con = DriverManager.getConnection(CONNECTION_ARG);
			try (Statement stmt = con.createStatement()) {
				System.out.println("Using database: " + stmt.execute("USE team052;"));	
			}
		} catch (SQLException e) {
			e.printStackTrace();	
		}
	}
	
	static int getNextStudentId() {
		Connection con = null;
		int id = 0;
		try {
			con = DriverManager.getConnection(CONNECTION_ARG);
			try (Statement stmt = con.createStatement()) {

				ResultSet set = stmt.executeQuery("SELECT MAX(registrationNumber) AS largestId FROM Student;");
				set.next();
				id = set.getInt("largestId") + 1;
			}
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return id;
	}
	static int getStudentIdFromEmail(String email) {
		int id = 0;
		Connection con = null;
		try {
			con = DriverManager.getConnection(CONNECTION_ARG);
			con.setAutoCommit(false);
			
			PreparedStatement stmt = con.prepareStatement("SELECT registrationNumber FROM Student WHERE email = ?;");
			stmt.setString(1, email);
			ResultSet set = stmt.executeQuery();
			while (set.next()) {
				id = set.getInt(1);
			}
			set.close();
			con.close();	
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return id;
		
	}
	static String getModuleInfo(String moduleId) {
		String output = "";
		Connection con = null;
		try {
			con = DriverManager.getConnection(CONNECTION_ARG);
			PreparedStatement stmt = con.prepareStatement("SELECT * FROM Module WHERE moduleId=?");
			ResultSet set = null;
			stmt.setString(1, moduleId);
			set = stmt.executeQuery();
			while (set.next()) {
				output += set.getString(2) + " " // name
						+ set.getString(3) + " " // credits
						+ set.getString(4) + " " // duration
						+ set.getString(5) + " " // code
						+ ((Integer.valueOf(set.getString(6)) == 1) ? "Core" : "Optional") + ":"; // core

			}
			set.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return output;
	}

	public static String getStudentModules(String studentId) {
		String output = "";
		Connection con = null;
		try {
			con = DriverManager.getConnection(CONNECTION_ARG);
			PreparedStatement stmt = con
					.prepareStatement("SELECT * FROM StudentModule WHERE Student_registrationNumber=?");
			ResultSet set = null;
			stmt.setString(1, studentId);
			set = stmt.executeQuery();
			while (set.next()) {
				output += set.getString(1) + " " // initGrade
						+ set.getString(2) + " " // resit
						+ set.getString(3) + " " // passed
						+ Database.getModuleInfo(set.getString(4));
			}
			set.close();
			con.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return output;

	}

	public static String getKey(String username) {
		Connection con = null;
		try {
			con = DriverManager.getConnection(CONNECTION_ARG);
			PreparedStatement stmt = con.prepareStatement("SELECT * FROM Users WHERE username = ?");
			ResultSet set = null;
			stmt.setString(1, username);
			set = stmt.executeQuery();
			String output = "";
			while (set.next()) {
				output += set.getString(2) + " " + set.getString(3) + " " + set.getString(4);
			}
			set.close();
			con.close();
			return output;
		} catch (SQLException e) {
			e.printStackTrace();
			return "";
		}

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

			while (studentRes.next()) {
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
			while (tutorRes.next())
				info += tutorRes.getString(2); // tutor name

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
						result += " " + fres.getString(1); // registration number
						result += " " + fres.getString(2); // title
						result += " " + fres.getString(3); // forename
						result += " " + fres.getString(4); // surname
						result += " " + fres.getString(5); // email
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
						result += " " + sres.getString(1); // registration number
						result += " " + sres.getString(2); // title
						result += " " + sres.getString(3); // forename
						result += " " + sres.getString(4); // surname
						result += " " + sres.getString(5); // email

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

	static String generateEmail(String fore, String sur) {
		String output = "";
		Connection con = null;
		int emailCount = 0;
		try {
			con = DriverManager.getConnection(CONNECTION_ARG);
			PreparedStatement stmt = con
					.prepareStatement("SELECT COUNT(email) AS count FROM Student WHERE forename=? AND surname=?;");
			stmt.setString(1, fore);
			stmt.setString(2, sur);
			ResultSet set = stmt.executeQuery();
			set.next();
			emailCount = Integer.valueOf(set.getString("count")) + 1;

		} catch (SQLException e) {
			e.printStackTrace();
		}

		output += fore.substring(0, 1) + sur + ((emailCount == 0) ? "" : String.valueOf(emailCount));

		return output + "@uni.ac.uk";
	}

	static int degreeId(String name) {
		int output = 0;
		Connection con = null;
		try {

			con = DriverManager.getConnection(CONNECTION_ARG);
			PreparedStatement stmt = con.prepareStatement("SELECT degreeId FROM Degree WHERE name = ?");
			stmt.setString(1, name);
			ResultSet set = stmt.executeQuery();
			while (set.next()) {
				output = set.getInt("degreeId");
			}
		}
		 catch (SQLException e) {
			 e.printStackTrace();
		 }
		
		return output;
	}
	
	public static void addStudent(String title, String forename, String surname, String batchInfo, String degree) {

		System.out.print("Added student: ");
		Connection con = null;
		try {

			con = DriverManager.getConnection(CONNECTION_ARG);
			con.setAutoCommit(false);
			
			PreparedStatement addition = con.prepareStatement(
					"INSERT INTO Student(registrationNumber, title, forename, surname, email, Tutor_tutorId, Department_deptId) VALUES (?,?,?,?,?,?,?);");
			PreparedStatement pdAddition = con.prepareStatement(
					"INSERT INTO Period(grade, start, end, label, creditTotal, Student_registrationNumber, Degree_degreeId) VALUES (?,?,?,?,?,?,?)");
			int studentId = getNextStudentId();
			
			
			//Add Student record
			addition.setInt(1, studentId);
			addition.setString(2, title);
			addition.setString(3, forename);
			addition.setString(4, surname);
			addition.setString(5, generateEmail(forename, surname));
			addition.setInt(6, java.sql.Types.INTEGER);
			addition.setInt(7, java.sql.Types.INTEGER);
			System.out.println(title);
			System.out.println(forename);
			System.out.println(surname);

			
			//Add Period record
			String[] bInfo = batchInfo.split(":");
			for (String x : bInfo) {
				System.out.println(x);
			}
			pdAddition.setFloat(1, Float.valueOf(bInfo[0])); //grade
			pdAddition.setDate(2, Date.valueOf(bInfo[1] + "-" + bInfo[2] + "-" + bInfo[3])); //start date
			pdAddition.setDate(3, Date.valueOf(bInfo[4] + "-" + bInfo[5] + "-" + bInfo[6])); //end date
			switch(bInfo[7]) {
			case "1":
				pdAddition.setString(4, "CER1"); // label
				break;
			case "2":
				pdAddition.setString(4, "DIP1"); // label
				break;
			case "3":
				pdAddition.setString(4, "BAC1"); // label
				break;
			case "4":
				pdAddition.setString(4, "MAS1"); // label
				break;
			case "P":
				pdAddition.setString(4, "PLA"); // label
				break;
			default:
				break;
			}
			
			if (bInfo[7] == "4") { //creditTotal
				pdAddition.setInt(5, 180);
				
			} else {
				pdAddition.setInt(5, 120);
			}
			
			//regNumber
			pdAddition.setInt(6, studentId);
			
			//degreeId
			pdAddition.setInt(7, degreeId(degree));
			
			System.out.println(" " + addition.execute());
			System.out.println(" " + pdAddition.execute());
			
			con.commit();
			con.setAutoCommit(true);
			
			addition.close();
			con.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static String getDegrees() {
		String output = "";
		Connection con = null;
		
		try {
			con = DriverManager.getConnection(CONNECTION_ARG);
			try (Statement stmt = con.createStatement()) {
				ResultSet set = stmt.executeQuery("SELECT * FROM Degree");
				while (set.next()) {
					output += set.getString(1) + " " + set.getString(2) + " " + set.getString(3) + ":"; 
				}	
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return output;
	}

	public static boolean checkStudentExists(String email) {
		boolean check = false;
		Connection con = null;
		try {
			con = DriverManager.getConnection(CONNECTION_ARG);
			PreparedStatement stmt = con.prepareStatement("SELECT * FROM Student WHERE email = ?");
			stmt.setString(1, email);
			ResultSet set = stmt.executeQuery();
			while(set.next()) {
				check = true; break;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return check;
	}

	public static boolean deleteStudent(String email) {
		boolean worked = false;
		Connection con = null;
		try {
			con = DriverManager.getConnection(CONNECTION_ARG);
			con.setAutoCommit(false);
			PreparedStatement stmt2 = con.prepareStatement("DELETE FROM Period WHERE Student_registrationNumber = ?;");
			PreparedStatement stmt3 = con.prepareStatement("DELETE FROM StudentModule WHERE Student_registrationNumber = ?;");

			PreparedStatement stmt = con.prepareStatement("DELETE FROM Student WHERE email = ?;");
			stmt.setString(1, email);
			stmt2.setInt(1, Database.getStudentIdFromEmail(email));
			stmt3.setInt(1, Database.getStudentIdFromEmail(email));
			stmt2.execute();
			stmt3.execute();
			worked = stmt.execute();
			con.commit();
			con.setAutoCommit(true);
		
		
		con.close();	
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return worked;
	}
	
	
	
	
}

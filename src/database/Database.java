package database;

import java.sql.*;
import java.sql.Date;
import java.util.*;

import javax.swing.JTextField;

import guis.BaseFrame;
import security.Authenticate;

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
	private static final String CONNECTION_ARG = "jdbc:mysql://localhost:3306/test?user=root&password=04be7a6d";
	// private static final String CONNECTION_ARG =
	// "jdbc:mysql://stusql.dcs.shef.ac.uk:3306/team052?user=team052&password=04be7a6d";

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
				System.out.println("Using database: " + stmt.execute("USE test;"));
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
						+ ((Integer.valueOf(set.getString(6)) == 1) ? "Core" : "Optional");
				switch (Integer.valueOf(set.getString(6))) {
				case 1:
					output += " CERTIFICATE:";
					break;
				case 2:
					output += " DIPLOMA:";
					break;
				case 3:
					output += " BACHELORS:";
					break;
				case 4:
					output += " MASTERS:";
					break;
				default:
					break;
				}

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
			System.out.println(output);
			return output;
		} catch (SQLException e) {
			e.printStackTrace();
			return "";
		}

	}

	public static String selectStudentInfo(String name) {
		String info = "";
		String[] searchName = name.toLowerCase().split(" ");

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
		} catch (SQLException e) {
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

			// Add Student record
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

			// Add Period record
			String[] bInfo = batchInfo.split(":");
			for (String x : bInfo) {
				System.out.println(x);
			}
			pdAddition.setFloat(1, Float.valueOf(bInfo[0])); // grade
			pdAddition.setDate(2, Date.valueOf(bInfo[1] + "-" + bInfo[2] + "-" + bInfo[3])); // start date
			pdAddition.setDate(3, Date.valueOf(bInfo[4] + "-" + bInfo[5] + "-" + bInfo[6])); // end date
			switch (bInfo[7]) {
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

			if (bInfo[7] == "4") { // creditTotal
				pdAddition.setInt(5, 180);

			} else {
				pdAddition.setInt(5, 120);
			}

			// regNumber
			pdAddition.setInt(6, studentId);

			// degreeId
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
			while (set.next()) {
				check = true;
				break;
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
			PreparedStatement stmt3 = con
					.prepareStatement("DELETE FROM StudentModule WHERE Student_registrationNumber = ?;");

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

	public static int getDegreeId(String degreeName) {
		int id = 0;
		Connection con = null;
		try {
			con = DriverManager.getConnection(CONNECTION_ARG);
			PreparedStatement stmt = con.prepareStatement("SELECT degreeId FROM Degree WHERE name = ?");
			stmt.setString(1, degreeName);
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

	public static List<String> loadOptionals(String degree) {
		List<String> models = new ArrayList<>();
		Connection con = null;
		try {
			con = DriverManager.getConnection(CONNECTION_ARG);
			int id = getDegreeId(degree);

			PreparedStatement stmt = con.prepareStatement("SELECT * FROM DegreeModule WHERE Degree_degreeId = ?");
			stmt.setInt(1, id);
			ResultSet set = stmt.executeQuery();
			while (set.next()) {
				PreparedStatement st = con.prepareStatement("SELECT name FROM Module WHERE moduleId = ? AND core = 0");
				System.out.println("Degree id: " + set.getInt(1));
				st.setInt(1, set.getInt(1));
				ResultSet set2 = st.executeQuery();
				while (set2.next()) {
					System.out.println("Found optional module: " + set2.getString(1));
					models.add(set2.getString(1));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return models;
	}

	public static List<String> removeOptional(int studentId) {
		List<String> models = new ArrayList<>();

		Connection con = null;
		try {
			con = DriverManager.getConnection(CONNECTION_ARG);

			PreparedStatement stmt = con
					.prepareStatement("SELECT Module_moduleId FROM StudentModule WHERE Student_registrationNumber = ?");
			stmt.setInt(1, studentId);
			ResultSet set = stmt.executeQuery();

			while (set.next()) {
				PreparedStatement st = con.prepareStatement("SELECT name FROM Module WHERE moduleId = ? AND core = 0");
				st.setInt(1, set.getInt(1));
				ResultSet set2 = st.executeQuery();
				while (set2.next()) {
					System.out.println("Found student's optional module: " + set2.getString(1));
					models.add(set2.getString(1));
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return models;
	}

	public static boolean addOptionalModule(String selectedModule, int studentId) {
		boolean done = false;
		Connection con = null;
		try {

			con = DriverManager.getConnection(CONNECTION_ARG);
			con.setAutoCommit(false);

			PreparedStatement stmt = con.prepareStatement("SELECT moduleId FROM Module WHERE name = ?");
			PreparedStatement stmt2 = con.prepareStatement(
					"INSERT INTO StudentModule(initialGrade, resitGrade, passed, Module_moduleId, Student_registrationNumber) VALUES (NULL, NULL, NULL, ?, ?);");

			stmt.setString(1, selectedModule);
			ResultSet set = stmt.executeQuery();
			int moduleId = 0;
			while (set.next()) {
				moduleId = set.getInt(1);
			}

			stmt2.setInt(1, moduleId);
			stmt2.setInt(2, studentId);
			done = stmt2.execute();

			con.commit();
			con.setAutoCommit(true);

			set.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return done;

	}

	public static ArrayList<String> getStudents() {
		ArrayList<String> studentInfo = new ArrayList<>();
		Connection con = null;
		try {
			con = DriverManager.getConnection(CONNECTION_ARG);

			PreparedStatement stmt = con.prepareStatement("SELECT * FROM Student;");
			ResultSet set = stmt.executeQuery();
			while (set.next()) {
				// reg number - title - forename
				System.out.println(set.getInt(1));
				System.out.println(set.getString(2));
				System.out.println(set.getString(3));
				System.out.println(set.getString(4));
				System.out.println(set.getString(5));

				String out = set.getInt(1) + " " + set.getString(2) + " " + set.getString(3) +

				// surname - email - tutorId - deptId
						" " + set.getString(4) + " " + set.getString(5) + " " + set.getInt(6) + " " + set.getInt(7);

				studentInfo.add(out);
			}

			set.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return studentInfo;
	}

	public static String getDegreeName(int studentId) {
		String degree = "";
		Connection con = null;
		try {
			con = DriverManager.getConnection(CONNECTION_ARG);
			PreparedStatement stmt2 = con.prepareStatement("SELECT name FROM Degree WHERE degreeId = ?;");
			PreparedStatement stmt = con
					.prepareStatement("SELECT Degree_degreeId FROM Period WHERE Student_registrationNumber = ?;");
			stmt.setInt(1, studentId);

			ResultSet set = stmt.executeQuery();

			while (set.next()) {
				stmt2.setInt(1, set.getInt(1));
				ResultSet set2 = stmt2.executeQuery();
				while (set2.next()) {
					degree = set2.getString(1);
				}
			}

			set.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return degree;
	}

	// student registration verification
	// Period - check date validity, check credit total
	public static String checkCreditTotal(String selectedStudentInfo) {

		String[] info = selectedStudentInfo.split(" ");
		String check = "";
		int foundCreditTotal = 0;
		int creditCount = 0;
		Connection con = null;
		try {

			con = DriverManager.getConnection(CONNECTION_ARG);

			// find credit total from Period table
			PreparedStatement stmt = con
					.prepareStatement("SELECT creditTotal FROM Period WHERE Student_registrationNumber = ?;");
			stmt.setInt(1, Integer.valueOf(info[0]));
			ResultSet set = stmt.executeQuery();
			while (set.next()) {
				foundCreditTotal = set.getInt(1);
			}

			PreparedStatement stmt2 = con.prepareStatement(
					"SELECT Module_moduleId FROM StudentModule WHERE Student_registrationNumber = ?;");
			stmt2.setInt(1, Integer.valueOf(info[0]));
			ResultSet set2 = stmt2.executeQuery();
			while (set2.next()) {
				PreparedStatement stmt3 = con.prepareStatement("SELECT credits FROM Module WHERE moduleId = ?;");
				stmt3.setInt(1, set2.getInt(1));
				ResultSet set3 = stmt3.executeQuery();

				while (set3.next()) {
					creditCount += set3.getInt(1);
				}

				set3.close();
			}
			set2.close();
			set.close();

			con.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (foundCreditTotal != creditCount) {
			check = "Not enough credits: ";
		} else {
			check = "Credit total matched: ";
		}
		return check += " " + String.valueOf(creditCount) + "/" + String.valueOf(foundCreditTotal);
	}

	public static int getStudentStudyLevel(String info) {
		int output = 0;
		Connection con = null;

		try {
			con = DriverManager.getConnection(CONNECTION_ARG);
			PreparedStatement stmt = con
					.prepareStatement("SELECT label FROM Period WHERE Student_registrationNumber = ?;");
			stmt.setString(1, info.split(" ")[0]);
			ResultSet set = stmt.executeQuery();

			while (set.next()) {
				switch (set.getString(1)) {
				case "CER":
					output = 1;
					break;
				case "DIP":
					output = 2;
					break;
				case "BAC":
					output = 3;
					break;
				case "MAS":
					output = 4;
					break;
				case "MASO":
					output = 5;
				}

			}

			set.close();
			con.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return output;
	}

	public static void verifyStudent(String selectedStudentInfo) {

	}

	public static ArrayList<String> getModules(String studentInfo) {

		String[] info = studentInfo.split(" ");
		ArrayList<String> moduleInfo = new ArrayList<>();

		Connection con = null;
		try {

			// get module ids
			con = DriverManager.getConnection(CONNECTION_ARG);
			PreparedStatement stmt = con.prepareStatement(
					"SELECT initialGrade, resitGrade, passed, Module_moduleId FROM StudentModule WHERE Student_registrationNumber = ?");
			stmt.setInt(1, Integer.valueOf(info[0]));
			ResultSet set = stmt.executeQuery();
			while (set.next()) {
				String i = "";
				PreparedStatement stmt2 = con
						.prepareStatement("SELECT name, code, credits, studyLevel FROM Module WHERE moduleId = ?");
				i = String.valueOf(set.getInt(1)) + " " + String.valueOf(set.getInt(2)) + " "
						+ String.valueOf(set.getInt(3));
				stmt2.setInt(1, set.getInt(4));
				ResultSet set2 = stmt2.executeQuery();
				while (set2.next()) {
					moduleInfo.add(String.valueOf(set.getInt(4)) + " " + set2.getString(1) + " " + set2.getString(2)
							+ " " + i + " " + set2.getString(3) + " " + set2.getString(4));
				}
				set2.close();
			}
			set.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return moduleInfo;
	}

	public static void updateModule(int moduleId, String init, String resit, String passed) {
		Connection con = null;
		try {
			con = DriverManager.getConnection(CONNECTION_ARG);
			con.setAutoCommit(false);
			PreparedStatement stmt = con.prepareStatement(
					"UPDATE StudentModule SET initialGrade = ?, resitGrade = ?, passed = ? WHERE Module_moduleId = ?");
			stmt.setInt(1, Integer.valueOf(init));
			stmt.setInt(2, Integer.valueOf(resit));
			stmt.setInt(3, Integer.valueOf(passed));
			stmt.setInt(4, moduleId);

			con.commit();
			con.setAutoCommit(true);
			con.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	// get overall grade for a student
	public static float getGrade(int regId) {
		float output = 0;
		Connection con = null;
		try {
			con = DriverManager.getConnection(CONNECTION_ARG);
			PreparedStatement stmt = con
					.prepareStatement("SELECT grade FROM Period WHERE Student_registrationNumber = ?");
			stmt.setInt(1, regId);

			ResultSet set = stmt.executeQuery();

			while (set.next()) {
				output = set.getFloat(1);
			}

			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return output;
	}

	public static List<String> loadUsers() {
		Connection con = null;
		List<String> m = new ArrayList<>();
		try {
			con = DriverManager.getConnection(CONNECTION_ARG);
			Statement stmt = con.createStatement();
			ResultSet set = stmt.executeQuery("SELECT id, username, permission FROM Users;");
			while (set.next()) {
				m.add(String.valueOf(set.getInt(1)) + " " + set.getString(2) + " " + set.getString(3));
			}
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return m;
	}

	public static boolean addUser(String username, String password, String selectedPermission) {
		boolean done = false;
		Connection con = null;
		try {
			con = DriverManager.getConnection(CONNECTION_ARG);
			con.setAutoCommit(false);
			PreparedStatement stmt = con
					.prepareStatement("INSERT INTO Users(id, username, password, permission, key) VALUES (?, ?, ?, ?)");
			// get next id
			PreparedStatement idStmt = con.prepareStatement("SELECT MAX(id) AS largestId FROM Users");
			ResultSet idSet = idStmt.executeQuery();
			idSet.next();

			int nextId = idSet.getInt(1) + 1;

			stmt.setInt(1, nextId);
			stmt.setString(2, username);
			stmt.setString(3, password);
			stmt.setString(4, Authenticate.createKey(password, username));
			switch (selectedPermission) {
			case "Student":
				stmt.setString(3, "S");
				break;
			case "Teacher":
				stmt.setString(3, "T");
				break;
			case "Registrar":
				stmt.setString(3, "R");
				break;
			case "Administrator":
				stmt.setString(3, "A");
				break;
			default:
				break;
			}

			done = stmt.execute();
			con.commit();
			con.setAutoCommit(true);
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return done;
	}

	public static boolean removeUser(String selectedValue) {
		boolean done = false;
		Connection con = null;
		try {

			con = DriverManager.getConnection(CONNECTION_ARG);

			con.setAutoCommit(false);

			PreparedStatement stmt = con.prepareStatement("DROP FROM Users WHERE id = ?");
			stmt.setString(1, selectedValue.split(" ")[0]);
			done = stmt.execute();

			con.commit();
			con.setAutoCommit(true);

			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return done;
	}

	public static boolean addModule(String name, int credits, String duration, String code, int core, int studyLevel) {
		boolean done = false;
		Connection con = null;
		try {
			con = DriverManager.getConnection(CONNECTION_ARG);
			con.setAutoCommit(false);
			PreparedStatement stmt = con.prepareStatement(
					"INSERT INTO Module(moduleId, name, credits, duration, code, core, studyLevel) VALUES (?, ?, ?, ?, ?, ?, ?)");
			PreparedStatement idStmt = con.prepareStatement("SELECT MAX(moduleId) AS largestId FROM Module");
			ResultSet idSet = idStmt.executeQuery();
			idSet.next();

			int nextId = idSet.getInt(1) + 1;
			stmt.setInt(1, nextId);
			stmt.setString(2, name);
			stmt.setInt(3, credits);
			stmt.setString(4, duration);
			stmt.setString(5, code);
			stmt.setInt(6, core);
			stmt.setInt(7, studyLevel);

			done = stmt.execute();
			con.commit();
			con.setAutoCommit(true);

			con.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return done;
	}

	public static List<String> getModuleElements() {
		List<String> items = new ArrayList<>();
		String selectQuery = "SELECT * FROM Module;";

		Connection con = null;
		try {
			con = DriverManager.getConnection(CONNECTION_ARG);
			Statement stmt = con.createStatement();
			ResultSet set = stmt.executeQuery(selectQuery);

			while (set.next()) {
				items.add(set.getInt(1) + " " + set.getString(2) + " " + set.getInt(3) + " " + set.getString(4) + " "
						+ set.getString(5) + " " + set.getInt(6) + " " + set.getInt(7));
			}

			con.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return items;

	}

	public static boolean removeModule(int moduleId) {
		Connection con = null;
		boolean done = false;
		try {

			con = DriverManager.getConnection(CONNECTION_ARG);
			PreparedStatement stmt = con.prepareStatement("DELETE FROM Module WHERE moduleId = ?;");
			stmt.setInt(1, moduleId);
			done = stmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return done;

	}

	public static void addDegreeModule(int moduleId, String degreeName) {
		Connection con = null;
		try {
			con = DriverManager.getConnection(CONNECTION_ARG);

			con.setAutoCommit(false);
			PreparedStatement stmt = con
					.prepareStatement("INSERT INTO Degree(Module_moduleId, Degree_degreeId) VALUES (?, ?);");
			stmt.setInt(1, moduleId);
			stmt.setInt(2, Database.getDegreeId(degreeName));
			stmt.execute();

			con.commit();
			con.setAutoCommit(true);

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static String getStudentInfo(String user) {
		Connection con = null;
		String output = "";
		try {
			con = DriverManager.getConnection(CONNECTION_ARG);
			PreparedStatement stmt = con.prepareStatement("SELECT * FROM Student WHERE forename = ?");
			PreparedStatement tutorInfo;

			stmt.setString(1, user);
			ResultSet set = stmt.executeQuery();
			ResultSet tutorRes;
			set.next();
			output = String.valueOf(set.getInt(1)) + " " + set.getString(2) + " " + set.getString(3) + " "
					+ set.getString(4) + " " + set.getString(5);

			tutorInfo = con.prepareStatement("SELECT * FROM Tutor WHERE tutorId=?");
			tutorInfo.setInt(1, set.getInt(1)); // supply tutorId
			tutorRes = tutorInfo.executeQuery();

			// point to tutor name
			while (tutorRes.next())
				output += " " + tutorRes.getString(2); // tutor name

			set.close();
			tutorRes.close();
			con.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return output;

	}

	public static boolean removeOptionalModule(String selectedModule) {
		boolean done = false;
		Connection con = null;
		try {
			System.out.println(selectedModule);
			con = DriverManager.getConnection(CONNECTION_ARG);
			con.setAutoCommit(false);
			PreparedStatement selectModuleIdStmt = con.prepareStatement("SELECT moduleId FROM Module WHERE name = ?");
			selectModuleIdStmt.setString(1, selectedModule);
			ResultSet set = selectModuleIdStmt.executeQuery();
			set.next();
			int id = set.getInt(1);
			System.out.println(id);

			PreparedStatement deleteStmt = con.prepareStatement("DELETE FROM StudentModule WHERE Module_moduleId = ?");
			deleteStmt.setInt(1, id);
			done = deleteStmt.execute();

			con.commit();
			con.setAutoCommit(true);
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return done;
	}

	public static boolean addDepartment(JTextField nameField, JTextField abbreviationField) {
		boolean done = false;
		Connection con = null;
		try {
			con = DriverManager.getConnection(CONNECTION_ARG);
			con.setAutoCommit(false);
				
			PreparedStatement idStmt = con.prepareStatement("SELECT MAX(deptId) FROM Department");
			ResultSet idSet = idStmt.executeQuery();
			idSet.next();

			int nextId = idSet.getInt(1) + 1;

			PreparedStatement stmt = con.prepareStatement("INSERT INTO Department(deptId, name, abbreviation) VALUES (?, ?, ?)");
			stmt.setInt(1, nextId);
			stmt.setString(2, nameField.getText());
			stmt.setString(3, abbreviationField.getText());
			done = stmt.execute();
			
			
			con.commit();
			con.setAutoCommit(true);
			
			idSet.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return done;
	}
	
	public static boolean addDegree(JTextField nameField, JTextField codeField) {
		boolean done = false;
		Connection con = null;
		try {
			con = DriverManager.getConnection(CONNECTION_ARG);
			con.setAutoCommit(false);
				
			PreparedStatement idStmt = con.prepareStatement("SELECT MAX(degreeId) FROM Degree");
			ResultSet idSet = idStmt.executeQuery();
			idSet.next();

			int nextId = idSet.getInt(1) + 1;
			
			
			
			
			PreparedStatement stmt = con.prepareStatement("INSERT INTO Degree(degreeId, name, code) VALUES (?, ?, ?)");
			stmt.setInt(1, nextId);
			stmt.setString(2, nameField.getText());
			stmt.setString(3, codeField.getText());
			done = stmt.execute();
			
			
			con.commit();
			con.setAutoCommit(true);
			
			idSet.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return done;
	}
	
	
	public static boolean removeDepartment(int deptId) {
		boolean done = false;
		Connection con = null;
		try {
			con = DriverManager.getConnection(CONNECTION_ARG);
			con.setAutoCommit(false);
			
			PreparedStatement stmt = con.prepareStatement("DELETE FROM Department WHERE deptId = ?;");
			stmt.setInt(1, deptId);
			done = stmt.execute();
			
			
			con.commit();
			con.setAutoCommit(true );
			con.close();
				
		} catch (SQLException e) {
			
		}
		return done;
	}
	

	public static boolean removeDegree(int degreeId) {
		boolean done = false;
		Connection con = null;
		try {
			con = DriverManager.getConnection(CONNECTION_ARG);
			con.setAutoCommit(false);
			
			PreparedStatement stmt = con.prepareStatement("DELETE FROM Degree WHERE degreeId = ?;");
			stmt.setInt(1, degreeId);
			done = stmt.execute();
			
			
			con.commit();
			con.setAutoCommit(true );
			con.close();
				
		} catch (SQLException e) {
			
		}
		return done;
	}

	public static List<String> getDepartments() {
		List<String> elements = new ArrayList<>();
		Connection con = null;
		try {
			con = DriverManager.getConnection(CONNECTION_ARG);
			
			Statement stmt = con.createStatement();
			ResultSet set = stmt.executeQuery("SELECT * FROM Departments");
			while (set.next()) {
				elements.add(set.getInt(1) + " " + set.getString(2) + " " + set.getString(3));
			}
			set.close();
			con.close();
		} catch (SQLException e ) {
			e.printStackTrace();
		}
		return elements;
	}

	public static void linkDepartment(String degreeName, int deptId) {
		Connection con = null;
		try {
			con = DriverManager.getConnection(CONNECTION_ARG);
			con.setAutoCommit(false);
			
			//search
			int degreeId = Database.getDegreeId(degreeName);
			PreparedStatement stmt = con.prepareStatement("INSERT INTO DegreeDepartment(Degree_degreeId, Department_deptId) VALUES (?, ?)");
			stmt.setInt(1, degreeId);
			stmt.setInt(2, deptId);
			stmt.execute();
			
			con.setAutoCommit(true);
			con.close();
		}	
		catch (SQLException e) {
			e.printStackTrace();
		}
			
	} 

}

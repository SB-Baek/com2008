package guis;



import admins.AdminFrame;
import database.Database;
import registrars.RegistrarFrame;
import students.StudentFrame;
import teachers.TeacherFrame;

/**
 * ApplicationWindow.java 03/11/2020
 * 
 * Initialises starting frame, handles which frames should be generated,
 * for specific user permission.
 * 
 */

public class ApplicationWindow  {

	public static void generateFrame(String username, String permission) {
		switch(permission) {
		case "S":
			new StudentFrame(username).setVisible(true);
			break;
		case "A":
			new AdminFrame(username).setVisible(true);
		case "R":
			new RegistrarFrame(username).setVisible(true);
			break;
		case "T":
			new TeacherFrame(username).setVisible(true);
		
		}
	}
	
	public static void main(String args[]) {
		
		Database.initConnection();
		//new LoginFrame().setVisible(true);
		
		
		
	}
}

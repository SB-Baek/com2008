package app;

import admins.AdminFrame;

import database.Database;
import guis.LoginFrame;
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

public class Launch  {

	public static void generateFrame(String username, String permission) {
		switch(permission) {
		case "S":
			new StudentFrame(username).setVisible(true);
			break;
		case "A":
			new AdminFrame(username).setVisible(true);
			break;
		case "R":
			new RegistrarFrame(username).setVisible(true);
			break;
		case "T":
			new TeacherFrame(username).setVisible(true);
			break;
		default:
			break;
		
		}
	}
	
	public static void main(String args[]) {
		
		Database.initConnection();
		new LoginFrame().setVisible(true);	
	}
}

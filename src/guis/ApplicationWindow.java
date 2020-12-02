package guis;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

import admins.AdminFrame;
import database.Database;
import registrars.RegistrarFrame;
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
		case "R":
			new RegistrarFrame(username);
			break;
		case "T":
			new TeacherFrame(username);
		
		}
	}
	
	public static void main(String args[]) {
		
		//new LoginFrame().display();
		Database.initConnection();
		new AdminFrame("J").display();
		
		
		
	}
}

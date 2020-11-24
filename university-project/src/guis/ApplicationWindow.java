package guis;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

import database.Database;

/**
 * @author James Horn
 * 
 * ApplicationWindow.java 03/11/2020
 * 
 * Handle all active frames
 * 
 * INFO
 * Don't forget to init frames
 * 
 * 
 * STARTUP
 * 2) use permissions to generate proper frame options (displaying toolbar or not)
 * 3) Add / remove widgets depending on permissions
 * 4) 
 * 
 * SECURITY
 * Protect against SQL attacks
 * Store login details(SSL / RSA ???)
 * database permissions
 * 
 */

public class ApplicationWindow  {

	public static void generateFrame(String username, String permission) {
		StudentFrame.initStudentFrame(username, permission);
		StudentFrame.display();
	}
	
	public static void main(String args[]) {
		new LoginFrame().display();
		
	}
}

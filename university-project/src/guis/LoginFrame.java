package guis;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;


/**
 * 
 * @author James Horn
 * 
 * LoginFrame.java 03/11/2020
 * 
 * Handle user credentials, leading to main application.
 *
 */

public class LoginFrame extends JFrame {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField usernameField;
	private JPasswordField passwordField;
	
	
	public void authenticateUser() {
		
		
		
		
		
		
		
		
		
	}
	
	
	
	
	public LoginFrame() {
		
		setTitle("University Project");
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Dimension screenResolution = toolkit.getScreenSize();
		
		
		setLocation(screenResolution.width / 2, screenResolution.height/2);
		setResizable(false);
		setBounds(100, 100, 450, 400);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		
		JPanel loginPanel = new JPanel();
		loginPanel.setBounds(100, 116, 42, 200);
		getContentPane().add(loginPanel);
		
		JLabel usernameLabel = new JLabel("Username");
		loginPanel.add(usernameLabel);
		
		usernameField = new JTextField();
		loginPanel.add(usernameField);
		usernameField.setColumns(20);
		
		JLabel passwordLabel = new JLabel("Password");
		loginPanel.add(passwordLabel);
		
		passwordField = new JPasswordField();
		passwordField.setColumns(20);
		loginPanel.add(passwordField);
		
		JButton loginButton = new JButton("Login");
		loginButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		loginPanel.add(loginButton);
		
		JPanel titlePanel = new JPanel();
		titlePanel.setBounds(100, 51, 242, 40);
		getContentPane().add(titlePanel);
		
		JLabel title = new JLabel("Enter Login Credentials");
		title.setFont(new Font("Calibri", Font.BOLD, 24));
		titlePanel.add(title);
		
	}

	public void display() {
		setVisible(true);
	}


	
	
}

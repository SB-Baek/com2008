import java.awt.EventQueue;
import javax.swing.*;
import java.awt.*;
import javax.swing.JFrame;

public class LoginFrameTest {

	
	public static JFrame frame = new JFrame();
	private static JTextField usernameField;
	private static JTextField passwordField;
	
	public static void main(String[] args) {
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 340, 506);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel title = new JLabel("Enter Login Credentials");
		title.setFont(new Font("Tahoma", Font.BOLD, 20));
		title.setBounds(45, 87, 252, 27);
		panel.add(title);
		
		JLabel username = new JLabel("Username:");
		username.setFont(new Font("Tahoma", Font.PLAIN, 12));
		username.setBounds(39, 175, 68, 25);
		panel.add(username);
		
		JLabel password = new JLabel("Password:");
		password.setFont(new Font("Tahoma", Font.PLAIN, 12));
		password.setBounds(41, 209, 115, 14);
		panel.add(password);
		
		usernameField = new JTextField();
		usernameField.setBounds(133, 178, 133, 20);
		panel.add(usernameField);
		usernameField.setColumns(10);
		
		passwordField = new JTextField();
		passwordField.setBounds(133, 209, 133, 20);
		panel.add(passwordField);
		passwordField.setColumns(10);
		
		JButton login = new JButton("Login");
		login.setBounds(112, 250, 89, 23);
		panel.add(login);
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginFrameTest window = new LoginFrameTest();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}

package guis;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import database.Database;
import security.Authenticate;

/**
 * 
 * @author James Horn
 * 
 *         LoginFrame.java 03/11/2020
 * 
 *         Handle user credentials, leading to main application.
 *
 */

public class LoginFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField usernameField;
	private JPasswordField passwordField;
    private static JLabel errMsg = new JLabel("");

	public void authenticateUser() {

	}

	public LoginFrame() {

		setTitle("University Project");
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Dimension screenResolution = toolkit.getScreenSize();

		setLocation(screenResolution.width / 2, screenResolution.height / 2);
		setResizable(false);
		setBounds(100, 50, 450, 400);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);

		getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(50, 0, 340, 506);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel title = new JLabel("Enter Login Credentials");
		title.setFont(new Font("Tahoma", Font.BOLD, 20));
		title.setBounds(45, 87, 252, 27);
		panel.add(title);
		
		JLabel username = new JLabel("Username:");
		username.setFont(new Font("Tahoma", Font.PLAIN, 12));
		username.setBounds(39, 175, 68, 25);
		username.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent arg0) {}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				
				errMsg.setText("");
			}
			@Override
			public void mouseExited(MouseEvent arg0) {}
			@Override
			public void mousePressed(MouseEvent arg0) {}
			@Override
			public void mouseReleased(MouseEvent arg0) {}
		});
		
		
		panel.add(username);
		
		JLabel password = new JLabel("Password:");
		password.setFont(new Font("Tahoma", Font.PLAIN, 12));
		password.setBounds(41, 209, 115, 14);
		password.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent arg0) {}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				
				errMsg.setText("");
			}
			@Override
			public void mouseExited(MouseEvent arg0) {}
			@Override
			public void mousePressed(MouseEvent arg0) {}
			@Override
			public void mouseReleased(MouseEvent arg0) {}
		});
		panel.add(password);
		
		usernameField = new JTextField();
		usernameField.setBounds(133, 178, 133, 20);
		panel.add(usernameField);
		usernameField.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(133, 209, 133, 20);
		panel.add(passwordField);
		passwordField.setColumns(10);
		
		errMsg.setFont(new Font("Tahoma", Font.PLAIN, 12));
		errMsg.setBounds(112, 273, 89, 20);
		panel.add(errMsg);
		
		JButton login = new JButton("Login");
		login.setBounds(112, 250, 89, 23);
		login.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				String[] info= Database.getKey(usernameField.getText()).split(" ");
			

				if (Authenticate.verify(String.valueOf(passwordField.getPassword()), info[3], info[0])) {
					ApplicationWindow.generateFrame(info[0],info[2]);
					dispose();
				} else {
					errMsg.setText("Invalid login");
				}
			
			}
		});
		panel.add(login);
		
		
		

	}

	public void display() {
		setVisible(true);
	}

}

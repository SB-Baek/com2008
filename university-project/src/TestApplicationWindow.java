import java.awt.EventQueue;
import javax.swing.JFrame;
import java.awt.GridLayout;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;

public class TestApplicationWindow {

	private JFrame frmLogin;
	private JTextField usernameField;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TestApplicationWindow window = new TestApplicationWindow();
					window.frmLogin.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public TestApplicationWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmLogin = new JFrame();
		frmLogin.setTitle("University Project");
		frmLogin.setBounds(100, 100, 450, 300);
		frmLogin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmLogin.getContentPane().setLayout(null);
		
		JPanel loginPanel = new JPanel();
		loginPanel.setBounds(100, 116, 242, 93);
		frmLogin.getContentPane().add(loginPanel);
		
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
		
		JPanel loginTitle = new JPanel();
		loginTitle.setBounds(100, 51, 242, 40);
		frmLogin.getContentPane().add(loginTitle);
		
		JLabel lblNewLabel_2 = new JLabel("Enter Login Credentials");
		lblNewLabel_2.setFont(new Font("Calibri", Font.BOLD, 24));
		loginTitle.add(lblNewLabel_2);
	}
}

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JList;
import javax.swing.JButton;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;

public class UserAccountsFrame extends JFrame {

	private JPanel contentPane;
	private JTextField passwordField;
	private JTextField usernameField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UserAccountsFrame frame = new UserAccountsFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public UserAccountsFrame() {
		setTitle("University Project");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 338, 379);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel addPanel = new JPanel();
		addPanel.setBounds(5, 0, 307, 145);
		contentPane.add(addPanel);
		addPanel.setLayout(null);
		
		JLabel addTitle = new JLabel("Add user");
		addTitle.setFont(new Font("Tahoma", Font.PLAIN, 14));
		addTitle.setBounds(10, 11, 149, 14);
		addPanel.add(addTitle);
		
		JLabel username = new JLabel("Username");
		username.setBounds(10, 36, 80, 14);
		addPanel.add(username);
		
		JLabel password = new JLabel("Password");
		password.setBounds(10, 61, 99, 14);
		addPanel.add(password);
		
		JLabel permission = new JLabel("Permission");
		permission.setBounds(10, 89, 99, 14);
		addPanel.add(permission);
		
		JComboBox permissionField = new JComboBox();
		permissionField.setModel(new DefaultComboBoxModel(new String[] {"Student", "Teacher", "Registrar", "Administrator"}));
		permissionField.setBounds(119, 86, 99, 20);
		addPanel.add(permissionField);
		
		passwordField = new JTextField();
		passwordField.setBounds(119, 58, 122, 20);
		addPanel.add(passwordField);
		passwordField.setColumns(10);
		
		usernameField = new JTextField();
		usernameField.setBounds(119, 36, 122, 20);
		addPanel.add(usernameField);
		usernameField.setColumns(10);
		
		JButton addButton = new JButton("Add user");
		addButton.setBounds(10, 114, 89, 23);
		addPanel.add(addButton);
		
		JPanel panel = new JPanel();
		panel.setBounds(5, 161, 307, 168);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel removeTitle = new JLabel("Remove user");
		removeTitle.setBounds(10, 11, 173, 17);
		removeTitle.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panel.add(removeTitle);
		
		JButton removeButton = new JButton("Remove");
		removeButton.setBounds(10, 134, 89, 23);
		panel.add(removeButton);
		
		JScrollPane removeScrollPane = new JScrollPane();
		removeScrollPane.setBounds(10, 29, 287, 105);
		panel.add(removeScrollPane);
		
		JList removalList = new JList();
		removeScrollPane.setViewportView(removalList);
	}
}

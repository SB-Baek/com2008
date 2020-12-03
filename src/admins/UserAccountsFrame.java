package admins;

import javax.swing.JFrame;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import database.Database;

import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JButton;
import javax.swing.JScrollPane;

/**
 * 
 * UserAccountsFrame.java 22/11/2020
 * 
 * Allows admins to add/remove users to the system, assigning usernames, passwords 
 * and permissions to users
 *
 */

public class UserAccountsFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField passwordField;
	private JTextField usernameField;
	private String selectedPermission = "S";
	private JList<String> removalList;
	private	JLabel message = new JLabel("");
	
	
	private String[] loadFields() {
		List<String> list = Database.loadUsers();
		String[] items = new String[list.size()];
		
		for (int a = 0; a < list.size(); a++) {
			items[a] = list.get(a);
		}
		return items;
		
	}
	
	public UserAccountsFrame() {
		setTitle("University Project");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
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
		
		JComboBox<String> permissionField = new JComboBox<>();
		permissionField.setModel(new DefaultComboBoxModel<>(new String[] {"Student", "Teacher", "Registrar", "Administrator"}));
		permissionField.setBounds(119, 86, 99, 20);
		permissionField.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				selectedPermission = (String) permissionField.getSelectedItem();
				
			}

		});
		addPanel.add(permissionField);
		
		passwordField = new JTextField();
		passwordField.setBounds(119, 58, 122, 20);
		addPanel.add(passwordField);
		passwordField.setColumns(10);
		
		usernameField = new JTextField();
		usernameField.setBounds(119, 36, 122, 20);
		addPanel.add(usernameField);
		usernameField.setColumns(10);
		
		message.setBounds(120, 100, 200, 100);
		addPanel.add(message);
		
		JButton addButton = new JButton("Add user");
		addButton.setBounds(10, 114, 89, 23);
		addButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				if (!usernameField.getText().equals("") && !passwordField.getText().equals("") && !(selectedPermission.equals(""))) {
					if (!Database.addUser(usernameField.getText(), passwordField.getText(), selectedPermission)) {
						message.setText("Added user");
						removalList.setListData(loadFields());
						
					} else {
						message.setText("Could not add user");
					}
				} else {
					message.setText("Make sure all fields are filled");
				}
			
				revalidate();
			}
			
		});
	
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
		removeButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (Database.removeUser((String) removalList.getSelectedValue())) {
					message.setText("Removed student");
					removalList.setListData(loadFields());

				} else {
					message.setText("Could not remove student");
				}
				
				revalidate();

			}
		});
		panel.add(removeButton);
		
		JScrollPane removeScrollPane = new JScrollPane();
		removeScrollPane.setBounds(10, 29, 287, 105);
		panel.add(removeScrollPane);
		
		
		removalList.setListData(loadFields());

		removeScrollPane.setViewportView(removalList);
	}
	
}

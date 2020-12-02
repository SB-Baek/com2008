package admins;

import javax.swing.JFrame;

import java.awt.BorderLayout;
import java.awt.EventQueue;

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
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JButton;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;

public class UserAccountsFrame extends JFrame {

	private JPanel contentPane;
	private JTextField passwordField;
	private JTextField usernameField;
	private String selectedPermission;
	private JList removalList;
	private	JLabel message = new JLabel("");
	
	private DefaultListModel<String> model = new DefaultListModel<>();
	
	private void loadFields() {
		List<String> elements = Database.loadUsers();
		for (String e : elements) {
			model.addElement(e);
		}
	}
	
	
	
	
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
		permissionField.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent arg0) {
				selectedPermission = (String) permissionField.getSelectedItem();
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {}
			@Override
			public void mouseExited(MouseEvent arg0) {}
			@Override
			public void mousePressed(MouseEvent arg0) {}
			@Override
			public void mouseReleased(MouseEvent arg0) {}

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
		
		message.setBounds(100, 114, 200, 100);
		addPanel.add(message);
		
		JButton addButton = new JButton("Add user");
		addButton.setBounds(10, 114, 89, 23);
		addButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (Database.addUser(usernameField.getText(), passwordField.getText(), selectedPermission)) {
					message.setText("Added user");
				} else {
					message.setText("Could not add user");
				}
				model = new DefaultListModel<>();
				loadFields();
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
		
		loadFields();
		
		removalList = new JList(model);
		removeScrollPane.setViewportView(removalList);
	}
}

package registrars;

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
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import java.awt.FlowLayout;
import javax.swing.JToolBar;
import java.awt.Color;
import javax.swing.border.BevelBorder;
import javax.swing.border.EtchedBorder;

import database.Database;

import java.awt.CardLayout;
import javax.swing.ScrollPaneConstants;
import java.awt.BorderLayout;
import javax.swing.JList;
import java.awt.List;

public class ARStudent extends JFrame {

	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_3;
	private JTextField textField_2;
	
	public ARStudent() {
	
		setResizable(false);
		setTitle("University Project");
		setBounds(100, 100, 230, 294);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel addForm = new JPanel();
		getContentPane().add(addForm);
		addForm.setLayout(null);
		
		JLabel forename = new JLabel("Forename: ");
		forename.setBounds(22, 68, 124, 14);
		addForm.add(forename);
		
		JLabel surname = new JLabel("Surname: ");
		surname.setBounds(22, 93, 102, 14);
		addForm.add(surname);
		
		JLabel title = new JLabel("Title: ");
		title.setBounds(22, 43, 46, 14);

		addForm.add(title);
		
		JLabel addTitle = new JLabel("Add new student");
		addTitle.setFont(new Font("Tahoma", Font.PLAIN, 14));
		addTitle.setBounds(10, 11, 176, 14);
		addForm.add(addTitle);
		
		textField = new JTextField();
		textField.setBounds(90, 43, 116, 14);
		addForm.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(90, 68, 116, 14);
		addForm.add(textField_1);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(90, 93, 116, 14);
		addForm.add(textField_3);
		
		JButton add = new JButton("Add");
		add.setBounds(22, 118, 89, 23);
		add.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Database.addStudent(textField.getText(), textField_1.getText(), textField_3.getText());
			}			
		});
		addForm.add(add);
		
		JLabel deleteTitle = new JLabel("Delete a student");
		deleteTitle.setFont(new Font("Tahoma", Font.PLAIN, 14));
		deleteTitle.setBounds(10, 152, 124, 14);
		addForm.add(deleteTitle);
		
		JLabel emailLabel = new JLabel("Email: ");
		emailLabel.setBounds(22, 177, 46, 14);
		addForm.add(emailLabel);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(90, 177, 116, 14);
		addForm.add(textField_2);
		
		JButton deleteButton = new JButton("Delete");
		deleteButton.setBounds(22, 202, 89, 23);
		addForm.add(deleteButton);
		
		JLabel addStatus = new JLabel("New label");
		addStatus.setBounds(121, 122, 85, 31);
		addForm.add(addStatus);
		
		JLabel addStatus_1 = new JLabel("New label");
		addStatus_1.setBounds(121, 202, 85, 31);
		addForm.add(addStatus_1);
	}
	
	public void display() {
		setVisible(true);
	}
}

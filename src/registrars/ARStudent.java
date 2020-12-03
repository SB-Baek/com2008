package registrars;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JComboBox;

import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.JScrollPane;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import database.Database;

/**
 * 
 * ARStudent.java 23/11/2020
 *
 * Allows registrars to add/remove students from the system
 *
 */

public class ARStudent extends JFrame {

	private static final long serialVersionUID = 1L;
	JTextField textField;
	 JTextField textField_1;
	 JTextField textField_3;
	 JTextField textField_2;
	 JTextField textField_4;
	 JComboBox<String> day1;
	 JComboBox<String> month1;
	 JComboBox<String> year1;
	 JComboBox<String> day2;
	 JComboBox<String> month2;
	 JComboBox<String> year2;
	 JComboBox<String> studyLevel;
	 JList<String> degreeList;
	 String initDay= "01", initMonth = "01", initYear = "2020";
	 String endDay = "01", endMonth = "01", endYear = "2020";
	 String sl = "1";
	 String degreeName = "BSC Computer Science";
	 JLabel addStatus;
	 JLabel deleteStatus;
	 String email= "";
	 
	 Map<Integer, String> idname = new HashMap<>();
	 
	 DefaultListModel<String> model = new DefaultListModel<>();
	 void updateDegreeList(String results) {
		 System.out.println(results);
		 
		 String[] res = results.split(":");
		 for (String x : res) {
			 String[] info = x.split(" ");
			 idname.put(Integer.valueOf(info[0]), info[1]);
			 model.addElement(info[1] + " " + info[2]);
		 }
		 revalidate();
	 }
	 
	public ARStudent() {
	
		setResizable(false);
		setTitle("University Project");
		setBounds(100, 100, 434, 419);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
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
		
		JLabel addTitle = new JLabel("Register a new student");
		addTitle.setFont(new Font("Tahoma", Font.PLAIN, 14));
		addTitle.setBounds(10, 11, 400, 14);
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
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(90, 325, 116, 14);
		addForm.add(textField_2);
		
		textField_4 = new JTextField();
		textField_4.setColumns(10);
		textField_4.setBounds(90, 117, 116, 14);
		addForm.add(textField_4);
		
		day1 = new JComboBox<>();
		day1.setModel(new DefaultComboBoxModel<>(new String[] {"2020", "2021", "2022", "2023", "2024", "2025", "2026", "2027"}));
		day1.setBounds(90, 139, 56, 20);
		day1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				initYear = (String) day1.getSelectedItem();
			}
		});
		addForm.add(day1);
		
		month1 = new JComboBox<>();
		month1.setModel(new DefaultComboBoxModel<>(new String[] {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12"}));
		month1.setBounds(147, 139, 39, 20);
		month1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				initMonth = (String) month1.getSelectedItem();
			}
		});
		addForm.add(month1);
		
		year1 = new JComboBox<>();
		year1.setModel(new DefaultComboBoxModel<>(new String[] {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"}));
		year1.setBounds(189, 139, 39, 20);
		year1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				initDay = (String) year1.getSelectedItem();
			}
		});
		addForm.add(year1);
		
		day2 = new JComboBox<>();
		day2.setModel(new DefaultComboBoxModel<>(new String[] {"2020", "2021", "2022", "2023", "2024", "2025", "2026", "2027"}));
		day2.setBounds(90, 170, 56, 20);
		day2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				endYear = (String) day2.getSelectedItem();
			}
		});
		addForm.add(day2);
		
		month2 = new JComboBox<>();
		month2.setModel(new DefaultComboBoxModel<>(new String[] {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12"}));
		month2.setBounds(147, 170, 39, 20);
		month2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				endMonth = (String) month2.getSelectedItem();
			}
		});
		addForm.add(month2);
		
		year2 = new JComboBox<>();
		year2.setModel(new DefaultComboBoxModel<>(new String[] {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"}));
		year2.setBounds(189, 170, 39, 20);
		year2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				endDay = (String) year2.getSelectedItem();
			}
		});
		addForm.add(year2);
		
		studyLevel = new JComboBox<>();
		studyLevel.setModel(new DefaultComboBoxModel<>(new String[] {"1", "2", "3", "4", "p"}));
		studyLevel.setBounds(107, 201, 39, 20);
		studyLevel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				sl = (String) studyLevel.getSelectedItem();
			}
		});
		addForm.add(studyLevel);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(238, 59, 180, 148);
		addForm.add(scrollPane);
		
		degreeList = new JList<>(model);
		degreeList.addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent arg0) {
				degreeName = (String) degreeList.getSelectedValue();
			}
			
		});
		scrollPane.setViewportView(degreeList);
		
		updateDegreeList(Database.getDegrees());
		
		JButton add = new JButton("Add");
		add.setBounds(22, 238, 89, 23);
		add.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				 
				String batch = textField_4.getText() + ":" + initYear + ":" + initMonth + ":" + initDay + ":" + endYear + ":" + endMonth + ":" + endDay + ":" + sl;
				if (batch.length() > 24) {
					// length of batch excluding length of degree, make sure that batch info is valid 
					//check batch length for valid student addition
					Database.addStudent(textField.getText(), textField_1.getText(), textField_3.getText(), batch, degreeName);
					addStatus.setText("Adding new student");

				} else {
					System.out.println(batch);
					addStatus.setText("Make sure all fields are filled !");
				}
				revalidate();
			}			
		});
		addForm.add(add);
		
		JLabel deleteTitle = new JLabel("Delete a student");
		deleteTitle.setFont(new Font("Tahoma", Font.PLAIN, 14));
		deleteTitle.setBounds(10, 300, 124, 14);
		addForm.add(deleteTitle);
		
		JLabel emailLabel = new JLabel("Email: ");
		emailLabel.setBounds(22, 325, 46, 14);
		addForm.add(emailLabel);
		
		
		JButton deleteButton = new JButton("Delete");
		deleteButton.setBounds(22, 356, 89, 23);
		deleteButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				email = textField_2.getText();
				if (Database.checkStudentExists(email)) {
					System.out.println("Deleted student: " + Database.deleteStudent(email));
					deleteStatus.setText("Successfully deleted student");
				} else {
					deleteStatus.setText("Could not find email");

				}
				revalidate();
			}			
		});
		addForm.add(deleteButton);
		
		addStatus = new JLabel("");
		addStatus.setBounds(132, 238, 251, 23);
		addForm.add(addStatus);
		
		deleteStatus = new JLabel("");
		deleteStatus.setBounds(132, 352, 85, 31);
		addForm.add(deleteStatus);
		
		JLabel grade = new JLabel("Initial Grade: ");
		grade.setBounds(22, 118, 102, 14);
		addForm.add(grade);
		
		JLabel lblStartDate = new JLabel("Start:");
		lblStartDate.setBounds(22, 145, 57, 14);
		addForm.add(lblStartDate);
		
		JLabel lblEndDate = new JLabel("End:");
		lblEndDate.setBounds(22, 170, 57, 14);
		addForm.add(lblEndDate);
		
		JLabel year = new JLabel("Study Level:");
		year.setBounds(22, 204, 77, 14);
		addForm.add(year);
		
		JLabel degree = new JLabel("Degree:");
		degree.setBounds(238, 43, 46, 14);
		addForm.add(degree);
	
	}
	
}

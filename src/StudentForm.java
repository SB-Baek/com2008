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
import java.awt.CardLayout;
import javax.swing.ScrollPaneConstants;
import java.awt.BorderLayout;
import javax.swing.JList;
import java.awt.List;
import javax.swing.JScrollBar;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class StudentForm {

	private JFrame frmLogin;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_3;
	private JTextField textField_2;
	private JTextField textField_4;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StudentForm window = new StudentForm();
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
	public StudentForm() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmLogin = new JFrame();
		frmLogin.setResizable(false);
		frmLogin.setTitle("University Project");
		frmLogin.setBounds(100, 100, 434, 419);
		frmLogin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel addForm = new JPanel();
		frmLogin.getContentPane().add(addForm);
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
		add.setBounds(22, 238, 89, 23);
		addForm.add(add);
		
		JLabel deleteTitle = new JLabel("Delete a student");
		deleteTitle.setFont(new Font("Tahoma", Font.PLAIN, 14));
		deleteTitle.setBounds(10, 300, 124, 14);
		addForm.add(deleteTitle);
		
		JLabel emailLabel = new JLabel("Email: ");
		emailLabel.setBounds(10, 325, 46, 14);
		addForm.add(emailLabel);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(47, 325, 116, 14);
		addForm.add(textField_2);
		
		JButton deleteButton = new JButton("Delete");
		deleteButton.setBounds(10, 356, 89, 23);
		addForm.add(deleteButton);
		
		JLabel addStatus = new JLabel("New label");
		addStatus.setBounds(132, 238, 251, 23);
		addForm.add(addStatus);
		
		JLabel addStatus_1 = new JLabel("New label");
		addStatus_1.setBounds(132, 352, 85, 31);
		addForm.add(addStatus_1);
		
		JLabel grade = new JLabel("Initial Grade: ");
		grade.setBounds(22, 118, 102, 14);
		addForm.add(grade);
		
		textField_4 = new JTextField();
		textField_4.setColumns(10);
		textField_4.setBounds(90, 117, 116, 14);
		addForm.add(textField_4);
		
		JLabel lblStartDate = new JLabel("Start Date: ");
		lblStartDate.setBounds(22, 145, 57, 14);
		addForm.add(lblStartDate);
		
		JComboBox day1 = new JComboBox();
		day1.setModel(new DefaultComboBoxModel(new String[] {"2020", "2021", "2022", "2023", "2024", "2025", "2026", "2027"}));
		day1.setBounds(90, 139, 56, 20);
		addForm.add(day1);
		
		JComboBox month1 = new JComboBox();
		month1.setModel(new DefaultComboBoxModel(new String[] {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12"}));
		month1.setBounds(147, 139, 39, 20);
		addForm.add(month1);
		
		JComboBox day1_1 = new JComboBox();
		day1_1.setModel(new DefaultComboBoxModel(new String[] {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"}));
		day1_1.setBounds(189, 139, 39, 20);
		addForm.add(day1_1);
		
		JLabel lblEndDate = new JLabel("End Date:");
		lblEndDate.setBounds(22, 170, 57, 14);
		addForm.add(lblEndDate);
		
		JComboBox day1_2 = new JComboBox();
		day1_2.setModel(new DefaultComboBoxModel(new String[] {"2020", "2021", "2022", "2023", "2024", "2025", "2026", "2027"}));
		day1_2.setBounds(90, 170, 56, 20);
		addForm.add(day1_2);
		
		JComboBox month1_1 = new JComboBox();
		month1_1.setModel(new DefaultComboBoxModel(new String[] {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12"}));
		month1_1.setBounds(147, 170, 39, 20);
		addForm.add(month1_1);
		
		JComboBox day1_1_1 = new JComboBox();
		day1_1_1.setModel(new DefaultComboBoxModel(new String[] {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"}));
		day1_1_1.setBounds(189, 170, 39, 20);
		addForm.add(day1_1_1);
		
		JLabel year = new JLabel("Study Level:");
		year.setBounds(22, 204, 77, 14);
		addForm.add(year);
		
		JComboBox month1_1_1 = new JComboBox();
		month1_1_1.setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3", "4", "p"}));
		month1_1_1.setBounds(107, 201, 39, 20);
		addForm.add(month1_1_1);
		
		JLabel degree = new JLabel("Degree:");
		degree.setBounds(238, 43, 46, 14);
		addForm.add(degree);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(238, 59, 180, 148);
		addForm.add(scrollPane);
		
		JList degreeList = new JList();
		scrollPane.setViewportView(degreeList);
	}
}

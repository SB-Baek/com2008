package registrars;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.Font;

import javax.swing.DefaultListModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import database.Database;
import guis.BaseFrame;

import javax.swing.JList;


/**
 * RegistrarFrame.java 28/11/2020
 * 
 * Generates JFrame for registrars. Registrars can add/remove students,
 * add/remove optional modules for students, check whether a student
 * registration is valid and check whether a student is properly accredited.
 * 
 */

public class RegistrarFrame extends BaseFrame {

	private static final long serialVersionUID = 1L;
	private JTextField emailField;
	private JLabel reg = new JLabel("");

	public void loadStudents(DefaultListModel<String> model) {
		ArrayList<String> studentInfo = Database.getStudents();
		for (String x : studentInfo) {
			model.addElement(x);
		}
	}

	public RegistrarFrame(String username) {

		initBaseFrame(username);

		JPanel viewing = new JPanel();
		viewing.setBounds(10, 88, 378, 400);
		getContentPane().add(viewing);
		viewing.setLayout(null);

		info.setBounds(10, 350, 200, 30);
		viewing.add(info);
		
		reg.setBounds(10, 150, 200, 30);

		JButton addRemove = new JButton("Add/Remove Student");
		addRemove.setBounds(10, 250, 162, 23);
		addRemove.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new ARStudent().setVisible(true);
			}
		});

		viewing.add(addRemove);

		JLabel lblNewLabel_5 = new JLabel("Student");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_5.setBounds(10, 11, 162, 23);
		viewing.add(lblNewLabel_5);
		
		emailField = new JTextField();
		emailField.setBounds(57, 105, 152, 20);
		viewing.add(emailField);
		emailField.setColumns(10);
		

		JButton btnNewButton = new JButton("Check Registration");
		btnNewButton.setBounds(10, 133, 200, 23);
		btnNewButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (!selectedStudentInfo.equals("")) { 
					reg.setText(Database.verifyStudent(Integer.valueOf(selectedStudentInfo.split(" ")[0])));
				} else {
					reg.setText("Please select a student");
				}
				revalidate();
			}

		});

		viewing.add(btnNewButton);

		JLabel creditLabel = new JLabel();
		creditLabel.setBounds(10, 230, 250, 23);
		creditLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		viewing.add(creditLabel);

		JButton creditTotal = new JButton("Check Credit Total");
		creditTotal.setBounds(10, 200, 275, 23);
		creditTotal.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (!selectedStudentInfo.equals("")) {
					info.setText("");
					creditLabel.setText(Database.checkCreditTotal(selectedStudentInfo));
				} else {
					info.setText("Please select a student");
				}
				revalidate();
			}

		});

		viewing.add(creditTotal);

		JLabel lblNewLabel_6 = new JLabel("Registration");
		lblNewLabel_6.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_6.setBounds(10, 80, 152, 14);
		viewing.add(lblNewLabel_6);

		JLabel lblNewLabel_7 = new JLabel("Email:");
		lblNewLabel_7.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_7.setBounds(10, 108, 108, 14);
		viewing.add(lblNewLabel_7);

		JButton btnAddremoveOptionalModules = new JButton("Add/Remove Optional Modules");
		btnAddremoveOptionalModules.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (!selectedStudentInfo.equals("")) {
					info.setText("");
					new OptionalModule(selectedStudentInfo).setVisible(true);
				} else {

					info.setText("Please select a student");
				}
				revalidate();
			}
		});
		btnAddremoveOptionalModules.setBounds(10, 300, 275, 23);
		viewing.add(btnAddremoveOptionalModules);

		JLabel optionalInfo = new JLabel("Select a student from the list.");
		optionalInfo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		optionalInfo.setBounds(10, 165, 200, 40);
		viewing.add(optionalInfo);

		DefaultListModel<String> listModel = new DefaultListModel<>();
		JList<String> list = new JList<>(listModel);
		list.setBounds(398, 60, 600, 407);

		loadStudents(listModel);
		getContentPane().add(list);

		list.addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent arg0) {

				selectedStudentInfo = list.getSelectedValue();
				displayStudentInfo(Database.selectStudentInfo(list.getSelectedValue()));
				revalidate();
			}
		});
	}
}
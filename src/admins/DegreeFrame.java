
package admins;

import javax.swing.JFrame;


import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import database.Database;

import javax.swing.JLabel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JList;

	public class DegreeFrame extends JFrame{

		private JPanel contentPane;
		private JTextField nameField;
		private JTextField codeField;
		private JLabel info = new JLabel("");
		private String removeSelected = "";
		private String linkSelected = "";
		private DefaultListModel<String> removeModel = new DefaultListModel<>();
		private DefaultListModel<String> linkModel = new DefaultListModel<>();


	public void loadRemoveModel() {
		for (String x : Database.getDegrees().split(":")) {
			removeModel.addElement(x);
		}
	}
	
	public void loadLinkModel() {
		for (String x : Database.getDepartments() ) {
			linkModel.addElement(x);
		}
	
	}
	
	
	public DegreeFrame() {
		
		loadRemoveModel();
		loadLinkModel();
	
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 710, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(5, 5, 643, 245);
		contentPane.add(panel);
		panel.setLayout(null);
		
		
		info.setBounds(10, 100, 300, 100);
		panel.add(info);
		
		JLabel title = new JLabel("Add a new Degree");
		title.setFont(new Font("Tahoma", Font.PLAIN, 14));
		title.setBounds(10, 11, 200, 17);
		panel.add(title);
		
		JButton addButton = new JButton("Add");
		addButton.setBounds(10, 96, 89, 23);
		addButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (!(nameField.equals("") || codeField.equals("")))  {
					if (Database.addDepartment(nameField, codeField)) {
						info.setText("Added degree");
					} else {
						info.setText("Could not add degree");
					}
				} else {
					info.setText("Make sure all fields are filled");
				}
				revalidate();
			}
		});
		panel.add(addButton);
		
		nameField = new JTextField();
		nameField.setBounds(96, 36, 97, 20);
		panel.add(nameField);
		nameField.setColumns(10);
		
		JLabel name = new JLabel("name:");
		name.setBounds(20, 39, 46, 14);
		panel.add(name);
		
		codeField = new JTextField();
		codeField.setBounds(96, 61, 97, 20);
		panel.add(codeField);
		codeField.setColumns(10);
		
		JLabel abbreviation = new JLabel("code:");
		abbreviation.setBounds(20, 64, 104, 14);
		panel.add(abbreviation);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(236, 30, 178, 163);
		panel.add(scrollPane);
		
		JList degreeList = new JList(removeModel);
		degreeList.addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent arg0) {
				removeSelected = (String) degreeList.getSelectedValue();
			}
			
		});
		scrollPane.setViewportView(degreeList);
		
		JLabel lblNewLabel_1 = new JLabel("Remove Degree");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1.setBounds(236, 13, 178, 14);
		panel.add(lblNewLabel_1);
		
		JButton btnNewButton = new JButton("Remove");
		btnNewButton.setBounds(236, 204, 89, 23);
		btnNewButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (!(removeSelected.equals("")))  {
					Database.removeDepartment(Integer.valueOf(removeSelected.split(" ")[0]));
					info.setText("Removed degree");

					
				} else {
					info.setText("Please select a degree");
					}
					revalidate();
				}
			});
		panel.add(btnNewButton);
		
		JScrollPane linkFrame = new JScrollPane();
		linkFrame.setBounds(434, 30, 199, 163);
		panel.add(linkFrame);
		
		JList departmentList = new JList(linkModel);
		departmentList.addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent arg0) {
				linkSelected = (String) departmentList.getSelectedValue();
			}
			
		});
		linkFrame.setViewportView(departmentList);
		
		JLabel linkTitle = new JLabel("Link to department");
		linkTitle.setFont(new Font("Tahoma", Font.PLAIN, 14));
		linkTitle.setBounds(434, 12, 178, 14);
		panel.add(linkTitle);
		
		JButton btnLink = new JButton("Link");
		btnLink.setBounds(434, 204, 89, 23);
		btnLink.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (!(linkSelected.equals("")))  {
					Database.linkDepartment(nameField.getText(), Integer.valueOf(linkSelected.split(" ")[0]));
					info.setText("Linked department");
				} else {
					info.setText("Please select a department");
					}
					revalidate();
				}
			});
		panel.add(btnLink);
	}
}

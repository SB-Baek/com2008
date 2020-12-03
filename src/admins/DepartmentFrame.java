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
import java.util.List;

import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JList;

/**
 * 
 * DepartmentFrame.java  28/11/2020
 * Allows admins to add/remove departments from the system
 *
 */

public class DepartmentFrame extends JFrame{

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField nameField;
	private JTextField abbreviationField;
	private JLabel info = new JLabel("");
	private String selectedField = "";

	
	
	private String[] loadListModel() {
		List<String> list = Database.getDepartments();
		String[] items = new String[list.size()];
		
		for (int a = 0; a < list.size(); a++) {
			items[a] = list.get(a);
		}
		return items;
		
	}
	
	public static void main(String[] args) {
		new DepartmentFrame().setVisible(true);
	}
	
	public DepartmentFrame() {
		loadListModel();
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(5, 5, 424, 245);
		contentPane.add(panel);
		panel.setLayout(null);

		JList<String> departmentList = new JList<>();
		departmentList.setListData(loadListModel());
		
		JLabel lblNewLabel = new JLabel("Add a new department");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel.setBounds(10, 11, 200, 17);
		panel.add(lblNewLabel);
		
		info.setBounds(10, 100, 300, 100);
		panel.add(info);

		JButton addButton = new JButton("Add");
		addButton.setBounds(10, 96, 89, 23);
		addButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (!(nameField.equals("") || abbreviationField.equals("")))  {
					Database.addDepartment(nameField, abbreviationField);
					
				} else {
					info.setText("Make sure all fields are filled");
				}
				departmentList.setListData(loadListModel());
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

		abbreviationField = new JTextField();
		abbreviationField.setBounds(96, 61, 97, 20);
		panel.add(abbreviationField);
		abbreviationField.setColumns(10);

		JLabel abbreviation = new JLabel("abbreviation:");
		abbreviation.setBounds(20, 64, 104, 14);
		panel.add(abbreviation);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(236, 30, 178, 163);
		panel.add(scrollPane);

		
		departmentList.addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent arg0) {
				selectedField = (String) departmentList.getSelectedValue();
			}
			
		});
		scrollPane.setViewportView(departmentList);
		
		JButton remove = new JButton("Remove");
		remove.setBounds(236, 204, 89, 23);
		remove.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (!(selectedField.equals("")))  {
					Database.removeDepartment(Integer.valueOf(selectedField.split(" ")[0]));
					info.setText("Removed department");
					departmentList.setListData(loadListModel());

					
				} else {
					info.setText("Could not remove department");
				}
				revalidate();
			}
		});
		panel.add(remove);
	}
}

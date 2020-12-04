
package admins;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import database.Database;

import javax.swing.JLabel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.List;

import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JList;

/**
 * DegreeFrame.java 28/11/2020
 * 
 * Admins can add/remove degrees from this frame and link new degrees to a department
 * 
 */

public class DegreeFrame extends JFrame{

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField nameField; //input name of degree
	private JTextField codeField; //input code for degree
	private JLabel info = new JLabel(""); //display information about processes
	private String removeSelected = ""; //gets info from remove list
	private String linkSelected = ""; //gets info from link list

	private int leadChecked = 0;

	public String[] loadRemoveModel() {
		return Database.getDegrees().split(":");
	}
	
	public String[] loadLinkModel() {
		List<String> list = Database.getDepartments();
		String[] items = new String[list.size()];
		
		for (int a = 0; a < list.size(); a++) {
			items[a] = list.get(a);
		}
		return items;
	}

	public static void main(String[] args) {
		new DegreeFrame().setVisible(true);
	}
	
	public DegreeFrame() {
		
		//load list model items
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
		
		info.setBounds(10, 220, 300, 20);
		panel.add(info);
		
		JLabel title = new JLabel("Add a new Degree");
		title.setFont(new Font("Tahoma", Font.PLAIN, 14));
		title.setBounds(10, 11, 200, 17);
		panel.add(title);
		
		JLabel infoTitle = new JLabel("link to degree before adding");
		infoTitle.setFont(new Font("Tahoma", Font.PLAIN, 14));
		infoTitle.setBounds(10, 160, 200, 17);
		panel.add(infoTitle);
		
		JList<String> departmentList = new JList<>();
		departmentList.setListData(loadLinkModel());
		
		JList<String> degreeList = new JList<>();
		degreeList.setListData(loadRemoveModel());
		
		JButton addButton = new JButton("Add");
		addButton.setBounds(10, 190, 89, 23);
		addButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (!(nameField.equals("") || codeField.equals("")))  {
					if (!Database.addDegree(nameField, codeField)) {
						info.setText("Added degree");
						degreeList.setListData(loadRemoveModel());

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
					Database.removeDegree(Integer.valueOf(removeSelected.split(" ")[0]));
					info.setText("Removed degree");
					degreeList.setListData(loadRemoveModel());

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
		
		JCheckBox lead = new JCheckBox("lead");
		lead.setBounds(540, 204, 89, 23);
		lead.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent arg0) {
				leadChecked = arg0.getStateChange();
			}
			
		});
		panel.add(lead);
		
		JButton btnLink = new JButton("Link");
		btnLink.setBounds(434, 204, 89, 23);
		btnLink.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (!(linkSelected.equals("") && !(nameField.getText().equals(""))))  {
					Database.linkDepartment(nameField.getText(), Integer.valueOf(linkSelected.split(" ")[0]), leadChecked);
					info.setText("Linked department");
					departmentList.setListData(loadLinkModel());

				} else {
					info.setText("Please fill all fields");
					}
					revalidate();
				}
			});
		panel.add(btnLink);
	}
	
	
}

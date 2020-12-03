
package registrars;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import database.Database;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JList;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.JTextArea;
import javax.swing.DefaultListModel;
import javax.swing.DropMode;
import java.awt.Color;

public class OptionalModule extends JFrame {

	private JPanel contentPane;
	private String selectedModule = "";
	private String selectedModuleR = "";
	private DefaultListModel<String> addModel = new DefaultListModel<>();
	private DefaultListModel<String> removeModel = new DefaultListModel<>();

	public void loadAddModels(String degree) {

		for (String x : Database.loadOptionals(degree)) {
			System.out.println("Loaded optional: " + x);
			addModel.addElement(x);
		}
	}

	public void loadRemoveModels(int studentId) {
	
		if (!(Database.removeOptional(studentId) == null)) {
			for (String x : Database.removeOptional(studentId)) {
				removeModel.addElement(x);
			}
		}
	}

	public OptionalModule(String studentInfo) {
		
		String[] info = studentInfo.split(" ");
		loadAddModels(Database.getDegreeName(Integer.valueOf(info[0])));
		loadRemoveModels(Integer.valueOf(info[0]));
		
		
		setTitle("University Project");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 448, 565);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel add = new JPanel();
		add.setBounds(10, 40, 412, 150);
		contentPane.add(add);
		
		JPanel delete = new JPanel();
		delete.setBounds(10, 290, 412, 150);
		contentPane.add(delete);
		delete.setLayout(null);
		
		JList removeModules = new JList(removeModel);
		
		removeModules.setBounds(195, 11, 207, 128);
		removeModules.addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent arg0) {
				selectedModuleR = (String) removeModules.getSelectedValue();
			}
			
		});
		delete.add(removeModules);
		
		JTextArea txtrDeleteModulesBy = new JTextArea();
		txtrDeleteModulesBy.setText("Delete modules by selecting from the list and click remove");
		txtrDeleteModulesBy.setRows(2);
		txtrDeleteModulesBy.setLineWrap(true);
		txtrDeleteModulesBy.setFont(new Font("Tahoma", Font.PLAIN, 11));
		txtrDeleteModulesBy.setEditable(false);
		txtrDeleteModulesBy.setBackground(Color.WHITE);
		txtrDeleteModulesBy.setBounds(10, 7, 168, 98);
		delete.add(txtrDeleteModulesBy);
		
		JButton btnRemove = new JButton("Remove");
		btnRemove.setBounds(10, 116, 89, 23);
		btnRemove.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (!(selectedModuleR.equals(""))) {
					Database.removeOptionalModule(selectedModuleR);
				} else {
					System.out.println("Could not remove module");
				}
				addModel = new DefaultListModel<>();
				removeModel = new DefaultListModel<>();
				loadAddModels(Database.getDegreeName(Integer.valueOf(info[0])));
				loadRemoveModels(Integer.valueOf(info[0]));
				revalidate();
			}
				
		});
		delete.add(btnRemove);
		
		JLabel addTitle = new JLabel("Add optional modules");
		addTitle.setFont(new Font("Tahoma", Font.PLAIN, 14));
		addTitle.setBounds(10, 11, 256, 22);
		contentPane.add(addTitle);
		add.setLayout(null);
		
		JList optionalModules = new JList(addModel);
		optionalModules.setBounds(188, 11, 214, 128);
		optionalModules.addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent arg0) {
				selectedModule = (String) optionalModules.getSelectedValue();
			}
			
		});
		add.add(optionalModules);
		
		JLabel RemoveTitle = new JLabel("Remove Modules");
		RemoveTitle.setFont(new Font("Tahoma", Font.PLAIN, 14));
		RemoveTitle.setBounds(10, 259, 118, 22);
		contentPane.add(RemoveTitle);
		
		JButton btnNewButton = new JButton("Add");
		btnNewButton.setBounds(10, 116, 89, 23);
		btnNewButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (!selectedModule.equals("")) {
					System.out.println(" Selected Module: " + selectedModule);
					Database.addOptionalModule(selectedModule, Integer.valueOf(info[0]));
							
				} else {
					System.out.println("Could not add optional module, nothing selected!");
				}
				addModel = new DefaultListModel<>();
				removeModel = new DefaultListModel<>();
				loadAddModels(Database.getDegreeName(Integer.valueOf(info[0])));
				loadRemoveModels(Integer.valueOf(info[0]));
				revalidate();
			}}
		);
		add.add(btnNewButton);
		
		JTextArea addText = new JTextArea();
		addText.setBackground(Color.WHITE);
		addText.setEditable(false);
		addText.setLineWrap(true);
		addText.setRows(2);
		addText.setFont(new Font("Tahoma", Font.PLAIN, 11));
		addText.setText("Select an module from the list and click add below");
		addText.setBounds(10, 7, 168, 98);
		add.add(addText);
	}

	public void display() {
		setVisible(true);
	}

}

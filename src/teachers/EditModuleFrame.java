package teachers;

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

import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.JTextField;
import javax.swing.DefaultListModel;
import javax.swing.JButton;

public class EditModuleFrame extends JFrame {

	private JPanel contentPane;
	private JTextField initField;
	private JTextField resitField;
	private JTextField passedField;
	private int moduleId = (Integer) null;

	private DefaultListModel<String> model = new DefaultListModel<>();

	private void loadModel(String studentInfo) {
		for (String x : Database.getModules(studentInfo)) {
			model.addElement(x);
		}
	}

	public EditModuleFrame(String studentInfo) {

		loadModel(studentInfo);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 605, 222);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(10, 11, 569, 175);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel title = new JLabel("Edit module details:");
		title.setFont(new Font("Tahoma", Font.PLAIN, 14));
		title.setBounds(10, 11, 187, 22);
		panel.add(title);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(223, 0, 345, 161);
		panel.add(scrollPane);

		JList<String> list = new JList<>(model);
		list.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent arg0) {
				moduleId = Integer.valueOf(list.getSelectedValue().split(" ")[0]);
			}
		});
		scrollPane.setViewportView(list);

		JLabel initLabel = new JLabel("Initial Grade:");
		initLabel.setBounds(10, 44, 92, 14);
		panel.add(initLabel);

		JLabel resitLabel = new JLabel("Resit Grade:");
		resitLabel.setBounds(10, 78, 92, 14);
		panel.add(resitLabel);

		JLabel passedLabel = new JLabel("Passed:");
		passedLabel.setBounds(10, 92, 92, 14);
		panel.add(passedLabel);

		initField = new JTextField();
		initField.setBounds(77, 44, 86, 20);
		panel.add(initField);
		initField.setColumns(10);

		resitField = new JTextField();
		resitField.setBounds(77, 75, 86, 20);
		panel.add(resitField);
		resitField.setColumns(10);

		passedField = new JTextField();
		passedField.setBounds(77, 106, 86, 20);
		panel.add(passedField);
		passedField.setColumns(10);

		JButton updateButton = new JButton("Update");
		updateButton.setBounds(13, 120, 89, 23);
		updateButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				Database.updateModule(moduleId, initField.getText(), resitField.getText(), passedField.getText());
			}

		});
		panel.add(updateButton);
	}

}

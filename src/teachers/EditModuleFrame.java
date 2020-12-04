package teachers;
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

/**
 * 
 * EditModuleFrame.java 27/11/2020
 * 
 * Add a new module for a degree course
 *
 */
public class EditModuleFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField initField;
	private JTextField resitField;
	private int moduleId = 0;

	
	
	private String[] loadModel(String studentInfo) {
		ArrayList<String> items = Database.getModules(studentInfo);
		String[] output = new String[items.size()];
		for (int a = 0; a < items.size(); a++) {
			output[a] = items.get(a);
		}
		return output;
	}

	public EditModuleFrame(String studentInfo) {

		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
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
		title.setBounds(10, 0, 187, 22);
		panel.add(title);
		
		JLabel explain = new JLabel("Select module from list");
		explain.setFont(new Font("Tahoma", Font.PLAIN, 14));
		explain.setBounds(10, 20, 187, 22);
		panel.add(explain);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(223, 0, 345, 161);
		panel.add(scrollPane);

		JList<String> list = new JList<>();
		list.setListData( loadModel(studentInfo));
		list.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent arg0) {
				System.out.println(list.getSelectedValue().split(" ")[0]);
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

		initField = new JTextField();
		initField.setBounds(90, 44, 86, 20);
		panel.add(initField);
		initField.setColumns(10);

		resitField = new JTextField();
		resitField.setBounds(90, 75, 86, 20);
		panel.add(resitField);
		resitField.setColumns(10);


		JButton updateButton = new JButton("Update");
		updateButton.setBounds(13, 130, 89, 23);
		updateButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				Database.updateModule(moduleId, initField.getText(), resitField.getText());
				list.setListData(loadModel(studentInfo));

			}

		});
		panel.add(updateButton);
	}

}

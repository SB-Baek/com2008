package admins;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import database.Database;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JScrollPane;

/**
 * ModuleFrame.java 21/11/2020
 * 
 * Allows admins to add/remove modules, linking them to degrees and study levels
 *
 */

public class ModuleFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField nameField;
	private JTextField creditsField;
	private JComboBox<String> durationComboBox;
	private JTextField codeField;
	private JTextField degree;
	private JLabel info = new JLabel("");
		
	private String[] loadList() {
		List<String> elements = Database.getModuleElements();
		String[] elems = new String[elements.size()];
		for (int a = 0; a < elements.size(); a++) {
			elems[a] = elements.get(a);
		}
		return elems;
	}
	
	public static void main(String[] args) {
		new ModuleFrame().setVisible(true);
	}
	

	public ModuleFrame() {
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 658, 403);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(5, 5, 632, 348);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel title = new JLabel("Add Module");
		title.setFont(new Font("Tahoma", Font.PLAIN, 14));
		title.setBounds(10, 11, 170, 14);
		panel.add(title);
		
		JLabel name = new JLabel("Name:");
		name.setBounds(10, 46, 46, 14);
		panel.add(name);
		
		JLabel credits = new JLabel("Credits:");
		credits.setBounds(10, 71, 46, 14);
		panel.add(credits);
		
		JLabel duration = new JLabel("Duration:");
		duration.setBounds(10, 96, 60, 14);
		panel.add(duration);
		
		nameField = new JTextField();
		nameField.setBounds(75, 43, 117, 20);
		panel.add(nameField);
		nameField.setColumns(10);
		
		creditsField = new JTextField();
		creditsField.setColumns(10);
		creditsField.setBounds(75, 68, 117, 20);
		panel.add(creditsField);
		
		durationComboBox = new JComboBox<>();
		durationComboBox.setModel(new DefaultComboBoxModel<>(new String[] {"AUTUMN", "SPRING", "YEAR"}));
		durationComboBox.setBounds(100, 93, 117, 20);
		panel.add(durationComboBox);
		
		codeField = new JTextField();
		codeField.setBounds(75, 124, 86, 20);
		panel.add(codeField);
		codeField.setColumns(10);
		
		JLabel code = new JLabel("Code: ");
		code.setBounds(10, 127, 46, 14);
		panel.add(code);
		
		JLabel core = new JLabel("Core:");
		core.setBounds(10, 152, 46, 14);
		panel.add(core);
		
		JComboBox<String> optionalComboBox = new JComboBox<>();
		optionalComboBox.setModel(new DefaultComboBoxModel<>(new String[] {"Core", "Optional"}));
		optionalComboBox.setBounds(75, 149, 65, 20);
		panel.add(optionalComboBox);
		
		JLabel studyLevel = new JLabel("Study Level:");
		studyLevel.setBounds(10, 177, 86, 14);
		panel.add(studyLevel);
		
		JComboBox<String> studyLevelComboBox = new JComboBox<>();
		studyLevelComboBox.setModel(new DefaultComboBoxModel<>(new String[] {"Certificate", "Diploma", "Bachelors", "Masters"}));
		studyLevelComboBox.setBounds(90, 174, 94, 20);
		panel.add(studyLevelComboBox);
		
		info.setBounds(291, 290, 200, 23);
		panel.add(info);
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(291, 46, 331, 208);
		panel.add(scrollPane);
		
		JList<String> listModules = new JList<>();
		listModules.setListData(loadList());
		scrollPane.setViewportView(listModules);
		
		JButton addModuleButton = new JButton("Add module");
		addModuleButton.setBounds(7, 208, 150, 23);
		addModuleButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int optional = 0;
				switch((String) optionalComboBox.getSelectedItem()) {
					case "Optional":
						optional = 0; break;
					case "Core":
						optional = 1; break;
					default: break;
				}
				String studyLevel = (String) studyLevelComboBox.getSelectedItem();
				int study = 0;
				switch(studyLevel) {
					case "Certificate" : study = 1; break;
					case "Diploma" : study = 2; break;
					case "Bachelors" : study = 3; break;
					case "Masters" : study = 4; break;
				}
				if (!Database.addModule(nameField.getText(), Integer.valueOf(creditsField.getText()), (String) durationComboBox.getSelectedItem(), codeField.getText(), optional, study)) {
					info.setText("Added module ");
				} else {
					info.setText("Could not add module");
				}
				listModules.setListData(loadList());
				revalidate();
			}
		});
		panel.add(addModuleButton);
		
		JLabel removeTitle = new JLabel("Remove Module");
		removeTitle.setFont(new Font("Tahoma", Font.PLAIN, 14));
		removeTitle.setBounds(291, 13, 170, 14);
		panel.add(removeTitle);
		
		JButton removeButton = new JButton("Remove Module");
		removeButton.setBounds(320, 264, 175, 23);
		removeButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (!Database.removeModule(Integer.valueOf(listModules.getSelectedValue().split(" ")[0]))) {
					info.setText("Removed module");
				} else {
					info.setText("Could not remove module");
				}
				listModules.setListData(loadList());
				revalidate();
			}
		});
		panel.add(removeButton);
		
		JLabel linkToDegree = new JLabel("Link to degree, select from list before adding");
		linkToDegree.setFont(new Font("Tahoma", Font.PLAIN, 14));
		linkToDegree.setBounds(10, 280, 300, 14);
		panel.add(linkToDegree);
		
		degree = new JTextField(); 
		degree.setBounds(10, 300, 117, 20);
		panel.add(degree);
		degree.setColumns(10);
		
		JLabel type = new JLabel("Type degree code");
		type.setFont(new Font("Tahoma", Font.PLAIN, 12));
		type.setBounds(140, 300, 200, 14);
		panel.add(type);
		
		JButton linkButton = new JButton("Link");
		linkButton.setBounds(10, 325, 117, 20);
		linkButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				Database.addDegreeModule(Integer.valueOf(listModules.getSelectedValue().split(" ")[0]), degree.getText());
				listModules.setListData(loadList());
				revalidate();	
			}				
		});
		panel.add(linkButton);
	}
}

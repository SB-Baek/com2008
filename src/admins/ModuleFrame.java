package admins;

import java.awt.BorderLayout;
import java.awt.EventQueue;

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

	public class ModuleFrame extends JFrame {

		private JPanel contentPane;
		private JTextField nameField;
		private JTextField creditsField;
		private JComboBox durationComboBox;
		private JTextField codeField;
		private JLabel info = new JLabel("");
		private DefaultListModel<String> items;
		
		
		private DefaultListModel<String> loadList(String tableName) {
			DefaultListModel<String> model = new DefaultListModel<>();
			List<String> elements = Database.getModuleElements(tableName);
			for (String elem : elements) {
				model.addElement(elem);
			}
			return model;
			
			
		}

		public ModuleFrame() {
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
			duration.setBounds(10, 96, 46, 14);
			panel.add(duration);
			
			nameField = new JTextField();
			nameField.setBounds(75, 43, 117, 20);
			panel.add(nameField);
			nameField.setColumns(10);
			
			creditsField = new JTextField();
			creditsField.setColumns(10);
			creditsField.setBounds(75, 68, 117, 20);
			panel.add(creditsField);
			
			durationComboBox = new JComboBox();
			durationComboBox.setModel(new DefaultComboBoxModel(new String[] {"AUTUMN", "SPRING", "YEAR"}));
			durationComboBox.setBounds(75, 93, 117, 20);
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
			
			JComboBox optionalComboBox = new JComboBox();
			optionalComboBox.setModel(new DefaultComboBoxModel(new String[] {"Core", "Optional"}));
			optionalComboBox.setBounds(75, 149, 65, 20);
			panel.add(optionalComboBox);
			
			JLabel studyLevel = new JLabel("Study Level:");
			studyLevel.setBounds(10, 177, 86, 14);
			panel.add(studyLevel);
			
			JComboBox studyLevelComboBox = new JComboBox();
			studyLevelComboBox.setModel(new DefaultComboBoxModel(new String[] {"Certificate", "Diploma", "Bachelors", "Masters"}));
			studyLevelComboBox.setBounds(75, 174, 94, 20);
			panel.add(studyLevelComboBox);
			
			info.setBounds(0, 0, 200, 200);
			panel.add(info);
			
			JButton addModuleButton = new JButton("Add module");
			addModuleButton.setBounds(7, 208, 89, 23);
			addModuleButton.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					int optional = (int) optionalComboBox.getSelectedItem();
					switch(optional) {
					case 0:
						optional = 0; break;
					case 1:
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
					if (Database.addModule(nameField.getText(), Integer.valueOf(creditsField.getText()), (String) durationComboBox.getSelectedItem(), codeField.getText(), optional, study)) {
						info.setText("Added module ");
					} else {
						info.setText("Could not add module");
					}
				}
			});
			panel.add(addModuleButton);
			JScrollPane scrollPane = new JScrollPane();
			scrollPane.setBounds(291, 46, 331, 208);
			panel.add(scrollPane);
			
			JList listModules = new JList(items);
			scrollPane.setViewportView(listModules);
			
			
			JLabel removeTitle = new JLabel("Remove Module");
			removeTitle.setFont(new Font("Tahoma", Font.PLAIN, 14));
			removeTitle.setBounds(291, 13, 170, 14);
			panel.add(removeTitle);
			
			JButton removeButton = new JButton("Remove Module");
			removeButton.setBounds(291, 264, 109, 23);
			removeButton.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					if (Database.removeModule((int) listModules.getSelectedValue())) {
						info.setText("Removed module");
					} else {
						info.setText("Could not remove module");
					}
				}
				
				
				
				
			});
			panel.add(removeButton);
		}
	}

package guis;

import java.awt.Font;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;

import database.Database;

public class ModuleFrame extends JFrame {

	private JPanel contentPane;
	private String info;
	private String[] columnNames = {"Initial Grade", "Resit Grade", "Has passed", "Module Name", "Credits", "Duration", "Code", "Level of study"};
	private static String[][] data;
	
	public ModuleFrame(String studentId) {
		init(studentId);
	}

	private void init(String id) {

		setTitle("Module Viewer");

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 812, 552);
		contentPane = new JPanel();
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel List = new JPanel();
		List.setBounds(10, 0, 781, 30);
		
		contentPane.add(List);
		List.setLayout(null);

		JLabel title = new JLabel("Modules");
		title.setFont(new Font("Tahoma", Font.PLAIN, 14));
		title.setBounds(0, 0, 123, 19);
		List.add(title);
	
		String[] info = Database.getStudentModules("0").split(":");

		data = new String[info.length][info[0].split(" ").length];
		for (int c= 0; c < info.length; c++) {
			String[] b = info[c].split(" ");
			for (int a = 0; a < b.length; a++) {
				if (b[a].equals("null")) {
					data[c][a] = "N/A";
				} else {
					data[c][a] = b[a]; 
					System.out.println(data[c][a]);
				}
			}	
		}
		JTable modules = new JTable(data, columnNames);
		modules.setBounds(10, 50, 761, 320);

		
		JScrollPane scroll = new JScrollPane();
		scroll.setBounds(10, 50, 761, 320);
		
		contentPane.add(scroll);
		scroll.setLayout(null);
		scroll.add(modules);

		
		
		contentPane.revalidate();
		setVisible(true);

	}

}

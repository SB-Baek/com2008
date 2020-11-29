package guis;

import java.awt.EventQueue;
import javax.swing.JFrame;
import java.awt.GridLayout;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import database.Database;

import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import java.awt.FlowLayout;

/**
 * 
 * @author horn1
 *
 *         After every operation call revalidate() to update GUI elements
 *
 */

public class BaseFrame extends JFrame {

	private static JTextField searchField;
	private static String searchQuery = "";
	private static String selectedStudentInfo = "";
	private static JPanel genInfo;
	private static JLabel ireg = new JLabel("");
	private static JLabel iname = new JLabel("");
	private static JLabel iEmail = new JLabel("");
	private static JLabel iTutor = new JLabel("");

	/**
	 * Initialise the contents of the student frame.
	 */
	public void initStudentFrame(String user) {
		new JFrame();
		setTitle("University Project");
		setBounds(100, 100, 1024, 768);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);

		JLabel logged = new JLabel("Logged in as");
		logged.setFont(new Font("Tahoma", Font.PLAIN, 12));
		logged.setBounds(10, 11, 137, 21);
		getContentPane().add(logged);

		JPanel info = new JPanel();
		info.setBounds(10, 34, 378, 43);
		getContentPane().add(info);
		info.setLayout(null);

		JButton logout = new JButton("Logout");
		logout.setBounds(182, 11, 89, 23);
		info.add(logout);

		JLabel nameTemp = new JLabel(user);
		nameTemp.setFont(new Font("Tahoma", Font.PLAIN, 12));
		nameTemp.setBounds(10, 11, 168, 19);
		info.add(nameTemp);

		JButton exit = new JButton("Exit");
		exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		exit.setBounds(281, 11, 89, 23);
		info.add(exit);

		JPanel viewing = new JPanel();
		viewing.setBounds(10, 88, 378, 74);
		getContentPane().add(viewing);
		viewing.setLayout(null);

		searchField = new JTextField();
		searchField.setToolTipText("Type here");
		searchField.setBounds(10, 33, 270, 28);
		viewing.add(searchField);
		searchField.setColumns(10);

		DefaultListModel<String> listModel = new DefaultListModel<>();
		JList<String> list = new JList<>(listModel);
		list.setBounds(398, 60, 600, 407);

		getContentPane().add(list);

		list.addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent arg0) {

				selectedStudentInfo = list.getSelectedValue();
				displayStudentInfo(Database.selectStudentInfo(list.getSelectedValue()));
				revalidate();

			}
		});

		genInfo = new JPanel();
		genInfo.setBounds(398, 472, 600, 246);
		getContentPane().add(genInfo);
		genInfo.setLayout(null);

		JLabel infoTitle = new JLabel("Information");
		infoTitle.setFont(new Font("Tahoma", Font.PLAIN, 14));
		infoTitle.setBounds(10, 11, 111, 25);
		genInfo.add(infoTitle);

		JLabel infoReg = new JLabel("Registration number: ");
		infoReg.setBounds(10, 50, 46, 14);
		ireg.setBounds(60, 50, 46, 14);
		genInfo.add(ireg);
		genInfo.add(infoReg);

		JLabel infoName = new JLabel("Name: ");
		infoName.setBounds(10, 75, 46, 14);
		iname.setBounds(60, 75, 46, 14);
		genInfo.add(infoName);
		genInfo.add(iname);

		JLabel infoEmail = new JLabel("Email:");
		infoEmail.setBounds(10, 100, 46, 14);
		iEmail.setBounds(60, 100, 46, 14);
		genInfo.add(infoEmail);
		genInfo.add(iEmail);

		JLabel infoTutor = new JLabel("Tutor:");
		infoTutor.setBounds(10, 125, 46, 14);
		iTutor.setBounds(60, 125, 46, 14);
		genInfo.add(infoTutor);
		genInfo.add(iTutor);

		JButton moduleViewer = new JButton("View Modules");
		moduleViewer.setBounds(10, 189, 89, 23);
		genInfo.add(moduleViewer);

		moduleViewer.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println(searchQuery);
				if (!searchQuery.equals("")) {
					System.out.println(selectedStudentInfo);
					new ModuleFrame(selectedStudentInfo.split(" ")[0]);
				}
			}
		});

		getContentPane().add(genInfo);

		JButton searchBut = new JButton("Search");
		searchBut.setIcon(
				new ImageIcon(BaseFrame.class.getResource("/javax/swing/plaf/metal/icons/ocean/hardDrive.gif")));
		searchBut.setBounds(283, 36, 85, 23);
		searchBut.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				searchQuery = searchField.getText();

				for (String name : Database.studentSearch(searchQuery)) {
					listModel.addElement(name);
				}
				// refresh list model
				list.setModel(listModel);
				revalidate();
			}

		});
		viewing.add(searchBut);

		JLabel records = new JLabel("View Student Records");
		records.setFont(new Font("Tahoma", Font.PLAIN, 12));
		records.setBounds(10, 11, 170, 23);
		viewing.add(records);

		JToolBar toolBar = new JToolBar();
		toolBar.setBounds(398, 22, 600, 25);
		getContentPane().add(toolBar);

		JButton student = new JButton("Student");
		toolBar.add(student);

		JButton degree = new JButton("Degree");
		toolBar.add(degree);

		JButton department = new JButton("Department");
		toolBar.add(department);

	}

	public void displayStudentInfo(String i) {
		System.out.println(i);
		String[] info = i.split(" ");
		ireg.setText(info[0]);
		iname.setText(info[1] + " " + info[2] + " " + info[3]);
		iEmail.setText(info[4]);
		iTutor.setText(info[5]);

		revalidate();
	}

	public void display() {
		setVisible(true);
	}

	public static String getSearch() {
		return searchQuery;
	}

	public static void generateInfo(String name, String email, String tutor) {
		JLabel jname = new JLabel(name);
		JLabel jemail = new JLabel(email);
		JLabel jtutor = new JLabel(tutor);
		genInfo.add(jname);
		genInfo.add(jemail);
		genInfo.add(jtutor);

	}

	public  void appendStudent(String name, String email, String tutor) {
		JPanel student = new JPanel();
		JLabel jname = new JLabel(name);
		JLabel jemail = new JLabel(email);
		JLabel jtutor = new JLabel(tutor);
		JButton jbutton = new JButton("info");
		student.add(jname);
		student.add(jemail);
		student.add(jtutor);
		student.add(jbutton);
		jbutton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				generateInfo(name, email, tutor);
			}

		});
		revalidate();
	}
}

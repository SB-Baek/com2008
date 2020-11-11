package guis;

import java.awt.EventQueue;
import javax.swing.JFrame;
import java.awt.GridLayout;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import java.awt.FlowLayout;

/**
 * 
 * @author horn1
 *
 *
 *	-
 *
 *
 *
 *	After every operation call revalidate() to update GUI elements
 *
 */



public class StudentFrame {

	private static JFrame frmLogin;
	private static JTextField searchField;
	private static String searchQuery = "";
	private static JPanel viewer;
	private static JPanel genInfo;
	
	
	/**
	 * Create the application.
	 */
	public StudentFrame() {
		initStudentFrame();
	}
	
	/**
	 * Initialise the contents of the student frame.
	 */
	public static void initStudentFrame() {
		frmLogin = new JFrame();
		frmLogin.setTitle("University Project");
		frmLogin.setBounds(100, 100, 1024, 768);
		frmLogin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmLogin.getContentPane().setLayout(null);
		
		JScrollPane viewerScroll = new JScrollPane();
		viewerScroll.setBounds(398, 11, 600, 707);
		frmLogin.getContentPane().add(viewerScroll);
		
		viewer = new JPanel();
		viewerScroll.setViewportView(viewer);
		viewer.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel logged = new JLabel("Logged in as");
		logged.setFont(new Font("Tahoma", Font.PLAIN, 12));
		logged.setBounds(10, 11, 137, 21);
		frmLogin.getContentPane().add(logged);
		
		JPanel info = new JPanel();
		info.setBounds(10, 34, 378, 43);
		frmLogin.getContentPane().add(info);
		info.setLayout(null);
		
		JButton logout = new JButton("Logout");
		logout.setBounds(182, 11, 89, 23);
		info.add(logout);
		
		JLabel nameTemp = new JLabel("Mr Example Username");
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
		frmLogin.getContentPane().add(viewing);
		viewing.setLayout(null);
		
		searchField = new JTextField();
		searchField.setToolTipText("Type here");
		searchField.setBounds(10, 33, 270, 28);
		viewing.add(searchField);
		searchField.setColumns(10);
		
		JButton searchBut = new JButton("Search");
		searchBut.setIcon(new ImageIcon(StudentFrame.class.getResource("/javax/swing/plaf/metal/icons/ocean/hardDrive.gif")));
		searchBut.setBounds(283, 36, 85, 23);
		searchBut.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				searchQuery = searchField.getText();
			}
			
		});
		viewing.add(searchBut);
		
		JLabel records = new JLabel("View Student Records");
		records.setFont(new Font("Tahoma", Font.PLAIN, 12));
		records.setBounds(10, 11, 170, 23);
		viewing.add(records);
		
		JToolBar toolBar = new JToolBar();
		toolBar.setBounds(398, 22, 600, 25);
		frmLogin.getContentPane().add(toolBar);
		
		JButton student = new JButton("Student");
		toolBar.add(student);
		
		JButton degree = new JButton("Degree");
		toolBar.add(degree);
		
		JButton department = new JButton("Department");
		toolBar.add(department);
		
		genInfo = new JPanel();
		genInfo.setBounds(398, 472, 600, 246);
		frmLogin.getContentPane().add(genInfo);
		genInfo.setLayout(null);
		
		JLabel Select = new JLabel("Information");
		Select.setBounds(10, 11, 111, 25);
		genInfo.add(Select);
	}
	
	public static void display() {
		frmLogin.setVisible(true);
	}
	
	public static String getSearch() {
		return searchQuery;
	}
	
	public static void updateInformation() {
			
	}
	
	public static void generateInfo(String name, String email, String tutor) {
		JLabel jname = new JLabel(name);
		JLabel jemail = new JLabel(email);
		JLabel jtutor = new JLabel(tutor);
		genInfo.add(jname);
		genInfo.add(jemail);
		genInfo.add(jtutor);

	}	
		
		
	public static void appendStudent(String name, String email, String tutor) {
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
		viewer.add(student);
		frmLogin.repaint();
		frmLogin.revalidate();
	}
}

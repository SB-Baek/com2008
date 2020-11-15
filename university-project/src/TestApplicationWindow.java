import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.GridLayout;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
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
import javax.swing.JToolBar;
import java.awt.Color;
import javax.swing.border.BevelBorder;
import javax.swing.border.EtchedBorder;
import java.awt.CardLayout;
import javax.swing.ScrollPaneConstants;
import java.awt.BorderLayout;
import javax.swing.JList;
import java.awt.List;

public class TestApplicationWindow {

	private JFrame frmLogin;
	private JTextField name;
	private JTextField code;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TestApplicationWindow window = new TestApplicationWindow();
					window.frmLogin.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public TestApplicationWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmLogin = new JFrame();
		frmLogin.setTitle("University Project");
		frmLogin.setBounds(100, 100, 1024, 768);
		frmLogin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmLogin.getContentPane().setLayout(null);
		
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
			}
		});
		exit.setBounds(281, 11, 89, 23);
		info.add(exit);
		
		JPanel viewing = new JPanel();
		viewing.setBounds(10, 88, 378, 129);
		frmLogin.getContentPane().add(viewing);
		viewing.setLayout(null);
		
		name = new JTextField();
		name.setToolTipText("Type here");
		name.setBounds(10, 35, 270, 28);
		viewing.add(name);
		name.setColumns(10);
		
		JButton searchBut = new JButton("Add");
		searchBut.setIcon(new ImageIcon(TestApplicationWindow.class.getResource("/javax/swing/plaf/metal/icons/ocean/hardDrive.gif")));
		searchBut.setBounds(283, 36, 85, 23);
		viewing.add(searchBut);
		
		JLabel records = new JLabel("Add New Department");
		records.setFont(new Font("Tahoma", Font.PLAIN, 12));
		records.setBounds(10, 11, 170, 23);
		viewing.add(records);
		
		code = new JTextField();
		code.setToolTipText("Type here");
		code.setColumns(10);
		code.setBounds(10, 74, 270, 28);
		viewing.add(code);
		
		JToolBar toolBar = new JToolBar();
		toolBar.setBounds(398, 22, 600, 25);
		frmLogin.getContentPane().add(toolBar);
		
		JButton student = new JButton("Student");
		toolBar.add(student);
		
		JButton degree = new JButton("Degree");
		toolBar.add(degree);
		
		JButton department = new JButton("Department");
		toolBar.add(department);
		
		JPanel genInfo = new JPanel();
		genInfo.setBounds(398, 472, 600, 246);
		frmLogin.getContentPane().add(genInfo);
		genInfo.setLayout(null);
		
		JLabel infoTitle = new JLabel("Information");
		infoTitle.setFont(new Font("Tahoma", Font.PLAIN, 14));
		infoTitle.setBounds(10, 11, 111, 25);
		genInfo.add(infoTitle);
		
		JLabel infoName = new JLabel("Name: ");
		infoName.setBounds(10, 50, 46, 14);
		genInfo.add(infoName);
		
		JLabel infoEmail = new JLabel("Email:");
		infoEmail.setBounds(10, 75, 46, 14);
		genInfo.add(infoEmail);
		
		JLabel infoSubject = new JLabel("Subject:");
		infoSubject.setBounds(10, 125, 46, 14);
		genInfo.add(infoSubject);
		
		JLabel infoTutor = new JLabel("Tutor:");
		infoTutor.setBounds(10, 100, 46, 14);
		genInfo.add(infoTutor);
		
		JLabel infoStudyLevel = new JLabel("Study Level:");
		infoStudyLevel.setBounds(10, 150, 85, 14);
		genInfo.add(infoStudyLevel);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setBounds(49, 50, 46, 14);
		genInfo.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("New label");
		lblNewLabel_1.setBounds(49, 75, 46, 14);
		genInfo.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("New label");
		lblNewLabel_2.setBounds(49, 100, 46, 14);
		genInfo.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("New label");
		lblNewLabel_3.setBounds(59, 125, 46, 14);
		genInfo.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("New label");
		lblNewLabel_4.setBounds(83, 150, 46, 14);
		genInfo.add(lblNewLabel_4);
		
		JList list = new JList();
		list.setBounds(992, 63, -583, 398);
		frmLogin.getContentPane().add(list);
		
		JList list_1 = new JList();
		list_1.setBounds(398, 466, 600, -407);
		frmLogin.getContentPane().add(list_1);
	}
}

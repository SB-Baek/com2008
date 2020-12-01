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

public class TestRegistrars {

	private JFrame frmLogin;
	private JTextField textField_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TestRegistrars window = new TestRegistrars();
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
	public TestRegistrars() {
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
		logout.setBounds(264, 10, 89, 23);
		info.add(logout);
		
		JLabel nameTemp = new JLabel("Mr Example Username");
		nameTemp.setFont(new Font("Tahoma", Font.PLAIN, 12));
		nameTemp.setBounds(10, 11, 168, 19);
		info.add(nameTemp);
		
		JPanel viewing = new JPanel();
		viewing.setBounds(10, 88, 378, 267);
		frmLogin.getContentPane().add(viewing);
		viewing.setLayout(null);
		
		JButton addRemove = new JButton("Add/Remove Student");
		addRemove.setIcon(null);
		addRemove.setBounds(10, 34, 162, 23);
		viewing.add(addRemove);
		
		JLabel lblNewLabel_5 = new JLabel("Student");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_5.setBounds(10, 11, 162, 23);
		viewing.add(lblNewLabel_5);
		
		JButton btnNewButton = new JButton("Check Registration");
		btnNewButton.setBounds(10, 133, 131, 23);
		viewing.add(btnNewButton);
		
		textField_1 = new JTextField();
		textField_1.setBounds(57, 105, 152, 20);
		viewing.add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblNewLabel_6 = new JLabel("Registration");
		lblNewLabel_6.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_6.setBounds(10, 80, 152, 14);
		viewing.add(lblNewLabel_6);
		
		JLabel lblNewLabel_7 = new JLabel("Email:");
		lblNewLabel_7.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_7.setBounds(10, 108, 108, 14);
		viewing.add(lblNewLabel_7);
		
		JLabel lblNewLabel_8 = new JLabel("Optional Modules");
		lblNewLabel_8.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_8.setBounds(10, 167, 131, 14);
		viewing.add(lblNewLabel_8);
		
		JButton btnAddremoveOptionalModules = new JButton("Add/Remove Optional Modules");
		btnAddremoveOptionalModules.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnAddremoveOptionalModules.setBounds(10, 192, 214, 23);
		viewing.add(btnAddremoveOptionalModules);
		
		JToolBar toolBar = new JToolBar();
		toolBar.setBounds(398, 22, 600, 25);
		frmLogin.getContentPane().add(toolBar);
		
		JButton student = new JButton("Student");
		toolBar.add(student);
		
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
		
		JButton button = new JButton("New button");
		button.setBounds(10, 189, 89, 23);
		genInfo.add(button);
		
		JList list = new JList();
		list.setBounds(992, 63, -583, 398);
		frmLogin.getContentPane().add(list);
		
		JList list_1 = new JList();
		list_1.setBounds(398, 466, 600, -407);
		frmLogin.getContentPane().add(list_1);
	}
}

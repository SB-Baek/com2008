package guis;

import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JToolBar;

public class DegreeFrame {


	private JFrame frmLogin;
	public static JTextField id;
	public static JTextField name;
	public static JTextField code;
	
	public DegreeFrame() {
		initDegree();
	}
	
	public void initDegree() {
		frmLogin = new JFrame();
		frmLogin.setTitle("University Project");
		frmLogin.setBounds(100, 100, 1024, 768);
		frmLogin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmLogin.getContentPane().setLayout(null);
		
		JScrollPane viewerScroll = new JScrollPane();
		viewerScroll.setBounds(398, 58, 600, 660);
		frmLogin.getContentPane().add(viewerScroll);
		
		JPanel viewer = new JPanel();
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
		viewing.setBounds(10, 88, 378, 180);
		frmLogin.getContentPane().add(viewing);
		viewing.setLayout(null);
		
		JButton searchBut = new JButton("Add");
		//searchBut.setIcon(new ImageIcon(DepartmentFrame.class.getResource("/javax/swing/plaf/metal/icons/ocean/hardDrive.gif")));
		searchBut.setBounds(283, 36, 85, 23);
		searchBut.setActionCommand("AddDeg");
        searchBut.addActionListener(EventHandler.getInstance());
		viewing.add(searchBut);
		
		JButton deleteBut = new JButton("Delete");
		deleteBut.setBounds(283, 73, 85, 23);
		deleteBut.setActionCommand("DeleteDeg");
        deleteBut.addActionListener(EventHandler.getInstance());
		viewing.add(deleteBut);
		
		JLabel records = new JLabel("Edit Degree");
		records.setFont(new Font("Tahoma", Font.PLAIN, 12));
		records.setBounds(10, 11, 170, 23);
		viewing.add(records);
		
		id = new JTextField();
		id.setToolTipText("Type here");
		id.setBounds(10, 35, 270, 28);
		viewing.add(id);
		id.setColumns(10);
		
		name = new JTextField();
		name.setToolTipText("Type here");
		name.setBounds(10, 74, 270, 28);
		viewing.add(name);
		name.setColumns(10);
		
		code = new JTextField();
		code.setToolTipText("Type here");
		code.setColumns(10);
		code.setBounds(10, 113, 270, 28);
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
	}
	
	public void display() {
		frmLogin.setVisible(true);
	}
	
	
	
}

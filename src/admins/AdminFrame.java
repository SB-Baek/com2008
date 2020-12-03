package admins;

import java.awt.Font;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import guis.BaseFrame;

/**
 * 
 * AdminFrame.java 29/11/2020
 * 
 * Creates welcome frame for administrators. Admins can perform actions on all users 
 * from this frame as well as view students.
 *
 */
public class AdminFrame extends BaseFrame  {
	
	private static final long serialVersionUID = 1L;

	public AdminFrame(String username) {
		
		initBaseFrame(username);
		
		JPanel adminOptions = new JPanel();
		adminOptions.setBounds(10, 150, 378, 400);
		getContentPane().add(adminOptions);
		adminOptions.setLayout(null);
		
		
		JLabel userAccountTitle = new JLabel("Admin controls");
		userAccountTitle.setBounds(0, 0, 200, 32);
		userAccountTitle.setFont(new Font("Tahoma", Font.PLAIN, 12));
		adminOptions.add(userAccountTitle);
		
		JButton uatButton = new JButton("Add / remove user accounts");
		uatButton.setBounds(0, 50,200, 30);
		uatButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new UserAccountsFrame().setVisible(true);
			}
		});
		
		JButton departmentButton = new JButton("Add / remove department");
		departmentButton.setBounds(0, 100, 200, 30);
		departmentButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				new DepartmentFrame().setVisible(true);
			}
		});
		
		JButton degreeButton = new JButton("Add / remove degree course");
		degreeButton.setBounds(0, 150, 200, 30);
		degreeButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				new DegreeFrame().setVisible(true);
			}
			
		});
		
		JButton mButton = new JButton("Add / remove modules");
		mButton.setBounds(0, 200, 200, 30);
		mButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				new ModuleFrame().setVisible(true);
			}			
		});
		
		adminOptions.add(uatButton);
		adminOptions.add(departmentButton);
		adminOptions.add(degreeButton);
		adminOptions.add(mButton);
		getContentPane().add(adminOptions);
			
	}

}

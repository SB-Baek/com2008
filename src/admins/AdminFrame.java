package admins;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import database.Database;
import guis.BaseFrame;
import teachers.Teacher;

public class AdminFrame extends BaseFrame  {
	
	public AdminFrame(String username) {
		
		initBaseFrame(username);
		
		JPanel adminOptions = new JPanel();
		adminOptions.setBounds(10, 466, 378, 74);
		getContentPane().add(adminOptions);
		adminOptions.setLayout(null);
		
		
		JLabel userAccountTitle = new JLabel("Admin controls");
		userAccountTitle.setBounds(0, 0, 378, 32);
		userAccountTitle.setFont(new Font("Tahoma", Font.PLAIN, 12));
		adminOptions.add(userAccountTitle);
		
		JButton uatButton = new JButton("Add / remove user accounts");
		uatButton.setBounds(0, 32, 200, 20);
		uatButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new UserAccountsFrame().setVisible(true);
			}
			
		});
		
		JButton mButton = new JButton("Add / remove modules");
		mButton.setBounds(0, 60, 200, 20);
		mButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				new ModuleFrame().setVisible(true);
			}			
		});
		
		
		
		
		
		
		
		adminOptions.add(uatButton);
		getContentPane().add(adminOptions);
		
		
		
		
		
		
		
		
		
		
		
	}
	
	
	
	
	
	
}

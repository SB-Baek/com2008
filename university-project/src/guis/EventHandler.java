package guis;

import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JToolBar;

import java.sql.*;


public class EventHandler extends JPanel implements ActionListener {
	
	private static EventHandler instance = new EventHandler();
	
    public static EventHandler getInstance() { 
    	return instance; 
    }
	
	public EventHandler() {
		
		setLayout(new GridLayout(0,1));
		
		JButton searchBut = new JButton("Add");
		searchBut.addActionListener(this);
		add(searchBut);
	}
	public void actionPerformed(ActionEvent event) {
		String command = event.getActionCommand();
		String database = "jdbc:mysql://stusql.dcs.shef.ac.uk/team052";
		String dataUser = "team052";
		String dataPassword = "04be7a6d";
		Connection con = null;
		Statement stmt = null; 
		
		try {
			//connect to the database
			con = DriverManager.getConnection(database, dataUser, dataPassword);
			stmt = con.createStatement();
			//add button action
			if (command.equals("Add")) {
				String depName = DepartmentFrame.name.getText();
				String depCode = DepartmentFrame.code.getText();
				String depAbb = DepartmentFrame.abb.getText();
				String query = "INSERT INTO Department VALUES('" + depName + "','" + depCode + "','" + depAbb +"')";
				stmt.executeUpdate(query);
			}
			else if (command.equals("Delete")) {
				String depName = DepartmentFrame.name.getText();
				String depCode = DepartmentFrame.code.getText();
				String depAbb = DepartmentFrame.abb.getText();
				String query = 
						"DELETE FROM Department WHERE deptid='" + depName + "'and name='" + depCode + "'and abbreviation='" + depAbb +"'";
				stmt.executeUpdate(query);
			}
			JOptionPane.showMessageDialog(null,"Query Executed");
		}
		catch (SQLException ex) {
			ex.printStackTrace();
			JOptionPane.showMessageDialog(null,"error");
		}
	}
	
}

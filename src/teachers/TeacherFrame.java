package teachers;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import database.Database;
import teachers.Teacher;

import guis.BaseFrame;

public class TeacherFrame extends BaseFrame {
	
	public TeacherFrame(String user) {
		initBaseFrame(user);
		
		JPanel teacherOptions = new JPanel();
		teacherOptions.setBounds(10, 200, 378, 400);
		getContentPane().add(teacherOptions);
		teacherOptions.setLayout(null);
		
	

		JButton aUButton = new JButton("Add or update student grades");
		aUButton.setBounds(0, 60, 250, 20);
		aUButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (!selectedStudentInfo.equals("")) { 
					new EditModuleFrame(selectedStudentInfo).setVisible(true);
				}
			}
			
		});
		teacherOptions.add(aUButton);
		
		JLabel weightedGradeLabel = new JLabel("Click on the list of students to edit details");
		weightedGradeLabel.setBounds(0, 0, 378, 32);
		weightedGradeLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		teacherOptions.add(weightedGradeLabel);
		
		JButton wGButton = new JButton("Calculate Overall Grade");
		wGButton.setBounds(0, 32, 200, 20);
		wGButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (!selectedStudentInfo.equals("")) { 
					Teacher.weightedMeanGrade(selectedStudentInfo);
				}
			}
			
		});
		teacherOptions.add(wGButton);
		getContentPane().add(teacherOptions);
		
		
		display();
	}
	
	
	
	
	
}

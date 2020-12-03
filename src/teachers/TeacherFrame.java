package teachers;

import java.awt.Font;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import guis.BaseFrame;

/**
 * 
 * TeacherFrame.java 13/11/2020
 * 
 * Allows teachers to calculate overall student grades,
 * progress and graduate students
 *
 */

public class TeacherFrame extends BaseFrame {
	
	private static final long serialVersionUID = 1L;
	private JLabel checkGrade = new JLabel("");
	private JLabel checkProgression = new JLabel("");
	private JLabel checkGradeString = new JLabel("");
	private JLabel genInfo = new JLabel("");
	
	public TeacherFrame(String user) {
		initBaseFrame(user);
		
		JPanel teacherOptions = new JPanel();
		teacherOptions.setBounds(10, 200, 378, 400);
		getContentPane().add(teacherOptions);
		teacherOptions.setLayout(null);
		
		checkGrade.setBounds(0, 50, 200, 20);
		checkProgression.setBounds(0, 130, 200, 20);
		checkGradeString.setBounds(0, 170, 200, 20);
		genInfo.setBounds(0, 200, 200, 20);
		
		teacherOptions.add(checkGrade);
		teacherOptions.add(checkProgression);
		teacherOptions.add(checkGradeString);
		teacherOptions.add(genInfo);
		
		JButton gradButton = new JButton("Determine final grade");
		gradButton.setBounds(0, 150, 200, 20);
		gradButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (!(selectedStudentInfo.equals(""))) {
					genInfo.setText("");
					checkGradeString.setText("Grade acheived: " + Teacher.graduate(selectedStudentInfo));

				} else {
					genInfo.setText("Please select a student");
				}
				revalidate();
			}
			
		});
		teacherOptions.add(gradButton);
		
		
		JButton aUButton = new JButton("Add or update student grades");
		aUButton.setBounds(0, 70, 250, 20);
		aUButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (!selectedStudentInfo.equals("")) { 
					genInfo.setText("");

					new EditModuleFrame(selectedStudentInfo).setVisible(true);
				}
				else {
					genInfo.setText("Please select a student");

				}
				
				revalidate();
				
			}
			
		});
		teacherOptions.add(aUButton);
		
		JButton pButton = new JButton("Progress student");
		pButton.setBounds(0, 110, 250, 20);
		pButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (!selectedStudentInfo.equals("")) { 
					genInfo.setText("");

					 if (Teacher.canProgress(selectedStudentInfo)) {
						 checkProgression.setText("Student can progress");
					 } else {
						 checkProgression.setText("Student cannot progress");
					 }
					 revalidate();
				} else {
					genInfo.setText("Please select a student ");

				}
				revalidate();
			}
			
		});
		teacherOptions.add(pButton);
		
		
		JLabel weightedGradeLabel = new JLabel("Click on the list of students to edit details");
		weightedGradeLabel.setBounds(0, 0, 378, 32);
		weightedGradeLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		teacherOptions.add(weightedGradeLabel);
		
		JButton wGButton = new JButton("Calculate Overall Grade");
		wGButton.setBounds(0, 30, 200, 20);
		wGButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (!selectedStudentInfo.equals("")) { 
					genInfo.setText("");

					checkGrade.setText("Overall grade achieved: " + Teacher.weightedMeanGrade(selectedStudentInfo));
				} else {
					genInfo.setText("Please select a student");

				}
				revalidate();
			}
			
		});
		teacherOptions.add(wGButton);
		getContentPane().add(teacherOptions);
		
		
		display();
	}
	
	
	
	
	
}

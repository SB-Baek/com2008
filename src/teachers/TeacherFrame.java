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
		teacherOptions.setBounds(10, 466, 378, 74);
		getContentPane().add(teacherOptions);
		teacherOptions.setLayout(null);
		
		JLabel weightedGradeLabel = new JLabel("Find the average score of a student from the list");
		weightedGradeLabel.setBounds(0, 0, 378, 32);
		weightedGradeLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		teacherOptions.add(weightedGradeLabel);
		
		JButton wGButton = new JButton("Calculate Overall Grade");
		wGButton.setBounds(0, 32, 200, 20);
		wGButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (!selectedStudentInfo.equals("")) { 
					ArrayList<String> info = Database.getModules(selectedStudentInfo);
					ArrayList<Float> initGrades = new ArrayList<>();
					ArrayList<Float> resitGrades = new ArrayList<>();
					ArrayList<Integer> credits = new ArrayList<>();
					
					int studyLevel = Database.getStudentStudyLevel(selectedStudentInfo);
					
					for (String x : info) {
						String[] b = x.split(" ");
						initGrades.add(Float.parseFloat(b[3]));
						resitGrades.add(Float.parseFloat(b[4]));
						credits.add(Integer.valueOf(b[6]));
						
						
					}
					float[] initGradesF = new float[initGrades.size()];
					float[] resitGradesF = new float[resitGrades.size()];
					int[] creditsF = new int[credits.size()];
					
					
					for (int a = 0; a < initGrades.size(); a++) {
						initGradesF[a] = initGrades.get(a);
						System.out.println(initGrades.get(a));
					}
					for (int a = 0; a < resitGrades.size(); a++) {
						resitGradesF[a] = resitGrades.get(a);
					
						System.out.println(resitGrades.get(a));

					}
					//get credits
					for (int a = 0; a < creditsF.length; a++) {
						creditsF[a] = credits.get(a);
						System.out.println(credits.get(a));

					}
				
						
					Teacher.weightedMeanGrade(studyLevel, initGradesF, resitGradesF, creditsF);
				}
			}
			
		});
		teacherOptions.add(wGButton);
		getContentPane().add(teacherOptions);
		
		
		display();
	}
	
	
	
	
	
}

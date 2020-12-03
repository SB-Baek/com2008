package teachers;

import java.util.ArrayList;

import database.Database;

/**
 * 
 * Teacher.java 13/11/2020
 * 
 * Performs all the calculations for the teacher frame
 * 
 *
 */

public class Teacher {

	
	public static float weightedMeanGrade(String selectedStudentInfo) {

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
			System.out.println(initGradesF[a]);
		}
		for (int a = 0; a < resitGrades.size(); a++) {
			resitGradesF[a] = resitGrades.get(a);
			System.out.println(resitGradesF[a]);

		}
		
		for (int a = 0; a < creditsF.length; a++) {
			creditsF[a] = credits.get(a);
			System.out.println(creditsF[a]);

		}

		float overallGrade = 0f;
		for (int x = 0; x < initGradesF.length; x++) {
			if (initGradesF[x] <= resitGradesF[x]) {
				overallGrade += initGradesF[x] * creditsF[x];
			} else {
				float cappedGrade = ((studyLevel % 4 == 0) ? 50 : 40);
				if (resitGradesF[x] >= cappedGrade) {
					overallGrade += cappedGrade * creditsF[x];

				} else {
					overallGrade += resitGradesF[x] * creditsF[x];
				}
			}
		}
		System.out.print("Overall grade: " + overallGrade);
		return overallGrade;
	}

	public static boolean canProgress(String studentInfo) {

		float studentGrade = Database.getGrade(Integer.valueOf(studentInfo.split(" ")[0]));
		int studyLevel = Database.getStudentStudyLevel(studentInfo);
		boolean progress = false;

		switch (studyLevel) {
		case 1:
		case 2:
		case 3:
			if (studentGrade >= 39.5f) {
				progress = true;
			}
			break;
		case 4:
			if (studentGrade >= 49.5f) {
				progress = true;
			}
			break;
		default:
			break;
		}
		return progress;
	}

	public static String graduate(String studentInfo) {
		float studentGrade = Database.getGrade(Integer.valueOf(studentInfo.split(" ")[0]));
		int studyLevel = Database.getStudentStudyLevel(studentInfo);
		float overallGrade = 0f;
		String gradClass = "";
		
		//previous grade from year 3 will split the overall grade 1:2 for 3 year courses
		if (studyLevel == 3) {
		overallGrade = studentGrade * 0.3333f + weightedMeanGrade(studentInfo) * 0.6666f;
		} else if (studyLevel == 4) {
			//even splitting of the year 3 grade (carried on from 2nd year and year 4 grade
			//if the student is taking a 4 year course
			overallGrade = studentGrade * 0.5f + weightedMeanGrade(studentInfo) * 0.5f;
		} else if (studyLevel == 5) {
			overallGrade = studentGrade;
		} else {
			gradClass = "N/A";
		}
		
		String[][] grades = {
					  {"fail", "pass(non-honours)", "third class", "lower second", "upper second", "first class"},
					  {"fail", "fail", "fail", "lower second", "upper second", "first class"},
					  {"fail", "fail", "fail", "pass", "merit", "distinction"},
					 };
		if (studyLevel >= 3) {
		if (overallGrade >= 69.5f) {
			gradClass = grades[studyLevel - 3][5];
		}
		else if ( overallGrade >= 59.5f && overallGrade < 69.5f) {
			gradClass = grades[studyLevel - 3][4];
		}
		else if ( overallGrade >= 49.5f && overallGrade < 59.5f) {
			gradClass = grades[studyLevel - 3][3];
		}
		else if ( overallGrade >= 44.5f && overallGrade < 49.5f) {
			gradClass = grades[studyLevel - 3][2];
		}
		else if ( overallGrade >= 39.5f && overallGrade < 44.5f) {
			gradClass = grades[studyLevel - 3][1];
		}
		else if (  overallGrade < 39.5f) {
			gradClass = grades[studyLevel - 3][0];
		}
		} else {
			gradClass = "Not applicable";
		}
		System.out.println("Graduate grade: " + gradClass);
		return gradClass;
		}		
}



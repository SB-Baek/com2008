package teachers;

public class Teacher {

	// base weight of all modules they've taken
	public static float weightedMeanGrade(int studyLevel, float[] initGrades, float[] resitGrades, int[] credits) {
		float overallGrade = 0f;
		for (int x = 0; x < initGrades.length; x++) {
			if (initGrades[x] <= resitGrades[x]) {
				overallGrade += initGrades[x] * credits[x];
			} else {
				float cappedGrade = ((studyLevel % 4 == 0) ? 50 : 40);
				if (resitGrades[x] >= cappedGrade) {
					overallGrade += cappedGrade * credits[x];

				} else {
					overallGrade += resitGrades[x] * credits[x];
				}
			}
		}
		System.out.print("Overall grade: " + overallGrade);
		return overallGrade;
	}

}

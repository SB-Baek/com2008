package app;

public class Module {

	String name, duration, code;
	int credits;
	float intialGrade, resitGrade;
	boolean passed;
	
	
	
	public Module(String name, String duration, String code, int credits, float intialGrade, float resitGrade,
			boolean passed) {
		this.name = name;
		this.duration = duration;
		this.code = code;
		this.credits = credits;
		this.intialGrade = intialGrade;
		this.resitGrade = resitGrade;
		this.passed = passed;
	}
	public String getName() {
		return name;
	}
	public String getDuration() {
		return duration;
	}

	public String getCode() {
		return code;
	}

	public int getCredits() {
		return credits;
	}

	public float getIntialGrade() {
		return intialGrade;
	}

	public float getResitGrade() {
		return resitGrade;
	}

	public boolean isPassed() {
		return passed;
	}

	
	
	
	
	
}

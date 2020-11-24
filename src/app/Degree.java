package app;

public class Degree {
	String name, code;
	StudyLevel levelOfStudy;
	
	public Degree(String name, String code, StudyLevel levelOfStudy) {
		this.name = name;
		this.code = code;
		this.levelOfStudy = levelOfStudy;
		
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}
}

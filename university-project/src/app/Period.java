package app;

import java.util.Date;

public class Period {

	float grade;
	String label;
	Date start, end;
	
	public Period(float grade, String label, Date start, Date end) {
		this.grade = grade;
		this.label = label;
		this.start = start;
		this.end = end;
	}

	public boolean canProgress() {
		//TODO
	}
	
	public float weightedMean() {
		//TODO
	}
	
	
	public float getGrade() {
		return grade;
	}

	public void setGrade(float grade) {
		this.grade = grade;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public Date getStart() {
		return start;
	}

	public void setStart(Date start) {
		this.start = start;
	}

	public Date getEnd() {
		return end;
	}

	public void setEnd(Date end) {
		this.end = end;
	}
}

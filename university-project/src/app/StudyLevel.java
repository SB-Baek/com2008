package app;

public enum StudyLevel {

	CERTIFICATE('1'),
	DIPLOMA('2'),
	BACHELORS('3'),
	MASTERS('4'),
	PLACEMENT('p');
	
	private final char level;
	
	StudyLevel(char level) {
		 this.level = level;
	}
	
	public char getLevel() { return this.level; }
	
	
	
	
	
	
	
	
}

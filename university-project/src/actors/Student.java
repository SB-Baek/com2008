package actors;

public class Student {

	String title, forename, surname, email;

	public Student(String title, String forename, String surname, String email) {
		this.title = title;
		this.forename = forename;
		this.surname = surname;
		this.email = email;
	}
	
	public void register() {
		//TODO
	}
	
	

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getForename() {
		return forename;
	}

	public void setForename(String forename) {
		this.forename = forename;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	
	
	
}

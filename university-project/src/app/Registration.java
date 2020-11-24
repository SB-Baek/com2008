package app;

public class Registration {

	String registrationLabel;
	int creditTotal;

	Registration(String regLabel, int creditTotal) {
		registrationLabel = regLabel;
		this.creditTotal = creditTotal;
	}
	
	public String getRegistrationLabel() {
		return registrationLabel;
	}


	public void setRegistrationLabel(String registrationLabel) {
		this.registrationLabel = registrationLabel;
	}


	public int getCreditTotal() {
		return creditTotal;
	}


	public void setCreditTotal(int creditTotal) {
		this.creditTotal = creditTotal;
	}

	
	
}

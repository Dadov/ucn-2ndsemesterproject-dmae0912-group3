package Models;

public class Customer extends Person {

	private String registrationDate;
	private int noOfStays;

	public Customer() {

	}

	public Customer(int personID, String CPR, String fname, String lname,
			String country, String ZIP, String city, String address,
			String email, String password, String registrationDate,
			int noOfStays) {
		super(personID, CPR, fname, lname, country, ZIP, city, address, email,
				password);
		this.registrationDate = registrationDate;
		this.noOfStays = noOfStays;
	}

	public Customer(String CPR, String fname, String lname,
			String country, String ZIP, String city, String address,
			String email, String password, String registrationDate,
			int noOfStays) {
		super(CPR, fname, lname, country, ZIP, city, address, email,
				password);
		this.registrationDate = registrationDate;
		this.noOfStays = noOfStays;
	}

	public String getRegistrationDate() {
		return registrationDate;
	}

	public void setRegistrationDate(String registrationDate) {
		this.registrationDate = registrationDate;
	}

	public int getNoOfStays() {
		return noOfStays;
	}

	public void setNoOfStays(int noOfStays) {
		this.noOfStays = noOfStays;
	}

	@Override
	public String toString() {
		return super.toString() + "Customer [registrationDate="
				+ registrationDate + ", noOfStays=" + noOfStays + "]";
	}

}

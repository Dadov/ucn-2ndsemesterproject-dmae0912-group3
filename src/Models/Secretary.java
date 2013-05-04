package Models;

public class Secretary extends Staff {

	public Secretary() {

	}

	public Secretary(int personID, String CPR, String fname, String lname,
			String country, String ZIP, String address, String email,
			String password, double salary) {
		super(personID, CPR, fname, lname, country, ZIP, address, email,
				password, salary);
	}

	@Override
	public String toString() {
		return super.toString() + "Secretary []";
	}

}
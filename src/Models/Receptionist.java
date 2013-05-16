package Models;

public class Receptionist extends Staff {

	public Receptionist() {

	}

	public Receptionist(int personID, String CPR, String fname, String lname,
			String country, String ZIP, String city, String address,
			String email, String password, double salary) {
		super(personID, CPR, fname, lname, country, ZIP, city, address, email,
				password, salary);
	}

	@Override
	public String toString() {
		return super.toString() + "Receptionist []";
	}

}

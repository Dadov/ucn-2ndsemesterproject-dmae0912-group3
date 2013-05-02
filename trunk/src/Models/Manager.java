package Models;

public class Manager extends Staff {

	public Manager() {
		super();
	}

	public Manager(int personID, String CPR, String fname, String lname,
			String country, String ZIP, String address, String email,
			double salary) {
		super(personID, CPR, fname, lname, country, ZIP, address, email, salary);
	}

	@Override
	public String toString() {
		return super.toString() + "Manager []";
	}

}

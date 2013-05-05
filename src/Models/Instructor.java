package Models;

import java.util.ArrayList;

public class Instructor extends Staff {

	public Instructor() {

	}

	public Instructor(int personID, String CPR, String fname, String lname,
			String country, String ZIP, String address, String email,
			String password, double salary, ArrayList<Activity> activities) {
		super(personID, CPR, fname, lname, country, ZIP, address, email,
				password, salary);
	}

	@Override
	public String toString() {
		return "Instructor []";
	}

}

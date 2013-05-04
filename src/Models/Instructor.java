package Models;

import java.util.ArrayList;

public class Instructor extends Staff {

	private ArrayList<Activity> activities;

	public Instructor() {

	}

	public Instructor(int personID, String CPR, String fname, String lname,
			String country, String ZIP, String address, String email,
			String password, double salary, ArrayList<Activity> activities) {
		super(personID, CPR, fname, lname, country, ZIP, address, email,
				password, salary);
		this.activities = activities;
	}

	public ArrayList<Activity> getActivities() {
		return activities;
	}

	public void setActivities(ArrayList<Activity> activities) {
		this.activities = activities;
	}

	@Override
	public String toString() {
		return super.toString() + "Instructor [activities=" + activities + "]";
	}

}

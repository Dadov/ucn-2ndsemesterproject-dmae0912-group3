package Models;

public class Staff extends Person {

	private double salary;

	public Staff() {

	}

	public Staff(int personID, String CPR, String fname, String lname,
			String country, String ZIP, String address, String email,
			String password, double salary) {
		super(personID, CPR, fname, lname, country, ZIP, address, email,
				password);
		this.salary = salary;
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	@Override
	public String toString() {
		return super.toString() + "Staff [salary=" + salary + "]";
	}

}

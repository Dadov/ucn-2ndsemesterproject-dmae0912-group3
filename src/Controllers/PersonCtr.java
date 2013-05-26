package Controllers;

import java.util.Arrays;

import Models.Customer;
import Models.Instructor;
import Models.Manager;
import Models.Receptionist;
import Models.Secretary;
import Models.Staff;

public class PersonCtr {

	public PersonCtr() {

	}

	public int personLogin(int ID, char[] password) {
		int rank = 0;

		try {
			StaffCtr staffCtr = new StaffCtr();
			Staff staff = staffCtr.getEmployee(ID);
			if (staff != null
					&& Arrays.equals(staff.getPassword().toCharArray(),
							password)) {
				if (staff instanceof Manager) {
					rank = 1;
				} else if (staff instanceof Secretary
						|| staff instanceof Receptionist) {
					rank = 2;
				} else if (staff instanceof Instructor) {
					rank = 3;
				}
			}

		} catch (Exception e) {
			System.out
					.println("Exception from PersonCtr, trying to get Staff.");
			e.printStackTrace();
			e.getMessage();
		}

		try {
			CustomersCtr customersCtr = new CustomersCtr();
			Customer customer = customersCtr.getCustomer(ID);

			if (customer != null
					&& Arrays.equals(customer.getPassword().toCharArray(),
							password)) {
				rank = 4;
			}

		} catch (Exception e) {
			System.out
					.println("Exception from PersonCtr, trying to get Customer");
			e.printStackTrace();
			e.getMessage();
		}

		// if none of the above pass, return 0 == invalid login
		System.out.println("Rank to be returned from PersonCtr " + rank);
		return rank;
	}
}

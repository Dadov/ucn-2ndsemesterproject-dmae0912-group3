package Controllers;

import Models.Customer;
import Models.Instructor;
import Models.Manager;
import Models.Receptionist;
import Models.Secretary;
import Models.Staff;

public class PersonCtr {

	public PersonCtr() {

	}

	// for login
	public int personLogin(int ID, char[] password) {

		try {
			// TODO tried to use StaffCtr, did not work
			StaffCtr staffCtr = new StaffCtr();
			Staff staff = staffCtr.getEmployee(ID);

			// DAOStaff daoStaff = new DAOStaff();
			System.out.println("ID looking for in staff: " + ID);
			// Staff staff = daoStaff.getStaff(ID, false);

			// if (staff.equals(null)) {
			// System.out.println("tu je null pica, staff");
			// }

			// System.out.println(staff.toString());
			// System.out.println(staff.getPassword().toCharArray());
			if (staff != null
					&& staff.getPassword().toCharArray().equals(password)) {
				if (staff instanceof Manager) {
					return 1;
				} else if (staff instanceof Secretary
						|| staff instanceof Receptionist) {
					return 2;
				} else if (staff instanceof Instructor) {
					return 3;
				}
			}

		} catch (Exception e) {
			System.out
					.println("Exception from PersonCtr, trying to get Staff.");
			e.printStackTrace();
			e.getMessage();
		}

		try {
			// TODO tried to use StaffCtr, did not work
			CustomersCtr customersCtr = new CustomersCtr();
			Customer customer = customersCtr.getCustomer(ID);

			// DAOCustomer daoCustomer = new DAOCustomer();
			System.out.println("ID looking for in customer: " + ID);
			// Customer customer = daoCustomer.getCustomer(ID, false);

			// if (customer.equals(null)) {
			// System.out.println("tu je null pica, customer");
			// }

			// System.out.println(customer.toString());
			if (customer != null
					&& customer.getPassword().toCharArray().equals(password)) {
				return 4;
			}

		} catch (Exception e) {
			System.out
					.println("Exception from PersonCtr, trying to get Customer");
			e.printStackTrace();
			e.getMessage();
		}

		// if none of the above pass, return 0 == invalid login
		return 0;
	}
}

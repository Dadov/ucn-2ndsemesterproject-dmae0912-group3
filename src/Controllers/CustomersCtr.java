package Controllers;

import java.util.ArrayList;
import DAO.*;
import Models.Customer;

public class CustomersCtr {

	// creates a new instance of CustomersCtr
	public CustomersCtr() {
		
	}
	
	// adds a new customer to the database
	public void newCustomer(String CPR, String fname, String lname, String country, 
			String ZIP, String address, String email, String password, 
			String registrationDate, int noOfStays) {
		Customer customer = new Customer();
		customer.getPersonID();
		customer.setFname(fname);
		customer.setLname(lname);
		customer.setCountry(country);
		customer.setZIP(ZIP);
		customer.setAddress(address);
		customer.setEmail(email);
		customer.setPassword(password);
		customer.setRegistrationDate(registrationDate);
		customer.setNoOfStays(noOfStays);
		try {
			DBConnection.startTransaction();
			IFDAOCustomer daoCustomer = new DAOCustomer();
			daoCustomer.insert(customer);
			DBConnection.commitTransaction();
		}
		catch (Exception e) {
			System.out.println("Error while creating customer in CustomersCtr");
			DBConnection.rollbackTransaction();
			e.getMessage();
			e.printStackTrace();
		}
	}
	
	// returns a customer, given the ID
	public Customer getCustomer(int customerID) {
		IFDAOCustomer daoCustomer = new DAOCustomer();
		return daoCustomer.getCustomer(customerID, true);
	}
	
	// returns all the customers from the database
	public ArrayList<Customer> getAllCustomers() {
		IFDAOCustomer daoCustomer = new DAOCustomer();
		ArrayList<Customer> allCustomers = new ArrayList<Customer>();
		allCustomers = daoCustomer.getAllCustomers(false);
		return allCustomers;
	}
	
	// updates the details of a specific customer, given the ID
	public int updateCustomer(String CPR, String fname, String lname, String country, String ZIP, String address, String email, String password, String registrationDate, int noOfStays) {
		IFDAOCustomer daoCustomer = new DAOCustomer();
		Customer customer = new Customer();
		customer.setFname(fname);
		customer.setLname(lname);
		customer.setCountry(country);
		customer.setZIP(ZIP);
		customer.setAddress(address);
		customer.setEmail(email);
		customer.setPassword(password);
		customer.setRegistrationDate(registrationDate);
		customer.setNoOfStays(noOfStays);
		return daoCustomer.update(customer);
	}
	
	// deletes a customer from the database, having specified the ID
	public int deleteCustomer(int customerID) {
		IFDAOCustomer daoCustomer = new DAOCustomer();
		return daoCustomer.delete(customerID);
	}

}

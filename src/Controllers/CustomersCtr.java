package Controllers;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;

import DAO.DAOAgency;
import DAO.DAOCustomer;
import DAO.DBConnection;
import DAO.IFDAOAgency;
import DAO.IFDAOCustomer;
import Models.ActivityBooking;
import Models.Agency;
import Models.Customer;

public class CustomersCtr {

	// creates a new instance of CustomersCtr
	public CustomersCtr() {

	}

	// adds a new agency to the database
	public void newAgency(String name, int discountLevel,
			LinkedList<Customer> customers) {
		Agency agency = new Agency();
		agency.getID();
		agency.setName(name);
		agency.setAgencyDiscountLevel(discountLevel);
		agency.setProvidedCustomers(customers);

		try {
			DBConnection.startTransaction();
			IFDAOAgency daoAgency = new DAOAgency();
			daoAgency.insert(agency);
			DBConnection.commitTransaction();
		} catch (Exception e) {
			System.out.println("Error while creating agency in CustomersCtr");
			DBConnection.rollbackTransaction();
			e.getMessage();
			e.printStackTrace();
		}
	}

	// returns an agency, given the ID
	public Agency getAgency(int agencyID) {
		IFDAOAgency daoAgency = new DAOAgency();
		return daoAgency.getAgency(agencyID, false);
	}

	// returns all agencies
	public LinkedList<Agency> getAllAgencies() {
		IFDAOAgency daoAgency = new DAOAgency();
		LinkedList<Agency> allAgencies = new LinkedList<Agency>();
		allAgencies = daoAgency.getAllAgencies(false);
		return allAgencies;
	}

	// updates an agency
	public int updateAgency(int id, String name, int discountLevel,
			LinkedList<Customer> customers) {
		IFDAOAgency daoAgency = new DAOAgency();
		Agency agency = new Agency();
		agency.setID(id);
		agency.setName(name);
		agency.setAgencyDiscountLevel(discountLevel);
		agency.setProvidedCustomers(customers);
		return daoAgency.update(agency);
	}

	// deletes an agency
	public int deleteAgency(int agencyID) {
		IFDAOAgency daoAgency = new DAOAgency();
		return daoAgency.delete(agencyID);
	}

	// adds a new customer to the database
	public void newCustomer(String CPR, String fname, String lname,
			String country, String ZIP, String address, String email,
			String password) {
		Customer customer = new Customer();
		customer.getPersonID();
		customer.setCPR(CPR);
		customer.setFname(fname);
		customer.setLname(lname);
		customer.setCountry(country);
		customer.setZIP(ZIP);
		customer.setAddress(address);
		customer.setEmail(email);
		customer.setPassword(password);
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		Date date = new Date();
		String dat = sdf.format(date);
		customer.setRegistrationDate(dat);
		customer.setNoOfStays(0);
		ActivitiesCtr actCtr = new ActivitiesCtr();
		try {
			DBConnection.startTransaction();
			IFDAOCustomer daoCustomer = new DAOCustomer();
			daoCustomer.insert(customer);
			ArrayList<Customer> customeris = daoCustomer.getAllCustomers(true);
			customer = customeris.get(customeris.size()-1);			
			DBConnection.commitTransaction();
			ArrayList<ActivityBooking> bookings = actCtr.getAllBookings();
			for(ActivityBooking booking: bookings){
				if(booking.getActivity().getActivityType().name().equals("Golf")||booking.getActivity().getActivityType().name().equals("Swimming")){
					ArrayList<Customer> customers = booking.getCustomers();
							customers.add(customer);
							booking.setCustomers(customers);
							actCtr.updateBooking(booking.getID(), booking.getCustomers(), booking.getActivity(), booking.getActivityTime(), booking.isOpenActivity(), booking.isInstructorHired());
				}
					
			}
		} catch (Exception e) {
			System.out.println("Error while creating customer in CustomersCtr");
			DBConnection.rollbackTransaction();
			e.getMessage();
			e.printStackTrace();
		}
	}

	// returns a customer, given the ID
	public Customer getCustomer(int customerID) {
		IFDAOCustomer daoCustomer = new DAOCustomer();
		return daoCustomer.getCustomer(customerID, false);
	}

	// returns all the customers from the database
	public ArrayList<Customer> getAllCustomers() {
		IFDAOCustomer daoCustomer = new DAOCustomer();
		ArrayList<Customer> allCustomers = new ArrayList<Customer>();
		allCustomers = daoCustomer.getAllCustomers(false);
		return allCustomers;
	}

	// updates the details of a specific customer, given the ID
	public int updateCustomer(int id, String CPR, String fname, String lname,
			String country, String ZIP, String address, String email,
			String password, String registrationDate, int noOfStays) {
		IFDAOCustomer daoCustomer = new DAOCustomer();
		Customer customer = new Customer();
		customer.setPersonID(id);
		customer.setCPR(CPR);
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

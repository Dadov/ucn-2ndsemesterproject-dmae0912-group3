package DAO;

import java.util.ArrayList;

import Models.Customer;

public interface IFDAOCustomer {

	public Customer getCustomer(int ID, boolean retrieveAssociation);

	public ArrayList<Customer> getAllCustomers(boolean retrieveAssociation);

	public int insert(Customer Customer);

	public int update(Customer Customer);

	public int delete(int ID);
}

package DAO;

import java.util.ArrayList;

import Models.Customer;

public interface IFDAOCustomer {

	public Customer getCustomer(int ID);

	public ArrayList<Customer> getAllCustomers();

	public int insert(Customer Customer);

	public int update(Customer Customer);

	public int delete(int ID);
}

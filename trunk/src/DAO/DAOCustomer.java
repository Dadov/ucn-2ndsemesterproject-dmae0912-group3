package DAO;

import java.sql.ResultSet;
import java.util.ArrayList;

import Models.Customer;

public class DAOCustomer implements IFDAOCustomer {

	public DAOCustomer() {
		// TODO con = DBConnection.getInstance().getDBCon();
	}

	@Override
	public Customer getCustomer(int ID, boolean retrieveAssociation) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Customer> getAllCustomers(boolean retrieveAssociation) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int insert(Customer Customer) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(Customer Customer) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(int ID) {
		// TODO Auto-generated method stub
		return 0;
	}

	@SuppressWarnings("unused")
	private Customer singleWhere(String wClause, boolean retrieveAssociation) {
		// TODO
		return null;
	}

	@SuppressWarnings("unused")
	private ArrayList<Customer> miscWhere(String wClause,
			boolean retrieveAssociation) {
		// TODO
		return null;
	}

	@SuppressWarnings("unused")
	private Customer buildCustomer(ResultSet results) {
		// TODO
		return null;
	}

	@SuppressWarnings("unused")
	private String buildQuery(String wClause) {
		// TODO
		return "";
	}

}

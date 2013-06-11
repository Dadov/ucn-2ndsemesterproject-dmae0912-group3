package DAO;

import java.sql.*;
import java.util.LinkedList;
import Models.*;

public class DAOAgency implements IFDAOAgency {

	private Connection con; // connection to the database
	
	// creates a new instance of DAOAgency
	public DAOAgency() {
		con = DBConnection.getInstance().getDBCon();
	}

	// gets one agency, given the ID 
	@Override
	public Agency getAgency(int ID, boolean retrieveAssociation) {
		String wClause = " agencyID = " + ID;
		return singleWhere(wClause, retrieveAssociation);
	}

	// gets all agencies
	@Override
	public LinkedList<Agency> getAllAgencies(boolean retrieveAssociation) {
		return miscWhere("", retrieveAssociation);
	}

	// inserts a new agency
	@Override
	public int insert(Agency agency) {
		// row count is set to -1
		int rc = -1; 
		// creates query 
		String query = "SET DATEFORMAT dmy;" +
				"INSERT INTO Agency(name, discountLevel) VALUES ('" +
				agency.getName() + "', " +
				agency.getAgencyDiscountLevel() + ");";
		System.out.println("Insert query : " + query);
		// creates statement and executes query
		try {
			Statement stmt = con.createStatement();
			stmt.setQueryTimeout(5);
			rc = stmt.executeUpdate(query);
			stmt.close();
			
		}
		catch(Exception e) {
			System.out.println("Agency was not inserted into the database");
			e.getMessage();
		}
		insertCustomers(agency.getProvidedCustomers(),-1);
		return rc;
	}
	
	// inserts customers in the ProvidedCustomers table
	public int insertCustomers(LinkedList<Customer> customers, int ID) {
		int rc = -1;
		if (ID == -1) {
			ID = getLastInsertedID();
		}
		String query = "SET DATEFORMAT dmy;";
		for (Customer customer : customers) {
			query = query + "INSERT INTO ProvidedCustomers(agencyID, customerID) VALUES(" +
			ID + ", " +
			customer.getPersonID() + ");";
		}
		try {
			Statement stmt = con.createStatement();
			stmt.setQueryTimeout(5);
			rc = stmt.executeUpdate(query);
			stmt.close();
		}
		catch (SQLException e) {
			System.out.println("Customers were not inserted into ProvidedCustomers");
		}
		return rc;
		
	}

	// updates an agency
	@Override
	public int update(Agency agency) {
		// row count set to -1
		int rc = -1;
		deleteCustomers(agency.getID());
		// creates query
		String query = "SET DATEFORMAT dmy;" +
				"UPDATE Agency SET " +
				"name = '" + agency.getName() + "', " +
				"discountLevel = " + agency.getAgencyDiscountLevel() + " " +
				"WHERE agencyID = " + agency.getID() + ";";
		System.out.println("Update query : " + query);
		// creates statement and executes query
		try {
			Statement stmt = con.createStatement();
			stmt.setQueryTimeout(5);
			rc = stmt.executeUpdate(query);
			stmt.close();
		}
		catch (Exception e) {
			System.out.println("Agency update failed.");
			e.getMessage();
		}
		insertCustomers(agency.getProvidedCustomers(), agency.getID());
		return rc;
	}

	// deletes an agency 
	@Override
	public int delete(int ID) {
		// row count set to -1
		int rc = -1;
		// creates query
		String query = "DELETE FROM Agency WHERE agencyID = " + ID + ";";
		System.out.println("Delete query : " + query);
		// creates statement and executes query
		try {
			Statement stmt = con.createStatement();
			stmt.setQueryTimeout(5);
			rc = stmt.executeUpdate(query);
			stmt.close();
		}
		catch (Exception e) {
			System.out.println("Agency was not deleted");
			e.getMessage();
		}
		return rc;
	}

	// deletes all the customers from ProvidedCustomers, given the agencyID
	public int deleteCustomers(int ID) {
		int rc = -1;
		String query = "DELETE FROM ProvidedCustomers WHERE agencyID = " + ID + ";";
		System.out.println("Delete query : " + query);
		try {
			Statement stmt = con.createStatement();
			stmt.setQueryTimeout(5);
			rc = stmt.executeUpdate(query);
			stmt.close();
		}
		catch (Exception e) {
			System.out.println("Failed to delete provided customers");
			e.getMessage();
		}
		return rc;
	}
	
	// used when only one agency is to be selected
	private Agency singleWhere(String wClause, boolean retrieveAssociation) {
		ResultSet results;
		Agency agency = new Agency();
		String query = buildQuery(wClause);
		System.out.println(query);
		// reads the agency from the database
		try {
			Statement stmt = con.createStatement();
			stmt.setQueryTimeout(5);
			results = stmt.executeQuery(query);
			if (results.next()) { // check whether there are any agencies in the database
				agency = buildAgency(results);
				//inserts provided customers into Agency object
				agency.setProvidedCustomers(getProvidedCustomers(wClause, false));
				stmt.close();
				if (retrieveAssociation) {
					// no association to be retrieved, so retrieveAssociation should be false
					// if retrieveAssociation is true, an IllegalArgumentException is thrown
					throw new IllegalArgumentException("No association to be retrived in Agency table");
				}
			}
			else { // no agency found
				agency = null;	
			}
		}
		catch (Exception e) {
			System.out.println("Query exception: " + e);
			e.printStackTrace();
		}
		return agency;
	}

	// used when more than one agency is to be selected
	private LinkedList<Agency> miscWhere(String wClause,
			boolean retrieveAssociation) {
		ResultSet results;
		LinkedList<Agency> list = new LinkedList<Agency>();
		String query = buildQuery(wClause);
		// reads the agency from the database
		try {
			Statement stmt = con.createStatement();
			stmt.setQueryTimeout(5);
			results = stmt.executeQuery(query);
			while (results.next()) {
				Agency agency = new Agency();
				agency = buildAgency(results);
				//inserts provided customers into Agency object
				wClause = "AgencyID = " + agency.getID();
				agency.setProvidedCustomers(getProvidedCustomers(wClause,false));
				list.add(agency);
			}
			stmt.close();
			if (retrieveAssociation) {
				// no association to be retrieved, so retrieveAssociation should be false
				// if retrieveAssociation is true, an IllegalArgumentException is thrown
				throw new IllegalArgumentException ("No association to be retrieved in Agency table");
			}
		}
		catch (Exception e) {
			System.out.println("Query exception : " + e);
			e.printStackTrace();
		}
		return list;
	}
	
	// returns a list of the customers provided by an agency
	private LinkedList<Customer> getProvidedCustomers(String wClause,
			boolean retrieveAssociation) {
		ResultSet results;
		String query = buildCustomersQuery(wClause);
		LinkedList<Customer> providedCustomers = new LinkedList<Customer>();
		Customer customer = new Customer();
		IFDAOCustomer daoCustomer = new DAOCustomer();
		try {
			Statement stmt = con.createStatement();
			stmt.setQueryTimeout(5);
			System.out.println("looking for this: " + query);
			results = stmt.executeQuery(query);
			while(results.next()) {
				customer = daoCustomer.getCustomer(results.getInt("customerID"),false);
				providedCustomers.add(customer);
			}
			stmt.close();
		}
		catch (Exception e) {
			System.out.println("Query exception : " + e);
			e.printStackTrace();
		}
		return providedCustomers;
	}

	// builds an Agency object
	private Agency buildAgency(ResultSet results) {
		Agency agency = new Agency();
		LinkedList<Customer> customers = new LinkedList<Customer>();
		agency.setProvidedCustomers(customers);
		// fills the Agency object with date from the database
		try {
			agency.setID(results.getInt("agencyID"));
			agency.setName(results.getString("name"));
			agency.setAgencyDiscountLevel(results.getInt("discountLevel"));
		}
		catch (Exception e) {
			System.out.println("Error in building the Agency object");
		}
		return agency;
	}

	// builds a query for retrieving information from the Agency table
	private String buildQuery(String wClause) {
		String query = "SET DATEFORMAT dmy;" + "SELECT * FROM Agency";
		if (wClause.length() > 0)
			query = query + " WHERE " + wClause;
		return query;
	}

	// builds a query for retrieving the customers a specific agency provides
	private String buildCustomersQuery(String wClause) {
		String query = "SET DATEFORMAT dmy;" + " SELECT customerID FROM ProvidedCustomers";
		if (wClause.length() > 0)
			query = query + " WHERE " + wClause;
		return query;
	}
	
	// retrieves the last inserted ID 
	public int getLastInsertedID() {
		ResultSet results;
		String query = "SELECT IDENT_CURRENT('Agency') AS agencyID;";
		int ID = 0;
		try {
			Statement stmt = con.createStatement();
			stmt.setQueryTimeout(5);
			results = stmt.executeQuery(query);
			if (results.next()) {
				ID = results.getInt("agencyID");
			}
			stmt.close();
		}
		catch (Exception e) {
			System.out.println("Failed in getting last inserted ID");
			e.getMessage();
		}
		return ID;
	}
	
}

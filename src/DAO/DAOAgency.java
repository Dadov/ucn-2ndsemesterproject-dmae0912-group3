package DAO;

import java.sql.*;
import java.util.ArrayList;
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
	public ArrayList<Agency> getAllAgencies(boolean retrieveAssociation) {
		return miscWhere("", retrieveAssociation);
	}

	// inserts a new agency
	@Override
	public int insert(Agency agency) {
		// row count is set to -1
		int rc = -1; 
		// creates query 
		String query = "SET DATEFORMAT dmy;" +
				"INSERT INTO Agency(agencyID, name, discountLevel) VALUES(" +
				agency.getID() + ",'" +
				agency.getName() + "'," +
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
			System.out.println("Agency was not inserted in the database");
			e.getMessage();
		}
		return rc;
	}

	// updates an agency
	@Override
	public int update(Agency agency) {
		// row count set to -1
		int rc = -1;
		// creates query
		String query = "SET DATEFORMAT dmy;" +
				"UPDATE Agency SET " +
				"name = '" + agency.getName() + "'," +
				"discountLevel = " + agency.getAgencyDiscountLevel() + 
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

	// used when only one agency is to be selected
	@SuppressWarnings("unused")
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
				stmt.close();
				if (retrieveAssociation) {
					// TODO: IFDAOProvidedCustomers, DAOProvidedCustomers
					//IFDAOProvidedCustomers daoProvidedCustomers = new DAOProvidedCustomers();
					//int agencyID = agency.getID();
					//ArrayList<Customer> customers = daoProvidedCustomers.getAllProvidedCustomers(agencyID,false);
					//agency.setProvidedCustomers(customers);
				}
			
			}
			else { // no agency found
				agency = null;	
			}
		}
		catch (Exception e) {
			System.out.println("Query exception: " + e);
		}
		return agency;
	}

	// used when more than one agency is to be selected
	@SuppressWarnings("unused")
	private ArrayList<Agency> miscWhere(String wClause, boolean retrieveAssociation) {
		ResultSet results;
		ArrayList<Agency> list = new ArrayList<Agency>();
		String query = buildQuery(wClause);
		// reads the agency from the database
		try {
			Statement stmt = con.createStatement();
			stmt.setQueryTimeout(5);
			results = stmt.executeQuery(query);
			while (results.next()) {
				Agency agency = new Agency();
				agency = buildAgency(results);
				list.add(agency);
			}
			stmt.close();
			if (retrieveAssociation) {
				for (Agency agency : list) {
					// TODO: IFDAOProvidedCustomers, DAOProvidedCustomers
					//IFDAOProvidedCustomers daoProvidedCustomers = new DAOProvidedCustomers();
					//int agencyID = agency.getID();
					//ArrayList<Customer> customers = daoProvidedCustomers.getAllProvidedCustomers(agencyID,false);
					//agency.setProvidedCustomers(customers);
				}
			}
		}
		catch (Exception e) {
			System.out.println("Query exception : " + e);
			e.printStackTrace();
		}
		return list;
	}

	// builds an Agency object
	@SuppressWarnings("unused")
	private Agency buildAgency(ResultSet results) {
		Agency agency = new Agency();
		ArrayList<Customer> customers = new ArrayList<Customer>();
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

	// builds a query
	@SuppressWarnings("unused")
	private String buildQuery(String wClause) {
		String query = "SELECT * FROM Agency";
		if (wClause.length() > 0)
			query = query + " WHERE " + wClause;
		return query;
	}

}

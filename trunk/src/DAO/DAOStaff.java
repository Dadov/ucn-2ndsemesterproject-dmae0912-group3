package DAO;

import java.sql.*;
import java.util.ArrayList;
import Models.Staff;

public class DAOStaff implements IFDAOStaff {

	private Connection con; // connection to the database
	
	// creates a new instance of DAOStaff
	public DAOStaff() {
		con = DBConnection.getInstance().getDBCon();
	}

	// gets one member of staff, given the ID 
	@Override
	public Staff getStaff(int ID, boolean retrieveAssociation) {
		String wClause = " staffID = '" + ID + "'";
		return singleWhere(wClause, retrieveAssociation);
	}

	// gets all members of staff
	@Override
	public ArrayList<Staff> getAllStaff(boolean retrieveAssociation) {
		return miscWhere("", retrieveAssociation);
	}

	// inserts a new member of staff
	@Override
	public int insert(Staff staff) {
		// row count is set to -1
		int rc = -1; 
		// creates query 
		String query = "SET DATEFORMAT dmy;" +
				"INSERT INTO Staff(staffID, salary) VALUES(" +
				staff.getPersonID() + "," +
				staff.getSalary() + ");";
		System.out.println("Insert query : " + query);
		// creates statement and executes query
		try {
			Statement stmt = con.createStatement();
			stmt.setQueryTimeout(5);
			rc = stmt.executeUpdate(query);
			stmt.close();
			
		}
		catch(Exception e) {
			System.out.println("Staff member was not inserted in the database");
			e.getMessage();
		}
		return rc;
	}

	// updates a member of staff
	@Override
	public int update(Staff staff) {
		// row count set to -1
		int rc = -1;
		// creates query
		String query = "SET DATEFORMAT dmy;" +
				"UPDATE Staff SET " +
				"salary = " + staff.getSalary() + 
				"WHERE staffID = " + staff.getPersonID() + ";";
		System.out.println("Update query : " + query);
		// creates statement and executes query
		try {
			Statement stmt = con.createStatement();
			stmt.setQueryTimeout(5);
			rc = stmt.executeUpdate(query);
			stmt.close();
		}
		catch (Exception e) {
			System.out.println("Staff update failed.");
			e.getMessage();
		}
		return rc;
	}

	// deletes one member of staff
	@Override
	public int delete(int ID) {
		// row count set to -1
		int rc = -1;
		// creates query
		String query = "DELETE FROM Staff WHERE staffID = " + ID + ";";
		System.out.println("Delete query : " + query);
		// creates statement and executes query
		try {
			Statement stmt = con.createStatement();
			stmt.setQueryTimeout(5);
			rc = stmt.executeUpdate(query);
			stmt.close();
		}
		catch (Exception e) {
			System.out.println("Staff member was not deleted");
			e.getMessage();
		}
		return rc;
	}

	// used when only one staff member is to be selected
	@SuppressWarnings("unused")
	private Staff singleWhere(String wClause, boolean retrieveAssociation) {
		ResultSet results;
		Staff staff = new Staff();
		String query = buildQuery(wClause);
		System.out.println(query);
		// reads the staff member from the database
		try {
			Statement stmt = con.createStatement();
			stmt.setQueryTimeout(5);
			results = stmt.executeQuery(query);
			if (results.next()) { // check whether there are any staff members in the database
				staff = buildStaff(results);
				stmt.close();
				if (retrieveAssociation) {
					// no associations
				}
			
			}
			else { // no staff member found
				staff = null;	
			}
		}
		catch (Exception e) {
			System.out.println("Query exception: "+e);
		}
		return staff;
	}

	// used when more than one staff member is to be selected
	@SuppressWarnings("unused")
	private ArrayList<Staff> miscWhere(String wClause, boolean retrieveAssociation) {
		ResultSet results;
		ArrayList<Staff> list = new ArrayList<Staff>();
		String query = buildQuery(wClause);
		// reads the staff from the database
		try {
			Statement stmt = con.createStatement();
			stmt.setQueryTimeout(5);
			results = stmt.executeQuery(query);
			while (results.next()) {
				Staff staff = new Staff();
				staff = buildStaff(results);
				list.add(staff);
			}
			stmt.close();
			if (retrieveAssociation) {
				for (Staff staff : list) {
					// no associations
				}
			}
		}
		catch (Exception e) {
			System.out.println("Query exception : " + e);
			e.printStackTrace();
		}
		return list;
	}

	// builds a staff member
	@SuppressWarnings("unused")
	private Staff buildStaff(ResultSet results) {
		Staff staff = new Staff();
		// fills the Agency object with date from the database
		try {
			staff.setPersonID(results.getInt("staffID"));
			staff.setSalary(results.getFloat("salary"));
		}
		catch (Exception e) {
			System.out.println("Error in building the Staff member");
		}
		return staff;
	}

	// builds a query
	@SuppressWarnings("unused")
	private String buildQuery(String wClause) {
		String query = "SELECT * FROM Staff";
		if (wClause.length() > 0)
			query = query + " WHERE " + wClause;
		return query;
	}

}

package DAO;

import java.sql.*;
import java.util.ArrayList;
import Models.*;

public class DAOStaff implements IFDAOStaff {

	private Connection con; // connection to the database
	
	// creates a new instance of DAOStaff
	public DAOStaff() {
		con = DBConnection.getInstance().getDBCon();
	}

	// gets one member of staff, given the ID 
	@Override
	public Staff getStaff(int ID, boolean retrieveAssociation) {
		String wClause = " staffID = " + ID + "";
		return singleWhere(wClause, retrieveAssociation, ID);
	}

	// gets all members of staff
	@Override
	public ArrayList<Staff> getAllStaff(boolean retrieveAssociation) {
		return miscWhere("", retrieveAssociation);
	}

	// inserts a new member of staff
	@Override
	public int insert(Staff staff, String staffType) {
		// row count is set to -1
		int rc = -1;
		//DAOPerson object needed to be able to insert data in Person table first
		IFDAOPerson daoPerson = new DAOPerson();
		daoPerson.insert(staff, "Staff");
		
		// creates query for inserting data in Staff table
		String query = "SET DATEFORMAT dmy; " +
				"INSERT INTO Staff(staffID, salary, staffType) VALUES (" +
				"(SELECT IDENT_CURRENT('Person')), " +
				staff.getSalary() + ", '" +
				staffType + "');"; 
		//System.out.println("Insert query : " + query);
		// creates statement and executes query
		try {
			Statement stmt = con.createStatement();
			stmt.setQueryTimeout(5);
			rc = stmt.executeUpdate(query);
			stmt.close();
			
		}
		catch(SQLException e) {
			System.out.println("Staff member was not inserted into the database");
			e.getMessage();
		}
		return rc;
	}

	// updates a member of staff
	@Override
	public int update(Staff staff, String staffType) {
		// row count set to -1
		int rc = -1;
		//DAOPerson object needed to be able to update data in Person table first
		IFDAOPerson daoPerson = new DAOPerson();
		daoPerson.update(staff, "Staff");
		
		// creates query for updating data in Staff table
		String query = "SET DATEFORMAT dmy; " +
				"UPDATE Staff SET " +
				"salary = " + staff.getSalary() + ", " +
				"staffType + '" + staffType + "', " +
				"WHERE staffID = " + staff.getPersonID() + ";";
		//System.out.println("Update query : " + query);
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
		//DAOPerson object needed to be able to delete data from Person table first
		IFDAOPerson daoPerson = new DAOPerson();
		daoPerson.delete(ID);
		
		// creates query for deleting data from Staff table
		String query = "DELETE FROM Staff WHERE staffID = " + ID + ";";
		//System.out.println("Delete query : " + query);
		// creates statement and executes query
		try {
			Statement stmt = con.createStatement();
			stmt.setQueryTimeout(5);
			rc = stmt.executeUpdate(query);
			stmt.close();
		}
		catch (Exception e) {
			System.out.println("Staff member was not deleted from the database");
			e.getMessage();
		}
		return rc;
	}

	// used when only one staff member is to be selected)
	private Staff singleWhere(String wClause, boolean retrieveAssociation, int ID) {
		ResultSet results;
		Staff staff = new Staff();
		String query = buildQuery(wClause);
		IFDAOPerson daoPerson = new DAOPerson();
		// reads the staff member from the database
		try {
			Statement stmt = con.createStatement();
			stmt.setQueryTimeout(5);
			results = stmt.executeQuery(query);
			if (results.next()) { // check whether there are any staff members in the database
				staff = (Staff) daoPerson.getPerson(ID, false);
				staff = buildStaff(results,staff);
				stmt.close();
				if (retrieveAssociation) {
					// no associations
					throw new IllegalArgumentException(
							"No association to be retrieved from Staff table");
				}
			
			}
			else { // no staff member found
				staff = null;	
			}
		}
		catch (SQLException e) {
			System.out.println("Query exception in singleWhere(DAOStaff): " + e);
		}
		return staff;
	}

	// used when more than one staff member is to be selected
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
				IFDAOPerson dbp = new DAOPerson();
				int ID = results.getInt("staffID");
				staff = (Staff) dbp.getPerson(ID, false);
				staff = buildStaff(results, staff);
				if (retrieveAssociation) {
					// no associations
					throw new IllegalArgumentException("There is no association to be retrieved from Staff table");
				}
				list.add((Staff) staff);
			}
			stmt.close();
		}
		catch (SQLException e) {
			System.out.println("Query exception in miscWhere (DAOStAff): " + e);
			e.printStackTrace();
		}
		return list;
	}

	// builds a staff member
	private Staff buildStaff(ResultSet results, Staff staff) {
		String position = null;
		
		try{
			position = results.getString("staffType");
		}
		catch (Exception e){
			System.out.println("Error in getting staffType");
		}
		switch(position){
		case("Instructor"): staff = new Instructor(staff);
							break;
		case("Manager"): staff = new Manager(staff);
							break;
		case("Receptionist"): staff = new Receptionist(staff);
								break;
		case("Secretary"):	staff = new Secretary(staff);
							break;
		}

		
		//@SuppressWarnings("rawtypes")
		//Class cls = staff.getClass();
		// fills the Agency object with results from the database
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
	private String buildQuery(String wClause) {
		String query = "SET DATEFORMAT dmy;" + "SELECT * FROM Staff ";
		if (wClause.length() > 0)
			query = query + " WHERE " + wClause;
		return query;
	}

}

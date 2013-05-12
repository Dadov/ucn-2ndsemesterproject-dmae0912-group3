package DAO;

import java.sql.*;
import java.util.ArrayList;
import Models.*;

public class DAOActivityBooking implements IFDAOActivityBooking {
	
	private Connection con; // connection to the database
	
	// creates a new instance of DAOActivityBooking
	public DAOActivityBooking() {
		con = DBConnection.getInstance().getDBCon();
	}
	
	// gets one activity booking given the ID
	@Override
	public ActivityBooking getActivityBookig(int ID, boolean retrieveAssociation) {
		String wClause = " activityBookingID = " + ID;
		return singleWhere(wClause, retrieveAssociation);
	}
	
	// gets all activity bookings
	@Override
	public ArrayList<ActivityBooking> getAllActivityBookings(boolean retrieveAssociation) {
		return miscWhere("", retrieveAssociation);
	}
	
	// inserts a new activity booking
	@Override
	public int insert(ActivityBooking activityBooking) {
		// row count is set to -1
		int rc = -1;
		// creates query
		String query = "SET DATEFORMAT dmy;" +
				"INSERT INTO ActivityBooking(activityBookingID, activityID, activityDate, activityTime, openActivity, instructorHired) VALUES(" +
				activityBooking.getId() + "," +
				activityBooking.getActivity().getID() + "," +
				activityBooking.isOpenActivity() + "," +
				activityBooking.isInstructorHired() + ")";
				
		System.out.println("Insert query : " + query);
		// creates statement and executes query
		try {
			Statement stmt = con.createStatement();
			stmt.setQueryTimeout(5);
			rc = stmt.executeUpdate(query);
			stmt.close();
		}
		catch(Exception e) {
			System.out.println("ActivityBooking was not inserted into the database");
			e.getMessage();
		}
		return rc;
	}
	
	// inserts customers in the ActivityCustomers table
	public int insertCustomers(ArrayList<Customer> customers, int id) {
		int rc = -1;
		if (id == -1)
			id = getLastInsertedID();
		String query = "SET DATEFORMAT dmy;";
		for (Customer customer : customers) {
			query = query + "INSERT INTO ActivityCustomers(activityBookingID, customerID) VALUES(" +
			id + "," +
			customer.getPersonID() + ");";
		}
		try {
			Statement stmt = con.createStatement();
			stmt.setQueryTimeout(5);
			rc = stmt.executeUpdate(query);
			stmt.close();
		}
		catch (SQLException e) {
			System.out.println("Customer was not inserted into ActivityCustomers");
		}
		return rc;
	}
	
	// updates an activity booking
	@Override
	public int update(ActivityBooking activityBooking) {
		// row count set to -1
		int rc = -1;
		// creates query
		String query = "SET DATEFORMAT dmy;" +
				"UPDATE ActivityBooking SET " +
				activityBooking.getId() + "," +
				activityBooking.getActivity().getID() + "," +
				activityBooking.isOpenActivity() + "," +
				activityBooking.isInstructorHired() + ")" +
				"WHERE activityBookingID = " + activityBooking.getId() + ";";
		System.out.println("Update query : " + query);
		// creates statement and executes query
		try {
			Statement stmt = con.createStatement();
			stmt.setQueryTimeout(5);
			rc = stmt.executeUpdate(query);
			stmt.close();
		}
		catch (Exception e) {
			System.out.println("ActivityBooking update failed.");
			e.getMessage();
		}
		return rc;
	}
	
	// deletes an activity booking
	@Override
	public int delete(int ID) {
		// row count set to -1
		int rc = -1;
		// creates query
		String query = "DELETE FROM ActivityBooking WHERE activityBookingID = " + ID + ";";
		System.out.println("Delete query : " + query);
		// creates statement and executes query
		try {
			Statement stmt = con.createStatement();
			stmt.setQueryTimeout(5);
			rc = stmt.executeUpdate(query);
			stmt.close();
		}
		catch (Exception e) {
			System.out.println("BookingActivity was not deleted");
			e.getMessage();
		}
		return rc;
	}
	
	//deletes all the customers from ActivityCustomers given the activityBookingID
	public int deleteCustomers(int id) {
		int rc = -1;
		String query = "DELETE FROM ActivityCustomers WHERE activityBookingID = " + id + ";";
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
	@SuppressWarnings("unused")
	private ActivityBooking singleWhere(String wClause, boolean retrieveAssociation) {
		ResultSet results;
		ActivityBooking activityBooking = new ActivityBooking();
		String query = buildQuery(wClause);
		System.out.println(query);
		// reads the agency from the database
		try {
			Statement stmt = con.createStatement();
			stmt.setQueryTimeout(5);
			results = stmt.executeQuery(query);
			if (results.next()) { // check whether there are any activity bookings in the database
				activityBooking = buildActivityBooking(results);
				//inserts activity customers into ActivityBooking object
				activityBooking.setCustomers(getActivityCustomers(wClause,false));
				stmt.close();
				if (retrieveAssociation) {
					
				}
			
			}
			else { // no agency found
				activityBooking = null;	
			}
		}
		catch (Exception e) {
			System.out.println("Query exception: " + e);
		}
		return activityBooking;
	}
	
	// used when more than one booking activity is to be selected
	@SuppressWarnings("unused")
	private ArrayList<ActivityBooking> miscWhere(String wClause, boolean retrieveAssociation) {
		ResultSet results;
		ArrayList<ActivityBooking> list = new ArrayList<ActivityBooking>();
		String query = buildQuery(wClause);
		// reads the activity booking from the database
		try {
			Statement stmt = con.createStatement();
			stmt.setQueryTimeout(5);
			results = stmt.executeQuery(query);
			while (results.next()) {
				ActivityBooking activityBooking = new ActivityBooking();
				activityBooking = buildActivityBooking(results);
				//inserts activity customers into ActivityBooking object
				activityBooking.setCustomers(getActivityCustomers(wClause,false));
				list.add(activityBooking);
			}
			stmt.close();
			if (retrieveAssociation) {
				for (ActivityBooking activityBooking : list) {
					
				}
			}
		}
		catch (Exception e) {
			System.out.println("Query exception : " + e);
			e.printStackTrace();
		}
		return list;
	}
	
	// returns a list of the customers provided by an activity booking
	private ArrayList<Customer> getActivityCustomers (String wClause, boolean retrieveAssociation) {
		ResultSet results;
		String query = buildCustomersQuery(wClause);
		ArrayList<Customer> activityCustomers = new ArrayList<Customer>();
		Customer customer = new Customer();
		IFDAOCustomer daoCustomer = new DAOCustomer();
		try {
			Statement stmt = con.createStatement();
			stmt.setQueryTimeout(5);
			results = stmt.executeQuery(query);
			while(results.next()) {
				customer = daoCustomer.getCustomer(results.getInt("customerID"),false);
				activityCustomers.add(customer);
			}
			stmt.close();
		}
		catch (Exception e) {
			System.out.println("Query exception : " + e);
			e.printStackTrace();
		}
		return activityCustomers;
	}

	// builds an ActivityBooking object
	@SuppressWarnings("unused")
	private ActivityBooking buildActivityBooking(ResultSet results) {
		ActivityBooking activityBooking = new ActivityBooking();
		ArrayList<Customer> customers = new ArrayList<Customer>();
		activityBooking.setCustomers(customers);
		Activity activity = new Activity();
		activityBooking.setActivity(activity);
		// fills the ActivityBooking object with date from the database
		try {
			activityBooking.setId(results.getInt("activityBookingID"));
			activityBooking.setOpenActivity(results.getBoolean("openActivity"));
			activityBooking.setInstructorHired(results.getBoolean("instructorHired"));
		}
		catch (Exception e) {
			System.out.println("Error in building the ActvityBooking object");
		}
		return activityBooking;
	}

	// builds a query for retrieving information from the ActivityBooking table
	@SuppressWarnings("unused")
	private String buildQuery(String wClause) {
		String query = "SET DATEFORMAT dmy;" + "SELECT * FROM ActivityBooking";
		if (wClause.length() > 0)
			query = query + " WHERE " + wClause;
		return query;
	}

	// builds a query for retrieving the customers a specific activity booking provides
	private String buildCustomersQuery(String wClause) {
		String query = "SET DATEFORMAT dmy;" + " SELECT customerID FROM ActivityCustomers";
		if (wClause.length() > 0)
			query = query + "WHERE" + wClause;
		return query;
	}
		
	// retrieves the last inserted ID 
	public int getLastInsertedID() {
		ResultSet results;
		String query = "SELECT IDENT_CURRENT('ActivityBooking') AS activityBookingID;";
		int id = 0;
		try {
			Statement stmt = con.createStatement();
			stmt.setQueryTimeout(5);
			results = stmt.executeQuery(query);
			if (results.next()) {
				id = results.getInt("activityBookingID");
			}
			stmt.close();
		}
		catch (Exception e) {
			System.out.println("Failed in getting last inserted ID");
			e.getMessage();
		}
		return id;
	}
		
}

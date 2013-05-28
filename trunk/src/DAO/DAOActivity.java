package DAO;

import java.sql.*;
import java.util.ArrayList;
import Models.*;

public class DAOActivity implements IFDAOActivity {
	
	private Connection con; // connection to the database

	// creates a new instance of DAOActivity
	public DAOActivity() {
		con = DBConnection.getInstance().getDBCon();
	}

	// gets one activity, given the ID
	@Override
	public Activity getActivity(int ID, boolean retrieveAssociation) {
		String wClause = " activityID = " + ID;
		return singleWhere(wClause, retrieveAssociation);
	}

	// gets all activities
	@Override
	public ArrayList<Activity> getAllActivities(boolean retrieveAssociation) {
		return miscWhere("", retrieveAssociation);
	}
	public ArrayList<Activity> findFreeActivities(String date, String time,
			Enum<ActivityType> activityType) {

		// constructing fancy query for retrieving only free activities
		String query = " activityType = '" + activityType.name() + "' and activityID not in (" +
				"	SELECT activityID FROM ActivityBooking WHERE activityDate = '" + date + "' and activityTime = '" + time +"')";

		return miscWhere(query, true);

	}

	// inserts a new activity
	@Override
	public int insert(Activity activity) {
		// row count is set to -1
		int rc = -1;
		// creates query
		String query = "INSERT INTO Activity(activityType, capacity, instructorPrice) VALUES('" +
				activity.getActivityType() + "', " +
				activity.getCapacity() + ", " +
				activity.getInstructorPrice() +	");";
		System.out.println("Insert query : " + query);
		// creates statement and executes query
		try {
			Statement stmt = con.createStatement();
			stmt.setQueryTimeout(5);
			rc = stmt.executeUpdate(query);
			stmt.close();
			
		}
		catch(Exception e) {
			System.out.println("Activity was not inserted into the database");
			e.getMessage();
		}
		return rc;
	}
	
	// inserts instructor in the ActivityInstructors table
	public int insertInstructor(ArrayList<Instructor> instructors, int id) {
		int rc = -1;
		if (id == -1)
			id = getLastInsertedID();
		String query = "SET DATEFORMAT dmy;";
		for (Instructor instructor : instructors) {
			query = query + "INSERT INTO ActivityInstructors(activityID, instructorID) VALUES(" +
			id + "," +
			instructor.getPersonID() + ");";
		}
		try {
			Statement stmt = con.createStatement();
			stmt.setQueryTimeout(5);
			rc = stmt.executeUpdate(query);
			stmt.close();
		}
		catch (SQLException e) {
			System.out.println("Instructor was not inserted into ActivityInstructors");
		}
		return rc;
	}

	// updates an activity
	@Override
	public int update(Activity activity) {
		// row count set to -1
		int rc = -1;
		deleteInstructors(activity.getID());
		// creates query
		String query = "UPDATE ACTIVITY SET " +
				"activityType = '" + activity.getActivityType() + "', " +
				"capacity = " + activity.getCapacity() + ", " +
				"instructorPrice = " + activity.getInstructorPrice() +
				" WHERE activityID = " + activity.getID() + ";";
		System.out.println("Update query : " + query);
		// creates statement and executes query
		try {
			Statement stmt = con.createStatement();
			stmt.setQueryTimeout(5);
			rc = stmt.executeUpdate(query);
			stmt.close();
		}
		catch (Exception e) {
			System.out.println("Acivity update failed.");
			e.getMessage();
		}
		insertInstructor(activity.getActivityInstructors(), activity.getID());
		return rc;
	}

	// deletes an agency
	@Override
	public int delete(int ID) {
		// row count set to -1
		int rc = -1;
		deleteInstructors(ID);
		// creates query
		String query = "DELETE FROM ACTIVITY WHERE activityID = " + ID + ";";
		System.out.println("Delete query :" + query);
		// creates statement and executes query
		try {
			Statement stmt = con.createStatement();
			stmt.setQueryTimeout(5);
			rc = stmt.executeUpdate(query);
			stmt.close();
		}
		catch (Exception e) {
			System.out.println("Activity was not deleted");
			e.getMessage();
		}
		return rc;
	}
	
	// deletes all instructors from AcitivtyInstructors, given the activityID
	public int deleteInstructors(int id) {
		int rc = -1;
		String query = "DELETE FROM ActivityInstructors WHERE activityID = " + id + ";";
		System.out.println("Delete query : " + query);
		try {
			Statement stmt = con.createStatement();
			stmt.setQueryTimeout(5);
			rc = stmt.executeUpdate(query);
			stmt.close();
		}
		catch (Exception e) {
			System.out.println("Failed to delete provided instructors");
			e.getMessage();
		}
		return rc;
	}

	// used only when one activity is to be selected
	private Activity singleWhere(String wClause, boolean retrieveAssociation) {
		ResultSet results;
		Activity activity = new Activity();
		String query = buildQuery(wClause);
		System.out.println("singleWhere query (DAOActivity): " + query);
		// reads the activity from the database
		try {
			Statement stmt = con.createStatement();
			stmt.setQueryTimeout(5);
			results = stmt.executeQuery(query);
			if (results.next()) { // checks whether there are any activities in the database
				activity = buildActivity(results);
				// inserts provided instructors into the Activity object
				activity.setActivityInstructors(getActivityInstructors(wClause,false));
				stmt.close();
				if (retrieveAssociation) {
					// no association to be retrieved
					throw new IllegalArgumentException(
							"There is no association to be retrieved from Activity table");
				}
			}
			else { // no activity found
				activity = null;
			}
		}
		catch (Exception e) {
			System.out.println("Query exception (DAOActivity singleWhere): ");
			e.getMessage();
		}
		return activity;
	}

	//used when more than one activity is to be selected
	private ArrayList<Activity> miscWhere(String wClause, boolean retrieveAssociation) {
		ResultSet results;
		ArrayList<Activity> list = new ArrayList<Activity>();
		String query = buildQuery(wClause);
		System.out.println("miscWhere query (DAOActivity): " + query);
		// reads the activity from the database
		try {
			Statement stmt = con.createStatement();
			stmt.setQueryTimeout(5);
			results = stmt.executeQuery(query);
			while (results.next()) {
				Activity activity = new Activity();
				activity = buildActivity(results);
				// inserts provided instructors into Activity object
				activity.setActivityInstructors(getActivityInstructors(wClause,true));
				list.add(activity);
			}
			stmt.close();
			if (retrieveAssociation) {
				// no association to be retrieved
			}
		}
		catch (Exception e) {
			System.out.println("Query exception : ");
			e.printStackTrace();
			e.getMessage();
		}
		return list;
	}
	
	// returns a list of the instructors provided by the activity
	private ArrayList<Instructor> getActivityInstructors (String wClause, boolean retrieveAssociation) {
		ResultSet results;
		String query = buildInstructorsQuery(wClause);
		ArrayList<Instructor> activityInstructors = new ArrayList<Instructor>();
		Instructor instructor = new Instructor();
		IFDAOStaff daoStaff = new DAOStaff();
		try {
			Statement stmt = con.createStatement();
			stmt.setQueryTimeout(5);
			results = stmt.executeQuery(query);
			while(results.next()) {
				try{
					instructor = (Instructor) daoStaff.getStaff(results.getInt("instructorID"), false);
				}
				catch(ClassCastException cce){
				}
				activityInstructors.add(instructor);
			}
			stmt.close();
		}
		catch (Exception e) {
			System.out.println("Query exception : " + e);
			e.printStackTrace();
		}
		return activityInstructors;
	}
	
	// builds an Activity object
	private Activity buildActivity(ResultSet results) {
		Activity activity = new Activity();
		ArrayList<Instructor> instructors = new ArrayList<Instructor>();
		activity.setActivityInstructors(instructors);
		// fills the Activity object with date from the database
		try {
			activity.setID(results.getInt("activityID"));
			activity.setActivityType(ActivityType.valueOf(results.getString("activityType")));
			activity.setCapacity(results.getInt("capacity"));
			activity.setInstructorPrice(results.getInt("instructorPrice"));
		}
		catch (Exception e) {
			System.out.println("Error in building the Activity object");
		}
		return activity;
	}

	// builds a query for retrieving information from the Activity table
	private String buildQuery(String wClause) {
		String query = "SET DATEFORMAT dmy; SELECT * FROM Activity";
		if (wClause.length() > 0)
			query = query + " WHERE " + wClause;
		return query;
	}
	
	// builds a query for retrieving the instructors a specific activity provides
	private String buildInstructorsQuery(String wClause) {
		String query = "SET DATEFORMAT dmy;" + " SELECT instructorID FROM ActivityInstructors";
		if (wClause.length() > 0)
			query = query + " WHERE " + wClause;
		return query;
	}
	
	//retrieves the last inserted ID
	public int getLastInsertedID() {
		ResultSet results;
		String query = "SELECT IDENT_CURRENT('Activity') AS activityID;";
		int id = 0;
		try {
			Statement stmt = con.createStatement();
			stmt.setQueryTimeout(5);
			results = stmt.executeQuery(query);
			if (results.next()) {
				id = results.getInt("activityID");
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

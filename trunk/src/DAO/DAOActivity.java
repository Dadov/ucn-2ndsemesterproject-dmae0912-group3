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

	// inserts a new activity
	@Override
	public int insert(Activity activity) {
		// row count is set to -1
		int rc = -1;
		// creates query
		String query = "SET DATEFORMAT dmy;" +
				"INSERT INTO Activity(activityID, activityType, capacity, instructorAvailability) VALUES(" +
				activity.getID() + ",'" +
				activity.getActivityType() + "'," +
				activity.getCapacity() + ",'" +
				activity.getActivityInstructors() + "',";
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

	@Override
	public int update(Activity activity) {
		// row count set to -1
		int rc = -1;
		// creates query
		String query = "SET DATEFORMAT dmy;" +
				"UPDATE ACTIVITY SET " +
		return 0;
	}

	@Override
	public int delete(int ID) {
		// TODO Auto-generated method stub
		return 0;
	}

	@SuppressWarnings("unused")
	private Activity singleWhere(String wClause, boolean retrieveAssociation) {
		// TODO
		return null;
	}

	@SuppressWarnings("unused")
	private ArrayList<Activity> miscWhere(String wClause,
			boolean retrieveAssociation) {
		// TODO
		return null;
	}

	@SuppressWarnings("unused")
	private Activity buildActivity(ResultSet results) {
		// TODO
		return null;
	}

	@SuppressWarnings("unused")
	private String buildQuery(String wClause) {
		// TODO
		return "";
	}

}

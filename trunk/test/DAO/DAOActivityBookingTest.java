package DAO;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import Models.ActivityTime;
import Models.ActivityType;
import Models.Customer;
import Models.Activity;
import Models.ActivityBooking;
import Models.Instructor;

public class DAOActivityBookingTest {
	private static Connection con;
	private ActivityBooking activityBooking;
	private IFDAOActivityBooking daoActivityBooking;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		
	}
	
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		con = DBConnection.getInstance().getDBCon();
		con.setAutoCommit(false);
		//con.close();
	}
	
	@Before
	public void setUp() throws Exception {
		con = DBConnection.getInstance().getDBCon();
		IFDAOCustomer dbc = new DAOCustomer();
		Customer c1 = new Customer(0,"987654-3210","No","One","Boulevarden 55","9000","Aalborg","noone@nomail.dk", "shitsinked", "Instructor", "10-10-2013", 100);
		dbc.insert(c1);
		c1.setPersonID(getLastInsertedID(2));
		Customer customer = new Customer(0, "111188-0000", "Jan", "Anderson", 
				"Denmark", "9000", "Aalborg", "Enrich Vej 10", 
				"monkey@mail.com", "grant", "14-05-2013", 10);
		dbc.insert(customer);
		customer.setPersonID(getLastInsertedID(2));
		ArrayList<Customer> customers = new ArrayList<Customer>();
		customers.add(c1);
		customers.add(customer);
		IFDAOActivity daoa= new DAOActivity();
		Activity activity = new Activity(ActivityType.BadmintonCourt, 4, new ArrayList<Instructor>(), 100);
		daoa.insert(activity);
		activity.setID(getLastInsertedID(3));
		ActivityTime activityTime = new ActivityTime("2010-09-10", "15:00");
		activityBooking = new ActivityBooking(customers, activity, activityTime, false, false);
	}
	
	private int getLastInsertedID(int i) {
		ResultSet results; //the results retrieved from the database will be stored here;
		String query = null;
		switch(i){
		case 2: query = "SELECT IDENT_CURRENT('Person') AS ID;";
			break;
		case 3: query = "SELECT IDENT_CURRENT('Activity') AS ID;";
		}
		int id = 0;
		try { //creating a statement and deleting a selected RoomBooking from database;
			Statement stmt = con.createStatement();
			stmt.setQueryTimeout(5);
			results = stmt.executeQuery(query);
			if(results.next()) { //retrieves the last ID
	 			id = results.getInt("ID");
			}
			stmt.close();	
		}
		catch (Exception e) { //error, exception call;
			System.out.println("Getting of ID failed");
			e.getMessage();
		}
		return id;
	}

	@After
	public void tearDown() throws Exception {
	}
	
	@Test
	public void testDAOActivityBooking() {
		DBConnection dbCon = mock(DBConnection.class);
		when(dbCon.getDBCon()).thenReturn(con);
	}
	
	@Test
	public void testGetActivityBooking() throws SQLException {
		con = DBConnection.getInstance().getDBCon();
		con.setAutoCommit(false);
		daoActivityBooking = new DAOActivityBooking();
		try {
			daoActivityBooking.insert(activityBooking);
			int lastID = daoActivityBooking.getLastInsertedID();
			activityBooking.setID(lastID);
			ActivityBooking lastActivityBooking = daoActivityBooking.getActivityBooking(lastID, true);
			System.out.println("Last activity: " + lastActivityBooking);
			assertEquals(activityBooking.toString(), lastActivityBooking.toString());
		}
		finally {
			con.rollback();
		}
	}
	
	@Test
	public void testCRUD() throws SQLException {
		con = DBConnection.getInstance().getDBCon();
		con.setAutoCommit(false);
		daoActivityBooking = new DAOActivityBooking();
		
		try{
			//insert test
			daoActivityBooking.insert(activityBooking);
			int lastID = daoActivityBooking.getLastInsertedID();
			activityBooking.setID(lastID);
			ArrayList<ActivityBooking> activityBookings = daoActivityBooking.getAllActivityBookings(true);
			ActivityBooking lastActivityBooking = activityBookings.get(activityBookings.size()-1);
			
			//get test
			assertEquals(activityBooking.getID(), 
					lastActivityBooking.getID());
			assertEquals(activityBooking.getCustomers().get(0).toString(), 
					lastActivityBooking.getCustomers().get(0).toString());
			assertEquals(activityBooking.getActivity().toString(), 
					lastActivityBooking.getActivity().toString());
			assertEquals(activityBooking.getActivityTime().toString(), 
					lastActivityBooking.getActivityTime().toString());
			
			// update test
			ActivityTime activityTime = new ActivityTime("2007-07-12", "12:00");
			lastActivityBooking.setActivityTime(activityTime);
			lastActivityBooking.setInstructorHired(true);
			lastActivityBooking.setOpenActivity(true);
			daoActivityBooking.update(lastActivityBooking);
			assertEquals(lastActivityBooking.toString(),
					daoActivityBooking.getActivityBooking(daoActivityBooking.getLastInsertedID(), true).toString());
			
			
			
			// delete test
			daoActivityBooking.delete(lastActivityBooking.getID());
			assertNull(daoActivityBooking.getActivityBooking(lastActivityBooking.getID(), true));
			
		} 
		finally {
			con.rollback();
		}
	}

}

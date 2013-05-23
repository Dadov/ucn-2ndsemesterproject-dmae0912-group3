package DAO;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import Models.ActivityTime;
import Models.Customer;
import Models.Activity;
import Models.ActivityBooking;

public class DAOActivityBookingTest {
	private Connection con;
	private ActivityBooking activityBooking;
	private IFDAOActivityBooking daoActivityBooking;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}
	
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		
	}
	
	@Before
	public void setUp() throws Exception {
		Customer customer1 = new Customer();
		Customer customer2 = new Customer();
		ArrayList<Customer> customers = new ArrayList<Customer>();
		customers.add(customer1);
		customers.add(customer2);
		Activity activity = new Activity();
		ActivityTime activityTime = new ActivityTime("24-09-2010", "15:00");
		activityBooking = new ActivityBooking(customers, activity, activityTime, false, false);
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
			assertEquals(activityBooking, lastActivityBooking);
		}
		finally {
			con.rollback();
			con.close();
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
			assertEquals(activityBooking.getCustomers().toString(), 
					lastActivityBooking.getCustomers().toString());
			assertEquals(activityBooking.getActivity().toString(), 
					lastActivityBooking.getActivity().toString());
			assertEquals(activityBooking.getActivityTime().toString(), 
					lastActivityBooking.getActivityTime().toString());
			
			// update test
			ActivityTime activityTime = new ActivityTime("12-07-2007", "12:00");
			lastActivityBooking.setActivityTime(activityTime);
			lastActivityBooking.setInstructorHired(true);
			lastActivityBooking.setOpenActivity(true);
			daoActivityBooking.update(activityBooking);
			assertEquals(lastActivityBooking.toString(),
					daoActivityBooking.getActivityBooking(daoActivityBooking.getLastInsertedID(), true).toString());
			
			
			
			// delete test
			daoActivityBooking.delete(lastActivityBooking.getID());
			assertNull(daoActivityBooking.getActivityBooking(lastActivityBooking.getID(), true));
			
		} 
		finally {
			con.rollback();
			con.close();
		}
	}

}

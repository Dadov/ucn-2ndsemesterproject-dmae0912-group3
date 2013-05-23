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

import Models.Customer;
import Models.Activity;
import Models.ActivityType;
import Models.ActivityBooking;

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
		con.close();
	}
	
	@Before
	public void setUp() throws Exception {
		IFDAOActivity dbActivity = new DAOActivity();
		Activity activity = dbActivity.getActivity("TennisCourt", false);
		Activity activity2 = dbActivity.getActivity("BadmintonCourt", false);
		ArrayList<Activity> activitiesBooked = new ArrayList<Activity>();
		Customer customer = new Customer();
		customer.setPersonID(3);
		activitiesBooked.add(activity);
		activitiesBooked.add(activity2);
		activityBooking = new ActivityBooking();
	}
	
	@After
	public void tearDown() throws Exception {
	}
	
	@Test
	public final void testDAOActivityBooking() {
		DBConnection dbCon = mock(DBConnection.class);
		when(dbCon.getDBCon()).thenReturn(con);
	}
	
	@Test
	public final void testGetActivityBooking() throws SQLException {
		con = DBConnection.getInstance().getDBCon();
		con.setAutoCommit(false);
		daoActivityBooking = new DAOActivityBooking();
		try{
			daoActivityBooking.insert(activityBooking);
			activityBooking.setId(daoActivityBooking.getLastInsertedID());
			ActivityBooking activitybooking = daoActivityBooking.getActivityBooking(daoActivityBooking.getLastInsertedID(),false);
			assertEquals(activityBooking.toString(), activitybooking.toString());
		}
			finally{
				con.rollback();
			}
		}
	
	@Test
	public final void testCRUD() throws SQLException {
		con = DBConnection.getInstance().getDBCon();
		con.setAutoCommit(false);
		daoActivityBooking = new DAOActivityBooking();
		
		try{
			daoActivityBooking.insert(activityBooking);
			ArrayList<ActivityBooking> activityBookings = daoActivityBooking.getAllActivityBookings(false);
			ActivityBooking lastActivityBooking = activityBookings.get(activityBookings.size() -1);
			
			assertEquals(activityBooking.getId(), lastActivityBooking.getId());
			assertEquals(activityBooking.getCustomers(), lastActivityBooking.getCustomers());
			assertEquals(activityBooking.getActivity(), lastActivityBooking.getActivity());
			assertEquals(activityBooking.getActivityTime().toString(), lastActivityBooking.getActivityTime().toString());
			
			// update test
			// TODO
			
			
			// delete test
			daoActivityBooking.delete(lastActivityBooking.getId());
			assertNull(daoActivityBooking.getActivityBooking(lastActivityBooking.getId(), false));
			
		} finally {
			con.rollback();
		}
	}

}

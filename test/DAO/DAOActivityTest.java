package DAO;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
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

import Models.Activity;
import Models.ActivityType;
import Models.Instructor;

public class DAOActivityTest {
	private Connection con;
	private Activity activity;
	private IFDAOActivity daoActivity;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}
	
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}
	
	@Before
	public void setUp() throws Exception {
		Instructor instructor1 = new Instructor(5, "130423-5607", "John", "Keane", "China", 
				"8900", "King Kong", "Taifun Street", "ching@chong.com", "yesyes", 200.5);
		Instructor instructor2 = new Instructor(6, "150626-5807", "Johnny", "Kenny", "Spain", 
				"5600", "Godzilla", "Walt Street", "kong@chong.com", "nono", 150.5);
		ArrayList<Instructor> instructors = new ArrayList<Instructor>();
		instructors.add(instructor1);
		instructors.add(instructor2);
		//have to figure out this error, mind is too fucked to think
		//activity = new Activity(TennisCourt, 20, instructors);
	}
	
	@After
	public void tearDown() throws Exception {
	}
	
	@Test
	public void testDAOActvity() {
		DBConnection dbCon = mock(DBConnection.class);
		when(dbCon.getDBCon()).thenReturn(con);
	}
	
	@Test
	public void testCRUD() throws SQLException {
		con = DBConnection.getInstance().getDBCon();
		con.setAutoCommit(false);
		daoActivity = new DAOActivity();
		try {
			// insert
			daoActivity.insert(activity);
			
			ArrayList<Activity> activities = daoActivity.getAllActivities(false);
			Activity lastActivity = activities.get(activities.size()-1);
			
			//get
			assertEquals(activity.getID(), lastActivity.getID());
			assertEquals(activity.getActivityType(), lastActivity.getActivityType());
			assertEquals(activity.getCapacity(), lastActivity.getCapacity());
			assertEquals(activity.getActivityInstructors(), lastActivity.getActivityInstructors());
			
			//update test
			//error again, need to figure out this shit
			//lastActivity.setActivityType(BadmintonCourt);
			lastActivity.setCapacity(30);
			System.out.println("Update test: " + lastActivity.getID());
			daoActivity.update(lastActivity);
			assertEquals(lastActivity.toString(), 
					daoActivity.getActivity(lastActivity.getID(), false).toString());
			
			// delete test
			daoActivity.delete(lastActivity.getID());
			assertNull(daoActivity.getActivity(lastActivity.getID(), false));

		}
		finally {
			con.rollback();
			con.close();
		}
	}
}
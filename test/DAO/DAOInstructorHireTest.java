/**
 * 
 */
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

import Models.Activity;
import Models.ActivityBooking;
import Models.ActivityTime;
import Models.ActivityType;
import Models.Customer;
import Models.Instructor;
import Models.InstructorHire;

/**
 * @author David
 *
 */
public class DAOInstructorHireTest {
	private static Connection con;
	private InstructorHire instructorHire;
	private IFDAOInstructorHire daoHire;
	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		con = DBConnection.getInstance().getDBCon();
		con.setAutoCommit(false);
		//con.close();
	}

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		con = DBConnection.getInstance().getDBCon();
		con.setAutoCommit(false);
		IFDAOStaff dbs= new DAOStaff();
		IFDAOCustomer dbc = new DAOCustomer();
		Instructor instructor = new Instructor(0,"987654-3210","No","One","Boulevarden 55","9000","Aalborg","noone@nomail.dk", "shitsinked", "Instructor", 100);
		dbs.insert(instructor, "Instructor");
		instructor.setPersonID(getLastInsertedID(2));
		Customer customer = new Customer(0, "111188-0000", "Jan", "Anderson", 
				"Denmark", "9000", "Aalborg", "Enrich Vej 10", 
				"monkey@mail.com", "grant", "14-05-2013", 10);
		dbc.insert(customer);
		customer.setPersonID(getLastInsertedID(2));
		IFDAOActivity dba = new DAOActivity();
		ArrayList<Instructor> instructors = new ArrayList<Instructor>();
		instructors.add(instructor);
		Activity activity = new Activity(ActivityType.BadmintonCourt, 4, instructors , 100);
		dba.insert(activity);
		activity.setID(getLastInsertedID(3));
		ActivityTime activityTime = new ActivityTime("2013-10-10", "19:00");
		ArrayList<Customer> customers = new ArrayList<Customer>();
		customers.add(customer);
		ActivityBooking activityBooking = new ActivityBooking(1,customers,activity,activityTime,true,true);
		IFDAOActivityBooking dbab = new DAOActivityBooking();
		dbab.insert(activityBooking);
		activityBooking.setID(dbab.getLastInsertedID());
		instructorHire = new InstructorHire(customer, instructor, activityBooking, activityTime);
		
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Test method for {@link DAO.DAOInstructorHire#DAOInstructorHire()}.
	 */
	@Test
	public final void testDAOInstructorHire() {
		// making mock for DBConnection
		DBConnection dbCon = mock(DBConnection.class);
		// using Mockito static methods
		// 'when' you call DBConnection.getDBCon() 'thenReturn' value should be
		// Connection con
		when(dbCon.getDBCon()).thenReturn(con);
	}

	/**
	 * Test method for {@link DAO.DAOInstructorHire#getInstructorHire(int, boolean)}.
	 * @throws SQLException 
	 */
	@Test
	public final void testGetInstructorHire() throws SQLException {
		// group statements into transaction so we can roll back after test
		daoHire = new DAOInstructorHire();
		try{
		daoHire.insert(instructorHire);
		instructorHire.setId(getLastInsertedID(1));
		InstructorHire ih = daoHire.getInstructorHire(getLastInsertedID(1),true);
		assertEquals(instructorHire.getCustomer().toString(), ih.getCustomer().toString());
		assertEquals(instructorHire.getInstructor().toString(), ih.getInstructor().toString());
		//assertEquals(instructorHire.getActivityBooking().toString(), ih.getActivityBooking().toString());
		assertEquals(instructorHire.getActivityTime().toString(), ih.getActivityTime().toString());
	}
		finally{
			con.rollback();
		}
	}

	/**
	 * Test method for {@link DAO.DAOInstructorHire#insert(Models.InstructorHire)}.
	 * @throws SQLException 
	 */
	@Test
	public final void testCRUD() throws SQLException {
		// group statements into transaction so we can roll back after test
		daoHire = new DAOInstructorHire();

		try {
			// insert test
			daoHire.insert(instructorHire);
			// getting all rooms, and picking up last which was previously
			// inserted
			ArrayList<InstructorHire> inHires = daoHire.getInstructorHires(true);
			InstructorHire lastHire = inHires.get(inHires.size() - 1);
			// assert generated SQL statement with string containing expected
			// correct/expected SQL statement
			// have to know (write) each SQL statement expected before coding
			// DAO part

			// cannot compare by toString since we don't have Room number
			//assertEquals(instructorHire.getActivityBooking().toString(), lastHire.getActivityBooking().toString());
			assertEquals(instructorHire.getActivityTime().toString(), lastHire.getActivityTime().toString());
			assertEquals(instructorHire.getCustomer().toString(), lastHire.getCustomer().toString());
			assertEquals(instructorHire.getInstructor().toString(), lastHire.getInstructor().toString());

			// update test
			lastHire.setActivityTime(new ActivityTime("2014-09-09", "15:00"));
			System.out.println("update test: " + lastHire.getId());
			daoHire.update(lastHire);
			assertEquals(lastHire.toString(), daoHire.getInstructorHire(lastHire.getId(),true).toString());

			// delete test
			daoHire.delete(lastHire.getId());
			assertNull(daoHire.getInstructorHire(lastHire.getId(), false));

		} finally {
			con.rollback();
		}
	}
	public int getLastInsertedID(int i){
		ResultSet results; //the results retrieved from the database will be stored here;
		String query = null;
		switch(i){
		case 1: query = "SELECT IDENT_CURRENT('InstructorHire') AS ID;"; //selects the latest inserted ID as "BookingID" column
			break;
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

}

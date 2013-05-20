package Models;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class ActivityBookingTest {
	private ActivityBooking activityBooking;
	private Activity activity;
	private ActivityTime activityTime;
	private ArrayList<Customer> customers;
	private Customer cust01;
	private Customer cust02;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		activityBooking = new ActivityBooking();
		cust01 = new Customer();
		cust02 = new Customer();
		activity = new Activity();
		activityTime = new ActivityTime();

		customers = new ArrayList<Customer>();
		customers.add(cust01);
		customers.add(cust02);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testSetId() {
		activityBooking.setId(1);
		assertEquals(1, activityBooking.getId());
	}

	@Test
	public void testSetCustomers() {
		activityBooking.setCustomers(customers);
		assertEquals(customers, activityBooking.getCustomers());
	}

	@Test
	public void testSetActivity() {
		activityBooking.setActivity(activity);
		assertEquals(activity, activityBooking.getActivity());
	}

	@Test
	public void testSetActivityTime() {
		activityBooking.setActivityTime(activityTime);
		assertEquals(activityTime, activityBooking.getActivityTime());
	}

	@Test
	public void testSetOpenActivity() {
		activityBooking.setOpenActivity(true);
		assertTrue(activityBooking.isOpenActivity());
	}

	@Test
	public void testSetInstructorHired() {
		activityBooking.setInstructorHired(true);
		assertTrue(activityBooking.isInstructorHired());
	}

	@Test
	public void testToString() {
		activityBooking.setActivity(activity);
		activityBooking.setActivityTime(activityTime);
		activityBooking.setCustomers(customers);
		String expectNullValues = "ActivityBooking [id=0, customers="
				+ "[Person [personID=0, CPR=null, fname=null, lname=null, "
				+ "country=null, ZIP=null, city=null, address=null, email=null, "
				+ "password=null]Customer [registrationDate=null, noOfStays=0], "
				+ "Person [personID=0, CPR=null, fname=null, lname=null, country=null, "
				+ "ZIP=null, city=null, address=null, email=null, password=null]"
				+ "Customer [registrationDate=null, noOfStays=0]], activity=Activity "
				+ "[id=0, activityType=null, capacity=0, activityInstructors=null], "
				+ "activityTime=ActivityTime [date=null, time=null], "
				+ "openActivity=false, instructorHired=false]";
		assertEquals(expectNullValues, activityBooking.toString());
	}

}

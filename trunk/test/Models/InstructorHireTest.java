package Models;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class InstructorHireTest {
	private InstructorHire instructorHire;
	private Customer customer;
	private Instructor instructor;
	private ActivityBooking activityBooking;
	private Activity activity;
	private ActivityTime activityTime;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		instructorHire = new InstructorHire();
		customer = new Customer();
		instructor = new Instructor();
		activity = new Activity();
		activityTime = new ActivityTime();
		activityBooking = new ActivityBooking();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testSetId() {
		instructorHire.setId(1);
		assertEquals(1, instructorHire.getId());
	}

	@Test
	public void testSetCustomer() {
		instructorHire.setCustomer(customer);
		assertEquals(customer, instructorHire.getCustomer());
	}

	@Test
	public void testSetActivityBooking() {
		instructorHire.setActivityBooking(activityBooking);
		assertEquals(activityBooking, instructorHire.getActivityBooking());
	}

	@Test
	public void testSetInstructor() {
		instructorHire.setInstructor(instructor);
		assertEquals(instructor, instructorHire.getInstructor());
	}

	@Test
	public void testSetActivityTime() {
		instructorHire.setActivityTime(activityTime);
		assertEquals(activityTime, instructorHire.getActivityTime());
	}

	@Test
	public void testToString() {
		instructorHire.setCustomer(customer);
		instructorHire.setInstructor(instructor);
		instructorHire.setActivityBooking(activityBooking);
		instructorHire.setActivityTime(activityTime);
		activityBooking.setActivity(activity);
		activityBooking.setActivityTime(activityTime);
		System.out.println(instructorHire.toString());
		String expectNullValues = "InstructorHire [id=0, customer=Person "
				+ "[personID=0, CPR=null, fname=null, lname=null, country=null, "
				+ "ZIP=null, city=null, address=null, email=null, password=null]"
				+ "Customer [registrationDate=null, noOfStays=0], instructor="
				+ "Person [personID=0, CPR=null, fname=null, lname=null, country=null, "
				+ "ZIP=null, city=null, address=null, email=null, password=null]Staff "
				+ "[salary=0.0]Instructor [], activityBooking=ActivityBooking "
				+ "[id=0, customers=null, activity=Activity [id=0, activityType=null, "
				+ "capacity=0, activityInstructors=null], activityTime=ActivityTime "
				+ "[date=null, time=null], openActivity=false, instructorHired=false], "
				+ "activityTime=ActivityTime [date=null, time=null]]";
		assertEquals(expectNullValues, instructorHire.toString());
	}

}

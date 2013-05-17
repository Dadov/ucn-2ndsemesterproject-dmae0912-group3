package Models;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class StaffTest {
	private Staff staff;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		staff = new Staff();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testSetSalary() {
		staff.setSalary(50.5);
		assertEquals(50.5, staff.getSalary(), 0);
	}

	@Test
	public void testToString() {
		String expectNullValues = "Person [personID=0, CPR=null, fname=null,"
				+ " lname=null, country=null, ZIP=null, city=null, address=null,"
				+ " email=null, password=null]Staff [salary=0.0]";
		assertEquals(expectNullValues, staff.toString());
	}

}

package Models;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class CustomerTest {
	private Customer customer;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		customer = new Customer();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testSetRegistrationDate() {
		customer.setRegistrationDate("13-03-2013");
		assertEquals("13-03-2013", customer.getRegistrationDate());
	}

	@Test
	public void testSetNoOfStays() {
		customer.setNoOfStays(5);
		assertEquals(5, customer.getNoOfStays());
	}

	@Test
	public void testToString() {
		String expectNullValues = "Person [personID=0, CPR=null, fname=null,"
				+ " lname=null, country=null, ZIP=null, city=null, address=null, "
				+ "email=null, password=null]Customer [registrationDate=null, "
				+ "noOfStays=0]";
		assertEquals(expectNullValues, customer.toString());
	}

}

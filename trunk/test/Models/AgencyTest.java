package Models;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class AgencyTest {
	private Agency agency;
	private ArrayList<Customer> providedCustomers;
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
		agency = new Agency();
		cust01 = new Customer();
		cust02 = new Customer();
		providedCustomers = new ArrayList<Customer>();
		providedCustomers.add(cust01);
		providedCustomers.add(cust02);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testSetID() {
		agency.setID(0);
		assertEquals(0, agency.getID());
	}

	@Test
	public void testSetName() {
		agency.setName("Go to HELL!");
		assertEquals("Go to HELL!", agency.getName());
	}

	@Test
	public void testSetAgencyDiscountLevel() {
		agency.setAgencyDiscountLevel(3);
		assertEquals(3, agency.getAgencyDiscountLevel());
	}

	@Test
	public void testSetProvidedCustomers() {
		agency.setProvidedCustomers(providedCustomers);
		assertEquals(providedCustomers, agency.getProvidedCustomers());
	}

	@Test
	public void testToString() {
		// provided customers needs to be assigned
		agency.setProvidedCustomers(providedCustomers);
		String expectNullValues = "Agency [id=0, name=null, agencyDiscountLevel=0,"
				+ " providedCustomers=[Person [personID=0, CPR=null, fname=null,"
				+ " lname=null, country=null, ZIP=null, city=null, address=null,"
				+ " email=null, password=null]Customer [registrationDate=null,"
				+ " noOfStays=0], Person [personID=0, CPR=null, fname=null, lname=null,"
				+ " country=null, ZIP=null, city=null, address=null, email=null,"
				+ " password=null]Customer [registrationDate=null, noOfStays=0]]]";
		assertEquals(expectNullValues, agency.toString());
	}

}

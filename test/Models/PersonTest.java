package Models;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class PersonTest {
	private Person person;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		// person = mock(Person.class);
		person = new Person();
		// System.out.println(person.toString());
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testSetPersonID() {
		person.setPersonID(1);
		assertEquals(1, person.getPersonID());
	}

	@Test
	public void testSetCPR() {
		person.setCPR("101010-1234");
		assertEquals("101010-1234", person.getCPR());
	}

	@Test
	public void testSetFname() {
		person.setFname("mr.");
		assertEquals("mr.", person.getFname());
	}

	@Test
	public void testSetLname() {
		person.setLname("wonder");
		assertEquals("wonder", person.getLname());
	}

	@Test
	public void testSetCountry() {
		person.setCountry("atlantis");
		assertEquals("atlantis", person.getCountry());
	}

	@Test
	public void testSetZIP() {
		person.setZIP("0000");
		assertEquals("0000", person.getZIP());
	}

	@Test
	public void testSetAddress() {
		person.setAddress("faraway");
		assertEquals("faraway", person.getAddress());
	}

	@Test
	public void testSetEmail() {
		person.setEmail("mr.wonder@wunderbar.a");
		assertEquals("mr.wonder@wunderbar.a", person.getEmail());
	}

	@Test
	public void testSetPassword() {
		person.setPassword("udontwannaknow");
		assertEquals("udontwannaknow", person.getPassword());
	}

	@Test
	public void testToString() {
		String expectNullValues = "Person [personID=0, CPR=null, fname=null,"
				+ " lname=null, country=null, ZIP=null, address=null, email=null,"
				+ " password=null]";
		assertEquals(expectNullValues, person.toString());
	}

}

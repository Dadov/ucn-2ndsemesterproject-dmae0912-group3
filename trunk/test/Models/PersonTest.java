package Models;

import static org.junit.Assert.fail;
import static org.mockito.Mockito.mock;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class PersonTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		Person person = mock(Person.class);
		System.out.println(person.toString());
		System.out.println(person.getCPR());
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testPerson() {
		fail("Not yet implemented");
	}

	@Test
	public void testPersonIntStringStringStringStringStringStringString() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetPersonID() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetPersonID() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetCPR() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetCPR() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetFname() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetFname() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetLname() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetLname() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetCountry() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetCountry() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetZIP() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetZIP() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetAddress() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetAddress() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetEmail() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetEmail() {
		fail("Not yet implemented");
	}

	@Test
	public void testToString() {
		fail("Not yet implemented");
	}

}

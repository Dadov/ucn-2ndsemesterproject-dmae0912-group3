package Models;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class SecretaryTest {
	private Secretary secretary;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		secretary = new Secretary();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testToString() {
		System.out.println(secretary.toString());
		String expectNullValues = "Person [personID=0, CPR=null, fname=null, lname=null, country=null, ZIP=null, city=null, address=null, email=null, password=null]Staff [salary=0.0]Secretary []";
		assertEquals(expectNullValues, secretary.toString());
	}
}

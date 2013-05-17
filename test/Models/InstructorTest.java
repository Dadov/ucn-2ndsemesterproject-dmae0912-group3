package Models;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class InstructorTest {
	private Instructor instructor;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		instructor = new Instructor();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testToString() {
		String expectNullValues = "Person [personID=0, CPR=null, fname=null,"
				+ " lname=null, country=null, ZIP=null, city=null, address=null,"
				+ " email=null, password=null]Staff [salary=0.0]Instructor []";
		assertEquals(expectNullValues, instructor.toString());
	}

}

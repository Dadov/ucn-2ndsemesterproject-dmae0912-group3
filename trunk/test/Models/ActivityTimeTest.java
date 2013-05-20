package Models;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class ActivityTimeTest {
	private ActivityTime activityTime;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		activityTime = new ActivityTime();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testSetDate() {
		activityTime.setDate("13-13-2013");
		assertEquals("13-13-2013", activityTime.getDate());
	}

	@Test
	public void testSetTime() {
		activityTime.setTime("23:59");
		assertEquals("23:59", activityTime.getTime());
	}

	@Test
	public void testToString() {
		String expectNullValues = "ActivityTime [date=null, time=null]";
		assertEquals(expectNullValues, activityTime.toString());
	}

}

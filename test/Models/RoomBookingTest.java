package Models;

import static org.junit.Assert.fail;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class RoomBookingTest {
	RoomBooking roomBooking = new RoomBooking();

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void toStringTest() throws Exception {
		// ArrayList<> toString() method test, to see what does it actually do
		Room room01 = new Room(1, RoomType.Single, 8.9, "first room");
		Room room02 = new Room(2, RoomType.Family, 13.3, "second room");
		ArrayList<Room> rooms = new ArrayList<Room>();
		rooms.add(room01);
		rooms.add(room02);
		roomBooking.setRoomsBooked(rooms);
		System.out.println(roomBooking.toString());
		System.out.println(roomBooking.getRoomsBooked());
	}

	@Test
	public void mockitoVerify() throws Exception {
		Room room01 = mock(Room.class);
		room01.setPrice(9.0);
		verify(room01).setPrice(9.0);
		when(room01.getPrice()).thenReturn(9.0);
	}

	@Test
	public void test() {
		fail("Not yet implemented"); // TODO
	}

}

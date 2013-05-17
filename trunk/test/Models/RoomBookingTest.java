package Models;

import static org.junit.Assert.assertEquals;
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
	private RoomBooking roomBooking;
	private Customer customer;
	private Room room01;
	private Room room02;
	private ArrayList<Room> roomsBooked;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		roomBooking = new RoomBooking();
		customer = new Customer();
		room01 = new Room();
		room02 = new Room();
		roomsBooked = new ArrayList<Room>();
		roomsBooked.add(room01);
		roomsBooked.add(room02);

		roomBooking.setCustomer(customer);
		roomBooking.setRoomsBooked(roomsBooked);
	}

	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Used to find out behavior of toString on ArrayList<> No real use in
	 * program, keeping as reference
	 */
	@Test
	public void toStringTest() throws Exception {
		Room room01 = new Room(1, RoomType.Single, 8.9, "first room");
		Room room02 = new Room(2, RoomType.Family, 13.3, "second room");
		ArrayList<Room> rooms = new ArrayList<Room>();
		rooms.add(room01);
		rooms.add(room02);
		roomBooking.setRoomsBooked(rooms);
		System.out.println(roomBooking.toString());
		System.out.println(roomBooking.getRoomsBooked());
	}

	/**
	 * Possible Mockito use case. No real use in program, keeping for reference
	 */
	@Test
	public void mockitoVerify() throws Exception {
		Room room01 = mock(Room.class);
		room01.setPrice(9.0);
		// verify if action was performed on the mock
		verify(room01).setPrice(9.0);
		when(room01.getPrice()).thenReturn(9.0);
	}

	@Test
	public void testSetId() {
		roomBooking.setId(1);
		assertEquals(1, roomBooking.getId());
	}

	@Test
	public void testSetCustomer() {
		roomBooking.setCustomer(customer);
		assertEquals(customer, roomBooking.getCustomer());
	}

	@Test
	public void testSetRoomsBooked() {
		roomBooking.setRoomsBooked(roomsBooked);
		assertEquals(roomsBooked, roomBooking.getRoomsBooked());
	}

	@Test
	public void testSetDateBooked() {
		roomBooking.setDateBooked("13-03-2013");
		assertEquals("13-03-2013", roomBooking.getDateBooked());
	}

	@Test
	public void testSetDateStart() {
		roomBooking.setDateStart("13-03-2013");
		assertEquals("13-03-2013", roomBooking.getDateStart());
	}

	@Test
	public void testSetDateEnd() {
		roomBooking.setDateEnd("13-03-2013");
		assertEquals("13-03-2013", roomBooking.getDateEnd());
	}

	@Test
	public void testToString() {
		String expectNullValues = "RoomBooking [id=0, customer=Person"
				+ " [personID=0, CPR=null, fname=null, lname=null, country=null,"
				+ " ZIP=null, city=null, address=null, email=null, password=null]"
				+ "Customer [registrationDate=null, noOfStays=0], "
				+ "roomsBooked=[Room [number=0, roomType=null, price=0.0, note=null],"
				+ " Room [number=0, roomType=null, price=0.0, note=null]],"
				+ " dateBooked=null, dateStart=null, dateEnd=null]";
		assertEquals(expectNullValues, roomBooking.toString());
	}
}

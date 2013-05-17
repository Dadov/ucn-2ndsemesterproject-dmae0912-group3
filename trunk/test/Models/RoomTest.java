package Models;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class RoomTest {
	private Room room;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		room = new Room();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testSetNumber() {
		room.setNumber(5);
		assertEquals(5, room.getNumber());
	}

	@Test
	public void testSetRoomType() {
		room.setRoomType(RoomType.Single);
		assertEquals(RoomType.Single, room.getRoomType());
	}

	@Test
	public void testSetPrice() {
		room.setPrice(100.1);
		assertEquals(100.1, room.getPrice(), 0);
	}

	@Test
	public void testSetNote() {
		room.setNote("the note");
		assertEquals("the note", room.getNote());
	}

	@Test
	public void testToString() {
		String expectNullValues = "Room [number=0, roomType=null, price=0.0, note=null]";
		assertEquals(expectNullValues, room.toString());
	}
}

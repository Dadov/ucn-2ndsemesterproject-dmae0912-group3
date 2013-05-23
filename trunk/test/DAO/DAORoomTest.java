package DAO;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import Models.Room;
import Models.RoomType;

public class DAORoomTest {
	private Connection con;
	private Room room;
	private IFDAORoom daoRoom;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		// cannot use con.close() after each @Test unit have to do it after all
		// tests
		Connection con = DBConnection.getInstance().getDBCon();
		con.setAutoCommit(false);
		con.close();
	}

	@Before
	public void setUp() throws Exception {
		// room = mock(Room.class);

		// random test
		// System.out.println(room.toString());

		room = new Room(RoomType.Single, 78.9, "testing");
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testDAORoom() {
		// making mock for DBConnection
		DBConnection dbCon = mock(DBConnection.class);
		// using Mockito static methods
		// 'when' you call DBConnection.getDBCon() 'thenReturn' value should be
		// Connection con
		when(dbCon.getDBCon()).thenReturn(con);
	}

	// deleted testGet/Insert/Update/Delete going to be be tested in testCRUD
	@Test
	public void testCRUD() throws SQLException {
		con = DBConnection.getInstance().getDBCon();
		// group statements into transaction so we can roll back after test
		con.setAutoCommit(false);
		daoRoom = new DAORoom();

		// some testing experiments, don't delete
		// no need for mocks, using data from database
		// ArrayList<Room> rooms = daoRoom.getAllRooms(false);

		/*
		 * Iterator<Room> iter = rooms.iterator(); while (iter.hasNext()) {
		 * System.out.println(iter.next().toString()); }
		 */
		// Room lastRoom = rooms.get(rooms.size() - 1);
		// System.out.println("Last room: " + room.toString());
		// System.out.println(rooms.size());
		// System.out.println(rooms.get(102));

		try {
			// insert test
			daoRoom.insert(room);
			// getting all rooms, and picking up last which was previously
			// inserted
			ArrayList<Room> rooms = daoRoom.getAllRooms(false);
			Room lastRoom = rooms.get(rooms.size() - 1);
			// assert generated SQL statement with string containing expected
			// correct/expected SQL statement
			// have to know (write) each SQL statement expected before coding
			// DAO part

			// cannot compare by toString since we don't have Room number
			assertEquals(room.getRoomType(), lastRoom.getRoomType());
			assertEquals(room.getPrice(), lastRoom.getPrice(), 0);
			assertEquals(room.getNote(), lastRoom.getNote());
			// assertEquals(rooms.size(), lastRoom.getNumber());

			// update test
			lastRoom.setNote("update test");
			System.out.println("Update test number: " + lastRoom.getNumber());
			daoRoom.update(lastRoom);
			assertEquals(lastRoom.toString(),
					daoRoom.getRoom(lastRoom.getNumber(), false).toString());

			// delete test
			daoRoom.delete(lastRoom.getNumber());
			assertNull(daoRoom.getRoom(lastRoom.getNumber(), false));

		} finally {
			con.rollback();
			// con.close();
		}
	}

	@Test
	public void testGetAllRooms() {
		// used in CRUDTest
	}

	@Test
	public void testFindFreeRooms() throws SQLException {
		con = DBConnection.getInstance().getDBCon();
		con.setAutoCommit(false);
		daoRoom = new DAORoom();

		try {
			ArrayList<Room> rooms;
			rooms = daoRoom.findFreeRooms("13-08-2014", "24-08-2014",
					RoomType.Single);
			System.out.println(rooms.toString());

		} finally {
			con.rollback();
			// con.close();
		}
	}

}

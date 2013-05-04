package DAO;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.mock;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import Models.Room;
// TODO probably delete mockito import

public class DAORoomTest {
	private Connection con;
	private Room room;
	private IFDAORoom daoRoom;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		room = mock(Room.class);

		// fun test
		System.out.println(room.toString());
	}

	@After
	public void tearDown() throws Exception {
	}

	// not much to do here, delete
	@Test
	public void testDAORoom() {
		// TODO
		fail("Not yet implemented");
	}

	// deleted testGet/Insert/Update/Delete going to be be tested in testCRUD
	@Test
	public void testCRUD() throws SQLException {
		// nothing to test
		fail("Not yet implemented"); // TODO
		con = DBConnection.getInstance().getDBCon();
		// group statements into transaction so we can roll back after test
		con.setAutoCommit(false);
		daoRoom = new DAORoom();

		// no need for mocks, using data from database
		ArrayList<Room> rooms = daoRoom.getAllRooms();
		room = rooms.get(rooms.lastIndexOf(room));

		try {
			// not usable testing values

			daoRoom.insert(room);
			// assert generated SQL statement with string containing expected
			// correct/expected SQL statement
			// have to know (write) each SQL statement expected before coding
			// DAO part
			assertEquals(null, daoRoom.getRoom(room.getNumber()));

			daoRoom.update(null);
			assertEquals(null, daoRoom.getRoom(0));

			daoRoom.delete(0);
			assertNull(daoRoom.delete(0));

		} finally {
			con.rollback();
			con.close();
		}
	}

	@Test
	public void testGetAllRooms() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public void testFindFreeRooms() {
		fail("Not yet implemented"); // TODO
	}

}

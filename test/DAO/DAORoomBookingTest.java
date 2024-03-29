/**
 * 
 */
package DAO;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import Models.Customer;
import Models.Room;
import Models.RoomBooking;
import Models.RoomType;

/**
 * @author David
 *
 */
public class DAORoomBookingTest {
	private static Connection con;
	private RoomBooking roomBook;
	private IFDAORoomBooking daoRoomBook;
	
	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		con = DBConnection.getInstance().getDBCon();
		con.setAutoCommit(false);
		//con.close();
	}

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		con = DBConnection.getInstance().getDBCon();
		IFDAORoom dbRoom = new DAORoom();
		Room room = new Room(500,RoomType.Family,100,"test");
		Room r2 =  new Room(1000, RoomType.Single, 50, "rest");
		ArrayList<Room> roomsBooked = new ArrayList<Room>();
		Customer customer = new Customer(0, "111188-0000", "Jan", "Anderson", 
				"Denmark", "9000", "Aalborg", "Enrich Vej 10", 
				"monkey@mail.com", "grant", "14-05-2013", 10);
		IFDAOCustomer dbc = new DAOCustomer();
		dbc.insert(customer);
		customer.setPersonID(getLastID(1));
		roomsBooked.add(room);
		roomsBooked.add(r2);
		dbRoom.insert(room);
		room.setNumber(getLastID(2));
		dbRoom.insert(r2);
		r2.setNumber(getLastID(2));
		roomBook = new RoomBooking(customer , roomsBooked, "2013-10-20", "2013-10-21", "2013-10-22");
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Test method for {@link DAO.DAORoomBooking#DAORoomBooking()}.
	 */
	@Test
	public final void testDAORoomBooking() {
		// making mock for DBConnection
				DBConnection dbCon = mock(DBConnection.class);
				// using Mockito static methods
				// 'when' you call DBConnection.getDBCon() 'thenReturn' value should be
				// Connection con
				when(dbCon.getDBCon()).thenReturn(con);
	}

	/**
	 * Test method for {@link DAO.DAORoomBooking#getRoomBooking(int, boolean)}.
	 * @throws SQLException 
	 */
	@Test
	public final void testGetRoomBooking() throws SQLException {
		con = DBConnection.getInstance().getDBCon();
		con.setAutoCommit(false);
		// group statements into transaction so we can roll back after test
		daoRoomBook = new DAORoomBooking();
		try{
		daoRoomBook.insert(roomBook);
		roomBook.setId(daoRoomBook.getLastInsertedID());
		System.out.println("Room booking to insert: " + roomBook);
		RoomBooking rb = daoRoomBook.getRoomBooking(daoRoomBook.getLastInsertedID(),true);
		System.out.println("Last room booking: " + rb);
		assertEquals(roomBook.toString(), rb.toString());
		
	}
		finally{
			con.rollback();
		}
	}


	/**
	 * Test method for {@link DAO.DAORoomBooking#insert(Models.RoomBooking)}.
	 * @throws SQLException 
	 */
	@Test
	public final void testCRUD() throws SQLException {
		con = DBConnection.getInstance().getDBCon();
		con.setAutoCommit(false);
		// group statements into transaction so we can roll back after test
		daoRoomBook = new DAORoomBooking();

		try {
			// insert test
			daoRoomBook.insert(roomBook);
			// getting all rooms, and picking up last which was previously
			// inserted
			ArrayList<RoomBooking> roomBooks = daoRoomBook.getAllRoomBookings(true);
			RoomBooking lastRoomBooking = roomBooks.get(roomBooks.size() - 1);
			// assert generated SQL statement with string containing expected
			// correct/expected SQL statement
			// have to know (write) each SQL statement expected before coding
			// DAO part

			// cannot compare by toString since we don't have Room number
			assertEquals(roomBook.getCustomer().toString(), lastRoomBooking.getCustomer().toString());
			assertEquals(roomBook.getDateBooked(), lastRoomBooking.getDateBooked());
			assertEquals(roomBook.getDateEnd(), lastRoomBooking.getDateEnd());
			assertEquals(roomBook.getDateStart(), lastRoomBooking.getDateStart());
			assertEquals(roomBook.getRoomsBooked().toString(), lastRoomBooking.getRoomsBooked().toString());

			// update test
			lastRoomBooking.setDateBooked("2014-10-10");
			System.out.println("update test: " + lastRoomBooking.getId());
			daoRoomBook.update(lastRoomBooking);
			assertEquals(lastRoomBooking.toString(),
					daoRoomBook.getRoomBooking(lastRoomBooking.getId(),true).toString());

			// delete test
			daoRoomBook.delete(lastRoomBooking.getId());
			assertNull(daoRoomBook.getRoomBooking(lastRoomBooking.getId(), false));

		} finally {
			con.rollback();
		}
	}
		public int getLastID(int i){
			String query = null;
			switch (i){
			case 1: query = "SELECT IDENT_CURRENT('Person') AS ID;";
			break;
			case 2: query = "SELECT IDENT_CURRENT('Room') AS ID;";
			break;
			}
			ResultSet results;
			int id = 0;
			try{
				Statement stmt = con.createStatement();
				stmt.setQueryTimeout(5);
				results = stmt.executeQuery(query);
				if(results.next()) { //retrieves the last ID
		 			id = results.getInt("ID");
				}
				stmt.close();	
			}
			catch (Exception e) { //error, exception call;
				System.out.println("Getting of ID failed");
				e.getMessage();
			}
			return id;
			}
	}





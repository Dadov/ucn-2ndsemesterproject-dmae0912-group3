package DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import Models.Room;
import Models.RoomType;

public class DAORoom implements IFDAORoom {
	private Connection con;

	public DAORoom() {
		con = DBConnection.getInstance().getDBCon();
	}

	@Override
	public Room getRoom(int number, boolean retrieveAssociation) {
		String wClause = " number = '" + number + "'";
		return singleWhere(wClause, retrieveAssociation);
	}

	@Override
	public ArrayList<Room> getAllRooms(boolean retrieveAssociation) {
		return miscWhere("", retrieveAssociation);
	}

	// takes roomType as argument but it's commented out
	// might be a good idea to return all Rooms and filter it in GUI
	@Override
	public ArrayList<Room> findFreeRooms(String startDate, String endDate,
			Enum<RoomType> roomType) {
		// TODO not tested, yet

		// constructing fancy query for retrieving only free rooms
		String query = "SET DATEFORMAT dmy; "
				+ "SELECT * FROM Room WHERE /* roomType = 'Family' and */ number not in ("
				+ "SELECT roomNumber FROM RoomsBooked WHERE roomBookingID in ("
				+ "SELECT bookingID FROM RoomBooking WHERE dateStart >= '"
				+ startDate + "' and dateEnd <=  '" + endDate + "'));";
		System.out.println(query);

		return miscWhere(query, false);
	}

	@Override
	public int insert(Room room) {
		int rc = -1;

		String query = "INSERT INTO ROOM (roomType, price, note) VALUES ('"
				+ room.getRoomType() + "'," + room.getPrice() + ",'"
				+ room.getNote() + "');";
		System.out.println("Insert'Room' query:  " + query);

		try {
			Statement stmt = con.createStatement();
			stmt.setQueryTimeout(5);
			rc = stmt.executeUpdate(query);
			stmt.close();
		} catch (Exception e) {
			System.out.println("Error while inserting Room into database: ");
			e.getCause();
		}

		return rc;
	}

	@Override
	public int update(Room room) {
		Room roomObj = room;
		int rc = -1;

		String query = "UPDATE ROOM SET " + "roomType = '"
				+ roomObj.getRoomType() + "'," + "price = "
				+ roomObj.getPrice() + "," + "note = '" + roomObj.getNote()
				+ "' WHERE number = " + roomObj.getNumber() + ";";
		System.out.println("Update query :" + query);

		try {
			Statement stmt = con.createStatement();
			stmt.setQueryTimeout(5);
			rc = stmt.executeUpdate(query);
			stmt.close();
		} catch (Exception e) {
			System.out.println("Room was not updated");
			e.getMessage();
		}

		return rc;
	}

	@Override
	public int delete(int number) {
		int rc = -1;

		String query = "DELETE FROM Room WHERE number = " + number + ";";
		System.out.println("Delete query: " + query);

		try {
			Statement stmt = con.createStatement();
			stmt.setQueryTimeout(5);
			rc = stmt.executeUpdate(query);
			stmt.close();
		} catch (Exception e) {
			System.out.println("Error during deleting Room: ");
			e.getMessage();
		}

		return rc;
	}

	private Room singleWhere(String wClause, boolean retrieveAssociation) {
		ResultSet results;
		Room roomObj = new Room();

		String query = buildQuery(wClause);
		System.out.println(query);

		try {
			Statement stmt = con.createStatement();
			stmt.setQueryTimeout(5);
			results = stmt.executeQuery(query);

			if (results.next()) {
				roomObj = buildRoom(results);
				stmt.close();

				if (retrieveAssociation) {
					// TODO or not TODO,association with RoomBooking
					System.out
							.println("There is no association to be retreived.");
				}

			} else {
				roomObj = null;
			}

		} catch (Exception e) {
			System.out.println("Query exception: ");
			e.getMessage();
		}
		return roomObj;
	}

	private ArrayList<Room> miscWhere(String wClause,
			boolean retrieveAssociation) {
		ResultSet results;
		ArrayList<Room> list = new ArrayList<Room>();

		String query = buildQuery(wClause);

		try {
			Statement stmt = con.createStatement();
			stmt.setQueryTimeout(5);
			results = stmt.executeQuery(query);

			while (results.next()) {
				Room roomObj = new Room();
				roomObj = buildRoom(results);
				list.add(roomObj);
			}
			stmt.close();

			if (retrieveAssociation) {
				// TODO or not TODO,association with RoomBookingSystem.out
				System.out.println("There is no association to be retreived.");

			}

		} catch (Exception e) {
			System.out.println("Query exception: ");
			e.getMessage();
		}
		return list;
	}

	private Room buildRoom(ResultSet results) {
		Room roomObj = new Room();
		try {
			roomObj.setNumber(results.getInt("number"));
			roomObj.setRoomType(RoomType.valueOf(results.getString("roomType")));
			roomObj.setPrice(results.getDouble("price"));
			roomObj.setNote(results.getString("note"));
		} catch (Exception e) {
			System.out.println("Erroe in building Room object.");
			e.getMessage();
		}
		return null;
	}

	private String buildQuery(String wClause) {
		String query = "SELECT * FROM Room";

		if (wClause.length() > 0)
			query = query + wClause;
		return query;
	}

}

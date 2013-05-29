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
		String wClause = " number = " + number;
		return singleWhere(wClause, retrieveAssociation);
	}

	@Override
	public ArrayList<Room> getAllRooms(boolean retrieveAssociation) {
		return miscWhere("", retrieveAssociation);
	}

	@Override
	public ArrayList<Room> findFreeRooms(String startDate, String endDate) {
		String query = /* " roomType = '" + roomType + "' AND */" number NOT IN "
				+ "(SELECT roomNumber FROM RoomsBooked WHERE roomBookingID IN "
				+ "(SELECT bookingID FROM RoomBooking WHERE dateEnd >= '"
				+ startDate + "' and dateStart <=  '" + endDate + "'));";

		return miscWhere(query, false);
	}

	@Override
	public ArrayList<Room> findFreeRoomsOfType(String startDate,
			String endDate, Enum<RoomType> roomType) {
		String query = " roomType = '" + roomType + "' AND number NOT IN "
				+ "(SELECT roomNumber FROM RoomsBooked WHERE roomBookingID IN "
				+ "(SELECT bookingID FROM RoomBooking WHERE dateEnd >= '"
				+ startDate + "' and dateStart <=  '" + endDate + "'));";

		return miscWhere(query, false);
	}

	@Override
	public ArrayList<Room> findBookedRooms(String startDate, String endDate) {
		String query = " number IN "
				+ "(SELECT roomNumber FROM RoomsBooked WHERE roomBookingID IN "
				+ "(SELECT bookingID FROM RoomBooking WHERE dateEnd >= '"
				+ startDate + "' and dateStart <=  '" + endDate + "'));";

		return miscWhere(query, false);
	}

	@Override
	public int insert(Room room) {
		int rc = -1;

		String query = "INSERT INTO ROOM (roomType, price, note) VALUES ('"
				+ room.getRoomType() + "'," + room.getPrice() + ",'"
				+ room.getNote() + "');";
		System.out.println("Insert 'Room' query:  " + query);

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
		System.out.println("Update 'Room' query :" + query);

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
		System.out.println("Delete 'Room' query: " + query);

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
		System.out.println("singleWhere query (DAORoom): " + query);

		try {
			Statement stmt = con.createStatement();
			stmt.setQueryTimeout(5);
			results = stmt.executeQuery(query);

			if (results.next()) {
				roomObj = buildRoom(results);
				stmt.close();

				if (retrieveAssociation) {
					// there is no association to be retrieved
					// if 'true' is passed we throw an IllegalArgumentException
					throw new IllegalArgumentException(
							"There is no association to be retrieved Room table.");
				}

			} else {
				roomObj = null;
			}

		} catch (Exception e) {
			System.out.println("Query exception (DAORoom singleWhere): ");
			e.getMessage();
		}
		return roomObj;
	}

	private ArrayList<Room> miscWhere(String wClause,
			boolean retrieveAssociation) {
		ResultSet results;
		ArrayList<Room> list = new ArrayList<Room>();

		String query = buildQuery(wClause);
		System.out.println("miscWhere query (DAORoom): " + query);

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
				throw new IllegalArgumentException(
						"There is no association to be retrieved Room table.");
			}

		} catch (Exception e) {
			System.out.println("Query exception (DAORoom miscWhere): ");
			e.printStackTrace();
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
			System.out.println("Error in building Room object.");
			e.getMessage();
		}
		return roomObj;
	}

	private String buildQuery(String wClause) {
		String query = "SET DATEFORMAT dmy; SELECT * FROM Room";

		if (wClause.length() > 0)
			query = query + " WHERE " + wClause;

		return query;
	}
}

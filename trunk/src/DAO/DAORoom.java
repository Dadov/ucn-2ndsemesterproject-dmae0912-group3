package DAO;

import java.sql.ResultSet;
import java.util.ArrayList;

import Models.Room;
import Models.RoomType;

public class DAORoom implements IFDAORoom {

	public DAORoom() {
		// con = DBConnection.getInstance().getDBCon();
	}

	@Override
	public Room getRoom(int number) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Room> getAllRooms() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Room> findFreeRooms(String startDate, String endDate,
			Enum<RoomType> roomType) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int insert(Room room) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(Room room) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(int number) {
		// TODO Auto-generated method stub
		return 0;
	}

	@SuppressWarnings("unused")
	private Room singleWhere(String wClause, boolean retrieveAssociatio) {
		// TODO
		return null;
	}

	@SuppressWarnings("unused")
	private ArrayList<Room> miscWhere(String wClause,
			boolean retrieveAssociation) {
		// TODO
		return null;
	}

	@SuppressWarnings("unused")
	private Room buildRoom(ResultSet results) {
		// TODO
		return null;
	}

	@SuppressWarnings("unused")
	private String buildQuery(String wClause) {
		// TODO
		return "";
	}

}

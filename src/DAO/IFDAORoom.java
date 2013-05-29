package DAO;

import java.util.ArrayList;

import Models.Room;
import Models.RoomType;

public interface IFDAORoom {

	public Room getRoom(int number, boolean retrieveAssociation);

	public ArrayList<Room> getAllRooms(boolean retrieveAssociation);

	// find free rooms on given date period and filter by type
	// should filtering by type be optional?
	public ArrayList<Room> findFreeRooms(String startDate, String endDate);

	public ArrayList<Room> findFreeRoomsOfType(String startDate,
			String endDate, Enum<RoomType> roomType);

	public ArrayList<Room> findBookedRooms(String startDate, String endDate);

	public int insert(Room room);

	public int update(Room room);

	public int delete(int number);

}

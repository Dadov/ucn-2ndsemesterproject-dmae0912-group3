package DAO;

import java.util.ArrayList;

import Models.Room;
import Models.RoomType;

public interface IFDAORoom {

	public Room getRoom(int number);

	public ArrayList<Room> getAllRooms();

	// find free rooms on given date period and filter by type
	// should filtering by type be optional?
	public ArrayList<Room> findFreeRooms(String startDate, String endDate,
			Enum<RoomType> roomType);

	public int insert(Room room);

	public int update(Room room);

	public int delete(int number);
}

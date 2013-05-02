package DAO;

import java.util.ArrayList;

import Models.Room;
import Models.RoomType;

public interface IFRoomBookingDAO {

	// find room by its number (ID)
	public Room getRoom(int number);

	// get all Rooms
	public ArrayList<Room> getAllRooms();

	// find free rooms on given date period and filter by type
	// should filtering by type be optional?
	public ArrayList<Room> findFreeRooms(String startDate, String endDate,
			Enum<RoomType> roomType);

	// insert Room
	// returns int, see Statement.executeUpdate()
	public int insert(Room room);

	// update Room
	// returns int, see Statement.executeUpdate()
	public int update(Room room);

	// delete Room
	// returns int, see Statement.executeUpdate()
	public int delete(int number);
}

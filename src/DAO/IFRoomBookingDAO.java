package DAO;

import java.util.ArrayList;

import Models.RoomBooking;

public interface IFRoomBookingDAO {

	// find room by its id
	public RoomBooking getRoomBooking(int id);

	// get all RoomBookings
	public ArrayList<RoomBooking> getAllRoomBookings();

	// insert RoomBooking
	// returns int, see Statement.executeUpdate()
	public int insert(RoomBooking roomBooking);

	// update RoomBooking
	// returns int, see Statement.executeUpdate()
	public int update(RoomBooking roomBooking);

	// delete RoomBooking
	// returns int, see Statement.executeUpdate()
	public int delete(int id);
}

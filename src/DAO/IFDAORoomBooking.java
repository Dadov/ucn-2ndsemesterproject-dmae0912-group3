package DAO;

import java.util.ArrayList;

import Models.RoomBooking;

public interface IFDAORoomBooking {

	// find room by its id
	public RoomBooking getRoomBooking(int id, boolean retrieveAssociation);

	// get all RoomBookings
	public ArrayList<RoomBooking> getAllRoomBookings(boolean retrieveAssociation);

	// insert RoomBooking
	// returns int, see Statement.executeUpdate()
	public int insert(RoomBooking roomBooking);

	// update RoomBooking
	// returns int, see Statement.executeUpdate()
	public int update(RoomBooking roomBooking);

	// delete RoomBooking
	// returns int, see Statement.executeUpdate()
	public int delete(int id);
	
	public int getLastInsertedID();
}

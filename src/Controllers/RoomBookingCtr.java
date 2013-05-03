package Controllers;

import Models.*;
import DAO.*;
import java.util.ArrayList; 

public class RoomBookingCtr {

	// creates a new instance of RoomBookingCtr
	public RoomBookingCtr() {
		
	}
	
	// starts a new booking
	public void newBooking(Customer customer, ArrayList<Room> rooms, String bookDate, String startDate, String endDate) {
		RoomBooking booking = new RoomBooking();
		booking.getId();
		booking.setCustomer(customer);
		booking.setRoomsBooked(rooms);
		booking.setDateBooked(bookDate);
		booking.setDateStart(startDate);
		booking.setDateEnd(endDate);
		
        try{
	         DBConnection.startTransaction();
	         IFDAORoomBooking daoBooking = new DAORoomBooking();
	         daoBooking.insert(booking);
	         DBConnection.commitTransaction();
        }
        catch(Exception e)
        {
        	System.out.println("Error while creating booking in RoomBookingCtr");
            DBConnection.rollbackTransaction();
            e.getMessage();
            e.printStackTrace();
        }
	}
	
	// finds a booking by id
	public RoomBooking findBooking(int id) {
		IFDAORoomBooking daoBooking = new DAORoomBooking();
        return daoBooking.getRoomBooking(id);
	}
	
	// retrieves all bookings
	public ArrayList<RoomBooking> getAllBookings() {
		IFDAORoomBooking daoBooking = new DAORoomBooking();
	    ArrayList<RoomBooking> allBookings = new ArrayList<RoomBooking>();
	    allBookings = daoBooking.getAllRoomBookings();
	    return allBookings;
	}

	// updates a booking
	public int updateBooking(int newId, Customer newCustomer, ArrayList<Room> newRooms,  String newBookDate, String newStartDate, String newEndDate) {
		IFDAORoomBooking daoBooking = new DAORoomBooking();
        RoomBooking booking = new RoomBooking();
        booking.setId(newId);
        booking.setCustomer(newCustomer);
        booking.setRoomsBooked(newRooms);
        booking.setDateBooked(newBookDate);
        booking.setDateStart(newStartDate);
        booking.setDateEnd(newEndDate);
        return daoBooking.update(booking);
	}
	
	// deletes a booking
	public int deleteBooking(int id) {
		IFDAORoomBooking daoBooking = new DAORoomBooking();
		return daoBooking.delete(id);
	}
	
	
}

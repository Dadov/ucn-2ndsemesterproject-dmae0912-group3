package Controllers;

import java.util.ArrayList;

import DAO.DAORoom;
import DAO.DAORoomBooking;
import DAO.IFDAORoom;
import DAO.IFDAORoomBooking;
import Models.Customer;
import Models.Room;
import Models.RoomBooking;
import Models.RoomType;

public class RoomsCtr {
	private IFDAORoom daoRoom;
	private IFDAORoomBooking daoRoomBooking;

	// creates a new instance of RoomBookingCtr
	public RoomsCtr() {

	}

	// METHODS FOR ROOM BOOKING
	// starts a new booking

	public void newRoomBooking(Customer customer, ArrayList<Room> rooms,
			String bookDate, String startDate, String endDate) throws Exception {
		RoomBooking newRoomBooking = new RoomBooking();
		daoRoom = new DAORoom();
		ArrayList<Room> bookedRooms = daoRoom.findFreeRooms(startDate, endDate);
		System.out.println("Another crappy message.");

		newRoomBooking.setCustomer(customer);
		newRoomBooking.setRoomsBooked(rooms);
		newRoomBooking.setDateBooked(bookDate);
		newRoomBooking.setDateStart(startDate);
		newRoomBooking.setDateEnd(endDate);

		for (Room room : rooms) {
			// if freeRooms doesn't contain this room, it's bad already used for
			// the date
			String str = room.toString();
			System.out.println(bookedRooms.toString());
			System.out.println(bookedRooms.contains(room.toString()));
			boolean test = bookedRooms.contains(room.toString());
			// if (freeRooms.contains(room.toString())) {
			// System.out.println("funguje pica");
			// break;
			// } else {
			// throw new Exception("tam mas");
			// // System.out.println("nefunguje pica");
			// }
			for (Room bookedRoom : bookedRooms) {
				if (room.getNumber() == bookedRoom.getNumber()) {
					throw new Exception("tam mas");
				}
			}
		}

		daoRoomBooking = new DAORoomBooking();
		daoRoomBooking.insert(newRoomBooking);
	}

	// finds a booking by id
	public RoomBooking findBooking(int id) {
		daoRoomBooking = new DAORoomBooking();
		return daoRoomBooking.getRoomBooking(id, false);
	}

	// retrieves all bookings
	public ArrayList<RoomBooking> getAllBookings() {
		daoRoomBooking = new DAORoomBooking();
		ArrayList<RoomBooking> allBookings = daoRoomBooking
				.getAllRoomBookings(true);
		return allBookings;
	}

	// updates a booking
	public int updateBooking(int newId, Customer newCustomer,
			ArrayList<Room> newRooms, String newBookDate, String newStartDate,
			String newEndDate) {
		daoRoomBooking = new DAORoomBooking();
		RoomBooking booking = new RoomBooking();
		booking.setId(newId);
		booking.setCustomer(newCustomer);
		booking.setRoomsBooked(newRooms);
		booking.setDateBooked(newBookDate);
		booking.setDateStart(newStartDate);
		booking.setDateEnd(newEndDate);
		return daoRoomBooking.update(booking);
	}

	// deletes a booking
	public int deleteBooking(int id) {
		daoRoomBooking = new DAORoomBooking();
		return daoRoomBooking.delete(id);
	}

	public int deleteRoomsBooked(int id) {
		daoRoomBooking = new DAORoomBooking();
		return daoRoomBooking.deleteRoomsBooked(id);
	}

	// METHODS FOR ROOM BOOKING END

	// METHODS FOR ROOM
	// starts a new booking
	public void newRoom(RoomType roomType, double price, String note) {
		Room room = new Room();
		room.getNumber();
		room.setRoomType(roomType);
		room.setPrice(price);
		room.setNote(note);
	}

	// finds a room by id
	public Room findRoom(int number) {
		IFDAORoom daoRoom = new DAORoom();
		return daoRoom.getRoom(number, false);
	}

	public ArrayList<Room> findFreeRooms(String startDate, String endDate) {
		daoRoom = new DAORoom();
		return daoRoom.findFreeRooms(startDate, endDate);
	}

	public ArrayList<Room> findFreeRoomsOfType(String startDate,
			String endDate, Enum<RoomType> roomType) {
		daoRoom = new DAORoom();
		return daoRoom.findFreeRoomsOfType(startDate, endDate, roomType);
	}

	// retrieves all rooms
	public ArrayList<Room> getAllrooms() {
		IFDAORoom daoRoom = new DAORoom();
		ArrayList<Room> allRooms = new ArrayList<Room>();
		allRooms = daoRoom.getAllRooms(false);
		return allRooms;
	}

	// updates a Room
	public int updateRoom(int newId, RoomType roomType, double price,
			String note) {
		IFDAORoom daoRoom = new DAORoom();
		Room room = new Room();
		room.setNumber(newId);
		room.setRoomType(roomType);
		room.setPrice(price);
		room.setNote(note);
		return daoRoom.update(room);
	}

	// deletes a Room
	public int deleteRoom(int id) {
		IFDAORoom daoRoom = new DAORoom();
		return daoRoom.delete(id);
	}
	// METHODS FOR ROOM END

}

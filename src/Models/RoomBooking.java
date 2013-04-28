package Models;

import java.util.ArrayList;

public class RoomBooking {
	
	private int id;
	private Customer customer;
	private ArrayList<Room> roomsBooked;
	private String dateBooked;
	private String dateStart;
	private String dateEnd;
	
	public RoomBooking() {
		
	}

	public RoomBooking(int id, Customer customer, ArrayList<Room> roomsBooked,
			String dateBooked, String dateStart, String dateEnd) {
		this.id = id;
		this.customer = customer;
		this.roomsBooked = roomsBooked;
		this.dateBooked = dateBooked;
		this.dateStart = dateStart;
		this.dateEnd = dateEnd;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public ArrayList<Room> getRoomsBooked() {
		return roomsBooked;
	}

	public void setRoomsBooked(ArrayList<Room> roomsBooked) {
		this.roomsBooked = roomsBooked;
	}

	public String getDateBooked() {
		return dateBooked;
	}

	public void setDateBooked(String dateBooked) {
		this.dateBooked = dateBooked;
	}

	public String getDateStart() {
		return dateStart;
	}

	public void setDateStart(String dateStart) {
		this.dateStart = dateStart;
	}

	public String getDateEnd() {
		return dateEnd;
	}

	public void setDateEnd(String dateEnd) {
		this.dateEnd = dateEnd;
	}

	@Override
	public String toString() {
		return "RoomBooking [id=" + id + ", customer=" + customer
				+ ", roomsBooked=" + roomsBooked + ", dateBooked=" + dateBooked
				+ ", dateStart=" + dateStart + ", dateEnd=" + dateEnd + "]";
	}
	
}

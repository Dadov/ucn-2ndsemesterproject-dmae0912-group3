package Models;

public class Room {

	private int number;
	private Enum<RoomType> roomType;
	private double price;
	private String note;

	public Room() {

	}

	public Room(Enum<RoomType> roomType, double price, String note) {
		this.roomType = roomType;
		this.price = price;
		this.note = note;
	}

	public Room(int number, Enum<RoomType> roomType, double price, String note) {
		this.number = number;
		this.roomType = roomType;
		this.price = price;
		this.note = note;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public Enum<RoomType> getRoomType() {
		return roomType;
	}

	public void setRoomType(Enum<RoomType> roomType) {
		this.roomType = roomType;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	@Override
	public String toString() {
		return "Room [number=" + number + ", roomType=" + roomType + ", price="
				+ price + ", note=" + note + "]";
	}

}

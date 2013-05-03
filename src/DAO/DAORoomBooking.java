package DAO;

import java.sql.ResultSet;
import java.util.ArrayList;

import Models.RoomBooking;

public class DAORoomBooking implements IFDAORoomBooking {

	public DAORoomBooking() {
		// con = DBConnection.getInstance().getDBCon();
	}

	@Override
	public RoomBooking getRoomBooking(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<RoomBooking> getAllRoomBookings() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int insert(RoomBooking roomBooking) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(RoomBooking roomBooking) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(int id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@SuppressWarnings("unused")
	private RoomBooking singleWhere(String wClause, boolean retrieveAssociation) {
		// TODO
		return null;
	}

	@SuppressWarnings("unused")
	private ArrayList<RoomBooking> miscWhere(String wClause,
			boolean retrieveAssociation) {
		// TODO
		return null;
	}

	@SuppressWarnings("unused")
	private RoomBooking buildRoomBooking(ResultSet results) {
		// TODO
		return null;
	}

	@SuppressWarnings("unused")
	private String buildQuery(String wClause) {
		// TODO
		return "";
	}

}

package DAO;

import java.sql.ResultSet;
import java.util.ArrayList;

import Models.ActivityBooking;

public class DAOActivityBooking implements IFDAOActivityBooking {

	public DAOActivityBooking() {
		// TODO con = DBConnection.getInstance().getDBCon();
	}

	@Override
	public ActivityBooking getActivityBookig(int ID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<ActivityBooking> getAllActivityBookings() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int insert(ActivityBooking activityBooking) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(ActivityBooking activityBooking) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(int ID) {
		// TODO Auto-generated method stub
		return 0;
	}

	@SuppressWarnings("unused")
	private ActivityBooking singleWhere(String wClause,
			boolean retrieveAssociation) {
		// TODO
		return null;
	}

	@SuppressWarnings("unused")
	private ArrayList<ActivityBooking> miscWhere(String wClause,
			boolean retrieveAssociation) {
		// TODO
		return null;
	}

	@SuppressWarnings("unused")
	private ActivityBooking buildActivityBooking(ResultSet results) {
		// TODO
		return null;
	}

	@SuppressWarnings("unused")
	private String buildQuery(String wClause) {
		// TODO
		return "";
	}

}

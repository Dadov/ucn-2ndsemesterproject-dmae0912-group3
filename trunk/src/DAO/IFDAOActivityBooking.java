package DAO;

import java.util.ArrayList;

import Models.ActivityBooking;

public interface IFDAOActivityBooking {

	public ActivityBooking getActivityBookig(int ID);

	public ArrayList<ActivityBooking> getAllActivityBookings();

	public int insert(ActivityBooking activityBooking);

	public int update(ActivityBooking activityBooking);

	public int delete(int ID);

}

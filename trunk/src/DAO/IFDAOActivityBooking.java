package DAO;

import java.util.ArrayList;

import Models.ActivityBooking;

public interface IFDAOActivityBooking {

	public ActivityBooking getActivityBookig(int ID, boolean retrieveAssociation);

	public ArrayList<ActivityBooking> getAllActivityBookings(
			boolean retrieveAssociation);

	public int insert(ActivityBooking activityBooking);

	public int update(ActivityBooking activityBooking);

	public int delete(int ID);

}

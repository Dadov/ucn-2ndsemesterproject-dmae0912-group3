package DAO;

import java.util.ArrayList;

import Models.Activity;
import Models.ActivityType;

public interface IFDAOActivity {

	public Activity getActivity(int ID, boolean retrieveAssociation);

	public ArrayList<Activity> getAllActivities(boolean retrieveAssociation);

	public int insert(Activity activity);

	public int update(Activity activity);

	public int delete(int ID);
	
	public int getLastInsertedID();

	public ArrayList<Activity> findFreeActivities(String date,
			String time, Enum<ActivityType> activityType);
}

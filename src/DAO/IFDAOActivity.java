package DAO;

import java.util.ArrayList;

import Models.Activity;

public interface IFDAOActivity {

	public Activity getActivity(int ID, boolean retrieveAssociation);

	public ArrayList<Activity> getAllActivities(boolean retrieveAssociation);

	public int insert(Activity activity);

	public int update(Activity activity);

	public int delete(int ID);
}

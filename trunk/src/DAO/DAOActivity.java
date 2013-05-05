package DAO;

import java.sql.ResultSet;
import java.util.ArrayList;

import Models.Activity;

public class DAOActivity implements IFDAOActivity {

	public DAOActivity() {
		// TODO con = DBConnection.getInstance().getDBCon();
	}

	@Override
	public Activity getActivity(int ID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Activity> getAllActivities() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int insert(Activity activity) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(Activity activity) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(int ID) {
		// TODO Auto-generated method stub
		return 0;
	}

	@SuppressWarnings("unused")
	private Activity singleWhere(String wClause, boolean retrieveAssociation) {
		// TODO
		return null;
	}

	@SuppressWarnings("unused")
	private ArrayList<Activity> miscWhere(String wClause,
			boolean retrieveAssociation) {
		// TODO
		return null;
	}

	@SuppressWarnings("unused")
	private Activity buildActivity(ResultSet results) {
		// TODO
		return null;
	}

	@SuppressWarnings("unused")
	private String buildQuery(String wClause) {
		// TODO
		return "";
	}

}

package DAO;

import java.sql.ResultSet;
import java.util.ArrayList;

import Models.Staff;

public class DAOStaff implements IFDAOStaff {

	public DAOStaff() {
		// TODO con = DBConnection.getInstance().getDBCon();
	}

	@Override
	public Staff getStaff(int ID, boolean retrieveAssociation) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Staff> getAllStaff(boolean retrieveAssociation) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int insert(Staff Staff) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(Staff Staff) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(int ID) {
		// TODO Auto-generated method stub
		return 0;
	}

	@SuppressWarnings("unused")
	private Staff singleWhere(String wClause, boolean retrieveAssociation) {
		// TODO
		return null;
	}

	@SuppressWarnings("unused")
	private ArrayList<Staff> miscWhere(String wClause,
			boolean retrieveAssociation) {
		// TODO
		return null;
	}

	@SuppressWarnings("unused")
	private Staff buildStaff(ResultSet results) {
		// going to return Manager/Secretary/Receptionist/Instructor object
		// depending on the 'type' field in Staff table
		// still not gonna give type error
		// since all of them are subclasses ofStaff
		// TODO
		return null;
	}

	@SuppressWarnings("unused")
	private String buildQuery(String wClause) {
		// TODO
		return "";
	}

}

package DAO;

import java.sql.ResultSet;
import java.util.ArrayList;

import Models.Agency;

public class DAOAgency implements IFDAOAgency {

	public DAOAgency() {
		// TODO con = DBConnection.getInstance().getDBCon();
	}

	@Override
	public Agency getAgency(int ID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Agency> getAllAgencies() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int insert(Agency agency) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(Agency agency) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(int ID) {
		// TODO Auto-generated method stub
		return 0;
	}

	@SuppressWarnings("unused")
	private Agency singleWhere(String wClause, boolean retrieveAssociation) {
		// TODO
		return null;
	}

	@SuppressWarnings("unused")
	private ArrayList<Agency> miscWhere(String wClause,
			boolean retrieveAssociation) {
		// TODO
		return null;
	}

	@SuppressWarnings("unused")
	private Agency buildAgency(ResultSet results) {
		// TODO
		return null;
	}

	@SuppressWarnings("unused")
	private String buildQuery(String wClause) {
		// TODO
		return "";
	}

}

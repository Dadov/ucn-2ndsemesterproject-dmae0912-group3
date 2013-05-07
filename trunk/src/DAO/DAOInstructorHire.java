package DAO;

import java.sql.ResultSet;
import java.util.ArrayList;

import Models.InstructorHire;

public class DAOInstructorHire implements IFDAOInstructorHire {

	public DAOInstructorHire() {
		// TODO con = DBConnection.getInstance().getDBCon();
	}

	@Override
	public InstructorHire getInstructorHire(int ID, boolean retrieveAssociation) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<InstructorHire> getInstructorHires(
			boolean retrieveAssociation) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int insert(InstructorHire InstructorHire) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(InstructorHire InstructorHire) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(int ID) {
		// TODO Auto-generated method stub
		return 0;

	}

	@SuppressWarnings("unused")
	private InstructorHire singleWhere(String wClause,
			boolean retrieveAssociation) {
		// TODO
		return null;
	}

	@SuppressWarnings("unused")
	private ArrayList<InstructorHire> miscWhere(String wClause,
			boolean retrieveAssociation) {
		// TODO
		return null;
	}

	@SuppressWarnings("unused")
	private InstructorHire buildInstructorHire(ResultSet results) {
		// TODO
		return null;
	}

	@SuppressWarnings("unused")
	private String buildQuery(String wClause) {
		// TODO
		return "";
	}

}

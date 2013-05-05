package DAO;

import java.sql.ResultSet;
import java.util.ArrayList;

import Models.Person;

public class DAOPerson implements IFDAOPerson {

	public DAOPerson() {
		// TODO con = DBConnection.getInstance().getDBCon();
	}

	@Override
	public Person getPerson(int ID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Person> getAllPersons() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int insert(Person Person) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(Person Person) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(int ID) {
		// TODO con = DBConnection.getInstance().getDBCon();
		return 0;
	}

	@SuppressWarnings("unused")
	private Person singleWhere(String wClause, boolean retrieveAssociation) {
		// TODO
		return null;
	}

	@SuppressWarnings("unused")
	private ArrayList<Person> miscWhere(String wClause,
			boolean retrieveAssociation) {
		// TODO
		return null;
	}

	@SuppressWarnings("unused")
	private Person buildPerson(ResultSet results) {
		// TODO
		return null;
	}

	@SuppressWarnings("unused")
	private String buildQuery(String wClause) {
		// TODO
		return "";
	}

}

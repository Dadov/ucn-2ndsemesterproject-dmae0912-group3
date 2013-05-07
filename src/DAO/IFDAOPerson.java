package DAO;

import java.util.ArrayList;

import Models.Person;

public interface IFDAOPerson {

	public Person getPerson(int ID, boolean retrieveAssociation);

	public ArrayList<Person> getAllPersons(boolean retrieveAssociation);

	public int insert(Person Person);

	public int update(Person Person);

	public int delete(int ID);

}

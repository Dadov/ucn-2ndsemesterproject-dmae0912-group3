package DAO;

import java.util.ArrayList;

import Models.Person;

public interface IFDAOPerson {

	public Person getPerson(int ID);

	public ArrayList<Person> getAllPersons();

	public int insert(Person Person);

	public int update(Person Person);

	public int delete(int ID);

}

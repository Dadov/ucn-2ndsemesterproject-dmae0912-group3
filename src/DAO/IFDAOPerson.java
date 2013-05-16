package DAO;

import java.util.ArrayList;

import Models.Person;

/**
 *  This class is the interface for DAOPerson
 */
public interface IFDAOPerson {
    
    /*
     * gets Person instance, found by ID;
     * prompts for personID and retrieveAssociation;
     */
    public Person getPerson(int personID, boolean retrieveAssociation);
    
    
    /*
     * gets Person instance, found by CPR;
     * prompts for CPR and retrieveAssociation;
     */
    public Person getPerson(String CPR, boolean retrieveAssociation);
    
    
    /*
     * gets Person instance, found by first and last name;
     * there is a possibility to get more than one instance;
     * returns an ArrayList with Person instances;
     * prompts for fname and lname, and retrieveAssociation;
     */
    public ArrayList<Person> getPerson(String fname, String lname, boolean retrieveAssociation);
    
    
    /*
     * gets all Person instances;
     * prompts for retrieveAssociation;
     */
    public ArrayList<Person> getAllPersons(boolean retrieveAssociation);
    
    
    /*
     * inserts Person instance information in db;
     * returns row count number;
     */
    public int insert(Person person, String personType);
    
    
    /*
     * updates information about specified Person in db;
     * returns row count number;
     */
    public int update(Person person, String personType);
    
    
    /*
     * deletes information about specified Person in db;
     * returns row count number;
     */
    public int delete(int PersonID);

}

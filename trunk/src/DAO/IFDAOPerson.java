package DAO; //indicates the location of the class;

//in case, multiple Person instances must be retrieved;
import java.util.ArrayList;

import Models.Person; //imports the business class;

/**
 * interface of DAOPerson class;
 * for its implementation see DAOPerson;
 */
public interface IFDAOPerson {
    
    /*
     * gets Person instance, found by personID;
     * returns Person instance;
     * prompts for personID and retrieveAssociation;
     */
    public Person getPerson(int personID, boolean retrieveAssociation);
    
    
    /*
     * gets Person instance, found by CPR;
     * returns Person instance;
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
     * returns an ArrayList with Person instances;
     * prompts for retrieveAssociation;
     */
    public ArrayList<Person> getAllPersons(boolean retrieveAssociation);
    
    
    /*
     * inserts Person instance information in db;
     * prompts for Person instance and personType;
     */
    public int insert(Person person, String personType);
    
    
    /*
     * updates information about specified Person in db;
     * prompts for Person instance and personType;
     */
    public int update(Person person, String personType);
    
    
    /*
     * deletes information about specified Person in db;
     * prompts for personID;
     */
    public int delete(int PersonID);

}

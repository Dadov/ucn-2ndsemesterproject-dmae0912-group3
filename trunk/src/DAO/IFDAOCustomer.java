package DAO; //indicates the location of the class;

//in case, multiple Person instances must be retrieved;
import java.util.ArrayList;

import Models.Customer; //imports the business class;

/**
 * interface of DAOCustomer class;
 * for its implementation see DAOCustomer;
 */
public interface IFDAOCustomer {
    	
    /*
     * gets Customer instance, found by customerID;
     * returns Customer instance;
     * prompts for customerID and retrieveAssociation;
     */
    public Customer getCustomer(int customerID, boolean retrieveAssociation);
    
    /*
     * gets all Customer instances;
     * returns an ArrayList with Customer instances;
     * prompts for retrieveAssociation;
     */
    public ArrayList<Customer> getAllCustomers(boolean retrieveAssociation);

    /*
     * inserts Customer instance information in db;
     * prompts for Customer instance;
     */
    public int insert(Customer Customer);

    /*
     * updates information about specified Customer in db;
     * prompts for Customer instance;
     */
    public int update(Customer Customer);

    /*
     * deletes information about specified Customer in db;
     * prompts for customerID;
     */
    public int delete(int customerID);
}

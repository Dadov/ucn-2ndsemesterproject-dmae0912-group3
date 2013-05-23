package DAO; //indicates class location;

import static org.junit.Assert.assertEquals; //needed so comparisons can be made;
import static org.junit.Assert.assertNull; //needed to check that the DELETE operation has succeeded;
import static org.mockito.Mockito.mock; //needed to make a mock of DBConnection;
import static org.mockito.Mockito.when; //needed to verify that getDBCon() has succeeded;

import java.sql.Connection; //a connection with the db must be established;
import java.sql.SQLException; //exceptions must be handled, in case an operation fails;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before; //needed to prepare everything before testing;
import org.junit.BeforeClass; 
import org.junit.Test; //needed to indicate testing;

import Models.Customer; //the subject for testing;

import java.util.ArrayList; //Needed, because multiple Customer instances might be retrieved;

/**
 * unit test for DAOCustomer class;
 * first creates two Customer instances;
 * after the setUp, tests whether DAOCustomer can get the connection;
 * next method carries out a test on all CRUD operations in DAOCustomer;
 */
public class DAOCustomerTest {
    
    private Connection con;
    private Customer c1, c2; //Person instances;
    private IFDAOCustomer dao;
    
    
    @BeforeClass
    /*
     * empty, because there is nothing to do before any other method
     * from the class to be executed; 
     */
    public static void setUpBeforeClass() throws Exception {
    }

    @AfterClass
    /*
     * empty, because there is nothing to do after all methods from
     * the class are executed; 
     */
    public static void tearDownAfterClass() throws Exception {
    }

    
    @Before
    /*
     * prepares Customer instances for testing;
     * creates them by using the constructor, that sets all fields;
     * at least two are needed, so update method can be tested; 
     */
    public void setUp() throws Exception {
	c1 = new Customer(0, "111188-0000", "Jan", "Anderson", 
			"Danmark", "9000", "Aalborg", "Enrich Vej 10", 
			"monkey@mail.com", "grant", "14-05-2013", 10);
	
	c2 = new Customer(1, "010190-0000", "Emily", "Johanson", 
			"Danmark", "9000", "Aalborg", "Hobrovej 100", 
			"wildMonkey@crazy.com", "allow", "16-05-2013" , 0);
    }

    @After
    /*
     * empty, because there is nothing to do after the method is executed; 
     */
    public void tearDown() throws Exception {
    }
    

    @Test
    /*
     * carries out test on the constructor of class DAOCustomer;
     * makes a mock of DBConnection to verify that everything works as
     * expected that Connection instance can be retrieved;
     */
    public void testDAOCustomer() {
	DBConnection tCon = mock(DBConnection.class);
	//it must return con, as a result;
	when(tCon.getDBCon()).thenReturn(con);
    }
        
    @Test
    /*
     * tests all CRUD methods of DAOCustomer;
     * first, inserts Customer data, retrieves it by calling getAllCustomers() and
     * compares to check everything is ok;
     * second, gets Customer data found by ID and compares;
     * third, update Customer data;
     * forth, deletes Customer data;
     */
    public void testCRUD() throws SQLException {
	//gets the Connection instance in order to start transactions;
	con = DBConnection.getInstance().getDBCon();
	//autoCommit is set to false, so rollback can be used after tests;
	con.setAutoCommit(true);
	dao = new DAOCustomer();
	
	try {
	    //INSERT Customer;
	    dao.insert(c1);//inserts the Customer data;
	    //getAllCustomers() is used, because it is least complex;
	    ArrayList<Customer> customers = dao.getAllCustomers(false);
	    Customer lastInserted = customers.get(customers.size()-1);
	    //we have to compare field by field, because c1 doesn't have ID;
	    assertEquals(c1.getAddress(), lastInserted.getAddress());
	    assertEquals(c1.getCity(), lastInserted.getCity());
	    assertEquals(c1.getCountry(), lastInserted.getCountry());
	    assertEquals(c1.getCPR(), lastInserted.getCPR());
	    assertEquals(c1.getEmail(), lastInserted.getEmail());
	    assertEquals(c1.getFname(), lastInserted.getFname());
	    assertEquals(c1.getLname(), lastInserted.getLname());
	    assertEquals(c1.getNoOfStays(), lastInserted.getNoOfStays());
	    assertEquals(c1.getPassword(), lastInserted.getPassword());
	    assertEquals(c1.getRegistrationDate(), lastInserted.getRegistrationDate());
	    assertEquals(c1.getZIP(), lastInserted.getZIP());
	    
	    //GET Customer by ID;
	    //needed to retrieve the ID first, because c1 doesn't have one;
	    int customerID = lastInserted.getPersonID();
	    Customer resultByID = dao.getCustomer(customerID, false);
	    //comparing object by object;
	    assertEquals(lastInserted.toString(), resultByID.toString());
	    
	    //UPDATE Customer;
	    //changes c1 data with c2 data;
	    c2.setPersonID(customerID); //needed, because c2 doesn't have ID;
	    System.out.println("customerID = " + customerID);
	    dao.update(c2);
	    //updates the customers collection, so the last update can be retrieved;
	    customers = dao.getAllCustomers(false);
	    lastInserted = customers.get(customers.size()-1);
	    //comparing toString by toString;
	    assertEquals(c2.toString(), lastInserted.toString());
	    
	    //DELETE Customer;
	    dao.delete(customerID);
	    assertNull(dao.getCustomer(customerID, false));
	    
	} finally {
	    //rollback is used, to undo all changes that were made during testings;
	    //con.rollback();
	    con.close();
	}
    }
}

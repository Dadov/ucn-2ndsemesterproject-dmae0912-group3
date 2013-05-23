package DAO; //indicates class location;

import static org.junit.Assert.assertEquals; //needed so comparisons can be made;
import static org.junit.Assert.assertNull; //needed to check that the DELETE operation has succeeded;
import static org.mockito.Mockito.mock; //needed to make a mock of DBConnection;
import static org.mockito.Mockito.when; //needed to verify that getDBCon() has succeeded;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before; //needed to prepare everything before testing;
import org.junit.BeforeClass; 
import org.junit.Test; //needed to indicate testing;

import java.sql.Connection; //a connection with the db must be established;
import java.sql.SQLException; //exceptions must be handled, in case an operation fails;

import Models.Customer;
import Models.Person; //the subject for testing;
import java.util.ArrayList; //Needed, because multiple Person instances might be retrieved;


/**
 * unit test for DAOPerson class;
 * first creates two Person instances;
 * after the setUp, tests whether DAOPerson can get the connection;
 * next method carries out a test on all CRUD operations in DAOPerson;
 */
public class DAOPersonTest {

    private static Connection con;
    private Person p1, p2; //Person instances;
    private IFDAOPerson dao;
    
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
    	con.setAutoCommit(false);
		//con.close();
    }
    
    @Before
    /*
     * prepares Person instances for testing;
     * creates them by using the constructor, that sets all fields;
     * at least two are needed, so update method can be tested;
     */
    public void setUp() throws Exception {
	p1 = new Customer(0, "111188-0000", "Jan", "Anderson", 
			"Danmark", "9000", "Aalborg", "Enrich Vej 10", 
			"monkey@mail.com", "grant", "2010-10-10", 0);
	//System.out.println(p1.toString());
	
	p2 = new Customer(1, "010190-0000", "Emily", "Johanson", 
			"Danmark", "9000", "Aalborg", "Hobrovej 100", 
			"wildMonkey@crazy.com", "allow", null, 0);
	//System.out.println(p2.toString());
    }
    
    @After
    /*
     * empty, because there is nothing to do after the method is executed;
     */
    public void tearDown() throws Exception {
    }
    
    @Test
    /*
     * carries out test on the constructor of class DAOPerson;
     * makes a mock of DBConnection to verify that everything works as
     * expected that Connection instance can be retrieved;
     */
    public void testDAOPerson() {
	DBConnection tCon = mock(DBConnection.class);
	//it must return con, as a result;
	when(tCon.getDBCon()).thenReturn(con);
    }
        
    @Test
    /*
     * tests all CRUD methods of DAOPerson;
     * first, inserts Person data, retrieves it by calling getAllPersons() and
     * compares to check everything is ok;
     * second, gets Person data found by ID and compares;
     * third, gets Person data found by CPR number and compares;
     * forth, gets Person data found by First and Last name and compares;
     * fifth, updates Person data;
     * sixth, deletes Person data;
     */
    public void testCRUD() throws SQLException {
	//gets the Connection instance in order to start transactions;
	con = DBConnection.getInstance().getDBCon();
	//autoCommit is set to false, so rollback can be used after tests;
	con.setAutoCommit(false);
	dao = new DAOPerson();
	
	try {
	    //INSERT Person;
	    dao.insert(p1, "Customer"); //inserts the Person data;
	    //getAllPersons() is used, because it is least complex;
	    ArrayList<Person> persons = dao.getAllPersons(false);
	    Person lastInserted = persons.get(persons.size()-1);
	    //we have to compare field by field, because p1 doesn't have ID;
	    assertEquals(p1.getAddress(), lastInserted.getAddress());
	    assertEquals(p1.getCity(), lastInserted.getCity());
	    assertEquals(p1.getCountry(), lastInserted.getCountry());
	    assertEquals(p1.getCPR(), lastInserted.getCPR());
	    assertEquals(p1.getEmail(), lastInserted.getEmail());
	    assertEquals(p1.getFname(), lastInserted.getFname());
	    assertEquals(p1.getLname(), lastInserted.getLname());
	    assertEquals(p1.getPassword(), lastInserted.getPassword());
	    assertEquals(p1.getZIP(), lastInserted.getZIP());
	    
	    //GET Person by ID;
	    //needed to retrieve the ID, because p1 doesn't have one;
	    int personID = lastInserted.getPersonID();
	    Person resultByID = dao.getPerson(personID, false);
	    //comparing toString by toString;
	    assertEquals(lastInserted.toString(), resultByID.toString());
	    //assertEquals(lastInserted, resultByID);
	    //assertEquals(p1.getAddress(), resultByID.getAddress());
	    //assertEquals(p1.getCity(), resultByID.getCity());
	    //assertEquals(p1.getCountry(), resultByID.getCountry());
	    //assertEquals(p1.getCPR(), resultByID.getCPR());
	    //assertEquals(p1.getEmail(), resultByID.getEmail());
	    //assertEquals(p1.getFname(), resultByID.getFname());
	    //assertEquals(p1.getLname(), resultByID.getLname());
	    //assertEquals(p1.getPassword(), resultByID.getPassword());
	    //assertEquals(p1.getZIP(), resultByID.getZIP());
	    
	    //GET Person by CPR number;
	    Person resultByCPR = dao.getPerson(p1.getCPR(), false);
	    //comparing toString by toString;
	    assertEquals(lastInserted.toString(), resultByCPR.toString());
	    //assertEquals(p1.getAddress(), resultByCPR.getAddress());
	    //assertEquals(p1.getCity(), resultByCPR.getCity());
	    //assertEquals(p1.getCountry(), resultByCPR.getCountry());
	    //assertEquals(p1.getCPR(), resultByCPR.getCPR());
	    //assertEquals(p1.getEmail(), resultByCPR.getEmail());
	    //assertEquals(p1.getFname(), resultByCPR.getFname());
	    //assertEquals(p1.getLname(), resultByCPR.getLname());
	    //assertEquals(p1.getPassword(), resultByCPR.getPassword());
	    //assertEquals(p1.getZIP(), resultByCPR.getZIP());
	    
	    //GET Person by First and Last name;
	    Person resultByLFname = dao.getPerson(p1.getFname(), p1.getLname(), false).get(0);
	    //comparing toString by toString;
	    assertEquals(lastInserted.toString(), resultByLFname.toString());
	    //assertEquals(p1.getAddress(), resultByLFname.getAddress());
	    //assertEquals(p1.getCity(), resultByLFname.getCity());
	    //assertEquals(p1.getCountry(), resultByLFname.getCountry());
	    //assertEquals(p1.getCPR(), resultByLFname.getCPR());
	    //assertEquals(p1.getEmail(), resultByLFname.getEmail());
	    //assertEquals(p1.getFname(), resultByLFname.getFname());
	    //assertEquals(p1.getLname(), resultByLFname.getLname());
	    //assertEquals(p1.getPassword(), resultByLFname.getPassword());
	    //assertEquals(p1.getZIP(), resultByLFname.getZIP());
	    
	    //UPDATE Person;
	    //changes p1 data with p2 data;
	    p2.setPersonID(personID); //needed, because p2 doesn't have ID;
	    dao.update(p2, "Customer");
	    //updates the persons collection, so the last update can be retrieved;
	    persons = dao.getAllPersons(false);
	    lastInserted = persons.get(persons.size()-1);
	    //comparing toString by toString;
	    assertEquals(p2.toString(), lastInserted.toString());
	    //assertEquals(p2.getAddress(), lastInserted.getAddress());
	    //assertEquals(p2.getCity(), lastInserted.getCity());
	    //assertEquals(p2.getCountry(), lastInserted.getCountry());
	    //assertEquals(p2.getCPR(), lastInserted.getCPR());
	    //assertEquals(p2.getEmail(), lastInserted.getEmail());
	    //assertEquals(p2.getFname(), lastInserted.getFname());
	    //assertEquals(p2.getLname(), lastInserted.getLname());
	    //assertEquals(p2.getPassword(), lastInserted.getPassword());
	    //assertEquals(p2.getZIP(), lastInserted.getZIP());
	    
	    //DELETE Person;
	    dao.delete(personID);
	    assertNull(dao.getPerson(personID, false));
	    
	} finally {
	    //rollback is used, to undo all changes that were made during testings;
	    con.rollback();
	}
    }
}

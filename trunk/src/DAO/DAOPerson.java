package DAO; //indicates the location of the class;

import java.sql.Connection;	//a connection with the db must be established;
import java.sql.ResultSet;	//storage for the data which is going to be retrieved from the db; 
import java.sql.SQLException;	//exceptions must be handled, if an operation fails;
import java.sql.Statement;	//SQL queries, built in this class, must be executed;

//in case, multiple Person instances must be retrieved;
import java.util.ArrayList;

import Models.Customer;
//business class, where data fetched from the db will be stored;
import Models.Person;
import Models.Staff;

/**
 * separates the db access code from the business class Person; 
 * contains all necessary SQL-code for db access and data manipulation;
 * consists of 4 get and 3 update methods;
 * responsible for editing Person and Location tables;
 * for its interface check IFDAOPerson;
 */
public class DAOPerson implements IFDAOPerson {
    
    //needed, so data in the db can be retrieved or manipulated;
    private Connection con;

    
    /** Constructor */
    
    /*
     * creates DAOPerson;
     * initializes the reference to the db connection instance;
     */
    public DAOPerson() {
	//gets the instance for db connection;
	con = DBConnection.getInstance().getDBCon();
    }

    
    /** Get Methods */
    
    @Override
    /*
     * gets Person data from the db found by personID;
     * returns Person instance;
     * prompts for personID and retrieveAssociation;
     */
    public Person getPerson(int personID, boolean retrieveAssociation) {
	
	//Defining the search criteria, to find Person by personID;
	String wClause = "personID = " + personID;
	
	//See private Person singleWhere(wClause, retrieveAssociation);
	return singleWhere(wClause, retrieveAssociation);
    }
    
    
    @Override
    /*
     * gets Person data from the db found by CPR number;
     * returns Person instance;
     * prompts for CPR and retrieveAssociation;
     */
    public Person getPerson(String CPR, boolean retrieveAssociation) {
	
	//Defining the search criteria, to find Person by personCPR;
	String wClause = "CPR = '" + CPR + "'";
		
	//See private Person singleWhere(wClause, retrieveAssociation);
	return singleWhere(wClause, retrieveAssociation);
    }
    
    
    @Override
    /*
     * gets Person data from the db found by first and last name;
     * returns an ArrayList of Person instances;
     * ArrayList is used, because the possibility that
     * several persons have the same names must be considered;
     * prompts for fname and lname, and retrieveAssociation;
     */
    public ArrayList<Person> getPerson(String fname, String lname, boolean retrieveAssociation) {

	//Defining the search criteria, to find Person by first and last name;
	String wClause = "fname = '" + fname + "' AND lname = '" + lname + "'";
			
	//See private ArrayList<Person> miscWhere(wClause, retrieveAssociation);
	return miscWhere(wClause, retrieveAssociation);
    }

    
    @Override
    /*
     * gets all Person data from the db;
     * there is no search criteria, because all Person data will be retrieved;
     * ArrayList is used, because there will be multiple Person instances;
     * prompts for retrieveAssociation;
     */
    public ArrayList<Person> getAllPersons(boolean retrieveAssociation) {
	
	//wClause is empty, because all Person instances must be retrieved, so there's no condition;
	return miscWhere("", retrieveAssociation);
    }
    
    
    /** Update Methods */
    
    @Override
    /*
     * inserts Person instance information in db; 
     * returns a row count number at success;
     * the row count is initialized at -1, to indicate failure, in case the method fails;
     * creates INSERT query; 
     * affects Person and Location tables;
     * on call, edits the Location table first and then the Person table;
     * prompts for Person instance and personType;
     */
    public int insert(Person person, String personType) {
	
	insertLocation(person); //inserts Location data of the Person instance;
	
	int rc = -1; //row count; 
	
	//building INSERT query;
	String query =  "SET DATEFORMAT dmy; " + 		  /* SETS DATA FORMAT	*/
			"INSERT INTO Person "  +		  /* SPECIFIES COLUMNS	*/
			"(CPR, fname, lname, "  +	  	  /* Personal Data     	*/
			"address, locationZIP, country, " +	  /* Location Data      */
			"email, password, personType) "   +	  /* Credentials Data   */
			"VALUES (" +				  /* INSERTS THE VALUES */
			"'" + person.getCPR()		+ "', " + /* CPR 		*/
			"'" + person.getFname()		+ "', " + /* fname		*/
			"'" + person.getLname()		+ "', " + /* lname		*/
			"'" + person.getAddress()	+ "', " + /* address		*/
			"'" + person.getZIP()		+ "', " + /* locationZIP (FK)	*/
			"'" + person.getCountry()	+ "', " + /* country (FK)	*/
			"'" + person.getEmail()		+ "', " + /* email		*/
			"'" + person.getPassword()	+ "', " + /* password		*/
			"'" + personType		+ "');";  /* personType		*/
	//INSERT query building completed;
	//we don't need to insert personID in the db, 'cause it is auto-incremented (IDENTITY);
	
	try { //executing the query and inserting Person data;
	    	
	    Statement stmt = con.createStatement();
	    stmt.setQueryTimeout(5); //Sets query time out at 5 seconds;
		
	    //executes the query and gets the row count number;
	    rc = stmt.executeUpdate(query);
	    stmt.close();
	    
	} catch(SQLException cantInsert) {
	    System.out.println("Error: INSERT Person fails ");
	    cantInsert.getCause(); //shows the cause for exception;
	}
	
	return rc; //returns the row count;
    }

    
    @Override
    /*
     * updates information about specified Person in db; 
     * returns a row count number at success;
     * the row count is initialized at -1, to indicate failure, in case the method fails; 
     * creates UPDATE query;
     * affects Person and Location tables;
     * on call, edits the Location table first and then the Person table;
     * prompts for Person instance and personType;
     */
    //updates information about particular Person instance;
    public int update(Person person, String personType) {
	
	insertLocation(person); //inserts Location data of the Person instance;
	
	int rc = -1; //row count;
	
	//building UPDATE query;
	String query =  "SET DATEFORMAT dmy;" + 					/* SETS DATA FORMAT	*/
			"UPDATE Person SET "  +						/* SETS NEW DATA FOR	*/
			"CPR = '" 		+ person.getCPR()	+ "', " +	/* CPR			*/
			"fname = '" 		+ person.getFname()	+ "', " +	/* fname		*/
			"lname = '" 		+ person.getLname()	+ "', " +	/* lname		*/
			"address = '" 		+ person.getAddress()	+ "', " + 	/* address		*/
			"locationZIP = '"	+ person.getZIP()	+ "', " +	/* locationZIP (FK)	*/
			"country = '" 		+ person.getCountry()	+ "', " +	/* country (FK)		*/
			"email = '" 		+ person.getEmail()	+ "', " +	/* email		*/
			"password = '" 		+ person.getPassword()	+ "', " +	/* password		*/
			"personType = '" 	+ personType		+ "' "  +	/* personType		*/
			"WHERE personID = " 	+ person.getPersonID()	+ ";";		/* SPECIFIES FOR WHICH	*/
											/* TO APPLY THE UPDATES	*/
	//UPDATE query building completed;
	
	try { //executing the query and updating Person data;
	    	
	    Statement stmt = con.createStatement();
	    stmt.setQueryTimeout(5); //Sets query time out at 5 seconds;
		
	    //executes the query and gets the row count number;
	    rc = stmt.executeUpdate(query);
	    stmt.close();
	    
	} catch(SQLException cantUpdate) {
	    System.out.println("Error: UPDATE Person fails ");
	    cantUpdate.getCause(); //shows the cause for exception;
	}	
	
	return rc; //returns the row count;
    }
    
    
    @Override
    /*
     * deletes information about specified Person in db, found by personID; 
     * returns a row count number at success;
     * the row count is initialized at -1, to indicate failure, in case the method fails; 
     * creates DELETE query;
     * affects Person table;
     * prompts for personID;
     */
    public int delete(int PersonID) {
	
	int rc = -1; //row count;
	
	//building DELETE query;
	String query =  "DELETE FROM Person WHERE PersonID = " + PersonID + ";";
	
	
	try { //executing the query and deleting Person data;
	    	
	    Statement stmt = con.createStatement();
	    stmt.setQueryTimeout(5); //Sets query time out at 5 seconds;
		
	    //executes the query and gets the row count number;
	    rc = stmt.executeUpdate(query);
	    stmt.close();
	    
	} catch(SQLException cantDelete) {
	    System.out.println("Error: DELETE Person fails ");
	    cantDelete.getCause(); //shows the cause for exception;
	}	
	
	return rc; //returns the row count to the controller;
    }

    
    /** Statement Methods*/
    
    /*
     * fetches Person and Location data from the db;
     * creates Person instance, which is populated with the fetched data;
     * prompts for wClause and retrieveAssociation;
     */
    private Person singleWhere(String wClause, boolean retrieveAssociation) {
		
	//the data that is going to be retrieved from the db will be stored here;
	ResultSet results;
	//empty Person instance, which is going to be used as container for the fetched data;
	Person person = new Person(); 
	
	//building SELECT query, see private String buildQuery(wClause);
	String query = buildQuery(wClause);
	
	try {//fetching data from db;
	    
	    Statement stmt = con.createStatement();
	    stmt.setQueryTimeout(5); //Sets query time out at 5 seconds;
	    
	    //executes the query and gets Person data;
	    results = stmt.executeQuery(query);
	    
	    //checks if there is any Person at all;
	    //if there is, Person will be built;
	    if(results.next()) {
		
		//populates Person instance with retrieved data;
		person = buildPerson(results);
		stmt.close();
		
		//there shouldn't be any retrieve association;
		//in case the boolean condition returns true it will throw an exception;
		if(retrieveAssociation) {
		    throw new IllegalArgumentException("There is no association to be retrieved");
		}
		
	    } else {
		person = null;//Person data wasn't found;
	    }
	    
	} catch(SQLException cantFetch) {
	    System.out.println("Error: Fetching Person data fails");
	    cantFetch.getCause();  
	}
	
	return person; //returns single Person instance to the caller;
    }	
    

    /*
     * fetches multiple Person data from the db;
     * creates multiple instances, which are populated with the fetched data;
     * there's a while loop and also ArrayList is used;
     * prompts for wClause and retrieveAssociation;
     */
    private ArrayList<Person> miscWhere(String wClause, boolean retrieveAssociation) {
	
	//the data that is going to be retrieved from the db will be stored here;
	ResultSet results;
	//ArrayList is needed because multiple Person instances are going to be built;	
	ArrayList<Person> persons = new ArrayList<Person>();
	//empty Person instance, which is going to be used as container for the fetched data;
	Person person = new Person(); 	
	
	//building SELECT query, see private String buildQuery(wClause);
	String query = buildQuery(wClause);
		
	try {//fetching data from db;
	    
	    Statement stmt = con.createStatement();
	    stmt.setQueryTimeout(5); //Sets query time out at 5 seconds;
	    
	    //executes the query and gets Person data;
	    results = stmt.executeQuery(query);
	    
	    //while loop is used, because multiple ResultSets and multiple built Persons are expected;
	    while(results.next()) {
		
		//populates Person instance with retrieved data;
		person = buildPerson(results);
		
		//there shouldn't be any retrieve association;
		//in case the boolean condition returns true it will throw an exception;
		if(retrieveAssociation) {
		    throw new IllegalArgumentException("There is no association to be retrieved");
		}
		
		//adds the new instance to the list;
		persons.add(person);	   
	   
	    }// loop ends, because there are no more persons to be retrieved;
	    stmt.close();      		

	} catch(SQLException cantFetch) {
	    System.out.println("Error: Fetching Person data fails");
	    cantFetch.getCause(); 
	}
	
	return persons; //returns an ArrayList of Person;
    }

    
    /** Building Methods*/
    
    /* 
     * transforms data from relational to object model;
     * creates an instance of Person;
     * populates the instance with data retrieved from the ResultSet;
     * returns Person instance;
     */
    private Person buildPerson(ResultSet data) {
    	String position = null;
		Person person = null; //needed, because it will contain the data (container);
		
		try{
			position = data.getString("personType");
		}
		catch (Exception e){
			System.out.println("Error in getting staffType");
		}
		
		if(position.equals("Customer")) person = new Customer();
		else person = new Staff();
			
		
	try { //populating the container;			/* VALUE		*/
	    person.setPersonID(data.getInt("personID"));	/* personID		*/
	    person.setCPR(data.getString("CPR"));		/* CPR			*/
	    person.setFname(data.getString("fname"));		/* fname		*/
	    person.setLname(data.getString("lname"));		/* lname		*/
	    person.setAddress(data.getString("address"));	/* address		*/
	    person.setZIP(data.getString("locationZIP"));	/* locationZIP (FK)	*/
	    person.setCountry(data.getString("country"));	/* country (FK)		*/
	    person.setEmail(data.getString("email"));		/* email		*/
	    person.setPassword(data.getString("password"));	/* password		*/
	    person.setCity(data.getString("city"));		/* city			*/
	    
	    // person.setPersonType(data.getString("personType")); /* personType - we don't need it, it's a db issue;
	    
	} catch(Exception cantBuild) {
	    System.out.println("Error: Person instance can't be built");
	    cantBuild.getCause();
	}
	
	return person; //returns person to the method caller;
    }	
    
    
    /*
     * prompts for wClause (WHERE clause);
     * builds SELECT and RIGHT JOIN queries;
     * <!>applies RIGHT JOIN on Person and Location tables;
     * adds WHERE clause to the query, in case a Person must be obtained that satisfies a given conditions;
     * returns String query;
     */
    private String buildQuery(String wClause) {
	
	/* selects all columns in a specified table */
	
	String query =  "SET DATEFORMAT dmy; " +
			"SELECT * FROM Person RIGHT JOIN Location " +
			"ON Person.locationZIP=Location.ZIP"; 
	
	if(wClause.length()>0) {
	    query += " WHERE " + wClause; //adding the conditions to the query;
	}
	
	query += ";";
	
	return query; //returns query to the method caller;
    }


    /** Other Methods */
    
    /*
     * inserts Location data of Person instance in db; 
     * returns a row count number at success;
     * the row count is initialized at -1, to indicate failure, in case the method fails;
     * creates INSERT query; 
     * affects Location table;
     */
    private int insertLocation(Person person) {
	int rc = -1; //row count number;
	
	//building INSERT query;
	String query =  "SET DATEFORMAT dmy; " + 		  /* SETS DATA FORMAT	*/
			"INSERT INTO Location "   +		  /* SPECIFIES TABLE	*/
			"(ZIP, country, city) "   +	  	  /* SPECIFIES COLUMNS  */
			"VALUES (" +				  /* INSERTS THE VALUES */
			"'" + person.getZIP()		+ "', " + /* ZIP 		*/
			"'" + person.getCountry()	+ "', " + /* country		*/
			"'" + person.getCity()		+ "');";  /* city		*/
	//INSERT query building completed;
		
	try { //executing the query and inserting Person data;
	    	
	    Statement stmt = con.createStatement();
	    stmt.setQueryTimeout(5); //Sets query time out at 5 seconds;
		
	    //executes the query and gets the row count number;
	    rc = stmt.executeUpdate(query);
	    stmt.close();
	    
	} catch(SQLException cantInsert) {
	    System.out.println("Error: INSERT Location data fails ");
	    cantInsert.getCause(); //shows the cause for exception;
	}
		
	return rc; //returns the row count to the method caller;
    }
}

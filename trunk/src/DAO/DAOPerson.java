package DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Models.Person;

public class DAOPerson implements IFDAOPerson {
    
    //needed, so a connection with the db can be established;
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
     * gets Person data from the db found by ID;
     * returns Person instance to the controller;
     * prompts for personID and retrieveAssociation;
     */
    public Person getPerson(int personID, boolean retrieveAssociation) {
	
	//Defining search criteria, to find Person by personID;
	String wClause = "personID = " + personID;
	
	//See private Person singleWhere(wClause, retrieveAssociation);
	return singleWhere(wClause, retrieveAssociation);
    }
    
    
    @Override
    /*
     * gets Person data from the db found by CPR number;
     * returns Person instance to the controller;
     * prompts for CPR and retrieveAssociation;
     */
    public Person getPerson(String CPR, boolean retrieveAssociation) {
	
	//Defining search criteria, to find Person by personCPR;
	String wClause = "CPR = " + CPR;
		
	//See private Person singleWhere(wClause, retrieveAssociation);
	return singleWhere(wClause, retrieveAssociation);
    }
    
    
    @Override
    /*
     * gets Person data from the db found by first and last name;
     * returns an ArrayList of Person instances to the controller;
     * ArrayList is used, because the possibility that
     * several persons have the same names must be considered;
     * prompts for fname and lname, and retrieveAssociation;
     */
    public ArrayList<Person> getPerson(String fname, String lname, boolean retrieveAssociation) {

	//Defining search criteria, to find Person by first and last name;
	String wClause = "fname = " + fname + " AND lname = " + lname;
			
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
     */
    public int insert(Person person, String personType) {
	
	int rc = -1; //row count; 
	
	//building INSERT query;
	String query =  "SET DATEFORMAT dmy; " + 		  /* SETS DATA FORMAT	*/
			"INSERT INTO Person "  +		  /* SPECIFIES COLUMNS	*/
			"(personID, CPR, fname, lname, "  +	  /* Personal Data     	*/
			"address, locationZIP, country, " +	  /* Location Data      */
			"email, password, personType) "   +	  /* Credentials Data   */
			"VALUES (" +				  /* INSERTS THE VALUES */
			""  + person.getPersonID()	+ ", " +  /* personID		*/
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
	
	return rc; //returns the row count to the controller;
    }

    
    @Override
    /*
     * updates information about specified Person in db; 
     * returns a row count number at success;
     * the row count is initialized at -1, to indicate failure, in case the method fails; 
     * creates UPDATE query;
     */
    //updates information about particular Person instance;
    public int update(Person person, String personType) {
	
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
	
	return rc; //returns the row count to the controller;
    }
    
    
    @Override
    /*
     * deletes information about specified Person in db; 
     * returns a row count number at success;
     * the row count is initialized at -1, to indicate failure, in case the method fails; 
     * creates DELETE query;
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
     * fetches Person data from the db;
     * builds one instance, which is populated with the fetched data;
     * prompts for wClause and retrieveAssociation;
     */
    private Person singleWhere(String wClause, boolean retrieveAssociation) {
	
	//the data thats is going to be retrieved from the db will be stored here;
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
	    
		if(retrieveAssociation) {//if there is association, retrieve it;
		//do we need retrieve association? :)    
		}
		
	    } else {
		person = null;//Person data wasn't found;
	    }
	
	} catch(SQLException cantFetch) {
	    System.out.println("Error: Fetching Person data fails");
	    cantFetch.getCause();  
	}
	
	return person;
    }	
    

    /*
     * fetches multiple Person data from the db;
     * builds multiple instances, which are populated with the fetched data;
     * there's a while loop;
     * prompts for wClause and retrieveAssociation;
     */
    private ArrayList<Person> miscWhere(String wClause, boolean retrieveAssociation) {
	
	//the data thats is going to be retrieved from the db will be stored here;
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
	    
	    //while loop is used, because multiple ResultSets are expected;
	    while(results.next()) {
		
		//populates Person instance with retrieved data;
		person = buildPerson(results);
		
		if(retrieveAssociation) {//if there is association, retrieve it;
			//do we need retrieve association? :)    
		}
		
		//adds the new instance to the list;
		persons.add(person);
			   
	    }// loop ends, because there are no more persons to be retrieved;
	    stmt.close();      		

	} catch(SQLException cantFetch) {
	    System.out.println("Error: Fetching Person data fails");
	    cantFetch.getCause(); 
	}
	
	return persons;
    }

    
    /** Building Methods*/
    
    /* 
     * transforms data from relational to object model;
     * creates an instance of Person;
     * populates the instance with data retrieved from the ResultSet;
     * returns Person instance;
     */
    private Person buildPerson(ResultSet data) {
	
	Person person = new Person(); //needed, because it will contain the data (container);
	
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
	    
	    // person.setPersonType(data.getString("personType")); /* personType - we don't need it
	    
	} catch(Exception cantBuild) {
	    System.out.println("Error: Person instance can't be built");
	    cantBuild.getCause();
	}
	
	return person; //returns person to the method caller;
    }	
    
    
    /*
     * prompts for wClause (WHERE clause);
     * builds SELECT query;
     * adds WHERE clause to the query, in case a Person must be obtained that satisfies given conditions;
     * returns String query;
     */
    private String buildQuery(String wClause) {
									
	String query = "SET DATEFORMAT dmy;" + " SELECT * FROM Person";	/* selects all columns in Person */
	
	if(wClause.length()>0) {
	    query += " WHERE " + wClause; //adding the conditions to the query;
	}
	
	return query; //returns query to the method caller;
    }

}

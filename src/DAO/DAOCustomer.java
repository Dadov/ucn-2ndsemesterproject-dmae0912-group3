package DAO; //indicates the location of the class;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Models.Customer;
//a connection with the db must be established;
//storage for the data which is going to be retrieved from the db; 
//exceptions must be handle, if an operation fails;
//SQL queries, built in this class, must be executed;
//in case, multiple Customer instances must be retrieved;
//business class, where data fetched from the db will be stored;

/**
 * separates the db access code from the business class Customer; contains all
 * necessary SQL-code for db access and data manipulation; consists of 2 get and
 * 3 update methods; responsible for editing Customer table; calls DAOPerson
 * when a new Customer is being inserted; for its interface check IFDAOCustomer;
 */
public class DAOCustomer implements IFDAOCustomer {

	// needed, so data in the db can be retrieved or manipulated;
	private Connection con;

	/** Constructor */

	/*
	 * creates DAOCustomer; initializes the reference to the db connection
	 * instance;
	 */
	public DAOCustomer() {
		// gets the instance for db connection;
		con = DBConnection.getInstance().getDBCon();
	}

	/** Get Methods */

	@Override
	/*
	 * gets Customer data from the db found by customerID; returns Customer
	 * instance to the controller; prompts for customerID and
	 * retrieveAssociation;
	 */
	public Customer getCustomer(int customerID, boolean retrieveAssociation) {

		// Defining search criteria, to find Customer by customerID;
		String wClause = "customerID = " + customerID;

		// See private Customer singleWhere(wClause, retrieveAssociation);
		return singleWhere(wClause, retrieveAssociation);
	}

	@Override
	/*
	 * gets all Customer data from the db; there is no search criteria, because
	 * all Customer data will be retrieved; ArrayList is used, because there
	 * will be multiple Customer instances; prompts for retrieveAssociation;
	 */
	public ArrayList<Customer> getAllCustomers(boolean retrieveAssociation) {

		// wClause is empty, because all Customers must be retrieved, so there's
		// no condition;
		return miscWhere("", retrieveAssociation);
	}

	/** Update Methods */

	@Override
	/*
	 * inserts Customer instance information in db; returns a row count number
	 * at success; the row count is initialized at -1, to indicate failure, in
	 * case the method fails; creates INSERT query; affects Customer, Person and
	 * Location tables; on call, calls DAOPerson insert method, which edits
	 * Location and Person tables, and then it edits the Customer table; prompts
	 * for Customer instance;
	 */
	public int insert(Customer customer) {

		int rc = -1; // row count;

		// creates DAOPerson instance to insert data in Person and Location
		// tables;
		IFDAOPerson dao = new DAOPerson();
		// passes the Customer instance to DAOPerson and indicates that
		// personType is Customer;
		dao.insert(customer, "Customer");

		// building INSERT query;
		String query = "SET DATEFORMAT dmy; " + /* SETS DATA FORMAT */
		"INSERT INTO Customer " + /* SPECIFIES COLUMNS */
		"(customerID, registrationDate, noOfStays) " + /* Customer Data */
		"VALUES (" + /* INSERTS THE VALUES */
		"(SELECT IDENT_CURRENT('Person')), " + /* customerID */
		"'" + customer.getRegistrationDate() + "', " + /* registrationDate */
		"" + customer.getNoOfStays() + ");"; /* noOfStays */
		// INSERT query building completed;
		// we don't need to insert personID in the db, 'cause it is
		// auto-incremented (IDENTITY);

		try { // executing the query and inserting Customer data;

			Statement stmt = con.createStatement();
			stmt.setQueryTimeout(5); // Sets query time out at 5 seconds;

			// executes the query and gets the row count number;
			rc = stmt.executeUpdate(query);
			stmt.close();

		} catch (SQLException cantInsert) {
			System.out.println("Error: INSERT Customer fails ");
			cantInsert.getCause(); // shows the cause for exception;
		}

		return rc; // returns the row count to the controller;
	}

	@Override
	/*
	 * updates information about specified Customer in db; returns a row count
	 * number at success; the row count is initialized at -1, to indicate
	 * failure, in case the method fails; creates UPDATE query; affects
	 * Customer, Person and Location tables; on call, calls DAOPerson update
	 * method, which edits Location and Person tables, and then it edits the
	 * Customer table; prompts for Customer instance;
	 */
	public int update(Customer customer) {
		int rc = -1; // row count;

		// creates DAOPerson instance to update data in Person and Location
		// tables;
		IFDAOPerson dao = new DAOPerson();
		// passes the Customer instance to DAOPerson and indicates the
		// personType;
		dao.update(customer, "Customer");

		// building UPDATE query;
		String query = "SET DATEFORMAT dmy;" + /* SETS DATA FORMAT */
		"UPDATE Customer SET " + /* SETS NEW DATA FOR */
		"registrationDate = '" + customer.getRegistrationDate() + "', " + /* registrationDate */
		"noOfStays = " + customer.getNoOfStays() + /* noOfStays */
		"WHERE customerID = " + customer.getPersonID() + ";"; /*
															 * SPECIFIES FOR
															 * WHICH
															 */
		/* TO APPLY THE UPDATES */
		// UPDATE query building completed;
		System.out.println(query);

		try { // executing the query and updating Customer data;

			Statement stmt = con.createStatement();
			stmt.setQueryTimeout(5); // Sets query time out at 5 seconds;

			// executes the query and gets the row count number;
			rc = stmt.executeUpdate(query);
			System.out.println(rc);
			stmt.close();

		} catch (SQLException cantUpdate) {
			System.out.println("Error: UPDATE Customer fails "
					+ cantUpdate.getCause());
			// ; //shows the cause for exception;
		}

		return rc;
	}

	@Override
	/*
	 * deletes information about specified Customer in db found by customerID;
	 * returns a row count number at success; the row count is initialized at
	 * -1, to indicate failure, in case the method fails; creates DELETE query;
	 * affects Customer, Person and Location tables; on call, edits the Customer
	 * table first and then calls DAOPerson delete method, which edits Person
	 * table; prompts for Customer instance;
	 */
	public int delete(int customerID) {

		int rc = -1; // row count;

		// building DELETE query;
		String query = "DELETE FROM Customer WHERE customerID = " + customerID
				+ ";";

		try { // executing the query and deleting Customer data;

			Statement stmt = con.createStatement();
			stmt.setQueryTimeout(5); // Sets query time out at 5 seconds;

			// executes the query and gets the row count number;
			rc = stmt.executeUpdate(query);
			stmt.close();

		} catch (SQLException cantDelete) {
			System.out.println("Error: DELETE Customer fails ");
			cantDelete.getCause(); // shows the cause for exception;
		}

		// creates DAOPerson instance to delete data in Person and Location
		// tables;
		IFDAOPerson dao = new DAOPerson();
		// passes the Customer instance to DAOPerson;
		dao.delete(customerID);

		return rc; // returns the row count to the controller;
	}

	/** Statement Methods */

	/*
	 * fetches Customer data from the db; creates Customer instance, which is
	 * populated with the fetched data; prompts for wClause and
	 * retrieveAssociation;
	 */
	private Customer singleWhere(String wClause, boolean retrieveAssociation) {

		// the data thats is going to be retrieved from the db will be stored
		// here;
		ResultSet results;
		// empty Customer instance, which is going to be used as container for
		// the fetched data;
		Customer customer = new Customer();

		// needed to retrieve Customer's personal data(CPR, Fname, Lname, etc.);
		IFDAOPerson daoPerson = new DAOPerson();

		// Customer customer = (Customer)
		// daoPerson.getPerson(Integer.parseInt(wClause.replaceAll("\\D+","")),
		// false);
		// Person person =
		// daoPerson.getPerson(Integer.parseInt(wClause.replaceAll("\\D+","")),
		// false);
		// Customer customer = new customer();
		// customer = (customer) person;
		// Person person = new Customer();

		// building SELECT query, see private String buildQuery(wClause);
		String query = buildQuery(wClause);

		try {// fetching data from db;

			Statement stmt = con.createStatement();
			stmt.setQueryTimeout(5); // Sets query time out at 5 seconds;

			// executes the query and gets Customer data;
			results = stmt.executeQuery(query);

			// checks if there is any Customer at all;
			// if there is, Customer will be built;
			if (results.next()) {
				int ID = results.getInt("customerID");
				// populates Customer instance with data from Person and
				// Location tables;
				customer = (Customer) daoPerson.getPerson(ID, false);
				// populates Customer instance with data from Customer table;
				customer = buildCustomer(results, customer);
				stmt.close();

				// there shouldn't be any retrieve association;
				// in case the boolean condition returns true it will throw an
				// exception;
				if (retrieveAssociation) {
					throw new IllegalArgumentException(
							"There is no association to be retrieved");
				}

			} else {
				customer = null;// Customer data wasn't found;
			}

		} catch (SQLException cantFetch) {
			System.out.println("Error: Fetching Customer data fails");
			cantFetch.getCause();
		}

		return customer;
	}

	/*
	 * fetches multiple Customer data from the db; creates multiple instances,
	 * which are populated with the fetched data; there's a while loop and also
	 * ArrayList is used; prompts for wClause and retrieveAssociation;
	 */
	private ArrayList<Customer> miscWhere(String wClause,
			boolean retrieveAssociation) {
		IFDAOPerson daoPerson = new DAOPerson();
		// the data thats is going to be retrieved from the db will be stored
		// here;
		ResultSet results;
		// ArrayList is needed because multiple Customer instances are going to
		// be built;
		ArrayList<Customer> customers = new ArrayList<Customer>();
		// empty Customer instance, which is going to be used as container for
		// the fetched data;
		Customer customer = new Customer();

		// needed to retrieve Customer's personal data(CPR, Fname, Lname, etc.);
		// IFDAOPerson daoPerson = new DAOPerson();

		// building SELECT query, see private String buildQuery(wClause);
		String query = buildQuery(wClause);

		try {// fetching data from db;

			Statement stmt = con.createStatement();
			stmt.setQueryTimeout(5); // Sets query time out at 5 seconds;

			// executes the query and gets Customer data;
			results = stmt.executeQuery(query);

			// while loop is used, because multiple ResultSets are expected;
			// also, multiple Customer instances are going to be built;
			while (results.next()) {
				int ID = results.getInt("customerID");
				// populates Customer instance with data from Person and
				// Location tables;
				customer = (Customer) daoPerson.getPerson(ID, false);
				// populates Customer instance with data from Person and
				// Location tables;
				// populates Customer instance with data from Customer table;
				customer = buildCustomer(results, customer);

				// there shouldn't be any retrieve association;
				// in case the boolean condition returns true it will throw an
				// exception;
				if (retrieveAssociation) {
					throw new IllegalArgumentException(
							"There is no association to be retrieved");
				}

				// adds the new instance to the list;
				customers.add(customer);

			}// loop ends, because there are no more customers to be retrieved;
			stmt.close();

		} catch (SQLException cantFetch) {
			System.out.println("Error: Fetching Customer data fails");
			cantFetch.getCause();
		}

		return customers;
	}

	/** Building Methods */

	/*
	 * transforms data from relational to object model; creates an instance of
	 * Customer; populates the instance with data retrieved from the ResultSet;
	 * returns Customer instance;
	 */
	private Customer buildCustomer(ResultSet data, Customer customer) {

		// needed, because it will contain the data (container);
		// Customer customer = new Customer();

		try { // populating the container; /* VALUE */
			customer.setRegistrationDate(data.getString("dateFormated")); /* registrationDate */
			customer.setNoOfStays(data.getInt("noOfStays")); /* noOfStays */

		} catch (Exception cantBuild) {
			System.out.println("Error: Customer instance can' be built");
			cantBuild.getCause();
		}

		return customer; // returns customer to the method caller;
	}

	/*
	 * prompts for wClause (WHERE clause); builds SELECT query; adds WHERE
	 * clause to the query, in case a Customer must be obtained that satisfies
	 * given conditions; returns String query;
	 */
	private String buildQuery(String wClause) {

		String query = "SELECT *, CONVERT(CHAR(10), registrationDate, 105) AS dateFormated FROM Customer"; /*
																											 * selects
																											 * all
																											 * columns
																											 * in
																											 * Person
																											 */
		// System.out.println(query);
		if (wClause.length() > 0) {
			query += " WHERE " + wClause; // adding the conditions to the query;
		}
		System.out.println(query);

		return query; // returns query to the method caller;
	}

}

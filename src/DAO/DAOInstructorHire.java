package DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Models.Activity;
import Models.ActivityBooking;
import Models.ActivityTime;
import Models.Customer;
import Models.Instructor;
import Models.InstructorHire;

public class DAOInstructorHire implements IFDAOInstructorHire {

	private Connection con; // The reference is needed for building a connection
							// with the Database;

	public DAOInstructorHire() {
		con = DBConnection.getInstance().getDBCon();
	}


	@Override
	public InstructorHire getInstructorHire(int ID, boolean retrieveAssociation) {
		String wClause = "instructorHireID = " + ID; // Creates a search
														// criteria, this will
														// make to get that
														// instructorHire, which
														// fulfills the clause;
		return singleWhere(wClause, retrieveAssociation);
	}

	@Override
	public ArrayList<InstructorHire> getInstructorHires(
			boolean retrieveAssociation) {
		return miscWhere("", retrieveAssociation);// Note: wClause is empty,
													// because we want to get
													// all instructorHires, so
													// no condition(clause) is
													// needed;
	}

	@Override
	public int insert(InstructorHire instructorHire) {
		int rc = -1; // row count, it is set to -1, to guarantee nothing will
						// happen, in case the method fails to fulfill the task;

		// creates a query for data insertion;
		String query = "SET DATEFORMAT dmy;"
				+ " INSERT INTO InstructorHire(customerID, instructorID, activityBookingID, hireDate, hireTime) VALUES("
				+ instructorHire.getCustomer().getPersonID() + ","
				+ instructorHire.getInstructor().getPersonID() + ","
				+ instructorHire.getActivityBooking().getID() + ",'"
				+ instructorHire.getActivityTime().getDate() + "','"
				+ instructorHire.getActivityTime().getTime() + "');";

		System.out.println("Insert query : " + query);

		try { // creating a statement and inserting instructorHire in database;

			Statement stmt = con.createStatement();
			stmt.setQueryTimeout(5);
			rc = stmt.executeUpdate(query); // tries to get row count number;
			stmt.close();
		}// try ends;

		catch (SQLException ex) { // error, exception call;
			System.out
					.println("Error, instructorHire wasn't inserted in the database");
		}
		return rc; // returns the row count to controller;
	}

	@Override
	public int update(InstructorHire instructorHire) {
		int rc = -1; // row count, it is set to -1, to guarantee nothing will
						// happen, in case the method fails to fulfill the task;

		// creates update data query;
		String query = "SET DATEFORMAT dmy; " + " UPDATE InstructorHire SET "
				+ "customerID = " + instructorHire.getCustomer().getPersonID()
				+ "," + "instructorID = "
				+ instructorHire.getInstructor().getPersonID() + ","
				+ "activityBookingID = "
				+ instructorHire.getActivityBooking().getID() + ","
				+ "hireDate = '" + instructorHire.getActivityTime().getDate()
				+ "'," + "hireTime = '"
				+ instructorHire.getActivityTime().getTime() + "'"
				+ " WHERE instructorHireID = " + instructorHire.getId() + ";";

		System.out.println("Update query : " + query);

		try { // creating a statement and modifying a selected instructorHire's
				// data in database;
			Statement stmt = con.createStatement();
			stmt.setQueryTimeout(5);
			rc = stmt.executeUpdate(query); // tries to get row count number;
			stmt.close();
		}// try ends;
		catch (Exception e) { // error, exception call;
			System.out.println("instructorHire update fails");
			e.getMessage();
		}
		return rc; // returns the row count to controller;
	}

	@Override
	public int delete(int ID) {
		int rc = -1; // row count, it is set to -1, to guarantee nothing will
						// happen, in case the method fails to fulfill the task;
		// creates a deletion query;
		String query = "DELETE FROM InstructorHire WHERE instructorHireID = "
				+ ID + ";";

		System.out.println("Delete query : " + query);

		try { // creating a statement and deleting a selected instructorHire
				// from database;
			Statement stmt = con.createStatement();
			stmt.setQueryTimeout(5);
			rc = stmt.executeUpdate(query);
			stmt.close();
		} catch (Exception e) { // error, exception call;
			System.out.println("InstructorHire deletion fails");
			e.getMessage();
		}

		return rc; // returns the row count to controller;

	}

	private InstructorHire singleWhere(String wClause,
			boolean retrieveAssociation) {
		ResultSet results; // the results retrieved from the database will be
							// stored here;
		InstructorHire instructorHire = new InstructorHire(); // creates an
																// empty
																// instructorHire
																// instance,
																// which will be
																// populated
																// with
																// retrieved
																// data;

		String query = buildQuery(wClause); // creates SELECT query for
											// instructorHire table
		try { // fetching the instructorHire data from the database;
			Statement stmt = con.createStatement(); // Creates a statement, that
													// will be executed;
			stmt.setQueryTimeout(5);
			results = stmt.executeQuery(query); // Storing the results from the
												// statement execution;

			if (results.next()) { // checks if there are any instructorHire in
									// the database at all;
				instructorHire = buildInstructorHire(results);// builds
																// instructorHire
				stmt.close();
				if (retrieveAssociation) {// retrieves the customer object
					IFDAOCustomer dbCust = new DAOCustomer();
					int custID = instructorHire.getCustomer().getPersonID();
					Customer cust = dbCust.getCustomer(custID, false);
					instructorHire.setCustomer(cust);

					IFDAOStaff dbInst = new DAOStaff();
					int instID = instructorHire.getInstructor().getPersonID();
					Instructor staff = (Instructor) dbInst.getStaff(instID, false);
					instructorHire.setInstructor(staff);

					IFDAOActivityBooking dbActiv = new DAOActivityBooking();
					int actID = instructorHire.getActivityBooking().getID();
					ActivityBooking activityBooking = dbActiv
							.getActivityBooking(actID, true);
					instructorHire.setActivityBooking(activityBooking);
				}
			} else { // nothing was found
				instructorHire = null;
			}
		}// try ends;

		catch (Exception e) {
			System.out.println("Query exception: " + e);
		}
		return instructorHire;
	}

	private ArrayList<InstructorHire> miscWhere(String wClause,
			boolean retrieveAssociation) {
		ResultSet results; // the results retrieved from the database will be
							// stored here;
		ArrayList<InstructorHire> hires = new ArrayList<InstructorHire>(); // here
																			// instructorHire
																			// instances
																			// will
																			// be
																			// stored;
		InstructorHire instructorHire = new InstructorHire(); // creates an
																// empty
																// instructorHire
																// instance,
																// which will be
																// populated
																// with
																// retrieved
																// data;

		String query = buildQuery(wClause);

		try { // fetching the instructorHire data from the database;
			Statement stmt = con.createStatement(); // Creates a statement, that
													// will be executed;
			stmt.setQueryTimeout(5);
			results = stmt.executeQuery(query); // Storing the results from the
												// statement execution;

			while (results.next()) { // there is another row;

				instructorHire = buildInstructorHire(results); // calls a method
																// which will
																// populate
																// instructorHire
																// the instance
																// with data;
				hires.add(instructorHire); // adding the built object to the
											// list;
			}// while loop ends, because there are no more rows;
			stmt.close();

			if (retrieveAssociation) {// retrieves the customer objects
				IFDAOCustomer dbCust = new DAOCustomer();
				for (InstructorHire instructorHire1 : hires) {

					int custID = instructorHire1.getCustomer().getPersonID();
					Customer cust = dbCust.getCustomer(custID, false);
					instructorHire1.setCustomer(cust);

					IFDAOStaff dbInst = new DAOStaff();
					int instID = instructorHire.getInstructor().getPersonID();
					Instructor instructor = (Instructor) dbInst.getStaff(
							instID, false);
					instructorHire1.setInstructor(instructor);

					IFDAOActivityBooking dbActiv = new DAOActivityBooking();
					int actID = instructorHire.getActivityBooking().getID();
					ActivityBooking activityBooking = dbActiv
							.getActivityBooking(actID, true);
					instructorHire1.setActivityBooking(activityBooking);
				}
			}
		}// try ends;

		catch (Exception e) { // e.g. no instructorHire are found in the
								// database, this will result in an empty
								// OrderList;
			System.out.println("Query exception - select: " + e);
			e.printStackTrace();
		}

		return hires; // returns the OrderList;
	}

	private InstructorHire buildInstructorHire(ResultSet results) {
		
		Customer cust = new Customer();	
		Instructor instructor = new Instructor();
		ActivityTime activTime = new ActivityTime();
		ActivityBooking activBook = new ActivityBooking(0,new ArrayList<Customer>(),new Activity(), activTime,true,true);
		InstructorHire instructorHire = new InstructorHire(cust,instructor,activBook,activTime); // storage;

		try { // retrieving data from the InstructorHire table, by using the
				// columns;
			instructorHire.setId(results.getInt("instructorHireID"));
			cust.setPersonID(results.getInt("customerID"));
			activBook.setID(results.getInt("activityBookingID"));
			instructor.setPersonID(results.getInt("instructorID"));
			activTime.setDate(results.getString("hireDate"));
			String time = results.getString("hireTime");
			activTime.setTime(time.substring(0,5));

		} catch (Exception e) {
			System.out.println("error in building the instructorHire object");
		}
		return instructorHire;
	}

	private String buildQuery(String wClause) {
		String query = "SET DATEFORMAT dmy;"
				+ " SELECT instructorHireID, customerID, activityBookingID, instructorID, hireDate, hireTime FROM InstructorHire";
		if (wClause.length() > 0) {
			query = query + " WHERE " + wClause; // this query will retrieve
													// that RoomsBooked instance
													// data, which fulfils a
													// specified condition;
		}

		return query;
	}


	@Override
	public boolean checkInstructorAvailability(Instructor instructor,
			String date, String time) {
		// TODO Auto-generated method stub
		return false;
	}

}

package DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Models.Room;
import Models.RoomBooking;
import Models.Customer;

public class DAORoomBooking implements IFDAORoomBooking {

	private  Connection con; //The reference is needed for building a connection with the Database;
	
	public DAORoomBooking() {
		con = DBConnection.getInstance().getDBCon();
	}

	@Override
	public RoomBooking getRoomBooking(int id, boolean retrieveAssociation) {
		String wClause = "bookingID = " + id; //Creates a search criteria, this will make to get that RoomBooking, which fulfils the clause;
		String wClause2 = "roomBookingID = " + id; //where clause needed for retrieving room list for room booking 
		System.out.println("wClause: " + wClause);
		System.out.println("wClause2: " + wClause2);
		return singleWhere(wClause, wClause2, retrieveAssociation);  
	}

	@Override
	public ArrayList<RoomBooking> getAllRoomBookings(boolean retrieveAssociation) {
		return miscWhere("", retrieveAssociation); //Note: wClause is empty, because we want to get all RoomBookings, so no condition(clause) is needed;
	}

	@Override
	public int insert(RoomBooking roomBooking) {
        int rc = -1; //row count, it is set to -1, to guarantee nothing will happen, in case the method fails to fulfil the task;
        
        //creates a query for data insertion;
        String query = "SET DATEFORMAT dmy;"+ 
        		" INSERT INTO RoomBooking(customerID, dateStart, dateEnd, dateBooked) VALUES(" +
				roomBooking.getCustomer().getPersonID() + ",'" +
				roomBooking.getDateStart() + "','" +
				roomBooking.getDateEnd() + "','" +
				roomBooking.getDateBooked() + "');";
       
		System.out.println("Insert query : " + query); 
		
		try{ //creating a statement and inserting RoomBooking in database;
         
			Statement stmt = con.createStatement();
			stmt.setQueryTimeout(5);
			rc = stmt.executeUpdate(query); //tries to get row count number;
			stmt.close();
		}//try ends;
		
		
       catch(SQLException ex){ //error, exception call;
          System.out.println("roomBooking was not inserted in the database");
       }
		insertRoomsBooked(roomBooking.getRoomsBooked(),-1);//insert booked rooms in RoomsBooked table, see the method
		
		System.out.println("Row count in insert (DAORoomBooking): " + rc);
        return rc; //returns the row count to controller;
	}
	
	/**
	 *@param rooms - list of rooms, which are booked in one room booking
	 *@param id - id of the room booking 
	 */
	public int insertRoomsBooked(ArrayList<Room> rooms, int id){
		int rc = -1; //row count, it is set to -1, to guarantee nothing will happen, in case the method fails to fulfil the task;
		if(id==-1)id = getLastInsertedID();//if id is -1, it means we are looking for the last inserted id (by inserting) 
		
		//create query for inserting rooms booked
		String query = "SET DATEFORMAT dmy;"; 
		for(Room room: rooms){
	        	query = query + " INSERT INTO RoomsBooked(roomBookingID, roomNumber) VALUES(" +
	        			id + "," +
	        			room.getNumber() + ");";
	        } 
		 try{ //creating a statement and inserting RoomBooking in database;
	         
				Statement stmt = con.createStatement();
				stmt.setQueryTimeout(5);
				rc = stmt.executeUpdate(query); //tries to get row count number;
				stmt.close();
			}//try ends;
			
			
	       catch(SQLException ex){ //error, exception call;
	          System.out.println("roomsBooked were not inserted in the database");
	       }

       return rc; //returns the row count to controller;
	}

	@Override
	public int update(RoomBooking roomBooking) {
		int rc = -1; //row count, it is set to -1, to guarantee nothing will happen, in case the method fails to fulfill the task;
		
		//creates update data query;
		String query = "SET DATEFORMAT dmy;"+ 
				"UPDATE RoomBooking SET " +
				"customerID = " + roomBooking.getCustomer().getPersonID() + "," +
				"dateStart = '" + roomBooking.getDateStart() + "'," +
				"dateEnd = '" + roomBooking.getDateEnd() + "'," +
				"dateBooked = '" + roomBooking.getDateBooked() + "'" +
				" WHERE bookingID = " + roomBooking.getId() + ";";
		
		System.out.println("Update query : " + query);
	
		try { //creating a statement and modifying a selected RoomBooking's data in database;
			Statement stmt = con.createStatement();
			stmt.setQueryTimeout(5);
			rc = stmt.executeUpdate(query); //tries to get row count number;
			stmt.close();
		}//try ends;
		catch (Exception e) { //error, exception call;
			System.out.println("roomBooking update fails");
			e.getMessage();
		}
		deleteRoomsBooked(roomBooking.getId());//deletes all records from roomsBooked table with certain id
		insertRoomsBooked(roomBooking.getRoomsBooked(),roomBooking.getId());//inserts all rooms booked
		return rc; //returns the row count to controller;
	}

	@Override
	public int delete(int id) {
		int rc = -1; //row count, it is set to -1, to guarantee nothing will happen, in case the method fails to fulfil the task;
		deleteRoomsBooked(id);
		//creates a deletion query;
		String query = "DELETE FROM RoomBooking WHERE bookingID = " + id + ";";
				
		System.out.println("Delete query : " + query);
		
		try { //creating a statement and deleting a selected RoomBooking from database;
			Statement stmt = con.createStatement();
			stmt.setQueryTimeout(5);
			rc = stmt.executeUpdate(query);
			stmt.close();	
		}
		catch (Exception e) { //error, exception call;
			System.out.println("RoomBooking deletion fails");
			e.getMessage();
		}
		
		return rc; //returns the row count to controller;
	}
	
	/**
	 * This method deletes all room numbers from table roomsBooked with roomBookingID equal to @param id 
	 **/
	public int deleteRoomsBooked(int id){
		int rc = -1; //row count, it is set to -1, to guarantee nothing will happen, in case the method fails to fulfil the task;
		
		String query = "DELETE FROM RoomsBooked WHERE RoomBookingID = " + id + ";" ;
		System.out.println("Delete query : " + query);
		
		try { //creating a statement and deleting a selected RoomBooking from database;
			Statement stmt = con.createStatement();
			stmt.setQueryTimeout(5);
			rc = stmt.executeUpdate(query);
			stmt.close();	
		}
		catch (Exception e) { //error, exception call;
			System.out.println("RoomBooking deletion fails");
			e.getMessage();
		}
		return rc; //returns the row count to controller;
	}

	private RoomBooking singleWhere(String wClause, String wClause2, boolean retrieveAssociation) {
		ResultSet results; //the results retrieved from the database will be stored here;
		RoomBooking roomBooking = new RoomBooking(); //creates an empty RoomBooking instance, which will be populated with retrieved data;
                
	    String query = buildBookingQuery(wClause); //creates SELECT query for RoomBooking table
		System.out.println("Booking Query in singleWhere (DAORoomBooking): " + query);
	    try { //fetching the RoomBooking data from the database;
	 		Statement stmt = con.createStatement(); //Creates a statement, that will be executed;
	 		stmt.setQueryTimeout(5);
	 		results = stmt.executeQuery(query); //Storing the results from the statement execution;
	 		
	 		if(results.next()) { //checks if there are any RoomBooking in the database at all;
	 			roomBooking = buildRoomBooking(results);//builds roomBooking
	 			roomBooking.setRoomsBooked(getRoomsBooked(wClause2,false));//adds list of rooms booked to roomBooking object
                stmt.close();
                if(retrieveAssociation) {//retrieves the customer object
                	IFDAOCustomer dbCust = new DAOCustomer();
                	int custID = roomBooking.getCustomer().getPersonID();
                	Customer cust = dbCust.getCustomer(custID, false);
                	roomBooking.setCustomer(cust);
                }
			} else { //nothing was found
				roomBooking = null;
				System.out.println("We got to else again");
			}
		}//try ends;
		
	 	catch(Exception e) {
	 		System.out.println("Query exception: "+e);
	 	}
		System.out.println("roomBooking in singleWhere (DAORoomBooking): " + roomBooking);
		return roomBooking;
	}

	private ArrayList<RoomBooking> miscWhere(String wClause, boolean retrieveAssociation) {
		ResultSet results; //the results retrieved from the database will be stored here;
		ArrayList<RoomBooking> bookingList = new ArrayList<RoomBooking>(); //here RoomBooking instances will be stored;	
		RoomBooking roomBooking = new RoomBooking(); //creates an empty RoomBooking instance, which will be populated with retrieved data;	
		
		String query =  buildBookingQuery(wClause);
	  
	    try { // fetching the RoomBooking data from the database;
			Statement stmt = con.createStatement(); //Creates a statement, that will be executed;
		 	stmt.setQueryTimeout(5);
		 	results = stmt.executeQuery(query); //Storing the results from the statement execution;
		 	
			while(results.next()) { //there is another row;
				
				roomBooking = buildRoomBooking(results);	//calls a method which will populate RoomBooking the instance with data;
				int id = roomBooking.getId();
				String wClause2 = "roomBookingID = " + id; //where clause needed for retrieving room list for room booking
				roomBooking.setRoomsBooked(getRoomsBooked(wClause2,false));//adds list of rooms booked to roomBooking object
	            bookingList.add(roomBooking); //adding the built object to the list;
			}//while loop ends, because there are no more rows;
	        stmt.close();       
	        
	        if (retrieveAssociation) {//retrieves the customer objects
	        	IFDAOCustomer dbCust = new DAOCustomer();
				for (RoomBooking roomBooking1 : bookingList) {
					
					int custID = roomBooking1.getCustomer().getPersonID();
					Customer cust = dbCust.getCustomer(custID, false);
					roomBooking1.setCustomer(cust);
				}
	        }
	    }//try ends;
	    
	    catch(Exception e) { //e.g. no RoomBooking are found in the database, this will result in an empty OrderList;
		 		System.out.println("Query exception - select: "+e);
				e.printStackTrace();
		 	}
	    
	    
	    return bookingList; //returns the OrderList;	
	}
	
	private ArrayList<Room> getRoomsBooked(String wClause, boolean retrieveAssociation){
		ResultSet results; //the results retrieved from the database will be stored here;
		String query = buildBookedQuery(wClause); //builds SELECT query for RoomsBooked table
	    ArrayList<Room> roomsBooked = new ArrayList<Room>(); //creates empty list of rooms 
	    Room room = new Room(); // creates empty room object 
	    IFDAORoom dbRoom = new DAORoom(); //creates database object Room, needed for retrieving rooms
	    
	    try { // fetching the RoomBooking data from the database;
			Statement stmt = con.createStatement(); //Creates a statement, that will be executed;
		 	stmt.setQueryTimeout(5);
		 	results = stmt.executeQuery(query); //Storing the results from the statement execution; In this case room numbers
		 	
			while(results.next()) { //there is another row;
				room = dbRoom.getRoom(results.getInt("roomNumber"), false);	//finds room object by id, result of executing the query
				roomsBooked.add(room); //adds to the list
			}//while loop ends, because there are no more rows;
	        stmt.close();
	        
	    }
	    catch(Exception e){
	    	System.out.println("Query exception = select: "+e);
	    	e.printStackTrace();
	    }
	    return roomsBooked;
	}

	private RoomBooking buildRoomBooking(ResultSet results) {
		RoomBooking roomBooking = new RoomBooking(); //storage;
		Customer cust = new Customer();
		roomBooking.setCustomer(cust);//empty customer project, will be filled later
		ArrayList<Room> roomsBooked = new ArrayList<Room>();
		roomBooking.setRoomsBooked(roomsBooked); // empty list of rooms, will also be filled later
		
		try { //retrieving data from the RoomBooking table, by using the columns;
			roomBooking.setId(results.getInt("bookingID"));
			roomBooking.setDateStart(results.getString("dateStart"));
			roomBooking.setDateEnd(results.getString("dateEnd"));
			roomBooking.setDateBooked(results.getString("dateBooked"));
			cust.setPersonID(results.getInt("customerID"));
						
		}
		catch(Exception e) {
			System.out.println("error in building the RoomBooking object"); 
		}
         return roomBooking;
	}
	

	private String buildBookingQuery(String wClause) {
			String query = "SET DATEFORMAT dmy;" + " SELECT bookingID, customerID, dateStart, dateEnd, dateBooked FROM RoomBooking"; //query statement that will be executed;
		    if (wClause.length()>0) {
				query = query+" WHERE "+ wClause; //this query will retrieve that RoomBooking's instance data, which fulfils a specified condition;
			}
			
			return query; //returns the built query;
	}
	
	private String buildBookedQuery(String wClause){
		String query = "SET DATEFORMAT dmy;" + " SELECT roomNumber FROM RoomsBooked";
		 if (wClause.length()>0) {
				query = query+" WHERE "+ wClause; //this query will retrieve that RoomsBooked instance data, which fulfils a specified condition;
			}
		 
		 return query;
	}
	
	public int getLastInsertedID(){
		ResultSet results; //the results retrieved from the database will be stored here;
		String query = "SELECT IDENT_CURRENT('RoomBooking') AS BookingID;"; //selects the latest inserted ID as "BookingID" column
		int id = 0;
		try { //creating a statement and deleting a selected RoomBooking from database;
			Statement stmt = con.createStatement();
			stmt.setQueryTimeout(5);
			results = stmt.executeQuery(query);
			if(results.next()) { //retrieves the last ID
	 			id = results.getInt("BookingID");
			}
			stmt.close();	
		}
		catch (Exception e) { //error, exception call;
			System.out.println("Getting of ID failed");
			e.getMessage();
		}
		System.out.println("Last inserted ID in DAORoomBooking: " + id);
		return id;
	}

}

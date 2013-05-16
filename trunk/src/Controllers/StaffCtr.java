package Controllers;

import java.util.ArrayList;

import DAO.DAOStaff;
import DAO.IFDAOStaff;
import Models.Instructor;
import Models.Manager;
import Models.Receptionist;
import Models.Secretary;
import Models.Staff;

public class StaffCtr {

	public StaffCtr() {
		
	}
		//METHODS FOR Staff
		// creates a new employee
		public void newEmployee(String CPR, String fname, String lname,
				String country, String ZIP, String address, String email,
				String password, double salary, String position) {
			Staff employee = new Staff();
			if(position.equals("Instructor")){
				Instructor instructor = new Instructor();
				employee = instructor;
			}
			else if(position.equals("Manager")){
				Manager manager = new Manager();
				employee = manager;
			}
			else if(position.equals("Receptionist")){
				Receptionist receptionist = new Receptionist();
				employee = receptionist; 
			}
			else if(position.equals("Secretary")){
				Secretary secretary = new Secretary();
				employee = secretary;
			}
			employee.getPersonID();
			employee.setCPR(CPR);
			employee.setFname(fname);
			employee.setLname(lname);
			employee.setCountry(country);
			employee.setZIP(ZIP);
			employee.setAddress(address);
			employee.setEmail(email);
			employee.setPassword(password);
			employee.setSalary(salary);

			try {
				// TODO DBConnection.startTransaction();
				IFDAOStaff daoStaff = new DAOStaff();
				daoStaff.insert(employee);
				// TODO DBConnection.commitTransaction();
			} catch (Exception e) {
				System.out
						.println("Error while creating booking in RoomBookingCtr");
				// TODO DBConnection.rollbackTransaction();
				e.getMessage();
				e.printStackTrace();
			}
		}

		// finds a booking by id
		public Staff findEmployee(int id) {
			IFDAOStaff daoStaff = new DAOStaff();
			return daoStaff.getStaff(id, false);
		}

		// retrieves all bookings
		public ArrayList<Staff> getAllEmployees() {
			IFDAOStaff daoStaff = new DAOStaff();
			ArrayList<Staff> allStaff = new ArrayList<Staff>();
			allStaff = daoStaff.getAllStaff(false);
			return allStaff;
		}

		// updates a booking
		public int updateStaff(int newId, String CPR, String fname, String lname,
				String country, String ZIP, String address, String email,
				String password, double salary) {
			IFDAOStaff daoStaff = new DAOStaff();
			Staff employee = new Staff();
			employee.setPersonID(newId);
			employee.setCPR(CPR);
			employee.setFname(fname);
			employee.setLname(lname);
			employee.setCountry(country);
			employee.setZIP(ZIP);
			employee.setAddress(address);
			employee.setEmail(email);
			employee.setPassword(password);
			employee.setSalary(salary);
			return daoStaff.update(employee);
		}

		// deletes a booking
		public int deleteStaff(int id) {
			IFDAOStaff daoStaff = new DAOStaff();
			return daoStaff.delete(id);
		}
		//METHODS FOR STAFF END
	}



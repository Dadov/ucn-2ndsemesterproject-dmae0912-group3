package Controllers;

import java.util.ArrayList;

import DAO.DAOActivity;
import DAO.DAOInstructorHire;
import DAO.DAOActivityBooking;
import DAO.IFDAOActivity;
import DAO.IFDAOInstructorHire;
import DAO.IFDAOActivityBooking;
import Models.Activity;
import Models.ActivityBooking;
import Models.ActivityTime;
import Models.ActivityType;
import Models.Customer;
import Models.Instructor;
import Models.InstructorHire;


public class ActivitiesCtr {

	public ActivitiesCtr() {
	}

	// METHODS FOR INSTRUCTORHIRE 
		// starts a new instructorHire
		public void newInstructorHire(Customer customer, Instructor instructor,	ActivityBooking activBook, ActivityTime actTime) {
			InstructorHire instructorHire = new InstructorHire();
			instructorHire.getId();
			instructorHire.setCustomer(customer);
			instructorHire.setInstructor(instructor);
			instructorHire.setActivityBooking(activBook);
			instructorHire.setActivityTime(actTime);

			try {
				// TODO DBConnection.startTransaction();
				IFDAOInstructorHire daoInstructorHire = new DAOInstructorHire();
				daoInstructorHire.insert(instructorHire);
				// TODO DBConnection.commitTransaction();
			} catch (Exception e) {
				System.out.println("Error while creating instructorHire in InstructorHireCtr");
				// TODO DBConnection.rollbackTransaction();
				e.getMessage();
				e.printStackTrace();
			}
		}

		// finds a instructorHire by id
		public InstructorHire findInstructorHire(int id) {
			IFDAOInstructorHire daoinstructorHire = new DAOInstructorHire();
			return daoinstructorHire.getInstructorHire(id, false);
		}

		// retrieves all instructorHires
		public ArrayList<InstructorHire> getInstructorHires() {
			IFDAOInstructorHire daoinstructorHire = new DAOInstructorHire();
			ArrayList<InstructorHire> allinstructorHires = new ArrayList<InstructorHire>();
			allinstructorHires = daoinstructorHire.getInstructorHires(false);
			return allinstructorHires;
		}

		// updates a instructorHire
		public int updateInstructorHire(int newId, Customer newCustomer,Instructor newInstructor, ActivityBooking activityBooking, ActivityTime activityTime) {
			IFDAOInstructorHire daoInstructorHire = new DAOInstructorHire();
			InstructorHire instructorHire = new InstructorHire();
			instructorHire.setId(newId);
			instructorHire.setCustomer(newCustomer);
			instructorHire.setInstructor(newInstructor);
			instructorHire.setActivityBooking(activityBooking);
			instructorHire.setActivityTime(activityTime);
			return daoInstructorHire.update(instructorHire);
		}

		// deletes a instructorHire
		public int deleteInstructorHire(int id) {
			IFDAOInstructorHire daoInstructorHire = new DAOInstructorHire();
			return daoInstructorHire.delete(id);
		}
		//METOHDS FOR INSTRUCTORHIRE END
		
		
		//METHODS FOR ACTIVITYBOOKING
		// starts a new booking
		public void newActivityBooking(ArrayList<Customer> customers, Activity activity, ActivityTime activityTime, boolean openActivity,
				boolean instructorHired) {
			ActivityBooking booking = new ActivityBooking();
			booking.setCustomers(customers);
			booking.setActivity(activity);
			booking.setActivityTime(activityTime);
			booking.setOpenActivity(openActivity);
			booking.setInstructorHired(instructorHired);

			try {
				// TODO DBConnection.startTransaction();
				IFDAOActivityBooking daoBooking = new DAOActivityBooking();
				daoBooking.insert(booking);
				// TODO DBConnection.commitTransaction();
			} catch (Exception e) {
				System.out
						.println("Error while creating booking in ActivityBookingCtr");
				// TODO DBConnection.rollbackTransaction();
				e.getMessage();
				e.printStackTrace();
			}
		}

		// finds a booking by id
		public ActivityBooking findBooking(int id) {
			IFDAOActivityBooking daoBooking = new DAOActivityBooking();
			return daoBooking.getActivityBooking(id, false);
		}

		// retrieves all bookings
		public ArrayList<ActivityBooking> getAllBookings() {
			IFDAOActivityBooking daoBooking = new DAOActivityBooking();
			ArrayList<ActivityBooking> allBookings = new ArrayList<ActivityBooking>();
			allBookings = daoBooking.getAllActivityBookings(false);
			return allBookings;
		}

		// updates a booking
		public int updateBooking(int newId, ArrayList<Customer> customers, Activity activity, ActivityTime activityTime, boolean openActivity,
				boolean instructorHired) {
			IFDAOActivityBooking daoBooking = new DAOActivityBooking();
			ActivityBooking booking = new ActivityBooking();
			booking.setID(newId);
			booking.setCustomers(customers);
			booking.setActivity(activity);
			booking.setActivityTime(activityTime);
			booking.setOpenActivity(openActivity);
			return daoBooking.update(booking);
		}

		// deletes a booking
		public int deleteBooking(int id) {
			IFDAOActivityBooking daoBooking = new DAOActivityBooking();
			return daoBooking.delete(id);
		}
		//METHODS FOR ACTIVITYBOOKING END
		
		//METHODS FOR ACTIVITY
		// starts a new booking
				public void newActivity(ArrayList<Instructor> instructors, ActivityType activityType, int capacity, int instructorPrice) {
					Activity activity = new Activity();
					activity.getID();
					activity.setActivityInstructors(instructors);
					activity.setActivityType(activityType);
					activity.setCapacity(capacity);
					activity.setInstructorPrice(instructorPrice);

					try {
						// TODO DBConnection.startTransaction();
						IFDAOActivity daoactivity = new DAOActivity();
						daoactivity.insert(activity);
						// TODO DBConnection.commitTransaction();
					} catch (Exception e) {
						System.out
								.println("Error while creating activity in ActivityCtr");
						// TODO DBConnection.rollbackTransaction();
						e.getMessage();
						e.printStackTrace();
					}
				}

				// finds a activity by id
				public Activity findActivity(int id) {
					IFDAOActivity daoActivity = new DAOActivity();
					return daoActivity.getActivity(id, false);
				}

				// retrieves all activities
				public ArrayList<Activity> getAllactivitys() {
					IFDAOActivity daoActivity = new DAOActivity();
					ArrayList<Activity> allActivities = new ArrayList<Activity>();
					allActivities = daoActivity.getAllActivities(false);
					return allActivities;
				}

				// updates a activity
				public int updateactivity(int newId, ArrayList<Instructor> instructors, ActivityType activityType, int capacity, int instructorPrice) {
					IFDAOActivity daoActivity = new DAOActivity();
					Activity activity = new Activity();
					activity.setID(newId);
					activity.setActivityInstructors(instructors);
					activity.setActivityType(activityType);
					activity.setCapacity(capacity);
					activity.setInstructorPrice(instructorPrice);
					return daoActivity.update(activity);
				}

				// deletes a activity
				public int deleteActivity(int id) {
					IFDAOActivity daoActivity = new DAOActivity();
					return daoActivity.delete(id);
				}
				//METHODS FOR ACTIVITY END
		
	}


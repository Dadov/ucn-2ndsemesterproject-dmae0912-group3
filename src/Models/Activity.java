package Models;

import java.util.ArrayList;

public class Activity {

	private int id;
	private Enum<ActivityType> activityType;
	private int capacity;
	private ArrayList<Instructor> activityInstructors;
	private int instructorPrice;

	public Activity() {

	}

	public Activity(int id, Enum<ActivityType> activityType, int capacity,
			ArrayList<Instructor> activityInstructors, int instructorPrice) {
		this.id = id;
		this.activityType = activityType;
		this.capacity = capacity;
		this.activityInstructors = activityInstructors;
		this.instructorPrice = instructorPrice;
	}

	public Activity(Enum<ActivityType> activityType, int capacity, ArrayList<Instructor> activityInstructors, int instructorPrice) {
		this.activityType = activityType;
		this.capacity = capacity;
		this.activityInstructors = activityInstructors;
		this.instructorPrice = instructorPrice;
	}

	public int getID() {
		return id;
	}

	public void setID(int id) {
		this.id = id;
	}

	public Enum<ActivityType> getActivityType() {
		return activityType;
	}

	public void setActivityType(Enum<ActivityType> activityType) {
		this.activityType = activityType;
	}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	public ArrayList<Instructor> getActivityInstructors() {
		return activityInstructors;
	}

	public void setActivityInstructors(ArrayList<Instructor> activityInstructors) {
		this.activityInstructors = activityInstructors;
	}

	@Override
	public String toString() {
		return "Activity [id=" + id + ", activityType=" + activityType
				+ ", capacity=" + capacity + ", activityInstructors="
				+ activityInstructors + ", instructorPrice=" + instructorPrice
				+ "]";
	}

	public int getInstructorPrice() {
		return instructorPrice;
	}

	public void setInstructorPrice(int instructorPrice) {
		this.instructorPrice = instructorPrice;
	}

}

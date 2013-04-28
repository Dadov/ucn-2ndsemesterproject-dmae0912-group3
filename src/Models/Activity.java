package Models;

public class Activity {
	
	private int id;
	private Enum<ActivityType> activityType;
	private int capacity;
	private boolean instructorAvailability;
	
	Activity() {
		
	}

	public Activity(int id, Enum<ActivityType> activityType, int capacity,
			boolean instructorAvailability) {
		this.id = id;
		this.activityType = activityType;
		this.capacity = capacity;
		this.instructorAvailability = instructorAvailability;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
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

	public boolean isInstructorAvailability() {
		return instructorAvailability;
	}

	public void setInstructorAvailability(boolean instructorAvailability) {
		this.instructorAvailability = instructorAvailability;
	}

	@Override
	public String toString() {
		return "Activity [id=" + id + ", activityType=" + activityType
				+ ", capacity=" + capacity + ", instructorAvailability="
				+ instructorAvailability + "]";
	}

}

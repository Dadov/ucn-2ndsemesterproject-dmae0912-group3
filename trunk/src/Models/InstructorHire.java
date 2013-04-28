package Models;

public class InstructorHire {

	private int id;
	private Customer customer;
	private Instructor instructor;
	private ActivityTime activityTime;
	
	public InstructorHire() {
		
	}

	public InstructorHire(int id, Customer customer, Instructor instructor,
			ActivityTime activityTime) {
		this.id = id;
		this.customer = customer;
		this.instructor = instructor;
		this.activityTime = activityTime;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Instructor getInstructor() {
		return instructor;
	}

	public void setInstructor(Instructor instructor) {
		this.instructor = instructor;
	}

	public ActivityTime getActivityTime() {
		return activityTime;
	}

	public void setActivityTime(ActivityTime activityTime) {
		this.activityTime = activityTime;
	}

	@Override
	public String toString() {
		return "InstructorHire [id=" + id + ", customer=" + customer
				+ ", instructor=" + instructor + ", activityTime="
				+ activityTime + "]";
	}
	
}

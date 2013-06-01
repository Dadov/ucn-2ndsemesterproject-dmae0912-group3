package Models;

public class InstructorHire {

	private int id;
	private Customer customer;
	private Instructor instructor;
	private ActivityBooking activityBooking;
	private ActivityTime activityTime;
	private String status;

	public InstructorHire() {
		setStatus("unpaid");
	}

	public InstructorHire(int id, Customer customer, Instructor instructor,
			ActivityBooking activityBooking, ActivityTime activityTime) {
		this.id = id;
		this.customer = customer;
		this.instructor = instructor;
		this.activityBooking = activityBooking;
		this.activityTime = activityTime;
		setStatus("unpaid");
	}

	public InstructorHire(Customer customer, Instructor instructor,
			ActivityBooking activityBooking, ActivityTime activityTime) {
		this.customer = customer;
		this.instructor = instructor;
		this.activityBooking = activityBooking;
		this.activityTime = activityTime;
		setStatus("unpaid");
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

	public ActivityBooking getActivityBooking() {
		return activityBooking;
	}

	public void setActivityBooking(ActivityBooking activityBooking) {
		this.activityBooking = activityBooking;
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


	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "InstructorHire [id=" + id + ", customer=" + customer
				+ ", instructor=" + instructor + ", activityBooking="
				+ activityBooking + ", activityTime=" + activityTime
				+ ", status=" + status + "]";
	}

}

package Models;

import java.util.ArrayList;

public class ActivityBooking {

	private int id;
	private ArrayList<Customer> customers;
	private Activity activity;
	private ActivityTime activityTime;
	private boolean openActivity;
	private boolean instructorHired;

	public ActivityBooking() {

	}

	public ActivityBooking(int id, ArrayList<Customer> customers,
			Activity activity, ActivityTime activityTime, boolean openActivity,
			boolean instructorHired) {
		this.id = id;
		this.customers = customers;
		this.activity = activity;
		this.activityTime = activityTime;
		this.openActivity = openActivity;
		this.instructorHired = instructorHired;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public ArrayList<Customer> getCustomers() {
		return customers;
	}

	public void setCustomers(ArrayList<Customer> customers) {
		this.customers = customers;
	}

	public Activity getActivity() {
		return activity;
	}

	public void setActivity(Activity activity) {
		this.activity = activity;
	}

	public ActivityTime getActivityTime() {
		return activityTime;
	}

	public void setActivityTime(ActivityTime activityTime) {
		this.activityTime = activityTime;
	}

	public boolean isOpenActivity() {
		return openActivity;
	}

	public void setOpenActivity(boolean openActivity) {
		this.openActivity = openActivity;
	}

	public boolean isInstructorHired() {
		return instructorHired;
	}

	public void setInstructorHired(boolean instructorHired) {
		this.instructorHired = instructorHired;
	}

	@Override
	public String toString() {
		return "ActivityBooking [id=" + id + ", customers=" + customers
				+ ", activity=" + activity.toString() + ", activityTime="
				+ activityTime.toString() + ", openActivity=" + openActivity
				+ ", instructorHired=" + instructorHired + "]";
	}

}

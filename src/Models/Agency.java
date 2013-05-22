package Models;

import java.util.ArrayList;

public class Agency {

	private int id;
	private String name;
	private int agencyDiscountLevel;
	private ArrayList<Customer> providedCustomers;

	public Agency() {

	}

	public Agency(int id, String name, int agencyDiscountLevel,
			ArrayList<Customer> providedCustomers) {
		this.id = id;
		this.name = name;
		this.agencyDiscountLevel = agencyDiscountLevel;
		this.providedCustomers = providedCustomers;
	}

	public Agency(String name, int agencyDiscountLevel,
			ArrayList<Customer> providedCustomers) {
		this.name = name;
		this.agencyDiscountLevel = agencyDiscountLevel;
		this.providedCustomers = providedCustomers;
	}

	public int getID() {
		return id;
	}

	public void setID(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAgencyDiscountLevel() {
		return agencyDiscountLevel;
	}

	public void setAgencyDiscountLevel(int agencyDiscountLevel) {
		this.agencyDiscountLevel = agencyDiscountLevel;
	}

	public ArrayList<Customer> getProvidedCustomers() {
		return providedCustomers;
	}

	public void setProvidedCustomers(ArrayList<Customer> providedCustomers) {
		this.providedCustomers = providedCustomers;
	}

	@Override
	public String toString() {
		return "Agency [id=" + id + ", name=" + name + ", agencyDiscountLevel="
				+ agencyDiscountLevel + ", providedCustomers="
				+ providedCustomers + "]";
	}

}

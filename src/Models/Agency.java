package Models;

import java.util.LinkedList;

public class Agency {

	private int id;
	private String name;
	private int agencyDiscountLevel;
	private LinkedList<Customer> providedCustomers;

	public Agency() {

	}

	public Agency(int id, String name, int agencyDiscountLevel,
			LinkedList<Customer> providedCustomers) {
		this.id = id;
		this.name = name;
		this.agencyDiscountLevel = agencyDiscountLevel;
		this.providedCustomers = providedCustomers;
	}

	public Agency(String name, int agencyDiscountLevel,
			LinkedList<Customer> providedCustomers) {
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

	public LinkedList<Customer> getProvidedCustomers() {
		return providedCustomers;
	}

	public void setProvidedCustomers(LinkedList<Customer> providedCustomers) {
		this.providedCustomers = providedCustomers;
	}

	@Override
	public String toString() {
		return "Agency [id=" + id + ", name=" + name + ", agencyDiscountLevel="
				+ agencyDiscountLevel + ", providedCustomers="
				+ providedCustomers + "]";
	}

}

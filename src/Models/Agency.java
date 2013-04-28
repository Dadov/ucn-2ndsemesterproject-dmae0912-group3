package Models;

import java.util.ArrayList;

public class Agency {
	
	private String name;
	private Enum<AgencyDiscountLevel> agencyDiscountLevel;
	private ArrayList<Customer> providedCustomers;
	
	public Agency() {
		
	}

	public Agency(String name, Enum<AgencyDiscountLevel> agencyDiscountLevel,
			ArrayList<Customer> providedCustomers) {
		super();
		this.name = name;
		this.agencyDiscountLevel = agencyDiscountLevel;
		this.providedCustomers = providedCustomers;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Enum<AgencyDiscountLevel> getAgencyDiscountLevel() {
		return agencyDiscountLevel;
	}

	public void setAgencyDiscountLevel(Enum<AgencyDiscountLevel> agencyDiscountLevel) {
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
		return "Agency [name=" + name + ", agencyDiscountLevel="
				+ agencyDiscountLevel + ", providedCustomers="
				+ providedCustomers + "]";
	}

}

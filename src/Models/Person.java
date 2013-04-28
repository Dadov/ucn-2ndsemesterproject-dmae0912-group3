package Models;

public class Person {
	
	private int personID;
	private String CPR;
	private String fname;
	private String lname;
	private String country;
	private String ZIP;
	private String address;
	private String email;
	
	public Person() {
		
	}
	
	public Person(int personID, String CPR, String fname, String lname,
			String country, String ZIP, String address, String email) {
		super();
		this.personID = personID;
		this.CPR = CPR;
		this.fname = fname;
		this.lname = lname;
		this.country = country;
		this.ZIP = ZIP;
		this.address = address;
		this.email = email;
	}
	
	public int getPersonID() {
		return personID;
	}
	
	public void setPersonID(int personID) {
		this.personID = personID;
	}
	
	public String getCPR() {
		return CPR;
	}
	
	public void setCPR(String CPR) {
		this.CPR = CPR;
	}
	
	public String getFname() {
		return fname;
	}
	
	public void setFname(String fname) {
		this.fname = fname;
	}
	
	public String getLname() {
		return lname;
	}
	
	public void setLname(String lname) {
		this.lname = lname;
	}
	
	public String getCountry() {
		return country;
	}
	
	public void setCountry(String country) {
		this.country = country;
	}
	
	public String getZIP() {
		return ZIP;
	}
	
	public void setZIP(String ZIP) {
		this.ZIP = ZIP;
	}
	
	public String getAddress() {
		return address;
	}
	
	public void setAddress(String address) {
		this.address = address;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	@Override
	public String toString() {
		return "Person [personID=" + personID + ", CPR=" + CPR + ", fname="
				+ fname + ", lname=" + lname + ", country=" + country
				+ ", ZIP=" + ZIP + ", address=" + address + ", email=" + email
				+ "]";
	}
	
}

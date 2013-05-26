package Models;

public class Person {

	private int personID;
	private String CPR;
	private String fname;
	private String lname;
	private String country;
	private String ZIP;
	private String city;
	private String address;
	private String email;
	private String password;

	public Person() {

	}

	public Person(int personID, String CPR, String fname, String lname,
			String country, String ZIP, String city, String address,
			String email, String password) {
		this.personID = personID;
		this.CPR = CPR;
		this.fname = fname;
		this.lname = lname;
		this.country = country;
		this.ZIP = ZIP;
		this.city = city;
		this.address = address;
		this.email = email;
		this.password = password;
	}
	
	public Person(String CPR, String fname, String lname,
			String country, String ZIP, String city, String address,
			String email, String password) {
		this.CPR = CPR;
		this.fname = fname;
		this.lname = lname;
		this.country = country;
		this.ZIP = ZIP;
		this.city = city;
		this.address = address;
		this.email = email;
		this.password = password;
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

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "Person [personID=" + personID + ", CPR=" + CPR + ", fname="
				+ fname + ", lname=" + lname + ", country=" + country
				+ ", ZIP=" + ZIP + ", city=" + city + ", address=" + address
				+ ", email=" + email + ", password=" + password + "]";
	}

}

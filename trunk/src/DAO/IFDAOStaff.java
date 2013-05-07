package DAO;

import java.util.ArrayList;

import Models.Staff;

public interface IFDAOStaff {

	public Staff getStaff(int ID, boolean retrieveAssociation);

	public ArrayList<Staff> getAllStaff(boolean retrieveAssociation);

	public int insert(Staff Staff);

	public int update(Staff Staff);

	public int delete(int ID);
}

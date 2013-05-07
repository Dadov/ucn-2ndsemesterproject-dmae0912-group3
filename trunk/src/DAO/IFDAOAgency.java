package DAO;

import java.util.ArrayList;

import Models.Agency;

public interface IFDAOAgency {

	public Agency getAgency(int ID, boolean retrieveAssociation);

	public ArrayList<Agency> getAllAgencies(boolean retrieveAssociation);

	public int insert(Agency agency);

	public int update(Agency agency);

	public int delete(int ID);

}

package DAO;

import java.util.LinkedList;

import Models.Agency;

public interface IFDAOAgency {

	public Agency getAgency(int ID, boolean retrieveAssociation);

	public LinkedList<Agency> getAllAgencies(boolean retrieveAssociation);

	public int insert(Agency agency);

	public int update(Agency agency);

	public int delete(int ID);

}

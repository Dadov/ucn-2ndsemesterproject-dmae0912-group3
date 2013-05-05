package DAO;

import java.util.ArrayList;

import Models.Agency;

public interface IFDAOAgency {

	public Agency getAgency(int ID);

	public ArrayList<Agency> getAllAgencies();

	public int insert(Agency agency);

	public int update(Agency agency);

	public int delete(int ID);

}

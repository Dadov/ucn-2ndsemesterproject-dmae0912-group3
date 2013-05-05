package DAO;

import java.util.ArrayList;

import Models.InstructorHire;

public interface IFDAOInstructorHire {

	public InstructorHire getInstructorHire(int ID);

	public ArrayList<InstructorHire> getInstructorHires();

	public int insert(InstructorHire InstructorHire);

	public int update(InstructorHire InstructorHire);

	public int delete(int ID);
}

package DAO;

import java.util.ArrayList;

import Models.Instructor;
import Models.InstructorHire;

public interface IFDAOInstructorHire {

	public InstructorHire getInstructorHire(int ID, boolean retrieveAssociation);

	public ArrayList<InstructorHire> getInstructorHires(
			boolean retrieveAssociation);

	public int insert(InstructorHire InstructorHire);

	public int update(InstructorHire InstructorHire);

	public int delete(int ID);

	public boolean checkInstructorAvailability(Instructor instructor,
			String date, String time);
}

package DAO;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import Models.Staff;

public class DAOStaffTest {
	
	private Connection con;
	private Staff staff;
	private IFDAOStaff daoStaff;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		staff = new Staff(12, "120945-3401", "Granny", "Smith", 
				"Japan", "3400", "Ching Chong", "Chookok Street 19",
				"granny_smith@chingchong.com", "grsmchicho", 20.50);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testDAOStaff() {
		DBConnection dbCon = mock(DBConnection.class);
		when(dbCon.getDBCon()).thenReturn(con);
	}

	@Test
	public void testCRUD() throws SQLException {
		con = DBConnection.getInstance().getDBCon();
		con.setAutoCommit(false);
		daoStaff = new DAOStaff();
		try {
			daoStaff.insert(staff,"Receptionist");
			ArrayList<Staff> staffMembers = daoStaff.getAllStaff(false);
			Staff lastStaff = staffMembers.get(staffMembers.size()-1);
			
			assertEquals(staff.getAddress(),lastStaff.getAddress());
			assertEquals(staff.getCity(), lastStaff.getCity());
			assertEquals(staff.getCountry(), lastStaff.getCountry());
			assertEquals(staff.getCPR(), lastStaff.getCPR());
			assertEquals(staff.getEmail(), lastStaff.getEmail());
			assertEquals(staff.getFname(), lastStaff.getFname());
			assertEquals(staff.getLname(), lastStaff.getLname());
			assertEquals(staff.getPassword(), lastStaff.getPassword());
			assertEquals(staff.getPersonID(), lastStaff.getPersonID());
			assertEquals(staff.getSalary(), lastStaff.getSalary(), 0.001);
			assertEquals(staff.getZIP(), lastStaff.getZIP());
			
			lastStaff.setAddress("test password");
			System.out.println("Update test: " + lastStaff.getPersonID());
			daoStaff.update(lastStaff, "Receptionist");
			assertEquals(lastStaff.toString(), daoStaff.getStaff(lastStaff.getPersonID(), false).toString());
			
			daoStaff.delete(lastStaff.getPersonID());
			assertNull(daoStaff.getStaff(lastStaff.getPersonID(), false));
		}
		finally {
			con.rollback();
			con.close();
		}
	}

}

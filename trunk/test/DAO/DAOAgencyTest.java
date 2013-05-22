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

import Models.Agency;
import Models.Customer;

public class DAOAgencyTest {
	
	private Connection con;
	private Agency agency;
	private IFDAOAgency daoAgency;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() {
		Customer customer1 = new Customer();
		Customer customer2 = new Customer();
		customer1.setPersonID(12);
		customer2.setPersonID(13);
		ArrayList<Customer> customers = new ArrayList<Customer>();
		customers.add(customer1);
		customers.add(customer2);
		agency = new Agency("Swaggins Travel", 40, customers);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testDAOAgency() {
		DBConnection dbCon = mock(DBConnection.class);
		when(dbCon.getDBCon()).thenReturn(con);
	}
	
	@Test
	public void testCRUD() throws SQLException {
		con = DBConnection.getInstance().getDBCon();
		con.setAutoCommit(false); 
		daoAgency = new DAOAgency();
		try {
			//insert
			daoAgency.insert(agency);
			ArrayList<Agency> agencies = daoAgency.getAllAgencies(false);
			Agency lastAgency = agencies.get(agencies.size()-1);
			
			//get
			assertEquals(agency.getAgencyDiscountLevel(), lastAgency.getAgencyDiscountLevel());
			assertEquals(agency.getName(), lastAgency.getName());
			assertEquals(agency.getProvidedCustomers(), lastAgency.getProvidedCustomers());
			
			//update
			lastAgency.setName("Test Name");
			System.out.println("Update test: " + lastAgency.getName());
			daoAgency.update(lastAgency);
			assertEquals(lastAgency.toString(), daoAgency.getAgency(lastAgency.getID(), false).toString());
			
			//delete
			daoAgency.delete(lastAgency.getID());
			assertNull(daoAgency.getAgency(lastAgency.getID(), false));
		}
		finally {
			con.rollback();
			con.close();
		}
	}

}

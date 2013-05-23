package DAO;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({DAOInstructorHireTest.class,DAORoomTest.class,DAORoomBookingTest.class,DAOStaffTest.class,DAOAgencyTest.class,DAOPersonTest.class, 
	DAOActivityBookingTest.class, DAOActivityTest.class, DAOCustomerTest.class})
public class DAOTestSuite {

}

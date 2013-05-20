package Models;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ PersonTest.class, CustomerTest.class, StaffTest.class,
		InstructorTest.class, ManagerTest.class, SecretaryTest.class,
		ReceptionistTest.class, RoomTest.class, RoomBookingTest.class,
		AgencyTest.class, ActivityTimeTest.class, ActivityBookingTest.class,
		InstructorHireTest.class })
public class ModelsTestSuite {

}

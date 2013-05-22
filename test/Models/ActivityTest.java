package Models; //indicates class location;

import static org.junit.Assert.assertEquals; //needed so comparisons can be made;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before; //needed to prepare everything before testing;
import org.junit.BeforeClass; 
import org.junit.Test; //needed to indicate testing;

import Models.Activity; //the subject for testing;
import java.util.ArrayList; //needed to store multiple Instructor instances;

/**
 * unit test for Activity class;
 * first creates Activity instance, three instructors and then
 * initializes the ArrayList and adds the instructors in it;
 * after the setUp, it carries out a test on all methods in Activity;
 */
public class ActivityTest {
   
    private Activity activity;
    private Instructor instr1, instr2, instr3;
    private ArrayList<Instructor> activityInstructors;
    
       
    @BeforeClass
    /*
     * empty, because there is nothing to do before any other method
     * from the class to be executed; 
     */
    public static void setUpBeforeClass() throws Exception {
    }
    
    @AfterClass
    /*
     * empty, because there is nothing to do after all methods from
     * the class are executed; 
     */
    public static void tearDownAfterClass() throws Exception {
    }
    
        
    @Before
    /*
     * prepares Activity instance for testing;
     * creates it by using the constructor, that sets all fields;
     * creates an ArrayList of Instructor;
     */
    public void setUp() throws Exception {
	activity = new Activity();
	instr1 = instr2 = instr3 = new Instructor();
	activityInstructors = new ArrayList<Instructor>();
	activityInstructors.add(instr1);
	activityInstructors.add(instr2);
	activityInstructors.add(instr3);
	
	activity.setActivityInstructors(activityInstructors);
    }
    
    @After
    /*
     * empty, because there is nothing to do after the method is executed;
     */
    public void tearDown() throws Exception {
    }
    
        
    @Test
    /*
     * tests setID method, which prompts for integer and compares;
     */
    public void testSetID() {
	activity.setID(134);
	assertEquals(134, activity.getID());
    }
   
    @Test
    /*
     * tests setActivityType, which prompts for enum and compares;
     */
    public void testSetActivityType() {
	activity.setActivityType(ActivityType.TennisCourt);
	assertEquals(ActivityType.TennisCourt, activity.getActivityType());
    }
    
    @Test
    /*
     * tests setCapacity, which prompts for integer and compares;
     */
    public void testSetCapacity() {
	activity.setCapacity(100);
	assertEquals(100, activity.getCapacity());
    }
   
    @Test
    /*
     * tests setActivityInstructors, which prompts for an ArrayList of type Instructors and compares;
     */
    public void testSetActivityInstructors() {
	activity.setActivityInstructors(activityInstructors);
	assertEquals(activityInstructors, activity.getActivityInstructors());
    }
    
    @Test
    /*
     * tests toSring method and compares;
     */
    public void testToString () {
	String testValue = "Activity [id=0, activityType=null, capacity=0, activityInstructors=" +
			"[Person [personID=0, CPR=null, fname=null, lname=null, country=null, " +
			"ZIP=null, city=null, address=null, email=null, password=null]Staff " +
			"[salary=0.0]Instructor [], Person [personID=0, CPR=null, fname=null, " +
			"lname=null, country=null, ZIP=null, city=null, address=null, email=null, " +
			"password=null]Staff [salary=0.0]Instructor [], Person [personID=0, " +
			"CPR=null, fname=null, lname=null, country=null, ZIP=null, city=null, " +
			"address=null, email=null, password=null]Staff [salary=0.0]Instructor []]]";	
	assertEquals(testValue, activity.toString());
    }
}
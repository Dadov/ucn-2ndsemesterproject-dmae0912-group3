package GUI;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;

import Controllers.ActivitiesCtr;
import Controllers.CustomersCtr;
import Models.Activity;
import Models.ActivityBooking;
import Models.ActivityTime;
import Models.Customer;
import Models.Instructor;
import Models.InstructorHire;
import javax.swing.JTextField;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.RowSpec;
import javax.swing.JCheckBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.BoxLayout;
import java.awt.GridLayout;


public class ActivitiesGUI extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel activitiesWrapper;
	private JTable table;
	private ActivitiesCtr actCtr;
	private CustomersCtr custCtr;
	private ArrayList<ActivityBooking> bookings;
	private ArrayList<InstructorHire> hires;
	private DefaultTableModel dtmActivityBooking;
	private DefaultTableModel dtmInstructorHire;
	private DefaultTableModel dtmActivity;
	private JTable table_1;
	private JTextField textFieldBooking;
	private JTextField textFieldInstructor;
	private JTable activityTable;
	private JTextField showActivity;
	private ArrayList<Activity> activities;
	private JTextField showIDField;
	private JTextField showActivityField;
	private JTextField showDateField;
	private JTextField showTimeField;
	private JCheckBox showOpenActivity;
	private JCheckBox showInstructorHired;
	private JPanel BookingTable;
	private JButton btnDelete;
	private JLabel showIdLabel;
	private Customer c1;
	private Instructor i1;
	

	public ActivitiesGUI() {
		actCtr = new ActivitiesCtr();
		bookings = new ArrayList<ActivityBooking>();
		hires = new ArrayList<InstructorHire>();
		activities = new ArrayList<Activity>();
		custCtr = new CustomersCtr();
		c1 = null; //TODO
		i1 = null; //TODO
		
		
		
		activitiesWrapper = new JPanel();
		activitiesWrapper.setPreferredSize(new Dimension(780, 535));
		add(activitiesWrapper);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		activitiesWrapper.add(tabbedPane);
		
		JPanel bookActivityTab = new JPanel();
		bookActivityTab.setPreferredSize(new Dimension(780, 500));
		tabbedPane.addTab("Book Activity", null, bookActivityTab, null);
		
		dtmActivityBooking = new DefaultTableModel(new Object[][] {
				},
			new String[] {
				"ID", "Activity", "Date", "Time", "Open Activity"
			}
		);
		
		
		JPanel BookingInput = new JPanel();
		bookActivityTab.add(BookingInput,BorderLayout.NORTH);
		
		BookingInput.setLayout(new FormLayout(new ColumnSpec[] {
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				ColumnSpec.decode("127px"),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				ColumnSpec.decode("91px"),
				ColumnSpec.decode("65px"),
				ColumnSpec.decode("203dlu"),
				ColumnSpec.decode("59px"),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				ColumnSpec.decode("86px"),
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,},
			new RowSpec[] {
				FormFactory.LINE_GAP_ROWSPEC,
				RowSpec.decode("23px"),}));
		
		
		
		JButton buttonJoin = new JButton("Join Activity");
		buttonJoin.addActionListener(new ActionListener() {
			

			public void actionPerformed(ActionEvent e) {
				Customer customer = null;
				int id = 0;
				int actId = 0;
				ActivityBooking actBook = null;
				try{
					actId = (int) table.getModel().getValueAt(table.getSelectedRow(), 0);
					id = Integer.parseInt(JOptionPane.showInputDialog(activitiesWrapper, "Customer's ID", "Request", 1));
					customer = custCtr.getCustomer(id);
					customer.getPersonID();
					actBook = actCtr.findBooking(actId);
					if(actBook.getCustomers().size()<actBook.getActivity().getCapacity()){
						actBook.getCustomers().add(customer);
						actCtr.updateBooking(actBook.getID(), actBook.getCustomers(), actBook.getActivity(), actBook.getActivityTime(), actBook.isOpenActivity(), actBook.isInstructorHired());
						JOptionPane.showMessageDialog(activitiesWrapper, "You have joined activity: " + actBook.getActivity().getActivityType().name(), "Message", 1);
					}
				}
				catch(ArrayIndexOutOfBoundsException aie){
					JOptionPane.showMessageDialog(activitiesWrapper, "Select a row in the table!", "Warning", 2);
				}
				catch(NumberFormatException nfe){
					JOptionPane.showMessageDialog(activitiesWrapper, "Enter valid number!", "Warning", 2);
				}
				catch(NullPointerException npe){
					JOptionPane.showMessageDialog(activitiesWrapper, "Enter valid ID!", "Warning", 2);
				}
			}
		});
		BookingInput.add(buttonJoin, "4, 2, left, top");
		
		JButton btnSelectBooking = new JButton("Select");
		btnSelectBooking.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row = table.getSelectedRow();
				int ID = (int) table.getModel().getValueAt(row, 0);
				showBooking(ID);
			}
		});
		BookingInput.add(btnSelectBooking, "5, 2, right, top");
		
		JButton btnShowBooking = new JButton("Show");
		btnShowBooking.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int ID = 0;
				try{
					ID = Integer.parseInt(textFieldBooking.getText());
				}
				catch(NumberFormatException nfe){
					JOptionPane.showMessageDialog(activitiesWrapper, "Please, enter valid number!","Warning!", 2);
				}
				showBooking(ID);
			}
		});
		BookingInput.add(btnShowBooking, "7, 2, right, top");
		
		textFieldBooking = new JTextField();
		textFieldBooking.setText("ID");
		BookingInput.add(textFieldBooking, "9, 2, right, top");
		textFieldBooking.setColumns(10);
		
		BookingTable = new JPanel();
		bookActivityTab.add(BookingTable);
		BookingTable.setLayout(new CardLayout(0,0));
		
		JPanel showAllBookings = new JPanel();
		BookingTable.add(showAllBookings, "showAllBookings");
		
		
		JScrollPane scrollPane_ActivityBooking = new JScrollPane();
		showAllBookings.add(scrollPane_ActivityBooking);
		scrollPane_ActivityBooking.setPreferredSize(new Dimension(750,450));
		
		table = new JTable();
		table.setModel(dtmActivityBooking);
		table.getColumnModel().getColumn(0).setPreferredWidth(73);
		scrollPane_ActivityBooking.setViewportView(table);
		
		JPanel showBooking = new JPanel();
		BookingTable.add(showBooking, "showBooking");
		showBooking.setLayout(new BorderLayout(0,0));
		
		showIDField = new JTextField();
		showIDField.setColumns(10);
		
		showActivityField = new JTextField();
		showActivityField.setText("");
		showActivityField.setColumns(10);
		
		showDateField = new JTextField();
		showDateField.setText("");
		showDateField.setColumns(10);
		
		showTimeField = new JTextField();
		showTimeField.setColumns(10);
		
		showInstructorHired = new JCheckBox("Instructor Hired");
		
		
		JPanel showBookingInfo = new JPanel();
		showBooking.add(showBookingInfo, BorderLayout.NORTH);
		showBookingInfo.setLayout(new FlowLayout(FlowLayout.CENTER,5,5));
		showBooking.setBorder(BorderFactory.createTitledBorder("Activity Booking Info"));
		
		JPanel showBookingInfoContainer = new JPanel();
		showBookingInfo.add(showBookingInfoContainer);
		showBookingInfoContainer.setLayout(new BoxLayout(showBookingInfoContainer,
				BoxLayout.X_AXIS));
		
		JPanel showBookingLeft = new JPanel();
		showBookingInfoContainer.add(showBookingLeft);
		showBookingLeft.setLayout(new GridLayout(0, 1, 0, 0));
		
		JPanel showIdLabelPanel = new JPanel();
		showIdLabelPanel.setLayout(new BoxLayout(showIdLabelPanel,BoxLayout.X_AXIS));
		showBookingLeft.add(showIdLabelPanel);
		
		showIdLabel = new JLabel("ID");
		showIdLabelPanel.add(showIdLabel);
		showIdLabel.setHorizontalAlignment(SwingConstants.CENTER);
		
		JPanel showActivityLabelPanel = new JPanel();
		showActivityLabelPanel.setLayout(new BoxLayout(showActivityLabelPanel,BoxLayout.X_AXIS));
		showBookingLeft.add(showActivityLabelPanel);
		
		JLabel showActivityLabel = new JLabel("Activity");
		showActivityLabelPanel.add(showActivityLabel);
		showActivityLabel.setHorizontalAlignment(SwingConstants.CENTER);
		
		JPanel showDateLabelPanel = new JPanel();
		showDateLabelPanel.setLayout(new BoxLayout(showDateLabelPanel,BoxLayout.X_AXIS));
		showBookingLeft.add(showDateLabelPanel);
		
		JLabel showDateLabel = new JLabel("Date");
		showDateLabelPanel.add(showDateLabel);
		showDateLabel.setHorizontalAlignment(SwingConstants.CENTER);
		
		JPanel showTimeLabelPanel = new JPanel();
		showTimeLabelPanel.setLayout(new BoxLayout(showTimeLabelPanel,BoxLayout.X_AXIS));
		showBookingLeft.add(showTimeLabelPanel);
		
		JLabel showTimeLabel = new JLabel("Time");
		showTimeLabelPanel.add(showTimeLabel);
		showTimeLabel.setHorizontalAlignment(SwingConstants.CENTER);
		
		JPanel openActivityPanel = new JPanel();
		openActivityPanel.setLayout(new BoxLayout(openActivityPanel,BoxLayout.X_AXIS));
		showBookingLeft.add(openActivityPanel);
		
		JLabel openActivityLabel = new JLabel("Open Activity");
		openActivityPanel.add(openActivityLabel);
		openActivityLabel.setHorizontalAlignment(SwingConstants.CENTER);
		
		showOpenActivity = new JCheckBox("");
		showOpenActivity.setEnabled(false);
		
		JPanel showBookingRight = new JPanel();
		showBookingInfoContainer.add(showBookingRight);
		showBookingRight.setLayout(new BoxLayout(showBookingRight,BoxLayout.Y_AXIS));
		
		JPanel showIdFieldPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		showBookingRight.add(showIdFieldPanel);
		
		showIDField = new JTextField();
		showIdFieldPanel.add(showIDField);
		showIDField.setHorizontalAlignment(SwingConstants.CENTER);
		showIDField.setEditable(false);
		showIDField.setColumns(5);
		
		JPanel showActivityFieldPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		showBookingRight.add(showActivityFieldPanel);
		
		showActivityField = new JTextField();
		showActivityFieldPanel.add(showActivityField);
		showActivityField.setHorizontalAlignment(SwingConstants.CENTER);
		showActivityField.setEditable(false);
		showActivityField.setColumns(20);
		
		JPanel showDateFieldPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		showBookingRight.add(showDateFieldPanel);
		
		showDateField = new JTextField();
		showDateFieldPanel.add(showDateField);
		showDateField.setHorizontalAlignment(SwingConstants.CENTER);
		showDateField.setEditable(false);
		showDateField.setColumns(25);
		
		JPanel showTimeFieldPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		showBookingRight.add(showTimeFieldPanel);
		
		showTimeField = new JTextField();
		showTimeField.setHorizontalAlignment(SwingConstants.CENTER);
		showTimeField.setEditable(false);
		showTimeField.setColumns(15);
		showTimeFieldPanel.add(showTimeField);
		
		JPanel showOpenFieldPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		showBookingRight.add(showOpenFieldPanel);
		
		showOpenFieldPanel.add(showOpenActivity);
		
		JPanel showBottomPanel = new JPanel();
		showBooking.add(showBottomPanel,BorderLayout.CENTER);
		showBottomPanel.setLayout(new BorderLayout(0,0));
		
		JPanel showBottomMenuRight = new JPanel();
		showBottomPanel.add(showBottomMenuRight, BorderLayout.CENTER);
		
		btnDelete = new JButton("Delete");
		showBottomMenuRight.add(btnDelete);
		
		JPanel showBottomMenuLeft = new JPanel();
		showBottomPanel.add(showBottomMenuLeft, BorderLayout.WEST);
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showAllBookings();
			}
		});
		showBottomMenuLeft.add(btnBack);
		
		JPanel createBooking = new JPanel();
		BookingTable.add(createBooking, "createBooking");
		createBooking.setLayout(new BorderLayout(0,0));
		
		JPanel createBookingInfoContainer = new JPanel();
		createBooking.add(createBookingInfoContainer, BorderLayout.NORTH);
		createBookingInfoContainer.setLayout(new FlowLayout(FlowLayout.CENTER,5, 5));
		createBookingInfoContainer.setBorder(BorderFactory.createTitledBorder("Create Booking"));
		
		JPanel createBookingInfo = new JPanel();
		createBookingInfo.setLayout(new BoxLayout(createBookingInfo,BoxLayout.X_AXIS));
		createBookingInfoContainer.add(createBookingInfo);
		
		JPanel createBookingLeft = new JPanel();
		createBookingInfo.add(createBookingLeft);
		createBookingLeft.setLayout(new GridLayout(0, 1, 0, 0));
		
		JPanel createActivityLabelPanel = new JPanel();
		createActivityLabelPanel.setLayout(new BoxLayout(createActivityLabelPanel,
				BoxLayout.X_AXIS));
		createBookingLeft.add(createActivityLabelPanel);

		JLabel createActivityLabel = new JLabel("Activity");
		createActivityLabelPanel.add(createActivityLabel);
		createActivityLabel.setHorizontalAlignment(SwingConstants.CENTER);

		JPanel createDateLabelPanel = new JPanel();
		createDateLabelPanel.setLayout(new BoxLayout(
				createDateLabelPanel, BoxLayout.X_AXIS));
		createBookingLeft.add(createDateLabelPanel);

		JLabel createDateLabel = new JLabel("Date");
		createDateLabelPanel.add(createDateLabel);
		createDateLabel.setHorizontalAlignment(SwingConstants.CENTER);

		JPanel createTimeLabelPanel = new JPanel();
		createTimeLabelPanel.setLayout(new BoxLayout(createTimeLabelPanel,
				BoxLayout.X_AXIS));
		createBookingLeft.add(createTimeLabelPanel);

		JLabel createTimeLabel = new JLabel("Time");
		createTimeLabelPanel.add(createTimeLabel);
		createTimeLabel.setHorizontalAlignment(SwingConstants.CENTER);

		JPanel createOpenLabelPanel = new JPanel();
		createBookingLeft.add(createOpenLabelPanel);
		createOpenLabelPanel.setLayout(new BoxLayout(
				createOpenLabelPanel, BoxLayout.X_AXIS));
		
		JLabel createOpenLabel = new JLabel("Open Activity");
		createOpenLabelPanel.add(createOpenLabel);
		createOpenLabel.setHorizontalAlignment(SwingConstants.CENTER);

		
		JPanel createBookingRight = new JPanel();
		createBookingRight.setLayout(new BoxLayout(createBookingRight,BoxLayout.Y_AXIS));
		createBookingInfo.add(createBookingRight);
		
		JPanel createActivityFieldPanel = new JPanel();
		FlowLayout flowLayout_4 = (FlowLayout) createActivityFieldPanel
				.getLayout();
		flowLayout_4.setAlignment(FlowLayout.LEFT);
		createBookingRight.add(createActivityFieldPanel);
		
		String[] items = {"TennisCourt", "BadmintonCourt", "VolleyBallCourt", "HandBallCourt", "FitnessCenter"};
		@SuppressWarnings({ "rawtypes", "unchecked" })
		final
		JComboBox createActivityCombo = new JComboBox(new DefaultComboBoxModel(items));
		createActivityFieldPanel.add(createActivityCombo);
		
		JPanel createDateFieldPanel = new JPanel(
				new FlowLayout(FlowLayout.LEFT));
		createBookingRight.add(createDateFieldPanel);

		final JFormattedTextField createDateField = new JFormattedTextField("dd-mm-yyyy");
		createDateFieldPanel.add(createDateField);
		createDateField.setHorizontalAlignment(SwingConstants.CENTER);
		createDateField.setColumns(20);

		JPanel createTimeFieldPanel = new JPanel(new FlowLayout(
				FlowLayout.LEFT));
		createBookingRight.add(createTimeFieldPanel);

		final JFormattedTextField createTimeField = new JFormattedTextField("hh:mm");
		createTimeFieldPanel.add(createTimeField);
		createTimeField.setHorizontalAlignment(SwingConstants.CENTER);
		createTimeField.setColumns(25);

		JPanel createOpenPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		createBookingRight.add(createOpenPanel);
		
		final JCheckBox createOpenBox = new JCheckBox();
		createOpenPanel.add(createOpenBox);

		
		
		JPanel createBottomMenu = new JPanel();
		createBooking.add(createBottomMenu, BorderLayout.CENTER);
		createBottomMenu.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		JButton createBtnCreate = new JButton("Create");
		createBtnCreate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Customer customer = null;
				int id = 0;
				ArrayList<Customer> customers = new ArrayList<Customer>();
				String activityType = (String) createActivityCombo.getSelectedItem();
				String date = createDateField.getText();
				String time = createTimeField.getText();
				ActivityTime actTime = new ActivityTime(date, time);
				ArrayList<Activity> activities = actCtr.findFreeActivities(date, time, activityType);
				boolean openActivity = createOpenBox.isSelected();
				try{
					if(customer == null){
						id = Integer.parseInt(JOptionPane.showInputDialog(activitiesWrapper, "Customer's ID", "Request", 1));
						customer = custCtr.getCustomer(id);
						customers.add(customer);
					}
					Activity activity = activities.get(0);
					actCtr.newActivityBooking(customers, activity, actTime, openActivity, false);
					JOptionPane.showMessageDialog(activitiesWrapper, "You have created new activity: " + activity.getActivityType().name(), "Message", 1);
				}
				catch(ArrayIndexOutOfBoundsException ae){
					JOptionPane.showMessageDialog(activitiesWrapper, "No facility available, try another hour.", "Message", 3);
				}
				catch(NumberFormatException nfe){
					JOptionPane.showMessageDialog(activitiesWrapper, "Enter valid number!", "Warning", 2);
				}
				catch(NullPointerException npe){
					JOptionPane.showMessageDialog(activitiesWrapper, "Enter valid ID!", "Warning", 2);
			}
			}
		});
		createBottomMenu.add(createBtnCreate);

		JButton createBtnCancel = new JButton("Back");
		createBtnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showAllBookings();
			}
		});
		createBottomMenu.add(createBtnCancel);
		
		JPanel hireInstructorTab = new JPanel();
		hireInstructorTab.setPreferredSize(new Dimension(780, 500));
		tabbedPane.addTab("My Activities", null, hireInstructorTab, null);
		
		dtmInstructorHire = new DefaultTableModel(new Object[][] {
		},
		new String[] {
			"ID", "Activity", "Time", "Date"
		}
	);
		
		JPanel hireInputPanel = new JPanel();
		hireInstructorTab.add(hireInputPanel);
		hireInputPanel.setLayout(new FormLayout(new ColumnSpec[] {
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				ColumnSpec.decode("105px"),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				ColumnSpec.decode("right:90px"),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				ColumnSpec.decode("51px"),
				ColumnSpec.decode("227dlu"),
				ColumnSpec.decode("61px"),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				ColumnSpec.decode("left:86px"),
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,},
			new RowSpec[] {
				FormFactory.LINE_GAP_ROWSPEC,
				RowSpec.decode("23px"),}));
		
		JButton btnHireInstructor = new JButton("Hire Instructor");
		hireInputPanel.add(btnHireInstructor, "2, 2, left, top");
		
		JButton btnEditHire = new JButton("Edit ");
		hireInputPanel.add(btnEditHire, "4, 2, left, top");
		
		JButton btnShowInstructor = new JButton("Show ");
		hireInputPanel.add(btnShowInstructor, "8, 2, left, top");
		
		textFieldInstructor = new JTextField();
		hireInputPanel.add(textFieldInstructor, "10, 2, left, center");
		textFieldInstructor.setText("ID");
		textFieldInstructor.setColumns(10);
		
		JPanel hireTablePanel = new JPanel();
		hireInstructorTab.add(hireTablePanel);
		
		JScrollPane scrollPane_InstructorHire = new JScrollPane();
		hireTablePanel.add(scrollPane_InstructorHire);
		scrollPane_InstructorHire.setPreferredSize(new Dimension(750,450));
		table_1 = new JTable();
		table_1.setModel(dtmInstructorHire);
		table_1.getColumnModel().getColumn(0).setPreferredWidth(73);
		scrollPane_InstructorHire.setViewportView(table_1);
		fillInstructorTable();
		
		JPanel activityTab = new JPanel();
		activityTab.setPreferredSize(new Dimension(780, 500));
		tabbedPane.addTab("Activity", null, activityTab, null);
		
		JPanel ActivityInputPanel = new JPanel();
		activityTab.add(ActivityInputPanel);
		ActivityInputPanel.setLayout(new FormLayout(new ColumnSpec[] {
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				ColumnSpec.decode("105px"),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				ColumnSpec.decode("right:90px"),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				ColumnSpec.decode("51px"),
				ColumnSpec.decode("204dlu"),
				ColumnSpec.decode("103px"),
				ColumnSpec.decode("left:86px"),
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,},
			new RowSpec[] {
				FormFactory.LINE_GAP_ROWSPEC,
				RowSpec.decode("23px"),}));
		
		JButton btnCreateActivity = new JButton("Create Activity");
		ActivityInputPanel.add(btnCreateActivity, "2, 2, left, top");
		
		JButton btnEditActivity = new JButton("Edit Activity");
		ActivityInputPanel.add(btnEditActivity, "4, 2, left, top");
		
		JButton btnShowActivity = new JButton("Show Activity");
		ActivityInputPanel.add(btnShowActivity, "8, 2, left, top");
		
		showActivity = new JTextField();
		showActivity.setText("ID");
		ActivityInputPanel.add(showActivity, "9, 2, left, center");
		showActivity.setColumns(10);
		
		JPanel activityTablePanel = new JPanel();
		activityTab.add(activityTablePanel);
		
		JScrollPane scrollPane_Activity = new JScrollPane();
		activityTablePanel.add(scrollPane_Activity);
		scrollPane_Activity.setPreferredSize(new Dimension(750,450));
		
		dtmActivity = new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
					"ID", "Type", "Capacity"
				}
			);
		activityTable = new JTable();
		activityTable.setModel(dtmActivity);
		scrollPane_Activity.setViewportView(activityTable);
		fillActivitiesTable();
		
		
		JButton buttonCreate = new JButton("Create new Activity");
		buttonCreate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				createBooking();
			}
		});

		BookingInput.add(buttonCreate, "2, 2, left, top");
		
		showAllBookings();
	}
		protected void showAllBookings() {
			CardLayout c1 = (CardLayout) BookingTable.getLayout();
			c1.show(BookingTable, "showAllBookings");
			fillActivityTable();
		
	}
		protected void createBooking() {
		CardLayout c1 = (CardLayout) (BookingTable.getLayout());
		c1.show(BookingTable, "createBooking");
		
	}
		public void fillActivityTable(){
			bookings = actCtr.getAllBookings();
			for(ActivityBooking actBook: bookings){
				
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
				Date date = null;
				try {
					date = sdf.parse(actBook.getActivityTime().getDate() + " " + actBook.getActivityTime().getTime());
					System.out.println(date);
				} catch (ParseException e) {
					e.printStackTrace();
				}
				Date currentDate = null;
				currentDate = new Date();
				System.out.println(currentDate);
				//Initialise variables for filling table
				
				if(date.compareTo(currentDate)>0) {
				int ID = actBook.getID();
				String actType = actBook.getActivity().getActivityType().name();
				String dat = actBook.getActivityTime().getDate();
				String time = actBook.getActivityTime().getTime();
				String open = "no";
				if(actBook.isOpenActivity()) open = "yes";
				
				
				
				//add the values to the table
				Object[] rowData = {ID,actType,dat,time,open};
				dtmActivityBooking.addRow(rowData);
				}
			}
		}
			
			public void fillInstructorTable(){
				hires = actCtr.getInstructorHires();
				bookings = actCtr.getAllBookings();
				if(c1!=null){
				for(ActivityBooking insHire: bookings){
					//Initialise variables for filling table
					for(Customer c: insHire.getCustomers()){
						if(c.equals(c1)){
							int ID = insHire.getID();
							String actType = insHire.getActivity().getActivityType().name();
							String date = insHire.getActivityTime().getDate();
							String time = insHire.getActivityTime().getTime();	
							//add the values to the table
							Object[] rowData = {ID,actType,date,time};
							dtmInstructorHire.addRow(rowData);
								}
							}	
						}	
				}
				else if(i1 != null){
					for(InstructorHire insHire: hires){
						//Initialise variables for filling table
						
							if(i1.equals(insHire.getInstructor())){
								int ID = insHire.getId();
								String actType = insHire.getActivityBooking().getActivity().getActivityType().name();
								String date = insHire.getActivityTime().getDate();
								String time = insHire.getActivityTime().getTime();	
								//add the values to the table
								Object[] rowData = {ID,actType,date,time};
								dtmInstructorHire.addRow(rowData);
									}
									
							}	
				}
					else{
						for(InstructorHire insHire: hires){
						int ID = insHire.getId();
						String actType = insHire.getActivityBooking().getActivity().getActivityType().name();
						String date = insHire.getActivityTime().getDate();
						String time = insHire.getActivityTime().getTime();	
						//add the values to the table
						Object[] rowData = {ID,actType,date,time};
						dtmInstructorHire.addRow(rowData);
					}
					}
				}
			
			public void fillActivitiesTable(){
				activities = actCtr.getAllactivitys();
				for(Activity act: activities){
					
					//Initialise variables for filling table
					int ID = act.getID();
					//String actType = act.getActivityType().Activity();
					int capacity = act.getCapacity();
					
					
					
					
					//add the values to the table
					Object[] rowData = {ID,capacity};
					dtmActivity.addRow(rowData);
					
				}
			}
				protected void showBooking(int id) {
					ActivityBooking booking = null;
					try {
						booking = actCtr.findBooking(id);
					} catch (Exception e) {
						JOptionPane.showMessageDialog(null, "Error");
					}
						showIDField.setText(Integer.toString(booking.getID()));
						showActivityField.setText(booking.getActivity().getActivityType().name());
						showDateField.setText(booking.getActivityTime().getDate());
						showTimeField.setText(booking.getActivityTime().getTime());
						showOpenActivity.setSelected(booking.isOpenActivity());
						showInstructorHired.setSelected(booking.isInstructorHired());
						CardLayout cl = (CardLayout) (BookingTable.getLayout());
						cl.show(BookingTable, "showBooking");
						if(booking.isOpenActivity())btnDelete.setVisible(false);
			}
				
				}
			
		
	


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
	private JPanel hireTablePanel;
	private JTextField showHireIDField;
	private JTextField showHireActivityField;
	private JTextField showHireDateField;
	private JTextField showHireTimeField;
	private JTextField showHireCustomerField;
	private JTextField showHireInstructorField;
	private JTextField createHireCustomerField;
	private JTextField createHireBookingField;
	private JTextField createHireDateField;
	private JTextField createHireTimeField;
	

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
		
		//TODO HERE'S STARTING ACTIVITY BOOKING
		
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
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int id = Integer.parseInt(showIDField.getText());
				actCtr.deleteBooking(id);
				JOptionPane.showMessageDialog(activitiesWrapper, "Activity booking has been cancelled.", "Message", 1);
				showAllBookings();
			}
		});
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
		
		//TODO HERE'S STARTING INSTRUCTOR HIRE
		
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
		if(c1==null)btnHireInstructor.setEnabled(false);
		btnHireInstructor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showInstructorCreate();
			}
		});
		if(i1!=null)btnHireInstructor.setEnabled(false);
		hireInputPanel.add(btnHireInstructor, "2, 2, left, top");
		
		JButton btnEditHire = new JButton("Select");
		btnEditHire.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int row = table_1.getSelectedRow();
				int ID = (int) table_1.getModel().getValueAt(row, 0);
				showInstructorHire(ID);
			}
		});
		hireInputPanel.add(btnEditHire, "4, 2, left, top");
		
		JButton btnShowInstructor = new JButton("Show");
		btnShowInstructor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int ID = 0;
				try{
					ID = Integer.parseInt(textFieldInstructor.getText());
				}
				catch(NumberFormatException nfe){
					JOptionPane.showMessageDialog(activitiesWrapper, "Please, enter valid number!","Warning!", 2);
				}
				showInstructorHire(ID);
			}
		});
		hireInputPanel.add(btnShowInstructor, "8, 2, left, top");
		
		textFieldInstructor = new JTextField();
		hireInputPanel.add(textFieldInstructor, "10, 2, left, center");
		textFieldInstructor.setText("ID");
		textFieldInstructor.setColumns(10);
		
		hireTablePanel = new JPanel();
		hireInstructorTab.add(hireTablePanel);
		hireTablePanel.setLayout(new CardLayout(0, 0));
		
		JPanel showAllHires = new JPanel();
		hireTablePanel.add(showAllHires, "showhires");
		
		JScrollPane scrollPane_InstructorHire = new JScrollPane();
		showAllHires.add(scrollPane_InstructorHire);
		scrollPane_InstructorHire.setPreferredSize(new Dimension(750,450));
		table_1 = new JTable();
		table_1.setModel(dtmInstructorHire);
		table_1.getColumnModel().getColumn(0).setPreferredWidth(73);
		scrollPane_InstructorHire.setViewportView(table_1);
		
		JPanel showHire = new JPanel();
		hireTablePanel.add(showHire, "showhire");
		showHire.setLayout(new BorderLayout(0,0));
		
		JPanel showHireInfo = new JPanel();
		showHire.add(showHireInfo, BorderLayout.NORTH);
		showHireInfo.setLayout(new FlowLayout(FlowLayout.CENTER,5,5));
		showHire.setBorder(BorderFactory.createTitledBorder("Activity Hire Info"));
		
		JPanel showHireInfoContainer = new JPanel();
		showHireInfo.add(showHireInfoContainer);
		showHireInfoContainer.setLayout(new BoxLayout(showHireInfoContainer,
				BoxLayout.X_AXIS));
		
		JPanel showHireLeft = new JPanel();
		showHireInfoContainer.add(showHireLeft);
		showHireLeft.setLayout(new GridLayout(0, 1, 0, 0));
		
		JPanel showHireIdLabelPanel = new JPanel();
		showHireIdLabelPanel.setLayout(new BoxLayout(showHireIdLabelPanel,BoxLayout.X_AXIS));
		showHireLeft.add(showHireIdLabelPanel);
		
		JLabel showHireIdLabel = new JLabel("ID");
		showHireIdLabelPanel.add(showHireIdLabel);
		showHireIdLabel.setHorizontalAlignment(SwingConstants.CENTER);
		
		JPanel showHireCustomerLabelPanel = new JPanel();
		showHireCustomerLabelPanel.setLayout(new BoxLayout(showHireCustomerLabelPanel,BoxLayout.X_AXIS));
		showHireLeft.add(showHireCustomerLabelPanel);
		
		JLabel showHireCustomerLabel = new JLabel("Customer");
		showHireCustomerLabelPanel.add(showHireCustomerLabel);
		showHireCustomerLabel.setHorizontalAlignment(SwingConstants.CENTER);
		
		JPanel showHireInstructorLabelPanel = new JPanel();
		showHireInstructorLabelPanel.setLayout(new BoxLayout(showHireInstructorLabelPanel,BoxLayout.X_AXIS));
		showHireLeft.add(showHireInstructorLabelPanel);
		
		JLabel showHireInstructorLabel = new JLabel("Instructor");
		showHireInstructorLabelPanel.add(showHireInstructorLabel);
		showHireInstructorLabel.setHorizontalAlignment(SwingConstants.CENTER);
		
		JPanel showHireActivityLabelPanel = new JPanel();
		showHireActivityLabelPanel.setLayout(new BoxLayout(showHireActivityLabelPanel,BoxLayout.X_AXIS));
		showHireLeft.add(showHireActivityLabelPanel);
		
		JLabel showHireActivityLabel = new JLabel("Activity");
		showHireActivityLabelPanel.add(showHireActivityLabel);
		showHireActivityLabel.setHorizontalAlignment(SwingConstants.CENTER);
		
		JPanel showHireDateLabelPanel = new JPanel();
		showHireDateLabelPanel.setLayout(new BoxLayout(showHireDateLabelPanel,BoxLayout.X_AXIS));
		showHireLeft.add(showHireDateLabelPanel);
		
		JLabel showHireDateLabel = new JLabel("Date");
		showHireDateLabelPanel.add(showHireDateLabel);
		showHireDateLabel.setHorizontalAlignment(SwingConstants.CENTER);
		
		JPanel showHireTimeLabelPanel = new JPanel();
		showHireTimeLabelPanel.setLayout(new BoxLayout(showHireTimeLabelPanel,BoxLayout.X_AXIS));
		showHireLeft.add(showHireTimeLabelPanel);
		
		JLabel showHireTimeLabel = new JLabel("Time");
		showHireTimeLabelPanel.add(showHireTimeLabel);
		showHireTimeLabel.setHorizontalAlignment(SwingConstants.CENTER);
		
		
		JPanel showHireRight = new JPanel();
		showHireInfoContainer.add(showHireRight);
		showHireRight.setLayout(new BoxLayout(showHireRight,BoxLayout.Y_AXIS));
		
		JPanel showHireIdFieldPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		showHireRight.add(showHireIdFieldPanel);
		
		showHireIDField = new JTextField();
		showHireIdFieldPanel.add(showHireIDField);
		showHireIDField.setHorizontalAlignment(SwingConstants.CENTER);
		showHireIDField.setEditable(false);
		showHireIDField.setColumns(5);
		
		JPanel showHireCustomerFieldPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		showHireRight.add(showHireCustomerFieldPanel);
		
		showHireCustomerField = new JTextField();
		showHireCustomerFieldPanel.add(showHireCustomerField);
		showHireCustomerField.setHorizontalAlignment(SwingConstants.CENTER);
		showHireCustomerField.setEditable(false);
		showHireCustomerField.setColumns(5);
		
		JPanel showHireInstructorFieldPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		showHireRight.add(showHireInstructorFieldPanel);
		
		showHireInstructorField = new JTextField();
		showHireInstructorFieldPanel.add(showHireInstructorField);
		showHireInstructorField.setHorizontalAlignment(SwingConstants.CENTER);
		showHireInstructorField.setEditable(false);
		showHireInstructorField.setColumns(5);
		
		JPanel showHireActivityFieldPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		showHireRight.add(showHireActivityFieldPanel);
		
		showHireActivityField = new JTextField();
		showHireActivityFieldPanel.add(showHireActivityField);
		showHireActivityField.setHorizontalAlignment(SwingConstants.CENTER);
		showHireActivityField.setEditable(false);
		showHireActivityField.setColumns(20);
		
		JPanel showHireDateFieldPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		showHireRight.add(showHireDateFieldPanel);
		
		showHireDateField = new JTextField();
		showHireDateFieldPanel.add(showHireDateField);
		showHireDateField.setHorizontalAlignment(SwingConstants.CENTER);
		showHireDateField.setEditable(false);
		showHireDateField.setColumns(25);
		
		JPanel showHireTimeFieldPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		showHireRight.add(showHireTimeFieldPanel);
		
		showHireTimeField = new JTextField();
		showHireTimeField.setHorizontalAlignment(SwingConstants.CENTER);
		showHireTimeField.setEditable(false);
		showHireTimeField.setColumns(15);
		showHireTimeFieldPanel.add(showHireTimeField);
		
		
		
		JPanel showBottomHirePanel = new JPanel();
		showHire.add(showBottomHirePanel,BorderLayout.CENTER);
		showBottomHirePanel.setLayout(new BorderLayout(0,0));
		
		JPanel showBottomHireMenuRight = new JPanel();
		showBottomHirePanel.add(showBottomHireMenuRight, BorderLayout.CENTER);
		
		JButton btnHireDelete = new JButton("Cancel");
		btnHireDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int id = Integer.parseInt(showHireIDField.getText());
				actCtr.deleteInstructorHire(id);
				JOptionPane.showMessageDialog(activitiesWrapper, "Instructor hiring has been cancelled.", "Message", 1);
				showAllHires();
			}
		});
		showBottomHireMenuRight.add(btnHireDelete);
		
		JPanel showBottomHireMenuLeft = new JPanel();
		showBottomHirePanel.add(showBottomHireMenuLeft, BorderLayout.WEST);
		
		JPanel createHire = new JPanel();
		hireTablePanel.add(createHire, "createHire");
		createHire.setLayout(new BorderLayout(0,0));
		fillInstructorTable();
		
		JButton btnHireBack = new JButton("Back");
		btnHireBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showAllHires();
			}
		});
		
		showBottomHireMenuLeft.add(btnHireBack);
		createHire.setLayout(new BorderLayout(0,0));
		
		JPanel createHireInfoContainer = new JPanel();
		createHire.add(createHireInfoContainer, BorderLayout.NORTH);
		createHireInfoContainer.setLayout(new FlowLayout(FlowLayout.CENTER,5, 5));
		createHireInfoContainer.setBorder(BorderFactory.createTitledBorder("Create Hire"));
		
		JPanel createHireInfo = new JPanel();
		createHireInfo.setLayout(new BoxLayout(createHireInfo,BoxLayout.X_AXIS));
		createHireInfoContainer.add(createHireInfo);
		
		JPanel createHireLeft = new JPanel();
		createHireInfo.add(createHireLeft);
		createHireLeft.setLayout(new GridLayout(0, 1, 0, 0));
		
		JPanel createHireLabelPanel = new JPanel();
		createHireLabelPanel.setLayout(new BoxLayout(createHireLabelPanel,
				BoxLayout.X_AXIS));
		createHireLeft.add(createHireLabelPanel);

		JLabel createHireLabel = new JLabel("Booking ID");
		createHireLabelPanel.add(createHireLabel);
		createHireLabel.setHorizontalAlignment(SwingConstants.CENTER);

	
		JPanel createHireCustomerLabelPanel = new JPanel();
		createHireLeft.add(createHireCustomerLabelPanel);
		createHireCustomerLabelPanel.setLayout(new BoxLayout(
				createHireCustomerLabelPanel, BoxLayout.X_AXIS));
		
		JLabel createHireCustomerLabel = new JLabel("Customer ID");
		createHireCustomerLabelPanel.add(createHireCustomerLabel);
		createHireCustomerLabel.setHorizontalAlignment(SwingConstants.CENTER);
		
		JPanel createHireDateLabelPanel = new JPanel();
		createHireDateLabelPanel.setLayout(new BoxLayout(
				createHireDateLabelPanel, BoxLayout.X_AXIS));
		createHireLeft.add(createHireDateLabelPanel);
		
		JLabel createHireDateLabel = new JLabel("Date");
		createHireDateLabelPanel.add(createHireDateLabel);
		createHireDateLabel.setHorizontalAlignment(SwingConstants.CENTER);

		JPanel createHireTimeLabelPanel = new JPanel();
		createHireTimeLabelPanel.setLayout(new BoxLayout(createHireTimeLabelPanel,
				BoxLayout.X_AXIS));
		createHireLeft.add(createHireTimeLabelPanel);

		JLabel createHireTimeLabel = new JLabel("Time");
		createHireTimeLabelPanel.add(createHireTimeLabel);
		createHireTimeLabel.setHorizontalAlignment(SwingConstants.CENTER);

		
		JPanel createHireRight = new JPanel();
		createHireRight.setLayout(new BoxLayout(createHireRight,BoxLayout.Y_AXIS));
		createHireInfo.add(createHireRight);
		
		JPanel createHireFieldPanel = new JPanel();
		FlowLayout flowLayout = (FlowLayout) createHireFieldPanel.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		createHireRight.add(createHireFieldPanel);
		
		createHireBookingField = new JTextField();
		createHireFieldPanel.add(createHireBookingField);
		createHireBookingField.setHorizontalAlignment(SwingConstants.CENTER);
		createHireBookingField.setColumns(20);
		createHireBookingField.setEnabled(false);
		
		JPanel createHireCustomerPanel = new JPanel();
		FlowLayout flowLayout1 = (FlowLayout) createHireCustomerPanel.getLayout();
		flowLayout1.setAlignment(FlowLayout.LEFT);
		createHireRight.add(createHireCustomerPanel);
		
		createHireCustomerField = new JTextField();
		createHireCustomerPanel.add(createHireCustomerField);
		createHireCustomerField.setHorizontalAlignment(SwingConstants.CENTER);
		createHireCustomerField.setColumns(20);
		createHireCustomerField.setEnabled(false);
		
		
		JPanel createHireDateFieldPanel = new JPanel(
				new FlowLayout(FlowLayout.LEFT));
		createHireRight.add(createHireDateFieldPanel);

		createHireDateField = new JFormattedTextField("dd-mm-yyyy");
		createHireDateFieldPanel.add(createHireDateField);
		createHireDateField.setHorizontalAlignment(SwingConstants.CENTER);
		createHireDateField.setColumns(20);

		JPanel createHireTimeFieldPanel = new JPanel(new FlowLayout(
				FlowLayout.LEFT));
		createHireRight.add(createHireTimeFieldPanel);

		createHireTimeField = new JFormattedTextField("hh:mm");
		createHireTimeFieldPanel.add(createHireTimeField);
		createHireTimeField.setHorizontalAlignment(SwingConstants.CENTER);
		createHireTimeField.setColumns(20);

		
		JPanel createHireBottomMenu = new JPanel();
		createHire.add(createHireBottomMenu, BorderLayout.CENTER);
		createHireBottomMenu.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		JButton createHireBtnCreate = new JButton("Create");
		createHireBtnCreate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					int row = table.getSelectedRow();
					int ID = (int) table.getModel().getValueAt(row, 0);
					ActivityBooking activBook = actCtr.findBooking(ID);
					Activity activity = activBook.getActivity();
					String date = createHireDateField.getText();
					String time = createHireTimeField.getText();
					Instructor instructor = null;
					for(Instructor instr: activity.getActivityInstructors()){
						if(actCtr.checkInstructorAvailability(instr, date,time))instructor = instr;
					}
					int custID = 0;
					if(c1==null)custID = Integer.parseInt(JOptionPane.showInputDialog(activitiesWrapper, "Customer's ID", "Request", 1)); 
					c1 = custCtr.getCustomer(custID);
					ActivityTime actTime = new ActivityTime(date, time);
					actCtr.newInstructorHire(c1,instructor,activBook,actTime);
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
		createHireBottomMenu.add(createHireBtnCreate);

		JButton createHireBtnCancel = new JButton("Back");
		createHireBtnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showAllBookings();
			}
		});
		createHireBottomMenu.add(createHireBtnCancel);
		
		//TODO HERE'S STARTING ACTIVITY
		
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
		protected void showInstructorCreate() {
			try{
				int row = table_1.getSelectedRow();
			int bookID = (int) table_1.getModel().getValueAt(row, 0);
			createHireBookingField.setText(String.valueOf(bookID));
			int id = Integer.parseInt(JOptionPane.showInputDialog(activitiesWrapper, "Enter Customer's ID:", "Request", 1));
			ActivityBooking actBook = actCtr.findBooking(bookID);
			String date = actBook.getActivityTime().getDate();
			String time = actBook.getActivityTime().getTime();
			createHireDateField.setText(date);
			createHireTimeField.setText(time);
			if(c1!=null)createHireCustomerField.setText(Integer.toString(c1.getPersonID()));
			else createHireCustomerField.setText(String.valueOf(id));
			CardLayout c1 = (CardLayout) (hireTablePanel.getLayout());
			c1.show(hireTablePanel, "createHire");
			}
			catch(NumberFormatException nfe){
				JOptionPane.showMessageDialog(activitiesWrapper, "Please, enter valid number!","Warning!", 2);
			}
			catch(ArrayIndexOutOfBoundsException ae){
				JOptionPane.showMessageDialog(activitiesWrapper, "Please, select a row from the table!","Warning!", 2);
			}
			
		
	}
		protected void showAllHires() {
			CardLayout c1 = (CardLayout) hireTablePanel.getLayout();
			c1.show(hireTablePanel, "showhires");
			fillInstructorTable();
		
	}
		protected void showInstructorHire(int id) {
			InstructorHire hire = null;
			try {
				hire = actCtr.findInstructorHire(id);
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "Error");
			}
				showHireIDField.setText(Integer.toString(hire.getId()));
				showHireCustomerField.setText(Integer.toString(hire.getCustomer().getPersonID()));
				showHireInstructorField.setText(Integer.toString(hire.getInstructor().getPersonID()));
				showHireActivityField.setText(hire.getActivityBooking().getActivity().getActivityType().name());
				showHireDateField.setText(hire.getActivityTime().getDate());
				showHireTimeField.setText(hire.getActivityTime().getTime());
				CardLayout cl = (CardLayout) (hireTablePanel.getLayout());
				cl.show(hireTablePanel, "showhire");
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
		private void fillActivityTable(){
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
			
			private void fillInstructorTable(){
				dtmInstructorHire = new DefaultTableModel(new Object[][] {
				},
				new String[] {
					"ID", "Activity", "Time", "Date"
				}
			);
				table_1.setModel(dtmInstructorHire);
				hires = actCtr.getInstructorHires();
				bookings = actCtr.getAllBookings();
				
				if(c1!=null){
					dtmInstructorHire = new DefaultTableModel(new Object[][] {
					},
					new String[] {
						"Booking ID", "Instructor Hire ID", "Activity", "Time", "Date", "Instructor"
					}
				);
				for(ActivityBooking insHire: bookings){
					//Initialise variables for filling table
					for(Customer c: insHire.getCustomers()){
						if(c.equals(c1)){
							int ID = insHire.getID();
							String actType = insHire.getActivity().getActivityType().name();
							String date = insHire.getActivityTime().getDate();
							String time = insHire.getActivityTime().getTime();	
							boolean hired = insHire.isInstructorHired();
							int IHID = 0;
							if(hired)for(InstructorHire  ih: hires)if(ih.getActivityBooking().getID()==ID)IHID = ih.getId();
							//add the values to the table
							Object[] rowData = {ID,IHID,actType,date,time,hired};
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
			
			private void fillActivitiesTable(){
				activities = actCtr.getAllactivitys();
				for(Activity act: activities){
					
					//Initialise variables for filling table
					int ID = act.getID();
					String actType = act.getActivityType().name();
					int capacity = act.getCapacity();
					
					
					
					
					//add the values to the table
					Object[] rowData = {ID,actType, capacity};
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
			
		
	


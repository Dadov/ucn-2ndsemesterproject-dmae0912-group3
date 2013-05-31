package GUI;

import java.awt.Dimension;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.CardLayout;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.border.TitledBorder;
import javax.swing.UIManager;

import Controllers.RoomsCtr;
import Models.Room;
import Models.RoomBooking;

import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CustomersGUI extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel customersWrapper;
	private JTabbedPane customersTabbedPane;
	private JPanel custCrudPanel;
	private JPanel upperCrudWrapper;
	private JPanel lowerCrudWrapper;
	private JPanel custBillingPanel;
	private JPanel upperCrudWrapperLeft;
	private JPanel upperCrudWrapperRight;
	private JButton newCustomerButton;
	private JLabel custIDLabel;
	private JTextField custIDTextField;
	private JButton custSearchButton;
	private JPanel allCustPanel;
	private JPanel showCustPanel;
	private JPanel editCustPanel;
	private JPanel newCustPanel;
	private JScrollPane allCustScrollPane;
	private JTable allCustTable;
	private DefaultTableModel allCustTableModel;
	private JPanel upperBillingPanelWrapper;
	private JPanel lowerBillingPanelWrapper;
	private JPanel upperBillWrapLeft;
	private JPanel upperBillWrapRight;
	private JButton billGetBookingsButton;
	private JLabel billCustIDLabel;
	private JTextField billCustIDField;
	private JButton billGetInstructorButton;
	private JPanel billRooms;
	private JPanel billInstructors;
	private JPanel upperBillRoomWrap;
	private JPanel lowerBillRoomWrap;
	private JPanel upperBillInstructorsWrap;
	private JPanel lowerBillInstructorsWrap;
	private JPanel lowerBillRoomWrapLeft;
	private JLabel markPaidLabel;
	private JTextField markPaidField;
	private JButton markPaidButton;
	private JScrollPane custBillRoomScrollPane;
	private JTable custBillRoomTable;
	private DefaultTableModel custBillRoomTableModel;
	private JScrollPane custBillinstHireScrollPane;
	private JTable custBillinstHireTable;
	private DefaultTableModel custBillinstHireTableModel;

	public CustomersGUI() {
		initialize();
	}

	public void initialize() {
		customersWrapper = new JPanel();
		customersWrapper.setPreferredSize(new Dimension(780, 535));
		add(customersWrapper);

		customersTabbedPane = new JTabbedPane(JTabbedPane.TOP);
		customersWrapper.add(customersTabbedPane);

		custCrudPanel = new JPanel();
		custCrudPanel.setPreferredSize(new Dimension(780, 500));
		customersTabbedPane.addTab("Manage Customers", null, custCrudPanel,
				null);
		custCrudPanel.setLayout(new BorderLayout(0, 0));

		upperCrudWrapper = new JPanel();
		upperCrudWrapper.setBorder(new TitledBorder(UIManager
				.getBorder("TitledBorder.border"), "Customers",
				TitledBorder.LEADING, TitledBorder.TOP, null, null));
		custCrudPanel.add(upperCrudWrapper, BorderLayout.NORTH);
		upperCrudWrapper.setLayout(new BorderLayout(0, 0));

		upperCrudWrapperLeft = new JPanel();
		upperCrudWrapper.add(upperCrudWrapperLeft, BorderLayout.WEST);

		newCustomerButton = new JButton("New Customer");
		newCustomerButton.setActionCommand("New Customer\r\n");
		upperCrudWrapperLeft.add(newCustomerButton);

		upperCrudWrapperRight = new JPanel();
		upperCrudWrapper.add(upperCrudWrapperRight, BorderLayout.EAST);

		custIDLabel = new JLabel("Customer ID:");
		upperCrudWrapperRight.add(custIDLabel);

		custIDTextField = new JTextField();
		upperCrudWrapperRight.add(custIDTextField);
		custIDTextField.setColumns(10);

		custSearchButton = new JButton("Search");
		upperCrudWrapperRight.add(custSearchButton);

		lowerCrudWrapper = new JPanel();
		custCrudPanel.add(lowerCrudWrapper);
		lowerCrudWrapper.setLayout(new CardLayout(0, 0));

		allCustPanel = new JPanel();
		allCustPanel.setBorder(new TitledBorder(null, "All Customers",
				TitledBorder.LEADING, TitledBorder.TOP, null, null));
		lowerCrudWrapper.add(allCustPanel, "name_2955934936676");

		allCustScrollPane = new JScrollPane();
		allCustPanel.add(allCustScrollPane);

		allCustTable = new JTable();
		allCustTable
				.setPreferredScrollableViewportSize(new Dimension(750, 340));

		allCustTableModel = new DefaultTableModel(new Object[][] {},
				new String[] { "column", "column", "column", "column" });
		allCustTable.setModel(allCustTableModel);
		allCustTable.getColumnModel().getColumn(0).setPreferredWidth(73);
		allCustScrollPane.setViewportView(allCustTable);


		showCustPanel = new JPanel();
		lowerCrudWrapper.add(showCustPanel, "name_2958297347905");

		editCustPanel = new JPanel();
		lowerCrudWrapper.add(editCustPanel, "name_2959956938604");

		newCustPanel = new JPanel();
		lowerCrudWrapper.add(newCustPanel, "name_2961412833601");

		custBillingPanel = new JPanel();
		custBillingPanel.setPreferredSize(new Dimension(780, 500));
		customersTabbedPane.addTab("Customers Billing", null, custBillingPanel,
				null);
		custBillingPanel.setLayout(new BorderLayout(0, 0));

		upperBillingPanelWrapper = new JPanel();
		upperBillingPanelWrapper.setBorder(new TitledBorder(null, "Options",
				TitledBorder.LEADING, TitledBorder.TOP, null, null));
		custBillingPanel.add(upperBillingPanelWrapper, BorderLayout.NORTH);
		upperBillingPanelWrapper.setLayout(new BorderLayout(0, 0));

		upperBillWrapLeft = new JPanel();
		FlowLayout fl_upperBillWrapLeft = (FlowLayout) upperBillWrapLeft
				.getLayout();
		fl_upperBillWrapLeft.setAlignment(FlowLayout.LEFT);
		upperBillingPanelWrapper.add(upperBillWrapLeft, BorderLayout.WEST);

		billCustIDLabel = new JLabel("Customer ID:");
		upperBillWrapLeft.add(billCustIDLabel);

		billCustIDField = new JTextField();
		upperBillWrapLeft.add(billCustIDField);
		billCustIDField.setColumns(10);

		billGetBookingsButton = new JButton("Get Room Bookings");
		billGetBookingsButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				fillCustRoomBillTable();
			}
		});
		upperBillWrapLeft.add(billGetBookingsButton);

		billGetInstructorButton = new JButton("Get Hired Instructors");
		billGetInstructorButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fillInstHireBillTable();
			}
		});
		upperBillWrapLeft.add(billGetInstructorButton);

		upperBillWrapRight = new JPanel();
		FlowLayout fl_upperBillWrapRight = (FlowLayout) upperBillWrapRight
				.getLayout();
		fl_upperBillWrapRight.setAlignment(FlowLayout.RIGHT);
		upperBillingPanelWrapper.add(upperBillWrapRight);

		lowerBillingPanelWrapper = new JPanel();
		custBillingPanel.add(lowerBillingPanelWrapper);
		lowerBillingPanelWrapper.setLayout(new CardLayout(0, 0));

		billRooms = new JPanel();
		billRooms.setBorder(new TitledBorder(UIManager
				.getBorder("TitledBorder.border"), "Customers Room Bookings",
				TitledBorder.CENTER, TitledBorder.TOP, null, null));
		lowerBillingPanelWrapper.add(billRooms, "Bill Rooms");
		billRooms.setLayout(new BorderLayout(0, 0));

		upperBillRoomWrap = new JPanel();
		billRooms.add(upperBillRoomWrap);

		custBillRoomScrollPane = new JScrollPane();
		upperBillRoomWrap.add(custBillRoomScrollPane);

		custBillRoomTable = new JTable();
		custBillRoomTable
				.setPreferredScrollableViewportSize(new Dimension(750, 340));
		custBillRoomTableModel = new DefaultTableModel(new Object[][] {},
				new String[] { "Booking ID", "Customer ID", "First Name",
						"Last Name", "Address", "Total Price", "Status" });
		custBillRoomTable.setModel(custBillRoomTableModel);

		custBillRoomTable.getColumnModel().getColumn(0).setPreferredWidth(73);
		custBillRoomScrollPane.setViewportView(custBillRoomTable);

		lowerBillRoomWrap = new JPanel();
		billRooms.add(lowerBillRoomWrap, BorderLayout.SOUTH);
		lowerBillRoomWrap.setLayout(new BorderLayout(0, 0));

		lowerBillRoomWrapLeft = new JPanel();
		lowerBillRoomWrap.add(lowerBillRoomWrapLeft, BorderLayout.WEST);

		markPaidLabel = new JLabel("Room Booking ID: ");
		lowerBillRoomWrapLeft.add(markPaidLabel);

		markPaidField = new JTextField();
		lowerBillRoomWrapLeft.add(markPaidField);
		markPaidField.setColumns(10);

		markPaidButton = new JButton("Mark Paid");
		markPaidButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				markPaidRoomBooking();
			}
		});
		lowerBillRoomWrapLeft.add(markPaidButton);

		billInstructors = new JPanel();
		billInstructors.setBorder(new TitledBorder(UIManager
				.getBorder("TitledBorder.border"),
				"Customers Hired Instructors", TitledBorder.CENTER,
				TitledBorder.TOP, null, null));
		lowerBillingPanelWrapper.add(billInstructors, "Bill Instructors");
		billInstructors.setLayout(new BorderLayout(0, 0));

		upperBillInstructorsWrap = new JPanel();
		billInstructors.add(upperBillInstructorsWrap, BorderLayout.CENTER);

		custBillinstHireScrollPane = new JScrollPane();
		upperBillInstructorsWrap.add(custBillinstHireScrollPane);

		custBillinstHireTable = new JTable();
		custBillinstHireTable.setPreferredScrollableViewportSize(new Dimension(
				750, 340));
		custBillinstHireTableModel = new DefaultTableModel(new Object[][] {},
				new String[] { "Instructor Hire ID", "Customer ID",
						"First Name",
						"Last Name", "Address", "Total Price", "Status" });
		custBillinstHireTable.setModel(custBillinstHireTableModel);

		custBillinstHireTable.getColumnModel().getColumn(0)
				.setPreferredWidth(73);
		custBillinstHireScrollPane.setViewportView(custBillinstHireTable);

		lowerBillInstructorsWrap = new JPanel();
		billInstructors.add(lowerBillInstructorsWrap, BorderLayout.SOUTH);
	}

	/*
	 * Manually added methods
	 */

	@SuppressWarnings("unused")
	private void fillCustRoomBillTable() {
		CardLayout card = (CardLayout) (lowerBillingPanelWrapper.getLayout());
		card.show(lowerBillingPanelWrapper, "Bill Rooms");

		custBillRoomTableModel.getDataVector().removeAllElements();

		RoomsCtr roomsCtr = new RoomsCtr();
		ArrayList<RoomBooking> roomBookings;

		// Filtering customers room booking, first get a custID
		int custID;
		try {
			custID = Integer.parseInt(billCustIDField.getText());
		} catch (NumberFormatException e) {
			custID = 0; // default value used if one in field is invalid
		}

		// if custId = 0 , then get all roomBookings
		if (custID == 0) {
			roomBookings = roomsCtr.getAllBookings();
			// otherwise return only ones that have given custID
		} else {
			roomBookings = roomsCtr.getAllBookings();
			ArrayList<RoomBooking> custRoomBookings = new ArrayList<RoomBooking>();
			for (RoomBooking booking : roomBookings) {
				if (booking.getCustomer().getPersonID() == custID) {
					custRoomBookings.add(booking);
				}
			}
			roomBookings = custRoomBookings;
		}

		// Filling the table using filtered room bookings
		for (RoomBooking roombooking : roomBookings) {
			int bookingID = roombooking.getId();
			int customerID = roombooking.getCustomer().getPersonID();
			String fname = roombooking.getCustomer().getFname();
			String lname = roombooking.getCustomer().getLname();
			String address = roombooking.getCustomer().getAddress() + ", "
					+ roombooking.getCustomer().getCity() + ", "
					+ roombooking.getCustomer().getZIP() + ", "
					+ roombooking.getCustomer().getCountry();
			// getting total price
			double totalPrice = 0;
			ArrayList<Room> rooms = roombooking.getRoomsBooked();
			String roomsBooked = "";
			for (Room room : rooms) {
				totalPrice = totalPrice + room.getPrice();
			}
			// Determining status of booking either paid or cancelled and date
			String status;
			if (roombooking.isCancelled()) {
				status = "Cancelled at: " + roombooking.getDateAccounted();
			} else if (roombooking.isPaid()) {
				status = "Paid at: " + roombooking.getDateAccounted();
			} else {
				status = "Not accounted";
			}

			Object[] rowData = { bookingID, customerID, fname, lname,
					address, totalPrice, status };
			custBillRoomTableModel.addRow(rowData);
		}
	}

	private void markPaidRoomBooking() {
		RoomsCtr roomsCtr = new RoomsCtr();

		int id = Integer.parseInt(markPaidField.getText());
		RoomBooking roomBooking = roomsCtr.findBooking(id);

		String payDate = new SimpleDateFormat("dd-MM-yyyy").format(Calendar
				.getInstance().getTime());
		roomBooking.setDateAccounted(payDate);
		roomBooking.setPaid(true);

		int rc = roomsCtr.updateRoomBooking(roomBooking);

		if (rc > 0) {
			JOptionPane.showMessageDialog(null, "Booking number " + id
					+ " was paid", "Booking payment",
					JOptionPane.INFORMATION_MESSAGE);
		} else {
			JOptionPane.showMessageDialog(null, "Booking number " + id
					+ " did not exist", "Booking payment",
					JOptionPane.ERROR_MESSAGE);
		}

		// refresh
		fillCustRoomBillTable();

	}

	private void fillInstHireBillTable() {
		CardLayout card = (CardLayout) (lowerBillingPanelWrapper.getLayout());
		card.show(lowerBillingPanelWrapper, "Bill Instructors");

	}
	
	@SuppressWarnings("unused")
	private void markPaidInstHire() {

	}


}

package GUI;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EtchedBorder;
import javax.swing.table.DefaultTableModel;

import Controllers.CustomersCtr;
import Controllers.RoomsCtr;
import Models.Customer;
import Models.Room;
import Models.RoomBooking;
import Models.RoomType;

public class RoomsGUI extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel roomsWrapper;
	private JPanel roomBookingPanel;
	private JPanel checkRoomAvailabilityPanel;
	private JPanel chraTablePanel;
	private JPanel chraInputPanel;
	private JTextField chraDateStartField;
	private JTextField chraDateEndField;
	private JComboBox<RoomType> chraRoomTypeComboBox;
	private JLabel chraRoomTypeLabel;
	private JLabel chraStartDateLabel;
	private JLabel chraEndDateLabel;
	private JScrollPane chraTableScrollPane;
	private JTable chraTable;
	private DefaultTableModel chraTableModel;
	private JPanel bookRoomPanel;
	private JPanel roomBookingsPanel;
	private JTextField brStartDateTextField;
	private JTextField brEndDateTextField;
	private JTextField brCustomerIdTextField;
	private JButton btnBookRoom;
	private JLabel brRoomTypeLabel;
	private JLabel brStartDateLabel;
	private JLabel brEndDateLabel;
	private JLabel brCustomerLabel;
	private JButton chraButton;
	private JScrollPane rbTableScrollPane;
	private JTable rbTable;
	private DefaultTableModel rbTableModel;
	private JPanel cancelBookingPanel;
	private JTextField rbCustIDField;
	private JButton rbFilterButton;
	private JLabel rbCustIDLabel;
	private JTextField cbpTextField;
	private JButton cbpButton;
	private JLabel cbpLabel;
	private JPanel rbTablePanel;

	private RoomsCtr roomsCtr;
	private CustomersCtr customersCtr;
	private JFormattedTextField brRoomNumField;
	private JPanel rbFilterPanel;
	private JPanel rbLabelPanel;
	private JLabel rbLabel;

	public RoomsGUI() {
		setPreferredSize(new Dimension(780, 535));

		roomsWrapper = new JPanel();
		roomsWrapper.setPreferredSize(new Dimension(780, 535));
		add(roomsWrapper);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		roomsWrapper.add(tabbedPane);

		checkRoomAvailabilityPanel = new JPanel();
		tabbedPane.addTab("Check Room Availability", null,
				checkRoomAvailabilityPanel, null);
		checkRoomAvailabilityPanel.setLayout(new BorderLayout(0, 0));

		chraInputPanel = new JPanel();
		chraInputPanel.setBorder(null);
		checkRoomAvailabilityPanel.add(chraInputPanel, BorderLayout.NORTH);
		GridBagLayout gbl_chraInputPanel = new GridBagLayout();
		gbl_chraInputPanel.columnWidths = new int[] { 30, 80, 36, 140, 35, 140,
				35, 0, 0 };
		gbl_chraInputPanel.rowHeights = new int[] { 0, 20, 0 };
		gbl_chraInputPanel.columnWeights = new double[] { 0.0, 0.0, 0.0, 0.0,
				0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		gbl_chraInputPanel.rowWeights = new double[] { 0.0, 0.0,
				Double.MIN_VALUE };
		chraInputPanel.setLayout(gbl_chraInputPanel);

		chraRoomTypeLabel = new JLabel("Room Type:");
		GridBagConstraints gbc_chraRoomTypeLabel = new GridBagConstraints();
		gbc_chraRoomTypeLabel.anchor = GridBagConstraints.WEST;
		gbc_chraRoomTypeLabel.insets = new Insets(0, 0, 5, 5);
		gbc_chraRoomTypeLabel.gridx = 1;
		gbc_chraRoomTypeLabel.gridy = 0;
		chraInputPanel.add(chraRoomTypeLabel, gbc_chraRoomTypeLabel);

		chraStartDateLabel = new JLabel("Start Date (dd-mm-yyyy):");
		GridBagConstraints gbc_chraStartDateLabel = new GridBagConstraints();
		gbc_chraStartDateLabel.insets = new Insets(0, 0, 5, 5);
		gbc_chraStartDateLabel.gridx = 3;
		gbc_chraStartDateLabel.gridy = 0;
		chraInputPanel.add(chraStartDateLabel, gbc_chraStartDateLabel);

		chraEndDateLabel = new JLabel("End Date (dd-mm-yyyy):");
		GridBagConstraints gbc_chraEndDateLabel = new GridBagConstraints();
		gbc_chraEndDateLabel.insets = new Insets(0, 0, 5, 5);
		gbc_chraEndDateLabel.gridx = 5;
		gbc_chraEndDateLabel.gridy = 0;
		chraInputPanel.add(chraEndDateLabel, gbc_chraEndDateLabel);

		chraRoomTypeComboBox = new JComboBox<RoomType>();
		chraRoomTypeComboBox.setModel(new DefaultComboBoxModel<RoomType>(
				RoomType.values()));
		chraRoomTypeComboBox.setToolTipText("");
		GridBagConstraints gbc_chraRoomTypeComboBox = new GridBagConstraints();
		gbc_chraRoomTypeComboBox.anchor = GridBagConstraints.NORTHWEST;
		gbc_chraRoomTypeComboBox.insets = new Insets(0, 0, 0, 5);
		gbc_chraRoomTypeComboBox.gridx = 1;
		gbc_chraRoomTypeComboBox.gridy = 1;
		chraInputPanel.add(chraRoomTypeComboBox, gbc_chraRoomTypeComboBox);

		chraDateStartField = new JTextField();
		GridBagConstraints gbc_chraDateStartField = new GridBagConstraints();
		gbc_chraDateStartField.anchor = GridBagConstraints.NORTHWEST;
		gbc_chraDateStartField.insets = new Insets(0, 0, 0, 5);
		gbc_chraDateStartField.gridx = 3;
		gbc_chraDateStartField.gridy = 1;
		chraInputPanel.add(chraDateStartField, gbc_chraDateStartField);
		chraDateStartField.setColumns(10);

		chraDateEndField = new JTextField();
		GridBagConstraints gbc_chraDateEndField = new GridBagConstraints();
		gbc_chraDateEndField.insets = new Insets(0, 0, 0, 5);
		gbc_chraDateEndField.anchor = GridBagConstraints.NORTHWEST;
		gbc_chraDateEndField.gridx = 5;
		gbc_chraDateEndField.gridy = 1;
		chraInputPanel.add(chraDateEndField, gbc_chraDateEndField);
		chraDateEndField.setColumns(10);

		chraButton = new JButton("Check Availability");
		chraButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				fillChraTable();
			}
		});
		GridBagConstraints gbc_chraButton = new GridBagConstraints();
		gbc_chraButton.gridx = 7;
		gbc_chraButton.gridy = 1;
		chraInputPanel.add(chraButton, gbc_chraButton);

		chraTablePanel = new JPanel();
		chraTablePanel.setBorder(null);
		checkRoomAvailabilityPanel.add(chraTablePanel, BorderLayout.CENTER);

		chraTableScrollPane = new JScrollPane();
		chraTablePanel.add(chraTableScrollPane);

		chraTable = new JTable();
		chraTable.setPreferredScrollableViewportSize(new Dimension(750, 420));

		chraTableModel = new DefaultTableModel(new Object[][] {}, new String[] {
				"Room Number", "Room Type", "Price", "Note" });
		chraTable.setModel(chraTableModel);
		chraTable.getColumnModel().getColumn(0).setPreferredWidth(73);
		chraTableScrollPane.setViewportView(chraTable);

		roomBookingPanel = new JPanel();
		roomBookingPanel.setPreferredSize(new Dimension(780, 500));
		tabbedPane.addTab("Room Bookings", null, roomBookingPanel, null);
		roomBookingPanel.setLayout(new BorderLayout(0, 0));

		bookRoomPanel = new JPanel();
		bookRoomPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null,
				null));
		roomBookingPanel.add(bookRoomPanel, BorderLayout.NORTH);
		GridBagLayout gbl_bookRoomPanel = new GridBagLayout();
		gbl_bookRoomPanel.columnWidths = new int[] { 30, 80, 35, 140, 35, 140,
				35, 100, 35, 85, 0 };
		gbl_bookRoomPanel.rowHeights = new int[] { 20, 30, 0 };
		gbl_bookRoomPanel.columnWeights = new double[] { 0.0, 1.0, 0.0, 0.0,
				0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		gbl_bookRoomPanel.rowWeights = new double[] { 0.0, 0.0,
				Double.MIN_VALUE };
		bookRoomPanel.setLayout(gbl_bookRoomPanel);

		brRoomTypeLabel = new JLabel("Room Numbers:");
		GridBagConstraints gbc_brRoomTypeLabel = new GridBagConstraints();
		gbc_brRoomTypeLabel.anchor = GridBagConstraints.WEST;
		gbc_brRoomTypeLabel.insets = new Insets(0, 0, 5, 5);
		gbc_brRoomTypeLabel.gridx = 1;
		gbc_brRoomTypeLabel.gridy = 0;
		bookRoomPanel.add(brRoomTypeLabel, gbc_brRoomTypeLabel);

		brStartDateLabel = new JLabel("Start Date (dd-mm-yyyy):");
		GridBagConstraints gbc_brStartDateLabel = new GridBagConstraints();
		gbc_brStartDateLabel.anchor = GridBagConstraints.WEST;
		gbc_brStartDateLabel.insets = new Insets(0, 0, 5, 5);
		gbc_brStartDateLabel.gridx = 3;
		gbc_brStartDateLabel.gridy = 0;
		bookRoomPanel.add(brStartDateLabel, gbc_brStartDateLabel);

		brEndDateLabel = new JLabel("End Date (dd-mm-yyyy):");
		GridBagConstraints gbc_brEndDateLabel = new GridBagConstraints();
		gbc_brEndDateLabel.anchor = GridBagConstraints.WEST;
		gbc_brEndDateLabel.insets = new Insets(0, 0, 5, 5);
		gbc_brEndDateLabel.gridx = 5;
		gbc_brEndDateLabel.gridy = 0;
		bookRoomPanel.add(brEndDateLabel, gbc_brEndDateLabel);

		brCustomerLabel = new JLabel("Customer ID:");
		GridBagConstraints gbc_brCustomerLabel = new GridBagConstraints();
		gbc_brCustomerLabel.anchor = GridBagConstraints.WEST;
		gbc_brCustomerLabel.insets = new Insets(0, 0, 5, 5);
		gbc_brCustomerLabel.gridx = 7;
		gbc_brCustomerLabel.gridy = 0;
		bookRoomPanel.add(brCustomerLabel, gbc_brCustomerLabel);

		brRoomNumField = new JFormattedTextField();
		GridBagConstraints gbc_brRoomNumField = new GridBagConstraints();
		gbc_brRoomNumField.insets = new Insets(0, 0, 0, 5);
		gbc_brRoomNumField.fill = GridBagConstraints.HORIZONTAL;
		gbc_brRoomNumField.gridx = 1;
		gbc_brRoomNumField.gridy = 1;
		bookRoomPanel.add(brRoomNumField, gbc_brRoomNumField);

		brStartDateTextField = new JTextField();
		GridBagConstraints gbc_brStartDateTextField = new GridBagConstraints();
		gbc_brStartDateTextField.anchor = GridBagConstraints.WEST;
		gbc_brStartDateTextField.insets = new Insets(0, 0, 0, 5);
		gbc_brStartDateTextField.gridx = 3;
		gbc_brStartDateTextField.gridy = 1;
		bookRoomPanel.add(brStartDateTextField, gbc_brStartDateTextField);
		brStartDateTextField.setColumns(10);

		brEndDateTextField = new JTextField();
		GridBagConstraints gbc_brEndDateTextField = new GridBagConstraints();
		gbc_brEndDateTextField.anchor = GridBagConstraints.WEST;
		gbc_brEndDateTextField.insets = new Insets(0, 0, 0, 5);
		gbc_brEndDateTextField.gridx = 5;
		gbc_brEndDateTextField.gridy = 1;
		bookRoomPanel.add(brEndDateTextField, gbc_brEndDateTextField);
		brEndDateTextField.setColumns(10);

		brCustomerIdTextField = new JTextField();
		GridBagConstraints gbc_brCustomerIdTextField = new GridBagConstraints();
		gbc_brCustomerIdTextField.anchor = GridBagConstraints.WEST;
		gbc_brCustomerIdTextField.insets = new Insets(0, 0, 0, 5);
		gbc_brCustomerIdTextField.gridx = 7;
		gbc_brCustomerIdTextField.gridy = 1;
		bookRoomPanel.add(brCustomerIdTextField, gbc_brCustomerIdTextField);
		brCustomerIdTextField.setColumns(10);

		btnBookRoom = new JButton("Book Rooms");
		btnBookRoom.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				bookRooms();
			}
		});
		GridBagConstraints gbc_btnBookRoom = new GridBagConstraints();
		gbc_btnBookRoom.anchor = GridBagConstraints.NORTHWEST;
		gbc_btnBookRoom.gridx = 9;
		gbc_btnBookRoom.gridy = 1;
		bookRoomPanel.add(btnBookRoom, gbc_btnBookRoom);

		roomBookingsPanel = new JPanel();
		roomBookingsPanel.setBorder(null);
		roomBookingPanel.add(roomBookingsPanel, BorderLayout.CENTER);
		roomBookingsPanel.setLayout(new BorderLayout(0, 0));

		rbLabelPanel = new JPanel();
		FlowLayout flowLayout_2 = (FlowLayout) rbLabelPanel.getLayout();
		flowLayout_2.setAlignment(FlowLayout.RIGHT);
		roomBookingsPanel.add(rbLabelPanel, BorderLayout.WEST);

		rbLabel = new JLabel("Current Bookings:");
		rbLabel.setHorizontalAlignment(SwingConstants.CENTER);
		rbLabelPanel.add(rbLabel);

		rbFilterPanel = new JPanel();
		FlowLayout fl_rbFilterPanel = (FlowLayout) rbFilterPanel.getLayout();
		fl_rbFilterPanel.setAlignment(FlowLayout.RIGHT);
		roomBookingsPanel.add(rbFilterPanel, BorderLayout.EAST);

		rbCustIDLabel = new JLabel(
				"Customer ID (Leave 0, to show all bookings):");
		rbFilterPanel.add(rbCustIDLabel);

		rbCustIDField = new JTextField();
		rbCustIDField.setText("0");
		rbFilterPanel.add(rbCustIDField);
		rbCustIDField.setColumns(10);

		rbFilterButton = new JButton("Filter");
		rbFilterButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				fillRbTable();
			}
		});
		rbFilterPanel.add(rbFilterButton);

		rbTablePanel = new JPanel();
		roomBookingsPanel.add(rbTablePanel, BorderLayout.SOUTH);

		rbTableScrollPane = new JScrollPane();
		rbTablePanel.add(rbTableScrollPane);

		rbTable = new JTable();
		rbTable.setPreferredScrollableViewportSize(new Dimension(750, 340));
		rbTableModel = new DefaultTableModel(new Object[][] {}, new String[] {
				"Booking ID", "Customer ID", "First Name", "Last Name",
				"Rooms Booked", "Date Start", "Date End", "Date Booked" });
		rbTable.setModel(rbTableModel);

		rbTable.getColumnModel().getColumn(0).setPreferredWidth(73);
		rbTableScrollPane.setViewportView(rbTable);

		cancelBookingPanel = new JPanel();
		FlowLayout flowLayout = (FlowLayout) cancelBookingPanel.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		cancelBookingPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED,
				null, null));
		roomBookingPanel.add(cancelBookingPanel, BorderLayout.SOUTH);

		cbpLabel = new JLabel("Booking ID:");
		cancelBookingPanel.add(cbpLabel);

		cbpTextField = new JTextField();
		cancelBookingPanel.add(cbpTextField);
		cbpTextField.setColumns(10);

		cbpButton = new JButton("Cancel Booking");
		cbpButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				cancelRoomBooking();
			}
		});
		cancelBookingPanel.add(cbpButton);
	}

	/*
	 * Manually added methods:
	 */

	// Fill check room availability table, triggered by using
	// "Check Availability" button
	private void fillChraTable() {
		chraTableModel.getDataVector().removeAllElements();

		roomsCtr = new RoomsCtr();
		String startDate = chraDateStartField.getText();
		String endDate = chraDateEndField.getText();
		RoomType roomType = (RoomType) chraRoomTypeComboBox.getSelectedItem();
		ArrayList<Room> freeRooms = roomsCtr.findFreeRoomsOfType(startDate,
				endDate, roomType);

		for (Room room : freeRooms) {
			int number = room.getNumber();
			Enum<RoomType> type = room.getRoomType();
			double price = room.getPrice();
			String note = room.getNote();

			Object[] rowData = { number, type, price, note };
			chraTableModel.addRow(rowData);
		}

	}

	// Fill room booking table, triggered by using "Filter" button
	private void fillRbTable() {
		rbTableModel.getDataVector().removeAllElements();

		roomsCtr = new RoomsCtr();
		ArrayList<RoomBooking> roomBookings;

		// Filtering customers room booking, first get a custID
		int custID;
		try {
			custID = Integer.parseInt(rbCustIDField.getText());
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
			ArrayList<Room> rooms = roombooking.getRoomsBooked();
			// constructing String out of all rooms booked numbers
			String roomsBooked = "";
			for (Room room : rooms) {
				roomsBooked = roomsBooked + room.getNumber() + ", ";
			}
			String dateStart = roombooking.getDateStart();
			String dateEnd = roombooking.getDateEnd();
			String dateBooked = roombooking.getDateBooked();

			Object[] rowData = { bookingID, customerID, fname, lname,
					roomsBooked, dateStart, dateEnd, dateBooked };
			rbTableModel.addRow(rowData);
		}

	}

	// Creates entries for booking a room for given customer, room and dates
	private void bookRooms() {
		roomsCtr = new RoomsCtr();
		customersCtr = new CustomersCtr();

		Customer customer = customersCtr.getCustomer(Integer
				.parseInt(brCustomerIdTextField.getText()));

		ArrayList<Room> rooms = new ArrayList<Room>();
		// String out of Room numbers, whitespace and commas
		String roomNumbersInput = brRoomNumField.getText();
		// remove whitespace
		roomNumbersInput.replaceAll("\\s", "");
		// split by ","
		String[] roomNumbers = roomNumbersInput.split(",");
		for (int i = 0; i < roomNumbers.length; i++) {
			rooms.add(roomsCtr.findRoom(Integer.parseInt(roomNumbers[i])));
		}
		// Formatting date for input
		String bookDate = new SimpleDateFormat("dd-MM-yyyy").format(Calendar
				.getInstance().getTime());
		String startDate = brStartDateTextField.getText();
		String endDate = brEndDateTextField.getText();

		try {
			roomsCtr.newRoomBooking(customer, rooms, bookDate, startDate,
					endDate);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage(),
					"Invalid dates", JOptionPane.ERROR_MESSAGE);
			// e.printStackTrace();
		}

		// 'refreshing' the table, maybe should add some popup
		fillRbTable();
	}

	// functionality moved to fillRbTable
	@SuppressWarnings("unused")
	private void filterRbByCustomer() {

	}

	private void cancelRoomBooking() {
		roomsCtr = new RoomsCtr();

		// get Room Booking to be updated
		int id = Integer.parseInt(cbpTextField.getText());
		RoomBooking roomBooking = roomsCtr.findBooking(id);
		// get current date
		String cancellDate = new SimpleDateFormat("dd-MM-yyyy").format(Calendar
				.getInstance().getTime());
		// update booking record, marking it cancelled with current date
		roomBooking.setDateAccounted(cancellDate);
		roomBooking.setCancelled(true);

		int rc = roomsCtr.updateRoomBooking(roomBooking);

		if (rc > 0) {
			JOptionPane.showMessageDialog(null, "Booking number " + id
					+ " was cancelled", "Booking cancellation",
					JOptionPane.INFORMATION_MESSAGE);
		} else {
			JOptionPane.showMessageDialog(null, "Booking number " + id
					+ " did not exist", "Booking cancellation",
					JOptionPane.ERROR_MESSAGE);
		}

		// 'refreshing' the table, maybe should add some popup
		fillRbTable();
	}
}

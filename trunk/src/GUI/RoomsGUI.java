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
import javax.swing.JFrame;
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
import Models.Agency;
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
	private JTabbedPane roomsTabbedPane;
	private JButton btnPickDate;
	private JButton btnPickDate_1;
	private JButton btnPickDate_2;
	private JButton btnPickDate_3;
	private JLabel lblAgencyId;
	private JTextField textFieldAgencyId;

	public RoomsGUI() {
		initialize();
	}

	public void initialize() {
		setPreferredSize(new Dimension(780, 535));

		roomsWrapper = new JPanel();
		roomsWrapper.setPreferredSize(new Dimension(780, 535));
		add(roomsWrapper);

		roomsTabbedPane = new JTabbedPane(JTabbedPane.TOP);
		roomsWrapper.add(roomsTabbedPane);
		

		checkRoomAvailabilityPanel = new JPanel();
		roomsTabbedPane.addTab("Check Room Availability", null,
				checkRoomAvailabilityPanel, null);
		checkRoomAvailabilityPanel.setLayout(new BorderLayout(0, 0));

		chraInputPanel = new JPanel();
		chraInputPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null,
				null));
		checkRoomAvailabilityPanel.add(chraInputPanel, BorderLayout.NORTH);
		GridBagLayout gbl_chraInputPanel = new GridBagLayout();
		gbl_chraInputPanel.columnWidths = new int[] { 30, 80, 36, 85, 0, 35, 85, 0,
				35, 0, 0 };
		gbl_chraInputPanel.rowHeights = new int[] { 0, 20, 0 };
		gbl_chraInputPanel.columnWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0,
				0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
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

		chraStartDateLabel = new JLabel("Start Date:");
		GridBagConstraints gbc_chraStartDateLabel = new GridBagConstraints();
		gbc_chraStartDateLabel.anchor = GridBagConstraints.WEST;
		gbc_chraStartDateLabel.insets = new Insets(0, 0, 5, 5);
		gbc_chraStartDateLabel.gridx = 3;
		gbc_chraStartDateLabel.gridy = 0;
		chraInputPanel.add(chraStartDateLabel, gbc_chraStartDateLabel);

		chraEndDateLabel = new JLabel("End Date:");
		GridBagConstraints gbc_chraEndDateLabel = new GridBagConstraints();
		gbc_chraEndDateLabel.anchor = GridBagConstraints.WEST;
		gbc_chraEndDateLabel.insets = new Insets(0, 0, 5, 5);
		gbc_chraEndDateLabel.gridx = 6;
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
		gbc_chraDateStartField.gridx = 3;
		gbc_chraDateStartField.gridy = 1;
		chraInputPanel.add(chraDateStartField, gbc_chraDateStartField);
		chraDateStartField.setColumns(10);
		
		btnPickDate = new JButton("Pick Date");
		btnPickDate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
						JFrame f = new JFrame();
						chraDateStartField.setText(new DatePicker(f).setPickedDate());
			}
		});
		GridBagConstraints gbc_btnPickDate = new GridBagConstraints();
		gbc_btnPickDate.insets = new Insets(0, 0, 0, 5);
		gbc_btnPickDate.gridx = 4;
		gbc_btnPickDate.gridy = 1;
		chraInputPanel.add(btnPickDate, gbc_btnPickDate);

		chraDateEndField = new JTextField();
		GridBagConstraints gbc_chraDateEndField = new GridBagConstraints();
		gbc_chraDateEndField.anchor = GridBagConstraints.NORTHWEST;
		gbc_chraDateEndField.gridx = 6;
		gbc_chraDateEndField.gridy = 1;
		chraInputPanel.add(chraDateEndField, gbc_chraDateEndField);
		chraDateEndField.setColumns(10);

		chraButton = new JButton("Check Availability");
		chraButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				fillChraTable();
			}
		});
		
		btnPickDate_1 = new JButton("Pick Date");
		btnPickDate_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
						JFrame f = new JFrame();
						chraDateEndField.setText(new DatePicker(f).setPickedDate());
			}
		});
		GridBagConstraints gbc_btnPickDate_1 = new GridBagConstraints();
		gbc_btnPickDate_1.insets = new Insets(0, 0, 0, 5);
		gbc_btnPickDate_1.gridx = 7;
		gbc_btnPickDate_1.gridy = 1;
		chraInputPanel.add(btnPickDate_1, gbc_btnPickDate_1);
		GridBagConstraints gbc_chraButton = new GridBagConstraints();
		gbc_chraButton.anchor = GridBagConstraints.NORTH;
		gbc_chraButton.gridx = 9;
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
		roomsTabbedPane.addTab("Room Bookings", null, roomBookingPanel, null);
		roomBookingPanel.setLayout(new BorderLayout(0, 0));

		bookRoomPanel = new JPanel();
		bookRoomPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null,
				null));
		roomBookingPanel.add(bookRoomPanel, BorderLayout.NORTH);
		GridBagLayout gbl_bookRoomPanel = new GridBagLayout();
		gbl_bookRoomPanel.columnWidths = new int[] {77, 30, 77, 30, 0, 77, 30, 0, 77, 0, 77, 30};
		gbl_bookRoomPanel.rowHeights = new int[] { 20, 30, 0 };
		gbl_bookRoomPanel.columnWeights = new double[] { 0.0, 0.0, 0.0, 0.0,
				0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0 };
		gbl_bookRoomPanel.rowWeights = new double[] { 0.0, 0.0,
				Double.MIN_VALUE };
		bookRoomPanel.setLayout(gbl_bookRoomPanel);

		brRoomTypeLabel = new JLabel("Room Numbers:");
		GridBagConstraints gbc_brRoomTypeLabel = new GridBagConstraints();
		gbc_brRoomTypeLabel.gridwidth = 2;
		gbc_brRoomTypeLabel.anchor = GridBagConstraints.WEST;
		gbc_brRoomTypeLabel.insets = new Insets(0, 0, 5, 5);
		gbc_brRoomTypeLabel.gridx = 0;
		gbc_brRoomTypeLabel.gridy = 0;
		bookRoomPanel.add(brRoomTypeLabel, gbc_brRoomTypeLabel);

		brStartDateLabel = new JLabel("Start Date:");
		GridBagConstraints gbc_brStartDateLabel = new GridBagConstraints();
		gbc_brStartDateLabel.anchor = GridBagConstraints.WEST;
		gbc_brStartDateLabel.insets = new Insets(0, 0, 5, 5);
		gbc_brStartDateLabel.gridx = 2;
		gbc_brStartDateLabel.gridy = 0;
		bookRoomPanel.add(brStartDateLabel, gbc_brStartDateLabel);

		brEndDateLabel = new JLabel("End Date:");
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
		gbc_brCustomerLabel.gridx = 8;
		gbc_brCustomerLabel.gridy = 0;
		bookRoomPanel.add(brCustomerLabel, gbc_brCustomerLabel);
		
		lblAgencyId = new JLabel("Agency ID:");
		GridBagConstraints gbc_lblAgencyId = new GridBagConstraints();
		gbc_lblAgencyId.anchor = GridBagConstraints.WEST;
		gbc_lblAgencyId.insets = new Insets(0, 0, 5, 5);
		gbc_lblAgencyId.gridx = 10;
		gbc_lblAgencyId.gridy = 0;
		bookRoomPanel.add(lblAgencyId, gbc_lblAgencyId);

		brRoomNumField = new JFormattedTextField();
		GridBagConstraints gbc_brRoomNumField = new GridBagConstraints();
		gbc_brRoomNumField.insets = new Insets(0, 0, 0, 5);
		gbc_brRoomNumField.fill = GridBagConstraints.HORIZONTAL;
		gbc_brRoomNumField.gridx = 0;
		gbc_brRoomNumField.gridy = 1;
		bookRoomPanel.add(brRoomNumField, gbc_brRoomNumField);

		brStartDateTextField = new JTextField();
		GridBagConstraints gbc_brStartDateTextField = new GridBagConstraints();
		gbc_brStartDateTextField.insets = new Insets(0, 0, 0, 5);
		gbc_brStartDateTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_brStartDateTextField.gridx = 2;
		gbc_brStartDateTextField.gridy = 1;
		bookRoomPanel.add(brStartDateTextField, gbc_brStartDateTextField);
		brStartDateTextField.setColumns(10);
		
		btnPickDate_2 = new JButton("Calendar");
		btnPickDate_2.setSize(new Dimension(1, 1));
		btnPickDate_2.setSize(10, 10);
		btnPickDate_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame f = new JFrame();
				brStartDateTextField.setText(new DatePicker(f).setPickedDate());
			}
		});
		GridBagConstraints gbc_btnPickDate_2 = new GridBagConstraints();
		gbc_btnPickDate_2.insets = new Insets(0, 0, 0, 5);
		gbc_btnPickDate_2.gridx = 3;
		gbc_btnPickDate_2.gridy = 1;
		bookRoomPanel.add(btnPickDate_2, gbc_btnPickDate_2);

		brEndDateTextField = new JTextField();
		GridBagConstraints gbc_brEndDateTextField = new GridBagConstraints();
		gbc_brEndDateTextField.insets = new Insets(0, 0, 0, 5);
		gbc_brEndDateTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_brEndDateTextField.gridx = 5;
		gbc_brEndDateTextField.gridy = 1;
		bookRoomPanel.add(brEndDateTextField, gbc_brEndDateTextField);
		brEndDateTextField.setColumns(10);
		
		btnPickDate_3 = new JButton("Calendar");
		btnPickDate_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame f = new JFrame();
				brEndDateTextField.setText(new DatePicker(f).setPickedDate());
			}
		});
		GridBagConstraints gbc_btnPickDate_3 = new GridBagConstraints();
		gbc_btnPickDate_3.insets = new Insets(0, 0, 0, 5);
		gbc_btnPickDate_3.gridx = 6;
		gbc_btnPickDate_3.gridy = 1;
		bookRoomPanel.add(btnPickDate_3, gbc_btnPickDate_3);

		brCustomerIdTextField = new JTextField();
		GridBagConstraints gbc_brCustomerIdTextField = new GridBagConstraints();
		gbc_brCustomerIdTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_brCustomerIdTextField.insets = new Insets(0, 0, 0, 5);
		gbc_brCustomerIdTextField.gridx = 8;
		gbc_brCustomerIdTextField.gridy = 1;
		bookRoomPanel.add(brCustomerIdTextField, gbc_brCustomerIdTextField);
		brCustomerIdTextField.setColumns(10);
		
		textFieldAgencyId = new JTextField();
		GridBagConstraints gbc_textFieldAgencyId = new GridBagConstraints();
		gbc_textFieldAgencyId.insets = new Insets(0, 0, 0, 5);
		gbc_textFieldAgencyId.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldAgencyId.gridx = 10;
		gbc_textFieldAgencyId.gridy = 1;
		bookRoomPanel.add(textFieldAgencyId, gbc_textFieldAgencyId);
		textFieldAgencyId.setColumns(10);
				
						btnBookRoom = new JButton("Book Rooms");
						btnBookRoom.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent arg0) {
								bookRooms();
							}
						});
						GridBagConstraints gbc_btnBookRoom = new GridBagConstraints();
						gbc_btnBookRoom.anchor = GridBagConstraints.WEST;
						gbc_btnBookRoom.gridx = 11;
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
		rbTable.setPreferredScrollableViewportSize(new Dimension(750, 345));
		rbTableModel = new DefaultTableModel(new Object[][] {}, new String[] {
				"Booking ID", "Customer ID", "First Name", "Last Name",
				"Rooms Booked", "Date Start", "Date End",
				"Status" });
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

	public void setAgencyID(Agency agency){
		roomsTabbedPane.setSelectedIndex(1);
		textFieldAgencyId.setText(String.valueOf(agency.getID()));
		}
	
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
					roomsBooked, dateStart, dateEnd, status };
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
			int agenID = Integer.parseInt(textFieldAgencyId.getText());
			Agency agency = customersCtr.getAgency(agenID);
			agency.getProvidedCustomers().add(customer);
			roomsCtr.newRoomBooking(customer, rooms, bookDate, startDate,
					endDate);
			customersCtr.updateAgency(agenID, agency.getName(), agency.getAgencyDiscountLevel(), agency.getProvidedCustomers());
		}
		catch(NumberFormatException nfe){
			JOptionPane.showMessageDialog(roomsWrapper, "Enter valid number","Error", JOptionPane.ERROR_MESSAGE);
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

	@SuppressWarnings("unused")
	private boolean checkDateFormat(String input) {
		String inputFormat = input;
		SimpleDateFormat correnctFormat = new SimpleDateFormat("dd-mm-yyyy");
		
		// try {
		//
		// }

		return false;
	}
}

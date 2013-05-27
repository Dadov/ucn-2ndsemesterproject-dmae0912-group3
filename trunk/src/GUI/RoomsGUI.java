package GUI;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

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
	private JPanel bookRoomPanel;
	private JPanel roomBookingsPanel;
	private JComboBox<RoomType> comboBox;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JButton btnBookRoom;
	private JLabel brRoomTypeLabel;
	private JLabel brStartDateLabel;
	private JLabel brEndDateLabel;
	private JLabel brCustomerLabel;
	private JButton btnNewButton;
	private JScrollPane rbTableScrollPane;
	private JTable rbTable;

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
		gbl_chraInputPanel.columnWidths = new int[] { 0, 28, 0, 86, 0, 86, 0,
				0, 0 };
		gbl_chraInputPanel.rowHeights = new int[] { 0, 20, 0 };
		gbl_chraInputPanel.columnWeights = new double[] { 0.0, 0.0, 0.0, 0.0,
				0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		gbl_chraInputPanel.rowWeights = new double[] { 0.0, 0.0,
				Double.MIN_VALUE };
		chraInputPanel.setLayout(gbl_chraInputPanel);

		chraRoomTypeLabel = new JLabel("Room Type:");
		GridBagConstraints gbc_chraRoomTypeLabel = new GridBagConstraints();
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

		btnNewButton = new JButton("Check Availability");
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.gridx = 7;
		gbc_btnNewButton.gridy = 1;
		chraInputPanel.add(btnNewButton, gbc_btnNewButton);

		chraTablePanel = new JPanel();
		chraTablePanel.setBorder(null);
		checkRoomAvailabilityPanel.add(chraTablePanel, BorderLayout.CENTER);

		chraTableScrollPane = new JScrollPane();
		chraTablePanel.add(chraTableScrollPane);

		chraTable = new JTable();
		chraTable.setPreferredScrollableViewportSize(new Dimension(750, 420));
		chraTable.setModel(new DefaultTableModel(new Object[][] { { null, null,
				null, null }, }, new String[] { "Room Number", "Room Type",
				"Price", "Note" }));
		chraTable.getColumnModel().getColumn(0).setPreferredWidth(73);
		chraTableScrollPane.setViewportView(chraTable);

		roomBookingPanel = new JPanel();
		roomBookingPanel.setPreferredSize(new Dimension(780, 500));
		tabbedPane.addTab("Room Bookings", null, roomBookingPanel, null);
		roomBookingPanel.setLayout(new BorderLayout(0, 0));

		bookRoomPanel = new JPanel();
		roomBookingPanel.add(bookRoomPanel, BorderLayout.NORTH);
		GridBagLayout gbl_bookRoomPanel = new GridBagLayout();
		gbl_bookRoomPanel.columnWidths = new int[] { 0, 28, 46, 86, 46, 86, 46,
				86, 0, 85, 0 };
		gbl_bookRoomPanel.rowHeights = new int[] { 0, 23, 0 };
		gbl_bookRoomPanel.columnWeights = new double[] { 0.0, 0.0, 0.0, 0.0,
				0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		gbl_bookRoomPanel.rowWeights = new double[] { 0.0, 0.0,
				Double.MIN_VALUE };
		bookRoomPanel.setLayout(gbl_bookRoomPanel);

		brRoomTypeLabel = new JLabel("Room Type:");
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

		comboBox = new JComboBox<RoomType>();
		comboBox.setModel(new DefaultComboBoxModel<RoomType>(RoomType.values()));
		GridBagConstraints gbc_comboBox = new GridBagConstraints();
		gbc_comboBox.anchor = GridBagConstraints.WEST;
		gbc_comboBox.insets = new Insets(0, 0, 0, 5);
		gbc_comboBox.gridx = 1;
		gbc_comboBox.gridy = 1;
		bookRoomPanel.add(comboBox, gbc_comboBox);

		textField = new JTextField();
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.anchor = GridBagConstraints.WEST;
		gbc_textField.insets = new Insets(0, 0, 0, 5);
		gbc_textField.gridx = 3;
		gbc_textField.gridy = 1;
		bookRoomPanel.add(textField, gbc_textField);
		textField.setColumns(10);

		textField_1 = new JTextField();
		GridBagConstraints gbc_textField_1 = new GridBagConstraints();
		gbc_textField_1.anchor = GridBagConstraints.WEST;
		gbc_textField_1.insets = new Insets(0, 0, 0, 5);
		gbc_textField_1.gridx = 5;
		gbc_textField_1.gridy = 1;
		bookRoomPanel.add(textField_1, gbc_textField_1);
		textField_1.setColumns(10);

		textField_2 = new JTextField();
		GridBagConstraints gbc_textField_2 = new GridBagConstraints();
		gbc_textField_2.anchor = GridBagConstraints.WEST;
		gbc_textField_2.insets = new Insets(0, 0, 0, 5);
		gbc_textField_2.gridx = 7;
		gbc_textField_2.gridy = 1;
		bookRoomPanel.add(textField_2, gbc_textField_2);
		textField_2.setColumns(10);

		btnBookRoom = new JButton("Book Room");
		GridBagConstraints gbc_btnBookRoom = new GridBagConstraints();
		gbc_btnBookRoom.anchor = GridBagConstraints.NORTHWEST;
		gbc_btnBookRoom.gridx = 9;
		gbc_btnBookRoom.gridy = 1;
		bookRoomPanel.add(btnBookRoom, gbc_btnBookRoom);

		roomBookingsPanel = new JPanel();
		roomBookingPanel.add(roomBookingsPanel, BorderLayout.CENTER);

		rbTableScrollPane = new JScrollPane();
		roomBookingsPanel.add(rbTableScrollPane);

		rbTable = new JTable();
		rbTable.setPreferredScrollableViewportSize(new Dimension(750, 420));
		rbTable.setModel(new DefaultTableModel(new Object[][] { { null, null,
				null, null }, }, new String[] { "Booking ID", "Customer ID",
				"Date Start", "Date End" }));
		rbTable.getColumnModel().getColumn(0).setPreferredWidth(73);
		rbTableScrollPane.setViewportView(rbTable);

	}

}

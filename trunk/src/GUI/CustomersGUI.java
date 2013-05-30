package GUI;

import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.CardLayout;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.border.TitledBorder;
import javax.swing.UIManager;
import java.awt.FlowLayout;

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
	private JButton billGetActivitiesButton;
	private JButton billGetInstructorButton;
	private JPanel billRooms;
	private JPanel billActivities;
	private JPanel billInstructors;
	private JPanel upperBillRoomWrap;
	private JPanel lowerBillRoomWrap;
	private JPanel upperBillActivitiesWrap;
	private JPanel lowerBillActivitiesWrap;
	private JPanel upperBillInstructorsWrap;
	private JPanel lowerBillInstructorsWrap;
	private JPanel lowerBillRoomWrapLeft;
	private JLabel markPaidLabel;
	private JTextField markPaidField;
	private JButton markPaidButton;

	public CustomersGUI() {

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
				.setPreferredScrollableViewportSize(new Dimension(450, 400));

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
		upperBillWrapLeft.add(billGetBookingsButton);

		billGetActivitiesButton = new JButton("Get Reserved Activities");
		billGetActivitiesButton.setActionCommand("Get Reserved Activities");
		upperBillWrapLeft.add(billGetActivitiesButton);

		billGetInstructorButton = new JButton("Get Hired Instructors");
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
		billRooms.setBorder(new TitledBorder(null, "Customers Room Bookings",
				TitledBorder.LEADING, TitledBorder.TOP, null, null));
		lowerBillingPanelWrapper.add(billRooms, "name_1371790270790");
		billRooms.setLayout(new BorderLayout(0, 0));

		upperBillRoomWrap = new JPanel();
		billRooms.add(upperBillRoomWrap);

		lowerBillRoomWrap = new JPanel();
		billRooms.add(lowerBillRoomWrap, BorderLayout.SOUTH);
		lowerBillRoomWrap.setLayout(new BorderLayout(0, 0));

		lowerBillRoomWrapLeft = new JPanel();
		lowerBillRoomWrap.add(lowerBillRoomWrapLeft, BorderLayout.WEST);

		markPaidLabel = new JLabel("Booking ID: ");
		lowerBillRoomWrapLeft.add(markPaidLabel);

		markPaidField = new JTextField();
		lowerBillRoomWrapLeft.add(markPaidField);
		markPaidField.setColumns(10);

		markPaidButton = new JButton("Mark Paid");
		lowerBillRoomWrapLeft.add(markPaidButton);

		billActivities = new JPanel();
		billActivities.setBorder(new TitledBorder(null,
				"Customers Reserved Activities", TitledBorder.LEADING,
				TitledBorder.TOP, null, null));
		lowerBillingPanelWrapper.add(billActivities, "name_1374775856083");
		billActivities.setLayout(new BorderLayout(0, 0));

		upperBillActivitiesWrap = new JPanel();
		billActivities.add(upperBillActivitiesWrap, BorderLayout.CENTER);
		upperBillActivitiesWrap.setLayout(new FlowLayout(FlowLayout.CENTER, 5,
				5));

		lowerBillActivitiesWrap = new JPanel();
		billActivities.add(lowerBillActivitiesWrap, BorderLayout.SOUTH);

		billInstructors = new JPanel();
		billInstructors.setBorder(new TitledBorder(null,
				"Customers Hired Instructors", TitledBorder.LEADING,
				TitledBorder.TOP, null, null));
		lowerBillingPanelWrapper.add(billInstructors, "name_1376202770651");
		billInstructors.setLayout(new BorderLayout(0, 0));

		upperBillInstructorsWrap = new JPanel();
		billInstructors.add(upperBillInstructorsWrap, BorderLayout.CENTER);

		lowerBillInstructorsWrap = new JPanel();
		billInstructors.add(lowerBillInstructorsWrap, BorderLayout.SOUTH);
	}

}

package GUI;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import java.awt.BorderLayout;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import java.awt.CardLayout;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.border.TitledBorder;
import javax.swing.UIManager;
import Controllers.ActivitiesCtr;
import Controllers.CustomersCtr;
import Controllers.RoomsCtr;
import Models.Agency;
import Models.Customer;
import Models.InstructorHire;
import Models.Room;
import Models.RoomBooking;

import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Component;

public class CustomersGUI extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private CustomersCtr custCtr;
	private Customer customer;
	private JPanel customersWrapper;
	private JTabbedPane customersTabbedPane;
	private JPanel custCrudPanel;
	private JPanel upperCrudWrapper;
	private JPanel lowerCrudWrapper;
	private JPanel custBillingPanel;
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
	private JTextField IDSearchField;
	private JPanel allCust;
	private JPanel showCust;
	private JTextField showIDField;
	private JTextField showCPRField;
	private JTextField showFNameField;
	private JTextField showLNameField;
	private JTextField showAddressField;
	private JTextField showZIPField;
	private JTextField showCountryField;
	private JTextField showEmailField;
	private JTextField showPassField;
	private JTextField showRegDateField;
	private JTextField showStaysField;
	private JPanel editCust;
	private JTextField editIDField;
	private JTextField editCPRField;
	private JTextField editFNameField;
	private JTextField editLNameField;
	private JTextField editAddressField;
	private JTextField editZIPField;
	private JTextField editCountryField;
	private JTextField editEmailField;
	private JTextField editPassField;
	private JTextField editRegDateField;
	private JPanel newCust;
	private JTextField createCPRField;
	private JTextField createFNameField;
	private JTextField createLNameField;
	private JTextField createAddressField;
	private JTextField createZIPField;
	private JTextField createCountryField;
	private JTextField createEmailField;
	private JTextField createPassField;
	private JTextField createRegDateField;
	private JTextField createStaysField;
	private JTextField editStaysField;
	private JLabel totalInstructor;
	private ActivitiesCtr actCtr;
	private JTextField textFieldSearchAgen;
	private JTable table;
	private JTextField textFieldAgenNameNew;
	private JTextField textFieldAgenDiscountNew;
	private JTextField textFieldAgenIdEdit;
	private JTextField textFieldAgenNameEdit;
	private JTextField textFieldAgenDiscountEdit;
	private JTextField textFieldAgenIdShow;
	private JTextField textFieldAgenNameShow;
	private JTextField textFieldAgenDiscountShow;
	private JPanel lowerAgenWrapper;
	private DefaultTableModel dtmAgen;

	public CustomersGUI() {
		custCtr = new CustomersCtr();
		initialize();
	}

	public void initialize() {
		
		actCtr = new ActivitiesCtr();
		setPreferredSize(new Dimension(780, 535));
		
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

		//from here
		upperCrudWrapper = new JPanel();
		upperCrudWrapper.setBorder(new TitledBorder(UIManager
				.getBorder("TitledBorder.border"), "Customers",
				TitledBorder.LEADING, TitledBorder.TOP, null, null));
		custCrudPanel.add(upperCrudWrapper, BorderLayout.NORTH);
		upperCrudWrapper.setLayout(new BorderLayout(0, 0));

		
		allCustTableModel = new DefaultTableModel() {
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		allCustTableModel.addColumn("ID");
		allCustTableModel.addColumn("CPR");
		allCustTableModel.addColumn("First name");
		allCustTableModel.addColumn("Last name");
		allCustTableModel.addColumn("Address");
		allCustTableModel.addColumn("ZIP");
		allCustTableModel.addColumn("Country");
		allCustTableModel.addColumn("Email");
		allCustTableModel.addColumn("Password");
		allCustTableModel.addColumn("Registration");
		allCustTableModel.addColumn("Stay");
		

		JPanel upperCrudWrapperLeft = new JPanel();
		upperCrudWrapper.add(upperCrudWrapperLeft, BorderLayout.WEST);

		JButton newCustButton = new JButton("New customer");
		newCustButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CardLayout card = (CardLayout) lowerCrudWrapper.getLayout();
				card.show(lowerCrudWrapper, "New customer");
			}
		});
		upperCrudWrapperLeft.add(newCustButton);

		JPanel upperCrudWrapperRight = new JPanel();
		upperCrudWrapper.add(upperCrudWrapperRight, BorderLayout.EAST);

		JLabel IDSearchLabel = new JLabel("Customer ID:");
		upperCrudWrapperRight.add(IDSearchLabel);

		IDSearchField = new JTextField();
		upperCrudWrapperRight.add(IDSearchField);
		IDSearchField.setColumns(5);

		JButton searchButton = new JButton("Search");
		searchButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				findCust(Integer.parseInt(IDSearchField.getText()));
				IDSearchField.setText("");
			}
		});
		upperCrudWrapperRight.add(searchButton);

		lowerCrudWrapper = new JPanel();
		custCrudPanel.add(lowerCrudWrapper);
		lowerCrudWrapper.setLayout(new CardLayout(0, 0));
		
		allCust = new JPanel();
		lowerCrudWrapper.add(allCust, "All customers");
		allCust.setLayout(new BorderLayout());
				
		JPanel allCustPanel = new JPanel();
		allCustPanel.setBorder(BorderFactory.createTitledBorder("All customers"));
		allCust.add(allCustPanel, BorderLayout.CENTER);
						
		allCustTable = new JTable(allCustTableModel);
		allCustTable.setAutoResizeMode(JTable.AUTO_RESIZE_NEXT_COLUMN);
		allCustTable.setAutoCreateRowSorter(true);
								
		allCustTable.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				int row = allCustTable.rowAtPoint(e.getPoint());
				if (e.getClickCount() == 2) {
				findCust(Integer.valueOf((Integer) allCustTableModel.getValueAt(row, 0)));
				}
			}
		});
										
		allCustPanel.setLayout(new BoxLayout(allCustPanel, BoxLayout.X_AXIS));
										
		JScrollPane scrollPane = new JScrollPane();
		allCustPanel.add(scrollPane);
												
		scrollPane.setViewportView(allCustTable);

		showCust = new JPanel();
		lowerCrudWrapper.add(showCust, "Show customer");
		showCust.setLayout(new BorderLayout());

		JPanel custContainer = new JPanel();
		showCust.add(custContainer, BorderLayout.NORTH);
		custContainer.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		custContainer.setBorder(BorderFactory
				.createTitledBorder("Customer details"));

		JPanel custDetails = new JPanel();
		custDetails.setLayout(new BoxLayout(custDetails, BoxLayout.X_AXIS));
		custContainer.add(custDetails);

		JPanel showCustLeft = new JPanel();
		custDetails.add(showCustLeft);
		showCustLeft.setLayout(new GridLayout(0, 1, 0, 0));

		JPanel IDLabelPanel = new JPanel();
		IDLabelPanel.setLayout(new BoxLayout(IDLabelPanel, BoxLayout.X_AXIS));
		showCustLeft.add(IDLabelPanel);

		JLabel IDLabel = new JLabel("ID");
		IDLabelPanel.add(IDLabel);
		IDLabel.setHorizontalAlignment(SwingConstants.CENTER);

		JPanel CPRLabelPanel = new JPanel();
		CPRLabelPanel.setLayout(new BoxLayout(CPRLabelPanel, BoxLayout.X_AXIS));
		showCustLeft.add(CPRLabelPanel);

		JLabel CPRLabel = new JLabel("CPR");
		CPRLabelPanel.add(CPRLabel);
		CPRLabel.setHorizontalAlignment(SwingConstants.CENTER);

		JPanel fNameLabelPanel = new JPanel();
		fNameLabelPanel.setLayout(new BoxLayout(fNameLabelPanel, BoxLayout.X_AXIS));
		showCustLeft.add(fNameLabelPanel);

		JLabel fNameLabel = new JLabel("First name");
		fNameLabelPanel.add(fNameLabel);
		fNameLabel.setHorizontalAlignment(SwingConstants.CENTER);

		JPanel lNameLabelPanel = new JPanel();
		lNameLabelPanel.setLayout(new BoxLayout(lNameLabelPanel, BoxLayout.X_AXIS));
		showCustLeft.add(lNameLabelPanel);

		JLabel lNameLabel = new JLabel("Last name");
		lNameLabelPanel.add(lNameLabel);
		lNameLabel.setHorizontalAlignment(SwingConstants.CENTER);

		JPanel addressLabelPanel = new JPanel();
		showCustLeft.add(addressLabelPanel);
		addressLabelPanel.setLayout(new BoxLayout(addressLabelPanel, BoxLayout.X_AXIS));

		JLabel addressLabel = new JLabel("Address");
		addressLabel.setHorizontalAlignment(SwingConstants.CENTER);
		addressLabelPanel.add(addressLabel);

		JPanel ZIPLabelPanel = new JPanel();
		showCustLeft.add(ZIPLabelPanel);
		ZIPLabelPanel.setLayout(new BoxLayout(ZIPLabelPanel, BoxLayout.X_AXIS));

		JLabel ZIPLabel = new JLabel("ZIP");
		ZIPLabel.setHorizontalAlignment(SwingConstants.CENTER);
		ZIPLabelPanel.add(ZIPLabel);
		
		JPanel countryLabelPanel = new JPanel();
		countryLabelPanel.setLayout(new BoxLayout(countryLabelPanel, BoxLayout.X_AXIS));
		showCustLeft.add(countryLabelPanel);

		JLabel countryLabel = new JLabel("Country");
		countryLabelPanel.add(countryLabel);
		countryLabel.setHorizontalAlignment(SwingConstants.CENTER);
		
		JPanel emailLabelPanel = new JPanel();
		emailLabelPanel.setLayout(new BoxLayout(emailLabelPanel, BoxLayout.X_AXIS));
		showCustLeft.add(emailLabelPanel);

		JLabel emailLabel = new JLabel("Email");
		emailLabelPanel.add(emailLabel);
		emailLabel.setHorizontalAlignment(SwingConstants.CENTER);
		
		JPanel passLabelPanel = new JPanel();
		passLabelPanel.setLayout(new BoxLayout(passLabelPanel, BoxLayout.X_AXIS));
		showCustLeft.add(passLabelPanel);

		JLabel passLabel = new JLabel("Password");
		passLabelPanel.add(passLabel);
		passLabel.setHorizontalAlignment(SwingConstants.CENTER);
		
		JPanel regDateLabelPanel = new JPanel();
		regDateLabelPanel.setLayout(new BoxLayout(regDateLabelPanel, BoxLayout.X_AXIS));
		showCustLeft.add(regDateLabelPanel);

		JLabel regDateLabel = new JLabel("Registration");
		regDateLabelPanel.add(regDateLabel);
		regDateLabel.setHorizontalAlignment(SwingConstants.CENTER);
		
		JPanel staysLabelPanel = new JPanel();
		staysLabelPanel.setLayout(new BoxLayout(staysLabelPanel, BoxLayout.X_AXIS));
		showCustLeft.add(staysLabelPanel);

		JLabel staysLabel = new JLabel("No. of stays");
		staysLabelPanel.add(staysLabel);
		staysLabel.setHorizontalAlignment(SwingConstants.CENTER);

		JPanel showCustRight = new JPanel();
		showCustRight.setLayout(new BoxLayout(showCustRight, BoxLayout.Y_AXIS));
		custDetails.add(showCustRight);

		JPanel showIDFieldPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		showCustRight.add(showIDFieldPanel);

		showIDField = new JTextField();
		showIDFieldPanel.add(showIDField);
		showIDField.setHorizontalAlignment(SwingConstants.CENTER);
		showIDField.setEditable(false);
		showIDField.setColumns(20);

		JPanel showCPRFieldPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		showCustRight.add(showCPRFieldPanel);

		showCPRField = new JTextField();
		showCPRFieldPanel.add(showCPRField);
		showCPRField.setHorizontalAlignment(SwingConstants.CENTER);
		showCPRField.setEditable(false);
		showCPRField.setColumns(20);

		JPanel showFNameFieldPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		showCustRight.add(showFNameFieldPanel);

		showFNameField = new JTextField();
		showFNameFieldPanel.add(showFNameField);
		showFNameField.setHorizontalAlignment(SwingConstants.CENTER);
		showFNameField.setEditable(false);
		showFNameField.setColumns(20);

		JPanel showLNameFieldPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		showCustRight.add(showLNameFieldPanel);

		showLNameField = new JTextField();
		showLNameFieldPanel.add(showLNameField);
		showLNameField.setHorizontalAlignment(SwingConstants.CENTER);
		showLNameField.setEditable(false);
		showLNameField.setColumns(20);

		JPanel showAddressFieldPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		showCustRight.add(showAddressFieldPanel);

		showAddressField = new JTextField();
		showAddressFieldPanel.add(showAddressField);
		showAddressField.setHorizontalAlignment(SwingConstants.CENTER);
		showAddressField.setEditable(false);
		showAddressField.setColumns(20);

		JPanel showZIPFieldPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		showCustRight.add(showZIPFieldPanel);

		showZIPField = new JTextField();
		showZIPFieldPanel.add(showZIPField);
		showZIPField.setHorizontalAlignment(SwingConstants.CENTER);
		showZIPField.setEditable(false);
		showZIPField.setColumns(20);

		JPanel showCountryFieldPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		showCustRight.add(showCountryFieldPanel);

		showCountryField = new JTextField();
		showCountryFieldPanel.add(showCountryField);
		showCountryField.setHorizontalAlignment(SwingConstants.CENTER);
		showCountryField.setEditable(false);
		showCountryField.setColumns(20);

		JPanel showEmailFieldPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		showCustRight.add(showEmailFieldPanel);

		showEmailField = new JTextField();
		showEmailFieldPanel.add(showEmailField);
		showEmailField.setHorizontalAlignment(SwingConstants.CENTER);
		showEmailField.setEditable(false);
		showEmailField.setColumns(20);

		JPanel showPassFieldPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		showCustRight.add(showPassFieldPanel);

		showPassField = new JTextField();
		showPassFieldPanel.add(showPassField);
		showPassField.setHorizontalAlignment(SwingConstants.CENTER);
		showPassField.setEditable(false);
		showPassField.setColumns(20);

		JPanel showRegDateFieldPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		showCustRight.add(showRegDateFieldPanel);

		showRegDateField = new JTextField();
		showRegDateFieldPanel.add(showRegDateField);
		showRegDateField.setHorizontalAlignment(SwingConstants.CENTER);
		showRegDateField.setEditable(false);
		showRegDateField.setColumns(20);
		
		JPanel showStaysFieldPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		showCustRight.add(showStaysFieldPanel);

		showStaysField = new JTextField();
		showStaysFieldPanel.add(showStaysField);
		showStaysField.setHorizontalAlignment(SwingConstants.CENTER);
		showStaysField.setEditable(false);
		showStaysField.setColumns(20);
		

		JPanel bottomButtons = new JPanel();
		showCust.add(bottomButtons, BorderLayout.CENTER);
		bottomButtons.setLayout(new BorderLayout());

		JPanel bottomButtonsRight = new JPanel();
		bottomButtons.add(bottomButtonsRight, BorderLayout.CENTER);

		JButton editButton = new JButton("Edit");
		editButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				editCust((int) Integer.parseInt(showIDField.getText()));
			}
		});
		bottomButtonsRight.add(editButton);

		JButton deleteButton = new JButton("Delete");
		deleteButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int reply = JOptionPane.showConfirmDialog(null,
						"Are you sure you want to delete this customer?",
						"Delete customer", JOptionPane.YES_NO_OPTION,
						JOptionPane.WARNING_MESSAGE);
				if (reply == JOptionPane.YES_OPTION) {
					boolean toDelete = true;
					try {
						custCtr.deleteCustomer(Integer.parseInt(showIDField
								.getText()));
					} 
					catch (Exception ex) {
						toDelete = false;
						JOptionPane.showMessageDialog(null,
								"Field value invalid", "Error", JOptionPane.ERROR_MESSAGE);
					}
					if (toDelete) {
						JOptionPane.showMessageDialog(null,
								"The customer was deleted", "Delete customer", JOptionPane.INFORMATION_MESSAGE);
						allCust();
					}
				}
			}
		});
		
		bottomButtonsRight.add(deleteButton);

		JPanel bottomButtonsLeft = new JPanel();
		bottomButtons.add(bottomButtonsLeft, BorderLayout.WEST);

		JButton backButton = new JButton("Back");
		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				allCust();
			}
		});
		bottomButtonsLeft.add(backButton);

		editCust = new JPanel();
		lowerCrudWrapper.add(editCust, "Edit customer");
		editCust.setLayout(new BorderLayout());

		JPanel editCustContainer = new JPanel();
		editCust.add(editCustContainer, BorderLayout.NORTH);
		editCustContainer.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		editCustContainer.setBorder(BorderFactory.createTitledBorder("Edit customer"));

		JPanel editCustDetails = new JPanel();
		editCustDetails.setLayout(new BoxLayout(editCustDetails, BoxLayout.X_AXIS));
		editCustContainer.add(editCustDetails);

		JPanel editCustLeft = new JPanel();
		editCustDetails.add(editCustLeft);
		editCustLeft.setLayout(new GridLayout(0, 1, 0, 0));

		JPanel editIDLabelPanel = new JPanel();
		editIDLabelPanel.setLayout(new BoxLayout(editIDLabelPanel, BoxLayout.X_AXIS));
		editCustLeft.add(editIDLabelPanel);

		JLabel editIDLabel = new JLabel("ID");
		editIDLabelPanel.add(editIDLabel);
		editIDLabel.setHorizontalAlignment(SwingConstants.CENTER);
		
		JPanel editCPRLabelPanel = new JPanel();
		editCPRLabelPanel.setLayout(new BoxLayout(editCPRLabelPanel, BoxLayout.X_AXIS));
		editCustLeft.add(editCPRLabelPanel);

		JLabel editCPRLabel = new JLabel("CPR");
		editCPRLabelPanel.add(editCPRLabel);
		editCPRLabel.setHorizontalAlignment(SwingConstants.CENTER);
		
		JPanel editFNameLabelPanel = new JPanel();
		editFNameLabelPanel.setLayout(new BoxLayout(editFNameLabelPanel, BoxLayout.X_AXIS));
		editCustLeft.add(editFNameLabelPanel);

		JLabel editFNameLabel = new JLabel("First name");
		editFNameLabelPanel.add(editFNameLabel);
		editFNameLabel.setHorizontalAlignment(SwingConstants.CENTER);
		
		JPanel editLNameLabelPanel = new JPanel();
		editLNameLabelPanel.setLayout(new BoxLayout(editLNameLabelPanel, BoxLayout.X_AXIS));
		editCustLeft.add(editLNameLabelPanel);

		JLabel editLNameLabel = new JLabel("Last name");
		editLNameLabelPanel.add(editLNameLabel);
		editLNameLabel.setHorizontalAlignment(SwingConstants.CENTER);
		
		JPanel editAddressLabelPanel = new JPanel();
		editAddressLabelPanel.setLayout(new BoxLayout(editAddressLabelPanel, BoxLayout.X_AXIS));
		editCustLeft.add(editAddressLabelPanel);

		JLabel editAddressLabel = new JLabel("Address");
		editAddressLabelPanel.add(editAddressLabel);
		editAddressLabel.setHorizontalAlignment(SwingConstants.CENTER);
		
		JPanel editZIPLabelPanel = new JPanel();
		editZIPLabelPanel.setLayout(new BoxLayout(editZIPLabelPanel, BoxLayout.X_AXIS));
		editCustLeft.add(editZIPLabelPanel);

		JLabel editZIPLabel = new JLabel("ZIP");
		editZIPLabelPanel.add(editZIPLabel);
		editZIPLabel.setHorizontalAlignment(SwingConstants.CENTER);

		JPanel editCountryLabelPanel = new JPanel();
		editCountryLabelPanel.setLayout(new BoxLayout(editCountryLabelPanel, BoxLayout.X_AXIS));
		editCustLeft.add(editCountryLabelPanel);

		JLabel editCountryLabel = new JLabel("Country");
		editCountryLabelPanel.add(editCountryLabel);
		editCountryLabel.setHorizontalAlignment(SwingConstants.CENTER);
		
		JPanel editEmailLabelPanel = new JPanel();
		editEmailLabelPanel.setLayout(new BoxLayout(editEmailLabelPanel, BoxLayout.X_AXIS));
		editCustLeft.add(editEmailLabelPanel);

		JLabel editEmailLabel = new JLabel("Email");
		editEmailLabelPanel.add(editEmailLabel);
		editEmailLabel.setHorizontalAlignment(SwingConstants.CENTER);
		
		JPanel editPassLabelPanel = new JPanel();
		editPassLabelPanel.setLayout(new BoxLayout(editPassLabelPanel, BoxLayout.X_AXIS));
		editCustLeft.add(editPassLabelPanel);

		JLabel editPassLabel = new JLabel("Password");
		editPassLabelPanel.add(editPassLabel);
		editPassLabel.setHorizontalAlignment(SwingConstants.CENTER);
		
		JPanel editRegDateLabelPanel = new JPanel();
		editRegDateLabelPanel.setLayout(new BoxLayout(editRegDateLabelPanel, BoxLayout.X_AXIS));
		editCustLeft.add(editRegDateLabelPanel);

		JLabel editRegDateLabel = new JLabel("Registration date");
		editRegDateLabelPanel.add(editRegDateLabel);
		editRegDateLabel.setHorizontalAlignment(SwingConstants.CENTER);
		
		JPanel editStaysLabelPanel = new JPanel();
		editStaysLabelPanel.setLayout(new BoxLayout(editStaysLabelPanel, BoxLayout.X_AXIS));
		editCustLeft.add(editStaysLabelPanel);

		JLabel editStaysLabel = new JLabel("No. of stays");
		editStaysLabelPanel.add(editStaysLabel);
		editStaysLabel.setHorizontalAlignment(SwingConstants.CENTER);

		JPanel editCustRight = new JPanel();
		editCustRight.setLayout(new BoxLayout(editCustRight, BoxLayout.Y_AXIS));
		editCustDetails.add(editCustRight);

		JPanel editIDFieldPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		editCustRight.add(editIDFieldPanel);

		editIDField = new JTextField();
		editIDFieldPanel.add(editIDField);
		editIDField.setHorizontalAlignment(SwingConstants.CENTER);
		editIDField.setColumns(20);

		JPanel editCPRFieldPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		editCustRight.add(editCPRFieldPanel);

		editCPRField = new JTextField();
		editCPRFieldPanel.add(editCPRField);
		editCPRField.setHorizontalAlignment(SwingConstants.CENTER);
		editCPRField.setColumns(20);
		
		JPanel editFNameFieldPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		editCustRight.add(editFNameFieldPanel);

		editFNameField = new JTextField();
		editFNameFieldPanel.add(editFNameField);
		editFNameField.setHorizontalAlignment(SwingConstants.CENTER);
		editFNameField.setColumns(20);

		JPanel editLNameFieldPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		editCustRight.add(editLNameFieldPanel);

		editLNameField = new JTextField();
		editLNameFieldPanel.add(editLNameField);
		editLNameField.setHorizontalAlignment(SwingConstants.CENTER);
		editLNameField.setColumns(20);
		
		JPanel editAddressFieldPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		editCustRight.add(editAddressFieldPanel);

		editAddressField = new JTextField();
		editAddressFieldPanel.add(editAddressField);
		editAddressField.setHorizontalAlignment(SwingConstants.CENTER);
		editAddressField.setColumns(20);
		
		JPanel editZIPFieldPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		editCustRight.add(editZIPFieldPanel);

		editZIPField = new JTextField();
		editZIPFieldPanel.add(editZIPField);
		editZIPField.setHorizontalAlignment(SwingConstants.CENTER);
		editZIPField.setColumns(20);
		
		JPanel editCountryFieldPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		editCustRight.add(editCountryFieldPanel);

		editCountryField = new JTextField();
		editCountryFieldPanel.add(editCountryField);
		editCountryField.setHorizontalAlignment(SwingConstants.CENTER);
		editCountryField.setColumns(20);

		JPanel editEmailFieldPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		editCustRight.add(editEmailFieldPanel);

		editEmailField = new JTextField();
		editEmailFieldPanel.add(editEmailField);
		editEmailField.setHorizontalAlignment(SwingConstants.CENTER);
		editEmailField.setColumns(20);
		
		JPanel editPassFieldPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		editCustRight.add(editPassFieldPanel);

		editPassField = new JTextField();
		editPassFieldPanel.add(editPassField);
		editPassField.setHorizontalAlignment(SwingConstants.CENTER);
		editPassField.setColumns(20);
		
		JPanel editRegDateFieldPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		editCustRight.add(editRegDateFieldPanel);

		editRegDateField = new JTextField();
		editRegDateFieldPanel.add(editRegDateField);
		editRegDateField.setHorizontalAlignment(SwingConstants.CENTER);
		editRegDateField.setColumns(20);

		JPanel editStaysFieldPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		editCustRight.add(editStaysFieldPanel);

		editStaysField = new JTextField();
		editStaysFieldPanel.add(editStaysField);
		editStaysField.setHorizontalAlignment(SwingConstants.CENTER);
		editStaysField.setColumns(20);

		JPanel editButtonsPanel = new JPanel();
		editCust.add(editButtonsPanel, BorderLayout.CENTER);
		editButtonsPanel.setLayout(new BorderLayout());

		JPanel editCenterButtonsPanel = new JPanel();
		editButtonsPanel.add(editCenterButtonsPanel, BorderLayout.CENTER);

		JButton editButton2 = new JButton("Edit");
		editButton2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int reply = JOptionPane.showConfirmDialog(null,
						"Are you sure you want to edit this customer?",
						"Edit customer", JOptionPane.YES_NO_OPTION,
						JOptionPane.WARNING_MESSAGE);
				if (reply == JOptionPane.YES_OPTION) {
					boolean toEdit = true;
					try {
						custCtr.updateCustomer(Integer.parseInt(editIDField.getText()), 
								editCPRField.getText(), editFNameField.getText(),
								editLNameField.getText(), editCountryField.getText(), 
								editZIPField.getText(), editAddressField.getText(), 
								editEmailField.getText(), editPassField.getText(), 
								editRegDateField.getText(), Integer.parseInt(editStaysField.getText()));
					}
					catch (NumberFormatException ex) {
						toEdit = false;
						JOptionPane.showMessageDialog(null,
								"Field value invalid", "Error",
								JOptionPane.ERROR_MESSAGE);
					}
					if (toEdit) {
						JOptionPane.showMessageDialog(null,
								"The customer was edited", "Edit customer",
								JOptionPane.INFORMATION_MESSAGE);
						findCust(Integer.parseInt(showIDField.getText()));
					}
				}
			}
		});
		editCenterButtonsPanel.add(editButton2);

		JButton cancelButtonEdit = new JButton("Cancel");
		cancelButtonEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				findCust((int) Integer.parseInt(editIDField.getText()));
			}
		});
		editCenterButtonsPanel.add(cancelButtonEdit);

		newCust = new JPanel();
		lowerCrudWrapper.add(newCust, "New customer");
		newCust.setLayout(new BorderLayout());

		JPanel newCustContainer = new JPanel();
		newCust.add(newCustContainer, BorderLayout.NORTH);
		newCustContainer.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		newCustContainer.setBorder(BorderFactory.createTitledBorder("New customer"));

		JPanel newCustDetails = new JPanel();
		newCustDetails.setLayout(new BoxLayout(newCustDetails, BoxLayout.X_AXIS));
		newCustContainer.add(newCustDetails);
//TODO
		JPanel newCustLeft = new JPanel();
		newCustDetails.add(newCustLeft);
		newCustLeft.setLayout(new GridLayout(0, 1, 0, 0));

		JPanel createCPRLabelPanel = new JPanel();
		newCustLeft.add(createCPRLabelPanel);
		createCPRLabelPanel.setLayout(new BoxLayout(createCPRLabelPanel, BoxLayout.X_AXIS));
		
		JLabel createCPRLabel = new JLabel("CPR");
		createCPRLabelPanel.add(createCPRLabel);
		createCPRLabel.setHorizontalAlignment(SwingConstants.CENTER);

		JPanel createFNameLabelPanel = new JPanel();
		newCustLeft.add(createFNameLabelPanel);
		createFNameLabelPanel.setLayout(new BoxLayout(createFNameLabelPanel, BoxLayout.X_AXIS));
		
		JLabel createFNameLabel = new JLabel("First name");
		createFNameLabelPanel.add(createFNameLabel);
		createFNameLabel.setHorizontalAlignment(SwingConstants.CENTER);
		
		JPanel createLNameLabelPanel = new JPanel();
		newCustLeft.add(createLNameLabelPanel);
		createLNameLabelPanel.setLayout(new BoxLayout(createLNameLabelPanel, BoxLayout.X_AXIS));
		
		JLabel createLNameLabel = new JLabel("Last name");
		createLNameLabelPanel.add(createLNameLabel);
		createLNameLabel.setHorizontalAlignment(SwingConstants.CENTER);

		JPanel createAddressLabelPanel = new JPanel();
		newCustLeft.add(createAddressLabelPanel);
		createAddressLabelPanel.setLayout(new BoxLayout(createAddressLabelPanel, BoxLayout.X_AXIS));
		
		JLabel createAddressLabel = new JLabel("Address");
		createAddressLabelPanel.add(createAddressLabel);
		createAddressLabel.setHorizontalAlignment(SwingConstants.CENTER);
		
		JPanel createZIPLabelPanel = new JPanel();
		newCustLeft.add(createZIPLabelPanel);
		createZIPLabelPanel.setLayout(new BoxLayout(createZIPLabelPanel, BoxLayout.X_AXIS));
		
		JLabel createZIPLabel = new JLabel("ZIP");
		createZIPLabelPanel.add(createZIPLabel);
		createZIPLabel.setHorizontalAlignment(SwingConstants.CENTER);

		JPanel createCountryLabelPanel = new JPanel();
		newCustLeft.add(createCountryLabelPanel);
		createCountryLabelPanel.setLayout(new BoxLayout(createCountryLabelPanel, BoxLayout.X_AXIS));
		
		JLabel createCountryLabel = new JLabel("Country");
		createCountryLabelPanel.add(createCountryLabel);
		createCountryLabel.setHorizontalAlignment(SwingConstants.CENTER);
		
		JPanel createEmailLabelPanel = new JPanel();
		newCustLeft.add(createEmailLabelPanel);
		createEmailLabelPanel.setLayout(new BoxLayout(createEmailLabelPanel, BoxLayout.X_AXIS));
		
		JLabel createEmailLabel = new JLabel("Email");
		createEmailLabelPanel.add(createEmailLabel);
		createEmailLabel.setHorizontalAlignment(SwingConstants.CENTER);

		JPanel createPassLabelPanel = new JPanel();
		newCustLeft.add(createPassLabelPanel);
		createPassLabelPanel.setLayout(new BoxLayout(createPassLabelPanel, BoxLayout.X_AXIS));
		
		JLabel createPassLabel = new JLabel("Password");
		createPassLabelPanel.add(createPassLabel);
		createPassLabel.setHorizontalAlignment(SwingConstants.CENTER);
		
		JPanel createRegDateLabelPanel = new JPanel();
		newCustLeft.add(createRegDateLabelPanel);
		createRegDateLabelPanel.setLayout(new BoxLayout(createRegDateLabelPanel, BoxLayout.X_AXIS));
		
		JLabel createRegDateLabel = new JLabel("Registration date");
		createRegDateLabelPanel.add(createRegDateLabel);
		createRegDateLabel.setHorizontalAlignment(SwingConstants.CENTER);
		
		JPanel createStaysLabelPanel = new JPanel();
		newCustLeft.add(createStaysLabelPanel);
		createStaysLabelPanel.setLayout(new BoxLayout(createStaysLabelPanel, BoxLayout.X_AXIS));
		
		JLabel createStaysLabel = new JLabel("No. of stays");
		createStaysLabelPanel.add(createStaysLabel);
		createStaysLabel.setHorizontalAlignment(SwingConstants.CENTER);

		JPanel newStaffRight = new JPanel();
		newStaffRight.setLayout(new BoxLayout(newStaffRight, BoxLayout.Y_AXIS));
		newCustDetails.add(newStaffRight);

		JPanel createCPRFieldPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		newStaffRight.add(createCPRFieldPanel);

		createCPRField = new JTextField();
		createCPRFieldPanel.add(createCPRField);
		createCPRField.setHorizontalAlignment(SwingConstants.CENTER);
		createCPRField.setColumns(20);
		
		JPanel createFNameFieldPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		newStaffRight.add(createFNameFieldPanel);

		createFNameField = new JTextField();
		createFNameFieldPanel.add(createFNameField);
		createFNameField.setHorizontalAlignment(SwingConstants.CENTER);
		createFNameField.setColumns(20);
		
		JPanel createLNameFieldPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		newStaffRight.add(createLNameFieldPanel);

		createLNameField = new JTextField();
		createLNameFieldPanel.add(createLNameField);
		createLNameField.setHorizontalAlignment(SwingConstants.CENTER);
		createLNameField.setColumns(20);
		
		JPanel createAddressFieldPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		newStaffRight.add(createAddressFieldPanel);

		createAddressField = new JTextField();
		createAddressFieldPanel.add(createAddressField);
		createAddressField.setHorizontalAlignment(SwingConstants.CENTER);
		createAddressField.setColumns(20);
		
		JPanel createZIPFieldPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		newStaffRight.add(createZIPFieldPanel);

		createZIPField = new JTextField();
		createZIPFieldPanel.add(createZIPField);
		createZIPField.setHorizontalAlignment(SwingConstants.CENTER);
		createZIPField.setColumns(20);
		
		JPanel createCountryFieldPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		newStaffRight.add(createCountryFieldPanel);

		createCountryField = new JTextField();
		createCountryFieldPanel.add(createCountryField);
		createCountryField.setHorizontalAlignment(SwingConstants.CENTER);
		createCountryField.setColumns(20);
		
		JPanel createEmailFieldPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		newStaffRight.add(createEmailFieldPanel);

		createEmailField = new JTextField();
		createEmailFieldPanel.add(createEmailField);
		createEmailField.setHorizontalAlignment(SwingConstants.CENTER);
		createEmailField.setColumns(20);
		
		JPanel createPassFieldPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		newStaffRight.add(createPassFieldPanel);

		createPassField = new JTextField();
		createPassFieldPanel.add(createPassField);
		createPassField.setHorizontalAlignment(SwingConstants.CENTER);
		createPassField.setColumns(20);
		
		JPanel createRegDateFieldPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		newStaffRight.add(createRegDateFieldPanel);

		createRegDateField = new JTextField();
		createRegDateFieldPanel.add(createRegDateField);
		createRegDateField.setHorizontalAlignment(SwingConstants.CENTER);
		createRegDateField.setColumns(20);
		
		JPanel createStaysFieldPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		newStaffRight.add(createStaysFieldPanel);

		createStaysField = new JTextField();
		createStaysFieldPanel.add(createStaysField);
		createStaysField.setHorizontalAlignment(SwingConstants.CENTER);
		createStaysField.setColumns(20);

		JPanel createBottomMenu = new JPanel();
		newCust.add(createBottomMenu, BorderLayout.CENTER);
		createBottomMenu.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		JButton createButton = new JButton("Create");
		createBottomMenu.add(createButton);

		JButton cancelButtonCreate = new JButton("Cancel");
		cancelButtonCreate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				allCust();
			}
		});
		createBottomMenu.add(cancelButtonCreate);
		createButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean toCreate = true;
				try {
					custCtr.newCustomer(createCPRField.getText(), createFNameField.getText(), 
							createLNameField.getText(), createCountryField.getText(), 
							createZIPField.getText(), createAddressField.getText(), 
							createEmailField.getText(), createPassField.getText(), 
							createRegDateField.getText(), Integer.parseInt(createStaysField.getText()));
				} 
				catch (NumberFormatException e1) {
					toCreate = false;
					JOptionPane.showMessageDialog(null, "Field value invalid",
							"Error", JOptionPane.ERROR_MESSAGE);
				}
				if (toCreate) {
					JOptionPane.showMessageDialog(null,
							"The customer was created", "Create customer",
							JOptionPane.INFORMATION_MESSAGE);
					allCust();
				}
			}
		});
		allCust();
		
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
						"First Name", "Last Name", "Address", 
						"Total Price", "Status" });
		custBillinstHireTable.setModel(custBillinstHireTableModel);

		custBillinstHireTable.getColumnModel().getColumn(0)
				.setPreferredWidth(73);
		custBillinstHireScrollPane.setViewportView(custBillinstHireTable);

		lowerBillInstructorsWrap = new JPanel();
		lowerBillInstructorsWrap.setLayout(new BorderLayout(0,0));
		billInstructors.add(lowerBillInstructorsWrap, BorderLayout.SOUTH);
		

		JButton payInstructor = new JButton("Mark paid");
		payInstructor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				markPaidInstHire();
			}
		});
		lowerBillInstructorsWrap.add(payInstructor, BorderLayout.EAST);
		
		totalInstructor = new JLabel("Total: ");
		lowerBillInstructorsWrap.add(totalInstructor, BorderLayout.WEST);
		
		JPanel agenPanel = new JPanel();
		customersTabbedPane.addTab("Agencies", null, agenPanel, null);
		agenPanel.setLayout(new BorderLayout(0, 0));
		
		JPanel upperAgenWrapper = new JPanel();
		agenPanel.add(upperAgenWrapper, BorderLayout.NORTH);
		upperAgenWrapper.setLayout(new BorderLayout(0, 0));
		
		JPanel upperAgenWrapLeft = new JPanel();
		upperAgenWrapper.add(upperAgenWrapLeft, BorderLayout.WEST);
		
		JButton btnNewAgency = new JButton("New Agency");
		btnNewAgency.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				CardLayout card = (CardLayout) (lowerAgenWrapper.getLayout());
				card.show(lowerAgenWrapper, "New Agency");
			}
		});
		upperAgenWrapLeft.add(btnNewAgency);
		
		JButton btnSelect = new JButton("Select");
		btnSelect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row = 0;
				Agency agency = null;
				try{
					row = table.getSelectedRow();
					agency = custCtr.getAgency((int) table.getModel().getValueAt(row, 0));
					showAgency(agency);
				}
				catch(ArrayIndexOutOfBoundsException ae){
					JOptionPane.showMessageDialog(customersWrapper, "Select a row in the table!", "Request", JOptionPane.WARNING_MESSAGE);
				}
				
			}
		});
		upperAgenWrapLeft.add(btnSelect);
		
		JButton btnNewBooking = new JButton("New Booking");
		btnNewBooking.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row = 0;
				Agency agency = null;
				try{
					row = table.getSelectedRow();
					agency = custCtr.getAgency((int) table.getModel().getValueAt(row, 0));
				}
				catch(ArrayIndexOutOfBoundsException ae){
					JOptionPane.showMessageDialog(customersWrapper, "Select a row in the table!", "Request", JOptionPane.WARNING_MESSAGE);
				}
				MainGUI.getInstance().showRoomBooking(agency);
			}
		});
		upperAgenWrapLeft.add(btnNewBooking);
		
		JPanel upperAgenWrapRight = new JPanel();
		upperAgenWrapper.add(upperAgenWrapRight, BorderLayout.EAST);
		
		textFieldSearchAgen = new JTextField();
		upperAgenWrapRight.add(textFieldSearchAgen);
		textFieldSearchAgen.setColumns(10);
		
		JButton btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int ID = 0;
				try{
					ID = Integer.parseInt(textFieldSearchAgen.getText());
					Agency agency = custCtr.getAgency(ID);
					agency.getID();
					showAgency(agency);
				}
				catch(NumberFormatException nfe){
					JOptionPane.showMessageDialog(customersWrapper, "Enter a valid number!", "Request", JOptionPane.WARNING_MESSAGE);
				}
				catch(NullPointerException npe){
					JOptionPane.showMessageDialog(customersWrapper, "Agency with such an ID doesn't exist!", "Request", JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		upperAgenWrapRight.add(btnSearch);
		
		lowerAgenWrapper = new JPanel();
		agenPanel.add(lowerAgenWrapper);
		lowerAgenWrapper.setLayout(new CardLayout(0, 0));
		
		JPanel allAgen = new JPanel();
		lowerAgenWrapper.add(allAgen, "All Agencies");
		allAgen.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane_1 = new JScrollPane();
		allAgen.add(scrollPane_1);
		
		table = new JTable();
		scrollPane_1.setViewportView(table);
		//TODO
		JPanel newAgen = new JPanel();
		lowerAgenWrapper.add(newAgen, "New Agency");
		newAgen.setLayout(new BorderLayout());
		
		JPanel newAgenContainer = new JPanel();
		newAgenContainer.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		newAgen.add(newAgenContainer, BorderLayout.NORTH);
		newAgenContainer.setBorder(BorderFactory.createTitledBorder("New Agency"));
		
		JPanel newAgenDetails = new JPanel();
		newAgenContainer.add(newAgenDetails);
		newAgenDetails.setLayout(new BoxLayout(newAgenDetails, BoxLayout.X_AXIS));
		
		JPanel newAgenLeft = new JPanel();
		newAgenDetails.add(newAgenLeft);
		newAgenLeft.setLayout(new GridLayout(0, 1, 0, 0));
		
		JPanel newAgenName = new JPanel();
		newAgenLeft.add(newAgenName);
		newAgenName.setLayout(new BoxLayout(newAgenName, BoxLayout.X_AXIS));
		
		JLabel lblName = new JLabel("Name:");
		newAgenName.add(lblName);
		lblName.setHorizontalAlignment(SwingConstants.CENTER);
		
		JPanel newAgenDiscount = new JPanel();
		newAgenLeft.add(newAgenDiscount);
		newAgenDiscount.setLayout(new BoxLayout(newAgenDiscount, BoxLayout.X_AXIS));
		
		JLabel lblDiscount = new JLabel("Discount:");
		newAgenDiscount.add(lblDiscount);
		lblDiscount.setHorizontalAlignment(SwingConstants.CENTER);
		
		JPanel newAgenRight = new JPanel();
		newAgenDetails.add(newAgenRight);
		newAgenRight.setLayout(new BoxLayout(newAgenRight, BoxLayout.Y_AXIS));
		
		JPanel newAgenNameField = new JPanel();
		newAgenRight.add(newAgenNameField);
		newAgenNameField.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		textFieldAgenNameNew = new JTextField();
		newAgenNameField.add(textFieldAgenNameNew);
		textFieldAgenNameNew.setColumns(10);
		
		JPanel newAgenDiscountField = new JPanel();
		newAgenRight.add(newAgenDiscountField);
		newAgenDiscountField.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		textFieldAgenDiscountNew = new JTextField();
		newAgenDiscountField.add(textFieldAgenDiscountNew);
		textFieldAgenDiscountNew.setColumns(10);
		
		JPanel newAgenButtons = new JPanel();
		newAgen.add(newAgenButtons, BorderLayout.CENTER);
		
		JButton btnCreate = new JButton("Create");
		btnCreate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name = textFieldAgenNameNew.getText();
				int discount = 0;
				try{
					discount = Integer.parseInt(textFieldAgenDiscountNew.getText());
				}
				catch(NumberFormatException nfe){
					JOptionPane.showMessageDialog(customersWrapper, "Discount is automatically 0.", "Message", JOptionPane.INFORMATION_MESSAGE);
				}
				if(discount<0||discount>100){
					JOptionPane.showMessageDialog(customersWrapper, "Discount is automatically 0.", "Message", JOptionPane.INFORMATION_MESSAGE);
					discount = 0;
				}
				custCtr.newAgency(name, discount, new LinkedList<Customer>());
				showAllAgencies();
			}
		});
		newAgenButtons.add(btnCreate);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showAllAgencies();
			}
		});
		newAgenButtons.add(btnCancel);
		
		JPanel editAgen = new JPanel();
		lowerAgenWrapper.add(editAgen, "Edit Agency");
		editAgen.setLayout(new BorderLayout(0, 0));
		
		JPanel editAgenContainer = new JPanel();
		editAgen.add(editAgenContainer, BorderLayout.NORTH);
		
		JPanel editAgenDetails = new JPanel();
		editAgenContainer.add(editAgenDetails);
		editAgenDetails.setLayout(new BoxLayout(editAgenDetails, BoxLayout.X_AXIS));
		
		JPanel editAgenLeft = new JPanel();
		editAgenDetails.add(editAgenLeft);
		editAgenLeft.setLayout(new GridLayout(0, 1, 0, 0));
		
		JPanel editAgenIdLabelPanel = new JPanel();
		editAgenLeft.add(editAgenIdLabelPanel);
		editAgenIdLabelPanel.setLayout(new BoxLayout(editAgenIdLabelPanel, BoxLayout.X_AXIS));
		
		JLabel lblId = new JLabel("ID:");
		editAgenIdLabelPanel.add(lblId);
		
		JPanel editAgenNameLabelPanel = new JPanel();
		editAgenLeft.add(editAgenNameLabelPanel);
		editAgenNameLabelPanel.setLayout(new BoxLayout(editAgenNameLabelPanel, BoxLayout.X_AXIS));
		
		JLabel lblName_1 = new JLabel("Name:");
		editAgenNameLabelPanel.add(lblName_1);
		
		JPanel editAgenDiscountLabelPanel = new JPanel();
		editAgenLeft.add(editAgenDiscountLabelPanel);
		editAgenDiscountLabelPanel.setLayout(new BoxLayout(editAgenDiscountLabelPanel, BoxLayout.X_AXIS));
		
		JLabel lblDiscount_1 = new JLabel("Discount:");
		editAgenDiscountLabelPanel.add(lblDiscount_1);
		
		JPanel editAgenRight = new JPanel();
		editAgenDetails.add(editAgenRight);
		editAgenRight.setLayout(new BoxLayout(editAgenRight, BoxLayout.Y_AXIS));
		
		JPanel editAgenIdFieldPanel = new JPanel();
		editAgenRight.add(editAgenIdFieldPanel);
		
		textFieldAgenIdEdit = new JTextField();
		textFieldAgenIdEdit.setEnabled(false);
		textFieldAgenIdEdit.setText("");
		editAgenIdFieldPanel.add(textFieldAgenIdEdit);
		textFieldAgenIdEdit.setColumns(10);
		
		JPanel editAgenNameFieldPanel = new JPanel();
		editAgenRight.add(editAgenNameFieldPanel);
		
		textFieldAgenNameEdit = new JTextField();
		textFieldAgenNameEdit.setText("");
		editAgenNameFieldPanel.add(textFieldAgenNameEdit);
		textFieldAgenNameEdit.setColumns(10);
		
		JPanel editAgenDiscountFieldPanel = new JPanel();
		editAgenRight.add(editAgenDiscountFieldPanel);
		
		textFieldAgenDiscountEdit = new JTextField();
		textFieldAgenDiscountEdit.setText("");
		editAgenDiscountFieldPanel.add(textFieldAgenDiscountEdit);
		textFieldAgenDiscountEdit.setColumns(10);
		
		JPanel editAgenButtonsPanel = new JPanel();
		editAgen.add(editAgenButtonsPanel, BorderLayout.CENTER);
		editAgenButtonsPanel.setLayout(new BorderLayout(0, 0));
		
		JPanel editAgenButtonsCenterPanel = new JPanel();
		editAgenButtonsPanel.add(editAgenButtonsCenterPanel, BorderLayout.CENTER);
		
		JButton btnEdit = new JButton("Edit");
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int ID = Integer.parseInt(textFieldAgenIdEdit.getText());
				String name = textFieldAgenNameEdit.getText();
				int discount = 0;
				try{
					discount = Integer.parseInt(textFieldAgenDiscountEdit.getText());
				}
				catch(NumberFormatException nfe){
					JOptionPane.showMessageDialog(customersWrapper, "Discount is automatically 0.", "Message", JOptionPane.INFORMATION_MESSAGE);
				}
				if(discount<0||discount>100){
					JOptionPane.showMessageDialog(customersWrapper, "Discount is automatically 0.", "Message", JOptionPane.INFORMATION_MESSAGE);
					discount = 0;
				}
				Agency agency = custCtr.getAgency(ID);
				custCtr.updateAgency(ID, name, discount, agency.getProvidedCustomers());
				showAllAgencies();
			}
		});
		editAgenButtonsCenterPanel.add(btnEdit);
		
		JButton btnCancel_1 = new JButton("Cancel");
		btnCancel_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showAllAgencies();
			}
		});
		editAgenButtonsCenterPanel.add(btnCancel_1);
		
		JPanel showAgen = new JPanel();
		lowerAgenWrapper.add(showAgen, "Show Agency");
		showAgen.setLayout(new BorderLayout(0, 0));
		
		JPanel showAgenContainer = new JPanel();
		showAgen.add(showAgenContainer, BorderLayout.NORTH);
		showAgenContainer.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JPanel showAgenDetails = new JPanel();
		showAgenContainer.add(showAgenDetails);
		showAgenDetails.setLayout(new BoxLayout(showAgenDetails, BoxLayout.X_AXIS));
		
		JPanel showAgenLeft = new JPanel();
		showAgenDetails.add(showAgenLeft);
		showAgenLeft.setLayout(new GridLayout(0, 1, 0, 0));
		
		JPanel showAgenIdLabelPanel = new JPanel();
		showAgenLeft.add(showAgenIdLabelPanel);
		showAgenIdLabelPanel.setLayout(new BoxLayout(showAgenIdLabelPanel, BoxLayout.X_AXIS));
		
		JLabel lblId_1 = new JLabel("ID:");
		lblId_1.setVerticalTextPosition(SwingConstants.CENTER);
		lblId_1.setHorizontalTextPosition(SwingConstants.TRAILING);
		lblId_1.setAlignmentY(Component.CENTER_ALIGNMENT);
		lblId_1.setAlignmentX(Component.LEFT_ALIGNMENT);
		lblId_1.setHorizontalAlignment(SwingConstants.LEADING);
		lblId_1.setVerticalAlignment(SwingConstants.CENTER);
		showAgenIdLabelPanel.add(lblId_1);
		
		JPanel showAgenNameLabel = new JPanel();
		showAgenLeft.add(showAgenNameLabel);
		showAgenNameLabel.setLayout(new BoxLayout(showAgenNameLabel, BoxLayout.X_AXIS));
		
		JLabel lblName_2 = new JLabel("Name:");
		lblName_2.setHorizontalAlignment(SwingConstants.LEFT);
		showAgenNameLabel.add(lblName_2);
		
		JPanel showAgenDiscountLabelPanel = new JPanel();
		showAgenLeft.add(showAgenDiscountLabelPanel);
		showAgenDiscountLabelPanel.setLayout(new BoxLayout(showAgenDiscountLabelPanel, BoxLayout.X_AXIS));
		
		JLabel lblDiscount_2 = new JLabel("Discount:");
		showAgenDiscountLabelPanel.add(lblDiscount_2);
		
		JPanel showAgenRight = new JPanel();
		showAgenDetails.add(showAgenRight);
		showAgenRight.setLayout(new BoxLayout(showAgenRight, BoxLayout.Y_AXIS));
		
		JPanel showAgenIdFieldPanel = new JPanel();
		showAgenRight.add(showAgenIdFieldPanel);
		
		textFieldAgenIdShow = new JTextField();
		textFieldAgenIdShow.setEnabled(false);
		textFieldAgenIdShow.setText("");
		showAgenIdFieldPanel.add(textFieldAgenIdShow);
		textFieldAgenIdShow.setColumns(10);
		
		JPanel showAgenNameFieldPanel = new JPanel();
		showAgenRight.add(showAgenNameFieldPanel);
		
		textFieldAgenNameShow = new JTextField();
		textFieldAgenNameShow.setEnabled(false);
		textFieldAgenNameShow.setText("");
		showAgenNameFieldPanel.add(textFieldAgenNameShow);
		textFieldAgenNameShow.setColumns(10);
		
		JPanel showAgenDiscountFieldPanel = new JPanel();
		showAgenRight.add(showAgenDiscountFieldPanel);
		
		textFieldAgenDiscountShow = new JTextField();
		textFieldAgenDiscountShow.setEnabled(false);
		textFieldAgenDiscountShow.setText("");
		showAgenDiscountFieldPanel.add(textFieldAgenDiscountShow);
		textFieldAgenDiscountShow.setColumns(10);
		
		JPanel showAgenButtons = new JPanel();
		showAgen.add(showAgenButtons, BorderLayout.CENTER);
		showAgenButtons.setLayout(new BorderLayout(0, 0));
		
		JPanel showAgenButtonsLeft = new JPanel();
		showAgenButtons.add(showAgenButtonsLeft, BorderLayout.WEST);
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showAllAgencies();
			}
		});
		showAgenButtonsLeft.add(btnBack);
		
		JPanel showButtonsAgenCenter = new JPanel();
		showAgenButtons.add(showButtonsAgenCenter, BorderLayout.CENTER);
		
		JButton btnEdit_1 = new JButton("Edit");
		btnEdit_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int ID = Integer.parseInt(textFieldAgenIdShow.getText());
				Agency agency = custCtr.getAgency(ID);
				editAgency(agency);
			}
		});
		showButtonsAgenCenter.add(btnEdit_1);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					int ID = Integer.parseInt(textFieldAgenIdShow.getText());
					custCtr.deleteAgency(ID);					
				}
				catch(NumberFormatException nfe){
					JOptionPane.showMessageDialog(customersWrapper, "Enter valid number", "Error", JOptionPane.ERROR_MESSAGE);
				}
					showAllAgencies();
				
			}
		});
		showButtonsAgenCenter.add(btnDelete);
		
		fillAgenTable();
		

	}

	/*
	 * Manually added methods
	 */
	
	private void editAgency(Agency agency){
		CardLayout card = (CardLayout) (lowerAgenWrapper.getLayout());
		card.show(lowerAgenWrapper, "Edit Agency");
		textFieldAgenIdEdit.setText(String.valueOf((agency.getID())));
		textFieldAgenNameEdit.setText(agency.getName());
		textFieldAgenDiscountEdit.setText(String.valueOf(agency.getAgencyDiscountLevel()));
	}
	
	private void showAgency(Agency agency){
		CardLayout card = (CardLayout) (lowerAgenWrapper.getLayout());
		card.show(lowerAgenWrapper, "Show Agency");
		textFieldAgenIdShow.setText(String.valueOf((agency.getID())));
		textFieldAgenNameShow.setText(agency.getName());
		textFieldAgenDiscountShow.setText(String.valueOf(agency.getAgencyDiscountLevel()));
	}

	private void showAllAgencies(){
		CardLayout card = (CardLayout) (lowerAgenWrapper.getLayout());
		card.show(lowerAgenWrapper, "All Agencies");
		fillAgenTable();
	}
	private void fillAgenTable(){
		dtmAgen = new DefaultTableModel(new Object[][] {},
				new String[] { "ID", "Name", "Discount"});
		table.setModel(dtmAgen);
		LinkedList<Agency> agencies = custCtr.getAllAgencies();
		for(Agency agency: agencies){
			int ID = agency.getID();
			String name = agency.getName();
			int discount = agency.getAgencyDiscountLevel();
			
			Object[] rowData = { ID,name,discount};
			dtmAgen.addRow(rowData);
		}
	}
	
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
		custBillinstHireTableModel = new DefaultTableModel(new Object[][] {},
				new String[] { "Instructor Hire ID", "Customer ID",
				"First Name", "Last Name", "Address", 
				"Total Price", "Status" });
		custBillinstHireTable.setModel(custBillinstHireTableModel);
		String text = null;
		try{
			Customer customer = custCtr.getCustomer(Integer.parseInt(billCustIDField.getText()));
			ActivitiesCtr actCtr = new ActivitiesCtr();
			ArrayList<InstructorHire> hires = actCtr.getInstructorHires();
			double totalPrice =0;
			for (InstructorHire ih: hires){
				if(ih.getCustomer().getPersonID()==customer.getPersonID()){
					Object[] rowData = {ih.getId(), customer.getPersonID(), customer.getFname(), customer.getLname(), customer.getAddress(), ih.getActivityBooking().getActivity().getInstructorPrice(),ih.getStatus() };
					custBillinstHireTableModel.addRow(rowData);
					if(ih.getStatus().equals("unpaid"))totalPrice = totalPrice + ih.getActivityBooking().getActivity().getInstructorPrice();
				}
			}
			text = "Total Price: " + totalPrice;
			totalInstructor.setText(text);
		}
		catch(NumberFormatException nfe){
			JOptionPane.showMessageDialog(null, "Enter valid number!", "Error ",
					JOptionPane.ERROR_MESSAGE);
		}
		
	

	}
			
	private void markPaidInstHire() {
		int rowCount = custBillinstHireTableModel.getRowCount();
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String dat = sdf.format(date);
		for(int row = 0; row<rowCount; row++){
			int ID = (int) custBillinstHireTableModel.getValueAt(row, 0);
			InstructorHire ih = actCtr.findInstructorHire(ID);
			if(ih.getStatus().equals("unpaid"))ih.setStatus("paid on: " + dat);
			actCtr.updateInstructorHire(ih.getId(), ih.getCustomer(), ih.getInstructor(), ih.getActivityBooking(), ih.getActivityTime(), ih.getStatus());
			
		}
		fillInstHireBillTable();
	}
	
	private void findCust(int ID) {
		boolean found = true;
		Customer customer = null;
		try {
			customer = custCtr.getCustomer(ID);
		} catch (IllegalArgumentException e) {
			found = false;
			JOptionPane.showMessageDialog(null, e.getMessage(), "Error",
					JOptionPane.ERROR_MESSAGE);
		}
		if (found) {
			showIDField.setText(Integer.toString(customer.getPersonID()));
			showCPRField.setText(customer.getCPR());
			showFNameField.setText(customer.getFname());
			showLNameField.setText(customer.getLname());
			showAddressField.setText(customer.getAddress());
			showZIPField.setText(customer.getZIP());
			showCountryField.setText(customer.getCountry());
			showEmailField.setText(customer.getEmail());
			showPassField.setText(customer.getPassword());
			showRegDateField.setText(customer.getRegistrationDate());
			showStaysField.setText(Integer.toString(customer.getNoOfStays()));
			
			CardLayout card = (CardLayout) (lowerCrudWrapper.getLayout());
			card.show(lowerCrudWrapper, "Show customer");
		}
	}
	
	private void editCust(int ID) {
		boolean edit = true;
		try {
			 customer = custCtr.getCustomer(ID);
		} 
		catch (Exception e) {
			edit = false;
			JOptionPane.showMessageDialog(null, e.getMessage(), "Error ",
					JOptionPane.ERROR_MESSAGE);
		}
		if (edit) {
			editIDField.setText(Integer.toString(customer.getPersonID()));
			editCPRField.setText(customer.getCPR());
			editFNameField.setText(customer.getFname());
			editLNameField.setText(customer.getLname());
			editAddressField.setText(customer.getAddress());
			editZIPField.setText(customer.getZIP());
			editCountryField.setText(customer.getCountry());
			editEmailField.setText(customer.getEmail());
			editPassField.setText(customer.getPassword());
			editRegDateField.setText(customer.getRegistrationDate());
			editStaysField.setText(Integer.toString(customer.getNoOfStays()));
			
			CardLayout card = (CardLayout) (lowerCrudWrapper.getLayout());
			card.show(lowerCrudWrapper, "Edit customer");
		}
	}
	
	private void allCust() {
		ArrayList<Customer> list = custCtr.getAllCustomers();
		allCustTableModel.setRowCount(0);
		for (Customer customer : list) {
			allCustTableModel.addRow(new Object[] { customer.getPersonID(), customer.getCPR(), 
					customer.getFname(), customer.getLname(), customer.getAddress(), 
					customer.getZIP(), customer.getCountry(), customer.getEmail(), 
					customer.getPassword(), customer.getRegistrationDate(), customer.getNoOfStays()});
		}
		CardLayout card = (CardLayout) (lowerCrudWrapper.getLayout());
		card.show(lowerCrudWrapper, "All customers");
	}
}
package GUI;

import Controllers.StaffCtr;
import Models.*;
import java.util.ArrayList;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class StaffGUI extends JPanel {

	private static final long serialVersionUID = 1L;

	private StaffCtr staffCtr;
	private Staff staff;
	
	private JTextField showIDField;
	private JTextField showCPRField;
	private JTextField showFNameField;
	private JTextField showLNameField;
	private JTextField showAddressField;
	private JTextField showZIPField;
	private JTextField showCountryField;
	private JTextField showEmailField;
	private JTextField showPassField;
	private JTextField showSalaryField;
	private JTextField showTypeField;

	private JTextField editIDField;
	private JTextField editCPRField;
	private JTextField editFNameField;
	private JTextField editLNameField;
	private JTextField editAddressField;
	private JTextField editZIPField;
	private JTextField editCountryField;
	private JTextField editEmailField;
	private JTextField editPassField;
	private JTextField editSalaryField;
	private JTextField editTypeField;

	private JTextField createCPRField;
	private JTextField createFNameField;
	private JTextField createLNameField;
	private JTextField createAddressField;
	private JTextField createZIPField;
	private JTextField createCountryField;
	private JTextField createEmailField;
	private JTextField createPassField;
	private JTextField createSalaryField;
	private JTextField createTypeField;

	private JPanel upperWrapper;
	private JPanel lowerWrapper;
	private JPanel showStaff;
	private JPanel allStaff;
	private JPanel editStaff;
	private JPanel newStaff;
	
	private JTextField IDField;
	
	private DefaultTableModel tableModel;
	private JTable allStaffTable;

	public StaffGUI() { 
		staffCtr = new StaffCtr();
		
		setPreferredSize(new Dimension(780, 535));
	
		tableModel = new DefaultTableModel() {
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		tableModel.addColumn("ID");
		tableModel.addColumn("CPR");
		tableModel.addColumn("First name");
		tableModel.addColumn("Last name");
		tableModel.addColumn("Address");
		tableModel.addColumn("ZIP");
		tableModel.addColumn("Country");
		tableModel.addColumn("Email");
		tableModel.addColumn("Password");
		tableModel.addColumn("Salary");
		tableModel.addColumn("Type");
		
		setLayout(new BorderLayout());

		upperWrapper = new JPanel();
		add(upperWrapper, BorderLayout.NORTH);
		upperWrapper.setLayout(new BorderLayout());
		upperWrapper.setBorder(BorderFactory.createTitledBorder("Staff"));

		JPanel upperWrapperLeft = new JPanel();
		upperWrapper.add(upperWrapperLeft, BorderLayout.WEST);

		JButton newStaffButton = new JButton("New staff");
		newStaffButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CardLayout card = (CardLayout) lowerWrapper.getLayout();
				card.show(lowerWrapper, "New staff");
			}
		});
		upperWrapperLeft.add(newStaffButton);

		JPanel upperWrapperRight = new JPanel();
		upperWrapper.add(upperWrapperRight, BorderLayout.EAST);

		JLabel IDLabel = new JLabel("ID:");
		upperWrapperRight.add(IDLabel);

		IDField = new JTextField();
		upperWrapperRight.add(IDField);
		IDField.setColumns(5);

		JButton searchButton = new JButton("Search");
		searchButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				findStaff(Integer.parseInt(IDField.getText()));
				IDField.setText("");
			}
		});
		upperWrapperRight.add(searchButton);

		lowerWrapper = new JPanel();
		this.add(lowerWrapper);
		lowerWrapper.setLayout(new CardLayout(0, 0));

		showStaff = new JPanel();
		lowerWrapper.add(showStaff, "showStaff");
		showStaff.setLayout(new BorderLayout());

		JPanel staffContainer = new JPanel();
		showStaff.add(staffContainer, BorderLayout.NORTH);
		staffContainer.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		staffContainer.setBorder(BorderFactory
				.createTitledBorder("Staff details"));

		JPanel staffDetails = new JPanel();
		staffDetails.setLayout(new BoxLayout(staffDetails, BoxLayout.X_AXIS));
		staffContainer.add(staffDetails);

		JPanel showStaffLeft = new JPanel();
		staffDetails.add(showStaffLeft);
		showStaffLeft.setLayout(new GridLayout(0, 1, 0, 0));

		JPanel IDLabelPanel = new JPanel();
		IDLabelPanel.setLayout(new BoxLayout(IDLabelPanel, BoxLayout.X_AXIS));
		showStaffLeft.add(IDLabelPanel);

		JLabel IDLabel2 = new JLabel("ID");
		IDLabelPanel.add(IDLabel2);
		IDLabel2.setHorizontalAlignment(SwingConstants.CENTER);

		JPanel CPRLabelPanel = new JPanel();
		CPRLabelPanel.setLayout(new BoxLayout(CPRLabelPanel, BoxLayout.X_AXIS));
		showStaffLeft.add(CPRLabelPanel);

		JLabel CPRLabel = new JLabel("CPR");
		CPRLabelPanel.add(CPRLabel);
		CPRLabel.setHorizontalAlignment(SwingConstants.CENTER);

		JPanel fNameLabelPanel = new JPanel();
		fNameLabelPanel.setLayout(new BoxLayout(fNameLabelPanel, BoxLayout.X_AXIS));
		showStaffLeft.add(fNameLabelPanel);

		JLabel fNameLabel = new JLabel("First name");
		fNameLabelPanel.add(fNameLabel);
		fNameLabel.setHorizontalAlignment(SwingConstants.CENTER);

		JPanel lNameLabelPanel = new JPanel();
		lNameLabelPanel.setLayout(new BoxLayout(lNameLabelPanel, BoxLayout.X_AXIS));
		showStaffLeft.add(lNameLabelPanel);

		JLabel lNameLabel = new JLabel("Last name");
		lNameLabelPanel.add(lNameLabel);
		lNameLabel.setHorizontalAlignment(SwingConstants.CENTER);

		JPanel addressLabelPanel = new JPanel();
		showStaffLeft.add(addressLabelPanel);
		addressLabelPanel.setLayout(new BoxLayout(addressLabelPanel, BoxLayout.X_AXIS));

		JLabel addressLabel = new JLabel("Address");
		addressLabel.setHorizontalAlignment(SwingConstants.CENTER);
		addressLabelPanel.add(addressLabel);

		JPanel ZIPLabelPanel = new JPanel();
		showStaffLeft.add(ZIPLabelPanel);
		ZIPLabelPanel.setLayout(new BoxLayout(ZIPLabelPanel, BoxLayout.X_AXIS));

		JLabel ZIPLabel = new JLabel("ZIP");
		ZIPLabel.setHorizontalAlignment(SwingConstants.CENTER);
		ZIPLabelPanel.add(ZIPLabel);
		
		JPanel countryLabelPanel = new JPanel();
		countryLabelPanel.setLayout(new BoxLayout(countryLabelPanel, BoxLayout.X_AXIS));
		showStaffLeft.add(countryLabelPanel);

		JLabel countryLabel = new JLabel("Country");
		countryLabelPanel.add(countryLabel);
		countryLabel.setHorizontalAlignment(SwingConstants.CENTER);
		
		JPanel emailLabelPanel = new JPanel();
		emailLabelPanel.setLayout(new BoxLayout(emailLabelPanel, BoxLayout.X_AXIS));
		showStaffLeft.add(emailLabelPanel);

		JLabel emailLabel = new JLabel("Email");
		emailLabelPanel.add(emailLabel);
		emailLabel.setHorizontalAlignment(SwingConstants.CENTER);
		
		JPanel passLabelPanel = new JPanel();
		passLabelPanel.setLayout(new BoxLayout(passLabelPanel, BoxLayout.X_AXIS));
		showStaffLeft.add(passLabelPanel);

		JLabel passLabel = new JLabel("Password");
		passLabelPanel.add(passLabel);
		passLabel.setHorizontalAlignment(SwingConstants.CENTER);
		
		JPanel salaryLabelPanel = new JPanel();
		salaryLabelPanel.setLayout(new BoxLayout(salaryLabelPanel, BoxLayout.X_AXIS));
		showStaffLeft.add(salaryLabelPanel);

		JLabel salaryLabel = new JLabel("Salary");
		salaryLabelPanel.add(salaryLabel);
		salaryLabel.setHorizontalAlignment(SwingConstants.CENTER);
		
		JPanel typeLabelPanel = new JPanel();
		typeLabelPanel.setLayout(new BoxLayout(typeLabelPanel, BoxLayout.X_AXIS));
		showStaffLeft.add(typeLabelPanel);

		JLabel typeLabel = new JLabel("Type");
		typeLabelPanel.add(typeLabel);
		typeLabel.setHorizontalAlignment(SwingConstants.CENTER);

		JPanel showStaffRight = new JPanel();
		showStaffRight.setLayout(new BoxLayout(showStaffRight, BoxLayout.Y_AXIS));
		staffDetails.add(showStaffRight);

		JPanel showIDFieldPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		showStaffRight.add(showIDFieldPanel);

		showIDField = new JTextField();
		showIDFieldPanel.add(showIDField);
		showIDField.setHorizontalAlignment(SwingConstants.CENTER);
		showIDField.setEditable(false);
		showIDField.setColumns(20);

		JPanel showCPRFieldPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		showStaffRight.add(showCPRFieldPanel);

		showCPRField = new JTextField();
		showCPRFieldPanel.add(showCPRField);
		showCPRField.setHorizontalAlignment(SwingConstants.CENTER);
		showCPRField.setEditable(false);
		showCPRField.setColumns(20);

		JPanel showFNameFieldPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		showStaffRight.add(showFNameFieldPanel);

		showFNameField = new JTextField();
		showFNameFieldPanel.add(showFNameField);
		showFNameField.setHorizontalAlignment(SwingConstants.CENTER);
		showFNameField.setEditable(false);
		showFNameField.setColumns(20);

		JPanel showLNameFieldPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		showStaffRight.add(showLNameFieldPanel);

		showLNameField = new JTextField();
		showLNameFieldPanel.add(showLNameField);
		showLNameField.setHorizontalAlignment(SwingConstants.CENTER);
		showLNameField.setEditable(false);
		showLNameField.setColumns(20);

		JPanel showAddressFieldPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		showStaffRight.add(showAddressFieldPanel);

		showAddressField = new JTextField();
		showAddressFieldPanel.add(showAddressField);
		showAddressField.setHorizontalAlignment(SwingConstants.CENTER);
		showAddressField.setEditable(false);
		showAddressField.setColumns(20);

		JPanel showZIPFieldPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		showStaffRight.add(showZIPFieldPanel);

		showZIPField = new JTextField();
		showZIPFieldPanel.add(showZIPField);
		showZIPField.setHorizontalAlignment(SwingConstants.CENTER);
		showZIPField.setEditable(false);
		showZIPField.setColumns(20);

		JPanel showCountryFieldPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		showStaffRight.add(showCountryFieldPanel);

		showCountryField = new JTextField();
		showCountryFieldPanel.add(showCountryField);
		showCountryField.setHorizontalAlignment(SwingConstants.CENTER);
		showCountryField.setEditable(false);
		showCountryField.setColumns(20);

		JPanel showEmailFieldPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		showStaffRight.add(showEmailFieldPanel);

		showEmailField = new JTextField();
		showEmailFieldPanel.add(showEmailField);
		showEmailField.setHorizontalAlignment(SwingConstants.CENTER);
		showEmailField.setEditable(false);
		showEmailField.setColumns(20);

		JPanel showPassFieldPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		showStaffRight.add(showPassFieldPanel);

		showPassField = new JTextField();
		showPassFieldPanel.add(showPassField);
		showPassField.setHorizontalAlignment(SwingConstants.CENTER);
		showPassField.setEditable(false);
		showPassField.setColumns(20);

		JPanel showSalaryFieldPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		showStaffRight.add(showSalaryFieldPanel);

		showSalaryField = new JTextField();
		showSalaryFieldPanel.add(showSalaryField);
		showSalaryField.setHorizontalAlignment(SwingConstants.CENTER);
		showSalaryField.setEditable(false);
		showSalaryField.setColumns(20);
		
		JPanel showTypeFieldPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		showStaffRight.add(showTypeFieldPanel);

		showTypeField = new JTextField();
		showTypeFieldPanel.add(showTypeField);
		showTypeField.setHorizontalAlignment(SwingConstants.CENTER);
		showTypeField.setEditable(false);
		showTypeField.setColumns(20);
		

		JPanel bottomButtons = new JPanel();
		showStaff.add(bottomButtons, BorderLayout.CENTER);
		bottomButtons.setLayout(new BorderLayout());

		JPanel bottomButtonsRight = new JPanel();
		bottomButtons.add(bottomButtonsRight, BorderLayout.CENTER);

		JButton editButton = new JButton("Edit");
		editButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				editStaff((int) Integer.parseInt(showIDField.getText()));
			}
		});
		bottomButtonsRight.add(editButton);

		JButton deleteButton = new JButton("Delete");
		deleteButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int reply = JOptionPane.showConfirmDialog(null,
						"Are you sure you want to delete this staff member ?",
						"Delete Staff", JOptionPane.YES_NO_OPTION,
						JOptionPane.WARNING_MESSAGE);
				if (reply == JOptionPane.YES_OPTION) {
					boolean toDelete = true;
					try {
						staffCtr.deleteStaff(Integer.parseInt(showIDField
								.getText()));
					} 
					catch (Exception ex) {
						toDelete = false;
						JOptionPane.showMessageDialog(null,
								"Field value invalid", "Error", JOptionPane.ERROR_MESSAGE);
					}
					if (toDelete) {
						JOptionPane.showMessageDialog(null,
								"The staff member was deleted", "Delete staff", JOptionPane.INFORMATION_MESSAGE);
						allStaff();
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
				allStaff();
			}
		});
		bottomButtonsLeft.add(backButton);

		editStaff = new JPanel();
		lowerWrapper.add(editStaff, "Edit staff");
		editStaff.setLayout(new BorderLayout());

		JPanel editStaffContainer = new JPanel();
		editStaff.add(editStaffContainer, BorderLayout.NORTH);
		editStaffContainer.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		editStaffContainer.setBorder(BorderFactory.createTitledBorder("Edit Staff"));

		JPanel editStaffDetails = new JPanel();
		editStaffDetails.setLayout(new BoxLayout(editStaffDetails, BoxLayout.X_AXIS));
		editStaffContainer.add(editStaffDetails);

		JPanel editStaffLeft = new JPanel();
		editStaffDetails.add(editStaffLeft);
		editStaffLeft.setLayout(new GridLayout(0, 1, 0, 0));

		JPanel editIDLabelPanel = new JPanel();
		editIDLabelPanel.setLayout(new BoxLayout(editIDLabelPanel, BoxLayout.X_AXIS));
		editStaffLeft.add(editIDLabelPanel);

		JLabel editIDLabel = new JLabel("ID");
		editIDLabelPanel.add(editIDLabel);
		editIDLabel.setHorizontalAlignment(SwingConstants.CENTER);
		
		JPanel editCPRLabelPanel = new JPanel();
		editCPRLabelPanel.setLayout(new BoxLayout(editCPRLabelPanel, BoxLayout.X_AXIS));
		editStaffLeft.add(editCPRLabelPanel);

		JLabel editCPRLabel = new JLabel("CPR");
		editCPRLabelPanel.add(editCPRLabel);
		editCPRLabel.setHorizontalAlignment(SwingConstants.CENTER);
		
		JPanel editFNameLabelPanel = new JPanel();
		editFNameLabelPanel.setLayout(new BoxLayout(editFNameLabelPanel, BoxLayout.X_AXIS));
		editStaffLeft.add(editFNameLabelPanel);

		JLabel editFNameLabel = new JLabel("First name");
		editFNameLabelPanel.add(editFNameLabel);
		editFNameLabel.setHorizontalAlignment(SwingConstants.CENTER);
		
		JPanel editLNameLabelPanel = new JPanel();
		editLNameLabelPanel.setLayout(new BoxLayout(editLNameLabelPanel, BoxLayout.X_AXIS));
		editStaffLeft.add(editLNameLabelPanel);

		JLabel editLNameLabel = new JLabel("Last name");
		editLNameLabelPanel.add(editLNameLabel);
		editLNameLabel.setHorizontalAlignment(SwingConstants.CENTER);
		
		JPanel editAddressLabelPanel = new JPanel();
		editAddressLabelPanel.setLayout(new BoxLayout(editAddressLabelPanel, BoxLayout.X_AXIS));
		editStaffLeft.add(editAddressLabelPanel);

		JLabel editAddressLabel = new JLabel("Address");
		editAddressLabelPanel.add(editAddressLabel);
		editAddressLabel.setHorizontalAlignment(SwingConstants.CENTER);
		
		JPanel editZIPLabelPanel = new JPanel();
		editZIPLabelPanel.setLayout(new BoxLayout(editZIPLabelPanel, BoxLayout.X_AXIS));
		editStaffLeft.add(editZIPLabelPanel);

		JLabel editZIPLabel = new JLabel("ZIP");
		editZIPLabelPanel.add(editZIPLabel);
		editZIPLabel.setHorizontalAlignment(SwingConstants.CENTER);

		JPanel editCountryLabelPanel = new JPanel();
		editCountryLabelPanel.setLayout(new BoxLayout(editCountryLabelPanel, BoxLayout.X_AXIS));
		editStaffLeft.add(editCountryLabelPanel);

		JLabel editCountryLabel = new JLabel("Country");
		editCountryLabelPanel.add(editCountryLabel);
		editCountryLabel.setHorizontalAlignment(SwingConstants.CENTER);
		
		JPanel editEmailLabelPanel = new JPanel();
		editEmailLabelPanel.setLayout(new BoxLayout(editEmailLabelPanel, BoxLayout.X_AXIS));
		editStaffLeft.add(editEmailLabelPanel);

		JLabel editEmailLabel = new JLabel("Email");
		editEmailLabelPanel.add(editEmailLabel);
		editEmailLabel.setHorizontalAlignment(SwingConstants.CENTER);
		
		JPanel editPassLabelPanel = new JPanel();
		editPassLabelPanel.setLayout(new BoxLayout(editPassLabelPanel, BoxLayout.X_AXIS));
		editStaffLeft.add(editPassLabelPanel);

		JLabel editPassLabel = new JLabel("Password");
		editPassLabelPanel.add(editPassLabel);
		editPassLabel.setHorizontalAlignment(SwingConstants.CENTER);
		
		JPanel editSalaryLabelPanel = new JPanel();
		editSalaryLabelPanel.setLayout(new BoxLayout(editSalaryLabelPanel, BoxLayout.X_AXIS));
		editStaffLeft.add(editSalaryLabelPanel);

		JLabel editSalaryLabel = new JLabel("Salary");
		editSalaryLabelPanel.add(editSalaryLabel);
		editSalaryLabel.setHorizontalAlignment(SwingConstants.CENTER);
		
		JPanel editTypeLabelPanel = new JPanel();
		editTypeLabelPanel.setLayout(new BoxLayout(editTypeLabelPanel, BoxLayout.X_AXIS));
		editStaffLeft.add(editTypeLabelPanel);

		JLabel editTypeLabel = new JLabel("Type");
		editTypeLabelPanel.add(editTypeLabel);
		editTypeLabel.setHorizontalAlignment(SwingConstants.CENTER);

		JPanel editStaffRight = new JPanel();
		editStaffRight.setLayout(new BoxLayout(editStaffRight, BoxLayout.Y_AXIS));
		editStaffDetails.add(editStaffRight);

		JPanel editIDFieldPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		editStaffRight.add(editIDFieldPanel);

		editIDField = new JTextField();
		editIDFieldPanel.add(editIDField);
		editIDField.setHorizontalAlignment(SwingConstants.CENTER);
		editIDField.setColumns(5);

		JPanel editCPRFieldPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		editStaffRight.add(editCPRFieldPanel);

		editCPRField = new JTextField();
		editCPRFieldPanel.add(editCPRField);
		editCPRField.setHorizontalAlignment(SwingConstants.CENTER);
		editCPRField.setColumns(20);
		
		JPanel editFNameFieldPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		editStaffRight.add(editFNameFieldPanel);

		editFNameField = new JTextField();
		editFNameFieldPanel.add(editFNameField);
		editFNameField.setHorizontalAlignment(SwingConstants.CENTER);
		editFNameField.setColumns(20);

		JPanel editLNameFieldPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		editStaffRight.add(editLNameFieldPanel);

		editLNameField = new JTextField();
		editLNameFieldPanel.add(editLNameField);
		editLNameField.setHorizontalAlignment(SwingConstants.CENTER);
		editLNameField.setColumns(20);
		
		JPanel editAddressFieldPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		editStaffRight.add(editAddressFieldPanel);

		editAddressField = new JTextField();
		editAddressFieldPanel.add(editAddressField);
		editAddressField.setHorizontalAlignment(SwingConstants.CENTER);
		editAddressField.setColumns(20);
		
		JPanel editZIPFieldPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		editStaffRight.add(editZIPFieldPanel);

		editZIPField = new JTextField();
		editZIPFieldPanel.add(editZIPField);
		editZIPField.setHorizontalAlignment(SwingConstants.CENTER);
		editZIPField.setColumns(20);
		
		JPanel editCountryFieldPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		editStaffRight.add(editCountryFieldPanel);

		editCountryField = new JTextField();
		editCountryFieldPanel.add(editCountryField);
		editCountryField.setHorizontalAlignment(SwingConstants.CENTER);
		editCountryField.setColumns(20);

		JPanel editEmailFieldPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		editStaffRight.add(editEmailFieldPanel);

		editEmailField = new JTextField();
		editEmailFieldPanel.add(editEmailField);
		editEmailField.setHorizontalAlignment(SwingConstants.CENTER);
		editEmailField.setColumns(20);
		
		JPanel editPassFieldPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		editStaffRight.add(editPassFieldPanel);

		editPassField = new JTextField();
		editPassFieldPanel.add(editPassField);
		editPassField.setHorizontalAlignment(SwingConstants.CENTER);
		editPassField.setColumns(20);
		
		JPanel editSalaryFieldPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		editStaffRight.add(editSalaryFieldPanel);

		editSalaryField = new JTextField();
		editSalaryFieldPanel.add(editSalaryField);
		editSalaryField.setHorizontalAlignment(SwingConstants.CENTER);
		editSalaryField.setColumns(20);

		JPanel editTypeFieldPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		editStaffRight.add(editTypeFieldPanel);

		editTypeField = new JTextField();
		editTypeFieldPanel.add(editTypeField);
		editTypeField.setHorizontalAlignment(SwingConstants.CENTER);
		editTypeField.setColumns(20);

		JPanel editButtonsPanel = new JPanel();
		editStaff.add(editButtonsPanel, BorderLayout.CENTER);
		editButtonsPanel.setLayout(new BorderLayout());

		JPanel editCenterButtonsPanel = new JPanel();
		editButtonsPanel.add(editCenterButtonsPanel, BorderLayout.CENTER);

		JButton editButton2 = new JButton("Edit");
		editButton2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int reply = JOptionPane.showConfirmDialog(null,
						"Are you sure you want to edit this staff member ?",
						"Edit staff", JOptionPane.YES_NO_OPTION,
						JOptionPane.WARNING_MESSAGE);
				if (reply == JOptionPane.YES_OPTION) {
					boolean toEdit = true;
					try {
						staffCtr.updateStaff(Integer.parseInt(editIDField.getText()), 
								editCPRField.getText(), editFNameField.getText(),
								editLNameField.getText(), editCountryField.getText(), 
								editZIPField.getText(), editAddressField.getText(), 
								editEmailField.getText(), editPassField.getText(), 
								Double.parseDouble(editSalaryField.getText()), editTypeField.getText());
					}
					catch (NumberFormatException ex) {
						toEdit = false;
						JOptionPane.showMessageDialog(null,
								"Field value invalid", "Error",
								JOptionPane.ERROR_MESSAGE);
					}
					if (toEdit) {
						JOptionPane.showMessageDialog(null,
								"The staff member was edited", "Edit staff",
								JOptionPane.INFORMATION_MESSAGE);
						findStaff(Integer.parseInt(showIDField.getText()));
					}
				}
			}
		});
		editCenterButtonsPanel.add(editButton2);

		JButton cancelButtonEdit = new JButton("Cancel");
		cancelButtonEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				findStaff((int) Integer.parseInt(editIDField.getText()));
			}
		});
		editCenterButtonsPanel.add(cancelButtonEdit);

		newStaff = new JPanel();
		lowerWrapper.add(newStaff, "New staff");
		newStaff.setLayout(new BorderLayout());

		JPanel newStaffContainer = new JPanel();
		newStaff.add(newStaffContainer, BorderLayout.NORTH);
		newStaffContainer.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		newStaffContainer.setBorder(BorderFactory.createTitledBorder("New staff"));

		JPanel newStaffDetails = new JPanel();
		newStaffDetails.setLayout(new BoxLayout(newStaffDetails, BoxLayout.X_AXIS));
		newStaffContainer.add(newStaffDetails);

		JPanel newStaffLeft = new JPanel();
		newStaffDetails.add(newStaffLeft);
		newStaffLeft.setLayout(new GridLayout(0, 1, 0, 0));

		JPanel createCPRLabelPanel = new JPanel();
		newStaffLeft.add(createCPRLabelPanel);
		createCPRLabelPanel.setLayout(new BoxLayout(createCPRLabelPanel, BoxLayout.X_AXIS));
		
		JLabel createCPRLabel = new JLabel("CPR");
		createCPRLabelPanel.add(createCPRLabel);
		createCPRLabel.setHorizontalAlignment(SwingConstants.CENTER);

		JPanel createFNameLabelPanel = new JPanel();
		newStaffLeft.add(createFNameLabelPanel);
		createFNameLabelPanel.setLayout(new BoxLayout(createFNameLabelPanel, BoxLayout.X_AXIS));
		
		JLabel createFNameLabel = new JLabel("First name");
		createFNameLabelPanel.add(createFNameLabel);
		createFNameLabel.setHorizontalAlignment(SwingConstants.CENTER);
		
		JPanel createLNameLabelPanel = new JPanel();
		newStaffLeft.add(createLNameLabelPanel);
		createLNameLabelPanel.setLayout(new BoxLayout(createLNameLabelPanel, BoxLayout.X_AXIS));
		
		JLabel createLNameLabel = new JLabel("Last name");
		createLNameLabelPanel.add(createLNameLabel);
		createLNameLabel.setHorizontalAlignment(SwingConstants.CENTER);

		JPanel createAddressLabelPanel = new JPanel();
		newStaffLeft.add(createAddressLabelPanel);
		createAddressLabelPanel.setLayout(new BoxLayout(createAddressLabelPanel, BoxLayout.X_AXIS));
		
		JLabel createAddressLabel = new JLabel("Address");
		createAddressLabelPanel.add(createAddressLabel);
		createAddressLabel.setHorizontalAlignment(SwingConstants.CENTER);
		
		JPanel createZIPLabelPanel = new JPanel();
		newStaffLeft.add(createZIPLabelPanel);
		createZIPLabelPanel.setLayout(new BoxLayout(createZIPLabelPanel, BoxLayout.X_AXIS));
		
		JLabel createZIPLabel = new JLabel("ZIP");
		createZIPLabelPanel.add(createZIPLabel);
		createZIPLabel.setHorizontalAlignment(SwingConstants.CENTER);

		JPanel createCountryLabelPanel = new JPanel();
		newStaffLeft.add(createCountryLabelPanel);
		createCountryLabelPanel.setLayout(new BoxLayout(createCountryLabelPanel, BoxLayout.X_AXIS));
		
		JLabel createCountryLabel = new JLabel("Country");
		createCountryLabelPanel.add(createCountryLabel);
		createCountryLabel.setHorizontalAlignment(SwingConstants.CENTER);
		
		JPanel createEmailLabelPanel = new JPanel();
		newStaffLeft.add(createEmailLabelPanel);
		createEmailLabelPanel.setLayout(new BoxLayout(createEmailLabelPanel, BoxLayout.X_AXIS));
		
		JLabel createEmailLabel = new JLabel("Email");
		createEmailLabelPanel.add(createEmailLabel);
		createEmailLabel.setHorizontalAlignment(SwingConstants.CENTER);

		JPanel createPassLabelPanel = new JPanel();
		newStaffLeft.add(createPassLabelPanel);
		createPassLabelPanel.setLayout(new BoxLayout(createPassLabelPanel, BoxLayout.X_AXIS));
		
		JLabel createPassLabel = new JLabel("Password");
		createPassLabelPanel.add(createPassLabel);
		createPassLabel.setHorizontalAlignment(SwingConstants.CENTER);
		
		JPanel createSalaryLabelPanel = new JPanel();
		newStaffLeft.add(createSalaryLabelPanel);
		createSalaryLabelPanel.setLayout(new BoxLayout(createSalaryLabelPanel, BoxLayout.X_AXIS));
		
		JLabel createSalaryLabel = new JLabel("Salary");
		createSalaryLabelPanel.add(createSalaryLabel);
		createSalaryLabel.setHorizontalAlignment(SwingConstants.CENTER);
		
		JPanel createTypeLabelPanel = new JPanel();
		newStaffLeft.add(createTypeLabelPanel);
		createTypeLabelPanel.setLayout(new BoxLayout(createTypeLabelPanel, BoxLayout.X_AXIS));
		
		JLabel createTypeLabel = new JLabel("Type");
		createTypeLabelPanel.add(createTypeLabel);
		createTypeLabel.setHorizontalAlignment(SwingConstants.CENTER);

		JPanel newStaffRight = new JPanel();
		newStaffRight.setLayout(new BoxLayout(newStaffRight, BoxLayout.Y_AXIS));
		newStaffDetails.add(newStaffRight);

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
		
		JPanel createSalaryFieldPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		newStaffRight.add(createSalaryFieldPanel);

		createSalaryField = new JTextField();
		createSalaryFieldPanel.add(createSalaryField);
		createSalaryField.setHorizontalAlignment(SwingConstants.CENTER);
		createSalaryField.setColumns(20);
		
		JPanel createTypeFieldPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		newStaffRight.add(createTypeFieldPanel);

		createTypeField = new JTextField();
		createTypeFieldPanel.add(createTypeField);
		createTypeField.setHorizontalAlignment(SwingConstants.CENTER);
		createTypeField.setColumns(20);

		JPanel createBottomMenu = new JPanel();
		newStaff.add(createBottomMenu, BorderLayout.CENTER);
		createBottomMenu.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		JButton createButton = new JButton("Create");
		createBottomMenu.add(createButton);

		JButton cancelButtonCreate = new JButton("Cancel");
		cancelButtonCreate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				allStaff();
			}
		});
		createBottomMenu.add(cancelButtonCreate);

		allStaff = new JPanel();
		lowerWrapper.add(allStaff, "All staff");
		allStaff.setLayout(new BorderLayout());

		JPanel allStaffPanel = new JPanel();
		allStaffPanel.setBorder(BorderFactory.createTitledBorder("All staff"));
		allStaff.add(allStaffPanel, BorderLayout.CENTER);

		allStaffTable = new JTable(tableModel);
		allStaffTable.setAutoResizeMode(JTable.AUTO_RESIZE_NEXT_COLUMN);
		allStaffTable.setAutoCreateRowSorter(true);

		allStaffTable.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				int row = allStaffTable.rowAtPoint(e.getPoint());
				if (e.getClickCount() == 2) {
					findStaff(Integer.valueOf((Integer) tableModel.getValueAt(row, 0)));
				}
			}
		});
		
		allStaffPanel.setLayout(new BoxLayout(allStaffPanel, BoxLayout.X_AXIS));

		JScrollPane scrollPane = new JScrollPane();
		allStaffPanel.add(scrollPane);

		scrollPane.setViewportView(allStaffTable);
		createButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean toCreate = true;
				try {
					staffCtr.newEmployee(createCPRField.getText(), createFNameField.getText(), 
							createLNameField.getText(), createCountryField.getText(), 
							createZIPField.getText(), createAddressField.getText(), 
							createEmailField.getText(), createPassField.getText(), 
							Double.parseDouble(createSalaryField.getText()), 
							createTypeField.getText());
				} 
				catch (NumberFormatException e1) {
					toCreate = false;
					JOptionPane.showMessageDialog(null, "Field value invalid",
							"Error", JOptionPane.ERROR_MESSAGE);
				}
				if (toCreate) {
					JOptionPane.showMessageDialog(null,
							"The staff member was created", "Create staff",
							JOptionPane.INFORMATION_MESSAGE);
					allStaff();
				}
			}
		});
		allStaff();
	}
	
	private void findStaff(int ID) {
		boolean found = true;
		Staff staff = null;
		try {
			staff = staffCtr.getEmployee(ID);
		} catch (Exception e) {
			found = false;
			JOptionPane.showMessageDialog(null, e.getMessage(), "Error",
					JOptionPane.ERROR_MESSAGE);
		}
		if (found) {
			showCPRField.setText(staff.getCPR());
			showFNameField.setText(staff.getFname());
			showLNameField.setText(staff.getLname());
			showAddressField.setText(staff.getAddress());
			showZIPField.setText(staff.getZIP());
			showCountryField.setText(staff.getCountry());
			showEmailField.setText(staff.getEmail());
			showPassField.setText(staff.getPassword());
			showSalaryField.setText(Double.toString(staff.getSalary()));
			showTypeField.setText(getType(staff));
			
			CardLayout card = (CardLayout) (lowerWrapper.getLayout());
			card.show(lowerWrapper, "Show staff");
		}
	}

	private void editStaff(int ID) {
		boolean edit = true;
		try {
			staff = staffCtr.getEmployee(ID);
		} 
		catch (Exception e) {
			edit = false;
			JOptionPane.showMessageDialog(null, e.getMessage(), "Error ",
					JOptionPane.ERROR_MESSAGE);
		}
		if (edit) {
			editCPRField.setText(staff.getCPR());
			editFNameField.setText(staff.getFname());
			editLNameField.setText(staff.getLname());
			editAddressField.setText(staff.getAddress());
			editZIPField.setText(staff.getZIP());
			editCountryField.setText(staff.getCountry());
			editEmailField.setText(staff.getEmail());
			editPassField.setText(staff.getPassword());
			editSalaryField.setText(Double.toString(staff.getSalary()));
			editTypeField.setText(getType(staff));
			
			CardLayout card = (CardLayout) (lowerWrapper.getLayout());
			card.show(lowerWrapper, "Edit staff member");
		}
	}

	private void allStaff() {
		ArrayList<Staff> staff = staffCtr.getAllEmployees();
		tableModel.setRowCount(0);
		for (Staff employee : staff) {
			tableModel.addRow(new Object[] { employee.getPersonID(), employee.getCPR(), 
					employee.getFname(), employee.getLname(), employee.getAddress(), 
					employee.getZIP(), employee.getCountry(), employee.getEmail(), 
					employee.getPassword(), employee.getSalary(), getType(employee)});
		}
		CardLayout card = (CardLayout) (lowerWrapper.getLayout());
		card.show(lowerWrapper, "All staff members");
	}
	
	private String getType(Staff staff) {
		String type = null;
		if (staff instanceof Instructor) {
			type = "Instructor";
		}
		else if (staff instanceof Manager) {
			type = "Manager";
		}
			else if (staff instanceof Receptionist) {
				type = "Receptionist";
			}
				else {
					type = "Secretary";
				}
		return type;
	}
}

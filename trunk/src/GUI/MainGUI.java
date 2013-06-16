package GUI;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import Models.Agency;

public class MainGUI {
	private JFrame frame;
	private JPanel menuWrapper;
	private JPanel loginWrapper;
	private LoginGUI loginGUI;
	private JButton activitvitiesButton;
	private JButton customersButton;
	private JButton roomsButton;
	private JButton staffButtom;
	private JButton logoutButton;
	private ActivitiesGUI activitiesGUI;
	private CustomersGUI customersGUI;
	private RoomsGUI roomsGUI;
	private StaffGUI staffGUI;
	private JPanel optionsPanel;
	private JPanel contentPanel;
	private JPanel optionsBorder;
	private JPanel contentBorder;
	private static MainGUI instance;

	public MainGUI() {

	}

	/**
	 * Create the GUI and show it. For thread safety, this method should be
	 * invoked from the event-dispatching thread.
	 */
	private void initialize() {
		// Create and set up the window.
		frame = new JFrame("Skynet");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		frame.setMinimumSize(new Dimension(1000, 600));
		System.out.println(frame.getSize());

		// Display the window.
		frame.pack();
		frame.getContentPane().setLayout(new CardLayout(0, 0));
		frame.setVisible(true);

		loginWrapper = new JPanel();
		frame.getContentPane().add(loginWrapper, "Login Wrapper");
		loginWrapper.setLayout(new BorderLayout(0, 0));

		loginGUI = new LoginGUI();
		loginGUI.setAlignmentX(Component.CENTER_ALIGNMENT);
		loginWrapper.add(loginGUI, BorderLayout.NORTH);
		FlowLayout flowLayout = (FlowLayout) loginGUI.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);

		menuWrapper = new JPanel();
		// case/switch depending on who's logged in add specific GUI parts, eg.
		// customer sees on Activities
		// and managers sees everything
		frame.getContentPane().add(menuWrapper, "Menu Wrapper");

		optionsBorder = new JPanel();
		optionsBorder.setBorder(new TitledBorder(UIManager
				.getBorder("TitledBorder.border"), "Options",
				TitledBorder.LEADING, TitledBorder.TOP, null, null));
		menuWrapper.add(optionsBorder);

		optionsPanel = new JPanel();
		optionsBorder.add(optionsPanel);
		optionsPanel.setPreferredSize(new Dimension(150, 515));

		activitvitiesButton = new MenuButton("Activities");
		customersButton = new MenuButton("Customers");
		roomsButton = new MenuButton("Rooms");
		staffButtom = new MenuButton("Staff");
		logoutButton = new MenuButton("Logout");

		contentBorder = new JPanel();
		contentBorder.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null,
				null));
		menuWrapper.add(contentBorder);

		contentPanel = new JPanel();
		contentBorder.add(contentPanel);
		contentPanel.setPreferredSize(new Dimension(780, 535));
		contentPanel.setLayout(new CardLayout(0, 0));

		activitvitiesButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Logged user ID: " + loginGUI.getUserID());
				CardLayout cl = (CardLayout) contentPanel.getLayout();
				cl.show(contentPanel, "ActivitiesGUI");
			}
		});

		customersButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				;
				CardLayout cl = (CardLayout) contentPanel.getLayout();
				cl.show(contentPanel, "CustomersGUI");
			}
		});

		roomsButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CardLayout cl = (CardLayout) contentPanel.getLayout();
				cl.show(contentPanel, "RoomsGUI");
			}
		});

		staffButtom.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CardLayout cl = (CardLayout) contentPanel.getLayout();
				cl.show(contentPanel, "StaffGUI");
			}
		});

		loginGUI.loginButton().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			try{
				login();

				activitiesGUI = new ActivitiesGUI(loginGUI.getUserID());
				contentPanel.add(activitiesGUI, "ActivitiesGUI");

				customersGUI = new CustomersGUI();
				contentPanel.add(customersGUI, "CustomersGUI");

				roomsGUI = new RoomsGUI();
				contentPanel.add(roomsGUI, "RoomsGUI");

				staffGUI = new StaffGUI();
				contentPanel.add(staffGUI, "StaffGUI");
			}
			catch(IllegalArgumentException ie){
				
			}
			}
		});

		logoutButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				optionsPanel.removeAll();
				contentPanel.removeAll();
				activitiesGUI = null;
				customersGUI = null;
				roomsGUI = null;
				staffGUI = null;
				CardLayout cl = (CardLayout) (frame.getContentPane()
						.getLayout());
				cl.show(frame.getContentPane(), "Login Wrapper");
				loginGUI.clearLoginFields();
			}
		});
		
	}

	public static void main(String[] args) {
		// Schedule a job for the event-dispatching thread:
		// creating and showing this application's GUI.
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {

				try {
					UIManager.setLookAndFeel(UIManager
							.getSystemLookAndFeelClassName());
				} catch (ClassNotFoundException | InstantiationException
						| IllegalAccessException
						| UnsupportedLookAndFeelException e) {
					e.printStackTrace();
				}

				MainGUI window = new MainGUI();
				instance = window;
				window.initialize();
			}
		});
	}

	private void login() {
		int rank = loginGUI.login();

		// int rank = 1;

		switch (rank) {
		// 0 = invalid login, avoid doing anything
		case (0):
			JOptionPane.showMessageDialog(null,
					"Invalid username and password.", "Login Failed",
					JOptionPane.INFORMATION_MESSAGE);
		throw new IllegalArgumentException();
		// manager
		case (1):
			System.out.println("Manager logged in, message from MainGUI");
			optionsPanel.add(activitvitiesButton);
			optionsPanel.add(customersButton);
			optionsPanel.add(roomsButton);
			optionsPanel.add(staffButtom);
			break;
		// receptionist / secretary
		case (2):
			System.out
					.println("Receptionist/Secretary logged in, message from MainGUI");
			optionsPanel.add(activitvitiesButton);
			optionsPanel.add(customersButton);
			optionsPanel.add(roomsButton);
			break;
		// instructor
		case (3):
			System.out.println("Instructor logged in, message from MainGUI");
			optionsPanel.add(activitvitiesButton);
			optionsPanel.add(customersButton);
			break;
		// customer
		case (4):
			System.out.println("Customer logged in, message from MainGUI");
			optionsPanel.add(activitvitiesButton);
		}
		
		optionsPanel.add(logoutButton);
		activitvitiesButton.setBackground(Color.decode("#FFFFFF"));
		logoutButton.setBackground(Color.decode("#F0F0F0"));
		staffButtom.setBackground(Color.decode("#F0F0F0"));
		customersButton.setBackground(Color.decode("#F0F0F0"));
		roomsButton.setBackground(Color.decode("#F0F0F0"));
		// if rank is in range 1 - 4 show contentPane
		if (rank > 0 && rank < 5) {
			CardLayout cl = (CardLayout) (frame.getContentPane().getLayout());
			cl.show(frame.getContentPane(), "Menu Wrapper");
		}
		
		}
	
		public static MainGUI getInstance(){
			return instance;
		}
		public void showRoomBooking(Agency agency){
			CardLayout cl = (CardLayout) contentPanel.getLayout();
			cl.show(contentPanel, "RoomsGUI");
			roomsButton.setBackground(Color.decode("#FFFFFF"));
			customersButton.setBackground(Color.decode("#F0F0F0"));
			
			roomsGUI.setAgencyID(agency);
		}

		public void fillInstructorHireTable() {
			activitiesGUI.fillInstructorTable();
			
		}

}

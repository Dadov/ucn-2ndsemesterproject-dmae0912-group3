package GUI;

import java.awt.BorderLayout;
import java.awt.CardLayout;
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

		// TODO moving buttons up so can work with them generally
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

		// TODO
		// activitiesGUI = new ActivitiesGUI();
		// contentPanel.add(activitiesGUI, "ActivitiesGUI");
		//
		// customersGUI = new CustomersGUI();
		// contentPanel.add(customersGUI, "CustomersGUI");
		//
		// roomsGUI = new RoomsGUI();
		// contentPanel.add(roomsGUI, "RoomsGUI");
		//
		// staffGUI = new StaffGUI();
		// contentPanel.add(staffGUI, "StaffGUI");

		activitvitiesButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				activitiesGUI = new ActivitiesGUI();
				// setting userID
				activitiesGUI.setUserID(loginGUI.getUserID());
				contentPanel.add(activitiesGUI, "ActivitiesGUI");
				System.out.println("Logged user ID: " + loginGUI.getUserID());
				CardLayout cl = (CardLayout) contentPanel.getLayout();
				cl.show(contentPanel, "ActivitiesGUI");
				// activitiesGUI.initialize();
			}
		});

		customersButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				customersGUI = new CustomersGUI();
				contentPanel.add(customersGUI, "CustomersGUI");
				CardLayout cl = (CardLayout) contentPanel.getLayout();
				cl.show(contentPanel, "CustomersGUI");
				// customersGUI.initialize();
			}
		});

		roomsButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				roomsGUI = new RoomsGUI();
				contentPanel.add(roomsGUI, "RoomsGUI");
				CardLayout cl = (CardLayout) contentPanel.getLayout();
				cl.show(contentPanel, "RoomsGUI");
				// roomsGUI.initialize();
			}
		});

		staffButtom.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				staffGUI = new StaffGUI();
				contentPanel.add(staffGUI, "StaffGUI");
				CardLayout cl = (CardLayout) contentPanel.getLayout();
				cl.show(contentPanel, "StaffGUI");
				// staffGUI.initialize();
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
			}
		});

		loginGUI.loginButton().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				login();
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
				window.initialize();
			}
		});
	}

	private void login() {
		
		// TODO
		// activitiesGUI = new ActivitiesGUI();
		// contentPanel.add(activitiesGUI, "ActivitiesGUI");

		// customersGUI = new CustomersGUI();
		// contentPanel.add(customersGUI, "CustomersGUI");
		//
		// roomsGUI = new RoomsGUI();
		// contentPanel.add(roomsGUI, "RoomsGUI");
		//
		// staffGUI = new StaffGUI();
		// contentPanel.add(staffGUI, "StaffGUI");

		// set user ID for activities GUI
		// activitiesGUI.setUserID(loginGUI.getUserID());

		// perform login and set 'rank' of logged in user to restrict amount of
		// functionality available
		int rank = loginGUI.login();
		// IFDAOCustomer dbc = new DAOCustomer();
		// IFDAOStaff dbs = new DAOStaff();



		// int rank = 1;

		switch (rank) {
		// 0 = invalid login, avoid doing anything
		case (0):
			JOptionPane.showMessageDialog(null,
					"Invlaid username and password.", "Login Failed",
					JOptionPane.INFORMATION_MESSAGE);
			break;
		// manager
		case (1):
			System.out.println("Manager logged in, message from MainGUI");
			// activitvitiesButton = new MenuButton("Activities");
			// customersButton = new MenuButton("Customers");
			// roomsButton = new MenuButton("Rooms");
			// staffButtom = new MenuButton("Staff");
			optionsPanel.add(activitvitiesButton);
			optionsPanel.add(customersButton);
			optionsPanel.add(roomsButton);
			optionsPanel.add(staffButtom);
			// activitiesGUI.setManager((Manager)
			// dbs.getStaff(loginGUI.getUserID(), true));
			break;
		// receptionist / secretary
		case (2):
			System.out
					.println("Receptionist/Secretary logged in, message from MainGUI");
			// activitvitiesButton = new MenuButton("Activities");
			// customersButton = new MenuButton("Customers");
			// roomsButton = new MenuButton("Rooms");
			optionsPanel.add(activitvitiesButton);
			optionsPanel.add(customersButton);
			optionsPanel.add(roomsButton);
			// activitiesGUI.setOtherStaff(dbs.getStaff(loginGUI.getUserID(),
			// true));
			break;
		// instructor
		case (3):
			System.out.println("Instructor logged in, message from MainGUI");
			// activitvitiesButton = new MenuButton("Activities");
			// customersButton = new MenuButton("Customers");
			optionsPanel.add(activitvitiesButton);
			optionsPanel.add(customersButton);
			// activitiesGUI.setInstructor((Instructor)
			// dbs.getStaff(loginGUI.getUserID(), true));
			break;
		// customer
		case (4):
			System.out.println("Customer logged in, message from MainGUI");
			// activitvitiesButton = new MenuButton("Activities");
			optionsPanel.add(activitvitiesButton);
			// activitiesGUI.setCustomer(dbc.getCustomer(loginGUI.getUserID(),true));
		}
		optionsPanel.add(logoutButton);
		// if rank is in range 1 - 4 show contentPane
		if (rank > 0 && rank < 5) {
			CardLayout cl = (CardLayout) (frame.getContentPane().getLayout());
			cl.show(frame.getContentPane(), "Menu Wrapper");
		}
	}

}

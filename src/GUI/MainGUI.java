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
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

public class MainGUI {
	private JFrame frame;
	private JPanel menuWrapper;
	private JPanel loginWrapper;
	private LoginGUI loginGUI;
	private ActivitiesGUI activitiesGUI;
	private CustomersGUI customersGUI;
	private RoomsGUI roomsGUI;
	private StaffGUI staffGUI;
	private JPanel optionsPanel;
	private JPanel contentPanel;
	private JPanel optionsBorder;
	private JPanel contentBorder;
	private int rank;

	public MainGUI() {

	}

	/**
	 * Create the GUI and show it. For thread safety, this method should be
	 * invoked from the event-dispatching thread.
	 */
	private void setRank(int rank) {
		this.rank = rank;
	}

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
		frame.getContentPane().add(loginWrapper, "name_8641309153887");
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
		frame.getContentPane().add(menuWrapper, "name_10074865581856");

		optionsBorder = new JPanel();
		optionsBorder.setBorder(new TitledBorder(UIManager
				.getBorder("TitledBorder.border"), "Options",
				TitledBorder.LEADING, TitledBorder.TOP, null, null));
		menuWrapper.add(optionsBorder);

		optionsPanel = new JPanel();
		optionsBorder.add(optionsPanel);
		optionsPanel.setPreferredSize(new Dimension(150, 550));

		// TODO case switch dependent on user here
		JButton activitvitiesButton;
		JButton customersButton;
		JButton roomsButton;
		JButton staffButtom;
		// TODO only printing the messages so the switch/case is working
		// int rank = 0;

		loginGUI.loginButton().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setRank(loginGUI.login());
			}
		});
		switch (rank) {
		// TODO invalid login
		case (0):
			System.out.println("Invalid login, Error message from MainGUI");
			break;
		// manager
		case (1):
			System.out.println("Manager logged in, message from MainGUI");
			activitvitiesButton = new MenuButton("Activities");
			customersButton = new MenuButton("Customers");
			roomsButton = new MenuButton("Rooms");
			staffButtom = new MenuButton("Staff");
			optionsPanel.add(activitvitiesButton);
			optionsPanel.add(customersButton);
			optionsPanel.add(roomsButton);
			optionsPanel.add(staffButtom);
			break;
		// receptionist / secretary
		case (2):
			System.out
					.println("Receptionist/Secretary logged in, message from MainGUI");
			activitvitiesButton = new MenuButton("Activities");
			customersButton = new MenuButton("Customers");
			roomsButton = new MenuButton("Rooms");
			optionsPanel.add(activitvitiesButton);
			optionsPanel.add(customersButton);
			optionsPanel.add(roomsButton);
			break;
		// instructor
		case (3):
			System.out.println("Instructor logged in, message from MainGUI");
			activitvitiesButton = new MenuButton("Activities");
			customersButton = new MenuButton("Customers");
			optionsPanel.add(activitvitiesButton);
			optionsPanel.add(customersButton);
			break;
		// customer
		case (4):
			System.out.println("Customer logged in, message from MainGUI");
			activitvitiesButton = new MenuButton("Activities");
			optionsPanel.add(activitvitiesButton);
		}

		// TODO part to be deleted after switch(rank) will be working
		// JButton activitvitiesButton = new MenuButton("Activities");
		// optionsPanel.add(activitvitiesButton);
		//
		// JButton customersButton = new MenuButton("Customers");
		// optionsPanel.add(customersButton);
		//
		// JButton roomsButton = new MenuButton("Rooms");
		// optionsPanel.add(roomsButton);
		//
		// JButton staffButtom = new MenuButton("Staff");
		// optionsPanel.add(staffButtom);
		// TODO end of part to be deleted

		contentBorder = new JPanel();
		contentBorder.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null,
				null));
		menuWrapper.add(contentBorder);

		contentPanel = new JPanel();
		contentBorder.add(contentPanel);
		contentPanel.setPreferredSize(new Dimension(780, 565));

	}

	public static void main(String[] args) {
		// Schedule a job for the event-dispatching thread:
		// creating and showing this application's GUI.
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				MainGUI window = new MainGUI();
				window.initialize();
			}
		});
	}
}

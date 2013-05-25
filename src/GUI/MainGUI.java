package GUI;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;

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

package GUI;

import java.awt.CardLayout;
import java.awt.Dimension;

import javax.swing.JFrame;

public class MainGUI {
	private JFrame frame;
	private LoginGUI loginGUI;

	public MainGUI() {

	}

	/**
	 * Create the GUI and show it. For thread safety, this method should be
	 * invoked from the event-dispatching thread.
	 */
	private void initialize() {
		// Create and set up the window.
		JFrame jFrame = new JFrame("Skynet");
		jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		Dimension dimension = new Dimension(1000, 600);
		jFrame.setMinimumSize(dimension);
		System.out.println(jFrame.getSize());

		// Display the window.
		jFrame.pack();
		jFrame.getContentPane().setLayout(new CardLayout(0, 0));
		jFrame.setVisible(true);

		loginGUI = new LoginGUI();
		jFrame.getContentPane().add(loginGUI, "loginGUI");
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
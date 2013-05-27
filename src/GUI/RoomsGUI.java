package GUI;

import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;

public class RoomsGUI extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel roomsWrapper;
	private JPanel roomBookingPanel;

	public RoomsGUI() {
		setPreferredSize(new Dimension(780, 535));

		roomsWrapper = new JPanel();
		roomsWrapper.setPreferredSize(new Dimension(780, 535));
		add(roomsWrapper);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		roomsWrapper.add(tabbedPane);

		roomBookingPanel = new JPanel();
		roomBookingPanel.setPreferredSize(new Dimension(780, 500));
		tabbedPane.addTab("RoomBooking", null, roomBookingPanel, null);

		JPanel cancelRoomBookingPanel = new JPanel();
		cancelRoomBookingPanel.setPreferredSize(new Dimension(780, 500));
		tabbedPane.addTab("Cancel Room Booking", null, cancelRoomBookingPanel,
				null);

	}

}

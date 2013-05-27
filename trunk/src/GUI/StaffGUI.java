package GUI;

import java.awt.Dimension;

import javax.swing.JPanel;

public class StaffGUI extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel staffWrapper;

	public StaffGUI() {

		staffWrapper = new JPanel();
		staffWrapper.setPreferredSize(new Dimension(780, 535));
		add(staffWrapper);
		// TODO placeholder should be replaced by SWING class
	}

}

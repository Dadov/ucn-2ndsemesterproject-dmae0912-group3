package GUI;

import java.awt.Dimension;

import javax.swing.JPanel;

public class ActivitiesGUI extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel activitiesWrapper;

	public ActivitiesGUI() {

		activitiesWrapper = new JPanel();
		activitiesWrapper.setPreferredSize(new Dimension(780, 535));
		add(activitiesWrapper);
		// TODO placeholder should be replaced by SWING class
	}

}

package GUI;

import java.awt.Dimension;

import javax.swing.JPanel;

public class CustomersGUI extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel customersWrapper;

	public CustomersGUI() {

		customersWrapper = new JPanel();
		customersWrapper.setPreferredSize(new Dimension(780, 535));
		add(customersWrapper);
		// TODO placeholder should be replaced by SWING class
	}

}

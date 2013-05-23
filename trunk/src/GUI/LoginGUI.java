package GUI;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Point;

import javax.swing.JPanel;

public class LoginGUI extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public LoginGUI() {
		initialize();
	}

	private void initialize() {
		setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		JPanel login = new JPanel();
		FlowLayout flowLayout = (FlowLayout) login.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		login.setPreferredSize(new Dimension(100, 100));
		login.setLocation(new Point(57, 231));
		login.setMinimumSize(new Dimension(100, 100));
		login.setMaximumSize(new Dimension(100, 100));
		add(login);

		JPanel news = new JPanel();
		add(news);
	}
}

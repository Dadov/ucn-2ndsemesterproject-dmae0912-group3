package GUI;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;

public class LoginGUI extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel loginPanel;
	private JPanel newsPanel;
	private Component rigidArea_1;
	// text pane as a placeholder
	private JTextPane textPane;
	private JPanel newsBorder;
	private Component rigidArea;
	private JPanel loginBorder;
	private JTextField loginUsername;
	private JTextField loginPassword;
	private JButton loginButton;

	public LoginGUI() {
		setAlignmentX(Component.LEFT_ALIGNMENT);
		setPreferredSize(new Dimension(1000, 600));
		setBorder(UIManager.getBorder("Button.border"));
		initialize();
	}

	private void initialize() {
		setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		loginPanel = new JPanel();
		loginPanel.setPreferredSize(new Dimension(150, 580));
		loginPanel.setAlignmentY(Component.BOTTOM_ALIGNMENT);
		loginPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
		add(loginPanel);
		loginPanel.setLayout(new BoxLayout(loginPanel, BoxLayout.Y_AXIS));

		rigidArea = Box.createRigidArea(new Dimension(20, 20));
		rigidArea.setPreferredSize(new Dimension(0, 450));
		loginPanel.add(rigidArea);

		loginBorder = new JPanel();
		loginBorder.setAlignmentX(Component.RIGHT_ALIGNMENT);
		loginBorder.setBorder(new TitledBorder(UIManager
				.getBorder("TitledBorder.border"), "Login",
				TitledBorder.LEADING, TitledBorder.TOP, null, null));
		loginPanel.add(loginBorder);
		loginBorder.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		loginUsername = new JTextField();
		loginBorder.add(loginUsername);
		loginUsername.setColumns(10);

		loginPassword = new JTextField();
		loginBorder.add(loginPassword);
		loginPassword.setColumns(10);

		loginButton = new JButton("Login");
		loginButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		loginButton.setActionCommand("Login");
		loginBorder.add(loginButton);

		rigidArea_1 = Box.createRigidArea(new Dimension(50, 0));
		rigidArea_1.setPreferredSize(new Dimension(0, 0));
		add(rigidArea_1);

		newsBorder = new JPanel();
		newsBorder.setBorder(new TitledBorder(UIManager
				.getBorder("TitledBorder.border"), "News and shit",
				TitledBorder.LEADING, TitledBorder.TOP, null, null));
		add(newsBorder);

		newsPanel = new JPanel();
		newsBorder.add(newsPanel);
		newsPanel.setPreferredSize(new Dimension(750, 545));

		textPane = new JTextPane();
		textPane.setPreferredSize(new Dimension(750, 500));
		textPane.setText("Welcome to Morocco Holiday center. \n\n\n Some news might be here. \n\n\n Login swaggot!");
		newsPanel.add(textPane);
	}
}

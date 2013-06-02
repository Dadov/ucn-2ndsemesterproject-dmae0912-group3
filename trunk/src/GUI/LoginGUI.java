package GUI;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Arrays;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;

import Controllers.CustomersCtr;
import Controllers.StaffCtr;
import Models.Customer;
import Models.Instructor;
import Models.Manager;
import Models.Receptionist;
import Models.Secretary;
import Models.Staff;

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
	private JPanel loginBorder;
	private JTextField loginID;
	private JPasswordField loginPassword;
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
		loginPanel.setPreferredSize(new Dimension(150, 530));
		loginPanel.setAlignmentY(Component.BOTTOM_ALIGNMENT);
		loginPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
		add(loginPanel);
		loginPanel.setLayout(new BoxLayout(loginPanel, BoxLayout.Y_AXIS));

		loginBorder = new JPanel();
		loginBorder.setAlignmentX(Component.RIGHT_ALIGNMENT);
		loginBorder.setBorder(new TitledBorder(UIManager
				.getBorder("TitledBorder.border"), "Login",
				TitledBorder.LEADING, TitledBorder.TOP, null, null));
		loginPanel.add(loginBorder);
		loginBorder.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		loginID = new JTextField();
		loginBorder.add(loginID);
		loginID.setColumns(10);

		loginPassword = new JPasswordField();
		loginBorder.add(loginPassword);
		loginPassword.setColumns(10);

		loginButton = new JButton("Login");
		loginButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				login();
			}
		});
		loginButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		loginButton.setActionCommand("Login");
		loginBorder.add(loginButton);

		rigidArea_1 = Box.createRigidArea(new Dimension(50, 0));
		rigidArea_1.setPreferredSize(new Dimension(0, 0));
		add(rigidArea_1);

		newsBorder = new JPanel();
		newsBorder.setBorder(new TitledBorder(UIManager
				.getBorder("TitledBorder.border"),
				"Place for news and announcements",
				TitledBorder.LEADING, TitledBorder.TOP, null, null));
		add(newsBorder);

		newsPanel = new JPanel();
		newsBorder.add(newsPanel);
		newsPanel.setPreferredSize(new Dimension(780, 500));

		textPane = new JTextPane();
		textPane.setPreferredSize(new Dimension(750, 500));
		textPane.setEditable(false);
		textPane.setText("Welcome to Morocco Holiday center. \n\n\n Place for news. \n\n\n Enter your ID and password to login.");
		newsPanel.add(textPane);
	}

	public int getUserID() {
		return Integer.parseInt(loginID.getText());
	}

	public int login() {
		int ID = 0;
		char[] password = null;

		try {
			ID = Integer.parseInt(loginID.getText());
			password = loginPassword.getPassword();
		} catch (NumberFormatException e1) {
			JOptionPane.showMessageDialog(null,
					"Invlaid username and password.", "Login Failed",
					JOptionPane.INFORMATION_MESSAGE);
		}
		int rank = 0;

		try {
			StaffCtr staffCtr = new StaffCtr();
			Staff staff = staffCtr.getEmployee(ID);
			if (staff != null
					&& Arrays.equals(staff.getPassword().toCharArray(),
							password)) {
				if (staff instanceof Manager) {
					rank = 1;
				} else if (staff instanceof Secretary
						|| staff instanceof Receptionist) {
					rank = 2;
				} else if (staff instanceof Instructor) {
					rank = 3;
				}
			}

		} catch (Exception e) {
			System.out
					.println("Exception from PersonCtr, trying to get Staff.");
			e.printStackTrace();
			e.getMessage();
		}

		try {
			CustomersCtr customersCtr = new CustomersCtr();
			Customer customer = customersCtr.getCustomer(ID);

			if (customer != null
					&& Arrays.equals(customer.getPassword().toCharArray(),
							password)) {
				rank = 4;
			}

		} catch (Exception e) {
			System.out
					.println("Exception from PersonCtr, trying to get Customer");
			e.printStackTrace();
			e.getMessage();
		}

		// if none of the above pass, return 0 == invalid login
		System.out.println("Rank to be returned from PersonCtr " + rank);
		return rank;
	}

	public JButton loginButton() {
		return loginButton;
	}
}

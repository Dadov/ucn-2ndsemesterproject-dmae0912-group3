package GUI;

import java.awt.Dimension;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;

public class ActivitiesGUI extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel activitiesWrapper;
	private JTable table;

	public ActivitiesGUI() {

		activitiesWrapper = new JPanel();
		activitiesWrapper.setPreferredSize(new Dimension(780, 535));
		add(activitiesWrapper);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		activitiesWrapper.add(tabbedPane);
		
		JPanel bookActivityTab = new JPanel();
		bookActivityTab.setPreferredSize(new Dimension(780, 500));
		tabbedPane.addTab("Book Activity", null, bookActivityTab, null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setPreferredSize(new Dimension(750,450));
		bookActivityTab.add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null},
			},
			new String[] {
				"ID", "Activity", "Date", "Time"
			}
		));
		table.getColumnModel().getColumn(0).setPreferredWidth(73);
		scrollPane.setViewportView(table);
		
		JButton buttonCreate = new JButton("Create new Activity");
		bookActivityTab.add(buttonCreate);
		
		JButton buttonJoin = new JButton("Join Activity");
		bookActivityTab.add(buttonJoin);
		
		
		JPanel hireInstructorTab = new JPanel();
		hireInstructorTab.setPreferredSize(new Dimension(780, 500));
		tabbedPane.addTab("Hire Instructor", null, hireInstructorTab, null);
		
		JPanel activityTab = new JPanel();
		tabbedPane.addTab("Activity", null, activityTab, null);
		// TODO placeholder should be replaced by SWING class
	}

}

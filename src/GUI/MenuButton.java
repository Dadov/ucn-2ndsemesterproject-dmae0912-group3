package GUI;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

public class MenuButton extends JButton {
	private static final long serialVersionUID = 1L;

	public MenuButton(String label) {
		super(label);
		this.setBackground(Color.decode("#F0F0F0"));
		this.setForeground(Color.decode("#000000"));
		this.setPreferredSize(new Dimension(140, 35));
		this.setMargin(new Insets(1, 1, 1, 1));
		this.setHorizontalAlignment(SwingConstants.CENTER);
		this.setFont(new Font("Tahoma", Font.BOLD, 12));
		this.setFocusPainted(false);
		this.addActionListener(new MainMenuButtonGenericAction());
		this.setBorder(new CompoundBorder(new LineBorder(Color.BLACK),
				new EmptyBorder(1, 1, 1, 1)));
		this.setContentAreaFilled(false);
		this.setOpaque(true);
	}

	public class MainMenuButtonGenericAction implements ActionListener {

		public MainMenuButtonGenericAction() {

		}

		public void actionPerformed(ActionEvent e) {
			JButton button = (JButton) e.getSource();
			if (button.getParent() != null) {
				for (Component component : button.getParent().getComponents()) {
					if (component instanceof JButton)
						component.setBackground(Color.decode("#F0F0F0"));
				}
			}

			button.setBackground(Color.decode("#FFFFFF"));
		}
	}
}
package View;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JComboBox;

import java.awt.Color;
import java.awt.Font;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import Config.ColorUse;

public class JpDoiXung extends JPanel {

	private static final long serialVersionUID = 1L;
	private JComboBox comboBox;
	/**
	 * Create the panel.
	 */
	public JpDoiXung() {
		setLayout(null);
		this.setBounds(0, 30, 300, 60);
		comboBox = new JComboBox();
		comboBox.setForeground(Color.decode(ColorUse.RED));
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"DES", "AES", "Hill", "Vengency"}));
		comboBox.setFont(new Font("Tahoma", Font.BOLD, 14));
		comboBox.setBounds(0, 0, 300, 60);
		add(comboBox);
	}
	public JComboBox getComboBox() {
		return comboBox;
	}
	public void setComboBox(JComboBox comboBox) {
		this.comboBox = comboBox;
	}
	
}

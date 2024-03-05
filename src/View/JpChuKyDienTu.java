package View;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JPanel;

import Config.ColorUse;

public class JpChuKyDienTu extends JPanel {

	private static final long serialVersionUID = 1L;
	private JComboBox comboBox;
	/**
	 * Create the panel.
	 */
	public JpChuKyDienTu() {
		setLayout(null);
		this.setBounds(0, 30, 300, 60);
		
		comboBox = new JComboBox();
		comboBox.setForeground(Color.decode(ColorUse.RED));
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"MD5", "SHA-1", "SHA-224", "SHA-256", "SHA-384", "SHA-512"}));
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
	public void chooseListener(ActionListener listener) {
		comboBox.addActionListener(listener);
	}
}

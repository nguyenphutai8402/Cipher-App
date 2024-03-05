package View;

import javax.swing.JPanel;

import Config.ColorUse;

import javax.swing.JComboBox;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;

public class JpBatDoiXung extends JPanel {

	private static final long serialVersionUID = 1L;
	private JComboBox comboBox;
	/**
	 * Create the panel.
	 */
	
	public JpBatDoiXung() {
		setLayout(null);
		this.setBounds(0, 30, 300, 60);
		
		comboBox = new JComboBox();
		comboBox.setForeground(Color.decode(ColorUse.RED));
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"RSA", "RSA/ECB/PKCS1Padding", "RSA/ECB/OAEPWithSHA-1AndMGF1Padding"}));
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
	public void comboBoxListener(ActionListener listener) {
		comboBox.addActionListener(listener);
	}
}

package View;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;

import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.SwingConstants;

import Config.ColorUse;

public class JpYC_CKDT extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField jtfHashFile;
	/**
	 * Create the panel.
	 */
	public JpYC_CKDT() {
		setLayout(null);
		setBackground(Color.decode(ColorUse.BLUE));
		setBounds(0, 0, 300, 571);
		
		JLabel jlHashInput = new JLabel("Hash File");
		jlHashInput.setForeground(Color.decode(ColorUse.LIGHT_BLUE));
		jlHashInput.setFont(new Font("Tahoma", Font.BOLD, 14));
		jlHashInput.setHorizontalAlignment(SwingConstants.RIGHT);
		jlHashInput.setBounds(10, 10, 75, 30);
		add(jlHashInput);
		
		jtfHashFile = new JTextField();
		jtfHashFile.setBackground(Color.decode(ColorUse.WHITE));
		jtfHashFile.setForeground(Color.decode(ColorUse.RED));
		jtfHashFile.setFont(new Font("Tahoma", Font.PLAIN, 14));
		jtfHashFile.setBounds(95, 10, 195, 30);
		add(jtfHashFile);
		jtfHashFile.setColumns(10);
	}
	public JTextField getJtfHashFile() {
		return jtfHashFile;
	}
	public void setJtfHashFile(JTextField jtfHashFile) {
		this.jtfHashFile = jtfHashFile;
	}
	public void hashInputListener(KeyListener Listener) {
		jtfHashFile.addKeyListener(Listener);
	}
}

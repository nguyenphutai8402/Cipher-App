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

public class JpYC_Vigenere extends JPanel {


	private static final long serialVersionUID = 1L;
	private JTextField jtfKey;
	private JTextField jtfNewKey;
	private JPanel jpNewKeyAndIV;
	private JToggleButton jtMaHoa;
	private JToggleButton jtGiaiMa;
	private JButton jbtNewKey;
	private JComboBox cbLang;
	/**
	 * Create the panel.
	 */
	public JpYC_Vigenere() {
		setBackground(Color.decode(ColorUse.BLUE));
		setLayout(null);
		setBounds(0, 0, 300, 571);
		ButtonGroup bg_EnDe = new ButtonGroup();
		
		jtMaHoa = new JToggleButton("Mã Hóa");
		jtMaHoa.setForeground(Color.decode(ColorUse.RED));
		jtMaHoa.setFont(new Font("Tahoma", Font.BOLD, 14));
		jtMaHoa.setBounds(10, 10, 130, 30);
		add(jtMaHoa);
		
		jtGiaiMa = new JToggleButton("Giải Mã");
		jtGiaiMa.setForeground(Color.decode(ColorUse.RED));
		jtGiaiMa.setFont(new Font("Tahoma", Font.BOLD, 14));
		jtGiaiMa.setBounds(160, 10, 130, 30);
		add(jtGiaiMa);
		
		bg_EnDe.add(jtMaHoa);
		bg_EnDe.add(jtGiaiMa);
		
		JLabel jlKey = new JLabel("Key");
		jlKey.setForeground(Color.decode(ColorUse.LIGHT_BLUE));
		jlKey.setFont(new Font("Tahoma", Font.BOLD, 14));
		jlKey.setHorizontalAlignment(SwingConstants.RIGHT);
		jlKey.setBounds(10, 50, 75, 30);
		add(jlKey);
		
		jtfKey = new JTextField();
		jtfKey.setBackground(Color.decode(ColorUse.WHITE));
		jtfKey.setForeground(Color.decode(ColorUse.RED));
		jtfKey.setFont(new Font("Tahoma", Font.PLAIN, 14));
		jtfKey.setBounds(95, 50, 195, 30);
		add(jtfKey);
		jtfKey.setColumns(10);
		
		JLabel jlLang = new JLabel("LANG");
		jlLang.setForeground(Color.decode(ColorUse.LIGHT_BLUE));
		jlLang.setHorizontalAlignment(SwingConstants.RIGHT);
		jlLang.setFont(new Font("Tahoma", Font.BOLD, 14));
		jlLang.setBounds(10, 90, 75, 30);
		add(jlLang);
		
		cbLang = new JComboBox();
		cbLang.setForeground(Color.decode(ColorUse.RED));
		cbLang.setModel(new DefaultComboBoxModel(new String[] {"ENG", "VIE"}));
		cbLang.setFont(new Font("Tahoma", Font.PLAIN, 14));
		cbLang.setBounds(95, 90, 195, 30);
		add(cbLang);
		
		jpNewKeyAndIV = new JPanel();
		jpNewKeyAndIV.setBackground(Color.decode(ColorUse.BLUE));
		jpNewKeyAndIV.setBounds(0, 130, 300, 135);
		jpNewKeyAndIV.setLayout(null);
		add(jpNewKeyAndIV);
		
		JLabel jlNewKey = new JLabel("New Key");
		jlNewKey.setForeground(Color.decode(ColorUse.LIGHT_BLUE));
		jlNewKey.setHorizontalAlignment(SwingConstants.RIGHT);
		jlNewKey.setFont(new Font("Tahoma", Font.BOLD, 14));
		jlNewKey.setBounds(10, 0, 61, 30);
		jpNewKeyAndIV.add(jlNewKey);
		
		jtfNewKey = new JTextField();
		jtfNewKey.setBackground(Color.decode(ColorUse.WHITE));
		jtfNewKey.setForeground(Color.decode(ColorUse.RED));
		jtfNewKey.setFont(new Font("Tahoma", Font.PLAIN, 14));
		jtfNewKey.setColumns(10);
		jtfNewKey.setBounds(95, 0, 195, 30);
		jpNewKeyAndIV.add(jtfNewKey);
		
		JLabel jlNote = new JLabel("*Lưu ý: Hãy ghi nhớ Key");
		jlNote.setForeground(Color.decode(ColorUse.LIGHT_BLUE));
		jlNote.setHorizontalAlignment(SwingConstants.LEFT);
		jlNote.setFont(new Font("Tahoma", Font.ITALIC, 11));
		jlNote.setBounds(10, 30, 280, 14);
		jpNewKeyAndIV.add(jlNote);
		
		jbtNewKey = new JButton("Create Key");
		jbtNewKey.setForeground(Color.decode(ColorUse.RED));
		jbtNewKey.setFont(new Font("Tahoma", Font.BOLD, 14));
		jbtNewKey.setBounds(10, 50, 280, 30);
		jpNewKeyAndIV.add(jbtNewKey);
	}
	public JTextField getJtfKey() {
		return jtfKey;
	}
	public void setJtfKey(JTextField jtfKey) {
		this.jtfKey = jtfKey;
	}
	public JTextField getJtfNewKey() {
		return jtfNewKey;
	}
	public void setJtfNewKey(JTextField jtfNewKey) {
		this.jtfNewKey = jtfNewKey;
	}
	public JPanel getJpNewKeyAndIV() {
		return jpNewKeyAndIV;
	}
	public void setJpNewKeyAndIV(JPanel jpNewKeyAndIV) {
		this.jpNewKeyAndIV = jpNewKeyAndIV;
	}
	public JToggleButton getJtMaHoa() {
		return jtMaHoa;
	}
	public void setJtMaHoa(JToggleButton jtMaHoa) {
		this.jtMaHoa = jtMaHoa;
	}
	public JToggleButton getJtGiaiMa() {
		return jtGiaiMa;
	}
	public void setJtGiaiMa(JToggleButton jtGiaiMa) {
		this.jtGiaiMa = jtGiaiMa;
	}
	public JButton getJbtNewKey() {
		return jbtNewKey;
	}
	public void setJbtNewKey(JButton jbtNewKey) {
		this.jbtNewKey = jbtNewKey;
	}
	public JComboBox getCbLang() {
		return cbLang;
	}
	public void setCbLang(JComboBox cbLang) {
		this.cbLang = cbLang;
	}
	public void createKey(ActionListener listener) {
		jbtNewKey.addActionListener(listener);
	}
	public void enAndDeListener(ActionListener listener) {
		jtMaHoa.addActionListener(listener);
		jtGiaiMa.addActionListener(listener);
	}
	public void keyInputListener(KeyListener listener) {
		jtfKey.addKeyListener(listener);
	}
	public void langListener(ActionListener listener) {
		cbLang.addActionListener(listener);
	}
}

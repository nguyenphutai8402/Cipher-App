package View;

import javax.swing.JPanel;
import javax.swing.JToggleButton;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyListener;

import javax.swing.JLabel;
import javax.swing.SwingConstants;

import Config.ColorUse;

import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;

public class JpYC_DES_AES extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField jtfKey;
	private JTextField jtfNewKey;
	private JPanel jpNewKeyAndIV;
	private JToggleButton jtMaHoa;
	private JToggleButton jtGiaiMa;
	private JLabel jlIV;
	private JTextField jtfIV;
	private JTextField jtfNewIV;
	private JButton jbtNewKey;
	private JComboBox cbMode;
	private JComboBox cbPaddingTwo;
	private JComboBox cbPaddingOne;
	/**
	 * Create the panel.
	 */
	public JpYC_DES_AES() {
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
		
		jlIV = new JLabel("IV");
		jlIV.setForeground(Color.decode(ColorUse.LIGHT_BLUE));
		jlIV.setHorizontalAlignment(SwingConstants.RIGHT);
		jlIV.setFont(new Font("Tahoma", Font.BOLD, 14));
		jlIV.setBounds(10, 90, 75, 30);
		add(jlIV);
		
		jtfIV = new JTextField();
		jtfIV.setBackground(Color.decode(ColorUse.WHITE));
		jtfIV.setForeground(Color.decode(ColorUse.RED));
		jtfIV.setFont(new Font("Tahoma", Font.PLAIN, 14));
		jtfIV.setColumns(10);
		jtfIV.setBounds(95, 90, 195, 30);
		add(jtfIV);
		
		JLabel jlMode = new JLabel("MODE");
		jlMode.setForeground(Color.decode(ColorUse.LIGHT_BLUE));
		jlMode.setHorizontalAlignment(SwingConstants.RIGHT);
		jlMode.setFont(new Font("Tahoma", Font.BOLD, 14));
		jlMode.setBounds(10, 130, 75, 30);
		add(jlMode);
		
		cbMode = new JComboBox();
		cbMode.setForeground(Color.decode(ColorUse.RED));
		cbMode.setModel(new DefaultComboBoxModel(new String[] {"ECB", "CBC", "CFB", "OFB", "CTR"}));
		cbMode.setFont(new Font("Tahoma", Font.PLAIN, 14));
		cbMode.setBounds(95, 130, 195, 30);
		add(cbMode);
		
		JLabel jlPadding = new JLabel("PADDING");
		jlPadding.setForeground(Color.decode(ColorUse.LIGHT_BLUE));
		jlPadding.setHorizontalAlignment(SwingConstants.RIGHT);
		jlPadding.setFont(new Font("Tahoma", Font.BOLD, 14));
		jlPadding.setBounds(10, 170, 76, 30);
		add(jlPadding);
		
		cbPaddingTwo = new JComboBox();
		cbPaddingTwo.setForeground(Color.decode(ColorUse.RED));
		cbPaddingTwo.setModel(new DefaultComboBoxModel(new String[] {"NoPadding", "PKCS5Padding"}));
		cbPaddingTwo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		cbPaddingTwo.setBounds(95, 170, 195, 30);
		add(cbPaddingTwo);
		
		cbPaddingOne = new JComboBox();
		cbPaddingOne.setForeground(Color.decode(ColorUse.RED));
		cbPaddingOne.setModel(new DefaultComboBoxModel(new String[] {"NoPadding"}));
		cbPaddingOne.setFont(new Font("Tahoma", Font.PLAIN, 14));
		cbPaddingOne.setBounds(95, 170, 195, 30);
		add(cbPaddingOne);
		
		jpNewKeyAndIV = new JPanel();
		jpNewKeyAndIV.setBackground(Color.decode(ColorUse.BLUE));
		jpNewKeyAndIV.setBounds(0, 210, 300, 135);
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
		
		JLabel jlNewIV = new JLabel("New IV");
		jlNewIV.setForeground(Color.decode(ColorUse.LIGHT_BLUE));
		jlNewIV.setHorizontalAlignment(SwingConstants.RIGHT);
		jlNewIV.setFont(new Font("Tahoma", Font.BOLD, 14));
		jlNewIV.setBounds(10, 40, 61, 30);
		jpNewKeyAndIV.add(jlNewIV);
		
		jtfNewIV = new JTextField();
		jtfNewIV.setBackground(Color.decode(ColorUse.WHITE));
		jtfNewIV.setForeground(Color.decode(ColorUse.RED));
		jtfNewIV.setFont(new Font("Tahoma", Font.PLAIN, 14));
		jtfNewIV.setColumns(10);
		jtfNewIV.setBounds(95, 40, 195, 30);
		jpNewKeyAndIV.add(jtfNewIV);
		
		JLabel jlNote = new JLabel("*Lưu ý: Hãy ghi nhớ bộ Key và IV");
		jlNote.setForeground(Color.decode(ColorUse.LIGHT_BLUE));
		jlNote.setHorizontalAlignment(SwingConstants.LEFT);
		jlNote.setFont(new Font("Tahoma", Font.ITALIC, 11));
		jlNote.setBounds(10, 70, 280, 14);
		jpNewKeyAndIV.add(jlNote);
		
		jbtNewKey = new JButton("Create Key And IV");
		jbtNewKey.setForeground(Color.decode(ColorUse.RED));
		jbtNewKey.setFont(new Font("Tahoma", Font.BOLD, 14));
		jbtNewKey.setBounds(10, 90, 280, 30);
		jpNewKeyAndIV.add(jbtNewKey);
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
	public void newKeyAndIVListener(ActionListener listener) {
		jtGiaiMa.addActionListener(listener);
		jtMaHoa.addActionListener(listener);
	}
	public JTextField getJtfKey() {
		return jtfKey;
	}
	public void setJtfKey(JTextField jtfKey) {
		this.jtfKey = jtfKey;
	}
	public JLabel getJlIV() {
		return jlIV;
	}
	public void setJlIV(JLabel jlIV) {
		this.jlIV = jlIV;
	}
	public JTextField getJtfIV() {
		return jtfIV;
	}
	public void setJtfIV(JTextField jtfIV) {
		this.jtfIV = jtfIV;
	}
	public JTextField getJtfNewIV() {
		return jtfNewIV;
	}
	public void setJtfNewIV(JTextField jtfNewIV) {
		this.jtfNewIV = jtfNewIV;
	}
	public JComboBox getCbMode() {
		return cbMode;
	}
	public void setCbMode(JComboBox cbMode) {
		this.cbMode = cbMode;
	}
	public JComboBox getCbPaddingTwo() {
		return cbPaddingTwo;
	}
	public void setCbPaddingTwo(JComboBox cbPaddingTwo) {
		this.cbPaddingTwo = cbPaddingTwo;
	}
	public JComboBox getCbPaddingOne() {
		return cbPaddingOne;
	}
	public void setCbPaddingOne(JComboBox cbPaddingOne) {
		this.cbPaddingOne = cbPaddingOne;
	}
	public void modeListener(ActionListener listener) {
		cbMode.addActionListener(listener);
	}
	public void checkYCListen(KeyListener keyAdapter) {
		jtfKey.addKeyListener(keyAdapter);
		jtfIV.addKeyListener(keyAdapter);
	}
	public void checkChangeMode(ActionListener listener) {
		cbMode.addActionListener(listener);
	}
	public void chooseCipherForDES(ActionListener listener) {
		cbMode.addActionListener(listener);
		cbPaddingOne.addActionListener(listener);
		cbPaddingTwo.addActionListener(listener);
	}
	public void createKeyAndIVListener(ActionListener listener) {
		jbtNewKey.addActionListener(listener);
	}
}

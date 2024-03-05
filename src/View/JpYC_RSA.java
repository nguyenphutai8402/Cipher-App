package View;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JToggleButton;
import javax.swing.SwingConstants;

import Config.ColorUse;

import javax.swing.JScrollPane;
import java.awt.Component;
import javax.swing.ScrollPaneConstants;
import javax.swing.JTextArea;

public class JpYC_RSA extends JPanel {

	private static final long serialVersionUID = 1L;
	private JPanel jpNewKeyAndIV;
	private JToggleButton jtMaHoa;
	private JToggleButton jtGiaiMa;
	private JLabel jlPrKey;
	private JButton jbtNewKey;
	private JScrollPane jspPukey;
	private JTextArea jtaPuKey;
	private JScrollPane jspPrKey;
	private JTextArea jtaPrKey;
	private JScrollPane jspNewPu;
	private JTextArea jtaNewPu;
	private JScrollPane jspNewPr;
	private JTextArea jtaNewPr;
	/**
	 * Create the panel.
	 */
	public JpYC_RSA() {
		setBackground(Color.decode(ColorUse.BLUE));
		setLayout(null);
		setBounds(0, 0, 300, 572);
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
		
		JLabel jlPuKey = new JLabel("<html>Public Key</html>");
		jlPuKey.setForeground(Color.decode(ColorUse.LIGHT_BLUE));
		jlPuKey.setVerticalAlignment(SwingConstants.TOP);
		jlPuKey.setHorizontalAlignment(SwingConstants.LEFT);
		jlPuKey.setFont(new Font("Tahoma", Font.BOLD, 14));
		jlPuKey.setBounds(10, 50, 60, 41);
		add(jlPuKey);
		
		jtaPuKey = new JTextArea(5, 20); // 5 hàng, 20 cột
		jtaPuKey.setBackground(Color.decode(ColorUse.WHITE));
		jtaPuKey.setForeground(Color.decode(ColorUse.RED));
		jtaPuKey.setFont(new Font("Tahoma", Font.PLAIN, 14));
        jtaPuKey.setLineWrap(true);
        jtaPuKey.setWrapStyleWord(true);
	
		jspPukey = new JScrollPane(jtaPuKey);
		jspPukey.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		jspPukey.setBounds(80, 50, 210, 80);
		add(jspPukey);
		
		jlPrKey = new JLabel("<html>Private Key</html>");
		jlPrKey.setForeground(Color.decode(ColorUse.LIGHT_BLUE));
		jlPrKey.setVerticalAlignment(SwingConstants.TOP);
		jlPrKey.setHorizontalAlignment(SwingConstants.LEFT);
		jlPrKey.setFont(new Font("Tahoma", Font.BOLD, 14));
		jlPrKey.setBounds(10, 140, 60, 41);
		add(jlPrKey);
		
		jspPrKey = new JScrollPane((Component) null);
		jspPrKey.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		jspPrKey.setBounds(80, 140, 210, 80);
		add(jspPrKey);
		
		jtaPrKey = new JTextArea(5, 20);
		jtaPrKey.setBackground(Color.decode(ColorUse.WHITE));
		jtaPrKey.setForeground(Color.decode(ColorUse.RED));
		jtaPrKey.setWrapStyleWord(true);
		jtaPrKey.setLineWrap(true);
		jtaPrKey.setFont(new Font("Tahoma", Font.PLAIN, 14));
		jspPrKey.setViewportView(jtaPrKey);
		
		jpNewKeyAndIV = new JPanel();
		jpNewKeyAndIV.setBackground(Color.decode(ColorUse.BLUE));
		jpNewKeyAndIV.setBounds(0, 231, 300, 341);
		jpNewKeyAndIV.setLayout(null);
		add(jpNewKeyAndIV);
		
		JLabel jlNewPuKey = new JLabel("<html>New Public Key</html>");
		jlNewPuKey.setForeground(Color.decode(ColorUse.LIGHT_BLUE));
		jlNewPuKey.setVerticalAlignment(SwingConstants.TOP);
		jlNewPuKey.setHorizontalAlignment(SwingConstants.LEFT);
		jlNewPuKey.setFont(new Font("Tahoma", Font.BOLD, 14));
		jlNewPuKey.setBounds(10, 0, 61, 51);
		jpNewKeyAndIV.add(jlNewPuKey);
		
		JLabel jlNote = new JLabel("*Lưu ý: Hãy ghi nhớ bộ Public Key và Private Key");
		jlNote.setForeground(Color.decode(ColorUse.LIGHT_BLUE));
		jlNote.setHorizontalAlignment(SwingConstants.LEFT);
		jlNote.setFont(new Font("Tahoma", Font.ITALIC, 11));
		jlNote.setBounds(10, 170, 280, 14);
		jpNewKeyAndIV.add(jlNote);
		
		jbtNewKey = new JButton("Create");
		jbtNewKey.setForeground(Color.decode(ColorUse.RED));
		jbtNewKey.setFont(new Font("Tahoma", Font.BOLD, 14));
		jbtNewKey.setBounds(10, 185, 280, 30);
		jpNewKeyAndIV.add(jbtNewKey);
		
		jspNewPu = new JScrollPane((Component) null);
		jspNewPu.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		jspNewPu.setBounds(80, 0, 210, 80);
		jpNewKeyAndIV.add(jspNewPu);
		
		jtaNewPu = new JTextArea(5, 20);
		jtaNewPu.setBackground(Color.decode(ColorUse.WHITE));
		jtaNewPu.setForeground(Color.decode(ColorUse.RED));
		jtaNewPu.setWrapStyleWord(true);
		jtaNewPu.setLineWrap(true);
		jtaNewPu.setFont(new Font("Tahoma", Font.PLAIN, 14));
		jspNewPu.setViewportView(jtaNewPu);
		
		JLabel jlNewPrKey = new JLabel("<html>New Private Key</html>");
		jlNewPrKey.setForeground(Color.decode(ColorUse.LIGHT_BLUE));
		jlNewPrKey.setVerticalAlignment(SwingConstants.TOP);
		jlNewPrKey.setHorizontalAlignment(SwingConstants.LEFT);
		jlNewPrKey.setFont(new Font("Tahoma", Font.BOLD, 14));
		jlNewPrKey.setBounds(10, 90, 61, 51);
		jpNewKeyAndIV.add(jlNewPrKey);
		
		jspNewPr = new JScrollPane((Component) null);
		jspNewPr.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		jspNewPr.setBounds(80, 90, 210, 80);
		jpNewKeyAndIV.add(jspNewPr);
		
		jtaNewPr = new JTextArea(5, 20);
		jtaNewPr.setBackground(Color.decode(ColorUse.WHITE));
		jtaNewPr.setForeground(Color.decode(ColorUse.RED));
		jtaNewPr.setWrapStyleWord(true);
		jtaNewPr.setLineWrap(true);
		jtaNewPr.setFont(new Font("Tahoma", Font.PLAIN, 14));
		jspNewPr.setViewportView(jtaNewPr);
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
	public JScrollPane getJspPukey() {
		return jspPukey;
	}
	public void setJspPukey(JScrollPane jspPukey) {
		this.jspPukey = jspPukey;
	}
	public JTextArea getJtaPuKey() {
		return jtaPuKey;
	}
	public void setJtaPuKey(JTextArea jtaPuKey) {
		this.jtaPuKey = jtaPuKey;
	}
	public JScrollPane getJspPrKey() {
		return jspPrKey;
	}
	public void setJspPrKey(JScrollPane jspPrKey) {
		this.jspPrKey = jspPrKey;
	}
	public JTextArea getJtaPrKey() {
		return jtaPrKey;
	}
	public void setJtaPrKey(JTextArea jtaPrKey) {
		this.jtaPrKey = jtaPrKey;
	}
	public JScrollPane getJspNewPu() {
		return jspNewPu;
	}
	public void setJspNewPu(JScrollPane jspNewPu) {
		this.jspNewPu = jspNewPu;
	}
	public JTextArea getJtaNewPu() {
		return jtaNewPu;
	}
	public void setJtaNewPu(JTextArea jtaNewPu) {
		this.jtaNewPu = jtaNewPu;
	}
	public JScrollPane getJspNewPr() {
		return jspNewPr;
	}
	public void setJspNewPr(JScrollPane jspNewPr) {
		this.jspNewPr = jspNewPr;
	}
	public JTextArea getJtaNewPr() {
		return jtaNewPr;
	}
	public void setJtaNewPr(JTextArea jtaNewPr) {
		this.jtaNewPr = jtaNewPr;
	}
	public void enAndDEListenner(ActionListener listener) {
		jtMaHoa.addActionListener(listener);
		jtGiaiMa.addActionListener(listener);
	}
	public void createPairKey(ActionListener listener) {
		jbtNewKey.addActionListener(listener);
	}
	public void checkPairKey(KeyListener listener) {
		jtaPuKey.addKeyListener(listener);
		jtaPrKey.addKeyListener(listener);
	}
}
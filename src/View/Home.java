package View;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.ButtonUI;

import Config.ColorUse;
import ControllerOfView.MenuController;

import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.Color;
import javax.swing.JComboBox;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JToggleButton;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

import java.awt.Font;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;

import javax.swing.JLabel;

public class Home extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private ButtonGroup buttonGroupPT;
	private ButtonGroup textAndFileBG;
	private JToggleButton jtDoiXung;
	private JToggleButton jtBatDoiXung;
	private JToggleButton jtHash;
	private JToggleButton jtCKDienTu;
	private JTextArea jtaOutput;
	private JTextArea jtaInput;
	private JPanel jpChoose;
	private JPanel jpYeuCauChinh;
	
	private JpDoiXung jpDoiXung;
	private JpBatDoiXung jpBatDoiXung;
	private JpHash jpHash;
	private JpChuKyDienTu jpChuKyDienTu;
	private JpYC_DES_AES jpYC_DES_AES;
	private JpYC_Vigenere jpYC_Vigenere;
	private JpYC_RSA jpYC_RSA;
	private JpYC_CKDT jpYC_CKDT;
	private JToggleButton jtText;
	private JToggleButton jtFile;
	private JButton jbSave;
	private JButton jbCopy;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {

	}

	/**
	 * Create the frame.
	 */
	class CopyText implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			String textToCopy = jtaOutput.getText();
			if (!textToCopy.isEmpty()) {
	            Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
	            clipboard.setContents(new StringSelection(textToCopy), null);
	        }
		}
	}
	public Home() {
		this.jpDoiXung = new JpDoiXung();
		jpDoiXung.getComboBox().setModel(new DefaultComboBoxModel(new String[] {"DES", "AES", "Twofish", "Vigenere", "Hill"}));
		this.jpBatDoiXung = new JpBatDoiXung();
		this.jpHash = new JpHash();
		this.jpChuKyDienTu = new JpChuKyDienTu(); 
		this.jpYC_DES_AES = new JpYC_DES_AES();
		this.jpYC_Vigenere = new JpYC_Vigenere();
		this.jpYC_RSA = new JpYC_RSA();
		this.jpYC_CKDT = new JpYC_CKDT();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 25, 1200, 700);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setBackground(Color.decode(ColorUse.BROWN));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		JPanel jpPhuongThuc = new JPanel();
		jpPhuongThuc.setBackground(Color.decode(ColorUse.BLUE));
		jpPhuongThuc.setBounds(0, 0, 295, 661);
		contentPane.add(jpPhuongThuc);
		jpPhuongThuc.setLayout(null);
		
		JLabel jlPhuongThuc = new JLabel("Phương Thức");
		jlPhuongThuc.setForeground(Color.decode(ColorUse.LIGHT_BLUE));
		jlPhuongThuc.setHorizontalAlignment(SwingConstants.CENTER);
		jlPhuongThuc.setFont(new Font("Tahoma", Font.BOLD, 14));
		jlPhuongThuc.setBounds(0, 0, 295, 30);
		jpPhuongThuc.add(jlPhuongThuc);
		
		buttonGroupPT = new ButtonGroup();
		
		jtDoiXung = new JToggleButton("Đối Xứng");
		jtDoiXung.setForeground(Color.decode(ColorUse.RED));
		jtDoiXung.setFont(new Font("Tahoma", Font.BOLD, 14));
		jtDoiXung.setHorizontalAlignment(SwingConstants.LEFT);
		jtDoiXung.setBounds(0, 30, 295, 30);
		jpPhuongThuc.add(jtDoiXung);
		
		jtBatDoiXung = new JToggleButton("Bất Đối Xứng");
		jtBatDoiXung.setForeground(Color.decode(ColorUse.RED));
		jtBatDoiXung.setFont(new Font("Tahoma", Font.BOLD, 14));
		jtBatDoiXung.setHorizontalAlignment(SwingConstants.LEFT);
		jtBatDoiXung.setBounds(0, 60, 295, 30);
		jpPhuongThuc.add(jtBatDoiXung);
		
		jtHash = new JToggleButton("Hash");
		jtHash.setForeground(Color.decode(ColorUse.RED));
		jtHash.setFont(new Font("Tahoma", Font.BOLD, 14));
		jtHash.setHorizontalAlignment(SwingConstants.LEFT);
		jtHash.setBounds(0, 90, 295, 30);
		jpPhuongThuc.add(jtHash);
		
		jtCKDienTu = new JToggleButton("Chữ Ký Điện Tử");
		jtCKDienTu.setForeground(Color.decode(ColorUse.RED));
		jtCKDienTu.setFont(new Font("Tahoma", Font.BOLD, 14));
		jtCKDienTu.setHorizontalAlignment(SwingConstants.LEFT);
		jtCKDienTu.setBounds(0, 120, 295, 30);
		jpPhuongThuc.add(jtCKDienTu);
		
		buttonGroupPT.add(jtDoiXung);
		buttonGroupPT.add(jtBatDoiXung);
		buttonGroupPT.add(jtHash);
		buttonGroupPT.add(jtCKDienTu);
		
		JPanel jpYeuCau = new JPanel();
		jpYeuCau.setBackground(Color.decode(ColorUse.BLUE));
		jpYeuCau.setBounds(300, 0, 300, 661);
		contentPane.add(jpYeuCau);
		jpYeuCau.setLayout(null);
		jpYeuCau.add(jpDoiXung);
		jpYeuCau.add(jpBatDoiXung);
		jpYeuCau.add(jpHash);
		jpYeuCau.add(jpChuKyDienTu);
		
		JLabel jlGiaiThuat = new JLabel("Giải Thuật");
		jlGiaiThuat.setForeground(Color.decode(ColorUse.LIGHT_BLUE));
		jlGiaiThuat.setHorizontalAlignment(SwingConstants.CENTER);
		jlGiaiThuat.setFont(new Font("Tahoma", Font.BOLD, 14));
		jlGiaiThuat.setBounds(0, 0, 300, 30);
		jpYeuCau.add(jlGiaiThuat);
		
		jpChoose = new JPanel();
		jpChoose.setLayout(null);
		jpChoose.setBackground(new Color(128, 255, 255));
		jpChoose.setBounds(0, 30, 300, 60);
		jpYeuCau.add(jpChoose);
		
		
		jpYeuCauChinh = new JPanel();
		jpYeuCauChinh.setBackground(Color.decode(ColorUse.BLUE));
		jpYeuCauChinh.setBounds(0, 90, 300, 571);
		jpYeuCau.add(jpYeuCauChinh);
		jpYeuCauChinh.setLayout(null);
		jpYeuCauChinh.add(jpYC_DES_AES);
		jpYeuCauChinh.add(jpYC_Vigenere);
		jpYeuCauChinh.add(jpYC_RSA);
		jpYeuCauChinh.add(jpYC_CKDT);
		
				
		JPanel jpInput = new JPanel();
		jpInput.setBackground(Color.decode(ColorUse.BLUE));
		jpInput.setBounds(605, 0, 579, 328);
		contentPane.add(jpInput);
		jpInput.setLayout(null);
		
		JLabel jlInput = new JLabel("Input");
		jlInput.setForeground(Color.decode(ColorUse.LIGHT_BLUE));
		jlInput.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		jlInput.setBounds(0, 0, 62, 30);
		jpInput.add(jlInput);
		
		textAndFileBG = new ButtonGroup();
		
		jtText = new JToggleButton("Text");
		jtText.setForeground(Color.decode(ColorUse.RED));
		jtText.setBackground(new Color(255, 0, 0));
		jtText.setFont(new Font("Tahoma", Font.BOLD, 14));
		jtText.setBounds(435, 0, 70, 30);
		jpInput.add(jtText);
		
		
		jtFile = new JToggleButton("File");
		jtFile.setForeground(Color.decode(ColorUse.RED));
		jtFile.setFont(new Font("Tahoma", Font.BOLD, 14));
		jtFile.setBounds(509, 0, 70, 30);
		jpInput.add(jtFile);
		
		textAndFileBG.add(jtText);
		textAndFileBG.add(jtFile);
		
		jtaInput = new JTextArea(5, 20); // 5 hàng, 20 cột
		jtaInput.setBackground(Color.decode(ColorUse.WHITE));
		jtaInput.setForeground(Color.decode(ColorUse.RED));
		jtaInput.setFont(new Font("Tahoma", Font.PLAIN, 14));
        jtaInput.setLineWrap(true);
        jtaInput.setWrapStyleWord(true);

        JScrollPane jspInput = new JScrollPane(jtaInput);
        jspInput.setBounds(0, 30, 579, 298);
        jspInput.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        jpInput.add(jspInput);
        
		JPanel jpOutput = new JPanel();
		jpOutput.setBackground(Color.decode(ColorUse.BLUE));
		jpOutput.setBounds(605, 333, 579, 327);
		contentPane.add(jpOutput);
		jpOutput.setLayout(null);
		
		JLabel jlOutput = new JLabel("Output");
		jlOutput.setForeground(Color.decode(ColorUse.LIGHT_BLUE));
		jlOutput.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		jlOutput.setBounds(0, 0, 61, 30);
		jpOutput.add(jlOutput);
		
		jbCopy = new JButton("Copy");
		jbCopy.setForeground(Color.decode(ColorUse.RED));
		jbCopy.setFont(new Font("Tahoma", Font.BOLD, 14));
		jbCopy.setBounds(435, 0, 70, 30);
		jpOutput.add(jbCopy);
		copyText(new CopyText());
		
		jbSave = new JButton("Save");
		jbSave.setForeground(Color.decode(ColorUse.RED));
		jbSave.setFont(new Font("Tahoma", Font.BOLD, 14));
		jbSave.setBounds(509, 0, 70, 30);
		jpOutput.add(jbSave);
		
		jtaOutput = new JTextArea(5, 20); // 5 hàng, 20 cột
		jtaOutput.setBackground(Color.decode(ColorUse.WHITE));
		jtaOutput.setForeground(Color.decode(ColorUse.RED));
		jtaOutput.setEditable(false);
		jtaOutput.setFont(new Font("Tahoma", Font.PLAIN, 14));
        jtaOutput.setLineWrap(true);
        jtaOutput.setWrapStyleWord(true);

        JScrollPane jspOutput = new JScrollPane(jtaOutput);
        jspOutput.setBounds(0, 30, 579, 298);
        jspOutput.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        jpOutput.add(jspOutput);
        
	}
	public ButtonGroup getButtonGroupPT() {
		return buttonGroupPT;
	}
	public void setButtonGroupPT(ButtonGroup buttonGroupPT) {
		this.buttonGroupPT = buttonGroupPT;
	}
	public JToggleButton getJtDoiXung() {
		return jtDoiXung;
	}
	public void setJtDoiXung(JToggleButton jtDoiXung) {
		this.jtDoiXung = jtDoiXung;
	}
	public JToggleButton getJtBatDoiXung() {
		return jtBatDoiXung;
	}
	public void setJtBatDoiXung(JToggleButton jtBatDoiXung) {
		this.jtBatDoiXung = jtBatDoiXung;
	}
	public JToggleButton getJtHash() {
		return jtHash;
	}
	public void setJtHash(JToggleButton jtHash) {
		this.jtHash = jtHash;
	}
	public JToggleButton getJtCKDienTu() {
		return jtCKDienTu;
	}
	public void setJtCKDienTu(JToggleButton jtCKDienTu) {
		this.jtCKDienTu = jtCKDienTu;
	}
	public JTextArea getJtaOutput() {
		return jtaOutput;
	}
	public void setJtaOutput(JTextArea jtaOutput) {
		this.jtaOutput = jtaOutput;
	}
	public JTextArea getJtaInput() {
		return jtaInput;
	}
	public void setJtaInput(JTextArea jtaInput) {
		this.jtaInput = jtaInput;
	}
	public JToggleButton getJtText() {
		return jtText;
	}
	public void setJtText(JToggleButton jtText) {
		this.jtText = jtText;
	}
	public JToggleButton getJtFile() {
		return jtFile;
	}
	public void setJtFile(JToggleButton jtFile) {
		this.jtFile = jtFile;
	}
	public JButton getJbSave() {
		return jbSave;
	}
	public void setJbSave(JButton jtSave) {
		this.jbSave = jtSave;
	}
	public JButton getJbCopy() {
		return jbCopy;
	}
	public void setJbCopy(JButton jtCopy) {
		this.jbCopy = jtCopy;
	}
	public JPanel getJpChoose() {
		return jpChoose;
	}
	public void setJpChoose(JPanel jpChoose) {
		this.jpChoose = jpChoose;
	}
	public JPanel getJpYeuCauChinh() {
		return jpYeuCauChinh;
	}
	public void setJpYeuCauChinh(JPanel jpYeuCauChinh) {
		this.jpYeuCauChinh = jpYeuCauChinh;
	}
	public JpDoiXung getJpDoiXung() {
		return jpDoiXung;
	}
	public void setJpDoiXung(JpDoiXung jpDoiXung) {
		this.jpDoiXung = jpDoiXung;
	}
	public JpBatDoiXung getJpBatDoiXung() {
		return jpBatDoiXung;
	}
	public void setJpBatDoiXung(JpBatDoiXung jpBatDoiXung) {
		this.jpBatDoiXung = jpBatDoiXung;
	}
	public JpHash getJpHash() {
		return jpHash;
	}
	public void setJpHash(JpHash jpHash) {
		this.jpHash = jpHash;
	}
	public JpChuKyDienTu getJpChuKyDienTu() {
		return jpChuKyDienTu;
	}
	public void setJpChuKyDienTu(JpChuKyDienTu jpChuKyDienTu) {
		this.jpChuKyDienTu = jpChuKyDienTu;
	}
	public JpYC_DES_AES getJpYC_DES_AES() {
		return jpYC_DES_AES;
	}
	public void setJpYC_DES_AES(JpYC_DES_AES jpYC_DES_AES) {
		this.jpYC_DES_AES = jpYC_DES_AES;
	}
	public JpYC_Vigenere getJpYC_Vigenere() {
		return jpYC_Vigenere;
	}
	public void setJp_Vigenere(JpYC_Vigenere jpYC_Vigenere) {
		this.jpYC_Vigenere = jpYC_Vigenere;
	}
	public JpYC_RSA getJpYC_RSA() {
		return jpYC_RSA;
	}
	public void setJpYC_RSA(JpYC_RSA jpYC_RSA) {
		this.jpYC_RSA = jpYC_RSA;
	}
	
	public JpYC_CKDT getJpYC_CKDT() {
		return jpYC_CKDT;
	}
	public void setJpYC_CKDT(JpYC_CKDT jpYC_CKDT) {
		this.jpYC_CKDT = jpYC_CKDT;
	}
	public void menuTBGroupListener(ActionListener listener) {
		jtDoiXung.addActionListener(listener);
		jtBatDoiXung.addActionListener(listener);
		jtHash.addActionListener(listener);
		jtCKDienTu.addActionListener(listener);
	}
	public void yCChinhListener(ActionListener listener) {
		jpDoiXung.getComboBox().addActionListener(listener);
	}
	public void inputTextListener(KeyListener listerner) {
		jtaInput.addKeyListener(listerner);
	}
	public void chooseCipherForDES(ActionListener listener) {
		jpYC_DES_AES.getJtMaHoa().addActionListener(listener);
		jpYC_DES_AES.getJtGiaiMa().addActionListener(listener);
		jpYC_DES_AES.getCbMode().addActionListener(listener);
		jpYC_DES_AES.getCbPaddingOne().addActionListener(listener);
		jpYC_DES_AES.getCbPaddingTwo().addActionListener(listener);
	}
	public void enAndDe(ActionListener listenner) {
		jpYC_DES_AES.getJtMaHoa().addActionListener(listenner);
		jpYC_DES_AES.getJtGiaiMa().addActionListener(listenner);
	}
	public void textFileListener(ActionListener litstener) {
		jtText.addActionListener(litstener);
		jtFile.addActionListener(litstener);
	}
	public void textListener(ActionListener listener) {
		jtText.addActionListener(listener);
	}
	public void canSave(ActionListener listener) {
		jtFile.addActionListener(listener);
	}
	public void saveEnAnDeFile(ActionListener listener) {
		jbSave.addActionListener(listener);
	}
	public void copyText(ActionListener listener) {
		jbCopy.addActionListener(listener);
	}
	
}


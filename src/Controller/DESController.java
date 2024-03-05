package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Base64;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.SwingUtilities;

import ControllerOfView.ControllerYCKeyAndIv;
import Model.DES;
import Utils.Check;
import View.Home;

public class DESController {
	private Home homeView;
	private DES dESModel;
	private ControllerYCKeyAndIv utils;
	private boolean changeKeyAndIVForText;
	private boolean checkEnFile;
	private String sourceFilePathEn;
	private String sourceFilePathDe;
	
	public DESController(Home homeView) {
		this.homeView = homeView;
		this.dESModel = new DES();
		this.utils = new ControllerYCKeyAndIv(homeView);
		this.changeKeyAndIVForText = false;
		this.sourceFilePathEn = "";
		this.sourceFilePathDe = "";
		Listeners();
	}
	
	public void Listeners() {
		homeView.chooseCipherForDES(new ChooseCipherForDES());
		homeView.getJpYC_DES_AES().checkYCListen(new checkYCKeyAndIVChangeListener());
		homeView.inputTextListener(new inputListener());
		homeView.enAndDe(new enAndDeForText());
		
		homeView.canSave(new canSave());
		homeView.getJpYC_DES_AES().checkYCListen(new checkKIVOfEnFile());
		homeView.getJpYC_DES_AES().chooseCipherForDES(new checkMPOfEnFile());
		homeView.saveEnAnDeFile(new saveEnAndDe());
		homeView.enAndDe(new enAndDe());
	}
	
	public void encrypt(String key, String iv, JComboBox cbMode, JComboBox cbPaddingOne, JComboBox cbPaddingTwo, String inputText) {
		dESModel.generateSecretKeyFromBase64(key);
		dESModel.generateIVFromBase64(iv);
		String mode = cbMode.getSelectedItem().toString();
		dESModel.setMode(mode);
		String padding = "";
		if(cbMode.getSelectedIndex() == 4) {
			padding = cbPaddingOne.getSelectedItem().toString();
			dESModel.setPadding(padding);
			
		}else {
			padding = cbPaddingTwo.getSelectedItem().toString();
			dESModel.setPadding(padding);
		}
		if(changeKeyAndIVForText) {
			SwingUtilities.invokeLater(new Runnable() {
			    public void run() {
			    	String outputText = "";
			    	try {
						outputText = dESModel.encrypt(inputText);
						homeView.getJtaOutput().setText(outputText);
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
						outputText = "Có lỗi xảy ra, Hoặc padding không đáp ứng";
						homeView.getJtaOutput().setText(outputText);
					}
			    }
			});
		}else {
			String outputText = "";
			outputText = utils.notifyKeyAndIv();
			homeView.getJtaOutput().setText(outputText);
		}
	}
	public void decrypt(String key, String iv, JComboBox cbMode, JComboBox cbPaddingOne, JComboBox cbPaddingTwo, String inputText) {
		dESModel.generateSecretKeyFromBase64(key);
		dESModel.generateIVFromBase64(iv);
		String mode = cbMode.getSelectedItem().toString();
		dESModel.setMode(mode);
		String padding = "";
		if(cbMode.getSelectedIndex() == 4) {
			padding = cbPaddingOne.getSelectedItem().toString();
			dESModel.setPadding(padding);
			
		}else {
			padding = cbPaddingTwo.getSelectedItem().toString();
			dESModel.setPadding(padding);
		}
		if(changeKeyAndIVForText) {
			SwingUtilities.invokeLater(new Runnable() {
			    public void run() {
			    	String outputText = "";
			    	try {
						outputText = dESModel.decrypt(inputText);
						homeView.getJtaOutput().setText(outputText);
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
						outputText = "Có lỗi xảy ra, Hoặc padding không đáp ứng";
						homeView.getJtaOutput().setText(outputText);
					}
			    }
			});
		}else {
			String outputText = "";
			outputText = utils.notifyKeyAndIv();
			homeView.getJtaOutput().setText(outputText);
		}
	}
	class ChooseCipherForDES implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			JToggleButton jtDoiXung = homeView.getJtDoiXung();
			JComboBox cbDoiXung = homeView.getJpDoiXung().getComboBox();
			JToggleButton jtText = homeView.getJtText();
			dESModel.setKey(null);
			dESModel.setIv(null);
			JToggleButton jtMaHoa = homeView.getJpYC_DES_AES().getJtMaHoa();
			JToggleButton jtGiaiMa = homeView.getJpYC_DES_AES().getJtGiaiMa();
			String key = homeView.getJpYC_DES_AES().getJtfKey().getText();
			String iv = homeView.getJpYC_DES_AES().getJtfIV().getText();
			String mode = "";
			JComboBox cbMode = homeView.getJpYC_DES_AES().getCbMode();
			String padding = "";
			JComboBox cbPaddingOne = homeView.getJpYC_DES_AES().getCbPaddingOne();
			JComboBox cbPaddingTwo = homeView.getJpYC_DES_AES().getCbPaddingTwo();
			changeKeyAndIVForText = utils.checkKeyAndIv();
			String inputText = homeView.getJtaInput().getText();
			if(jtDoiXung.isSelected() && cbDoiXung.getSelectedIndex() == 0 && jtText.isSelected() && jtMaHoa.isSelected()) {
				encrypt(key, iv, cbMode, cbPaddingOne, cbPaddingTwo, inputText);
			}else if(jtDoiXung.isSelected() && cbDoiXung.getSelectedIndex() == 0 && jtText.isSelected() && jtGiaiMa.isSelected()){
				decrypt(key, iv, cbMode, cbPaddingOne, cbPaddingTwo, inputText);
			}
		}
	}
	class checkYCKeyAndIVChangeListener implements KeyListener{
		@Override
		public void keyReleased(KeyEvent e) {
			// TODO Auto-generated method stub
			JToggleButton jtDoiXung = homeView.getJtDoiXung();
			JComboBox cbDoiXung = homeView.getJpDoiXung().getComboBox();
			JToggleButton jtText = homeView.getJtText();
			dESModel.setKey(null);
			dESModel.setIv(null);
			JToggleButton jtMaHoa = homeView.getJpYC_DES_AES().getJtMaHoa();
			JToggleButton jtGiaiMa = homeView.getJpYC_DES_AES().getJtGiaiMa();
			String key = homeView.getJpYC_DES_AES().getJtfKey().getText();
			String iv = homeView.getJpYC_DES_AES().getJtfIV().getText();
			String mode = "";
			JComboBox cbMode = homeView.getJpYC_DES_AES().getCbMode();
			String padding = "";
			JComboBox cbPaddingOne = homeView.getJpYC_DES_AES().getCbPaddingOne();
			JComboBox cbPaddingTwo = homeView.getJpYC_DES_AES().getCbPaddingTwo();
			changeKeyAndIVForText = utils.checkKeyAndIv();
			String inputText = homeView.getJtaInput().getText();
			if(jtDoiXung.isSelected() && cbDoiXung.getSelectedIndex() == 0 && jtText.isSelected() && jtMaHoa.isSelected()) {
				encrypt(key, iv, cbMode, cbPaddingOne, cbPaddingTwo, inputText);
			}else if(jtDoiXung.isSelected() && cbDoiXung.getSelectedIndex() == 0 && jtText.isSelected() && jtGiaiMa.isSelected()){
				decrypt(key, iv, cbMode, cbPaddingOne, cbPaddingTwo, inputText);
			}
		}

		@Override
		public void keyTyped(KeyEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void keyPressed(KeyEvent e) {
			// TODO Auto-generated method stub
			
		}
	}
	
	class inputListener implements KeyListener{

		@Override
		public void keyTyped(KeyEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void keyPressed(KeyEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void keyReleased(KeyEvent e) {
			// TODO Auto-generated method stub
			JToggleButton jtDoiXung = homeView.getJtDoiXung();
			JComboBox cbDoiXung = homeView.getJpDoiXung().getComboBox();
			JToggleButton jtText = homeView.getJtText();
			dESModel.setKey(null);
			dESModel.setIv(null);
			JToggleButton jtMaHoa = homeView.getJpYC_DES_AES().getJtMaHoa();
			JToggleButton jtGiaiMa = homeView.getJpYC_DES_AES().getJtGiaiMa();
			String key = homeView.getJpYC_DES_AES().getJtfKey().getText();
			String iv = homeView.getJpYC_DES_AES().getJtfIV().getText();
			String mode = "";
			JComboBox cbMode = homeView.getJpYC_DES_AES().getCbMode();
			String padding = "";
			JComboBox cbPaddingOne = homeView.getJpYC_DES_AES().getCbPaddingOne();
			JComboBox cbPaddingTwo = homeView.getJpYC_DES_AES().getCbPaddingTwo();
			changeKeyAndIVForText = utils.checkKeyAndIv();
			String inputText = homeView.getJtaInput().getText();
			if(jtDoiXung.isSelected() && cbDoiXung.getSelectedIndex() == 0 && jtText.isSelected() && jtMaHoa.isSelected()) {
				encrypt(key, iv, cbMode, cbPaddingOne, cbPaddingTwo, inputText);
			}else if(jtDoiXung.isSelected() && cbDoiXung.getSelectedIndex() == 0 && jtText.isSelected() && jtGiaiMa.isSelected()){
				decrypt(key, iv, cbMode, cbPaddingOne, cbPaddingTwo, inputText);
			}
		}
	}
	class enAndDeForText implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			JToggleButton jtDoiXung = homeView.getJtDoiXung();
			JComboBox cbDoiXung = homeView.getJpDoiXung().getComboBox();
			JToggleButton jtText = homeView.getJtText();
			JToggleButton jtFile = homeView.getJtFile();
			changeKeyAndIVForText = utils.checkKeyAndIv();
			if(jtDoiXung.isSelected() && cbDoiXung.getSelectedIndex() == 0 && jtText.isSelected()) {
				if(changeKeyAndIVForText) {
					homeView.getJtaInput().setText("");
					homeView.getJtaOutput().setText("");
				}else {
					homeView.getJtaInput().setText("");
					homeView.getJtaOutput().setText(utils.notifyKeyAndIv());
				}
			}
		}
	}
	
	public void enFile() {
		JToggleButton jtDoiXung = homeView.getJtDoiXung();
		JComboBox cbDoiXung = homeView.getJpDoiXung().getComboBox();
		JToggleButton jtFile = homeView.getJtFile();
        JToggleButton jtMaHoa = homeView.getJpYC_DES_AES().getJtMaHoa();
		JToggleButton jtGiaiMa = homeView.getJpYC_DES_AES().getJtGiaiMa();
		JButton jtSave = homeView.getJbSave();
		boolean changeKeyAndIV = utils.checkKeyAndIv();
		boolean changeModeAndPadding = utils.checkModeAndPaddingOfEnFile();
		if(jtDoiXung.isSelected() && cbDoiXung.getSelectedIndex() == 0 && jtFile.isSelected() && jtMaHoa.isSelected()) {
			JButton jtCopy = homeView.getJbCopy();
			jtCopy.setEnabled(false);
			if(changeKeyAndIV && changeModeAndPadding) {
				if(sourceFilePathEn.equals("")) {
					jtSave.setEnabled(false);
					String inputText = "";
					inputText = "Các yêu cầu đã đúng! Vui lòng hãy chọn File!";
					homeView.getJtaInput().setText(inputText);
					homeView.getJtaOutput().setText("");
				}else {
					jtSave.setEnabled(true);
					String inputText = "";
					inputText = sourceFilePathEn;
					homeView.getJtaInput().setText(inputText);
					homeView.getJtaOutput().setText("Hãy chọn thư mục lưu file mã hóa");
				}
			}else {
				jtSave.setEnabled(false);
				String inputText = "";
				inputText = utils.notifyEnFile();
				homeView.getJtaInput().setText(inputText);
				homeView.getJtaOutput().setText("");
			}
		}else if(jtDoiXung.isSelected() && cbDoiXung.getSelectedIndex() == 0 && jtFile.isSelected() && jtGiaiMa.isSelected()) {
			JButton jtCopy = homeView.getJbCopy();
			jtCopy.setEnabled(false);
			if(changeKeyAndIV && changeModeAndPadding) {
				if(sourceFilePathDe.equals("")) {
					jtSave.setEnabled(false);
					String inputText = "";
					inputText = "Các yêu cầu đã đúng! Vui lòng hãy chọn File!";
					homeView.getJtaInput().setText(inputText);
					homeView.getJtaOutput().setText("");
				}else {
					jtSave.setEnabled(true);
					String inputText = "";
					inputText = sourceFilePathDe;
					homeView.getJtaInput().setText(inputText);
					homeView.getJtaOutput().setText("Hãy chọn thư mục lưu file giải mã");
				}
			}else {
				jtSave.setEnabled(false);
				String inputText = "";
				inputText = utils.notifyEnFile();
				homeView.getJtaInput().setText(inputText);
				homeView.getJtaOutput().setText("");
			}
		}
	}
	
	class enAndDe implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			enFile();
		}
	}
	
	class canSave implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			JToggleButton jtDoiXung = homeView.getJtDoiXung();
			JComboBox cbDoiXung = homeView.getJpDoiXung().getComboBox();
			JToggleButton jtFile = homeView.getJtFile();
	        JToggleButton jtMaHoa = homeView.getJpYC_DES_AES().getJtMaHoa();
			JToggleButton jtGiaiMa = homeView.getJpYC_DES_AES().getJtGiaiMa();
			if(jtDoiXung.isSelected() && cbDoiXung.getSelectedIndex() == 0 && jtFile.isSelected() && jtMaHoa.isSelected()) {
				JFileChooser fileChooser = new JFileChooser();
	            int returnValue = fileChooser.showOpenDialog(null);
	            if (returnValue == JFileChooser.APPROVE_OPTION) {
	                sourceFilePathEn = fileChooser.getSelectedFile().getAbsolutePath();
	            }
			}else if(jtDoiXung.isSelected() && cbDoiXung.getSelectedIndex() == 0 && jtFile.isSelected() && jtGiaiMa.isSelected()){
				JFileChooser fileChooser = new JFileChooser();
	            int returnValue = fileChooser.showOpenDialog(null);
	            if (returnValue == JFileChooser.APPROVE_OPTION) {
	                sourceFilePathDe = fileChooser.getSelectedFile().getAbsolutePath();
	            }
			}
            enFile();
		}
	}
	class checkKIVOfEnFile implements KeyListener{
		@Override
		public void keyTyped(KeyEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void keyPressed(KeyEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void keyReleased(KeyEvent e) {
			// TODO Auto-generated method stub
			enFile();
		}
	}
	class checkMPOfEnFile implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			enFile();
		}
	}
	class saveEnAndDe implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			JToggleButton jtDoiXung = homeView.getJtDoiXung();
			JComboBox cbDoiXung = homeView.getJpDoiXung().getComboBox();
			JToggleButton jtFile = homeView.getJtFile();
	        JToggleButton jtMaHoa = homeView.getJpYC_DES_AES().getJtMaHoa();
			JToggleButton jtGiaiMa = homeView.getJpYC_DES_AES().getJtGiaiMa();
			JButton jbSave = homeView.getJbSave();
			JTextArea jtaInput = homeView.getJtaInput();
			JTextArea jtaOutput = homeView.getJtaOutput();
			String sFile = jtaInput.getText();
			String dFile = "";
			boolean changeKeyAndIV = utils.checkKeyAndIv();
			boolean changeModeAndPadding = utils.checkModeAndPaddingOfEnFile();
			
			String key = homeView.getJpYC_DES_AES().getJtfKey().getText();
			String iv = homeView.getJpYC_DES_AES().getJtfIV().getText();
			JComboBox cbMode = homeView.getJpYC_DES_AES().getCbMode();
			String padding = "";
			JComboBox cbPaddingOne = homeView.getJpYC_DES_AES().getCbPaddingOne();
			JComboBox cbPaddingTwo = homeView.getJpYC_DES_AES().getCbPaddingTwo();
			
			String mode = cbMode.getSelectedItem().toString();
			if(cbMode.getSelectedIndex() == 4) {
				padding = cbPaddingOne.getSelectedItem().toString();
			}else {
				padding = cbPaddingTwo.getSelectedItem().toString();
			}
			dESModel.generateSecretKeyFromBase64(key);
			dESModel.generateIVFromBase64(iv);
			dESModel.setMode(mode);
			dESModel.setPadding(padding);
			
			if(jtDoiXung.isSelected() && cbDoiXung.getSelectedIndex() == 0 && jtFile.isSelected() && jtMaHoa.isSelected()) {
			    File originalFile = new File(sFile);
			    if (!originalFile.exists() || !originalFile.isFile()) {
			    	jtaOutput.setText("Lỗi file nguồn");
			    }
			    JFileChooser fileChooser = new JFileChooser();
			    fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
			    int result = fileChooser.showSaveDialog(null);
			    if (result == JFileChooser.APPROVE_OPTION) {
			    	File selectedDirectory = fileChooser.getSelectedFile();
			        String fileName = "en_" + originalFile.getName();
			        File newFile = new File(selectedDirectory, fileName);
			        dFile = newFile.getAbsolutePath();
			        try {
						dESModel.encryptFile(sFile, dFile);
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						jtaOutput.setText("Lỗi trong quá trình chọn file");
					}
			        homeView.getJtaOutput().setText("Mã hóa file thành công");
			        sourceFilePathEn = "";
			        jbSave.setEnabled(false);
			    }
			}else if(jtDoiXung.isSelected() && cbDoiXung.getSelectedIndex() == 0 && jtFile.isSelected() && jtGiaiMa.isSelected()) {
				File originalFile = new File(sFile);
			    if (!originalFile.exists() || !originalFile.isFile()) {
			    	jtaOutput.setText("Lỗi file nguồn");
			    }
			    JFileChooser fileChooser = new JFileChooser();
			    fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
			    int result = fileChooser.showSaveDialog(null);
			    if (result == JFileChooser.APPROVE_OPTION) {
			    	File selectedDirectory = fileChooser.getSelectedFile();
			        String fileName = "de_" + originalFile.getName();
			        File newFile = new File(selectedDirectory, fileName);
			        dFile = newFile.getAbsolutePath();
			        try {
						dESModel.decryptFile(sFile, dFile);
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
						jtaOutput.setText("Lỗi trong quá trình chọn file");
					}
			        homeView.getJtaOutput().setText("Giải mã file thành công");
			        sourceFilePathDe = "";
			        jbSave.setEnabled(false);
			    }
			}
		}
	}
}

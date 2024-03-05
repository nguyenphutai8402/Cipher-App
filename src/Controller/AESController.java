package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.util.Base64;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JTextArea;
import javax.swing.JToggleButton;
import javax.swing.SwingUtilities;

import Controller.DESController.ChooseCipherForDES;
import Controller.DESController.canSave;
import Controller.DESController.checkKIVOfEnFile;
import Controller.DESController.checkMPOfEnFile;
import Controller.DESController.checkYCKeyAndIVChangeListener;
import Controller.DESController.enAndDe;
import Controller.DESController.inputListener;
import Controller.DESController.saveEnAndDe;
import ControllerOfView.ControllerYCKeyAndIv;
import Model.AES;
import Model.DES;
import View.Home;

public class AESController {
	private Home homeView;
	private AES aESModel;
	private ControllerYCKeyAndIv utils;
	private boolean changeKeyAndIVForText;
	private boolean checkEnFile;
	private String sourceFilePathEn;
	private String sourceFilePathDe;
	
	public AESController(Home homeView) {
		this.homeView = homeView;
		this.aESModel = new AES();
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
		aESModel.generateSecretKeyFromBase64(key);
		aESModel.generateIVFromBase64(iv);
		String mode = cbMode.getSelectedItem().toString();
		aESModel.setMode(mode);
		String padding = "";
		if(cbMode.getSelectedIndex() == 4) {
			padding = cbPaddingOne.getSelectedItem().toString();
			aESModel.setPadding(padding);
			
		}else {
			padding = cbPaddingTwo.getSelectedItem().toString();
			aESModel.setPadding(padding);
		}
		if(changeKeyAndIVForText) {
			SwingUtilities.invokeLater(new Runnable() {
			    public void run() {
			    	String outputText = "";
			    	try {
						outputText = aESModel.encrypt(inputText);
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
			outputText = utils.notifyKeyAndIv16Length();
			homeView.getJtaOutput().setText(outputText);
		}
	}
	public void decrypt(String key, String iv, JComboBox cbMode, JComboBox cbPaddingOne, JComboBox cbPaddingTwo, String inputText) {
		aESModel.generateSecretKeyFromBase64(key);
		aESModel.generateIVFromBase64(iv);
		String mode = cbMode.getSelectedItem().toString();
		aESModel.setMode(mode);
		String padding = "";
		if(cbMode.getSelectedIndex() == 4) {
			padding = cbPaddingOne.getSelectedItem().toString();
			aESModel.setPadding(padding);
			
		}else {
			padding = cbPaddingTwo.getSelectedItem().toString();
			aESModel.setPadding(padding);
		}
		if(changeKeyAndIVForText) {
			SwingUtilities.invokeLater(new Runnable() {
			    public void run() {
			    	String outputText = "";
			    	try {
						outputText = aESModel.decrypt(inputText);
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
			outputText = utils.notifyKeyAndIv16Length();
			homeView.getJtaOutput().setText(outputText);
		}
	}
	class ChooseCipherForDES implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			JToggleButton jtDoiXung = homeView.getJtDoiXung();
			JComboBox cbDoiXung = homeView.getJpDoiXung().getComboBox();
			JToggleButton jtText = homeView.getJtText();
			aESModel.setKey(null);
			aESModel.setIv(null);
			JToggleButton jtMaHoa = homeView.getJpYC_DES_AES().getJtMaHoa();
			JToggleButton jtGiaiMa = homeView.getJpYC_DES_AES().getJtGiaiMa();
			String key = homeView.getJpYC_DES_AES().getJtfKey().getText();
			String iv = homeView.getJpYC_DES_AES().getJtfIV().getText();
			String mode = "";
			JComboBox cbMode = homeView.getJpYC_DES_AES().getCbMode();
			String padding = "";
			JComboBox cbPaddingOne = homeView.getJpYC_DES_AES().getCbPaddingOne();
			JComboBox cbPaddingTwo = homeView.getJpYC_DES_AES().getCbPaddingTwo();
			changeKeyAndIVForText = utils.checkKeyAndIv16Length();
			String inputText = homeView.getJtaInput().getText();
			if(jtDoiXung.isSelected() && cbDoiXung.getSelectedIndex() == 1 && jtText.isSelected() && jtMaHoa.isSelected()) {
				encrypt(key, iv, cbMode, cbPaddingOne, cbPaddingTwo, inputText);
			}else if(jtDoiXung.isSelected() && cbDoiXung.getSelectedIndex() == 1 && jtText.isSelected() && jtGiaiMa.isSelected()){
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
			aESModel.setKey(null);
			aESModel.setIv(null);
			JToggleButton jtMaHoa = homeView.getJpYC_DES_AES().getJtMaHoa();
			JToggleButton jtGiaiMa = homeView.getJpYC_DES_AES().getJtGiaiMa();
			String key = homeView.getJpYC_DES_AES().getJtfKey().getText();
			String iv = homeView.getJpYC_DES_AES().getJtfIV().getText();
			String mode = "";
			JComboBox cbMode = homeView.getJpYC_DES_AES().getCbMode();
			String padding = "";
			JComboBox cbPaddingOne = homeView.getJpYC_DES_AES().getCbPaddingOne();
			JComboBox cbPaddingTwo = homeView.getJpYC_DES_AES().getCbPaddingTwo();
			changeKeyAndIVForText = utils.checkKeyAndIv16Length();
			String inputText = homeView.getJtaInput().getText();
			if(jtDoiXung.isSelected() && cbDoiXung.getSelectedIndex() == 1 && jtText.isSelected() && jtMaHoa.isSelected()) {
				encrypt(key, iv, cbMode, cbPaddingOne, cbPaddingTwo, inputText);
			}else if(jtDoiXung.isSelected() && cbDoiXung.getSelectedIndex() == 1 && jtText.isSelected() && jtGiaiMa.isSelected()){
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
			aESModel.setKey(null);
			aESModel.setIv(null);
			JToggleButton jtMaHoa = homeView.getJpYC_DES_AES().getJtMaHoa();
			JToggleButton jtGiaiMa = homeView.getJpYC_DES_AES().getJtGiaiMa();
			String key = homeView.getJpYC_DES_AES().getJtfKey().getText();
			String iv = homeView.getJpYC_DES_AES().getJtfIV().getText();
			String mode = "";
			JComboBox cbMode = homeView.getJpYC_DES_AES().getCbMode();
			String padding = "";
			JComboBox cbPaddingOne = homeView.getJpYC_DES_AES().getCbPaddingOne();
			JComboBox cbPaddingTwo = homeView.getJpYC_DES_AES().getCbPaddingTwo();
			changeKeyAndIVForText = utils.checkKeyAndIv16Length();
			String inputText = homeView.getJtaInput().getText();
			if(jtDoiXung.isSelected() && cbDoiXung.getSelectedIndex() == 1 && jtText.isSelected() && jtMaHoa.isSelected()) {
				encrypt(key, iv, cbMode, cbPaddingOne, cbPaddingTwo, inputText);
			}else if(jtDoiXung.isSelected() && cbDoiXung.getSelectedIndex() == 1 && jtText.isSelected() && jtGiaiMa.isSelected()){
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
			changeKeyAndIVForText = utils.checkKeyAndIv();
			if(jtDoiXung.isSelected() && cbDoiXung.getSelectedIndex() == 1 && jtText.isSelected()) {
				if(changeKeyAndIVForText) {
					homeView.getJtaInput().setText("");
					homeView.getJtaOutput().setText("");
				}else {
					homeView.getJtaInput().setText("");
					homeView.getJtaOutput().setText(utils.notifyKeyAndIv16Length());
				}
			}else {
				
			}
		}
	}
	
	public void fileECB() {
		JToggleButton jtDoiXung = homeView.getJtDoiXung();
		JComboBox cbDoiXung = homeView.getJpDoiXung().getComboBox();
		JToggleButton jtFile = homeView.getJtFile();
        JToggleButton jtMaHoa = homeView.getJpYC_DES_AES().getJtMaHoa();
		JToggleButton jtGiaiMa = homeView.getJpYC_DES_AES().getJtGiaiMa();
		JButton jtSave = homeView.getJbSave();
		boolean changeKeyAndIV = utils.checkKeyAndIv16Length();
		boolean changeModeAndPadding = utils.checkModeAndPaddingOfEnFile();
		if(jtDoiXung.isSelected() && cbDoiXung.getSelectedIndex() == 1 && jtFile.isSelected() && jtMaHoa.isSelected()) {
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
				inputText = utils.notifyFileOfECB();
				homeView.getJtaInput().setText(inputText);
				homeView.getJtaOutput().setText("");
			}
		}else if(jtDoiXung.isSelected() && cbDoiXung.getSelectedIndex() == 1 && jtFile.isSelected() && jtGiaiMa.isSelected()) {
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
				inputText = utils.notifyFileOfECB();
				homeView.getJtaInput().setText(inputText);
				homeView.getJtaOutput().setText("");
			}
		}
	}
	class enAndDe implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			fileECB();
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
			if(jtDoiXung.isSelected() && cbDoiXung.getSelectedIndex() == 1 && jtFile.isSelected() && jtMaHoa.isSelected()) {
				JFileChooser fileChooser = new JFileChooser();
	            int returnValue = fileChooser.showOpenDialog(null);
	            if (returnValue == JFileChooser.APPROVE_OPTION) {
	                sourceFilePathEn = fileChooser.getSelectedFile().getAbsolutePath();
	            }
			}else if(jtDoiXung.isSelected() && cbDoiXung.getSelectedIndex() == 1 && jtFile.isSelected() && jtGiaiMa.isSelected()){
				JFileChooser fileChooser = new JFileChooser();
	            int returnValue = fileChooser.showOpenDialog(null);
	            if (returnValue == JFileChooser.APPROVE_OPTION) {
	                sourceFilePathDe = fileChooser.getSelectedFile().getAbsolutePath();
	            }
			}
            fileECB();
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
			fileECB();
		}
	}
	class checkMPOfEnFile implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			fileECB();
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
			aESModel.generateSecretKeyFromBase64(key);
			aESModel.generateIVFromBase64(iv);
			aESModel.setMode(mode);
			aESModel.setPadding(padding);
			
			if(jtDoiXung.isSelected() && cbDoiXung.getSelectedIndex() == 1 && jtFile.isSelected() && jtMaHoa.isSelected()) {
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
						aESModel.encryptFile(sFile, dFile);
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						jtaOutput.setText("Lỗi trong quá trình chọn file");
					}
			        homeView.getJtaOutput().setText("Mã hóa file thành công");
			        sourceFilePathEn = "";
			        jbSave.setEnabled(false);
			    }
			}else if(jtDoiXung.isSelected() && cbDoiXung.getSelectedIndex() == 1 && jtFile.isSelected() && jtGiaiMa.isSelected()) {
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
						aESModel.decryptFile(sFile, dFile);
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

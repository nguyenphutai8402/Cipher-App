package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JTextArea;
import javax.swing.JToggleButton;

import Controller.DESController.canSave;
import Controller.DESController.checkKIVOfEnFile;
import Controller.DESController.checkMPOfEnFile;
import Controller.DESController.enAndDe;
import Controller.DESController.inputListener;
import Controller.DESController.saveEnAndDe;
import ControllerOfView.ControllerYCKeyAndIv;
import Model.DES;
import Model.RSA;
import View.Home;

public class RSAController {
	private Home homeView;
	private RSA rSAModel;
	private ControllerYCKeyAndIv utils;
	private boolean changePairKeyForText;
	private boolean checkEnFile;
	private String sourceFilePathEn;
	private String sourceFilePathDe;
	public RSAController(Home homeView) {
		this.homeView = homeView;
		this.rSAModel = new RSA();
		this.utils = new ControllerYCKeyAndIv(homeView);
		this.changePairKeyForText = false;
		this.sourceFilePathEn = "";
		this.sourceFilePathDe = "";
		Listeners();
	}
	public void Listeners() {
		homeView.getJpBatDoiXung().comboBoxListener(new ChooseCipher());
		homeView.getJpYC_RSA().checkPairKey(new checkPairKey());
		homeView.inputTextListener(new inputListener());
		homeView.getJpYC_RSA().enAndDEListenner(new enAndDeForText());
		
		homeView.canSave(new canSave());
		homeView.getJpYC_RSA().checkPairKey(new checkPairKeyOfEnFile());
		homeView.getJpBatDoiXung().comboBoxListener(new checkModeOfEnAndDE());
		homeView.saveEnAnDeFile(new saveEnAndDe());
		homeView.getJpYC_RSA().enAndDEListenner(new enAndDe());
	}
	public void encrypt(String puKey, String inputText) {
		String outputText = "";
		if(changePairKeyForText) {
			try {
				rSAModel.setPublicKeyFromString(puKey);
				String mode = homeView.getJpBatDoiXung().getComboBox().getSelectedItem().toString();
				rSAModel.setMode(mode);
				if(changePairKeyForText) {
					outputText = rSAModel.encrypt(inputText);
					homeView.getJtaOutput().setText(outputText);
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				outputText = "Có lỗi xảy ra";
				homeView.getJtaOutput().setText(outputText);
			}
		}else {
			outputText = utils.notifyPukeyAndPrKey();
			homeView.getJtaOutput().setText(outputText);
		}
	}
	public void decrypt(String prKey, String inputText) {
		String outputText = "";
		if(changePairKeyForText) {
			try {
				rSAModel.setPrivateKeyFromString(prKey);
				String mode = homeView.getJpBatDoiXung().getComboBox().getSelectedItem().toString();
				rSAModel.setMode(mode);
				if(changePairKeyForText) {
					outputText = rSAModel.decrypt(inputText);
					homeView.getJtaOutput().setText(outputText);
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				outputText = "Có lỗi xảy ra";
				homeView.getJtaOutput().setText(outputText);
			}
		}else {
			outputText = utils.notifyPukeyAndPrKey();
			homeView.getJtaOutput().setText(outputText);
		}
	}
	class ChooseCipher implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			JToggleButton jtBatDoiXung = homeView.getJtBatDoiXung();
			JToggleButton jtText = homeView.getJtText();
			rSAModel.setPublicKey(null);
			rSAModel.setPrivateKey(null);
			JToggleButton jtMaHoa = homeView.getJpYC_RSA().getJtMaHoa();
			JToggleButton jtGiaiMa = homeView.getJpYC_RSA().getJtGiaiMa();
			String puKey = homeView.getJpYC_RSA().getJtaPuKey().getText();
			String prKey = homeView.getJpYC_RSA().getJtaPrKey().getText();
			JComboBox cbMode = homeView.getJpBatDoiXung().getComboBox();
			String mode = cbMode.getSelectedItem().toString();
			String inputText = homeView.getJtaInput().getText();
			if(jtBatDoiXung.isSelected() && jtText.isSelected() && jtMaHoa.isSelected()) {
				changePairKeyForText = utils.checkPuKeyAndPrKey();
				encrypt(puKey, inputText);
			}else if(jtBatDoiXung.isSelected()&& jtText.isSelected() && jtGiaiMa.isSelected()){
				changePairKeyForText = utils.checkPuKeyAndPrKey();
				decrypt(prKey, inputText);
			}
		}
	}
	class checkPairKey implements KeyListener{
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
			JToggleButton jtBatDoiXung = homeView.getJtBatDoiXung();
			JToggleButton jtText = homeView.getJtText();
			rSAModel.setPublicKey(null);
			rSAModel.setPrivateKey(null);
			JToggleButton jtMaHoa = homeView.getJpYC_RSA().getJtMaHoa();
			JToggleButton jtGiaiMa = homeView.getJpYC_RSA().getJtGiaiMa();
			String puKey = homeView.getJpYC_RSA().getJtaPuKey().getText();
			String prKey = homeView.getJpYC_RSA().getJtaPrKey().getText();
			JComboBox cbMode = homeView.getJpBatDoiXung().getComboBox();
			String mode = cbMode.getSelectedItem().toString();
			String inputText = homeView.getJtaInput().getText();
			if(jtBatDoiXung.isSelected() && jtText.isSelected() && jtMaHoa.isSelected()) {
				changePairKeyForText = utils.checkPuKeyAndPrKey();
				encrypt(puKey, inputText);
			}else if(jtBatDoiXung.isSelected()&& jtText.isSelected() && jtGiaiMa.isSelected()){
				changePairKeyForText = utils.checkPuKeyAndPrKey();
				decrypt(prKey, inputText);
			}
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
			JToggleButton jtBatDoiXung = homeView.getJtBatDoiXung();
			JToggleButton jtText = homeView.getJtText();
			rSAModel.setPublicKey(null);
			rSAModel.setPrivateKey(null);
			JToggleButton jtMaHoa = homeView.getJpYC_RSA().getJtMaHoa();
			JToggleButton jtGiaiMa = homeView.getJpYC_RSA().getJtGiaiMa();
			String puKey = homeView.getJpYC_RSA().getJtaPuKey().getText();
			String prKey = homeView.getJpYC_RSA().getJtaPrKey().getText();
			JComboBox cbMode = homeView.getJpBatDoiXung().getComboBox();
			String mode = cbMode.getSelectedItem().toString();
			String inputText = homeView.getJtaInput().getText();
			if(jtBatDoiXung.isSelected() && jtText.isSelected() && jtMaHoa.isSelected()) {
				changePairKeyForText = utils.checkPuKeyAndPrKey();
				encrypt(puKey, inputText);
			}else if(jtBatDoiXung.isSelected()&& jtText.isSelected() && jtGiaiMa.isSelected()){
				changePairKeyForText = utils.checkPuKeyAndPrKey();
				decrypt(prKey, inputText);
			}
		}
	}
	class enAndDeForText implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			JToggleButton jtBatDoiXung = homeView.getJtBatDoiXung();
			JToggleButton jtText = homeView.getJtText();
			rSAModel.setPublicKey(null);
			rSAModel.setPrivateKey(null);
			JToggleButton jtMaHoa = homeView.getJpYC_RSA().getJtMaHoa();
			JToggleButton jtGiaiMa = homeView.getJpYC_RSA().getJtGiaiMa();
			String puKey = homeView.getJpYC_RSA().getJtaPuKey().getText();
			String prKey = homeView.getJpYC_RSA().getJtaPrKey().getText();
			JComboBox cbMode = homeView.getJpBatDoiXung().getComboBox();
			String mode = cbMode.getSelectedItem().toString();
			String inputText = homeView.getJtaInput().getText();
			if(jtBatDoiXung.isSelected() && jtText.isSelected()) {
				changePairKeyForText = utils.checkPuKeyAndPrKey();
				if(changePairKeyForText) {
					homeView.getJtaInput().setText("");
					homeView.getJtaOutput().setText("");
				}else {
					homeView.getJtaInput().setText("");
					String outputText = utils.notifyPukeyAndPrKey();
					homeView.getJtaOutput().setText(outputText);
				}
			}
		}
		
	}
	
	public void enFile() {
		JToggleButton jtBatDoiXung = homeView.getJtBatDoiXung();
		JToggleButton jtFile = homeView.getJtFile();
        JToggleButton jtMaHoa = homeView.getJpYC_RSA().getJtMaHoa();
		JToggleButton jtGiaiMa = homeView.getJpYC_RSA().getJtGiaiMa();
		JButton jtSave = homeView.getJbSave();
		boolean changePairkey = utils.checkPuKeyAndPrKey();
		if(jtBatDoiXung.isSelected() && jtFile.isSelected() && jtMaHoa.isSelected()) {
			JButton jtCopy = homeView.getJbCopy();
			jtCopy.setEnabled(false);
			if(changePairkey) {
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
				inputText = utils.notifyPukeyAndPrKey();
				homeView.getJtaInput().setText(inputText);
				homeView.getJtaOutput().setText("");
			}
		}else if(jtBatDoiXung.isSelected() && jtFile.isSelected() && jtGiaiMa.isSelected()) {
			JButton jtCopy = homeView.getJbCopy();
			jtCopy.setEnabled(false);
			if(changePairkey) {
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
				inputText = utils.notifyPukeyAndPrKey();
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
			JToggleButton jtBatDoiXung = homeView.getJtBatDoiXung();
			JToggleButton jtFile = homeView.getJtFile();
	        JToggleButton jtMaHoa = homeView.getJpYC_RSA().getJtMaHoa();
			JToggleButton jtGiaiMa = homeView.getJpYC_RSA().getJtGiaiMa();
			if(jtBatDoiXung.isSelected() && jtFile.isSelected() && jtMaHoa.isSelected()) {
				JFileChooser fileChooser = new JFileChooser();
	            int returnValue = fileChooser.showOpenDialog(null);
	            if (returnValue == JFileChooser.APPROVE_OPTION) {
	                sourceFilePathEn = fileChooser.getSelectedFile().getAbsolutePath();
	            }
			}else if(jtBatDoiXung.isSelected() && jtFile.isSelected() && jtGiaiMa.isSelected()){
				JFileChooser fileChooser = new JFileChooser();
	            int returnValue = fileChooser.showOpenDialog(null);
	            if (returnValue == JFileChooser.APPROVE_OPTION) {
	                sourceFilePathDe = fileChooser.getSelectedFile().getAbsolutePath();
	            }
			}
            enFile();
		}
	}
	class checkPairKeyOfEnFile implements KeyListener{
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
	class checkModeOfEnAndDE implements ActionListener{
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
			JToggleButton jtBatDoiXung = homeView.getJtBatDoiXung();
			JToggleButton jtFile = homeView.getJtFile();
	        JToggleButton jtMaHoa = homeView.getJpYC_RSA().getJtMaHoa();
			JToggleButton jtGiaiMa = homeView.getJpYC_RSA().getJtGiaiMa();
			JButton jbSave = homeView.getJbSave();
			JTextArea jtaInput = homeView.getJtaInput();
			JTextArea jtaOutput = homeView.getJtaOutput();
			String sFile = jtaInput.getText();
			String dFile = "";
//			boolean changePairKey = utils.checkPuKeyAndPrKey();
			JComboBox cbMode = homeView.getJpBatDoiXung().getComboBox();
			String mode = cbMode.getSelectedItem().toString();
			rSAModel.setMode(mode);
			if(jtBatDoiXung.isSelected()&& jtFile.isSelected() && jtMaHoa.isSelected()) {
				try {
					rSAModel.setPublicKeyFromString(homeView.getJpYC_RSA().getJtaPuKey().getText());
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
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
						rSAModel.fileEncrypt(sFile, dFile);
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						jtaOutput.setText("Lỗi trong quá trình chọn file");
					}
			        homeView.getJtaOutput().setText("Mã hóa file thành công");
			        sourceFilePathEn = "";
			        jbSave.setEnabled(false);
			    }
			}else if(jtBatDoiXung.isSelected()&& jtFile.isSelected() && jtGiaiMa.isSelected()) {
				try {
					rSAModel.setPrivateKeyFromString(homeView.getJpYC_RSA().getJtaPrKey().getText());
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
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
						rSAModel.fileDecrypt(sFile, dFile);
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

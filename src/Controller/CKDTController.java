package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFileChooser;
import javax.swing.JTextArea;
import javax.swing.JToggleButton;

import Controller.HashController.ComboBoxHashListener;
import Controller.HashController.canSave;
import Controller.HashController.comboBoxFileListenner;
import Controller.HashController.inputTextListener;
import ControllerOfView.ControllerYCKeyAndIv;
import Model.SHA_MD5;
import View.Home;

public class CKDTController {
	private Home homeView;
	private SHA_MD5 hash;
	private String sourceFilePathHash;
	private ControllerYCKeyAndIv utils;
	private boolean checkFile;
	public CKDTController(Home homeView) {
		this.homeView = homeView;
		this.hash = new SHA_MD5();
		this.utils = new ControllerYCKeyAndIv(homeView);
		this.sourceFilePathHash = "";
		this.checkFile = false;
		Listeners();
	}
	public void Listeners(){
		homeView.getJpChuKyDienTu().chooseListener(new comboBoxFileListenner());
		homeView.getJpYC_CKDT().hashInputListener(new inputHashListener());
		homeView.canSave(new canSave());
	}
	
	public void chooseFile() {
		String inputText = "";
		String output = "";
		String algorithms = homeView.getJpChuKyDienTu().getComboBox().getSelectedItem().toString();
		homeView.getJbSave().setEnabled(false);
		if(checkFile) {
			if(sourceFilePathHash.equals("")) {
				inputText = "Vui lòng hãy chọn File!";
				homeView.getJtaInput().setText("");
				homeView.getJtaOutput().setText(inputText);
			}else {
				String inputHash = homeView.getJpYC_CKDT().getJtfHashFile().getText();
				inputText = sourceFilePathHash;
				homeView.getJtaInput().setText(inputText);
				try {
					String outputHash = hash.hashFile(sourceFilePathHash, algorithms);
					if(inputHash.equals(outputHash)) {
						output = "File kiểm tra hợp lệ!";
					}else {
						output = "File kiểm tra không hợp lệ!";
					}
					homeView.getJtaOutput().setText(output);
					sourceFilePathHash = "";
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					homeView.getJtaInput().setText("Có lỗi xảy ra");
				}
			}
		}else {
			output = utils.notifyHashInput();
			homeView.getJtaOutput().setText(output);
		}
	}
	class canSave implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			JToggleButton jtCKDT = homeView.getJtCKDienTu();
			JToggleButton jtFile = homeView.getJtFile();
			JTextArea jtaOutput = homeView.getJtaOutput();
			checkFile = utils.checkHashInput();
			if(jtCKDT.isSelected()) {
				JFileChooser fileChooser = new JFileChooser();
	            int returnValue = fileChooser.showOpenDialog(null);
	            if (returnValue == JFileChooser.APPROVE_OPTION) {
	                sourceFilePathHash = fileChooser.getSelectedFile().getAbsolutePath();
	            }
	            chooseFile();
			}
		}
	}
	class comboBoxFileListenner implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			JToggleButton jtCKDT = homeView.getJtCKDienTu();
			JToggleButton jtFile = homeView.getJtFile();
			checkFile = utils.checkHashInput();
			if(jtCKDT.isSelected()) {
	            chooseFile();
			}
		}
	}
	class inputHashListener implements KeyListener{
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
			JToggleButton jtCKDT = homeView.getJtCKDienTu();
			JToggleButton jtFile = homeView.getJtFile();
			checkFile = utils.checkHashInput();
			if(jtCKDT.isSelected()) {
	            chooseFile();
			}
		}
	}
}

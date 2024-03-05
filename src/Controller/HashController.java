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

import ControllerOfView.ControllerYCKeyAndIv;
import Model.AES;
import Model.SHA_MD5;
import View.Home;

public class HashController {
	private Home homeView;
	private SHA_MD5 hash;
	private String sourceFilePathHash;
	public HashController(Home homeView) {
		this.homeView = homeView;
		this.hash = new SHA_MD5();
		this.sourceFilePathHash = "";
		Listeners();
	}
	public void Listeners() {
		homeView.getJpHash().comboBoxHashListener(new ComboBoxHashListener());
		homeView.inputTextListener(new inputTextListener());
		homeView.canSave(new canSave());
		homeView.getJpHash().comboBoxHashListener(new comboBoxFileListenner());
	}
	public void hash() {
		String input = homeView.getJtaInput().getText();
		String output = "";
		String algorithms = homeView.getJpHash().getComboBox().getSelectedItem().toString();
		try {
			output = hash.hashText(input, algorithms);
			homeView.getJtaOutput().setText(output);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			homeView.getJtaInput().setText("Có lỗi xảy ra");
		}
	}
	class ComboBoxHashListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			JToggleButton jtHash = homeView.getJtHash();
			JToggleButton jtText = homeView.getJtText();
			if(jtHash.isSelected() && jtText.isSelected()) {
				hash();
			}
		}
	}
	class inputTextListener implements KeyListener{
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
			JToggleButton jtHash = homeView.getJtHash();
			JToggleButton jtText = homeView.getJtText();
			if(jtHash.isSelected() && jtText.isSelected()) {
				hash();
			}
		}
	}
	public void chooseFile() {
		String inputText = "";
		String output = "";
		String algorithms = homeView.getJpHash().getComboBox().getSelectedItem().toString();
		homeView.getJbSave().setEnabled(false);
		if(sourceFilePathHash.equals("")) {
			inputText = "Vui lòng hãy chọn File!";
			homeView.getJtaInput().setText(inputText);
			homeView.getJtaOutput().setText("");
		}else {
			inputText = sourceFilePathHash;
			homeView.getJtaInput().setText(inputText);
			try {
				homeView.getJbCopy().setEnabled(true);
				output = hash.hashFile(sourceFilePathHash, algorithms);
				homeView.getJtaOutput().setText(output);
				sourceFilePathHash = "";
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				homeView.getJtaInput().setText("Có lỗi xảy ra");
			}
		}
	}
	class canSave implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			JToggleButton jtHash = homeView.getJtHash();
			JToggleButton jtFile = homeView.getJtFile();
			JTextArea jtaOutput = homeView.getJtaOutput();
			if(jtHash.isSelected() && jtFile.isSelected()) {
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
			JToggleButton jtHash = homeView.getJtHash();
			JToggleButton jtFile = homeView.getJtFile();
			if(jtHash.isSelected() && jtFile.isSelected()) {
	            chooseFile();
			}
		}
	}
}

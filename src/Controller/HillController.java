package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JComboBox;
import javax.swing.JToggleButton;

import Config.Alphabet;
import Controller.VigenereController.InputListener;
import Controller.VigenereController.LangListener;
import ControllerOfView.ControllerYCKeyAndIv;
import Model.Hill;
import Model.Vigenere;
import View.Home;

public class HillController {
	private Home homeView;
	private Hill hillController;
	private boolean changeKey;
	private ControllerYCKeyAndIv utils;
	public HillController(Home homeView) {
		this.homeView = homeView;
		this.hillController = new Hill();
		this.utils = new ControllerYCKeyAndIv(homeView);
		Listeners();
	}
	public void Listeners() {
		homeView.inputTextListener(new InputListener());
		homeView.getJpYC_Vigenere().keyInputListener(new InputListener());
		homeView.getJpYC_Vigenere().enAndDeListener(new enAndDe());
	}
	public void encrypt() {
		String input = homeView.getJtaInput().getText();
		String key = homeView.getJpYC_Vigenere().getJtfKey().getText();
		int[][] keyMatrix = hillController.convertToMatrix(key);
		String output = "";
		int lang = homeView.getJpYC_Vigenere().getCbLang().getSelectedIndex();
		if(changeKey) {
			try {
				if(lang == 0) {
					output = hillController.encryptFinal(Alphabet.ALPHABET_ENG, input, 2, keyMatrix);
				}else if(lang == 1) {
					output = hillController.encryptFinal(Alphabet.ALPHABET_VI, input, 2, keyMatrix);
				}
				homeView.getJtaOutput().setText(output);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				homeView.getJtaOutput().setText("Có lỗi xảy ra");
			}
		}else {
			homeView.getJtaOutput().setText(utils.notifyKeyOfHill());
		}
	}
	public void decrypt() {
		String input = homeView.getJtaInput().getText();
		String key = homeView.getJpYC_Vigenere().getJtfKey().getText();
		int[][] keyMatrix = hillController.convertToMatrix(key);
		String output = "";
		int lang = homeView.getJpYC_Vigenere().getCbLang().getSelectedIndex();
		if(changeKey) {
			try {
				if(lang == 0) {
					output = hillController.decryptFinal(Alphabet.ALPHABET_ENG, input, 2, keyMatrix);
				}else if(lang == 1) {
					output = hillController.decryptFinal(Alphabet.ALPHABET_VI, input, 2, keyMatrix);
				}
				homeView.getJtaOutput().setText(output);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				homeView.getJtaOutput().setText("Có lỗi xảy ra");
			}
		}else {
			homeView.getJtaOutput().setText(utils.notifyKeyOfHill());
		}
	}
	class InputListener implements KeyListener{
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
			JToggleButton jtMaHoa = homeView.getJpYC_Vigenere().getJtMaHoa();
			JToggleButton jtGiaiMa = homeView.getJpYC_Vigenere().getJtGiaiMa();
			if(jtDoiXung.isSelected() && cbDoiXung.getSelectedIndex() == 4 && jtMaHoa.isSelected()) {
				changeKey = utils.checkKeyOfHill();
				encrypt();
			}else if(jtDoiXung.isSelected() && cbDoiXung.getSelectedIndex() == 4 && jtGiaiMa.isSelected()) {
				changeKey = utils.checkKeyOfHill();
				decrypt();
			}
		}
	}
	class enAndDe implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			JToggleButton jtDoiXung = homeView.getJtDoiXung();
			JComboBox cbDoiXung = homeView.getJpDoiXung().getComboBox();
			if(jtDoiXung.isSelected() && cbDoiXung.getSelectedIndex() == 4) {
				changeKey = utils.checkKeyOfHill();
				if(changeKey) {
					homeView.getJtaInput().setText("");
					homeView.getJtaOutput().setText("");
				}else {
					homeView.getJtaInput().setText("");
					homeView.getJtaOutput().setText(utils.notifyKeyOfHill());
				}
			}
		}
	}
}

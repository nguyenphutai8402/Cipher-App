package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JComboBox;
import javax.swing.JToggleButton;

import Config.Alphabet;
import ControllerOfView.ControllerYCKeyAndIv;
import Model.Vigenere;
import View.Home;

public class VigenereController {
	private Home homeView;
	private Vigenere vigenereController;
	private boolean changeKey;
	private ControllerYCKeyAndIv utils;
	public VigenereController(Home homeView) {
		this.homeView = homeView;
		this.vigenereController = new Vigenere();
		this.utils = new ControllerYCKeyAndIv(homeView);
		Listeners();
	}
	public void Listeners() {
		homeView.inputTextListener(new InputListener());
		homeView.getJpYC_Vigenere().keyInputListener(new InputListener());
		homeView.getJpYC_Vigenere().langListener(new LangListener());
		homeView.getJpYC_Vigenere().enAndDeListener(new enAndDe());
	}
	public void encrypt() {
		String input = homeView.getJtaInput().getText();
		String key = homeView.getJpYC_Vigenere().getJtfKey().getText();
		String output = "";
		int lang = homeView.getJpYC_Vigenere().getCbLang().getSelectedIndex();
		if(changeKey) {
			try {
				if(lang == 0) {
					output = vigenereController.encrypt(Alphabet.ALPHABET_ENG, input, key);
				}else if(lang == 1) {
					output = vigenereController.encrypt(Alphabet.ALPHABET_VI, input, key);
				}
				homeView.getJtaOutput().setText(output);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				homeView.getJtaOutput().setText("Có lỗi xảy ra");
			}
		}else {
			homeView.getJtaOutput().setText(utils.notifyKeyOfVigenere());
		}
	}
	public void decrypt() {
		String input = homeView.getJtaInput().getText();
		String key = homeView.getJpYC_Vigenere().getJtfKey().getText();
		String output = "";
		int lang = homeView.getJpYC_Vigenere().getCbLang().getSelectedIndex();
		if(changeKey) {
			try {
				if(lang == 0) {
					output = vigenereController.decrypt(Alphabet.ALPHABET_ENG, input, key);
				}else if(lang == 1) {
					output = vigenereController.decrypt(Alphabet.ALPHABET_VI, input, key);
				}
				homeView.getJtaOutput().setText(output);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				homeView.getJtaOutput().setText("Có lỗi xảy ra");
			}
		}else {
			homeView.getJtaOutput().setText(utils.notifyKeyOfVigenere());
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
			changeKey = utils.checkKeyOfVigenere();
			if(jtDoiXung.isSelected() && cbDoiXung.getSelectedIndex() == 3 && jtMaHoa.isSelected()) {
				encrypt();
			}else if(jtDoiXung.isSelected() && cbDoiXung.getSelectedIndex() == 3 && jtGiaiMa.isSelected()) {
				decrypt();
			}
		}
	}
	class LangListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			JToggleButton jtDoiXung = homeView.getJtDoiXung();
			JComboBox cbDoiXung = homeView.getJpDoiXung().getComboBox();
			JToggleButton jtMaHoa = homeView.getJpYC_Vigenere().getJtMaHoa();
			JToggleButton jtGiaiMa = homeView.getJpYC_Vigenere().getJtGiaiMa();
			changeKey = utils.checkKeyOfVigenere();
			if(jtDoiXung.isSelected() && cbDoiXung.getSelectedIndex() == 3 && jtMaHoa.isSelected()) {
				encrypt();
			}else if(jtDoiXung.isSelected() && cbDoiXung.getSelectedIndex() == 3 && jtGiaiMa.isSelected()) {
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
			changeKey = utils.checkKeyOfVigenere();
			if(jtDoiXung.isSelected() && cbDoiXung.getSelectedIndex() == 3) {
				if(changeKey) {
					homeView.getJtaInput().setText("");
					homeView.getJtaOutput().setText("");
				}else {
					homeView.getJtaInput().setText("");
					homeView.getJtaOutput().setText(utils.notifyKeyOfVigenere());
				}
			}
		}
	}
}

package ControllerOfView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

import Utils.Visibility;
import View.Home;
import View.JpDoiXung;
import View.JpYC_DES_AES;

public class DoiXungController {
	private Home homeView;
	private DESViewController dESController;
	private VigenereController vigenereController;
	public DoiXungController(Home home) {
		this.homeView = home;
		Visibility.show(homeView.getJtFile(), homeView.getJbSave());
		Visibility.show(homeView.getJpYC_DES_AES());
		homeView.yCChinhListener(new doiXungMenuListener());
		this.dESController = new DESViewController(home);
		this.vigenereController = new VigenereController(home);
	}
	class doiXungMenuListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			int choose = homeView.getJpDoiXung().getComboBox().getSelectedIndex();
			if(choose == 0 || choose == 1 || choose == 2) {
				threeAction();
			}else if(choose == 3 || choose == 4){
				vigenereAction();
			}
		}
	}
	public void threeAction() {
		homeView.getJtDoiXung().setSelected(true);
		homeView.getJpYC_DES_AES().getJtMaHoa().setSelected(true);
		Visibility.show(homeView.getJpYC_DES_AES().getJpNewKeyAndIV());
		homeView.getJpYC_DES_AES().getJtfKey().setText("");
		homeView.getJpYC_DES_AES().getJtfIV().setText("");
		homeView.getJpYC_DES_AES().getCbMode().setSelectedIndex(0);	
		homeView.getJpYC_DES_AES().getCbPaddingTwo().setSelectedIndex(0);
		homeView.getJpYC_DES_AES().getJtfNewKey().setText("");
		homeView.getJpYC_DES_AES().getJtfNewIV().setText("");
		homeView.getJpYC_DES_AES().getJtfNewKey().setEditable(false);
		homeView.getJpYC_DES_AES().getJtfNewIV().setEditable(false);
		Visibility.show(homeView.getJtText(), homeView.getJtFile(), homeView.getJbCopy(),homeView.getJbSave());
		homeView.getJtText().setSelected(true);
		homeView.getJtFile().setSelected(false);
		homeView.getJbCopy().setSelected(false);
		homeView.getJbCopy().setEnabled(true);
		homeView.getJbSave().setSelected(false);
		homeView.getJbSave().setEnabled(false);
		homeView.getJtaInput().setText("");
		homeView.getJtaInput().setEditable(true);
		homeView.getJtaOutput().setText("Key không được để trống!");
		Visibility.show(homeView.getJpYC_DES_AES(), homeView.getJtFile(), homeView.getJtFile(), homeView.getJbSave());
		Visibility.hide(homeView.getJpYC_Vigenere(), homeView.getJpYC_RSA(), homeView.getJpYC_CKDT());
	}
	public void vigenereAction() {
		homeView.getJtDoiXung().setSelected(true);
		homeView.getJpYC_Vigenere().getJtMaHoa().setSelected(true);
		Visibility.show(homeView.getJpYC_Vigenere().getJpNewKeyAndIV());
		homeView.getJpYC_Vigenere().getJtfKey().setText("");
		homeView.getJpYC_Vigenere().getJtfNewKey().setEditable(false);;
		homeView.getJpYC_Vigenere().getCbLang().setSelectedIndex(0);
		homeView.getJpYC_Vigenere().getJtfNewKey().setText("");
		homeView.getJpYC_Vigenere().getJtfNewKey().setEditable(false);
		Visibility.show(homeView.getJtText(), homeView.getJbCopy());
		Visibility.hide(homeView.getJtFile(), homeView.getJbSave());
		homeView.getJtText().setSelected(true);
		homeView.getJtFile().setSelected(false);
		homeView.getJbCopy().setSelected(false);
		homeView.getJbSave().setSelected(false);
		homeView.getJtaInput().setText("");
		homeView.getJtaInput().setEditable(true);
		homeView.getJtaOutput().setText("Key không được để trống!");
		
		Visibility.show(homeView.getJpYC_Vigenere());
		Visibility.hide(homeView.getJpYC_DES_AES(), homeView.getJpYC_RSA(), homeView.getJpYC_CKDT());
	}
	public void startUp() {
		homeView.getJtDoiXung().setSelected(true);
		homeView.getJpDoiXung().getComboBox().setSelectedIndex(0);
		homeView.getJpYC_DES_AES().getJtMaHoa().setSelected(true);
		Visibility.show(homeView.getJpYC_DES_AES().getJpNewKeyAndIV());
		homeView.getJpYC_DES_AES().getJtfKey().setText("");
		homeView.getJpYC_DES_AES().getJtfIV().setText("");
		homeView.getJpYC_DES_AES().getCbMode().setSelectedIndex(0);	
		homeView.getJpYC_DES_AES().getCbPaddingTwo().setSelectedIndex(0);
		homeView.getJpYC_DES_AES().getJtfNewKey().setText("");
		homeView.getJpYC_DES_AES().getJtfNewIV().setText("");
		homeView.getJpYC_DES_AES().getJtfNewKey().setEditable(false);
		homeView.getJpYC_DES_AES().getJtfNewIV().setEditable(false);
		Visibility.show(homeView.getJtText(), homeView.getJtFile(), homeView.getJbCopy(),homeView.getJbSave());
		homeView.getJtText().setSelected(true);
		homeView.getJtFile().setSelected(false);
		homeView.getJbCopy().setSelected(false);
		homeView.getJbCopy().setEnabled(true);
		homeView.getJbSave().setSelected(false);
		homeView.getJbSave().setEnabled(false);
		homeView.getJtaInput().setText("");
		homeView.getJtaInput().setEditable(true);
		homeView.getJtaOutput().setText("Key không được để trống!");
	}
}

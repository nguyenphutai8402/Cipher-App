package ControllerOfView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JToggleButton;

import Utils.Visibility;
import View.Home;

public class BatDoiXungController {
	private Home homeView;
	private BatDoiXungController bDXController;
	public BatDoiXungController(Home homeView) {
		this.homeView = homeView;
		startUp();
		homeView.getJpYC_RSA().enAndDEListenner(new enAndDe());
	}
	class enAndDe implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			JToggleButton jtMaHoa = homeView.getJpYC_RSA().getJtMaHoa();
			JToggleButton jtGiaima = homeView.getJpYC_RSA().getJtGiaiMa();
			if(jtMaHoa.isSelected()) {
				Visibility.hide(homeView.getJpYC_RSA().getJtaPrKey());
				Visibility.show(homeView.getJpYC_RSA().getJtaPuKey());
			}else if(jtGiaima.isSelected()) {
				Visibility.hide(homeView.getJpYC_RSA().getJtaPuKey());
				Visibility.show(homeView.getJpYC_RSA().getJtaPrKey());
			}
		}
	}
	public void startUp() {
		homeView.getJtBatDoiXung().setSelected(true);
		homeView.getJpBatDoiXung().getComboBox().setSelectedIndex(0);
		homeView.getJpYC_RSA().getJtMaHoa().setSelected(true);
		Visibility.show(homeView.getJpYC_RSA().getJpNewKeyAndIV());
		Visibility.show(homeView.getJpYC_RSA().getJtaPuKey());
		homeView.getJpYC_RSA().getJtaPuKey().setText("");
		homeView.getJpYC_RSA().getJtaPrKey().setText("");
		homeView.getJpYC_RSA().getJtaNewPu().setText("");
		homeView.getJpYC_RSA().getJtaNewPu().setEditable(false);
		homeView.getJpYC_RSA().getJtaNewPr().setText("");
		homeView.getJpYC_RSA().getJtaNewPr().setEditable(false);
		Visibility.show(homeView.getJtText(), homeView.getJtFile(), homeView.getJbCopy(),homeView.getJbSave());
		homeView.getJtText().setSelected(true);
		homeView.getJtFile().setSelected(false);
		homeView.getJbCopy().setSelected(false);
		homeView.getJbCopy().setEnabled(true);
		homeView.getJbSave().setSelected(false);
		homeView.getJbSave().setEnabled(false);
		homeView.getJtaInput().setText("");
		homeView.getJtaInput().setEditable(true);
		homeView.getJtaOutput().setText("Khi mã hóa Public Key không được để trống!");
		Visibility.show(homeView.getJpYC_RSA());
		Visibility.hide(homeView.getJpYC_DES_AES(), homeView.getJpYC_Vigenere(), homeView.getJpHash());
		Visibility.hide(homeView.getJpYC_RSA().getJtaPrKey());
	}
}

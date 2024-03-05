package ControllerOfView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Utils.Visibility;
import View.Home;

public class DESViewController {
	private Home homeView;
	public DESViewController(Home homeView) {
		this.homeView = homeView;
		homeView.getJpYC_DES_AES().modeListener(new ModeListener());
		homeView.getJpYC_DES_AES().newKeyAndIVListener(new newKeyAndIVListener());
	}
	class ModeListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			int choose = homeView.getJpYC_DES_AES().getCbMode().getSelectedIndex();
			if(choose == 0) {
				Visibility.hide(homeView.getJpYC_DES_AES().getJtfIV(), homeView.getJpYC_DES_AES().getJlIV());
				Visibility.show(homeView.getJpYC_DES_AES().getCbPaddingTwo());
				Visibility.hide(homeView.getJpYC_DES_AES().getCbPaddingOne());
			}
			if(choose == 1 || choose == 2 || choose == 3) {
				Visibility.show(homeView.getJpYC_DES_AES().getJtfIV(), homeView.getJpYC_DES_AES().getJlIV());
				Visibility.show(homeView.getJpYC_DES_AES().getCbPaddingTwo());
				Visibility.hide(homeView.getJpYC_DES_AES().getCbPaddingOne());
			}else if(choose == 4) {
				Visibility.show(homeView.getJpYC_DES_AES().getJtfIV(), homeView.getJpYC_DES_AES().getJlIV());
				Visibility.show(homeView.getJpYC_DES_AES().getCbPaddingOne());
				Visibility.hide(homeView.getJpYC_DES_AES().getCbPaddingTwo());
			}
		}
	}
	class newKeyAndIVListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if(homeView.getJpYC_DES_AES().getJtMaHoa().isSelected()) {
				Visibility.show(homeView.getJpYC_DES_AES().getJpNewKeyAndIV());
			}else if(homeView.getJpYC_DES_AES().getJtGiaiMa().isSelected()) {
				Visibility.hide(homeView.getJpYC_DES_AES().getJpNewKeyAndIV());
			}
		}
	}
}

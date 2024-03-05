package ControllerOfView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Utils.Visibility;
import View.Home;

public class VigenereController {
	private Home homeView;
	public VigenereController(Home homeView) {
		this.homeView = homeView;
		homeView.getJpYC_Vigenere().getJtMaHoa().setSelected(true);
		homeView.getJpYC_Vigenere().enAndDeListener(new newKey());
	}
	class newKey implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if(homeView.getJpYC_Vigenere().getJtMaHoa().isSelected()) {
				Visibility.show(homeView.getJpYC_Vigenere().getJpNewKeyAndIV());
			}else if(homeView.getJpYC_Vigenere().getJtGiaiMa().isSelected()) {
				Visibility.hide(homeView.getJpYC_Vigenere().getJpNewKeyAndIV());
			}
		}
	}
}

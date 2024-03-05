package ControllerOfView;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JToggleButton;

import Utils.Visibility;
import View.Home;

public class MenuController {
	private Home homeView;
	private ControllerYCKeyAndIv controllerKeyAndIv;
	private DoiXungController dXController;
	private BatDoiXungController bDXController;
	private ControllerYCKeyAndIv yc;
	public MenuController(Home homeView) {
		this.homeView = homeView;
		this.yc = new ControllerYCKeyAndIv(homeView);
		homeView.menuTBGroupListener(new menuListener());
		homeView.textFileListener(new TextFileListener());
		this.controllerKeyAndIv = new ControllerYCKeyAndIv(homeView);
		this.dXController = new DoiXungController(homeView);
		dXController.startUp();
	}

	class menuListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if(homeView.getJtDoiXung().isSelected()) {
				dXController = new DoiXungController(homeView);
				dXController.startUp();
				Visibility.hide(homeView.getJpBatDoiXung(), homeView.getJpHash(), homeView.getJpChuKyDienTu());
				Visibility.show(homeView.getJpDoiXung());
			}else if(homeView.getJtBatDoiXung().isSelected()) {
				bDXController = new BatDoiXungController(homeView);
				Visibility.hide(homeView.getJpDoiXung(), homeView.getJpHash(), homeView.getJpChuKyDienTu());
				Visibility.show(homeView.getJpBatDoiXung());
			}else if(homeView.getJtHash().isSelected()) {
				hashController();
				Visibility.hide(homeView.getJpBatDoiXung(), homeView.getJpDoiXung(), homeView.getJpChuKyDienTu());
				Visibility.show(homeView.getJpHash());
			}else if(homeView.getJtCKDienTu().isSelected()) {
				ckdtController();
				Visibility.hide(homeView.getJpDoiXung(), homeView.getJpBatDoiXung(), homeView.getJpHash());
				Visibility.show(homeView.getJpChuKyDienTu());
			}
		}
	}
	class TextFileListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if(homeView.getJtText().isSelected()) {
				homeView.getJtaInput().setText("");
				homeView.getJtaInput().setEditable(true);
				homeView.getJtaOutput().setEditable(false);
				homeView.getJbCopy().setEnabled(true);
				homeView.getJbSave().setEnabled(false);
				homeView.getJbCopy().setSelected(false);
				homeView.getJbSave().setSelected(false);
			}else if(homeView.getJtFile().isSelected()) {
				homeView.getJtaInput().setEditable(false);
				homeView.getJtaOutput().setEditable(false);
				homeView.getJbCopy().setSelected(false);
				homeView.getJbSave().setSelected(false);
			}
		}
	}
	public void hashController() {
		homeView.getJtHash().setSelected(true);
		homeView.getJpHash().getComboBox().setSelectedIndex(0);
		Visibility.show(homeView.getJtText(), homeView.getJtFile(), homeView.getJbCopy());
		Visibility.hide(homeView.getJbSave());
		homeView.getJtText().setSelected(true);
		homeView.getJtFile().setSelected(false);
		homeView.getJbCopy().setSelected(false);
		homeView.getJbCopy().setEnabled(true);
		homeView.getJbSave().setSelected(false);
		homeView.getJbSave().setEnabled(false);
		homeView.getJtaInput().setText("");
		homeView.getJtaInput().setEditable(true);
		homeView.getJtaOutput().setText("Hãy nhập gì đó!");
		Visibility.hide(homeView.getJpYC_DES_AES(), homeView.getJpYC_Vigenere(), homeView.getJpYC_RSA(), homeView.getJpYC_CKDT());
	}
	public void ckdtController() {
		homeView.getJtCKDienTu().setSelected(true);
		homeView.getJpChuKyDienTu().getComboBox().setSelectedIndex(0);
		homeView.getJpYC_CKDT().getJtfHashFile().setText("");
		Visibility.show(homeView.getJtFile());
		Visibility.hide(homeView.getJtText(), homeView.getJbCopy(), homeView.getJbSave());
		homeView.getJtText().setSelected(false);
		homeView.getJtFile().setSelected(false);
		homeView.getJbCopy().setSelected(false);
		homeView.getJbSave().setSelected(false);
		homeView.getJtaInput().setEditable(false);
		homeView.getJtaInput().setText("");
		homeView.getJtaOutput().setText("Hãy điền mã Hash vào ô!");
		Visibility.hide(homeView.getJpYC_DES_AES(), homeView.getJpYC_Vigenere(), homeView.getJpYC_RSA());
		Visibility.show(homeView.getJpYC_CKDT());
	}
}

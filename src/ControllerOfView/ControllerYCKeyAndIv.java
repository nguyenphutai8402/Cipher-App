package ControllerOfView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JToggleButton;

import Config.Alphabet;
import Model.DES;
import Model.Hill;
import Model.RSA;
import Utils.Check;
import View.Home;

public class ControllerYCKeyAndIv {
	private DES dESModel;
	private Home homeView;
	private Hill hillModel;
	private RSA rSAModel;
	public ControllerYCKeyAndIv(Home homeView) {
		super();
		this.homeView = homeView;
		this.hillModel = new Hill();
		this.rSAModel = new RSA();
	}
	
	public String notifyKeyAndIv16Length() {
		String result = "";
		String key = homeView.getJpYC_DES_AES().getJtfKey().getText();
		String iv = homeView.getJpYC_DES_AES().getJtfIV().getText();
		if(homeView.getJpYC_DES_AES().getCbMode().getSelectedIndex() == 0) {
			if(Check.checkEmpty(key)){
				result = "Key không được để trống!";
			}else if(Check.isBase64WithLength16(key) == false) {
				result = "Key không hợp lệ!";
			}else {
				result = "Key và IV hợp lệ!";
			}
		}else {
			if(Check.checkEmpty(key) && Check.checkEmpty(iv)) {
				result = "Key không được để trống!\nIV không được để trống!";
			}else if(Check.checkEmpty(iv)) {
				result = "IV không được để trống!";
			}else if(Check.checkEmpty(key)){
				result = "Key không được để trống!";
			}else{
				if(Check.isBase64WithLength16(key) == false && Check.isBase64WithLength16(iv) == false) {
					result = "Key không hợp lệ!\nIV không hợp lệ!";
				}else if(Check.isBase64WithLength16(key) == false) {
					result = "Key không hợp lệ!";
				}else if(Check.isBase64WithLength16(iv) == false){
					result = "IV không hợp lệ!";
				}else {
					result = "Key và IV hợp lệ!";
				}
			}
		}
		return result;
	}
	
	public String notifyKeyAndIv() {
		String result = "";
		String key = homeView.getJpYC_DES_AES().getJtfKey().getText();
		String iv = homeView.getJpYC_DES_AES().getJtfIV().getText();
		if(homeView.getJpYC_DES_AES().getCbMode().getSelectedIndex() == 0) {
			if(Check.checkEmpty(key)){
				result = "Key không được để trống!";
			}else if(Check.isBase64WithLength8(key) == false) {
				result = "Key không hợp lệ!";
			}else {
				result = "Key và IV hợp lệ!";
			}
		}else {
			if(Check.checkEmpty(key) && Check.checkEmpty(iv)) {
				result = "Key không được để trống!\nIV không được để trống!";
			}else if(Check.checkEmpty(iv)) {
				result = "IV không được để trống!";
			}else if(Check.checkEmpty(key)){
				result = "Key không được để trống!";
			}else{
				if(Check.isBase64WithLength8(key) == false && Check.isBase64WithLength8(iv) == false) {
					result = "Key không hợp lệ!\nIV không hợp lệ!";
				}else if(Check.isBase64WithLength8(key) == false) {
					result = "Key không hợp lệ!";
				}else if(Check.isBase64WithLength8(iv) == false){
					result = "IV không hợp lệ!";
				}else {
					result = "Key và IV hợp lệ!";
				}
			}
		}
		return result;
	}
	public boolean checkKeyAndIv16Length() {
		boolean result = false;
		String key = homeView.getJpYC_DES_AES().getJtfKey().getText();
		String iv = homeView.getJpYC_DES_AES().getJtfIV().getText();
		if(homeView.getJpYC_DES_AES().getCbMode().getSelectedIndex() == 0) {
			if(Check.checkEmpty(key)){
				result = false;
			}else if(Check.isBase64WithLength16(key) == false) {
				result = false;
			}else{
				result = true;
			}
		}else {
			if(Check.checkEmpty(key) && Check.checkEmpty(iv)) {
				result = false;
			}else if(Check.checkEmpty(iv)) {
				result = false;
			}else if(Check.checkEmpty(key)){
				result = false;
			}else{
				if(Check.isBase64WithLength16(key) == false && Check.isBase64WithLength16(iv) == false) {
					result = false;
				}else if(Check.isBase64WithLength16(key) == false) {
					result = false;
				}else if(Check.isBase64WithLength16(iv) == false){
					result = false;
				}else{
					result = true;
				}
			}
		}
		return result;
	}
	public boolean checkKeyAndIv() {
		boolean result = false;
		String key = homeView.getJpYC_DES_AES().getJtfKey().getText();
		String iv = homeView.getJpYC_DES_AES().getJtfIV().getText();
		if(homeView.getJpYC_DES_AES().getCbMode().getSelectedIndex() == 0) {
			if(Check.checkEmpty(key)){
				result = false;
			}else if(Check.isBase64WithLength8(key) == false) {
				result = false;
			}else{
				result = true;
			}
		}else {
			if(Check.checkEmpty(key) && Check.checkEmpty(iv)) {
				result = false;
			}else if(Check.checkEmpty(iv)) {
				result = false;
			}else if(Check.checkEmpty(key)){
				result = false;
			}else{
				if(Check.isBase64WithLength8(key) == false && Check.isBase64WithLength8(iv) == false) {
					result = false;
				}else if(Check.isBase64WithLength8(key) == false) {
					result = false;
				}else if(Check.isBase64WithLength8(iv) == false){
					result = false;
				}else{
					result = true;
				}
			}
		}
		return result;
	}
	public boolean checkModeAndPaddingOfEnFile() {
		boolean result = false;
		JComboBox cbMode = homeView.getJpYC_DES_AES().getCbMode();
		JComboBox cbPaddingTwo = homeView.getJpYC_DES_AES().getCbPaddingTwo();
		if(cbMode.getSelectedIndex() == 0) {
			if(cbPaddingTwo.getSelectedIndex() == 0) {
				result = false;
			}else {
				result = true;
			}
		}else if(cbMode.getSelectedIndex() == 1) {
			if(cbPaddingTwo.getSelectedIndex() == 0) {
				result = false;
			}else {
				result = true;
			}
		}else {
			result = true;
		}
		return result;
	}
	public String notifyModeAndPaddingOfEnFile() {
		String result = "";
		JComboBox cbMode = homeView.getJpYC_DES_AES().getCbMode();
		JComboBox cbPaddingTwo = homeView.getJpYC_DES_AES().getCbPaddingTwo();
		if(cbMode.getSelectedIndex() == 0) {
			if(cbPaddingTwo.getSelectedIndex() == 0) {
				result = "Chọn Mode và Padding này khi mã hóa dễ gặp lỗi. Vui lòng chọn Mode và Padding khác!";
			}else {
				result = "Mode và Padding hợp lệ!";
			}
		}else if(cbMode.getSelectedIndex() == 1) {
			if(cbPaddingTwo.getSelectedIndex() == 0) {
				result = "Chọn Mode và Padding này khi mã hóa dễ gặp lỗi. Vui lòng chọn Mode và Padding khác!";
			}else {
				result = "Mode và Padding hợp lệ!";
			}
		}else {
			result = "Mode và Padding hợp lệ!";
		}
		return result;
	}
	public String notifyEnFile() {
		return notifyKeyAndIv() + "\n" + notifyModeAndPaddingOfEnFile();
	}
	public String notifyFileOfECB() {
		return notifyKeyAndIv16Length() + "\n" + notifyModeAndPaddingOfEnFile();
	}
	public boolean checkKeyOfVigenere() {
		boolean result = true;
		String key = homeView.getJpYC_Vigenere().getJtfKey().getText();
		int chooseLang = homeView.getJpYC_Vigenere().getCbLang().getSelectedIndex();
		char[] keyCharArray = key.toCharArray();
		if(Check.checkEmpty(key)) {
			result = false;
		}else {
			if(chooseLang == 0) {
				for(int i = 0; i < keyCharArray.length; i++) {
					if(!Check.contain(Alphabet.ALPHABET_ENG, keyCharArray[i])) {
						result = false;
					}
				}
			}else if(chooseLang == 1){
				for(int i = 0; i < keyCharArray.length; i++) {
					if(!Check.contain(Alphabet.ALPHABET_VI, keyCharArray[i])) {
						result = false;
					}
				}
			}
		}
		return result;
	}
	public String notifyKeyOfVigenere() {
		String result = "";
		String key = homeView.getJpYC_Vigenere().getJtfKey().getText();
		if(Check.checkEmpty(key)) {
			result = "Key không được để trống!";
		}else {
			if(checkKeyOfVigenere()) {
				result = "Key hợp lệ!";
			}else {
				result = "Key không hợp lệ do có chứa ký tự đặc biệt, viết hoa hoặc không nằm trong phạm vi bảng chữ cái đang chọn!";
			}
		}
		return result;
	}
	public boolean keyValid() {
		boolean result = true;
		int chooseLang = homeView.getJpYC_Vigenere().getCbLang().getSelectedIndex();
		String key = homeView.getJpYC_Vigenere().getJtfKey().getText();
		int[][] keyMatrix = hillModel.convertToMatrix(key);
        int dinhThuc = hillModel.calculateDeterminant(keyMatrix);
        if(dinhThuc != 0) {
        	if(chooseLang == 0) {
        		if(hillModel.isGCDOne(dinhThuc, Alphabet.ALPHABET_ENG.length) || hillModel.modInverse(dinhThuc, Alphabet.ALPHABET_ENG.length) != -1) {
        			result = true;
        		}else {
        			result = false;
        		}
			}else if(chooseLang == 1){
				if(hillModel.isGCDOne(dinhThuc, Alphabet.ALPHABET_VI.length) || hillModel.modInverse(dinhThuc, Alphabet.ALPHABET_VI.length) != -1) {
        			result = true;
        		}else {
        			result = false;
        		}
			}
        }else {
        	result = false;
        }
		return result;
	}
	public boolean checkKeyOfHill() {
		boolean result = true;
		String key = homeView.getJpYC_Vigenere().getJtfKey().getText();
        String[] numbers = key.split(" ");
        if (numbers.length != 4) {
            result = false;
        }
        for (String number : numbers) {
            try {
                int num = Integer.parseInt(number);
                if (num < 0 || num >= 100) {
                    result = false;
                }
            } catch (NumberFormatException e) {
                result = false;
            }
        }
        try {
        	if(keyValid()) {
            	result = true;
            }else {
            	result = false;
            }
		} catch (Exception e) {
			// TODO: handle exception
			result = false;
		}
		return result;
    }
	public String notifyKeyOfHill() {
		String result = "";
		String key = homeView.getJpYC_Vigenere().getJtfKey().getText();
		if(Check.checkEmpty(key)) {
			result = "Key không được để trống!";
		}else {
			if(checkKeyOfHill()) {
				if(keyValid()) {
		        	result = "Key hợp lệ!";
		        }else {
		        	result = "Key không hợp lệ!" + "\n" +  "Do định thức của Key = 1!" + 
		        "\n" + "UCLN của định thức và độ dài của bảng chữ cái đã chọn = 1!";
		        }
			}else {
				result = "Key không hợp lệ! Ví dụ về Key:" + "\n" + " 1 2 3 4";
			}
		}
		return result;
	}
	public boolean checkPuKeyAndPrKey() {
		boolean result = true;
		String puKey = homeView.getJpYC_RSA().getJtaPuKey().getText();
		String prKey = homeView.getJpYC_RSA().getJtaPrKey().getText();
		JToggleButton jtMaHoa = homeView.getJpYC_RSA().getJtMaHoa();
		JToggleButton jtGiaiMa = homeView.getJpYC_RSA().getJtGiaiMa();
		if(jtMaHoa.isSelected()) {
			if(Check.checkEmpty(puKey)) {
				result = false;
			}else {
				try {
					rSAModel.setPublicKeyFromString(puKey);
					result = true;
				} catch (Exception e) {
					result = false;
				}
			}
		}else if(jtGiaiMa.isSelected()){
			if(Check.checkEmpty(prKey)) {
				result = false;
			}else {
				try {
					rSAModel.setPrivateKeyFromString(prKey);
					result = true;
				} catch (Exception e) {
					result = false;
				}
			}
		}
		return result;
	}
	public String notifyPukeyAndPrKey() {
		String result = "";
		String puKey = homeView.getJpYC_RSA().getJtaPuKey().getText();
		String prKey = homeView.getJpYC_RSA().getJtaPrKey().getText();
		JToggleButton jtMaHoa = homeView.getJpYC_RSA().getJtMaHoa();
		JToggleButton jtGiaiMa = homeView.getJpYC_RSA().getJtGiaiMa();
		if(jtMaHoa.isSelected()) {
			if(Check.checkEmpty(puKey)) {
				result = "Khi mã hóa Public Key không được để trống!";
			}else {
				try {
					rSAModel.setPublicKeyFromString(puKey);
					result = "Hợp lệ!";
				} catch (Exception e) {
					result = "Public Key không hợp lệ!";
				}
			}
		}else if(jtGiaiMa.isSelected()){
			if(Check.checkEmpty(prKey)) {
				result = "Khi giải mã Private Key không được để trống!";
			}else {
				try {
					rSAModel.setPrivateKeyFromString(prKey);
					result = "Hợp lệ!";
				} catch (Exception e) {
					result = "Private Key không hợp lệ!";
				}
			}
		}
		return result;
	}
	public boolean checkHashInput() {
		String hashInput = homeView.getJpYC_CKDT().getJtfHashFile().getText();
		if(Check.checkEmpty(hashInput)) {
			return false;
		}else {
			return true;
		}
	}
	public String notifyHashInput() {
		String hashInput = homeView.getJpYC_CKDT().getJtfHashFile().getText();
		if(Check.checkEmpty(hashInput)) {
			return "Hãy điền mã Hash vào ô!";
		}else {
			return "Hợp lệ!";
		}
	}
}

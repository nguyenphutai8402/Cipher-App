package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Random;

import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.swing.JComboBox;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JToggleButton;

import Config.Alphabet;
import Model.AES;
import Model.DES;
import Model.Hill;
import Model.RSA;
import View.Home;

public class CreateKeyAndIVController {
	private Home homeView;
	private DES dESModel;
	private AES aESModel;
	private Hill hillModel;
	private RSA rSAModel;
	public CreateKeyAndIVController(Home homeView) {
		this.homeView = homeView;
		this.dESModel = new DES();
		this.aESModel = new AES();
		this.hillModel = new Hill();
		this.rSAModel = new RSA();
		homeView.getJpYC_DES_AES().createKeyAndIVListener(new CreateKeyAndIVListener());
		homeView.getJpYC_Vigenere().createKey(new CreateKeyAndIVListener());
		homeView.getJpYC_RSA().createPairKey(new CreatePairKey());
	}
	class CreateKeyAndIVListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			JToggleButton jtDoiXung = homeView.getJtDoiXung();
			JToggleButton jtBatDoiXung = homeView.getJtBatDoiXung();
			JComboBox cbDoiXung = homeView.getJpDoiXung().getComboBox();
			JToggleButton jtText = homeView.getJtText();
			JTextField jtNewKey = homeView.getJpYC_DES_AES().getJtfNewKey();
			JTextField jtNewIV = homeView.getJpYC_DES_AES().getJtfNewIV();
			if(jtDoiXung.isSelected() && cbDoiXung.getSelectedIndex() == 0) {
				try {
					SecretKey key = dESModel.createKey();
					IvParameterSpec iv = dESModel.createIV();
					byte[] keyBytes = dESModel.getKey().getEncoded();
			        String base64Key = Base64.getEncoder().encodeToString(keyBytes);
			        String base64IV = Base64.getEncoder().encodeToString(dESModel.getIvBase64());
			        jtNewKey.setText(base64Key);
			        jtNewIV.setText(base64IV);
				} catch (NoSuchAlgorithmException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}else if((jtDoiXung.isSelected() && cbDoiXung.getSelectedIndex() == 1) || (jtDoiXung.isSelected() && cbDoiXung.getSelectedIndex() == 2)){
				try {
					SecretKey key = aESModel.createKey();
					IvParameterSpec iv = aESModel.createIV();
					byte[] keyBytes = aESModel.getKey().getEncoded();
			        String base64Key = Base64.getEncoder().encodeToString(keyBytes);
			        String base64IV = Base64.getEncoder().encodeToString(aESModel.getIvBase64());
			        jtNewKey.setText(base64Key);
			        jtNewIV.setText(base64IV);
				} catch (NoSuchAlgorithmException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}else if(jtDoiXung.isSelected() && cbDoiXung.getSelectedIndex() == 3) {
				JTextField jtNewKeyVigenere = homeView.getJpYC_Vigenere().getJtfNewKey();
		        String key = createKeyForVigener();
		        jtNewKeyVigenere.setText(key);
			}else if(jtDoiXung.isSelected() && cbDoiXung.getSelectedIndex() == 4) {
				JTextField jtNewKeyVigenere = homeView.getJpYC_Vigenere().getJtfNewKey();
				int lang = homeView.getJpYC_Vigenere().getCbLang().getSelectedIndex();
				if(lang == 0) {
					String key = createKeyHill(4, Alphabet.ALPHABET_ENG.length);
			        jtNewKeyVigenere.setText(key);
				}else if(lang == 1){
					String key = createKeyHill(4, Alphabet.ALPHABET_VI.length);
			        jtNewKeyVigenere.setText(key);
				}
			}
		}
	}
	class CreatePairKey implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			try {
				JTextArea jtaNewPu = homeView.getJpYC_RSA().getJtaNewPu();
				JTextArea jtaNewPr = homeView.getJpYC_RSA().getJtaNewPr();
				rSAModel.generateKey();
				jtaNewPu.setText(rSAModel.getPublicKeyAsString());
				jtaNewPr.setText(rSAModel.getPrivateKeyAsString());
			} catch (NoSuchAlgorithmException e1) {
				// TODO Auto-generated catch block
			}
		}
	}
	public String keyHill(int length, int lang) {
		Random random = new Random();
        StringBuilder randomNumberStringBuilder = new StringBuilder();
        for (int i = 0; i < length; i++) {
            int randomDigit = random.nextInt(lang);
            randomNumberStringBuilder.append(randomDigit);
            if (i < length - 1) {
                randomNumberStringBuilder.append(" ");
            }
        }
		return randomNumberStringBuilder.toString();
	}
	public String createKeyHill(int length, int lang) {
		
        boolean check = false;
        String key = "";
        while(check == false) {
        	key = keyHill(length, lang);
            int[][] keyMatrix = hillModel.convertToMatrix(key);
            int dinhThuc = hillModel.calculateDeterminant(keyMatrix);
        	if(dinhThuc != 0) {
        		try {
        			if(hillModel.isGCDOne(dinhThuc, lang) || hillModel.modInverse(dinhThuc, lang) != -1) {
            			check = true;
            		}
				} catch (Exception e) {
					
				}
            }else {
            }
        }
		return key;
    }
	
	public String generateKeyVigenere(char[] charArray, int length) {
        Random random = new Random();
        StringBuilder randomStringBuilder = new StringBuilder();
        for (int i = 0; i < length; i++) {
            int randomIndex = random.nextInt(charArray.length);
            char randomChar = charArray[randomIndex];
            randomStringBuilder.append(randomChar);
        }
        return randomStringBuilder.toString();
    }
	public String createKeyForVigener() {
		String result = "";
		int lang = homeView.getJpYC_Vigenere().getCbLang().getSelectedIndex();
		if(lang == 0) {
			result = generateKeyVigenere(Alphabet.ALPHABET_ENG, 6);
		}else if(lang == 1) {
			result = generateKeyVigenere(Alphabet.ALPHABET_VI, 6);
		}
		return result;
	}
}

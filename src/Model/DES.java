package Model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import javax.swing.JFileChooser;

import Config.DES_AES_Config;

public class DES {
	private SecretKey key;	
	private byte[] ivBase64;
	private IvParameterSpec iv;
	private String mode;
	private String padding;
	public SecretKey getKey() {
		return key;
	}
	public void setKey(SecretKey key) {
		this.key = key;
	}
	public byte[] getIvBase64() {
		return ivBase64;
	}
	public void setIvBase64(byte[] ivBase64) {
		this.ivBase64 = ivBase64;
	}
	public IvParameterSpec getIv() {
		return iv;
	}
	public void setIv(IvParameterSpec iv) {
		this.iv = iv;
	}
	public String getMode() {
		return mode;
	}
	public void setMode(String mode) {
		this.mode = mode;
	}
	public String getPadding() {
		return padding;
	}
	public void setPadding(String padding) {
		this.padding = padding;
	}
	
	public SecretKey createKey() throws NoSuchAlgorithmException {
		KeyGenerator keyGenerator = KeyGenerator.getInstance("DES");
		keyGenerator.init(56);
		key = keyGenerator.generateKey();
		return key;
	}
	public SecretKey generateSecretKeyFromBase64(String base64Key) {
        try {
            byte[] keyBytes = Base64.getDecoder().decode(base64Key);
            key = new SecretKeySpec(keyBytes, "DES");
            return key;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
	
	public byte[] createBytesIV() {
		ivBase64 = new byte[8];
	    new SecureRandom().nextBytes(ivBase64);
	    return ivBase64;
	}
	public IvParameterSpec generateIVFromBase64(String base64IV) {
	    byte[] iVBytes = Base64.getDecoder().decode(base64IV);
	    iv = new IvParameterSpec(iVBytes);
	    return iv;
	}
	public IvParameterSpec createIV() {
		iv = new IvParameterSpec(createBytesIV());
		return iv;
	}
	public String encrypt(String text) throws Exception {
		if(mode.equals(DES_AES_Config.MODE_ECB)) {
			if (key == null) return "";
			String instance = "DES/" + mode + "/" + padding;
			Cipher cipher = Cipher.getInstance(instance);
			cipher.init(Cipher.ENCRYPT_MODE, key);
			byte[] plainText = text.getBytes("UTF-8");
			byte[] cipherText = cipher.doFinal(plainText);
			return Base64.getEncoder().encodeToString(cipherText);
		}else {
			if (key == null) return "";
			String instance = "DES/" + mode + "/" + padding;
			Cipher cipher = Cipher.getInstance(instance);
			cipher.init(Cipher.ENCRYPT_MODE, key, iv);
			byte[] plainText = text.getBytes("UTF-8");
			byte[] cipherText = cipher.doFinal(plainText);
			return Base64.getEncoder().encodeToString(cipherText);
		}
	}
	public String decrypt(String input) throws Exception {
		if(mode.equals(DES_AES_Config.MODE_ECB)) {
			if(key == null) return null;
			String instance = "DES/" + mode + "/" + padding;
			Cipher cipher = Cipher.getInstance(instance);
			cipher.init(Cipher.DECRYPT_MODE, key);
			byte[] plainText = cipher.doFinal(Base64.getDecoder().decode(input));
			String output = new String(plainText, "UTF-8");
			return output;
		}else {
			if(key == null) return null;
			String instance = "DES/" + mode + "/" + padding;
			Cipher cipher = Cipher.getInstance(instance);
			cipher.init(Cipher.DECRYPT_MODE, key, iv);
			byte[] plainText = cipher.doFinal(Base64.getDecoder().decode(input));
			String output = new String(plainText, "UTF-8");
			return output;
		}
	}
	public void encryptFile(String srcFile, String destFile) throws Exception {
        FileInputStream fis = null;
        FileOutputStream fos = null;
        try {
            if (key == null) throw new Exception("Key Not Found");
            File fileSrc = new File(srcFile);
            if (fileSrc.isFile()) {
    	        String instance = "DES/" + mode + "/" + padding;
                Cipher cipher = Cipher.getInstance(instance);
                if (mode.equals(DES_AES_Config.MODE_ECB)) cipher.init(Cipher.ENCRYPT_MODE, key);
                else cipher.init(Cipher.ENCRYPT_MODE, key, iv);

                fis = new FileInputStream(fileSrc);
                fos = new FileOutputStream(destFile);

                byte[] input_byte = new byte[1024];
                int bytes_read;
                while ((bytes_read = fis.read(input_byte)) != -1) {
                    byte[] output_byte = cipher.update(input_byte, 0, bytes_read);
                    if (output_byte != null) fos.write(output_byte);
                }
                byte[] output = cipher.doFinal();
                if (output != null) fos.write(output);
                fos.flush();
            }
        } finally {
            if (fis != null) fis.close();
            if (fos != null) fos.close();
        }

    }
	
	public void decryptFile(String srcFile, String destFile) throws Exception {
        FileInputStream fis = null;
        FileOutputStream fos = null;
        try {
            if (key == null) throw new Exception("Key Not Found");
            File fileSrc = new File(srcFile);
            if (fileSrc.isFile()) {
            	String instance = "DES/" + mode + "/" + padding;
                Cipher cipher = Cipher.getInstance(instance);
                if (mode.equals(DES_AES_Config.MODE_ECB)) cipher.init(Cipher.DECRYPT_MODE, key);
                else cipher.init(Cipher.DECRYPT_MODE, key, iv);

                fis = new FileInputStream(fileSrc);
                fos = new FileOutputStream(destFile);

                byte[] input_byte = new byte[1024];
                int bytes_read;
                while ((bytes_read = fis.read(input_byte)) != -1) {
                    byte[] output_byte = cipher.update(input_byte, 0, bytes_read);
                    if (output_byte != null) fos.write(output_byte);
                }
                byte[] output = cipher.doFinal();
                if (output != null) fos.write(output);
                fos.flush();
            }
        } finally {

            if (fis != null) fis.close();
            if (fos != null) fos.close();
        }

    }
	public static void main(String[] args) throws Exception{
		
	}
}

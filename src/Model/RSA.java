package Model;

import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

public class RSA {
    private KeyPair keyPair;
    private PublicKey publicKey;
    private PrivateKey privateKey;
    private String mode;
    
    public PublicKey getPublicKey() {
		return publicKey;
	}
	public void setPublicKey(PublicKey publicKey) {
		this.publicKey = publicKey;
	}
	public PrivateKey getPrivateKey() {
		return privateKey;
	}
	public void setPrivateKey(PrivateKey privateKey) {
		this.privateKey = privateKey;
	}
	public String getMode() {
		return mode;
	}
	public void setMode(String mode) {
		this.mode = mode;
	}
	public void generateKey() throws NoSuchAlgorithmException {
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(2048);
        keyPair = keyPairGenerator.generateKeyPair();
        publicKey = keyPair.getPublic();
        privateKey = keyPair.getPrivate();
        mode = "";
    }
    public String getPublicKeyAsString() {
        PublicKey publicKey = this.publicKey;
        byte[] publicKeyBytes = publicKey.getEncoded();
        String publicKeyString = Base64.getEncoder().encodeToString(publicKeyBytes);
        return publicKeyString;
    }
    public String getPrivateKeyAsString() {
        PrivateKey privateKey = this.privateKey;
        byte[] privateKeyBytes = privateKey.getEncoded();
        String privateKeyString = Base64.getEncoder().encodeToString(privateKeyBytes);
        return privateKeyString;
    }
    
    public void setPublicKeyFromString(String publicKeyString) throws Exception {
        byte[] publicKeyBytes = Base64.getDecoder().decode(publicKeyString);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(publicKeyBytes);
        PublicKey publicKey = keyFactory.generatePublic(keySpec);
        this.publicKey = publicKey;
    }
    
    public void setPrivateKeyFromString(String privateKeyString) throws Exception {
        byte[] privateKeyBytes = Base64.getDecoder().decode(privateKeyString);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(privateKeyBytes);
        PrivateKey privateKey = keyFactory.generatePrivate(keySpec);
        this.privateKey = privateKey;
    }
//
//    // Hàm để thiết lập PublicKey từ chuỗi
//    public PublicKey getPublicKeyFromString(String publicKeyString) throws Exception {
//        byte[] publicKeyBytes = Base64.getDecoder().decode(publicKeyString);
//
//        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
//        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(publicKeyBytes);
//        return keyFactory.generatePublic(keySpec);
//    }

    public String encrypt(String data) throws Exception {
        Cipher cipher = Cipher.getInstance(mode);
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        byte[] output = cipher.doFinal(data.getBytes(StandardCharsets.UTF_8));
        return Base64.getEncoder().encodeToString(output);
    }


    public String decrypt(String data) throws Exception {
        Cipher cipher = Cipher.getInstance(mode);
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        byte[] output = cipher.doFinal(Base64.getDecoder().decode(data));
        return new String(output, StandardCharsets.UTF_8);
    }

    public void fileEncrypt(String inputPath, String outputPath) throws Exception  {
        KeyGenerator keyGen = KeyGenerator.getInstance("AES");
        keyGen.init(256);
        byte[] iv = new byte[16];
        IvParameterSpec spec = new IvParameterSpec(iv);
        SecretKey secretKey = keyGen.generateKey();
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, secretKey,spec);

        CipherInputStream inputStream = new CipherInputStream(new BufferedInputStream(new FileInputStream(inputPath)), cipher);
        DataOutputStream dataOutputStream = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(outputPath)));

        String keyString = Base64.getEncoder().encodeToString(secretKey.getEncoded());
        dataOutputStream.writeUTF(encrypt(keyString));
        dataOutputStream.writeLong(new File(inputPath).length());
        dataOutputStream.writeUTF(Base64.getEncoder().encodeToString(iv));

        byte[] buff = new byte[1024];
        int i;
        while ((i=inputStream.read(buff)) != -1) {
            dataOutputStream.write(buff, 0, i);
        }
        inputStream.close();
        dataOutputStream.flush();
        dataOutputStream.close();
    }

    public void fileDecrypt(String inputPath, String outputPath) throws Exception  {
        DataInputStream dis = new DataInputStream(new BufferedInputStream(new FileInputStream(inputPath)));
        String keyString = dis.readUTF();
        long size = dis.readLong();
        byte[] iv = Base64.getDecoder().decode(dis.readUTF());

        SecretKey secretKey = new SecretKeySpec(Base64.getDecoder().decode(decrypt(keyString)), "AES");

        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE, secretKey, new IvParameterSpec(iv));
        CipherInputStream cis = new CipherInputStream(dis, cipher);
        BufferedOutputStream bof = new BufferedOutputStream(new FileOutputStream(outputPath));


        byte[] buff = new byte[1024];
        int i;
        while ((i=cis.read(buff)) != -1) {
            bof.write(buff, 0, i);
        }
        cis.close();
        bof.close();
    }

    public static void main(String[] args) throws Exception {
        
    }
}

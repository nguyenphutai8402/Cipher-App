package Model;

import Config.Alphabet;
import Utils.Check;

public class Vigenere {
	public String encrypt(char[] alphabet, String data, String key) {
		String result = "";
		int index_data = 0;
		int shift = 0;
		for(int i = 0; i < data.length(); i++) {
			char ch = data.charAt(i);
			if(Character.isLetter(ch) && Check.contain(alphabet, ch)) {
				index_data = Check.index(alphabet, Character.toLowerCase(ch));
				shift = Check.index(alphabet, Character.toLowerCase(key.charAt(i % key.length())));
				char encrypt = alphabet[(index_data + shift) % alphabet.length];
				if(Character.isLowerCase(ch)) {
					result += encrypt;
				}else {
					result += Character.toUpperCase(encrypt);
				}
			}else {
				result += ch;
			}
		}
		return result;
	}
	
	public String decrypt(char[] alphabet, String data, String key) {
		String result = "";
		int index_data = 0;
		int shift = 0;
		for(int i = 0; i < data.length(); i++) {
			char ch = data.charAt(i);
			if(Character.isLetter(ch) && Check.contain(alphabet, ch)) {
				index_data = Check.index(alphabet, Character.toLowerCase(ch));
				shift = Check.index(alphabet, Character.toLowerCase(key.charAt(i % key.length())));
				char decrypt = alphabet[(index_data - shift + alphabet.length) % alphabet.length];
				if(Character.isLowerCase(ch)) {
					result += decrypt;
				}else {
					result += Character.toUpperCase(decrypt);
				}
			}else {
				result += ch;
			}
		}
		return result;
	}
	public static void main(String[] args) {
		
	}
}

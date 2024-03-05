package Utils;

import java.util.Base64;

public class Check {
	public static boolean checkEmpty(String text) {
		return text.isEmpty();
	}
	public static boolean isBase64WithLength8(String input) {
        try {
            byte[] decodedBytes = Base64.getDecoder().decode(input);
            return decodedBytes.length == 8;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }
	public static boolean isBase64WithLength16(String input) {
		try {
            byte[] decodedBytes = Base64.getDecoder().decode(input);
            return decodedBytes.length == 16;
        } catch (IllegalArgumentException e) {
            return false;
        }
	}
	public static int index(char[] alphabet, char ch) {
		int result = 1000;
		for(int j = 0; j < alphabet.length; j++) {
			if(ch == alphabet[j]) {
				result = j;
			}
		}
		return result;
	}
	public static boolean contain(char[] alphabet, char ch) {
		boolean result = false;
		for(int j = 0; j < alphabet.length; j++) {
			if(ch == alphabet[j]) {
				result = true;
			}
		}
		return result;
	}
	public static void main(String[] args) {
	}
}

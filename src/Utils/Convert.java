package Utils;

import java.util.Base64;

public class Convert {
	public static String convertBase64(byte[] bytes) {
		return Base64.getEncoder().encodeToString(bytes);
	}
}

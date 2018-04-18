import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;


public class Scrambler {
	private static Cipher c;

	public Scrambler() {
		try {
			c = Cipher.getInstance("AES/CBC/PKCS5Padding");
		}
		catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		catch (NoSuchPaddingException e) {
			e.printStackTrace();			
		}
		catch (Exception e) {
			System.out.println("Unspecified error: Scramble");
			e.printStackTrace();
		}
	}

	public static String encrypt(String decryptedString, SecretKey key) {
        byte[] iv = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
        IvParameterSpec ivspec = new IvParameterSpec(iv);
		try {
			c.init(Cipher.ENCRYPT_MODE, key, ivspec);
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		} catch (InvalidAlgorithmParameterException e) {
			e.printStackTrace();
		}
		byte[] encrypted = null;
		try {
			encrypted = Base64.getEncoder().encode(c.doFinal(decryptedString.getBytes()));
		} catch (IllegalBlockSizeException e) {
			e.printStackTrace();
		} catch (BadPaddingException e) {
			e.printStackTrace();
		}		
		//Run AES algorithm against dS using key
		//Return the cipherText
		return encrypted.toString();
	}

	public static String decrypt(String encryptedString, SecretKey key) {
		byte[] dc = null;
		try {
	        byte[] iv = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
	        IvParameterSpec ivspec = new IvParameterSpec(iv);
			c.init(Cipher.DECRYPT_MODE, key, ivspec);
			dc = Base64.getDecoder().decode(c.doFinal(encryptedString.getBytes()));
		}
		catch (Exception e){
			e.printStackTrace();
		}
		return dc.toString();

	}

}

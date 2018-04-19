package security;

import java.security.NoSuchAlgorithmException;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;


public class Key {
	private SecretKey myKey;
	 
	public Key() {
		try {
			KeyGenerator kg = KeyGenerator.getInstance("AES");
			kg.init(128);
			myKey = kg.generateKey();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		
	}
	
	public SecretKey getKey() {
		return myKey;
	}

}

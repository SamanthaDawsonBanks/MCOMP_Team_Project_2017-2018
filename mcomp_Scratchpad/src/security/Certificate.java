package security;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;

/**
 * A class to generate and store a Public/Private Key pairing in
 * the Java Keystore. Used by the Leader of a Herd to encrypt
 * communication between itself and the Herd member.
 * 
 * This class may well merge with the Leader object, depending
 * on SOLID principles application.
 * 
 * @author Stephen Pope 15836791
 *
 */

public class Certificate {
	private static Certificate theCert = new Certificate();
	private KeyPairGenerator keyFactory;
	private KeyPair leaderPair;
	private PublicKey publicKey;
	private PrivateKey privateKey;
	
	/**
	 * The Certificate Constructor.
	 * 
	 * This class implements the Singleton Pattern from the GoF.
	 * As such, the constructor is private to prevent multiple
	 * certificates being generated.
	 */
	private Certificate() {
		leaderPair = keyFactory.generateKeyPair();
		publicKey = leaderPair.getPublic();
		privateKey = leaderPair.getPrivate();
	}
	
	/**
	 * Get the created instance of the certificate.
	 * 
	 * @return The Certificate containing the Public/Private KeyPair.
	 */
	public static Certificate getInstance() {
		return theCert;
	}

	/**
	 * Retrieve the Public Key from the certificate, so that it may
	 * be handed to Members of the Herd by the Leader.
	 * 
	 * @return The Public Key
	 */
	public PublicKey getPublicKey() {
		return publicKey;		
	}

}

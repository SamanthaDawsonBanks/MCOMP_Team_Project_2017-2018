package security;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;

public class Certificate {
	private static Certificate theCert = new Certificate();
	private KeyPairGenerator keyFactory;
	private KeyPair leaderPair;
	private PublicKey publicKey;
	private PrivateKey privateKey;

	private Certificate() {
		leaderPair = keyFactory.generateKeyPair();
		publicKey = leaderPair.getPublic();
		privateKey = leaderPair.getPrivate();
	}

	public static Certificate getInstance() {
		return theCert;
	}

	public PublicKey getPublicKey() {
		return publicKey;		
	}

}

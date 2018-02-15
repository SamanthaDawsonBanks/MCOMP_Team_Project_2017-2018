package security;

import java.security.KeyPair;
import java.security.KeyPairGenerator;

public class Certificate {
	private KeyPairGenerator keyFactory;
	private KeyPair leaderKey;
	
	private Certificate() {
		leaderKey = keyFactory.generateKeyPair();
	}
	
	public Certificate getInstance() {
		if(leaderKey == null) {
			return new Certificate();
		}
		else return this;
		
	}

}

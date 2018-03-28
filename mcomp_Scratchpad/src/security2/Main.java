public class Main {
	public static void main(String args[]) {
		Scrambler s = new Scrambler();
		Key k = new Key();
		String test = "Hello Brighton!";
		String encrypted = Scrambler.encrypt(test, k.getKey());
		System.out.print("Encrpyted String: " + encrypted);
		System.out.print("Decrypted String: " + Scrambler.decrypt(encrypted, k.getKey()));
	}
}
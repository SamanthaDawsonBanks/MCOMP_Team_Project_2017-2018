package security2;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.security.MessageDigest;
import java.util.Arrays;
import java.util.Base64;

/**
 * @author Stephen Pope 15836791
 *
 * Key class
 * 
 * This class takes in a String and encrypts/decrypts the String.
 * AES is used with the Electronic Code Block operation mode (for initial testing,
 * ECB is not considered secure and CBC - Chain Block or CTR - Counter modes should
 * replace this.
 * 
 * There are 3 methods:
 * 
 * SetKey defines and specifies the key that will be used for encryption and decryption
 * as this is symmetric encryption.
 * 
 * Encrypt takes a String and encrypts it using the prepared key.
 * 
 * Decrypt takes a String and decrypts the text using the prepared key.
 */
 
public class Key2 {
 
    private static SecretKeySpec sk;
    private static byte[] k;
    
    public static void main(String[] args) 
    {
        String k = "CI390"; 
        String ogString = "hey";
        String eString = Key2.encrypt(ogString, k) ;
        String deString = Key2.decrypt(eString, k) ;
         
        System.out.println(ogString);
        System.out.println(eString);
        System.out.println(deString);
    }
    
    /**
     * 
     * @param myKey
     */
    public static void setKey(String myKey) 
    {
        MessageDigest sha = null;
        try {
            k = myKey.getBytes("UTF-8");
            sha = MessageDigest.getInstance("SHA-256");
            k = sha.digest(k);
            k = Arrays.copyOf(k, 16); 
            sk = new SecretKeySpec(k, "AES");
        } 
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    /**
     * 
     * @param strToEncrypt
     * @param secret
     * @return The encrypted String
     */
    public static String encrypt(String strToEncrypt, String secret) 
    {
        try
        {
            setKey(secret);
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, sk);
            return Base64.getEncoder().encodeToString(cipher.doFinal(strToEncrypt.getBytes("UTF-8")));
        } 
        catch (Exception e) 
        {
            e.printStackTrace();
        }
        return null;
    }
    
    /**
     * 
     * @param strToDecrypt
     * @param secret
     * @return The decrypted String.
     */
    public static String decrypt(String strToDecrypt, String secret) 
    {
        try
        {
            setKey(secret);
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5PADDING");
            cipher.init(Cipher.DECRYPT_MODE, sk);
            return new String(cipher.doFinal(Base64.getDecoder().decode(strToDecrypt)));
        } 
        catch (Exception e) 
        {
            e.printStackTrace();
        }
        return null;
    }    

}



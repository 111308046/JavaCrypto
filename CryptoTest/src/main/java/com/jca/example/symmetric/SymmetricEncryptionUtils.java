package com.jca.example.symmetric;

import java.security.SecureRandom;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;

public class SymmetricEncryptionUtils {
	private static final String AES = "AES";
	private static final String AES_CIPHER_ALGO = "AES/CBC/PKCS5Padding";
	
	public static SecretKey createAESKey() throws Exception{
		SecureRandom secureRandom = new SecureRandom();
		KeyGenerator keyGenerator = KeyGenerator.getInstance(AES);
		keyGenerator.init(256, secureRandom);
		return keyGenerator.generateKey();
	}
	
	public static byte[] createInitializationVector(){
		byte[] iv = new byte[16];
		SecureRandom secureRandom = new SecureRandom();
		secureRandom.nextBytes(iv);
		return iv;
	}
	
	public static byte[] performAESEncryption(String plainText, SecretKey secretKey, byte[] iv) throws Exception{
		Cipher cipher = Cipher.getInstance(AES_CIPHER_ALGO);
		IvParameterSpec ivParaSpec = new IvParameterSpec(iv);
		cipher.init(Cipher.ENCRYPT_MODE, secretKey, ivParaSpec);
		return cipher.doFinal(plainText.getBytes());
	}
	
	public static String performAESDecryption(byte[] cipherText, SecretKey secretKey, byte[] iv) throws Exception{
		Cipher cipher = Cipher.getInstance(AES_CIPHER_ALGO);
		IvParameterSpec ivParaSpec = new IvParameterSpec(iv);
		cipher.init(Cipher.DECRYPT_MODE, secretKey, ivParaSpec);
		byte[] text = cipher.doFinal(cipherText);
		return new String(text);
	}
}

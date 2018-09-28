package com.jca.example.symmetric;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.math.BigInteger;
import java.util.Base64;

import javax.crypto.SecretKey;

import org.junit.Test;

public class SymmetricEncryptionUtilsTests {

	@Test
	public void testCreateAESKey() throws Exception{
		SecretKey secretKey = SymmetricEncryptionUtils.createAESKey();
		assertNotNull(secretKey);
		byte[] encoded = secretKey.getEncoded();
		String keyString = Base64.getEncoder().encodeToString(encoded);
		System.out.println(keyString);
	}
	
	@Test
	public void testPerforAESEncryption() throws Exception{
		SecretKey secretKey = SymmetricEncryptionUtils.createAESKey();
		byte[] iv = SymmetricEncryptionUtils.createInitializationVector();
		String plainText = "Where are u going in life?";
		byte[] cipherText = SymmetricEncryptionUtils.performAESEncryption(plainText, secretKey, iv);
		assertNotNull(cipherText);
		System.out.println(Base64.getEncoder().encodeToString(cipherText));
		String decryptedText = SymmetricEncryptionUtils.performAESDecryption(cipherText, secretKey, iv);
		assertEquals(decryptedText, plainText);
		System.out.println(decryptedText);
	}
}

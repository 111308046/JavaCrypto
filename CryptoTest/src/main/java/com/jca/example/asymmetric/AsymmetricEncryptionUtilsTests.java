package com.jca.example.asymmetric;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Base64;

import org.junit.Test;

public class AsymmetricEncryptionUtilsTests {
	
	
	@Test
	public void testGenerateRSAKeyPair() throws Exception {
		KeyPair keyPair = AsymmetricEncryptionUtils.generateRSAKeyPair();
		assertNotNull(keyPair);
		System.out.println("Private Key: " + Base64.getEncoder().encodeToString(keyPair.getPrivate().getEncoded()));
		System.out.println("Public  Key: " + Base64.getEncoder().encodeToString(keyPair.getPublic().getEncoded()));
	}
	
	@Test
	public void testPerformEncryDecry() throws Exception {
		KeyPair keyPair = AsymmetricEncryptionUtils.generateRSAKeyPair();
		PrivateKey privateKey = keyPair.getPrivate();
		PublicKey publicKey = keyPair.getPublic();
		String plainText = "I am going to win";
		byte[] cipherText = AsymmetricEncryptionUtils.performRSAEncryption(plainText, privateKey);
		System.out.println(Base64.getEncoder().encodeToString(cipherText));
		assertNotNull(cipherText);
		String decryptedText = AsymmetricEncryptionUtils.performRSADecryption(cipherText, publicKey);
		assertEquals(plainText, decryptedText);
		System.out.println(decryptedText);
	}
}

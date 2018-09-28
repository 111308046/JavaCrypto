package com.jca.example.keystore;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.security.KeyStore;
import java.security.KeyStore.SecretKeyEntry;
import java.util.Base64;

import javax.crypto.SecretKey;

import org.junit.Test;

import com.jca.example.symmetric.SymmetricEncryptionUtils;

public class KeyStoreUtilsTests {

	@Test
	public void testKeyStore() throws Exception{
		SecretKey secretKey = SymmetricEncryptionUtils.createAESKey();
		System.out.println(Base64.getEncoder().encodeToString(secretKey.getEncoded()));
		String secretKeyPassword = "SecretKeyPassword";
		String keyStorePassword = "KeyStorePassword";
		String keyAlias = "MyTestKey";
		KeyStore keyStore = KeyStoreUtils.createPrivateKeyJavaKeystore(keyStorePassword, keyAlias, secretKey, secretKeyPassword);
		assertNotNull(keyStore);
		
		//SecretKey key = (SecretKey)keyStore.getKey(keyAlias, secretKeyPassword.toCharArray());
		//assertEquals(key, secretKey);
		
		keyStore.load(null, keyStorePassword.toCharArray());
		KeyStore.ProtectionParameter entryPassword = new KeyStore.PasswordProtection(secretKeyPassword.toCharArray());
		KeyStore.SecretKeyEntry resultEntry = (SecretKeyEntry) keyStore.getEntry(keyAlias, entryPassword);
		SecretKey key = resultEntry.getSecretKey();
		assertEquals(key,  secretKey);
		System.out.println(Base64.getEncoder().encodeToString(key.getEncoded()));
	}
}

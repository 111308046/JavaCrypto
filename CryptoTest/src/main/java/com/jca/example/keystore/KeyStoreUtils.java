package com.jca.example.keystore;

import java.security.KeyStore;

import javax.crypto.SecretKey;

public class KeyStoreUtils {
	
	private static final String SECRET_KEY_KEYSTORE_TYPE = "JCEKS";
	
	public static KeyStore createPrivateKeyJavaKeystore(String keyStorePassword, String keyAlias, SecretKey secretKey, String secretKeyPassword) throws Exception {
		KeyStore keyStore = KeyStore.getInstance(SECRET_KEY_KEYSTORE_TYPE);
		keyStore.load(null, keyStorePassword.toCharArray());
		KeyStore.ProtectionParameter entryPassword = new KeyStore.PasswordProtection(secretKeyPassword.toCharArray());
		KeyStore.SecretKeyEntry privateKeyEntry = new KeyStore.SecretKeyEntry(secretKey);
		keyStore.setEntry(keyAlias, privateKeyEntry, entryPassword);
		return keyStore;
	}
}

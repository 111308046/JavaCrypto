package com.jca.example.signature;



import static org.junit.Assert.assertTrue;

import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.KeyPair;
import java.util.Base64;

import org.junit.Test;

import com.jca.example.asymmetric.AsymmetricEncryptionUtils;

public class DigitalSignatureUtilsTests {

	
	@Test
	public void testSignature() throws Exception {
		URL uri = this.getClass().getClassLoader().getResource("demo.txt");
		Path path = Paths.get(uri.toURI());
		byte[] input = Files.readAllBytes(path);
		
		KeyPair keyPair = AsymmetricEncryptionUtils.generateRSAKeyPair();
		byte[] signature = DigitalSignatureUtils.createDigitalSignature(input, keyPair.getPrivate());
		System.out.println(Base64.getEncoder().encodeToString(signature));
		boolean res = DigitalSignatureUtils.verifyDigitalSignature(input, signature, keyPair.getPublic());
		assertTrue(res);
	}
}

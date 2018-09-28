package com.jca.example.hash;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Base64;

import org.junit.Test;

public class HashUtilsTest {
	
	
	@Test
	public void testGenerateSalt() throws Exception{
		byte[] salt = HashUtils.generateRandomSalt();
		assertNotNull(salt);
		System.out.println(Base64.getEncoder().encodeToString(salt));
	}
	
	@Test 
	public void testCreateSHA2Hash() throws Exception {
		byte[] salt = HashUtils.generateRandomSalt();
		String input = "When going gets tough, tough gets going..!";
		byte[] hash = HashUtils.createSHA2Hash(input, salt);
		assertNotNull(hash);
		byte[] hash2 = HashUtils.createSHA2Hash(input, salt);
		System.out.println(Base64.getEncoder().encodeToString(hash));
		System.out.println(Base64.getEncoder().encodeToString(hash2));
		assertEquals(Base64.getEncoder().encodeToString(hash), Base64.getEncoder().encodeToString(hash2));
	}
	
	@Test
	public void testBcrypt() {
		String password = "Welcome@123lsdjfflsdjfljsdf";
		String hash = HashUtils.hashPassword(password);
		assertNotNull(hash);
		System.out.println(hash);
		assertTrue(HashUtils.verifyPassword(password, hash));
		assertFalse(HashUtils.verifyPassword("WrongPassword",  hash));
	}
	
	
}

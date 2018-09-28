package com.jca.example.hash;

import java.io.ByteArrayOutputStream;
import java.security.MessageDigest;
import java.security.SecureRandom;

import org.mindrot.jbcrypt.BCrypt;

public class HashUtils {
	
	public static final String SHA_ALGO = "SHA-256";
	
	public static byte[] generateRandomSalt() throws Exception{
		byte[] salt = new byte[16];
		SecureRandom secureRandom = new SecureRandom();
		secureRandom.nextBytes(salt);
		return salt;
	}
	
	public static byte[] createSHA2Hash(String input, byte[] salt) throws Exception {
		ByteArrayOutputStream bo = new ByteArrayOutputStream();
		bo.write(salt);
		bo.write(input.getBytes());
		byte[] valueToHash = bo.toByteArray();
		MessageDigest messageDigest = MessageDigest.getInstance(SHA_ALGO);
		return messageDigest.digest(valueToHash);
	}
	
	public static String hashPassword(String password){
		return BCrypt.hashpw(password, BCrypt.gensalt());
	}
	
	public static boolean verifyPassword(String password, String hashedPassword){
		return BCrypt.checkpw(password, hashedPassword);
	}
}

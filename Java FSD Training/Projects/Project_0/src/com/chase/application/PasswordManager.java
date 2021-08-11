package com.chase.application;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Base64;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

/*
 * This object handles the hashing of passwords.
 * Passwords are never stored in plain text at all in this program unless they proceed to be immediately passed to this object.
 */

public class PasswordManager {
	private static PasswordManager manager = null;
	byte[] salt;
	SecretKeyFactory f;
	KeySpec spec;
	Base64.Encoder encoder = Base64.getEncoder();
	Base64.Decoder decoder = Base64.getDecoder();
	
	private PasswordManager() {
		try {
			f = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
	}
	
	public static PasswordManager getPasswordManager() {
		if(manager == null) {
			manager = new PasswordManager();
		}
		return manager;
	}
	
	public String newSalt() {
		salt = new byte[16];
		SecureRandom random = new SecureRandom();
		random.nextBytes(salt);
		String saltString = encoder.encodeToString(salt);
		return saltString;
	}
	
	public byte[] decodeSalt(String salt) {
		return decoder.decode(salt);
	}
	
	public String getHash(String password, String salt) {
		byte[] hash;
		byte[] saltByte = decoder.decode(salt);
		spec = new PBEKeySpec(password.toCharArray(), saltByte, 65536, 128);
		
		try {
			hash = f.generateSecret(spec).getEncoded();
		} catch (InvalidKeySpecException e) {
			e.printStackTrace();
			return "ERROR";
		}
		return encoder.encodeToString(hash);
	}
	
}

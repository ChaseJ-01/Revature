package com.chase.JUnitTests;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import com.chase.application.PasswordManager;

/*
 * This is my required JUnit test. 
 * It tests the password hashing method to ensure that the same password hashed multiple times returns the same hash.
 */

class PasswordTest {

	PasswordManager manager;
	String password;
	String hash;
	String salt;
	int count = 0;

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
		manager = PasswordManager.getPasswordManager();
		password = "pass" + count;
		salt = manager.newSalt();
		hash = manager.getHash(password, salt);
	}

	@RepeatedTest(10)
	void testPassword() {
		int actualResult = hash.compareTo(manager.getHash(password, salt));
		int expectedResult = 0;
		assertEquals(expectedResult, actualResult);
	}

}

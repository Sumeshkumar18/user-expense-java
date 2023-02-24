package com.learning.backend;

import java.security.SecureRandom;
import java.util.Random;

public class Utils {

	/** Characters allowed to create UUID. */
	private static final String UUID_STR = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

	/** Length of each part of the UUID. */
	private static final int UUID_LEN = 6;
	
	public static String generateUUID(Integer uuidLength) {

		if (uuidLength == null || uuidLength == 0) {
			uuidLength = UUID_LEN;
		}
		Random random = new SecureRandom();
		StringBuilder uuid = new StringBuilder();

		for (int i = 0; i < uuidLength; i++) {
			uuid.append(UUID_STR.charAt(random.nextInt(UUID_STR.length())));
		}

		return uuid.toString();
	}

}

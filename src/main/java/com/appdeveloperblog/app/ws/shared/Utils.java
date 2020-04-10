package com.appdeveloperblog.app.ws.shared;

import java.security.SecureRandom;
import java.util.Random;

import org.springframework.stereotype.Component;

@Component
public class Utils {

	private final Random RANDOM = new SecureRandom();
	private final String ALPHABET="0123456789abcdefghijklmnopqrstuvwxyz";
	
	public String generateUserId(int length) {
		return generateRandomString(length);
	}
	
	public String generateRandomString(int length) {
//		create an empty string obj can append array with para length
		StringBuilder returnValue=new StringBuilder(length);
		
		for (int i=0;i<length;i++) {
			returnValue.append(ALPHABET.charAt(RANDOM.nextInt(ALPHABET.length())));
		}
		
//		new String convert returnValue string obj to String
		return new String(returnValue);
		
	}
	
	
}

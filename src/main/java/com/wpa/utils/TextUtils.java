/**
 * 
 */
package com.wpa.utils;

import java.util.Base64;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Kanitta Moonlapong
 *
 */
public class TextUtils {

	public static String decodeBase64(String encodedText) {

		// Get decode text

		byte[] decodedBytes = Base64.getDecoder().decode(encodedText);
		String decodedText = new String(decodedBytes);

		return decodedText;

	}

	public static String encodeBase64(String text) {

		// Get decode text

		byte[] encodedBytes = Base64.getEncoder().encode(text.getBytes());
		String encodedText = new String(encodedBytes);

		return encodedText;

	}

	/*
	 * Check if input contains only English alphabet with capital letter
	 * @param String text
	 * @result False = only contains English capital, else return true
	 */
	public static boolean containsSpecialCharacter(String inputStr) {
		// return !inputStr.matches("/^([A-Z]+\\s)*[A-Z]+$/");

		Pattern pattern = Pattern.compile(new String("^[A-Z\\s]*$"));
		Matcher matcher = pattern.matcher(inputStr);
		if (matcher.matches()) {
			return false;
		}
		return true;
	}
	
	public static String processText(String inputStr) {
		//Convert all chars to lower cases
		//White space will be turned to the number of spaces as counted. 
		String convertedText = convertSpaceToNumber(inputStr.toLowerCase());
		//Result to return is the string swap from back to front and leave space between words equal to the number for each space.
		String revertedText = revertAndConvertSpaceToNumber(convertedText);
		
		return revertedText;
	}

	public static  String convertSpaceToNumber(String inputStr) {
		// TODO: check if string contains space
		StringBuffer outputStr = new StringBuffer();
		int spCounter = 1;
		int inputLength = inputStr.length();
		for (int index = 0; index < inputLength; index++) {
			if (Character.isWhitespace(inputStr.charAt(index))) {
				if (((index + 1) < inputLength) && Character.isWhitespace(inputStr.charAt(index + 1))) {
					spCounter += 1;
				} else {
					outputStr.append(String.valueOf(spCounter));
					spCounter = 1;
				}
				// return inputStr;
			} else {
				outputStr.append(inputStr.charAt(index));
			}
		}
		return outputStr.toString();
	}

	public static  String revertAndConvertSpaceToNumber(String inputStr) {
		StringBuilder outputStr = new StringBuilder();

		for (int index = inputStr.length() - 1; index >= 0; index--) {
			if (Character.isDigit(inputStr.charAt(index))) {

				for (int i = 0; i < Character.getNumericValue(inputStr.charAt(index)); i++) {
					outputStr.append(" ");
				}
			} else {
				outputStr.append(inputStr.charAt(index));
			}
		}
		return outputStr.toString();
	}

	public static String revertText(String inputStr) {
		StringBuilder outputStr = new StringBuilder();

		for (int i = inputStr.length() - 1; i >= 0; i--) {
			outputStr.append(inputStr.charAt(i));
		}
		return outputStr.toString();
	}

}

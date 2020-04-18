/**
 * 
 */
package com.wpa.controller;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.wpa.model.WordProcessingModel;
import com.wpa.utils.TextUtils;

/**
 * @author Kanitta Moonlapong
 *
 */
@RestController
public class WordProcessingController {
	
	private static String ERR_PARAM_REQUIRES = "Parameter 'funStr' is requires.";
	private static String ERR_SPECIAL_CHAR_FOUND = "Only english alphabet with capital letter allows.";
	/**
	 * 
	 */
	public WordProcessingController() {
		// TODO Auto-generated constructor stub
	}

	// The API will receive 1 string parameter, namely “funnyStr”
	@RequestMapping(value = "/wpapi", method = RequestMethod.GET)
	public WordProcessingModel getFunStr(@RequestParam String funStr, HttpServletResponse response) {
		WordProcessingModel word = new WordProcessingModel();
		if (!(null != funStr && funStr.length() > 0)) {
			throw new ResponseStatusException(
			           HttpStatus.BAD_REQUEST, ERR_PARAM_REQUIRES);

		}

		// The parameter will be encoded with Base64
		String originalStr = funStr;
		// Decode BASE64
		String decodedText;

		// API received based64 encoded parameter named funnyStr
		// Decode the based64 to plain string
		decodedText = TextUtils.decodeBase64(originalStr);
		//word.setDecodedText(decodedText);
		// After decoding, API will only accept English Capital Letter. If not, reject
		// and throw error message, 
		if (TextUtils.containsSpecialCharacter(decodedText)) {
			throw new ResponseStatusException(
			           HttpStatus.BAD_REQUEST, ERR_SPECIAL_CHAR_FOUND);
		}
		
		//Convert all chars to lower cases
		//White space will be turned to the number of spaces as counted. 
		String convertedText = TextUtils.convertSpaceToNumber(decodedText.toLowerCase());
		//Result to return is the string swap from back to front and leave space between words equal to the number for each space.
		String revertedText = TextUtils.revertAndConvertSpaceToNumber(convertedText);
		//Encode the result with based64 and return back to the caller.
		String encodedText = TextUtils.encodeBase64(revertedText);
		word.setResult(encodedText);

		return word;

	}
}


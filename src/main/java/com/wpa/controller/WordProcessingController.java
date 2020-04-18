/**
 * 
 */
package com.wpa.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.wpa.model.WordProcessingModel;
import com.wpa.utils.TextUtils;

/**
 * @author Kanitta Moonlapong
 *
 */
@RestController
public class WordProcessingController {

	/**
	 * 
	 */
	public WordProcessingController() {
		// TODO Auto-generated constructor stub
	}

	// The API will receive 1 string parameter, namely “funnyStr”
	@RequestMapping(value = "/wpapi/funStr/{funStr}", method = RequestMethod.GET)
	public WordProcessingModel getFunStr(@PathVariable("funStr") String funStr) {
		WordProcessingModel word = new WordProcessingModel(funStr);
		if (null == funStr) {
			word.setErrorMsg("parameter 'funStr' is requires.");
			return word;
		}

		// The parameter will be encoded with Base64
		String originalStr = word.getEncodedText();
		// Decode BASE64
		String decodedText;

		// API received based64 encoded parameter named funnyStr
		// Decode the based64 to plain string
		decodedText = TextUtils.decodeBase64(originalStr);
		//word.setDecodedText(decodedText);
		// After decoding, API will only accept English Capital Letter. If not, reject
		// and throw error message, 
		if (TextUtils.containsSpecialCharacter(decodedText)) {
			word.setErrorMsg("Only English Capital Letter is allows.");
			return word;
		}
		
		//Convert all chars to lower cases
		//White space will be turned to the number of spaces as counted. 
		String convertedText = TextUtils.convertSpaceToNumber(decodedText.toLowerCase());
		//Result to return is the string swap from back to front and leave space between words equal to the number for each space.
		String revertedText = TextUtils.revertAndConvertSpaceToNumber(convertedText);
		//Encode the result with based64 and return back to the caller.
		String encodedText = TextUtils.encodeBase64(revertedText);
		word.setDecodedText(encodedText);
		// To lowercase

		// Encode with space counting

		// Revert

		// Encode to BASE64
		return word;

	}
}

/*
 * 2. The API will receive 1 string parameter, namely “funnyStr”
 * 
 * 3. The parameter will be encoded with Base64
 * 
 * 4. The API will work as below :-
 * 
 * 4a). API received based64 encoded parameter named “funnyStr”
 * 
 * 4b). Decode the based64 to plain string
 * 
 * 4c). After decoding, API will only accept English Capital Letter. If not,
 * reject and throw error message, or otherwise, convert all chars to lower
 * cases
 * 
 * 4d). White space will be turned to the number of spaces as counted. (Do not
 * skip this step. We’ll check the code.)
 */
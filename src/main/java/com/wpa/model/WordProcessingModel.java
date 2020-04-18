/**
 * 
 */
package com.wpa.model;

/**
 * @author Kanitta Moonlapong
 *
 */
public class WordProcessingModel {

	private String encodedText;
	private String result;
	private String errorMsg;

	public WordProcessingModel(String encodedText) {
		super();
		this.encodedText = encodedText;
	}

	public String getEncodedText() {
		return encodedText;
	}

	public void setEncodedText(String encodedText) {
		this.encodedText = encodedText;
	}

	public String getDecodedText() {
		return result;
	}

	public void setDecodedText(String decodedText) {
		this.result = decodedText;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}
	
	

}

package com.wpa;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.wpa.utils.TextUtils;

@ExtendWith(SpringExtension.class)
class WordProcessingApi2ApplicationTests {
	
	@Test
	public void textConvertSpaceToNumber() 
	{
	    assertEquals("TEXT1INPUT", TextUtils.convertSpaceToNumber("TEXT INPUT"));
	    assertEquals("TEXT2INPUT", TextUtils.convertSpaceToNumber("TEXT  INPUT"));
	    assertEquals("TEXT1INPUT2HELLO", TextUtils.convertSpaceToNumber("TEXT INPUT  HELLO"));
	    assertEquals("1TEXT2INPUT", TextUtils.convertSpaceToNumber(" TEXT  INPUT"));
	    assertEquals("1TEXT2INPUT3", TextUtils.convertSpaceToNumber(" TEXT  INPUT   "));
	    assertEquals("1TEXT2INPUT3HI10O5", TextUtils.convertSpaceToNumber(" TEXT  INPUT   HI          O     "));
	    assertEquals("a1hen2has2many3chicks", TextUtils.convertSpaceToNumber("A HEN  HAS  MANY   CHICKS".toLowerCase()));
	}
	
	
	@Test
	public void testRevertAndConvertSpacText() 
	{
	    assertEquals("skcihc   ynam  sah  neh a".toLowerCase(), TextUtils.revertAndConvertSpaceToNumber("a1hen2has2many3chicks"));
	}
	
	@Test
	/*
	 * Check if input contains only English capital
	 */
	public void testContainsSpecialCharacter() 
	{
		assertEquals(false, TextUtils.containsSpecialCharacter("TEXT INPUT"));
	    assertEquals(true, TextUtils.containsSpecialCharacter("a1hen2has2many3chicks"));
	    assertEquals(true, TextUtils.containsSpecialCharacter("Afsdsfdsfds"));
	    assertEquals(true, TextUtils.containsSpecialCharacter("TEXT14520"));
	}
	
	@Test
	/*
	 * @input English capital case only
	 * @output Reverted lower case text
	 */
	public void testProcessText() 
	{
		assertEquals("dlrow eht", TextUtils.processText("THE WORLD"));
	    assertEquals("skcihc   ynam  sah  neh a", TextUtils.processText("A HEN  HAS  MANY   CHICKS"));
	}

}

package com.wpa;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.wpa.utils.TextUtils;

@SpringBootTest
class WordProcessingApi2ApplicationTests {

	@Test
	void contextLoads() {
	}
	

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
	public void testContainsSpecialCharacter() 
	{
		assertEquals(false, TextUtils.containsSpecialCharacter("TEXT INPUT"));
	    assertEquals(true, TextUtils.containsSpecialCharacter("a1hen2has2many3chicks"));
	    assertEquals(true, TextUtils.containsSpecialCharacter("Afsdsfdsfds"));
	    assertEquals(true, TextUtils.containsSpecialCharacter("TEXT14520"));
	}

}

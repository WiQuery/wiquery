package org.odlabs.wiquery.ui.datepicker;

import junit.framework.TestCase;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

public class DatePickerYearRangeTestCase extends TestCase{
	protected static final Logger log = LoggerFactory.getLogger(
			DatePickerYearRangeTestCase.class);

	@Test
	public void testGetJavaScriptOption() {
		DatePickerYearRange yearRAnge = new DatePickerYearRange(new Short("-10"), new Short("10"));
		
		// Short params
		String expectedJavascript = "'-10:+10'";
		String generatedJavascript = yearRAnge.getJavascriptOption().toString();
		
		log.info(expectedJavascript);
		log.info(generatedJavascript);		
		Assert.assertEquals(generatedJavascript, expectedJavascript);
		
		// IllegalParameters param
		yearRAnge.setYearFrom(new Short("16"));
		try {
			generatedJavascript = yearRAnge.getJavascriptOption().toString();
			assertTrue(false);
		} catch (Exception e) {
			// We have an expected error
			assertEquals("Invalid year range", e.getMessage());
		}
	}
}

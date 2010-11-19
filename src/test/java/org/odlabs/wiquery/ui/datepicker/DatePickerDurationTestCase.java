package org.odlabs.wiquery.ui.datepicker;

import junit.framework.TestCase;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.junit.Assert;
import org.junit.Test;

public class DatePickerDurationTestCase extends TestCase{
	protected static final Logger log = LoggerFactory.getLogger(
			DatePickerDurationTestCase.class);

	@Test
	public void testGetJavaScriptOption() {
		DatePickerShortYearCutOff shortYearCutOff = new DatePickerShortYearCutOff(new Short("5"));
		
		// Short param
		String expectedJavascript = "5";
		String generatedJavascript = shortYearCutOff.getJavascriptOption().toString();
		
		log.info(expectedJavascript);
		log.info(generatedJavascript);		
		Assert.assertEquals(generatedJavascript, expectedJavascript);
		
		// Literal param
		shortYearCutOff.setLiteralParam("+25");
		expectedJavascript = "'+25'";
		generatedJavascript = shortYearCutOff.getJavascriptOption().toString();
		
		log.info(expectedJavascript);
		log.info(generatedJavascript);		
		Assert.assertEquals(generatedJavascript, expectedJavascript);
		
		// Null param
		shortYearCutOff.setShortParam(null);
		try {
			generatedJavascript = shortYearCutOff.getJavascriptOption().toString();
			assertTrue(false);
		} catch (Exception e) {
			// We have an expected error
			assertEquals("The DatePickerShortYearCutOff must have one not null parameter", e.getMessage());
		}
	}
}

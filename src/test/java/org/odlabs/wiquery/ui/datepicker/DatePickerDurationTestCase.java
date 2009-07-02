package org.odlabs.wiquery.ui.datepicker;

import junit.framework.TestCase;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

public class DatePickerDurationTestCase extends TestCase{
	protected static final Logger log = LoggerFactory.getLogger(
			DatePickerDurationTestCase.class);

	@Test
	public void testGetJavaScriptOption() {
		DatePickerDuration duration = new DatePickerDuration(new Short("5"));
		
		// Short param
		String expectedJavascript = "5";
		String generatedJavascript = duration.getJavascriptOption().toString();
		
		log.info(expectedJavascript);
		log.info(generatedJavascript);		
		Assert.assertEquals(generatedJavascript, expectedJavascript);
		
		// DurationEnum param
		duration.setDurationEnumParam(DatePickerDuration.DurationEnum.FAST);
		expectedJavascript = DatePickerDuration.DurationEnum.FAST.toString();
		generatedJavascript = duration.getJavascriptOption().toString();
		
		log.info(expectedJavascript);
		log.info(generatedJavascript);		
		Assert.assertEquals(generatedJavascript, expectedJavascript);
		
		// Null param
		duration.setDurationEnumParam(null);
		try {
			generatedJavascript = duration.getJavascriptOption().toString();
			assertTrue(false);
		} catch (Exception e) {
			// We have an expected error
			assertEquals("The DatePickerDuration must have one not null parameter", e.getMessage());
		}
	}
}

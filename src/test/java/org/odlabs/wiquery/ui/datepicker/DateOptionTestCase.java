package org.odlabs.wiquery.ui.datepicker;

import java.util.GregorianCalendar;

import junit.framework.TestCase;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.junit.Assert;
import org.junit.Test;

public class DateOptionTestCase extends TestCase{
	protected static final Logger log = LoggerFactory.getLogger(
			DateOptionTestCase.class);

	@Test
	public void testGetJavaScriptOption() {
		DateOption dateOption = new DateOption(new Short("5"));
		
		// Short param
		String expectedJavascript = "5";
		String generatedJavascript = dateOption.getJavascriptOption().toString();
		
		log.info(expectedJavascript);
		log.info(generatedJavascript);		
		Assert.assertEquals(generatedJavascript, expectedJavascript);
		
		// Literal param
		dateOption.setLiteralParam("-1y -1m");
		expectedJavascript = "'-1y -1m'";
		generatedJavascript = dateOption.getJavascriptOption().toString();
		
		log.info(expectedJavascript);
		log.info(generatedJavascript);		
		Assert.assertEquals(generatedJavascript, expectedJavascript);
		
		// Date param
		GregorianCalendar calendar = new GregorianCalendar(2009, 11, 7);
		dateOption.setDateParam(calendar.getTime());
		expectedJavascript = "new Date(2009,11,7,0,0,0,0)";
		generatedJavascript = dateOption.getJavascriptOption().toString();
		
		log.info(expectedJavascript);
		log.info(generatedJavascript);		
		Assert.assertEquals(generatedJavascript, expectedJavascript);
		
		calendar = new GregorianCalendar(2009, 11, 7, 17, 53, 25);
		dateOption.setDateParam(calendar.getTime());
		expectedJavascript = "new Date(2009,11,7,17,53,25,0)";
		generatedJavascript = dateOption.getJavascriptOption().toString();
		
		log.info(expectedJavascript);
		log.info(generatedJavascript);		
		Assert.assertEquals(generatedJavascript, expectedJavascript);
		
		// Null param
		dateOption.setShortParam(null);
		try {
			generatedJavascript = dateOption.getJavascriptOption().toString();
			assertTrue(false);
		} catch (Exception e) {
			// We have an expected error
			assertEquals("The DateOption must have one not null parameter", e.getMessage());
		}
	}
}

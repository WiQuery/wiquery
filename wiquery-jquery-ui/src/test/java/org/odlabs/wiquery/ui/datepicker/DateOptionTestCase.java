package org.odlabs.wiquery.ui.datepicker;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.GregorianCalendar;

import org.junit.Test;
import org.odlabs.wiquery.tester.WiQueryTestCase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DateOptionTestCase extends WiQueryTestCase {
	protected static final Logger log = LoggerFactory
			.getLogger(DateOptionTestCase.class);

	@Test
	public void testGetJavaScriptOption() {
		DateOption dateOption = new DateOption(new Short("5"));

		// Short param
		String expectedJavascript = "5";
		String generatedJavascript = dateOption.getJavascriptOption()
				.toString();

		log.info(expectedJavascript);
		log.info(generatedJavascript);
		assertEquals(generatedJavascript, expectedJavascript);

		// Literal param
		dateOption.setLiteralParam("-1y -1m");
		expectedJavascript = "'-1y -1m'";
		generatedJavascript = dateOption.getJavascriptOption().toString();

		log.info(expectedJavascript);
		log.info(generatedJavascript);
		assertEquals(generatedJavascript, expectedJavascript);

		// Date param
		GregorianCalendar calendar = new GregorianCalendar(2009, 11, 7);
		dateOption.setDateParam(calendar.getTime());
		expectedJavascript = "new Date(2009,11,7,0,0,0,0)";
		generatedJavascript = dateOption.getJavascriptOption().toString();

		log.info(expectedJavascript);
		log.info(generatedJavascript);
		assertEquals(generatedJavascript, expectedJavascript);

		calendar = new GregorianCalendar(2009, 11, 7, 17, 53, 25);
		dateOption.setDateParam(calendar.getTime());
		expectedJavascript = "new Date(2009,11,7,17,53,25,0)";
		generatedJavascript = dateOption.getJavascriptOption().toString();

		log.info(expectedJavascript);
		log.info(generatedJavascript);
		assertEquals(generatedJavascript, expectedJavascript);

		// Null param
		dateOption.setShortParam(null);
		try {
			generatedJavascript = dateOption.getJavascriptOption().toString();
			assertTrue(false);
		} catch (Exception e) {
			// We have an expected error
			assertEquals("The DateOption must have one not null parameter",
					e.getMessage());
		}
	}

	@Override
	protected Logger getLog() {
		return log;
	}
}

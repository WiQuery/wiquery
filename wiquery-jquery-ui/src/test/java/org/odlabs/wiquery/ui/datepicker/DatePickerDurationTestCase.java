package org.odlabs.wiquery.ui.datepicker;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.odlabs.wiquery.tester.WiQueryTestCase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DatePickerDurationTestCase extends WiQueryTestCase {
	protected static final Logger log = LoggerFactory
			.getLogger(DatePickerDurationTestCase.class);

	@Test
	public void testGetJavaScriptOption() {
		DatePickerShortYearCutOff shortYearCutOff = new DatePickerShortYearCutOff(
				new Short("5"));

		// Short param
		String expectedJavascript = "5";
		String generatedJavascript = shortYearCutOff.getJavascriptOption()
				.toString();

		log.info(expectedJavascript);
		log.info(generatedJavascript);
		assertEquals(generatedJavascript, expectedJavascript);

		// Literal param
		shortYearCutOff.setLiteralParam("+25");
		expectedJavascript = "'+25'";
		generatedJavascript = shortYearCutOff.getJavascriptOption().toString();

		log.info(expectedJavascript);
		log.info(generatedJavascript);
		assertEquals(generatedJavascript, expectedJavascript);

		// Null param
		shortYearCutOff.setShortParam(null);
		try {
			generatedJavascript = shortYearCutOff.getJavascriptOption()
					.toString();
			assertTrue(false);
		} catch (Exception e) {
			// We have an expected error
			assertEquals(
					"The DatePickerShortYearCutOff must have one not null parameter",
					e.getMessage());
		}
	}

	@Override
	protected Logger getLog() {
		return log;
	}
}

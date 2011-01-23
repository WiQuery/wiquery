package org.odlabs.wiquery.ui.datepicker;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.odlabs.wiquery.tester.WiQueryTestCase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DatePickerYearRangeTestCase extends WiQueryTestCase {
	protected static final Logger log = LoggerFactory
			.getLogger(DatePickerYearRangeTestCase.class);

	@Test
	public void testGetJavaScriptOption() {
		DatePickerYearRange yearRAnge = new DatePickerYearRange(
				new Short("-10"), new Short("10"));

		// Short params
		String expectedJavascript = "'-10:+10'";
		String generatedJavascript = yearRAnge.getJavascriptOption().toString();

		log.info(expectedJavascript);
		log.info(generatedJavascript);
		assertEquals(generatedJavascript, expectedJavascript);
	}
}

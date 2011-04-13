package org.odlabs.wiquery.ui.datepicker;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.odlabs.wiquery.tester.WiQueryTestCase;
import org.odlabs.wiquery.ui.datepicker.DatePickerYearRange.DatePickerYearRangeControl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DatePickerYearRangeTestCase extends WiQueryTestCase {
	protected static final Logger log = LoggerFactory
			.getLogger(DatePickerYearRangeTestCase.class);

	@Test
	public void testGetJavaScriptOption() {
		DatePickerYearRange yearRange = new DatePickerYearRange(
				new Short("2000"), new Short("2010"));

		// Absolute
		String expectedJavascript = "'2000:2010'";
		String generatedJavascript = yearRange.getJavascriptOption().toString();

		log.info(expectedJavascript);
		log.info(generatedJavascript);
		assertEquals(generatedJavascript, expectedJavascript);
		
		// Relative today
		yearRange.setRelativeRange(new Short("-10"), new Short("10"), true);
		expectedJavascript = "'-10:+10'";
		generatedJavascript = yearRange.getJavascriptOption().toString();

		log.info(expectedJavascript);
		log.info(generatedJavascript);
		assertEquals(generatedJavascript, expectedJavascript);
		
		// Relative to the selected date
		yearRange.setRelativeRange(new Short("-10"), new Short("10"), false);
		expectedJavascript = "'c-10:c+10'";
		generatedJavascript = yearRange.getJavascriptOption().toString();

		log.info(expectedJavascript);
		log.info(generatedJavascript);
		assertEquals(generatedJavascript, expectedJavascript);
		
		// Mixed range
		yearRange.setRange(new Short("2000"), new Short("-10"), DatePickerYearRangeControl.ABSOLUTE, DatePickerYearRangeControl.RELATIVE_TODAY);
		expectedJavascript = "'2000:-10'";
		generatedJavascript = yearRange.getJavascriptOption().toString();

		log.info(expectedJavascript);
		log.info(generatedJavascript);
		assertEquals(generatedJavascript, expectedJavascript);
	}
}

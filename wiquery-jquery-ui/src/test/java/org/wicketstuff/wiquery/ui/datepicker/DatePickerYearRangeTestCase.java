package org.wicketstuff.wiquery.ui.datepicker;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.wicketstuff.wiquery.tester.WiQueryTestCase;
import org.wicketstuff.wiquery.ui.datepicker.DatePickerYearRange.DatePickerYearRangeControl;

public class DatePickerYearRangeTestCase extends WiQueryTestCase
{
	protected static final Logger log = LoggerFactory.getLogger(DatePickerYearRangeTestCase.class);

	@Test
	public void testGetJavaScriptOption()
	{
		DatePickerYearRange yearRange = new DatePickerYearRange(Short.parseShort("2000"),
			Short.parseShort("2010"));

		// Absolute
		String expectedJavascript = "'2000:2010'";
		String generatedJavascript = yearRange.getJavascriptOption().toString();

		log.info(expectedJavascript);
		log.info(generatedJavascript);
		assertEquals(generatedJavascript, expectedJavascript);

		// Relative today
		yearRange.setRelativeRange(Short.parseShort("-10"), Short.parseShort("10"), true);
		expectedJavascript = "'-10:+10'";
		generatedJavascript = yearRange.getJavascriptOption().toString();

		log.info(expectedJavascript);
		log.info(generatedJavascript);
		assertEquals(generatedJavascript, expectedJavascript);

		// Relative to the selected date
		yearRange.setRelativeRange(Short.parseShort("-10"), Short.parseShort("10"), false);
		expectedJavascript = "'c-10:c+10'";
		generatedJavascript = yearRange.getJavascriptOption().toString();

		log.info(expectedJavascript);
		log.info(generatedJavascript);
		assertEquals(generatedJavascript, expectedJavascript);

		// Mixed range
		yearRange.setRange(Short.parseShort("2000"), Short.parseShort("-10"), DatePickerYearRangeControl.ABSOLUTE,
			DatePickerYearRangeControl.RELATIVE_TODAY);
		expectedJavascript = "'2000:-10'";
		generatedJavascript = yearRange.getJavascriptOption().toString();

		log.info(expectedJavascript);
		log.info(generatedJavascript);
		assertEquals(generatedJavascript, expectedJavascript);
	}

	@Override
	protected Logger getLog()
	{
		return log;
	}
}

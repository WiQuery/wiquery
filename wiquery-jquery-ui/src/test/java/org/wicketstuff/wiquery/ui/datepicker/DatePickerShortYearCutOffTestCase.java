package org.wicketstuff.wiquery.ui.datepicker;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.wicketstuff.wiquery.core.options.ArrayItemOptions;
import org.wicketstuff.wiquery.core.options.IntegerItemOptions;
import org.wicketstuff.wiquery.tester.WiQueryTestCase;

public class DatePickerShortYearCutOffTestCase extends WiQueryTestCase
{
	protected static final Logger log = LoggerFactory
		.getLogger(DatePickerShortYearCutOffTestCase.class);

	@Test
	public void testGetJavaScriptOption()
	{
		DatePickerNumberOfMonths numberOfMonths = new DatePickerNumberOfMonths(Short.parseShort("5"));

		// Short param
		String expectedJavascript = "5";
		String generatedJavascript = numberOfMonths.getJavascriptOption().toString();

		log.info(expectedJavascript);
		log.info(generatedJavascript);
		assertEquals(generatedJavascript, expectedJavascript);

		// DurationEnum param
		ArrayItemOptions<IntegerItemOptions> array = new ArrayItemOptions<>();
		numberOfMonths.setArrayParam(array);

		try
		{
			generatedJavascript = numberOfMonths.getJavascriptOption().toString();
			assertTrue(false);
		}
		catch (Exception e)
		{
			// We have an expected error
			assertEquals("The 'arrayParam' in the DatePickerNumberOfMonths must have two values",
				e.getMessage());
		}

		array.add(new IntegerItemOptions(2));
		array.add(new IntegerItemOptions(3));
		expectedJavascript = "[2,3]";
		generatedJavascript = numberOfMonths.getJavascriptOption().toString();

		log.info(expectedJavascript);
		log.info(generatedJavascript);
		assertEquals(generatedJavascript, expectedJavascript);

		// Null param
		numberOfMonths.setShortParam(null);
		try
		{
			generatedJavascript = numberOfMonths.getJavascriptOption().toString();
			assertTrue(false);
		}
		catch (Exception e)
		{
			// We have an expected error
			assertEquals("The DatePickerNumberOfMonths must have one not null parameter",
				e.getMessage());
		}
	}

	@Override
	protected Logger getLog()
	{
		return log;
	}
}

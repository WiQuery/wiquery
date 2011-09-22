package org.odlabs.wiquery.core.javascript.helper;

import static org.junit.Assert.*;

import java.util.GregorianCalendar;

import org.junit.Test;
import org.odlabs.wiquery.tester.WiQueryTestCase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DateHelperTestCase extends WiQueryTestCase
{
	protected static final Logger log = LoggerFactory.getLogger(DateHelperTestCase.class);

	@Test
	public void testGetJSDate()
	{
		// Date param
		String expectedJavascript = "new Date()";
		String generatedJavascript = DateHelper.getJSDate().toString();

		log.info(expectedJavascript);
		log.info(generatedJavascript);
		assertEquals(generatedJavascript, expectedJavascript);
	}

	@Test
	public void testGetJSDateWithCalendar()
	{
		// Date param
		GregorianCalendar calendar = new GregorianCalendar(2009, 11, 7);
		String expectedJavascript = "new Date(2009,11,7,0,0,0,0)";
		String generatedJavascript = DateHelper.getJSDate(calendar).toString();

		log.info(expectedJavascript);
		log.info(generatedJavascript);
		assertEquals(generatedJavascript, expectedJavascript);
	}

	@Test
	public void testGetJSDateWithDate()
	{
		// Date param
		GregorianCalendar calendar = new GregorianCalendar(2009, 11, 11);
		String expectedJavascript = "new Date(2009,11,11,0,0,0,0)";
		String generatedJavascript = DateHelper.getJSDate(calendar.getTime()).toString();

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

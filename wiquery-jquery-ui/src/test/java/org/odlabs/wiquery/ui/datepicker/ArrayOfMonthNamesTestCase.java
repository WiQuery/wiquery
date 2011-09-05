package org.odlabs.wiquery.ui.datepicker;

import static org.junit.Assert.*;

import org.junit.Test;
import org.odlabs.wiquery.tester.WiQueryTestCase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ArrayOfMonthNamesTestCase extends WiQueryTestCase
{
	protected static final Logger log = LoggerFactory.getLogger(ArrayOfMonthNamesTestCase.class);

	@Test
	public void testGetJavaScriptOption()
	{
		ArrayOfMonthNames arrays =
			new ArrayOfMonthNames("Janvier", "Fevrier", "Mars", "Avril", "Mai", "Juin", "Juillet",
				"Aout", "Septembre", "Octobre", "Novembre", "Decembre");

		// Names param
		String expectedJavascript =
			"['Janvier','Fevrier','Mars','Avril'"
				+ ",'Mai','Juin','Juillet','Aout','Septembre','Octobre'"
				+ ",'Novembre','Decembre']";
		String generatedJavascript = arrays.getJavascriptOption().toString();

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

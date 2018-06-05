package org.wicketstuff.wiquery.ui.datepicker;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.wicketstuff.wiquery.tester.WiQueryTestCase;

public class ArrayOfDayNamesTestCase extends WiQueryTestCase
{
	protected static final Logger log = LoggerFactory.getLogger(ArrayOfDayNamesTestCase.class);

	@Test
	public void testGetJavaScriptOption()
	{
		ArrayOfDayNames arrays = new ArrayOfDayNames("Dimanche", "Lundi", "Mardi", "Mercredi",
			"Jeudi", "Vendredi", "Samedi");

		// Names param
		String expectedJavascript = "['Dimanche','Lundi','Mardi','Mercredi'" +
			",'Jeudi','Vendredi','Samedi']";
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

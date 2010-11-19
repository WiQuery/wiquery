package org.odlabs.wiquery.ui.datepicker;

import junit.framework.TestCase;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.junit.Assert;
import org.junit.Test;

public class ArrayOfDayNamesTestCase extends TestCase{
	protected static final Logger log = LoggerFactory.getLogger(
			ArrayOfDayNamesTestCase.class);

	@Test
	public void testGetJavaScriptOption() {
		ArrayOfDayNames arrays = new ArrayOfDayNames(
				"Dimanche", "Lundi", "Mardi", "Mercredi", "Jeudi", "Vendredi", "Samedi");
		
		// Names param
		String expectedJavascript = "['Dimanche','Lundi','Mardi','Mercredi'" +
				",'Jeudi','Vendredi','Samedi']";
		String generatedJavascript = arrays.getJavascriptOption().toString();
		
		log.info(expectedJavascript);
		log.info(generatedJavascript);		
		Assert.assertEquals(generatedJavascript, expectedJavascript);
	}
}

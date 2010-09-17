package org.odlabs.wiquery.ui.datepicker;

import junit.framework.TestCase;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ArrayOfMonthNamesTestCase extends TestCase{
	protected static final Logger log = LoggerFactory.getLogger(
			ArrayOfMonthNamesTestCase.class);

	@Test
	public void testGetJavaScriptOption() {
		ArrayOfMonthNames arrays = new ArrayOfMonthNames(
				"Janvier", "Fevrier", "Mars", "Avril", "Mai", "Juin", "Juillet",
				"Aout", "Septembre", "Octobre", "Novembre", "Decembre");
		
		// Names param
		String expectedJavascript = "['Janvier','Fevrier','Mars','Avril'" +
				",'Mai','Juin','Juillet','Aout','Septembre','Octobre'" +
				",'Novembre','Decembre']";
		String generatedJavascript = arrays.getJavascriptOption().toString();
		
		log.info(expectedJavascript);
		log.info(generatedJavascript);		
		Assert.assertEquals(generatedJavascript, expectedJavascript);
	}
}

package org.odlabs.wiquery.ui.accordion;

import junit.framework.TestCase;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

public class AccordionIconTestCase extends TestCase{
	protected static final Logger log = LoggerFactory.getLogger(
			AccordionIconTestCase.class);

	@Test
	public void testGetJavaScriptOption() {
		AccordionIcon accordionIcon = new AccordionIcon(
				"classA", "classB");
		
		// Int param
		String expectedJavascript = "{'header': 'classA', 'headerSelected': 'classB'}";
		String generatedJavascript = accordionIcon.getJavascriptOption().toString();
		
		log.info(expectedJavascript);
		log.info(generatedJavascript);		
		Assert.assertEquals(generatedJavascript, expectedJavascript);
	}
}

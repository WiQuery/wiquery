package org.odlabs.wiquery.ui.accordion;

import junit.framework.TestCase;

import org.odlabs.wiquery.core.options.LiteralOption;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.junit.Assert;
import org.junit.Test;

public class AccordionHeaderTestCase extends TestCase{
	protected static final Logger log = LoggerFactory.getLogger(
			AccordionHeaderTestCase.class);

	@Test
	public void testGetJavaScriptOption() {
		AccordionHeader header  = new AccordionHeader("jQuery('#test')");
		
		// Object param
		String expectedJavascript = "jQuery('#test')";
		String generatedJavascript = header.getJavascriptOption().toString();
		
		log.info(expectedJavascript);
		log.info(generatedJavascript);		
		Assert.assertEquals(generatedJavascript, expectedJavascript);
		
		// Literal param
		header.setLiteralParam(new LiteralOption("> li > :first-child,> :not(li):even"));
		expectedJavascript = "'> li > :first-child,> :not(li):even'";
		generatedJavascript = header.getJavascriptOption().toString();
		
		log.info(expectedJavascript);
		log.info(generatedJavascript);		
		Assert.assertEquals(generatedJavascript, expectedJavascript);
		
		// Null param
		header.setObjectParam(null);
		try {
			generatedJavascript = header.getJavascriptOption().toString();
			assertTrue(false);
		} catch (Exception e) {
			// We have an expected error
			assertEquals("The AccordionHeader must have one not null parameter", e.getMessage());
		}
	}
}

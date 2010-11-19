package org.odlabs.wiquery.ui.accordion;

import junit.framework.TestCase;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.junit.Assert;
import org.junit.Test;

public class AccordionAnimatedTestCase extends TestCase{
	protected static final Logger log = LoggerFactory.getLogger(
			AccordionAnimatedTestCase.class);

	@Test
	public void testGetJavaScriptOption() {
		AccordionAnimated snap  = new AccordionAnimated(true);
		
		// Boolean param
		String expectedJavascript = "true";
		String generatedJavascript = snap.getJavascriptOption().toString();
		
		log.info(expectedJavascript);
		log.info(generatedJavascript);		
		Assert.assertEquals(generatedJavascript, expectedJavascript);
		
		// Effect param
		snap.setEffectParam("slide");
		expectedJavascript = "'slide'";
		generatedJavascript = snap.getJavascriptOption().toString();
		
		log.info(expectedJavascript);
		log.info(generatedJavascript);		
		Assert.assertEquals(generatedJavascript, expectedJavascript);
		
		// Null param
		snap.setEffectParam(null);
		try {
			generatedJavascript = snap.getJavascriptOption().toString();
			assertTrue(false);
		} catch (Exception e) {
			// We have an expected error
			assertEquals("The AccordionAnimated must have one not null parameter", e.getMessage());
		}
	}
}

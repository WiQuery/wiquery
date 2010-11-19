package org.odlabs.wiquery.ui.slider;

import junit.framework.TestCase;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.junit.Assert;
import org.junit.Test;

public class SliderRangeTestCase extends TestCase{
	protected static final Logger log = LoggerFactory.getLogger(
			SliderRangeTestCase.class);

	@Test
	public void testGetJavaScriptOption() {
		SliderRange accordionActive = new SliderRange(true);
		
		// Boolean param
		String expectedJavascript = "true";
		String generatedJavascript = accordionActive.getJavascriptOption().toString();
		
		log.info(expectedJavascript);
		log.info(generatedJavascript);		
		Assert.assertEquals(generatedJavascript, expectedJavascript);
		
		// Literal param
		accordionActive.setRangeEnumParam(SliderRange.RangeEnum.MAX);
		expectedJavascript = SliderRange.RangeEnum.MAX.toString();
		generatedJavascript = accordionActive.getJavascriptOption().toString();
		
		log.info(expectedJavascript);
		log.info(generatedJavascript);		
		Assert.assertEquals(generatedJavascript, expectedJavascript);
		
		// Null param
		accordionActive.setRangeEnumParam(null);
		try {
			generatedJavascript = accordionActive.getJavascriptOption().toString();
			assertTrue(false);
		} catch (Exception e) {
			// We have an expected error
			assertEquals("The SliderRange must have one not null parameter", e.getMessage());
		}
	}
}

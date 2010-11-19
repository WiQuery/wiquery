package org.odlabs.wiquery.ui.slider;

import junit.framework.TestCase;

import org.odlabs.wiquery.ui.slider.SliderAnimate.AnimateEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.junit.Assert;
import org.junit.Test;

public class SliderAnimateTestCase extends TestCase{
	protected static final Logger log = LoggerFactory.getLogger(
			SliderAnimateTestCase.class);

	@Test
	public void testGetJavaScriptOption() {
		SliderAnimate animate = new SliderAnimate(true);
		
		// Boolean param
		String expectedJavascript = "true";
		String generatedJavascript = animate.getJavascriptOption().toString();
		
		log.info(expectedJavascript);
		log.info(generatedJavascript);		
		Assert.assertEquals(generatedJavascript, expectedJavascript);
		
		// Number param
		expectedJavascript = "5";
		animate.setNumberParam(5);
		generatedJavascript = animate.getJavascriptOption().toString();
		
		log.info(expectedJavascript);
		log.info(generatedJavascript);		
		Assert.assertEquals(generatedJavascript, expectedJavascript);
		
		// Literal param
		animate.setAnimateEnumParam(AnimateEnum.SLOW);
		expectedJavascript = AnimateEnum.SLOW.toString();
		generatedJavascript = animate.getJavascriptOption().toString();
		
		log.info(expectedJavascript);
		log.info(generatedJavascript);		
		Assert.assertEquals(generatedJavascript, expectedJavascript);
		
		// Null param
		animate.setAnimateEnumParam(null);
		try {
			generatedJavascript = animate.getJavascriptOption().toString();
			assertTrue(false);
		} catch (Exception e) {
			// We have an expected error
			assertEquals("The SliderAnimate must have one not null parameter", e.getMessage());
		}
	}
}

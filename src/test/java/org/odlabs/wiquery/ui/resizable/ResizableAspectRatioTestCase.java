package org.odlabs.wiquery.ui.resizable;

import junit.framework.TestCase;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ResizableAspectRatioTestCase extends TestCase{
	protected static final Logger log = LoggerFactory.getLogger(
			ResizableAspectRatioTestCase.class);

	@Test
	public void testGetJavaScriptOption() {
		ResizableAspectRatio aspectRatio  = new ResizableAspectRatio(true);
		
		// Boolean param
		String expectedJavascript = "true";
		String generatedJavascript = aspectRatio.getJavascriptOption().toString();
		
		log.info(expectedJavascript);
		log.info(generatedJavascript);		
		Assert.assertEquals(generatedJavascript, expectedJavascript);
		
		// Float param
		aspectRatio.setFloatParam(0.5F);
		expectedJavascript = "0.5";
		generatedJavascript = aspectRatio.getJavascriptOption().toString();
		
		log.info(expectedJavascript);
		log.info(generatedJavascript);		
		Assert.assertEquals(generatedJavascript, expectedJavascript);
		
		// Null param
		aspectRatio.setFloatParam(null);
		try {
			generatedJavascript = aspectRatio.getJavascriptOption().toString();
			assertTrue(false);
		} catch (Exception e) {
			// We have an expected error
			assertEquals("The ResizableAspectRatio must have one not null parameter", e.getMessage());
		}
	}
}

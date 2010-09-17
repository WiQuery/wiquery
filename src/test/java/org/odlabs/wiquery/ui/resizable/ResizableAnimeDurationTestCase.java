package org.odlabs.wiquery.ui.resizable;

import junit.framework.TestCase;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ResizableAnimeDurationTestCase extends TestCase{
	protected static final Logger log = LoggerFactory.getLogger(
			ResizableAnimeDurationTestCase.class);

	@Test
	public void testGetJavaScriptOption() {
		ResizableAnimeDuration resizableAnimeDuration = new ResizableAnimeDuration(5);
		
		// Integer param
		String expectedJavascript = "5";
		String generatedJavascript = resizableAnimeDuration.getJavascriptOption().toString();
		
		log.info(expectedJavascript);
		log.info(generatedJavascript);		
		Assert.assertEquals(generatedJavascript, expectedJavascript);
		
		// DurationEnum param
		resizableAnimeDuration.setDurationEnumParam(ResizableAnimeDuration.DurationEnum.FAST);
		expectedJavascript = ResizableAnimeDuration.DurationEnum.FAST.toString();
		generatedJavascript = resizableAnimeDuration.getJavascriptOption().toString();
		
		log.info(expectedJavascript);
		log.info(generatedJavascript);		
		Assert.assertEquals(generatedJavascript, expectedJavascript);
		
		// Null param
		resizableAnimeDuration.setDurationEnumParam(null);
		try {
			generatedJavascript = resizableAnimeDuration.getJavascriptOption().toString();
			assertTrue(false);
		} catch (Exception e) {
			// We have an expected error
			assertEquals("The ResizableAnimeDuration must have one not null parameter", e.getMessage());
		}
	}
}

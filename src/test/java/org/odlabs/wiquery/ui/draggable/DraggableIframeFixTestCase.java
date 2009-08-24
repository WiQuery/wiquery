package org.odlabs.wiquery.ui.draggable;

import junit.framework.TestCase;

import org.odlabs.wiquery.core.options.LiteralOption;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

public class DraggableIframeFixTestCase extends TestCase{
	protected static final Logger log = LoggerFactory.getLogger(
			DraggableIframeFixTestCase.class);

	@Test
	public void testGetJavaScriptOption() {
		DraggableIframeFix iframeFix  = new DraggableIframeFix(true);
		
		// Boolean param
		String expectedJavascript = "true";
		String generatedJavascript = iframeFix.getJavascriptOption().toString();
		
		log.info(expectedJavascript);
		log.info(generatedJavascript);		
		Assert.assertEquals(generatedJavascript, expectedJavascript);
		
		// Selector param
		iframeFix.setSelectorParam("ul");
		expectedJavascript = "'ul'";
		generatedJavascript = iframeFix.getJavascriptOption().toString();
		
		log.info(expectedJavascript);
		log.info(generatedJavascript);		
		Assert.assertEquals(generatedJavascript, expectedJavascript);
		
		// Null param
		iframeFix.setSelectorParam(null);
		try {
			generatedJavascript = iframeFix.getJavascriptOption().toString();
			assertTrue(false);
		} catch (Exception e) {
			// We have an expected error
			assertEquals("The DraggableIframeFix must have one not null parameter", e.getMessage());
		}
	}
}

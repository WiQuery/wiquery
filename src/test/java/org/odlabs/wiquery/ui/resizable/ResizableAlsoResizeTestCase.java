package org.odlabs.wiquery.ui.resizable;

import junit.framework.TestCase;

import org.odlabs.wiquery.core.options.LiteralOption;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ResizableAlsoResizeTestCase extends TestCase{
	protected static final Logger log = LoggerFactory.getLogger(
			ResizableAlsoResizeTestCase.class);

	@Test
	public void testGetJavaScriptOption() {
		ResizableAlsoResize alsoResize  = new ResizableAlsoResize("jQuery('#test')");
		
		// Object param
		String expectedJavascript = "jQuery('#test')";
		String generatedJavascript = alsoResize.getJavascriptOption().toString();
		
		log.info(expectedJavascript);
		log.info(generatedJavascript);		
		Assert.assertEquals(generatedJavascript, expectedJavascript);
		
		// Literal param
		alsoResize.setLiteralParam(new LiteralOption(".other"));
		expectedJavascript = "'.other'";
		generatedJavascript = alsoResize.getJavascriptOption().toString();
		
		log.info(expectedJavascript);
		log.info(generatedJavascript);		
		Assert.assertEquals(generatedJavascript, expectedJavascript);
		
		// Null param
		alsoResize.setObjectParam(null);
		try {
			generatedJavascript = alsoResize.getJavascriptOption().toString();
			assertTrue(false);
		} catch (Exception e) {
			// We have an expected error
			assertEquals("The ResizableAlsoResize must have one not null parameter", e.getMessage());
		}
	}
}

package org.odlabs.wiquery.ui.resizable;

import junit.framework.TestCase;

import org.odlabs.wiquery.core.options.LiteralOption;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.junit.Assert;
import org.junit.Test;

public class ResizableHandlesTestCase extends TestCase{
	protected static final Logger log = LoggerFactory.getLogger(
			ResizableHandlesTestCase.class);

	@Test
	public void testGetJavaScriptOption() {
		ResizableHandles handles  = new ResizableHandles("jQuery('#test')");
		
		// Object param
		String expectedJavascript = "jQuery('#test')";
		String generatedJavascript = handles.getJavascriptOption().toString();
		
		log.info(expectedJavascript);
		log.info(generatedJavascript);		
		Assert.assertEquals(generatedJavascript, expectedJavascript);
		
		// Literal param
		handles.setLiteralParam(new LiteralOption("e, s, se"));
		expectedJavascript = "'e, s, se'";
		generatedJavascript = handles.getJavascriptOption().toString();
		
		log.info(expectedJavascript);
		log.info(generatedJavascript);		
		Assert.assertEquals(generatedJavascript, expectedJavascript);
		
		// Null param
		handles.setObjectParam(null);
		try {
			generatedJavascript = handles.getJavascriptOption().toString();
			assertTrue(false);
		} catch (Exception e) {
			// We have an expected error
			assertEquals("The ResizableHandles must have one not null parameter", e.getMessage());
		}
	}
}

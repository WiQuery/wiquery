package org.odlabs.wiquery.ui.droppable;

import junit.framework.TestCase;

import org.odlabs.wiquery.core.javascript.JsScope;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.junit.Assert;
import org.junit.Test;

public class DroppableAcceptTestCase extends TestCase{
	protected static final Logger log = LoggerFactory.getLogger(
			DroppableAcceptTestCase.class);

	@Test
	public void testGetJavaScriptOption() {
		DroppableAccept helper  = new DroppableAccept(".special");
		
		// HelperEnum param
		String expectedJavascript = "'.special'";
		String generatedJavascript = helper.getJavascriptOption().toString();
		
		log.info(expectedJavascript);
		log.info(generatedJavascript);		
		Assert.assertEquals(generatedJavascript, expectedJavascript);
		
		// Function param
		helper.setFunctionParam(JsScope.quickScope("return true;"));
		expectedJavascript = "function() {\n\treturn true;\n}";
		generatedJavascript = helper.getJavascriptOption().toString();
		
		log.info(expectedJavascript);
		log.info(generatedJavascript);		
		Assert.assertEquals(generatedJavascript, expectedJavascript);
		
		// Null param
		helper.setHelperEnumParam(null);
		try {
			generatedJavascript = helper.getJavascriptOption().toString();
			assertTrue(false);
		} catch (Exception e) {
			// We have an expected error
			assertEquals("The DroppableAccept must have one not null parameter", e.getMessage());
		}
	}
}

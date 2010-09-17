package org.odlabs.wiquery.ui.draggable;

import junit.framework.TestCase;

import org.odlabs.wiquery.core.javascript.JsScope;
import org.odlabs.wiquery.ui.draggable.DraggableHelper.HelperEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

public class DraggableHelperTestCase extends TestCase{
	protected static final Logger log = LoggerFactory.getLogger(
			DraggableHelperTestCase.class);

	@Test
	public void testGetJavaScriptOption() {
		DraggableHelper helper  = new DraggableHelper(HelperEnum.CLONE);
		
		// HelperEnum param
		String expectedJavascript = HelperEnum.CLONE.toString();
		String generatedJavascript = helper.getJavascriptOption().toString();
		
		log.info(expectedJavascript);
		log.info(generatedJavascript);		
		Assert.assertEquals(generatedJavascript, expectedJavascript);
		
		// Function param
		helper.setFunctionParam(JsScope.quickScope("return document.getElementById('anId');"));
		expectedJavascript = "function() {\n\treturn document.getElementById('anId');\n}";
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
			assertEquals("The DraggableHelper must have one not null parameter", e.getMessage());
		}
	}
}

package org.odlabs.wiquery.ui.resizable;

import junit.framework.TestCase;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.junit.Assert;
import org.junit.Test;

public class ResizableContainmentTestCase extends TestCase{
	protected static final Logger log = LoggerFactory.getLogger(
			ResizableContainmentTestCase.class);

	@Test
	public void testGetJavaScriptOption() {
		ResizableContainment containment  = new ResizableContainment("jQuery('#test')");
		
		// Selector param
		String expectedJavascript = "jQuery('#test')";
		String generatedJavascript = containment.getJavascriptOption().toString();
		
		log.info(expectedJavascript);
		log.info(generatedJavascript);		
		Assert.assertEquals(generatedJavascript, expectedJavascript);
		
		// Literal param
		containment.setElementEnumParam(ResizableContainment.ElementEnum.PARENT);
		expectedJavascript = ResizableContainment.ElementEnum.PARENT.toString();
		generatedJavascript = containment.getJavascriptOption().toString();
		
		log.info(expectedJavascript);
		log.info(generatedJavascript);		
		Assert.assertEquals(generatedJavascript, expectedJavascript);
		
		// Null param
		containment.setElementOrSelectorParam(null);
		try {
			generatedJavascript = containment.getJavascriptOption().toString();
			assertTrue(false);
		} catch (Exception e) {
			// We have an expected error
			assertEquals("The ResizableContainment must have one not null parameter", e.getMessage());
		}
	}
}

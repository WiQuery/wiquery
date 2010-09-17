package org.odlabs.wiquery.ui.sortable;

import junit.framework.TestCase;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SortableContainmentTestCase extends TestCase{
	protected static final Logger log = LoggerFactory.getLogger(
			SortableContainmentTestCase.class);

	@Test
	public void testGetJavaScriptOption() {
		SortableContainment containment  = new SortableContainment("#test");
		
		// Literal param
		String expectedJavascript = "'#test'";
		String generatedJavascript = containment.getJavascriptOption().toString();
		
		log.info(expectedJavascript);
		log.info(generatedJavascript);		
		Assert.assertEquals(generatedJavascript, expectedJavascript);
		
		// Element param
		containment.setElementEnumParam(SortableContainment.ElementEnum.PARENT);
		expectedJavascript = SortableContainment.ElementEnum.PARENT.toString();
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
			assertEquals("The SortableContainment must have one not null parameter", e.getMessage());
		}
	}
}

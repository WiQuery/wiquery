package org.odlabs.wiquery.ui.draggable;

import junit.framework.TestCase;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

public class DraggableSnapTestCase extends TestCase{
	protected static final Logger log = LoggerFactory.getLogger(
			DraggableSnapTestCase.class);

	@Test
	public void testGetJavaScriptOption() {
		DraggableSnap snap  = new DraggableSnap(true);
		
		// Boolean param
		String expectedJavascript = "true";
		String generatedJavascript = snap.getJavascriptOption().toString();
		
		log.info(expectedJavascript);
		log.info(generatedJavascript);		
		Assert.assertEquals(generatedJavascript, expectedJavascript);
		
		// Selector param
		snap.setSelectorParam("ul");
		expectedJavascript = "'ul'";
		generatedJavascript = snap.getJavascriptOption().toString();
		
		log.info(expectedJavascript);
		log.info(generatedJavascript);		
		Assert.assertEquals(generatedJavascript, expectedJavascript);
		
		// Null param
		snap.setSelectorParam(null);
		try {
			generatedJavascript = snap.getJavascriptOption().toString();
			assertTrue(false);
		} catch (Exception e) {
			// We have an expected error
			assertEquals("The DraggableSnap must have one not null parameter", e.getMessage());
		}
	}
}

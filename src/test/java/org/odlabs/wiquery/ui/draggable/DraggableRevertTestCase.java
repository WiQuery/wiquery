package org.odlabs.wiquery.ui.draggable;

import junit.framework.TestCase;

import org.odlabs.wiquery.ui.draggable.DraggableRevert.RevertEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

public class DraggableRevertTestCase extends TestCase{
	protected static final Logger log = LoggerFactory.getLogger(
			DraggableRevertTestCase.class);

	@Test
	public void testGetJavaScriptOption() {
		DraggableRevert revert  = new DraggableRevert(true);
		
		// Boolean param
		String expectedJavascript = "true";
		String generatedJavascript = revert.getJavascriptOption().toString();
		
		log.info(expectedJavascript);
		log.info(generatedJavascript);		
		Assert.assertEquals(generatedJavascript, expectedJavascript);
		
		// RevertEnum param
		revert.setRevertEnumParam(RevertEnum.VALID);
		expectedJavascript = RevertEnum.VALID.toString();
		generatedJavascript = revert.getJavascriptOption().toString();
		
		log.info(expectedJavascript);
		log.info(generatedJavascript);		
		Assert.assertEquals(generatedJavascript, expectedJavascript);
		
		// Null param
		revert.setRevertEnumParam(null);
		try {
			generatedJavascript = revert.getJavascriptOption().toString();
			assertTrue(false);
		} catch (Exception e) {
			// We have an expected error
			assertEquals("The DraggableRevert must have one not null parameter", e.getMessage());
		}
	}
}

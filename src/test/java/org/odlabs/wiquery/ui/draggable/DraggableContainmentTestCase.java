package org.odlabs.wiquery.ui.draggable;

import junit.framework.TestCase;

import org.odlabs.wiquery.ui.draggable.DraggableContainment.ContainmentEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

public class DraggableContainmentTestCase extends TestCase{
	protected static final Logger log = LoggerFactory.getLogger(
			DraggableContainmentTestCase.class);

	@Test
	public void testGetJavaScriptOption() {
		DraggableContainment containment  = new DraggableContainment(ContainmentEnum.WINDOW);
		
		// ContainmentEnum param
		String expectedJavascript = ContainmentEnum.WINDOW.toString();
		String generatedJavascript = containment.getJavascriptOption().toString();
		
		log.info(expectedJavascript);
		log.info(generatedJavascript);		
		Assert.assertEquals(generatedJavascript, expectedJavascript);
		
		// Literal param
		containment.setStringParam("'#anId'");
		expectedJavascript = "'#anId'";
		generatedJavascript = containment.getJavascriptOption().toString();
		
		log.info(expectedJavascript);
		log.info(generatedJavascript);		
		Assert.assertEquals(generatedJavascript, expectedJavascript);
		
		// Array param
		containment.setArrayParam(1, 2, 3, 4);
		expectedJavascript = "[1,2,3,4]";
		generatedJavascript = containment.getJavascriptOption().toString();
		
		log.info(expectedJavascript);
		log.info(generatedJavascript);		
		Assert.assertEquals(generatedJavascript, expectedJavascript);
		
		// Null param
		containment.setContainmentEnumParam(null);
		try {
			generatedJavascript = containment.getJavascriptOption().toString();
			assertTrue(false);
		} catch (Exception e) {
			// We have an expected error
			assertEquals("The DraggableContainment must have one not null parameter", e.getMessage());
		}
	}
}

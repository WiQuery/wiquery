package org.odlabs.wiquery.ui.resizable;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.odlabs.wiquery.tester.WiQueryTestCase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ResizableContainmentTestCase extends WiQueryTestCase {
	protected static final Logger log = LoggerFactory
			.getLogger(ResizableContainmentTestCase.class);

	@Test
	public void testGetJavaScriptOption() {
		ResizableContainment containment = new ResizableContainment(
				"jQuery('#test')");

		// Selector param
		String expectedJavascript = "jQuery('#test')";
		String generatedJavascript = containment.getJavascriptOption()
				.toString();

		log.info(expectedJavascript);
		log.info(generatedJavascript);
		assertEquals(generatedJavascript, expectedJavascript);

		// Literal param
		containment
				.setElementEnumParam(ResizableContainment.ElementEnum.PARENT);
		expectedJavascript = ResizableContainment.ElementEnum.PARENT.toString();
		generatedJavascript = containment.getJavascriptOption().toString();

		log.info(expectedJavascript);
		log.info(generatedJavascript);
		assertEquals(generatedJavascript, expectedJavascript);

		// Null param
		containment.setElementOrSelectorParam(null);
		try {
			generatedJavascript = containment.getJavascriptOption().toString();
			assertTrue(false);
		} catch (Exception e) {
			// We have an expected error
			assertEquals(
					"The ResizableContainment must have one not null parameter",
					e.getMessage());
		}
	}
}

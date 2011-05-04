package org.odlabs.wiquery.ui.droppable;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.odlabs.wiquery.core.javascript.JsScope;
import org.odlabs.wiquery.tester.WiQueryTestCase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DroppableAcceptTestCase extends WiQueryTestCase {
	protected static final Logger log = LoggerFactory
			.getLogger(DroppableAcceptTestCase.class);

	@Test
	public void testGetJavaScriptOption() {
		DroppableAccept helper = new DroppableAccept(".special");

		// HelperEnum param
		String expectedJavascript = "'.special'";
		String generatedJavascript = helper.getJavascriptOption().toString();

		log.info(expectedJavascript);
		log.info(generatedJavascript);
		assertEquals(generatedJavascript, expectedJavascript);

		// Function param
		helper.setFunctionParam(JsScope.quickScope("return true;"));
		expectedJavascript = "function() {\n\treturn true;\n}";
		generatedJavascript = helper.getJavascriptOption().toString();

		log.info(expectedJavascript);
		log.info(generatedJavascript);
		assertEquals(generatedJavascript, expectedJavascript);

		// Null param
		helper.setHelperEnumParam(null);
		try {
			generatedJavascript = helper.getJavascriptOption().toString();
			assertTrue(false);
		} catch (Exception e) {
			// We have an expected error
			assertEquals(
					"The DroppableAccept must have one not null parameter", e
							.getMessage());
		}
	}
}

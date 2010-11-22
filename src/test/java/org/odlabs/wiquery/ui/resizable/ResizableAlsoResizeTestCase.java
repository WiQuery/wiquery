package org.odlabs.wiquery.ui.resizable;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.odlabs.wiquery.core.options.LiteralOption;
import org.odlabs.wiquery.tester.WiQueryTestCase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ResizableAlsoResizeTestCase extends WiQueryTestCase {
	protected static final Logger log = LoggerFactory
			.getLogger(ResizableAlsoResizeTestCase.class);

	@Test
	public void testGetJavaScriptOption() {
		ResizableAlsoResize alsoResize = new ResizableAlsoResize(
				"jQuery('#test')");

		// Object param
		String expectedJavascript = "jQuery('#test')";
		String generatedJavascript = alsoResize.getJavascriptOption()
				.toString();

		log.info(expectedJavascript);
		log.info(generatedJavascript);
		assertEquals(generatedJavascript, expectedJavascript);

		// Literal param
		alsoResize.setLiteralParam(new LiteralOption(".other"));
		expectedJavascript = "'.other'";
		generatedJavascript = alsoResize.getJavascriptOption().toString();

		log.info(expectedJavascript);
		log.info(generatedJavascript);
		assertEquals(generatedJavascript, expectedJavascript);

		// Null param
		alsoResize.setObjectParam(null);
		try {
			generatedJavascript = alsoResize.getJavascriptOption().toString();
			assertTrue(false);
		} catch (Exception e) {
			// We have an expected error
			assertEquals(
					"The ResizableAlsoResize must have one not null parameter",
					e.getMessage());
		}
	}
}

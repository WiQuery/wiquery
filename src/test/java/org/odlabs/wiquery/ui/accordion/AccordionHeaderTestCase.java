package org.odlabs.wiquery.ui.accordion;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.odlabs.wiquery.core.options.LiteralOption;
import org.odlabs.wiquery.tester.WiQueryTestCase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AccordionHeaderTestCase extends WiQueryTestCase {
	protected static final Logger log = LoggerFactory
			.getLogger(AccordionHeaderTestCase.class);

	@Test
	public void testGetJavaScriptOption() {
		AccordionHeader header = new AccordionHeader("jQuery('#test')");

		// Object param
		String expectedJavascript = "jQuery('#test')";
		String generatedJavascript = header.getJavascriptOption().toString();

		log.info(expectedJavascript);
		log.info(generatedJavascript);
		assertEquals(generatedJavascript, expectedJavascript);

		// Literal param
		header.setLiteralParam(new LiteralOption(
				"> li > :first-child,> :not(li):even"));
		expectedJavascript = "'> li > :first-child,> :not(li):even'";
		generatedJavascript = header.getJavascriptOption().toString();

		log.info(expectedJavascript);
		log.info(generatedJavascript);
		assertEquals(generatedJavascript, expectedJavascript);

		// Null param
		header.setObjectParam(null);
		try {
			generatedJavascript = header.getJavascriptOption().toString();
			assertTrue(false);
		} catch (Exception e) {
			// We have an expected error
			assertEquals(
					"The AccordionHeader must have one not null parameter", e
							.getMessage());
		}
	}
}

package org.odlabs.wiquery.ui.accordion;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.odlabs.wiquery.core.options.LiteralOption;
import org.odlabs.wiquery.tester.WiQueryTestCase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AccordionActiveTestCase extends WiQueryTestCase {
	protected static final Logger log = LoggerFactory
			.getLogger(AccordionActiveTestCase.class);

	@Test
	public void testGetJavaScriptOption() {
		AccordionActive accordionActive = new AccordionActive(5);

		// Int param
		String expectedJavascript = "5";
		String generatedJavascript = accordionActive.getJavascriptOption()
				.toString();

		log.info(expectedJavascript);
		log.info(generatedJavascript);
		assertEquals(generatedJavascript, expectedJavascript);

		// Boolean param
		accordionActive.setBooleanParam(false);
		expectedJavascript = "false";
		generatedJavascript = accordionActive.getJavascriptOption().toString();

		log.info(expectedJavascript);
		log.info(generatedJavascript);
		assertEquals(generatedJavascript, expectedJavascript);

		// jQuery param
		accordionActive.setJQueryParam("$('#test')");
		expectedJavascript = "$('#test')";
		generatedJavascript = accordionActive.getJavascriptOption().toString();

		log.info(expectedJavascript);
		log.info(generatedJavascript);
		assertEquals(generatedJavascript, expectedJavascript);

		// Selector or element param
		accordionActive.setSelectorOrElementParam(new LiteralOption("div"));
		expectedJavascript = "'div'";
		generatedJavascript = accordionActive.getJavascriptOption().toString();

		log.info(expectedJavascript);
		log.info(generatedJavascript);
		assertEquals(generatedJavascript, expectedJavascript);

		// Null param
		accordionActive.setJQueryParam(null);
		try {
			generatedJavascript = accordionActive.getJavascriptOption()
					.toString();
			assertTrue(false);
		} catch (Exception e) {
			// We have an expected error
			assertEquals(
					"The AccordionActive must have one not null parameter", e
							.getMessage());
		}
	}
}

package org.odlabs.wiquery.ui.accordion;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.odlabs.wiquery.tester.WiQueryTestCase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AccordionAnimatedTestCase extends WiQueryTestCase {
	protected static final Logger log = LoggerFactory
			.getLogger(AccordionAnimatedTestCase.class);

	@Test
	public void testGetJavaScriptOption() {
		AccordionAnimated snap = new AccordionAnimated(true);

		// Boolean param
		String expectedJavascript = "true";
		String generatedJavascript = snap.getJavascriptOption().toString();

		log.info(expectedJavascript);
		log.info(generatedJavascript);
		assertEquals(generatedJavascript, expectedJavascript);

		// Effect param
		snap.setEffectParam("slide");
		expectedJavascript = "'slide'";
		generatedJavascript = snap.getJavascriptOption().toString();

		log.info(expectedJavascript);
		log.info(generatedJavascript);
		assertEquals(generatedJavascript, expectedJavascript);

		// Null param
		snap.setEffectParam(null);
		try {
			generatedJavascript = snap.getJavascriptOption().toString();
			assertTrue(false);
		} catch (Exception e) {
			// We have an expected error
			assertEquals(
					"The AccordionAnimated must have one not null parameter",
					e.getMessage());
		}
	}

	@Override
	protected Logger getLog() {
		return log;
	}
}

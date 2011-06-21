package org.odlabs.wiquery.ui.slider;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.odlabs.wiquery.tester.WiQueryTestCase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SliderRangeTestCase extends WiQueryTestCase {
	protected static final Logger log = LoggerFactory
			.getLogger(SliderRangeTestCase.class);

	@Test
	public void testGetJavaScriptOption() {
		SliderRange accordionActive = new SliderRange(true);

		// Boolean param
		String expectedJavascript = "true";
		String generatedJavascript = accordionActive.getJavascriptOption()
				.toString();

		log.info(expectedJavascript);
		log.info(generatedJavascript);
		assertEquals(generatedJavascript, expectedJavascript);

		// Literal param
		accordionActive.setRangeEnumParam(SliderRange.RangeEnum.MAX);
		expectedJavascript = SliderRange.RangeEnum.MAX.toString();
		generatedJavascript = accordionActive.getJavascriptOption().toString();

		log.info(expectedJavascript);
		log.info(generatedJavascript);
		assertEquals(generatedJavascript, expectedJavascript);

		// Null param
		accordionActive.setRangeEnumParam(null);
		try {
			generatedJavascript = accordionActive.getJavascriptOption()
					.toString();
			assertTrue(false);
		} catch (Exception e) {
			// We have an expected error
			assertEquals("The SliderRange must have one not null parameter",
					e.getMessage());
		}
	}

	@Override
	protected Logger getLog() {
		return log;
	}
}

package org.odlabs.wiquery.ui.resizable;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.odlabs.wiquery.tester.WiQueryTestCase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ResizableAnimeDurationTestCase extends WiQueryTestCase {
	protected static final Logger log = LoggerFactory
			.getLogger(ResizableAnimeDurationTestCase.class);

	@Test
	public void testGetJavaScriptOption() {
		ResizableAnimeDuration resizableAnimeDuration = new ResizableAnimeDuration(
				5);

		// Integer param
		String expectedJavascript = "5";
		String generatedJavascript = resizableAnimeDuration
				.getJavascriptOption().toString();

		log.info(expectedJavascript);
		log.info(generatedJavascript);
		assertEquals(generatedJavascript, expectedJavascript);

		// DurationEnum param
		resizableAnimeDuration
				.setDurationEnumParam(ResizableAnimeDuration.DurationEnum.FAST);
		expectedJavascript = ResizableAnimeDuration.DurationEnum.FAST
				.toString();
		generatedJavascript = resizableAnimeDuration.getJavascriptOption()
				.toString();

		log.info(expectedJavascript);
		log.info(generatedJavascript);
		assertEquals(generatedJavascript, expectedJavascript);

		// Null param
		resizableAnimeDuration.setDurationEnumParam(null);
		try {
			generatedJavascript = resizableAnimeDuration.getJavascriptOption()
					.toString();
			assertTrue(false);
		} catch (Exception e) {
			// We have an expected error
			assertEquals(
					"The ResizableAnimeDuration must have one not null parameter",
					e.getMessage());
		}
	}

	@Override
	protected Logger getLog() {
		return log;
	}
}

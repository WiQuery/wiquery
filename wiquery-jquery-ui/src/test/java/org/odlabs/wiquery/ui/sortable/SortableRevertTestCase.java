package org.odlabs.wiquery.ui.sortable;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.odlabs.wiquery.tester.WiQueryTestCase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SortableRevertTestCase extends WiQueryTestCase {
	protected static final Logger log = LoggerFactory
			.getLogger(SortableRevertTestCase.class);

	@Test
	public void testGetJavaScriptOption() {
		SortableRevert revert = new SortableRevert(true);

		// Boolean param
		String expectedJavascript = "true";
		String generatedJavascript = revert.getJavascriptOption().toString();

		log.info(expectedJavascript);
		log.info(generatedJavascript);
		assertEquals(generatedJavascript, expectedJavascript);

		// Integer param
		revert.setIntParam(25);
		expectedJavascript = "25";
		generatedJavascript = revert.getJavascriptOption().toString();

		log.info(expectedJavascript);
		log.info(generatedJavascript);
		assertEquals(generatedJavascript, expectedJavascript);

		// Null param
		revert.setIntParam(null);
		try {
			generatedJavascript = revert.getJavascriptOption().toString();
			assertTrue(false);
		} catch (Exception e) {
			// We have an expected error
			assertEquals("The SortableRevert must have one not null parameter",
					e.getMessage());
		}
	}

	@Override
	protected Logger getLog() {
		return log;
	}
}

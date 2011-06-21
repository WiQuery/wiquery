package org.odlabs.wiquery.ui.sortable;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.odlabs.wiquery.core.options.LiteralOption;
import org.odlabs.wiquery.tester.WiQueryTestCase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SortableContainmentTestCase extends WiQueryTestCase {
	protected static final Logger log = LoggerFactory
			.getLogger(SortableContainmentTestCase.class);

	@Test
	public void testGetJavaScriptOption() {
		SortableContainment containment = new SortableContainment("#test");

		// Literal param
		String expectedJavascript = "'#test'";
		String generatedJavascript = containment.getJavascriptOption()
				.toString();

		// Selector param
		containment.setSelector(new LiteralOption("#test"));
		expectedJavascript = "'#test'";
		generatedJavascript = containment.getJavascriptOption().toString();

		log.info(expectedJavascript);
		log.info(generatedJavascript);
		assertEquals(generatedJavascript, expectedJavascript);

		// Element param
		containment.setElementEnumParam(SortableContainment.ElementEnum.PARENT);
		expectedJavascript = SortableContainment.ElementEnum.PARENT.toString();
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
					"The SortableContainment must have one not null parameter",
					e.getMessage());
		}
	}

	@Override
	protected Logger getLog() {
		return log;
	}
}

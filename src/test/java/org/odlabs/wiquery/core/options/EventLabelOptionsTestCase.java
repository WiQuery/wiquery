package org.odlabs.wiquery.core.options;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.odlabs.wiquery.core.events.MouseEvent;
import org.odlabs.wiquery.tester.WiQueryTestCase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EventLabelOptionsTestCase extends WiQueryTestCase {
	protected static final Logger log = LoggerFactory
			.getLogger(EventLabelOptionsTestCase.class);

	/**
	 * Method to test the generated javascript
	 */
	@Test
	public void testJavascriptOption() {
		EventLabelOptions event = new EventLabelOptions(MouseEvent.CLICK);

		String expectedJavascript = "'click'";
		String generatedJavascript = event.getJavascriptOption().toString();
		log.info(expectedJavascript);
		log.info(generatedJavascript);
		assertEquals(generatedJavascript, expectedJavascript);
	}
}

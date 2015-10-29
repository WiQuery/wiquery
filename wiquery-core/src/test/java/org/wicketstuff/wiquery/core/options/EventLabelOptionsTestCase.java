package org.wicketstuff.wiquery.core.options;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.wicketstuff.wiquery.core.events.MouseEvent;
import org.wicketstuff.wiquery.core.options.EventLabelOptions;
import org.wicketstuff.wiquery.tester.WiQueryTestCase;

public class EventLabelOptionsTestCase extends WiQueryTestCase
{
	protected static final Logger log = LoggerFactory.getLogger(EventLabelOptionsTestCase.class);

	/**
	 * Method to test the generated javascript
	 */
	@Test
	public void testJavascriptOption()
	{
		EventLabelOptions event = new EventLabelOptions(MouseEvent.CLICK);

		String expectedJavascript = "'click'";
		String generatedJavascript = event.getJavascriptOption().toString();
		log.info(expectedJavascript);
		log.info(generatedJavascript);
		assertEquals(generatedJavascript, expectedJavascript);
	}

	@Override
	protected Logger getLog()
	{
		return log;
	}
}

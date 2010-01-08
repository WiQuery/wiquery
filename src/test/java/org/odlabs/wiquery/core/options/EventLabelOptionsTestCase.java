package org.odlabs.wiquery.core.options;

import org.odlabs.wiquery.core.events.MouseEvent;
import org.odlabs.wiquery.core.options.EventLabelOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import junit.framework.TestCase;

public class EventLabelOptionsTestCase extends TestCase {
	protected static final Logger log = LoggerFactory.getLogger(
			EventLabelOptionsTestCase.class);
	
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
		Assert.assertEquals(generatedJavascript, expectedJavascript);
	}
}

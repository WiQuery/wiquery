package org.odlabs.wiquery.ui.position;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.odlabs.wiquery.tester.WiQueryTestCase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Test for {@link PositionOffset}
 * 
 * @author Julien Roche
 */
public class PositionOffsetTestCase extends WiQueryTestCase {
	protected static final Logger log = LoggerFactory
			.getLogger(PositionOffsetTestCase.class);

	/**
	 * Test of the javascript generation
	 */
	@Test
	public void testGetJavaScriptOption() {
		PositionOffset yearRAnge = new PositionOffset(5, 10);

		// Short params
		String expectedJavascript = "'5 10'";
		String generatedJavascript = yearRAnge.getJavascriptOption().toString();

		log.info(expectedJavascript);
		log.info(generatedJavascript);
		assertEquals(generatedJavascript, expectedJavascript);
	}
}

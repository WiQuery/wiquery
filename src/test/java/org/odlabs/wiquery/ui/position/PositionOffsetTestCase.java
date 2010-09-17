package org.odlabs.wiquery.ui.position;

import junit.framework.TestCase;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Test for {@link PositionOffset}
 * @author Julien Roche
 *
 */
public class PositionOffsetTestCase extends TestCase{
	protected static final Logger log = LoggerFactory.getLogger(
			PositionOffsetTestCase.class);

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
		Assert.assertEquals(generatedJavascript, expectedJavascript);
	}
}

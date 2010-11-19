package org.odlabs.wiquery.core.options;

import junit.framework.TestCase;

import org.odlabs.wiquery.core.options.LiteralOption;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.junit.Assert;
import org.junit.Test;

/**
 * Unit test on the {@link LiteralOption}
 * @author Julien Roche
 *
 */
public class LiteralOptionTestCase extends TestCase {

	protected static final Logger log = LoggerFactory.getLogger(
			LiteralOptionTestCase.class);
	
	/**
	 * Test {@link LiteralOption#toString()}
	 */
	@Test
	public void testToString() {
		// With quote
		String expectedJavascript = "'a'";
		String generatedJavascript = new LiteralOption("a").toString();
		
		log.info(expectedJavascript);
		log.info(generatedJavascript);
		
		Assert.assertEquals(generatedJavascript, expectedJavascript);
		
		// With double quote
		expectedJavascript = "\"Hello\"";
		generatedJavascript = new LiteralOption("Hello", true).toString();
		
		log.info(expectedJavascript);
		log.info(generatedJavascript);
		
		Assert.assertEquals(generatedJavascript, expectedJavascript);
	}
}

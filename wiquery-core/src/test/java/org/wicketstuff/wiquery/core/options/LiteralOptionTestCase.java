package org.wicketstuff.wiquery.core.options;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.wicketstuff.wiquery.core.options.LiteralOption;
import org.wicketstuff.wiquery.tester.WiQueryTestCase;

/**
 * Unit test on the {@link LiteralOption}
 * 
 * @author Julien Roche
 */
public class LiteralOptionTestCase extends WiQueryTestCase
{

	protected static final Logger log = LoggerFactory.getLogger(LiteralOptionTestCase.class);

	/**
	 * Test {@link LiteralOption#toString()}
	 */
	@Test
	public void testToString()
	{
		// With quote
		String expectedJavascript = "'a'";
		String generatedJavascript = new LiteralOption("a").toString();

		log.info(expectedJavascript);
		log.info(generatedJavascript);

		assertEquals(generatedJavascript, expectedJavascript);

		// With double quote
		expectedJavascript = "\"Hello\"";
		generatedJavascript = new LiteralOption("Hello", true).toString();

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

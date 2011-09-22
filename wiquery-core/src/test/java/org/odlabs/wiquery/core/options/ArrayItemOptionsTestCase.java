package org.odlabs.wiquery.core.options;

import static org.junit.Assert.*;

import org.junit.Test;
import org.odlabs.wiquery.tester.WiQueryTestCase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Unit test on the {@link ArrayItemOptions}
 * 
 * @author Julien Roche
 */
public class ArrayItemOptionsTestCase extends WiQueryTestCase
{

	protected static final Logger log = LoggerFactory.getLogger(ArrayItemOptionsTestCase.class);

	/**
	 * Check the syntax
	 */
	@Test
	public void testGetJavascriptItemOptions()
	{
		ArrayItemOptions<IntegerItemOptions> options = new ArrayItemOptions<IntegerItemOptions>();
		IntegerItemOptions o1 = new IntegerItemOptions(5);
		IntegerItemOptions o2 = new IntegerItemOptions(43);

		String expectedJavascript = "[]";
		String generatedJavascript = options.getJavascriptOption().toString();
		log.info(expectedJavascript);
		log.info(generatedJavascript);
		assertEquals(generatedJavascript, expectedJavascript);

		// Second generation
		options.add(o1);
		options.add(o2);
		expectedJavascript = "[5,43]";
		generatedJavascript = options.getJavascriptOption().toString();
		log.info(expectedJavascript);
		log.info(generatedJavascript);
		assertEquals(generatedJavascript, expectedJavascript);
	}

	/**
	 * Test the values
	 */
	@Test
	public void testValues()
	{
		ArrayItemOptions<IntegerItemOptions> options = new ArrayItemOptions<IntegerItemOptions>();
		IntegerItemOptions o1 = new IntegerItemOptions(5);
		IntegerItemOptions o2 = new IntegerItemOptions(43);

		assertTrue(options.values().length == 0);
		options.add(o1);
		options.add(o2);
		assertTrue(options.values().length == 2);
	}

	@Override
	protected Logger getLog()
	{
		return log;
	}
}

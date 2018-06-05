package org.wicketstuff.wiquery.core.options;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.wicketstuff.wiquery.tester.WiQueryTestCase;

/**
 * Unit test on the {@link ListItemOptions}
 * 
 * @author Julien Roche
 */
public class ListItemOptionsTestCase extends WiQueryTestCase
{

	protected static final Logger log = LoggerFactory.getLogger(ListItemOptionsTestCase.class);

	/**
	 * Check the syntax
	 */
	@Test
	public void testGetJavascriptItemOptions()
	{
		ListItemOptions<IntegerItemOptions> options = new ListItemOptions<>();
		IntegerItemOptions o1 = new IntegerItemOptions(5);
		IntegerItemOptions o2 = new IntegerItemOptions(43);

		String expectedJavascript = "{}";
		String generatedJavascript = options.getJavascriptOption().toString();
		log.info(expectedJavascript);
		log.info(generatedJavascript);
		assertEquals(generatedJavascript, expectedJavascript);

		// Second generation
		options.add(o1);
		options.add(o2);
		expectedJavascript = "{5,43}";
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
		ListItemOptions<IntegerItemOptions> options = new ListItemOptions<>();
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

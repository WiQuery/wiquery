package org.odlabs.wiquery.ui.core.options;

import junit.framework.TestCase;

import org.odlabs.wiquery.core.options.IntegerItemOptions;
import org.odlabs.wiquery.core.options.ListItemOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Unit test on the {@link ListItemOptions}
 * @author Julien Roche
 *
 */
public class ListItemOptionsTestCase extends TestCase {

	protected static final Logger log = LoggerFactory.getLogger(
			ListItemOptionsTestCase.class);

	/**
	 * Check the syntax
	 */
	@Test
	public void testGetJavascriptItemOptions() {
		ListItemOptions<IntegerItemOptions> options = new ListItemOptions<IntegerItemOptions>();
		IntegerItemOptions o1 = new IntegerItemOptions(5);
		IntegerItemOptions o2 = new IntegerItemOptions(43);
		
		String expectedJavascript = "{}";
		String generatedJavascript = options.getJavascriptItemOptions().toString();
		log.info(expectedJavascript);
		log.info(generatedJavascript);
		Assert.assertEquals(generatedJavascript, expectedJavascript);
		
		// Second generation
		options.add(o1);
		options.add(o2);
		expectedJavascript = "{5,43}";
		generatedJavascript = options.getJavascriptItemOptions().toString();
		log.info(expectedJavascript);
		log.info(generatedJavascript);
		Assert.assertEquals(generatedJavascript, expectedJavascript);
	}
	
	/**
	 * Test the values
	 */
	@Test
	public void testValues() {
		ListItemOptions<IntegerItemOptions> options = new ListItemOptions<IntegerItemOptions>();
		IntegerItemOptions o1 = new IntegerItemOptions(5);
		IntegerItemOptions o2 = new IntegerItemOptions(43);
		
		Assert.assertTrue(options.values().length == 0);
		options.add(o1);
		options.add(o2);
		Assert.assertTrue(options.values().length == 2);
	}
}

package org.odlabs.wiquery.core.javascript;

import junit.framework.TestCase;

import org.odlabs.wiquery.core.events.MouseEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Unit test on the {@link JsUtils}
 * @author Julien Roche
 *
 */
public class JsUtilsTestCase extends TestCase {

	protected static final Logger log = LoggerFactory.getLogger(
			JsUtilsTestCase.class);
	
	/**
	 * Test {@link JsUtils#array(CharSequence...)}
	 */
	@Test
	public void testArray() {
		String expectedJavascript = "['a', 'b', 'c']";
		String generatedJavascript = JsUtils.array("'a'", "'b'", "'c'").toString();
		
		log.info(expectedJavascript);
		log.info(generatedJavascript);
		
		Assert.assertEquals(generatedJavascript, expectedJavascript);
	}
	
	/**
	 * Test {@link JsUtils#implode(org.odlabs.wiquery.core.events.EventLabel...)}
	 */
	@Test
	public void testImplode() {
		String expectedJavascript = "'click dblclick'";
		String generatedJavascript = JsUtils.implode(MouseEvent.CLICK, MouseEvent.DBLCLICK);
		
		log.info(expectedJavascript);
		log.info(generatedJavascript);
		
		Assert.assertEquals(generatedJavascript, expectedJavascript);
	}
	
	/**
	 * Test {@link JsUtils#quotes(CharSequence)}
	 */
	@Test
	public void testQuotes() {
		String expectedJavascript = "'a'";
		String generatedJavascript = JsUtils.quotes("a");
		
		log.info(expectedJavascript);
		log.info(generatedJavascript);
		
		Assert.assertEquals(generatedJavascript, expectedJavascript);
	}
	
	/**
	 * Test {@link JsUtils#string(int)}
	 */
	@Test
	public void testString() {
		String expectedJavascript = "5";
		String generatedJavascript = JsUtils.string(5);
		
		log.info(expectedJavascript);
		log.info(generatedJavascript);
		
		Assert.assertEquals(generatedJavascript, expectedJavascript);
	}
}

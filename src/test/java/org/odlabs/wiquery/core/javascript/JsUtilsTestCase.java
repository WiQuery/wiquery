package org.odlabs.wiquery.core.javascript;

import junit.framework.TestCase;

import org.odlabs.wiquery.core.events.MouseEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.junit.Assert;
import org.junit.Test;

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
	 * Test {@link JsUtils#doubleQuotes(CharSequence)} and
	 * {@link JsUtils#doubleQuotes(CharSequence, boolean)}
	 */
	@Test
	public void testDoubleQuotes() {
		// Without escaped quote
		String expectedJavascript = "\"a\"";
		String generatedJavascript = JsUtils.doubleQuotes("a");
		
		log.info(expectedJavascript);
		log.info(generatedJavascript);
		
		Assert.assertEquals(generatedJavascript, expectedJavascript);
		
		// With escaped quote
		expectedJavascript = "\"\\\"Hello\\\"\"";
		generatedJavascript = JsUtils.doubleQuotes("\"Hello\"", true);
		
		log.info(expectedJavascript);
		log.info(generatedJavascript);
		
		Assert.assertEquals(generatedJavascript, expectedJavascript);
	}
	
	/**
	 * Test {@link JsUtils#escapeDoubleQuote(CharSequence)}
	 */
	@Test
	public void testEscapeDoubleQuote() {
		String expectedJavascript = "\\\"Hello\\\"";
		String generatedJavascript = JsUtils.escapeDoubleQuote("\"Hello\"");
		
		log.info(expectedJavascript);
		log.info(generatedJavascript);
		
		Assert.assertEquals(generatedJavascript, expectedJavascript);
	}
	
	/**
	 * Test {@link JsUtils#escapeQuote(CharSequence)}
	 */
	@Test
	public void testEscapeQuote() {
		String expectedJavascript = "l\\'oiseau";
		String generatedJavascript = JsUtils.escapeQuote("l'oiseau");
		
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
	 * Test {@link JsUtils#quotes(CharSequence)} and
	 * {@link JsUtils#quotes(CharSequence, boolean)}
	 */
	@Test
	public void testQuotes() {
		// Without escaped quote
		String expectedJavascript = "'a'";
		String generatedJavascript = JsUtils.quotes("a");
		
		log.info(expectedJavascript);
		log.info(generatedJavascript);
		
		Assert.assertEquals(generatedJavascript, expectedJavascript);
		
		// With escaped quote
		expectedJavascript = "'l\\'oiseau'";
		generatedJavascript = JsUtils.quotes("l'oiseau", true);
		
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

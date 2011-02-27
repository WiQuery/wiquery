package org.odlabs.wiquery.core.javascript;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.io.Serializable;

import junit.framework.Assert;

import org.junit.Test;
import org.odlabs.wiquery.core.events.MouseEvent;
import org.odlabs.wiquery.tester.WiQueryTestCase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Unit test on the {@link JsUtils}
 * 
 * @author Julien Roche
 */
public class JsUtilsTestCase extends WiQueryTestCase {

	protected static final Logger log = LoggerFactory
			.getLogger(JsUtilsTestCase.class);

	/**
	 * Test {@link JsUtils#array(CharSequence...)}
	 */
	@Test
	public void testArray() {
		String expectedJavascript = "['a', 'b', 'c']";
		String generatedJavascript = JsUtils.array("'a'", "'b'", "'c'")
				.toString();

		log.info(expectedJavascript);
		log.info(generatedJavascript);

		assertEquals(generatedJavascript, expectedJavascript);
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

		assertEquals(generatedJavascript, expectedJavascript);

		// With escaped quote
		expectedJavascript = "\"\\\"Hello\\\"\"";
		generatedJavascript = JsUtils.doubleQuotes("\"Hello\"", true);

		log.info(expectedJavascript);
		log.info(generatedJavascript);

		assertEquals(generatedJavascript, expectedJavascript);
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

		assertEquals(generatedJavascript, expectedJavascript);
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

		assertEquals(generatedJavascript, expectedJavascript);
	}

	/**
	 * Test
	 * {@link JsUtils#implode(org.odlabs.wiquery.core.events.EventLabel...)}
	 */
	@Test
	public void testImplode() {
		String expectedJavascript = "'click dblclick'";
		String generatedJavascript = JsUtils.implode(MouseEvent.CLICK,
				MouseEvent.DBLCLICK);

		log.info(expectedJavascript);
		log.info(generatedJavascript);

		assertEquals(generatedJavascript, expectedJavascript);
	}
	
	/**
	 * Test
	 * {@link JsUtils#json(java.io.Serializable)}
	 */
	@Test
	public void testJson() {
		try {
			String expectedJavascript = "{\"anId\":\"aLiteralValue\",\"aValue\":42,\"bools\":[true,false,true]}";
			String generatedJavascript = JsUtils.json(new JSonObject("aLiteralValue", 42, true, false, true));

			log.info(expectedJavascript);
			log.info(generatedJavascript);
			
			assertEquals(generatedJavascript, expectedJavascript);
			
		} catch (IOException e) {
			Assert.fail(e.getMessage());
		}
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

		assertEquals(generatedJavascript, expectedJavascript);

		// With escaped quote
		expectedJavascript = "'l\\'oiseau'";
		generatedJavascript = JsUtils.quotes("l'oiseau", true);

		log.info(expectedJavascript);
		log.info(generatedJavascript);

		assertEquals(generatedJavascript, expectedJavascript);
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

		assertEquals(generatedJavascript, expectedJavascript);
	}
	
	protected class JSonObject implements Serializable {
		private static final long serialVersionUID = 1L;
		
		private String anId;
		private int aValue;
		private boolean[] bools;
		
		public JSonObject(String anId, int aValue, boolean... bools) {
			this.anId = anId;
			this.aValue = aValue;
			this.bools = bools;
		}
		
		public String getAnId() {
			return anId;
		}

		public void setAnId(String anId) {
			this.anId = anId;
		}

		public int getaValue() {
			return aValue;
		}

		public void setaValue(int aValue) {
			this.aValue = aValue;
		}

		public boolean[] getBools() {
			return bools;
		}

		public void setBools(boolean[] bools) {
			this.bools = bools;
		}
	}
}

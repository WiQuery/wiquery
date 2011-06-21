package org.odlabs.wiquery.ui.datepicker;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.odlabs.wiquery.tester.WiQueryTestCase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AbstractArrayOfDateNamesTestCase extends WiQueryTestCase {
	protected static final Logger log = LoggerFactory
			.getLogger(AbstractArrayOfDateNamesTestCase.class);

	@Test
	public void testGetJavaScriptOption() {
		DefaultArrayOfDateNames arrays = new DefaultArrayOfDateNames(
				"Dimanche", "Lundi", "Mardi");

		// Names param
		String expectedJavascript = "['Dimanche','Lundi','Mardi']";
		String generatedJavascript = arrays.getJavascriptOption().toString();

		log.info(expectedJavascript);
		log.info(generatedJavascript);
		assertEquals(generatedJavascript, expectedJavascript);

		// Empty exception
		try {
			arrays = new DefaultArrayOfDateNames();
			arrays.getJavascriptOption().toString();
			assertTrue(false);
		} catch (Exception e) {
			assertEquals(e.getMessage(), "We must have a list of names");
		}

		// Not the desired size exception
		try {
			arrays = new DefaultArrayOfDateNames("1", "2");
			arrays.getJavascriptOption().toString();
			assertTrue(false);
		} catch (Exception e) {
			assertEquals(e.getMessage(), "The list must have 3 names");
		}
	}

	private class DefaultArrayOfDateNames extends AbstractArrayOfDateNames {
		// Constants
		/** Constant of serialization */
		private static final long serialVersionUID = -9097637272858071731L;

		public DefaultArrayOfDateNames(String... names) {
			super(names);
		}

		@Override
		public Integer getNumberOfName() {
			// TODO Auto-generated method stub
			return 3;
		}
	}

	@Override
	protected Logger getLog() {
		return log;
	}
}

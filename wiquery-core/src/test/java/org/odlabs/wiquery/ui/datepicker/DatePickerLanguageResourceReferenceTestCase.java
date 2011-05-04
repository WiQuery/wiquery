package org.odlabs.wiquery.ui.datepicker;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.Locale;

import org.junit.Test;
import org.odlabs.wiquery.tester.WiQueryTestCase;
import org.odlabs.wiquery.ui.datepicker.DatePickerLanguageResourceReference.DatePickerLanguages;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Unit test on the {@link DatePickerLanguageResourceReference}
 * 
 * @author Julien Roche
 */
public class DatePickerLanguageResourceReferenceTestCase extends
		WiQueryTestCase {

	protected static final Logger log = LoggerFactory
			.getLogger(DatePickerLanguageResourceReferenceTestCase.class);

	/**
	 * Test
	 * {@link DatePickerLanguageResourceReference.DatePickerLanguages#getDatePickerLanguages(java.util.Locale)}
	 */
	@Test
	public void testGetDatePickerLanguages() {
		assertNull(DatePickerLanguages.getDatePickerLanguages(new Locale("gj")));

		assertEquals(DatePickerLanguages.getDatePickerLanguages(new Locale(
				"sr", "SR")), DatePickerLanguages.SERBIA);

		assertEquals(DatePickerLanguages
				.getDatePickerLanguages(new Locale("sr")),
				DatePickerLanguages.SERBIAN);
	}

	/**
	 * Test
	 * {@link DatePickerLanguageResourceReference.DatePickerLanguages#getJsFileName(DatePickerLanguages)
	 * )}
	 */
	@Test
	public void testGetJsFileName() {
		assertNull(DatePickerLanguages.getJsFileName(null));

		assertEquals(DatePickerLanguages.getJsFileName(
				DatePickerLanguages.FRENCH).toString(),
				"i18n/jquery.ui.datepicker-fr.js");

		assertEquals(DatePickerLanguages.getJsFileName(
				DatePickerLanguages.SERBIA).toString(),
				"i18n/jquery.ui.datepicker-sr-SR.js");
	}
}

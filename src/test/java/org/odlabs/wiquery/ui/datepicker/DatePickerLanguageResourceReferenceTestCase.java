package org.odlabs.wiquery.ui.datepicker;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
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
 * @author Hielke Hoeve
 */
public class DatePickerLanguageResourceReferenceTestCase extends
		WiQueryTestCase {

	protected static final Logger log = LoggerFactory
			.getLogger(DatePickerLanguageResourceReferenceTestCase.class);

	@Test
	public void testGetDatePickerLanguages() {
		Locale nonavailableLocale = new Locale("wiquery");
		Locale availableLocale = DatePickerLanguages.ARMENIAN.getLocale();
		
		assertNull(DatePickerLanguages.getDatePickerLanguages(nonavailableLocale));
		assertNull(DatePickerLanguageResourceReference.get(nonavailableLocale));

		assertNotNull(DatePickerLanguages.getDatePickerLanguages(availableLocale));
		assertNotNull(DatePickerLanguageResourceReference.get(availableLocale));
		
		for(DatePickerLanguages language : DatePickerLanguages.values())
		{
			assertEquals(language, DatePickerLanguages.getDatePickerLanguages(language.getLocale()));
			assertNotNull(DatePickerLanguageResourceReference.get(availableLocale));
		}
	}

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

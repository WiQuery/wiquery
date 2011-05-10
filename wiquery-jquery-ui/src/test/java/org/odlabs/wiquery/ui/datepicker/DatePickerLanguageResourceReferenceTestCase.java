package org.odlabs.wiquery.ui.datepicker;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.Locale;

import org.apache.wicket.Application;
import org.apache.wicket.util.lang.Packages;
import org.apache.wicket.util.resource.locator.IResourceStreamLocator;
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
			//assert if the language getter is implemented correctly to return the exact variant. 
			assertEquals(language, DatePickerLanguages.getDatePickerLanguages(language.getLocale()));
			
			//assert if the reference getter is implemented correctly to return a reference to the exact variant. 
			DatePickerLanguageResourceReference ref = DatePickerLanguageResourceReference.get(language.getLocale());
			assertNotNull(ref);
			
			//assert if the file is actually there.
			IResourceStreamLocator locator = Application.get().getResourceSettings().getResourceStreamLocator();
			String absolutePath = Packages.absolutePath(DatePickerLanguageResourceReference.class, DatePickerLanguages.getJsFileName(language));
			assertNotNull("Resource "+DatePickerLanguages.getJsFileName(language)+" for locale "+language.getLocale()+" does not exist!", locator.locate(DatePickerLanguageResourceReference.class, absolutePath));
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

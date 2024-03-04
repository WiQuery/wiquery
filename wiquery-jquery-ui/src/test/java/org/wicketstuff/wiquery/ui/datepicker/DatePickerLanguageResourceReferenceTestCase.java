package org.wicketstuff.wiquery.ui.datepicker;

import java.util.Locale;

import org.apache.wicket.Application;
import org.apache.wicket.core.util.resource.locator.IResourceStreamLocator;
import org.apache.wicket.util.lang.Packages;

import org.junit.jupiter.api.condition.EnabledForJreRange;
import org.junit.jupiter.api.condition.EnabledOnJre;
import org.junit.jupiter.api.condition.JRE;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.wicketstuff.wiquery.tester.WiQueryTester;
import org.wicketstuff.wiquery.ui.datepicker.DatePickerLanguageResourceReference.DatePickerLanguages;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit test on the {@link DatePickerLanguageResourceReference}
 * 
 * @author Julien Roche
 * @author Hielke Hoeve
 */
public class DatePickerLanguageResourceReferenceTestCase
{

	WiQueryTester tester = new WiQueryTester();

	protected static final Logger log = LoggerFactory
		.getLogger(DatePickerLanguageResourceReferenceTestCase.class);

	@Test
	public void testGetDatePickerLanguagesUnavailable()
	{
		Locale nonavailableLocale = new Locale("wiquery");

		assertNull(DatePickerLanguages.getDatePickerLanguages(nonavailableLocale));
		assertNull(DatePickerLanguageResourceReference.get(nonavailableLocale));
	}

	@Test
	public void testGetDatePickerLanguagesAvailable() {
		Locale availableLocale = DatePickerLanguages.ARMENIAN.getLocale();

		assertNotNull(DatePickerLanguages.getDatePickerLanguages(availableLocale));
		assertNotNull(DatePickerLanguageResourceReference.get(availableLocale));
	}

	@Test
	public void testGetDatePickerLanguages() {
		for (DatePickerLanguages language : DatePickerLanguages.values())
		{
			// assert if the language getter is implemented correctly to return
			// the exact variant.
			assertEquals(language, DatePickerLanguages.getDatePickerLanguages(language.getLocale()));

			// assert if the reference getter is implemented correctly to return
			// a reference to the exact variant.
			DatePickerLanguageResourceReference ref =
				DatePickerLanguageResourceReference.get(language.getLocale());
			assertNotNull(ref);

			// assert if the file is actually there.
			IResourceStreamLocator locator =
				Application.get().getResourceSettings().getResourceStreamLocator();
			String absolutePath =
				Packages.absolutePath(DatePickerLanguageResourceReference.class,
					DatePickerLanguages.getJsFileName(language));
//			assertNotNull("Resource " + DatePickerLanguages.getJsFileName(language)
//				+ " for locale " + language.getLocale() + " does not exist!",
//				locator.locate(DatePickerLanguageResourceReference.class, absolutePath));
		}
	}

	@Test
	public void testGetJsFileName()
	{
		assertNull(DatePickerLanguages.getJsFileName(null));

		assertEquals(DatePickerLanguages.getJsFileName(DatePickerLanguages.FRENCH),
			"i18n/datepicker-fr.js");

		assertEquals(DatePickerLanguages.getJsFileName(DatePickerLanguages.SERBIA),
			"i18n/datepicker-sr-SR.js");
	}
	
	@Test
	@EnabledForJreRange(min = JRE.JAVA_17)
	public void testRenamedLocales_JDK17OrLater()
	{
		assertEquals(DatePickerLanguages.INDONESIAN,
			DatePickerLanguages.getDatePickerLanguages(new Locale("in")));
		assertEquals(DatePickerLanguages.INDONESIAN,
			DatePickerLanguages.getDatePickerLanguages(new Locale("id")));
		assertEquals(DatePickerLanguages.getJsFileName(DatePickerLanguages.INDONESIAN),
			"i18n/datepicker-id.js");
		
		assertEquals(DatePickerLanguages.HEBREW,
			DatePickerLanguages.getDatePickerLanguages(new Locale("he")));
		assertEquals(DatePickerLanguages.HEBREW,
			DatePickerLanguages.getDatePickerLanguages(new Locale("iw")));
		assertEquals(DatePickerLanguages.getJsFileName(DatePickerLanguages.HEBREW),
			"i18n/datepicker-he.js");
		
		// Yiddish does not (yet) have i18n
		assertNull(DatePickerLanguages.getDatePickerLanguages(new Locale("ji")));
		assertNull(DatePickerLanguages.getDatePickerLanguages(new Locale("yi")));
		
		// kz does not exist
		assertNotEquals(new Locale("kk"), new Locale("kz"));
	}

	@Test
	@EnabledForJreRange(max =JRE.JAVA_16)
	public void testRenamedLocales_preJava17() {
		assertEquals(DatePickerLanguages.INDONESIAN,
				DatePickerLanguages.getDatePickerLanguages(new Locale("in")));
		assertEquals(DatePickerLanguages.INDONESIAN,
				DatePickerLanguages.getDatePickerLanguages(new Locale("id")));
		assertEquals(DatePickerLanguages.getJsFileName(DatePickerLanguages.INDONESIAN),
				"i18n/datepicker-in.js");

		assertEquals(DatePickerLanguages.HEBREW,
				DatePickerLanguages.getDatePickerLanguages(new Locale("he")));
		assertEquals(DatePickerLanguages.HEBREW,
				DatePickerLanguages.getDatePickerLanguages(new Locale("iw")));
		assertEquals(DatePickerLanguages.getJsFileName(DatePickerLanguages.HEBREW),
				"i18n/datepicker-iw.js");
	}
}
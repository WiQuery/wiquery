package org.odlabs.wiquery.ui.datepicker;

import java.util.Locale;

import junit.framework.TestCase;

import org.odlabs.wiquery.ui.datepicker.DatePickerLanguageResourceReference.DatePickerLanguages;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Unit test on the {@link DatePickerLanguageResourceReference}
 * @author Julien Roche
 *
 */
public class DatePickerLanguageResourceReferenceTestCase extends TestCase {

	protected static final Logger log = LoggerFactory.getLogger(
			DatePickerLanguageResourceReferenceTestCase.class);
	
	/**
	 * Test {@link DatePickerLanguageResourceReference.DatePickerLanguages#getDatePickerLanguages(java.util.Locale)}
	 */
	@Test
	public void testGetDatePickerLanguages() {
		Assert.assertNull(DatePickerLanguages.getDatePickerLanguages(new Locale("gj")));
		
		Assert.assertEquals(DatePickerLanguages.getDatePickerLanguages(new Locale("sr", "SR")),
				DatePickerLanguages.SERBIA);
		
		Assert.assertEquals(DatePickerLanguages.getDatePickerLanguages(new Locale("sr")),
				DatePickerLanguages.SERBIAN);
	}
	
	/**
	 * Test {@link DatePickerLanguageResourceReference.DatePickerLanguages#getJsFileName(DatePickerLanguages))}
	 */
	@Test
	public void testGetJsFileName() {
		Assert.assertNull(DatePickerLanguages.getJsFileName(null));
		
		Assert.assertEquals(
				DatePickerLanguages.getJsFileName(DatePickerLanguages.FRENCH).toString(),
				"i18n/ui.datepicker-fr.min.js");
		
		Assert.assertEquals(
				DatePickerLanguages.getJsFileName(DatePickerLanguages.SERBIA).toString(),
				"i18n/ui.datepicker-sr-SR.min.js");
	}
	
	/**
	 * Test {@link DatePickerLanguageResourceReference.DatePickerLanguages#isCanHaveLocaleFile(Locale))}
	 */
	@Test
	public void testIsCanHaveLocaleFile() {
		Assert.assertFalse(DatePickerLanguages.isCanHaveLocaleFile(null));
		Assert.assertFalse(DatePickerLanguages.isCanHaveLocaleFile(new Locale("gj")));
		Assert.assertFalse(DatePickerLanguages.isCanHaveLocaleFile(new Locale("en")));
		Assert.assertTrue(DatePickerLanguages.isCanHaveLocaleFile(Locale.FRENCH));
	}
}

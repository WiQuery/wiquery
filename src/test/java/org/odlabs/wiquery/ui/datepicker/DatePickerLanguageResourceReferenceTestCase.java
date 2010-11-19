package org.odlabs.wiquery.ui.datepicker;

import java.util.Locale;

import junit.framework.TestCase;

import org.odlabs.wiquery.ui.datepicker.DatePickerLanguageResourceReference.DatePickerLanguages;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.junit.Assert;
import org.junit.Test;

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
				"i18n/jquery.ui.datepicker-fr.js");
		
		Assert.assertEquals(
				DatePickerLanguages.getJsFileName(DatePickerLanguages.SERBIA).toString(),
				"i18n/jquery.ui.datepicker-sr-SR.js");
	}
}

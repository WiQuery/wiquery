package org.odlabs.wiquery.ui.datepicker.scope;

import junit.framework.TestCase;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.junit.Assert;
import org.junit.Test;

/**
 * Unit test on the {@link JsScopeUiDatePickerOnChangeEvent}
 * @author Julien Roche
 *
 */
public class DefaultJsScopeUiDatePickerOnChangeEventTestCase extends TestCase {

	protected static final Logger log = LoggerFactory.getLogger(
			DefaultJsScopeUiDatePickerOnChangeEventTestCase.class);

	/**
	 * Check the syntax
	 */
	@Test
	public void testJsScopeSyntax() {
		String expectedJavascript = "function(year, month, inst) {\n\talert('test');\n}";
		JsScopeUiDatePickerOnChangeEvent scopeUiEvent = new DefaultJsScopeUiDatePickerOnChangeEvent("alert('test');");
		String generatedJavascript = scopeUiEvent.render().toString();
		
		log.info(expectedJavascript);
		log.info(generatedJavascript);
		
		Assert.assertEquals(generatedJavascript, expectedJavascript);
		
		// Second generation
		generatedJavascript = scopeUiEvent.render().toString();
		log.info(expectedJavascript);
		log.info(generatedJavascript);
		
		Assert.assertEquals(generatedJavascript, expectedJavascript);
	}
	
}

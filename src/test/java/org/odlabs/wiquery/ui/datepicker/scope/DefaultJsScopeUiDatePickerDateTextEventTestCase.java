package org.odlabs.wiquery.ui.datepicker.scope;

import junit.framework.TestCase;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Unit test on the {@link DefaultJsScopeUiDatePickerDateTextEvent}
 * @author Julien Roche
 *
 */
public class DefaultJsScopeUiDatePickerDateTextEventTestCase extends TestCase {

	protected static final Logger log = LoggerFactory.getLogger(
			DefaultJsScopeUiDatePickerDateTextEventTestCase.class);

	/**
	 * Check the syntax
	 */
	@Test
	public void testJsScopeSyntax() {
		String expectedJavascript = "function(dateText, inst) {\n\talert('test');\n}";
		JsScopeUiDatePickerDateTextEvent scopeUiEvent = new DefaultJsScopeUiDatePickerDateTextEvent("alert('test');");
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

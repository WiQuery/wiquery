package org.odlabs.wiquery.ui.datepicker.scope;

import junit.framework.TestCase;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.junit.Assert;
import org.junit.Test;

/**
 * Unit test on the {@link JsScopeUiDatePickerEvent}
 * @author Julien Roche
 *
 */
public class DefaultJsScopeUiDatePickerEventTestCase extends TestCase {

	protected static final Logger log = LoggerFactory.getLogger(
			DefaultJsScopeUiDatePickerEventTestCase.class);

	/**
	 * Check the syntax
	 */
	@Test
	public void testJsScopeSyntax() {
		String expectedJavascript = "function(date) {\n\talert('test');\n}";
		JsScopeUiDatePickerEvent scopeUiEvent = new DefaultJsScopeUiDatePickerEvent("alert('test');");
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

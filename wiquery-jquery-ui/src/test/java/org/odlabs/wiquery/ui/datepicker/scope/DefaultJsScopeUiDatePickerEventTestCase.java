package org.odlabs.wiquery.ui.datepicker.scope;

import static org.junit.Assert.*;

import org.junit.Test;
import org.odlabs.wiquery.tester.WiQueryTestCase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Unit test on the {@link JsScopeUiDatePickerEvent}
 * 
 * @author Julien Roche
 */
public class DefaultJsScopeUiDatePickerEventTestCase extends WiQueryTestCase
{

	protected static final Logger log = LoggerFactory
		.getLogger(DefaultJsScopeUiDatePickerEventTestCase.class);

	/**
	 * Check the syntax
	 */
	@Test
	public void testJsScopeSyntax()
	{
		String expectedJavascript = "function(date) {\n\talert('test');\n}";
		JsScopeUiDatePickerEvent scopeUiEvent =
			new DefaultJsScopeUiDatePickerEvent("alert('test');");
		String generatedJavascript = scopeUiEvent.render().toString();

		log.info(expectedJavascript);
		log.info(generatedJavascript);

		assertEquals(generatedJavascript, expectedJavascript);

		// Second generation
		generatedJavascript = scopeUiEvent.render().toString();
		log.info(expectedJavascript);
		log.info(generatedJavascript);

		assertEquals(generatedJavascript, expectedJavascript);
	}

	@Override
	protected Logger getLog()
	{
		return log;
	}
}

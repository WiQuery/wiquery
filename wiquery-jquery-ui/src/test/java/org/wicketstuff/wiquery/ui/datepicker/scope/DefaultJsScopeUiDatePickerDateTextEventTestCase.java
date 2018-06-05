package org.wicketstuff.wiquery.ui.datepicker.scope;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.wicketstuff.wiquery.tester.WiQueryTestCase;

/**
 * Unit test on the {@link DefaultJsScopeUiDatePickerDateTextEvent}
 * 
 * @author Julien Roche
 */
public class DefaultJsScopeUiDatePickerDateTextEventTestCase extends WiQueryTestCase
{

	protected static final Logger log = LoggerFactory
		.getLogger(DefaultJsScopeUiDatePickerDateTextEventTestCase.class);

	/**
	 * Check the syntax
	 */
	@Test
	public void testJsScopeSyntax()
	{
		String expectedJavascript = "function(dateText, inst) {\n\talert('test');\n}";
		JsScopeUiDatePickerDateTextEvent scopeUiEvent = new DefaultJsScopeUiDatePickerDateTextEvent(
			"alert('test');");
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

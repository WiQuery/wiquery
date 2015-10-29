package org.wicketstuff.wiquery.ui.datepicker.scope;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.wicketstuff.wiquery.tester.WiQueryTestCase;
import org.wicketstuff.wiquery.ui.datepicker.scope.DefaultJsScopeUiDatePickerEvent;
import org.wicketstuff.wiquery.ui.datepicker.scope.JsScopeUiDatePickerEvent;

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

package org.wicketstuff.wiquery.ui.core;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.wicketstuff.wiquery.tester.WiQueryTestCase;
import org.wicketstuff.wiquery.ui.core.DefaultJsScopeUiEvent;
import org.wicketstuff.wiquery.ui.core.JsScopeUiEvent;

/**
 * Unit test on the {@link DefaultJsScopeUiEvent}
 * 
 * @author Julien Roche
 */
public class DefaultJsScopeUiEventTestCase extends WiQueryTestCase
{

	protected static final Logger log = LoggerFactory
		.getLogger(DefaultJsScopeUiEventTestCase.class);

	/**
	 * Check the syntax
	 */
	@Test
	public void testJsScopeSyntax()
	{
		String expectedJavascript = "function(event, ui) {\n\talert('test');\n}";
		JsScopeUiEvent scopeUiEvent = new DefaultJsScopeUiEvent("alert('test');");
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

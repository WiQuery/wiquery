package org.odlabs.wiquery.ui.core;

import junit.framework.TestCase;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.junit.Assert;
import org.junit.Test;

/**
 * Unit test on the {@link DefaultJsScopeUiEvent}
 * @author Julien Roche
 *
 */
public class DefaultJsScopeUiEventTestCase extends TestCase {

	protected static final Logger log = LoggerFactory.getLogger(
			DefaultJsScopeUiEventTestCase.class);

	/**
	 * Check the syntax
	 */
	@Test
	public void testJsScopeSyntax() {
		String expectedJavascript = "function(event, ui) {\n\talert('test');\n}";
		JsScopeUiEvent scopeUiEvent = new DefaultJsScopeUiEvent("alert('test');");
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

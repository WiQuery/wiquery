package org.odlabs.wiquery.ui.core;

import junit.framework.TestCase;

import org.odlabs.wiquery.ui.core.JsScopeUiEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Unit test on the {@link JsScopeUiEvent}
 * @author Julien Roche
 *
 */
public class JsScopeUiEventTestCase extends TestCase {

	protected static final Logger log = LoggerFactory.getLogger(
			JsScopeUiEventTestCase.class);

	/**
	 * Check the syntax
	 */
	@Test
	public void testJsScopeSyntax() {
		String expectedJavascript = "function(event, ui) {\n\talert('test');\n}";
		JsScopeUiEvent scopeUiEvent = new JsScopeUiEvent("alert('test');");
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

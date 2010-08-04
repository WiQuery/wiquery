package org.odlabs.wiquery.core.javascript;

import junit.framework.TestCase;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Unit test on the {@link JsScope}
 * @author Julien Roche
 *
 */
public class JsScopeEventTestCase extends TestCase {

	protected static final Logger log = LoggerFactory.getLogger(
			JsScopeEventTestCase.class);
	
	/**
	 * Check the quickScope function
	 */
	@Test
	public void testQuickScope() {
		String expectedJavascript = "function(event) {\n\talert('test');\n}";
		JsScopeEvent quickScope = JsScopeEvent.quickScope("alert('test');");
		String generatedJavascript = quickScope.render().toString();
		
		log.info(expectedJavascript);
		log.info(generatedJavascript);
		
		Assert.assertEquals(generatedJavascript, expectedJavascript);
		
		// Second generation
		generatedJavascript = quickScope.render().toString();
		
		log.info(expectedJavascript);
		log.info(generatedJavascript);
		
		Assert.assertEquals(generatedJavascript, expectedJavascript);
	}
	
	/**
	 * Check the quickScope function
	 */
	@Test
	public void testQuickScopeJsStatement() {
		String expectedJavascript = "function(event) {\n\talert('test');\n}";
		JsScopeEvent quickScope = JsScopeEvent.quickScope(new JsStatement().append("alert('test')"));
		String generatedJavascript = quickScope.render().toString();
		
		log.info(expectedJavascript);
		log.info(generatedJavascript);
		
		Assert.assertEquals(generatedJavascript, expectedJavascript);
		
		// Second generation
		generatedJavascript = quickScope.render().toString();
		
		log.info(expectedJavascript);
		log.info(generatedJavascript);
		
		Assert.assertEquals(generatedJavascript, expectedJavascript);
	}
}

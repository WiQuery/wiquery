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
public class JsScopeTestCase extends TestCase {

	protected static final Logger log = LoggerFactory.getLogger(
			JsScopeTestCase.class);
	
	/**
	 * Check the quickScope function
	 */
	@Test
	public void testQuickScope() {
		String expectedJavascript = "function() {\n\talert('test');\n}";
		JsScope quickScope = JsScope.quickScope("alert('test');");
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
		String expectedJavascript = "function() {\n\talert('test');\n}";
		JsScope quickScope = JsScope.quickScope(new JsStatement().append("alert('test')"));
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
	 * Check the syntax
	 */
	@Test
	public void testJsScopeUiEventSyntax() {
		String expectedJavascript = "function(param1, param2, param3) {\n\talert('test');\n}";
		JsScope  scope= new JsScope("param1", "param2", "param3") {
			private static final long serialVersionUID = 1L;

			/* (non-Javadoc)
			 * @see org.odlabs.wiquery.core.javascript.JsScope#execute(org.odlabs.wiquery.core.javascript.JsScopeContext)
			 */
			@Override
			protected void execute(JsScopeContext scopeContext) {
				scopeContext.append("alert('test');");
			}
			
		};
		String generatedJavascript = scope.render().toString();
		
		log.info(expectedJavascript);
		log.info(generatedJavascript);
		
		Assert.assertEquals(generatedJavascript, expectedJavascript);
		
		// Second generation
		generatedJavascript = scope.render().toString();
		
		log.info(expectedJavascript);
		log.info(generatedJavascript);
		
		Assert.assertEquals(generatedJavascript, expectedJavascript);
	}
	
}

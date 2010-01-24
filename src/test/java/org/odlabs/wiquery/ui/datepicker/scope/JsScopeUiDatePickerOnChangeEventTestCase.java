package org.odlabs.wiquery.ui.datepicker.scope;

import junit.framework.TestCase;

import org.odlabs.wiquery.core.javascript.JsScopeContext;
import org.odlabs.wiquery.core.javascript.JsStatement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Unit test on the {@link JsScopeUiDatePickerOnChangeEvent}
 * @author Julien Roche
 *
 */
public class JsScopeUiDatePickerOnChangeEventTestCase extends TestCase {

	protected static final Logger log = LoggerFactory.getLogger(
			JsScopeUiDatePickerOnChangeEventTestCase.class);

	/**
	 * Check the syntax
	 */
	@Test
	public void testJsScopeSyntax() {
		String expectedJavascript = "function(year, month, inst) {\n\talert('test');\n}";
		JsScopeUiDatePickerOnChangeEvent scopeUiEvent = new JsScopeUiDatePickerOnChangeEvent(){
			private static final long serialVersionUID = 1L;

			/* (non-Javadoc)
			 * @see org.odlabs.wiquery.core.javascript.JsScope#execute(org.odlabs.wiquery.core.javascript.JsScopeContext)
			 */
			@Override
			protected void execute(JsScopeContext scopeContext) {
				scopeContext.append("alert('test');");
			}
			
		};
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
	
	/**
	 * Check the quickScope function
	 */
	@Test
	public void testQuickScope() {
		String expectedJavascript = "function(year, month, inst) {\n\talert('test');\n}";
		JsScopeUiDatePickerOnChangeEvent quickScope = JsScopeUiDatePickerOnChangeEvent.quickScope("alert('test');");
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
		String expectedJavascript = "function(year, month, inst) {\n\talert('test');\n}";
		JsScopeUiDatePickerOnChangeEvent quickScope = JsScopeUiDatePickerOnChangeEvent.quickScope(new JsStatement().append("alert('test')"));
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

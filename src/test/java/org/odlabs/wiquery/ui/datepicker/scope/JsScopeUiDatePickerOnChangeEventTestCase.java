package org.odlabs.wiquery.ui.datepicker.scope;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.odlabs.wiquery.core.javascript.JsScopeContext;
import org.odlabs.wiquery.core.javascript.JsStatement;
import org.odlabs.wiquery.tester.WiQueryTestCase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Unit test on the {@link JsScopeUiDatePickerOnChangeEvent}
 * 
 * @author Julien Roche
 */
public class JsScopeUiDatePickerOnChangeEventTestCase extends WiQueryTestCase {

	protected static final Logger log = LoggerFactory
			.getLogger(JsScopeUiDatePickerOnChangeEventTestCase.class);

	/**
	 * Check the syntax
	 */
	@Test
	public void testJsScopeSyntax() {
		String expectedJavascript = "function(year, month, inst) {\n\talert('test');\n}";
		JsScopeUiDatePickerOnChangeEvent scopeUiEvent = new JsScopeUiDatePickerOnChangeEvent() {
			private static final long serialVersionUID = 1L;

			/*
			 * (non-Javadoc)
			 * @see
			 * org.odlabs.wiquery.core.javascript.JsScope#execute(org.odlabs
			 * .wiquery.core.javascript.JsScopeContext)
			 */
			@Override
			protected void execute(JsScopeContext scopeContext) {
				scopeContext.append("alert('test');");
			}

		};
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

	/**
	 * Check the quickScope function
	 */
	@Test
	public void testQuickScope() {
		String expectedJavascript = "function(year, month, inst) {\n\talert('test');\n}";
		JsScopeUiDatePickerOnChangeEvent quickScope = JsScopeUiDatePickerOnChangeEvent
				.quickScope("alert('test');");
		String generatedJavascript = quickScope.render().toString();

		log.info(expectedJavascript);
		log.info(generatedJavascript);

		assertEquals(generatedJavascript, expectedJavascript);

		// Second generation
		generatedJavascript = quickScope.render().toString();

		log.info(expectedJavascript);
		log.info(generatedJavascript);

		assertEquals(generatedJavascript, expectedJavascript);
	}

	/**
	 * Check the quickScope function
	 */
	@Test
	public void testQuickScopeJsStatement() {
		String expectedJavascript = "function(year, month, inst) {\n\talert('test');\n}";
		JsScopeUiDatePickerOnChangeEvent quickScope = JsScopeUiDatePickerOnChangeEvent
				.quickScope(new JsStatement().append("alert('test')"));
		String generatedJavascript = quickScope.render().toString();

		log.info(expectedJavascript);
		log.info(generatedJavascript);

		assertEquals(generatedJavascript, expectedJavascript);

		// Second generation
		generatedJavascript = quickScope.render().toString();

		log.info(expectedJavascript);
		log.info(generatedJavascript);

		assertEquals(generatedJavascript, expectedJavascript);
	}
}

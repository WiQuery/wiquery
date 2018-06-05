package org.wicketstuff.wiquery.ui.position;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.wicketstuff.wiquery.core.javascript.JsScopeContext;
import org.wicketstuff.wiquery.core.javascript.JsStatement;
import org.wicketstuff.wiquery.tester.WiQueryTestCase;

/**
 * Unit test on the {@link JsScopePositionEvent}
 * 
 * @author Julien Roche
 */
public class JsScopePositionEventTestCase extends WiQueryTestCase
{

	protected static final Logger log = LoggerFactory.getLogger(JsScopePositionEventTestCase.class);

	/**
	 * Check the syntax
	 */
	@Test
	public void testJsScopeSyntax()
	{
		String expectedJavascript = "function(params) {\n\talert('test');\n}";
		JsScopePositionEvent scopeUiEvent = new JsScopePositionEvent()
		{
			private static final long serialVersionUID = 1L;

			@Override
			protected void execute(JsScopeContext scopeContext)
			{
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
	public void testQuickScope()
	{
		String expectedJavascript = "function(params) {\n\talert('test');\n}";
		JsScopePositionEvent quickScope = JsScopePositionEvent.quickScope("alert('test');");
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
	public void testQuickScopeJsStatement()
	{
		String expectedJavascript = "function(params) {\n\talert('test');\n}";
		JsScopePositionEvent quickScope = JsScopePositionEvent
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

	@Override
	protected Logger getLog()
	{
		return log;
	}
}

package org.wicketstuff.wiquery.core.javascript;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.wicketstuff.wiquery.tester.WiQueryTestCase;

/**
 * Unit test on the {@link JsScope}
 * 
 * @author Julien Roche
 */
public class JsScopeTestCase extends WiQueryTestCase
{

	protected static final Logger log = LoggerFactory.getLogger(JsScopeTestCase.class);

	/**
	 * Check the quickScope function
	 */
	@Test
	public void testQuickScope()
	{
		String expectedJavascript = "function() {\n\talert('test');\n}";
		JsScope quickScope = JsScope.quickScope("alert('test');");
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
		String expectedJavascript = "function() {\n\talert('test');\n}";
		JsScope quickScope = JsScope.quickScope(new JsStatement().append("alert('test')"));
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
	 * Check the syntax
	 */
	@Test
	public void testJsScopeUiEventSyntax()
	{
		String expectedJavascript = "function(param1, param2, param3) {\n\talert('test');\n}";
		JsScope scope = new JsScope("param1", "param2", "param3")
		{
			private static final long serialVersionUID = 1L;

			@Override
			protected void execute(JsScopeContext scopeContext)
			{
				scopeContext.append("alert('test');");
			}

		};
		String generatedJavascript = scope.render().toString();

		log.info(expectedJavascript);
		log.info(generatedJavascript);

		assertEquals(generatedJavascript, expectedJavascript);

		// Second generation
		generatedJavascript = scope.render().toString();

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

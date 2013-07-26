package org.odlabs.wiquery.ui.tabs;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.odlabs.wiquery.tester.WiQueryTestCase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TabsAnimateOptionTestCase extends WiQueryTestCase
{
	protected static final Logger log = LoggerFactory.getLogger(TabsAnimateOptionTestCase.class);

	@Test
	public void testGetJavaScriptOption()
	{
		TabsAnimateOption snap = new TabsAnimateOption(true);

		// Boolean param
		String expectedJavascript = "true";
		String generatedJavascript = snap.getJavascriptOption().toString();

		log.info(expectedJavascript);
		log.info(generatedJavascript);
		assertEquals(generatedJavascript, expectedJavascript);

		// Integer param
		snap.setIntegerParam(200);
		expectedJavascript = "200";
		generatedJavascript = snap.getJavascriptOption().toString();
		
		log.info(expectedJavascript);
		log.info(generatedJavascript);
		assertEquals(generatedJavascript, expectedJavascript);
		
		// Literal param
		snap.setLiteralParam("swing");
		expectedJavascript = "'swing'";
		generatedJavascript = snap.getJavascriptOption().toString();

		log.info(expectedJavascript);
		log.info(generatedJavascript);
		assertEquals(generatedJavascript, expectedJavascript);
		
		// Object param
		snap.setObjectParam(new TabsEffectOptionObject()
				.setEasing("linear")
				.setDelay(50)
				.setDuration(200)
				.setEffect("blind")
		);
		expectedJavascript = "{easing: 'linear', delay: 50, duration: 200, effect: 'blind'}";
		generatedJavascript = snap.getJavascriptOption().toString();

		log.info(expectedJavascript);
		log.info(generatedJavascript);
		assertEquals(generatedJavascript, expectedJavascript);

		// Null param
		snap.setLiteralParam(null);
		try
		{
			generatedJavascript = snap.getJavascriptOption().toString();
			assertTrue(false);
		}
		catch (Exception e)
		{
			// We have an expected error
			assertEquals("The GenericAnimateOption must have one not null parameter", e.getMessage());
		}
	}

	@Override
	protected Logger getLog()
	{
		return log;
	}
}

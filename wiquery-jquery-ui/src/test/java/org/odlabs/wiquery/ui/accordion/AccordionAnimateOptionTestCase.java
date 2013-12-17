package org.odlabs.wiquery.ui.accordion;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.odlabs.wiquery.tester.WiQueryTestCase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AccordionAnimateOptionTestCase extends WiQueryTestCase
{
	protected static final Logger log = LoggerFactory.getLogger(AccordionAnimateOptionTestCase.class);

	@Test
	public void testGetJavaScriptOption()
	{
		AccordionAnimateOption snap = new AccordionAnimateOption(true);

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
		snap.setObjectParam(new AccordionEffectOptionObject()
				.setEasing("linear")
				.setDown(new AccordionDownEffectOptionObject()
						.setDuration(200)
				)
		);
		expectedJavascript = "{easing: 'linear', down: {duration: 200}}";
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

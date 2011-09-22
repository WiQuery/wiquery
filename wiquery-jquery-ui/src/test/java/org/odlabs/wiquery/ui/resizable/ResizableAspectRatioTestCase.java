package org.odlabs.wiquery.ui.resizable;

import static org.junit.Assert.*;

import org.junit.Test;
import org.odlabs.wiquery.tester.WiQueryTestCase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ResizableAspectRatioTestCase extends WiQueryTestCase
{
	protected static final Logger log = LoggerFactory.getLogger(ResizableAspectRatioTestCase.class);

	@Test
	public void testGetJavaScriptOption()
	{
		ResizableAspectRatio aspectRatio = new ResizableAspectRatio(true);

		// Boolean param
		String expectedJavascript = "true";
		String generatedJavascript = aspectRatio.getJavascriptOption().toString();

		log.info(expectedJavascript);
		log.info(generatedJavascript);
		assertEquals(generatedJavascript, expectedJavascript);

		// Float param
		aspectRatio.setFloatParam(0.5F);
		expectedJavascript = "0.5";
		generatedJavascript = aspectRatio.getJavascriptOption().toString();

		log.info(expectedJavascript);
		log.info(generatedJavascript);
		assertEquals(generatedJavascript, expectedJavascript);

		// Null param
		aspectRatio.setFloatParam(null);
		try
		{
			generatedJavascript = aspectRatio.getJavascriptOption().toString();
			assertTrue(false);
		}
		catch (Exception e)
		{
			// We have an expected error
			assertEquals("The ResizableAspectRatio must have one not null parameter",
				e.getMessage());
		}
	}

	@Override
	protected Logger getLog()
	{
		return log;
	}
}

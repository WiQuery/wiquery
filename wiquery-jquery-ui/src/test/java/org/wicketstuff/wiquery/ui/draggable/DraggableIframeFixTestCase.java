package org.wicketstuff.wiquery.ui.draggable;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.wicketstuff.wiquery.tester.WiQueryTestCase;

public class DraggableIframeFixTestCase extends WiQueryTestCase
{
	protected static final Logger log = LoggerFactory.getLogger(DraggableIframeFixTestCase.class);

	@Test
	public void testGetJavaScriptOption()
	{
		DraggableIframeFix iframeFix = new DraggableIframeFix(true);

		// Boolean param
		String expectedJavascript = "true";
		String generatedJavascript = iframeFix.getJavascriptOption().toString();

		log.info(expectedJavascript);
		log.info(generatedJavascript);
		assertEquals(generatedJavascript, expectedJavascript);

		// Selector param
		iframeFix.setSelectorParam("ul");
		expectedJavascript = "'ul'";
		generatedJavascript = iframeFix.getJavascriptOption().toString();

		log.info(expectedJavascript);
		log.info(generatedJavascript);
		assertEquals(generatedJavascript, expectedJavascript);

		// Null param
		iframeFix.setSelectorParam(null);
		try
		{
			generatedJavascript = iframeFix.getJavascriptOption().toString();
			assertTrue(false);
		}
		catch (Exception e)
		{
			// We have an expected error
			assertEquals("The DraggableIframeFix must have one not null parameter", e.getMessage());
		}
	}

	@Override
	protected Logger getLog()
	{
		return log;
	}
}

package org.odlabs.wiquery.ui.resizable;

import static org.junit.Assert.*;

import org.junit.Test;
import org.odlabs.wiquery.core.options.LiteralOption;
import org.odlabs.wiquery.tester.WiQueryTestCase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ResizableHandlesTestCase extends WiQueryTestCase
{
	protected static final Logger log = LoggerFactory.getLogger(ResizableHandlesTestCase.class);

	@Test
	public void testGetJavaScriptOption()
	{
		ResizableHandles handles = new ResizableHandles("jQuery('#test')");

		// Object param
		String expectedJavascript = "jQuery('#test')";
		String generatedJavascript = handles.getJavascriptOption().toString();

		log.info(expectedJavascript);
		log.info(generatedJavascript);
		assertEquals(generatedJavascript, expectedJavascript);

		// Literal param
		handles.setLiteralParam(new LiteralOption("e, s, se"));
		expectedJavascript = "'e, s, se'";
		generatedJavascript = handles.getJavascriptOption().toString();

		log.info(expectedJavascript);
		log.info(generatedJavascript);
		assertEquals(generatedJavascript, expectedJavascript);

		// Null param
		handles.setObjectParam(null);
		try
		{
			generatedJavascript = handles.getJavascriptOption().toString();
			assertTrue(false);
		}
		catch (Exception e)
		{
			// We have an expected error
			assertEquals("The ResizableHandles must have one not null parameter", e.getMessage());
		}
	}

	@Override
	protected Logger getLog()
	{
		return log;
	}
}

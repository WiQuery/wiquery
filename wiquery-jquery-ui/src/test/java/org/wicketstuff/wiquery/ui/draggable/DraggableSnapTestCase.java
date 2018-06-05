package org.wicketstuff.wiquery.ui.draggable;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.wicketstuff.wiquery.tester.WiQueryTestCase;

public class DraggableSnapTestCase extends WiQueryTestCase
{
	protected static final Logger log = LoggerFactory.getLogger(DraggableSnapTestCase.class);

	@Test
	public void testGetJavaScriptOption()
	{
		DraggableSnap snap = new DraggableSnap(true);

		// Boolean param
		String expectedJavascript = "true";
		String generatedJavascript = snap.getJavascriptOption().toString();

		log.info(expectedJavascript);
		log.info(generatedJavascript);
		assertEquals(generatedJavascript, expectedJavascript);

		// Selector param
		snap.setSelectorParam("ul");
		expectedJavascript = "'ul'";
		generatedJavascript = snap.getJavascriptOption().toString();

		log.info(expectedJavascript);
		log.info(generatedJavascript);
		assertEquals(generatedJavascript, expectedJavascript);

		// Null param
		snap.setSelectorParam(null);
		try
		{
			generatedJavascript = snap.getJavascriptOption().toString();
			assertTrue(false);
		}
		catch (Exception e)
		{
			// We have an expected error
			assertEquals("The DraggableSnap must have one not null parameter", e.getMessage());
		}
	}

	@Override
	protected Logger getLog()
	{
		return log;
	}
}

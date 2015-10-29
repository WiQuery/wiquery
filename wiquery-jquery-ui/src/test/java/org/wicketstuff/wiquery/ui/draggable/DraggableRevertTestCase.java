package org.wicketstuff.wiquery.ui.draggable;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.wicketstuff.wiquery.tester.WiQueryTestCase;
import org.wicketstuff.wiquery.ui.draggable.DraggableRevert;
import org.wicketstuff.wiquery.ui.draggable.DraggableRevert.RevertEnum;

public class DraggableRevertTestCase extends WiQueryTestCase
{
	protected static final Logger log = LoggerFactory.getLogger(DraggableRevertTestCase.class);

	@Test
	public void testGetJavaScriptOption()
	{
		DraggableRevert revert = new DraggableRevert(true);

		// Boolean param
		String expectedJavascript = "true";
		String generatedJavascript = revert.getJavascriptOption().toString();

		log.info(expectedJavascript);
		log.info(generatedJavascript);
		assertEquals(generatedJavascript, expectedJavascript);

		// RevertEnum param
		revert.setRevertEnumParam(RevertEnum.VALID);
		expectedJavascript = RevertEnum.VALID.toString();
		generatedJavascript = revert.getJavascriptOption().toString();

		log.info(expectedJavascript);
		log.info(generatedJavascript);
		assertEquals(generatedJavascript, expectedJavascript);

		// Null param
		revert.setRevertEnumParam(null);
		try
		{
			generatedJavascript = revert.getJavascriptOption().toString();
			assertTrue(false);
		}
		catch (Exception e)
		{
			// We have an expected error
			assertEquals("The DraggableRevert must have one not null parameter", e.getMessage());
		}
	}

	@Override
	protected Logger getLog()
	{
		return log;
	}
}

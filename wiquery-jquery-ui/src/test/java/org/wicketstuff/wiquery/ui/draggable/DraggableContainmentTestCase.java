package org.wicketstuff.wiquery.ui.draggable;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.wicketstuff.wiquery.core.options.LiteralOption;
import org.wicketstuff.wiquery.tester.WiQueryTestCase;
import org.wicketstuff.wiquery.ui.draggable.DraggableContainment;
import org.wicketstuff.wiquery.ui.draggable.DraggableContainment.ContainmentEnum;

public class DraggableContainmentTestCase extends WiQueryTestCase
{
	protected static final Logger log = LoggerFactory.getLogger(DraggableContainmentTestCase.class);

	@Test
	public void testGetJavaScriptOption()
	{
		DraggableContainment containment = new DraggableContainment(ContainmentEnum.WINDOW);

		// ContainmentEnum param
		String expectedJavascript = ContainmentEnum.WINDOW.toString();
		String generatedJavascript = containment.getJavascriptOption().toString();

		log.info(expectedJavascript);
		log.info(generatedJavascript);
		assertEquals(generatedJavascript, expectedJavascript);

		// Selector param
		containment.setSelector(new LiteralOption("#anId"));
		expectedJavascript = "'#anId'";
		generatedJavascript = containment.getJavascriptOption().toString();

		// Literal param
		containment.setStringParam("'#anId'");
		expectedJavascript = "'#anId'";
		generatedJavascript = containment.getJavascriptOption().toString();

		log.info(expectedJavascript);
		log.info(generatedJavascript);
		assertEquals(generatedJavascript, expectedJavascript);

		// Array param
		containment.setArrayParam(1, 2, 3, 4);
		expectedJavascript = "[1,2,3,4]";
		generatedJavascript = containment.getJavascriptOption().toString();

		log.info(expectedJavascript);
		log.info(generatedJavascript);
		assertEquals(generatedJavascript, expectedJavascript);

		// Null param
		containment.setContainmentEnumParam(null);
		try
		{
			generatedJavascript = containment.getJavascriptOption().toString();
			assertTrue(false);
		}
		catch (Exception e)
		{
			// We have an expected error
			assertEquals("The DraggableContainment must have one not null parameter",
				e.getMessage());
		}
	}

	@Override
	protected Logger getLog()
	{
		return log;
	}
}

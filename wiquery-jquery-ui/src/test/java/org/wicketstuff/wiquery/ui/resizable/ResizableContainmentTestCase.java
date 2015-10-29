package org.wicketstuff.wiquery.ui.resizable;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.wicketstuff.wiquery.core.options.LiteralOption;
import org.wicketstuff.wiquery.tester.WiQueryTestCase;
import org.wicketstuff.wiquery.ui.resizable.ResizableContainment;

public class ResizableContainmentTestCase extends WiQueryTestCase
{
	protected static final Logger log = LoggerFactory.getLogger(ResizableContainmentTestCase.class);

	@Test
	public void testGetJavaScriptOption()
	{
		ResizableContainment containment = new ResizableContainment("jQuery('#test')");

		// Object param
		String expectedJavascript = "jQuery('#test')";
		String generatedJavascript = containment.getJavascriptOption().toString();

		log.info(expectedJavascript);
		log.info(generatedJavascript);
		assertEquals(generatedJavascript, expectedJavascript);

		// Selector param
		containment.setSelector(new LiteralOption("#test"));
		expectedJavascript = "'#test'";
		generatedJavascript = containment.getJavascriptOption().toString();

		log.info(expectedJavascript);
		log.info(generatedJavascript);
		assertEquals(generatedJavascript, expectedJavascript);

		// Enum param
		containment.setElementEnumParam(ResizableContainment.ElementEnum.PARENT);
		expectedJavascript = ResizableContainment.ElementEnum.PARENT.toString();
		generatedJavascript = containment.getJavascriptOption().toString();

		log.info(expectedJavascript);
		log.info(generatedJavascript);
		assertEquals(generatedJavascript, expectedJavascript);

		// Null param
		containment.setElementOrSelectorParam(null);
		try
		{
			generatedJavascript = containment.getJavascriptOption().toString();
			assertTrue(false);
		}
		catch (Exception e)
		{
			// We have an expected error
			assertEquals("The ResizableContainment must have one not null parameter",
				e.getMessage());
		}
	}

	@Override
	protected Logger getLog()
	{
		return log;
	}
}

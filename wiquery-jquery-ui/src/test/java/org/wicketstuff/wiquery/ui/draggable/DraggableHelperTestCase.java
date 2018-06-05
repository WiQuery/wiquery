package org.wicketstuff.wiquery.ui.draggable;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.wicketstuff.wiquery.core.javascript.JsScope;
import org.wicketstuff.wiquery.tester.WiQueryTestCase;
import org.wicketstuff.wiquery.ui.draggable.DraggableHelper.HelperEnum;

public class DraggableHelperTestCase extends WiQueryTestCase
{
	protected static final Logger log = LoggerFactory.getLogger(DraggableHelperTestCase.class);

	@Test
	public void testGetJavaScriptOption()
	{
		DraggableHelper helper = new DraggableHelper(HelperEnum.CLONE);

		// HelperEnum param
		String expectedJavascript = HelperEnum.CLONE.toString();
		String generatedJavascript = helper.getJavascriptOption().toString();

		log.info(expectedJavascript);
		log.info(generatedJavascript);
		assertEquals(generatedJavascript, expectedJavascript);

		// Function param
		helper.setFunctionParam(JsScope.quickScope("return document.getElementById('anId');"));
		expectedJavascript = "function() {\n\treturn document.getElementById('anId');\n}";
		generatedJavascript = helper.getJavascriptOption().toString();

		log.info(expectedJavascript);
		log.info(generatedJavascript);
		assertEquals(generatedJavascript, expectedJavascript);

		// Null param
		helper.setHelperEnumParam(null);
		try
		{
			generatedJavascript = helper.getJavascriptOption().toString();
			assertTrue(false);
		}
		catch (Exception e)
		{
			// We have an expected error
			assertEquals("The DraggableHelper must have one not null parameter", e.getMessage());
		}
	}

	@Override
	protected Logger getLog()
	{
		return log;
	}
}

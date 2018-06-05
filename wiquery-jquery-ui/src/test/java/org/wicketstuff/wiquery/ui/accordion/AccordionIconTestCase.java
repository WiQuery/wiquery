package org.wicketstuff.wiquery.ui.accordion;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.wicketstuff.wiquery.tester.WiQueryTestCase;
import org.wicketstuff.wiquery.ui.themes.UiIcon;

public class AccordionIconTestCase extends WiQueryTestCase
{
	protected static final Logger log = LoggerFactory.getLogger(AccordionIconTestCase.class);

	@Test
	public void testGetJavaScriptOption()
	{
		AccordionIcon accordionIcon = new AccordionIcon("classA", "classB");

		// Int param
		String expectedJavascript = "{'header': 'classA', 'activeHeader': 'classB'}";
		String generatedJavascript = accordionIcon.getJavascriptOption().toString();

		log.info(expectedJavascript);
		log.info(generatedJavascript);
		assertEquals(generatedJavascript, expectedJavascript);

		accordionIcon = new AccordionIcon(false);

		expectedJavascript = "false";
		generatedJavascript = accordionIcon.getJavascriptOption().toString();

		log.info(expectedJavascript);
		log.info(generatedJavascript);
		assertEquals(generatedJavascript, expectedJavascript);

		accordionIcon = new AccordionIcon(UiIcon.ARROW_1_EAST, UiIcon.ARROW_1_NORTH);

		expectedJavascript = "{'header': '" + UiIcon.ARROW_1_EAST.getCssClass() +
			"', 'activeHeader': '" + UiIcon.ARROW_1_NORTH.getCssClass() + "'}";
		generatedJavascript = accordionIcon.getJavascriptOption().toString();

		log.info(expectedJavascript);
		log.info(generatedJavascript);
		assertEquals(generatedJavascript, expectedJavascript);
	}

	@Override
	protected Logger getLog()
	{
		return log;
	}
}

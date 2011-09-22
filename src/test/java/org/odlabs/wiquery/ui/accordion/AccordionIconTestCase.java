package org.odlabs.wiquery.ui.accordion;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.odlabs.wiquery.tester.WiQueryTestCase;
import org.odlabs.wiquery.ui.themes.UiIcon;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AccordionIconTestCase extends WiQueryTestCase {
	protected static final Logger log = LoggerFactory
			.getLogger(AccordionIconTestCase.class);

	@Test
	public void testGetJavaScriptOption() {
		AccordionIcon accordionIcon = new AccordionIcon("classA", "classB");

		// Int param
		String expectedJavascript = "{'header': 'classA', 'headerSelected': 'classB'}";
		String generatedJavascript = accordionIcon.getJavascriptOption()
				.toString();

		log.info(expectedJavascript);
		log.info(generatedJavascript);
		assertEquals(generatedJavascript, expectedJavascript);

		accordionIcon = new AccordionIcon(false);

		expectedJavascript = "false";
		generatedJavascript = accordionIcon.getJavascriptOption().toString();

		log.info(expectedJavascript);
		log.info(generatedJavascript);
		assertEquals(generatedJavascript, expectedJavascript);

		accordionIcon = new AccordionIcon(UiIcon.ARROW_1_EAST,
				UiIcon.ARROW_1_NORTH);

		expectedJavascript = "{'header': '" + UiIcon.ARROW_1_EAST.getCssClass()
				+ "', 'headerSelected': '" + UiIcon.ARROW_1_NORTH.getCssClass()
				+ "'}";
		generatedJavascript = accordionIcon.getJavascriptOption().toString();

		log.info(expectedJavascript);
		log.info(generatedJavascript);
		assertEquals(generatedJavascript, expectedJavascript);
	}
}

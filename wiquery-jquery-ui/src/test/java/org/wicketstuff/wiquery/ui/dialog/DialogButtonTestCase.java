package org.wicketstuff.wiquery.ui.dialog;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.wicketstuff.wiquery.core.javascript.JsScope;
import org.wicketstuff.wiquery.tester.WiQueryTestCase;
import org.wicketstuff.wiquery.ui.themes.UiIcon;

public class DialogButtonTestCase extends WiQueryTestCase
{
	protected static final Logger log = LoggerFactory.getLogger(DialogButtonTestCase.class);

	@Test
	public void testGetJavaScriptOption()
	{
		DialogButton button = new DialogButton("Ok", JsScope.quickScope("alert('test');"));
		String expectedJavascript = "{text: 'Ok', click: function() {\n\talert('test');\n}}";
		String generatedJavascript = button.getJavascriptOption().toString();

		log.info(expectedJavascript);
		log.info(generatedJavascript);
		assertEquals(generatedJavascript, expectedJavascript);

		button.setIcon(UiIcon.ARROW_1_EAST);
		button.setIconPosition("bottom");
		button.setShowLabel(false);
		expectedJavascript = "{label: 'Ok', click: function() {\n\talert('test');\n}, icon: " +
			"'ui-icon-arrow-1-e', showLabel: false}";
		generatedJavascript = button.getJavascriptOption().toString();

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

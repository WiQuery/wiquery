package org.odlabs.wiquery.ui.dialog;

import static org.junit.Assert.*;

import org.junit.Test;
import org.odlabs.wiquery.core.javascript.JsScope;
import org.odlabs.wiquery.tester.WiQueryTestCase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DialogButtonTestCase extends WiQueryTestCase
{
	protected static final Logger log = LoggerFactory.getLogger(DialogButtonTestCase.class);

	@Test
	public void testGetJavaScriptOption()
	{
		DialogButton button = new DialogButton("Ok", JsScope.quickScope("alert('test');"));
		String expectedJavascript = "'Ok':function() {\n\talert('test');\n}";
		String generatedJavascript = button.getJavascriptOption().toString();

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

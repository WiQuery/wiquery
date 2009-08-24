package org.odlabs.wiquery.ui.dialog;

import junit.framework.TestCase;

import org.odlabs.wiquery.core.javascript.JsScope;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

public class DialogButtonTestCase extends TestCase {
	protected static final Logger log = LoggerFactory.getLogger(
			DialogButtonTestCase.class);

	@Test
	public void testGetJavaScriptOption() {
		DialogButton button = new DialogButton("Ok", JsScope.quickScope("alert('test');"));
		String expectedJavascript = "'Ok':function() {\n\talert('test');\n}";
		String generatedJavascript = button.getJavascriptOption().toString();
		
		log.info(expectedJavascript);
		log.info(generatedJavascript);		
		Assert.assertEquals(generatedJavascript, expectedJavascript);
	}
}

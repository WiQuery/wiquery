package org.odlabs.wiquery.core.javascript.helper;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.odlabs.wiquery.core.javascript.JsStatement;
import org.odlabs.wiquery.core.options.Options;
import org.odlabs.wiquery.tester.WiQueryTestCase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Unit test on the {@link CssHelper}
 * 
 * @author Julien Roche
 */
public class CssHelperTestCase extends WiQueryTestCase {

	protected static final Logger log = LoggerFactory
			.getLogger(CssHelperTestCase.class);

	/**
	 * Test {@link CssHelper#css(String, String)}
	 */
	@Test
	public void testCss() {
		String expectedJavascript = "$('div').css('font-weight', 'bold');";
		String generatedJavascript = new JsStatement().$(null, "div")
				.chain(CssHelper.css("font-weight", "bold")).render()
				.toString();

		log.info(expectedJavascript);
		log.info(generatedJavascript);

		assertEquals(generatedJavascript, expectedJavascript);
	}

	/**
	 * Test {@link CssHelper#css(org.odlabs.wiquery.core.options.Options)}
	 */
	@Test
	public void testCssOptions() {
		String expectedJavascript = "$('div').css({font-weight: 'bold'});";

		Options options = new Options();
		options.putLiteral("font-weight", "bold");
		String generatedJavascript = new JsStatement().$(null, "div")
				.chain(CssHelper.css(options)).render().toString();

		log.info(expectedJavascript);
		log.info(generatedJavascript);

		assertEquals(generatedJavascript, expectedJavascript);
	}

	@Override
	protected Logger getLog() {
		return log;
	}
}

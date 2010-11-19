package org.odlabs.wiquery.core.javascript.helper;

import junit.framework.TestCase;

import org.odlabs.wiquery.core.javascript.JsStatement;
import org.odlabs.wiquery.core.options.Options;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.junit.Assert;
import org.junit.Test;

/**
 * Unit test on the {@link CssHelper}
 * @author Julien Roche
 *
 */
public class CssHelperTestCase extends TestCase {

	protected static final Logger log = LoggerFactory.getLogger(
			CssHelperTestCase.class);
	
	/**
	 * Test {@link CssHelper#css(String, String)}
	 */
	@Test
	public void testCss() {
		String expectedJavascript = "$('div').css('font-weight', 'bold');";
		String generatedJavascript = new JsStatement().$(null, "div").chain(
				CssHelper.css("font-weight", "bold")).render().toString();
		
		log.info(expectedJavascript);
		log.info(generatedJavascript);
		
		Assert.assertEquals(generatedJavascript, expectedJavascript);
	}
	
	/**
	 * Test {@link CssHelper#css(org.odlabs.wiquery.core.options.Options)}
	 */
	@Test
	public void testCssOptions() {
		String expectedJavascript = "$('div').css({font-weight: 'bold'});";
		
		Options options = new Options();
		options.putLiteral("font-weight", "bold");
		String generatedJavascript = new JsStatement().$(null, "div").chain(
				CssHelper.css(options)).render().toString();
		
		log.info(expectedJavascript);
		log.info(generatedJavascript);
		
		Assert.assertEquals(generatedJavascript, expectedJavascript);
	}
}

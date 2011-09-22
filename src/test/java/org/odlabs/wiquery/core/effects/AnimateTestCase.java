/*
 * Copyright (c) 2010 WiQuery team
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package org.odlabs.wiquery.core.effects;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;

import org.junit.Test;
import org.odlabs.wiquery.core.javascript.JsStatement;
import org.odlabs.wiquery.tester.WiQueryTestCase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Test on {@link AnimateDuration}
 * 
 * @author Julien Roche
 */
public class AnimateTestCase extends WiQueryTestCase {
	protected static final Logger log = LoggerFactory
			.getLogger(AnimateTestCase.class);

	/**
	 * Test method for
	 * {@link org.odlabs.wiquery.core.effects.Animate#statementArgs()}.
	 */
	@Test
	public void testStatementArgs() {
		HashMap<String, String> properties = new HashMap<String, String>();
		properties.put("width", "100%");
		
		// First case
		AnimateDuration duration = new AnimateDuration(500);
		String expectedJavascript = "$('#aComponent').animate({width: '100%'}, {duration: 500});";
		String generatedJavascript = new JsStatement().$(null, "#aComponent").chain(
				new Animate(properties, duration)).render().toString();
		
		log.info(expectedJavascript);
		log.info(generatedJavascript);
		assertEquals(generatedJavascript, expectedJavascript);
		
		// Second case
		expectedJavascript = "$('#aComponent').animate({width: '100%'}, {duration: 500, easing: 'linear'});";
		generatedJavascript = new JsStatement().$(null, "#aComponent").chain(
				new Animate(properties, duration, "linear")).render().toString();
		
		log.info(expectedJavascript);
		log.info(generatedJavascript);
		assertEquals(generatedJavascript, expectedJavascript);
	}
}

/*
 * Copyright (c) 2009 WiQuery team
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
package org.odlabs.wiquery.ui.autocomplete;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.odlabs.wiquery.core.javascript.JsScope;
import org.odlabs.wiquery.core.options.ArrayItemOptions;
import org.odlabs.wiquery.core.options.LiteralOption;
import org.odlabs.wiquery.tester.WiQueryTestCase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Test on {@link AutocompleteSource}
 * 
 * @author Julien Roche
 */
public class AutocompleteSourceTestCase extends WiQueryTestCase {
	protected static final Logger log = LoggerFactory
			.getLogger(AutocompleteSourceTestCase.class);

	/**
	 * Test the javascript generation
	 */
	@Test
	public void testGetJavaScriptOption() {
		AutocompleteSource source = new AutocompleteSource(
				"http://localhost:8080/url.jsp");

		// String param
		String expectedJavascript = "'http://localhost:8080/url.jsp'";
		String generatedJavascript = source.getJavascriptOption().toString();

		log.info(expectedJavascript);
		log.info(generatedJavascript);
		assertEquals(generatedJavascript, expectedJavascript);

		// Scope
		source.setJsScope(JsScope.quickScope("return ['a', 'b', 'c'];"));
		expectedJavascript = "function() {\n\treturn ['a', 'b', 'c'];\n}";
		generatedJavascript = source.getJavascriptOption().toString();

		log.info(expectedJavascript);
		log.info(generatedJavascript);
		assertEquals(generatedJavascript, expectedJavascript);

		// Array
		ArrayItemOptions<LiteralOption> array = new ArrayItemOptions<LiteralOption>();
		array.add(new LiteralOption("a"));
		array.add(new LiteralOption("b"));
		array.add(new LiteralOption("c"));

		source.setArray(array);
		expectedJavascript = "['a','b','c']";
		generatedJavascript = source.getJavascriptOption().toString();

		log.info(expectedJavascript);
		log.info(generatedJavascript);
		assertEquals(generatedJavascript, expectedJavascript);

		// Null param
		source.setString(null);

		try {
			generatedJavascript = source.getJavascriptOption().toString();
			assertTrue(false);

		} catch (Exception e) {
			// We have an expected error
			assertEquals(
					"The AutocompleteSource must have one not null parameter",
					e.getMessage());
		}
	}

	@Override
	protected Logger getLog() {
		return log;
	}
}

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
package org.odlabs.wiquery.ui.button;

import junit.framework.TestCase;

import org.odlabs.wiquery.ui.themes.UiIcon;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.junit.Assert;
import org.junit.Test;

/**
 * Test on {@link ButtonIcon}
 * @author Julien Roche
 *
 */
public class ButtonIconTestCase extends TestCase{
	protected static final Logger log = LoggerFactory.getLogger(
			ButtonIconTestCase.class);

	/**
	 * Test the javascript generation
	 */
	@Test
	public void testGetJavaScriptOption() {
		ButtonIcon buttonIcon = new ButtonIcon("ui-icon-gear", "ui-icon-triangle-1-s");
		
		String expectedJavascript = "{primary: 'ui-icon-gear', secondary: 'ui-icon-triangle-1-s'}";
		String generatedJavascript = buttonIcon.getJavascriptOption().toString();
		
		log.info(expectedJavascript);
		log.info(generatedJavascript);		
		Assert.assertEquals(generatedJavascript, expectedJavascript);
	}
	
	/**
	 * Test the javascript generation
	 */
	@Test
	public void testGetJavaScriptOptionEnum() {
		ButtonIcon buttonIcon = new ButtonIcon(UiIcon.GEAR, UiIcon.TRIANGLE_1_SOUTH);		
		String expectedJavascript = "{primary: 'ui-icon-gear', secondary: 'ui-icon-triangle-1-s'}";
		String generatedJavascript = buttonIcon.getJavascriptOption().toString();
		
		log.info(expectedJavascript);
		log.info(generatedJavascript);		
		Assert.assertEquals(generatedJavascript, expectedJavascript);
	}
}

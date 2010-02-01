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

import org.apache.wicket.Page;
import org.apache.wicket.util.tester.WicketTester;
import org.odlabs.wiquery.utils.WiQueryWebApplication;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Test on {@link Button}
 * @author Julien Roche
 *
 */
public class ButtonTestCase extends TestCase {
	// Properties
	private Button button;

	/**
	 * @throws java.lang.Exception
	 */
	public void setUp() throws Exception {
		new WicketTester(new WiQueryWebApplication() {
			@Override
			public Class<? extends Page> getHomePage() {
				return null;
			}
		});
		
		button = new Button("anId");
		button.setMarkupId(button.getId());
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.ui.button.Button#destroy()}.
	 */
	@Test
	public void testDestroy() {
		Assert.assertNotNull(button.destroy());
		Assert.assertEquals(button.destroy().render().toString(), 
				"$('#anId').button('destroy');");
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.ui.button.Button#disable()}.
	 */
	@Test
	public void testDisable() {
		Assert.assertNotNull(button.disable());
		Assert.assertEquals(button.disable().render().toString(), 
				"$('#anId').button('disable');");
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.ui.button.Button#enable()}.
	 */
	@Test
	public void testEnable() {
		Assert.assertNotNull(button.enable());
		Assert.assertEquals(button.enable().render().toString(), 
				"$('#anId').button('enable');");
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.ui.button.Button#getIcons()}.
	 */
	@Test
	public void testGetIcons() {
		Assert.assertNull(button.getIcons());
		button.setIcons(new ButtonIcon("ui-icon-gear", "ui-icon-triangle-1-s"));
		Assert.assertNotNull(button.getIcons());
		Assert.assertEquals(button.getIcons().getJavascriptOption().toString(), 
				"{primary: 'ui-icon-gear', secondary: 'ui-icon-triangle-1-s'}");
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.ui.button.Button#getLabel()}.
	 */
	@Test
	public void testGetLabel() {
		Assert.assertNull(button.getLabel());
		button.setLabel("a label");
		Assert.assertNotNull(button.getLabel());
		Assert.assertEquals(button.getLabel(), "a label");
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.ui.button.Button#getOptions()}.
	 */
	@Test
	public void testGetOptions() {
		Assert.assertNotNull(button.getOptions());
		Assert.assertEquals(button.getOptions().getJavaScriptOptions().toString(), "{}");
		button.setLabel("a label");
		Assert.assertEquals(button.getOptions().getJavaScriptOptions().toString(), "{label: 'a label'}");
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.ui.button.Button#isText()}.
	 */
	@Test
	public void testIsText() {
		Assert.assertTrue(button.isText());
		button.setText(false);
		Assert.assertFalse(button.isText());
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.ui.button.Button#statement()}.
	 */
	@Test
	public void testStatement() {
		Assert.assertNotNull(button.statement());
		Assert.assertEquals(button.statement().render().toString(), "$('#anId').button({});");
	}
}

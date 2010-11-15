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
import org.apache.wicket.WicketRuntimeException;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.util.tester.WicketTester;
import org.odlabs.wiquery.ui.themes.UiIcon;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Test on {@link ButtonBehavior}
 * @author Julien Roche
 *
 */
public class ButtonTestCase extends TestCase {
	// Properties
	private ButtonBehavior buttonBehavior;
	private WebMarkupContainer button;
	private WicketTester wicketTester;

	/**
	 * @throws java.lang.Exception
	 */
	public void setUp() throws Exception {
		wicketTester = new WicketTester(new WebApplication() {
			@Override
			public Class<? extends Page> getHomePage() {
				return ButtonPageFailedTest.class;
			}
		});
		
		button = new WebMarkupContainer("anId");
		buttonBehavior = new ButtonBehavior();
		button.add(buttonBehavior);
		button.setMarkupId(button.getId());
	}
	
	/**
	 * Test the type of the element (for {@link ButtonBehavior#onComponentTag(org.apache.wicket.Component, org.apache.wicket.markup.ComponentTag)}
	 */
	@Test
	public void testDomType() {
		try{
			wicketTester.startPage(ButtonPageFailedTest.class);
			fail();
			
		} catch (Exception e) {
			assertTrue(e instanceof WicketRuntimeException);
			assertEquals("Component failedButton must be applied to a tag of type 'input', 'button' or 'a', not '<table wicket:id=\"failedButton\" id=\"failedButton1\">' (line 0, column 0)", e.getMessage());
		}
	}

	/**
	 * Test method for {@link ButtonBehavior#destroy()}.
	 */
	@Test
	public void testDestroy() {
		Assert.assertNotNull(buttonBehavior.destroy());
		Assert.assertEquals(buttonBehavior.destroy().render().toString(), 
				"$('#anId').button('destroy');");
	}

	/**
	 * Test method for {@link ButtonBehavior#disable()}.
	 */
	@Test
	public void testDisable() {
		Assert.assertNotNull(buttonBehavior.disable());
		Assert.assertEquals(buttonBehavior.disable().render().toString(), 
				"$('#anId').button('disable');");
	}

	/**
	 * Test method for {@link ButtonBehavior#enable()}.
	 */
	@Test
	public void testEnable() {
		Assert.assertNotNull(buttonBehavior.enable());
		Assert.assertEquals(buttonBehavior.enable().render().toString(), 
				"$('#anId').button('enable');");
	}

	/**
	 * Test method for {@link ButtonBehavior#getIcons()}.
	 */
	@Test
	public void testGetIcons() {
		Assert.assertNull(buttonBehavior.getIcons());
		buttonBehavior.setIcons(new ButtonIcon("ui-icon-gear", "ui-icon-triangle-1-s"));
		Assert.assertNotNull(buttonBehavior.getIcons());
		Assert.assertEquals(buttonBehavior.getIcons().getJavascriptOption().toString(), 
				"{primary: 'ui-icon-gear', secondary: 'ui-icon-triangle-1-s'}");
		
		buttonBehavior.setIcons(UiIcon.GEAR, UiIcon.TRIANGLE_1_SOUTH);
		Assert.assertNotNull(buttonBehavior.getIcons());
		Assert.assertEquals(buttonBehavior.getIcons().getJavascriptOption().toString(), 
				"{primary: 'ui-icon-gear', secondary: 'ui-icon-triangle-1-s'}");
	}

	/**
	 * Test method for {@link ButtonBehavior#getLabel()}.
	 */
	@Test
	public void testGetLabel() {
		Assert.assertNull(buttonBehavior.getLabel());
		buttonBehavior.setLabel("a label");
		Assert.assertNotNull(buttonBehavior.getLabel());
		Assert.assertEquals(buttonBehavior.getLabel(), "a label");		
	}

	/**
	 * The method for {@link ButtonBehavior#setLabel(org.apache.wicket.model.IModel)}.
	 */
	@Test
	public void testSetLabelModel() {
		//Options is added before bind.
		ButtonTestPage page = (ButtonTestPage)wicketTester.startPage(new ButtonTestPage(true));
		Assert.assertNotNull(page.getBehavior().getLabel());
		Assert.assertEquals(page.getBehavior().getLabel(), "This is a link");
		
		page = (ButtonTestPage)wicketTester.startPage(new ButtonTestPage(false));
		Assert.assertNotNull(page.getBehavior().getLabel());
		Assert.assertEquals(page.getBehavior().getLabel(), "This is a link");
	}
	
	
	/**
	 * Test method for {@link ButtonBehavior#getOptions()}.
	 */
	@Test
	public void testGetOptions() {
		Assert.assertNotNull(buttonBehavior.getOptions());
		Assert.assertEquals(buttonBehavior.getOptions().getJavaScriptOptions().toString(), "{}");
		buttonBehavior.setLabel("a label");
		Assert.assertEquals(buttonBehavior.getOptions().getJavaScriptOptions().toString(), "{label: 'a label'}");
	}
	
	/**
	 * Test method for {@link ButtonBehavior#isDisabled()}.
	 */
	@Test
	public void testIsDisabled() {
		Assert.assertNull(buttonBehavior.isDisabled());
		buttonBehavior.setDisabled(true);
		Assert.assertTrue(buttonBehavior.isDisabled());
	}

	/**
	 * Test method for {@link ButtonBehavior#isText()}.
	 */
	@Test
	public void testIsText() {
		Assert.assertTrue(buttonBehavior.isText());
		buttonBehavior.setText(false);
		Assert.assertFalse(buttonBehavior.isText());
	}

	/**
	 * Test method for {@link ButtonBehavior#statement()}.
	 */
	@Test
	public void testStatement() {
		Assert.assertNotNull(buttonBehavior.statement());
		Assert.assertEquals(buttonBehavior.statement().render().toString(), "$('#anId').button({});");
	}
	
	/**
	 * Test method for {@link ButtonBehavior#widget()}.
	 */
	@Test
	public void testWidget() {
		Assert.assertNotNull(buttonBehavior.widget());
		Assert.assertEquals(buttonBehavior.widget().render().toString(), 
				"$('#anId').button('widget');");
	}
}

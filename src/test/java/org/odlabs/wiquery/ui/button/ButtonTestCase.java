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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.apache.wicket.WicketRuntimeException;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.util.tester.TestPanelSource;
import org.junit.Before;
import org.junit.Test;
import org.odlabs.wiquery.tester.WiQueryTestCase;
import org.odlabs.wiquery.ui.DivTestPanel;
import org.odlabs.wiquery.ui.InputTestPanel;
import org.odlabs.wiquery.ui.slider.Slider;
import org.odlabs.wiquery.ui.themes.UiIcon;

/**
 * Test on {@link ButtonBehavior}
 * 
 * @author Julien Roche
 */
public class ButtonTestCase extends WiQueryTestCase {
	// Properties
	private ButtonBehavior buttonBehavior;
	private WebMarkupContainer button;

	@Override
	@Before
	public void setUp() {
		super.setUp();

		tester.startPanel(new TestPanelSource() {
			private static final long serialVersionUID = 1L;

			public Panel getTestPanel(String panelId) {
				Panel panel = new InputTestPanel(panelId);
				button = new WebMarkupContainer("anId");
				buttonBehavior = new ButtonBehavior();
				button.add(buttonBehavior);
				button.setMarkupId(button.getId());
				panel.add(button);
				return panel;
			}
		});
	}

	/**
	 * Test the type of the element (for
	 * {@link ButtonBehavior#onComponentTag(org.apache.wicket.Component, org.apache.wicket.markup.ComponentTag)}
	 */
	@Test
	public void testDomType() {
		try {
			tester.startPage(ButtonPageFailedTestPage.class);
			fail();

		} catch (Exception e) {
			assertTrue(e instanceof WicketRuntimeException);
			assertEquals(
					"Component failedButton must be applied to a tag of type 'input', 'button' or 'a', not  '<table wicket:id=\"failedButton\" id=\"failedButton1\">' (line 0, column 0)",
					e.getMessage());
		}
	}

	/**
	 * Test method for {@link ButtonBehavior#destroy()}.
	 */
	@Test
	public void testDestroy() {
		assertNotNull(buttonBehavior.destroy());
		assertEquals(buttonBehavior.destroy().render().toString(),
				"$('#anId').button('destroy');");
	}

	/**
	 * Test method for {@link ButtonBehavior#disable()}.
	 */
	@Test
	public void testDisable() {
		assertNotNull(buttonBehavior.disable());
		assertEquals(buttonBehavior.disable().render().toString(),
				"$('#anId').button('disable');");
	}

	/**
	 * Test method for {@link ButtonBehavior#enable()}.
	 */
	@Test
	public void testEnable() {
		assertNotNull(buttonBehavior.enable());
		assertEquals(buttonBehavior.enable().render().toString(),
				"$('#anId').button('enable');");
	}

	/**
	 * Test method for {@link ButtonBehavior#getIcons()}.
	 */
	@Test
	public void testGetIcons() {
		assertNull(buttonBehavior.getIcons());
		buttonBehavior.setIcons(new ButtonIcon("ui-icon-gear",
				"ui-icon-triangle-1-s"));
		assertNotNull(buttonBehavior.getIcons());
		assertEquals(
				buttonBehavior.getIcons().getJavascriptOption().toString(),
				"{primary: 'ui-icon-gear', secondary: 'ui-icon-triangle-1-s'}");

		buttonBehavior.setIcons(UiIcon.GEAR, UiIcon.TRIANGLE_1_SOUTH);
		assertNotNull(buttonBehavior.getIcons());
		assertEquals(
				buttonBehavior.getIcons().getJavascriptOption().toString(),
				"{primary: 'ui-icon-gear', secondary: 'ui-icon-triangle-1-s'}");
	}

	/**
	 * Test method for {@link ButtonBehavior#getLabel()}.
	 */
	@Test
	public void testGetLabel() {
		assertNull(buttonBehavior.getLabel());
		buttonBehavior.setLabel("a label");
		assertNotNull(buttonBehavior.getLabel());
		assertEquals(buttonBehavior.getLabel(), "a label");
	}

	/**
	 * The method for
	 * {@link ButtonBehavior#setLabel(org.apache.wicket.model.IModel)}.
	 */
	@Test
	public void testSetLabelModel() {
		// Options is added before bind.
		ButtonTestPage page = (ButtonTestPage) tester
				.startPage(new ButtonTestPage(true));
		assertNotNull(page.getBehavior().getLabel());
		assertEquals(page.getBehavior().getLabel(), "This is a link");

		page = (ButtonTestPage) tester
				.startPage(new ButtonTestPage(false));
		assertNotNull(page.getBehavior().getLabel());
		assertEquals(page.getBehavior().getLabel(), "This is a link");
	}

	/**
	 * Test method for {@link ButtonBehavior#getOptions()}.
	 */
	@Test
	public void testGetOptions() {
		assertNotNull(buttonBehavior.getOptions());
		assertEquals(buttonBehavior.getOptions().getJavaScriptOptions()
				.toString(), "{}");
		buttonBehavior.setLabel("a label");
		assertEquals(buttonBehavior.getOptions().getJavaScriptOptions()
				.toString(), "{label: 'a label'}");
	}

	/**
	 * Test method for {@link ButtonBehavior#isDisabled()}.
	 */
	@Test
	public void testIsDisabled() {
		assertNull(buttonBehavior.isDisabled());
		buttonBehavior.setDisabled(true);
		assertTrue(buttonBehavior.isDisabled());
	}

	/**
	 * Test method for {@link ButtonBehavior#isText()}.
	 */
	@Test
	public void testIsText() {
		assertTrue(buttonBehavior.isText());
		buttonBehavior.setText(false);
		assertFalse(buttonBehavior.isText());
	}

	/**
	 * Test method for {@link ButtonBehavior#statement()}.
	 */
	@Test
	public void testStatement() {
		assertNotNull(buttonBehavior.statement());
		assertEquals(buttonBehavior.statement().render().toString(),
				"$('#anId').button({});");
	}

	/**
	 * Test method for {@link ButtonBehavior#widget()}.
	 */
	@Test
	public void testWidget() {
		assertNotNull(buttonBehavior.widget());
		assertEquals(buttonBehavior.widget().render().toString(),
				"$('#anId').button('widget');");
	}
}

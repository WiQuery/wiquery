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
package org.odlabs.wiquery.ui.accordion;

import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.util.tester.DummyPanelPage;
import org.apache.wicket.util.tester.TestPanelSource;
import org.odlabs.wiquery.core.options.LiteralOption;
import org.odlabs.wiquery.tester.WiQueryTestCase;
import org.odlabs.wiquery.ui.accordion.Accordion.AccordionTriggerEvent;
import org.odlabs.wiquery.ui.core.JsScopeUiEvent;
import org.junit.Assert;
import org.junit.Test;

/**
 * Test on {@link Accordion}
 * 
 * @author Julien Roche
 * 
 */
public class AccordionTestCase extends WiQueryTestCase {
	// Properties
	private Accordion accordion;

	public void setUp() {
		super.setUp();

		accordion = new Accordion("anId");
		accordion.setMarkupId(accordion.getId());
	}

	/**
	 * Test method for
	 * {@link org.odlabs.wiquery.ui.accordion.Accordion#activate(int)}.
	 */
	@Test
	public void testActivateInt() {
		Assert.assertNotNull(accordion.activate(5));
		Assert.assertEquals(accordion.activate(5).render().toString(),
				"$('#anId').accordion('activate', 5);");
	}

	/**
	 * Test method for
	 * {@link org.odlabs.wiquery.ui.accordion.Accordion#destroy()}.
	 */
	@Test
	public void testDestroy() {
		Assert.assertNotNull(accordion.destroy());
		Assert.assertEquals(accordion.destroy().render().toString(),
				"$('#anId').accordion('destroy');");
	}

	/**
	 * Test method for
	 * {@link org.odlabs.wiquery.ui.accordion.Accordion#disable()}.
	 */
	@Test
	public void testDisable() {
		Assert.assertNotNull(accordion.disable());
		Assert.assertEquals(accordion.disable().render().toString(),
				"$('#anId').accordion('disable');");
	}

	/**
	 * Test method for
	 * {@link org.odlabs.wiquery.ui.accordion.Accordion#enable()}.
	 */
	@Test
	public void testEnable() {
		Assert.assertNotNull(accordion.enable());
		Assert.assertEquals(accordion.enable().render().toString(),
				"$('#anId').accordion('enable');");
	}

	/**
	 * Test method for
	 * {@link org.odlabs.wiquery.ui.accordion.Accordion#getActive()}.
	 */
	@Test
	public void testGetActive() {
		Assert.assertNull(accordion.getActive());
		accordion.setActive(new AccordionActive(true));
		Assert.assertNotNull(accordion.getActive());
		Assert.assertEquals(accordion.getActive().getJavascriptOption()
				.toString(), "true");
	}

	/**
	 * Test method for
	 * {@link org.odlabs.wiquery.ui.accordion.Accordion#getAnimated()}.
	 */
	@Test
	public void testGetAnimationEffect() {
		Assert.assertNotNull(accordion.getAnimated());
		Assert.assertEquals(accordion.getAnimated().getJavascriptOption()
				.toString(), "'slide'");
		accordion.setAnimated(new AccordionAnimated(true));
		Assert.assertEquals(accordion.getAnimated().getJavascriptOption()
				.toString(), "true");
	}

	/**
	 * Test method for
	 * {@link org.odlabs.wiquery.ui.accordion.Accordion#getEvent()}.
	 */
	@Test
	public void testGetEvent() {
		Assert.assertNotNull(accordion.getEvent());
		Assert.assertEquals(accordion.getEvent(), AccordionTriggerEvent.CLICK);
		accordion.setEvent(AccordionTriggerEvent.MOUSEOVER);
		Assert.assertEquals(accordion.getEvent(),
				AccordionTriggerEvent.MOUSEOVER);
	}

	/**
	 * Test method for
	 * {@link org.odlabs.wiquery.ui.accordion.Accordion#getHeader()}.
	 */
	@Test
	public void testGetHeader() {
		Assert.assertNotNull(accordion.getHeader());
		Assert.assertEquals(accordion.getHeader().getJavascriptOption(),
				"'> li> :first-child, > :not(li):even'");
		accordion.setHeader(new AccordionHeader(new LiteralOption("> li")));
		Assert.assertEquals(accordion.getHeader().getJavascriptOption(),
				"'> li'");
	}

	/**
	 * Test method for
	 * {@link org.odlabs.wiquery.ui.accordion.Accordion#getIcons()}.
	 */
	@Test
	public void testGetIcons() {
		Assert.assertNotNull(accordion.getIcons());
		Assert
				.assertEquals(accordion.getIcons().getJavascriptOption()
						.toString(),
						"{'header': 'ui-icon-triangle-1-e', 'headerSelected': 'ui-icon-triangle-1-s'}");
		accordion.setIcons(new AccordionIcon("ui-icon-triangle-1-s",
				"ui-icon-triangle-1-e"));
		Assert
				.assertEquals(accordion.getIcons().getJavascriptOption()
						.toString(),
						"{'header': 'ui-icon-triangle-1-s', 'headerSelected': 'ui-icon-triangle-1-e'}");
	}

	/**
	 * Test method for
	 * {@link org.odlabs.wiquery.ui.accordion.Accordion#getOptions()}.
	 */
	@Test
	public void testGetOptions() {
		Assert.assertNotNull(accordion.getOptions());
		Assert.assertEquals(accordion.getOptions().getJavaScriptOptions()
				.toString(), "{}");
		accordion.setAutoHeight(true);
		Assert.assertEquals(accordion.getOptions().getJavaScriptOptions()
				.toString(), "{autoHeight: true}");
	}

	/**
	 * Test method for
	 * {@link org.odlabs.wiquery.ui.accordion.Accordion#isAutoHeight()}.
	 */
	@Test
	public void testIsAutoHeight() {
		Assert.assertTrue(accordion.isAutoHeight());
		accordion.setAutoHeight(false);
		Assert.assertFalse(accordion.isAutoHeight());
	}

	/**
	 * Test method for
	 * {@link org.odlabs.wiquery.ui.accordion.Accordion#isClearStyle()}.
	 */
	@Test
	public void testIsClearStyle() {
		Assert.assertFalse(accordion.isClearStyle());
		accordion.setClearStyle(true);
		Assert.assertTrue(accordion.isClearStyle());
	}

	/**
	 * Test method for
	 * {@link org.odlabs.wiquery.ui.accordion.Accordion#isCollapsible()}.
	 */
	@Test
	public void testIsCollapsible() {
		Assert.assertFalse(accordion.isCollapsible());
		accordion.setCollapsible(true);
		Assert.assertTrue(accordion.isCollapsible());
	}

	/**
	 * Test method for
	 * {@link org.odlabs.wiquery.ui.accordion.Accordion#isDisabled()}.
	 */
	@Test
	public void testIsDisabled() {
		Assert.assertFalse(accordion.isDisabled());
		accordion.setDisabled(true);
		Assert.assertTrue(accordion.isDisabled());
	}

	/**
	 * Test method for
	 * {@link org.odlabs.wiquery.ui.accordion.Accordion#isFillSpace()}.
	 */
	@Test
	public void testIsFillSpace() {
		Assert.assertFalse(accordion.isFillSpace());
		accordion.setFillSpace(true);
		Assert.assertTrue(accordion.isFillSpace());
	}

	/**
	 * Test method for
	 * {@link org.odlabs.wiquery.ui.accordion.Accordion#isNavigation()}.
	 */
	@Test
	public void testIsNavigation() {
		Assert.assertFalse(accordion.isNavigation());
		accordion.setNavigation(true);
		Assert.assertTrue(accordion.isNavigation());
	}

	/**
	 * Test method for
	 * {@link org.odlabs.wiquery.ui.accordion.Accordion#setChangeEvent(org.odlabs.wiquery.ui.core.JsScopeUiEvent)}
	 * .
	 */
	@Test
	public void testSetChangeEvent() {
		Assert.assertEquals(accordion.statement().render().toString(),
				"$('#anId').accordion({});");
		accordion.setChangeEvent(JsScopeUiEvent.quickScope("alert('event');"));
		Assert
				.assertEquals(accordion.statement().render().toString(),
						"$('#anId').accordion({change: function(event, ui) {\n\talert('event');\n}});");
	}

	/**
	 * Test method for
	 * {@link org.odlabs.wiquery.ui.accordion.Accordion#setChangeStartEvent(org.odlabs.wiquery.ui.core.JsScopeUiEvent)}
	 * .
	 */
	@Test
	public void testSetChangeStartEvent() {
		Assert.assertEquals(accordion.statement().render().toString(),
				"$('#anId').accordion({});");
		accordion.setChangeStartEvent(JsScopeUiEvent
				.quickScope("alert('event');"));
		Assert
				.assertEquals(
						accordion.statement().render().toString(),
						"$('#anId').accordion({changestart: function(event, ui) {\n\talert('event');\n}});");
	}

	/**
	 * Test method for
	 * {@link org.odlabs.wiquery.ui.accordion.Accordion#statement()}.
	 */
	@Test
	public void testStatement() {
		Assert.assertNotNull(accordion.statement());
		Assert.assertEquals(accordion.statement().render().toString(),
				"$('#anId').accordion({});");
	}

	/**
	 * Test method for
	 * {@link org.odlabs.wiquery.ui.accordion.Accordion#widget()}.
	 */
	@Test
	public void testWidget() {
		Assert.assertNotNull(accordion.widget());
		Assert.assertEquals(accordion.widget().render().toString(),
				"$('#anId').accordion('widget');");
	}
	
	/**
	 * Test method for the complete component.
	 * TODO create an accordion that is serverside testable.
	 */
	@Test
	public void testAccordionOnPage()
	{
		tester.startPanel(new TestPanelSource() {
			private static final long serialVersionUID = 1L;

			public Panel getTestPanel(String panelId) {
				Panel panel = new AccordionTestPanel(panelId);
				panel.add(accordion);
				return panel;
			}
		});
		
		tester.assertComponent(DummyPanelPage.TEST_PANEL_ID+":anId", Accordion.class);
	}
}

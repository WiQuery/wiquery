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

import static org.junit.Assert.*;

import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.util.tester.ITestPanelSource;
import org.junit.Before;
import org.junit.Test;
import org.odlabs.wiquery.core.options.LiteralOption;
import org.odlabs.wiquery.tester.WiQueryTestCase;
import org.odlabs.wiquery.ui.DivTestPanel;
import org.odlabs.wiquery.ui.accordion.Accordion.AccordionTriggerEvent;
import org.odlabs.wiquery.ui.core.JsScopeUiEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Test on {@link Accordion}
 * 
 * @author Julien Roche
 */
public class AccordionTestCase extends WiQueryTestCase
{
	private static final Logger log = LoggerFactory.getLogger(AccordionTestCase.class);

	private Accordion accordion;

	@Override
	@Before
	public void setUp()
	{
		super.setUp();

		tester.startPanel(new ITestPanelSource()
		{
			private static final long serialVersionUID = 1L;

			public Panel getTestPanel(String panelId)
			{
				Panel panel = new DivTestPanel(panelId);
				accordion = new Accordion("anId");
				accordion.setMarkupId(accordion.getId());
				panel.add(accordion);
				return panel;
			}
		});
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.ui.accordion.Accordion#activate(int)}.
	 */
	@Test
	public void testActivateInt()
	{
		assertNotNull(accordion.activate(5));
		assertEquals(accordion.activate(5).render().toString(),
			"$('#anId').accordion('activate', 5);");
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.ui.accordion.Accordion#destroy()}.
	 */
	@Test
	public void testDestroy()
	{
		assertNotNull(accordion.destroy());
		assertEquals(accordion.destroy().render().toString(), "$('#anId').accordion('destroy');");
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.ui.accordion.Accordion#disable()}.
	 */
	@Test
	public void testDisable()
	{
		assertNotNull(accordion.disable());
		assertEquals(accordion.disable().render().toString(), "$('#anId').accordion('disable');");
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.ui.accordion.Accordion#enable()}.
	 */
	@Test
	public void testEnable()
	{
		assertNotNull(accordion.enable());
		assertEquals(accordion.enable().render().toString(), "$('#anId').accordion('enable');");
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.ui.accordion.Accordion#getActive()}.
	 */
	@Test
	public void testGetActive()
	{
		assertNull(accordion.getActive());
		accordion.setActive(new AccordionActive(true));
		assertNotNull(accordion.getActive());
		assertEquals(accordion.getActive().getJavascriptOption().toString(), "true");
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.ui.accordion.Accordion#getAnimated()}.
	 */
	@Test
	public void testGetAnimationEffect()
	{
		assertNotNull(accordion.getAnimated());
		assertEquals(accordion.getAnimated().getJavascriptOption().toString(), "'slide'");
		accordion.setAnimated(new AccordionAnimated(true));
		assertEquals(accordion.getAnimated().getJavascriptOption().toString(), "true");
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.ui.accordion.Accordion#getEvent()}.
	 */
	@Test
	public void testGetEvent()
	{
		assertNotNull(accordion.getEvent());
		assertEquals(accordion.getEvent(), AccordionTriggerEvent.CLICK);
		accordion.setEvent(AccordionTriggerEvent.MOUSEOVER);
		assertEquals(accordion.getEvent(), AccordionTriggerEvent.MOUSEOVER);
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.ui.accordion.Accordion#getHeader()}.
	 */
	@Test
	public void testGetHeader()
	{
		assertNotNull(accordion.getHeader());
		assertEquals(accordion.getHeader().getJavascriptOption(),
			"'> li> :first-child, > :not(li):even'");
		accordion.setHeader(new AccordionHeader(new LiteralOption("> li")));
		assertEquals(accordion.getHeader().getJavascriptOption(), "'> li'");
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.ui.accordion.Accordion#getIcons()}.
	 */
	@Test
	public void testGetIcons()
	{
		assertNotNull(accordion.getIcons());

		assertEquals(accordion.getIcons().getJavascriptOption().toString(),
			"{'header': 'ui-icon-triangle-1-e', 'headerSelected': 'ui-icon-triangle-1-s'}");
		accordion.setIcons(new AccordionIcon("ui-icon-triangle-1-s", "ui-icon-triangle-1-e"));

		assertEquals(accordion.getIcons().getJavascriptOption().toString(),
			"{'header': 'ui-icon-triangle-1-s', 'headerSelected': 'ui-icon-triangle-1-e'}");
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.ui.accordion.Accordion#getOptions()}.
	 */
	@Test
	public void testGetOptions()
	{
		assertNotNull(accordion.getOptions());
		assertEquals(accordion.getOptions().getJavaScriptOptions().toString(), "{}");
		accordion.setAutoHeight(true);
		assertEquals(accordion.getOptions().getJavaScriptOptions().toString(), "{autoHeight: true}");
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.ui.accordion.Accordion#isAutoHeight()}.
	 */
	@Test
	public void testIsAutoHeight()
	{
		assertTrue(accordion.isAutoHeight());
		accordion.setAutoHeight(false);
		assertFalse(accordion.isAutoHeight());
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.ui.accordion.Accordion#isClearStyle()}.
	 */
	@Test
	public void testIsClearStyle()
	{
		assertFalse(accordion.isClearStyle());
		accordion.setClearStyle(true);
		assertTrue(accordion.isClearStyle());
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.ui.accordion.Accordion#isCollapsible()}.
	 */
	@Test
	public void testIsCollapsible()
	{
		assertFalse(accordion.isCollapsible());
		accordion.setCollapsible(true);
		assertTrue(accordion.isCollapsible());
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.ui.accordion.Accordion#isDisabled()}.
	 */
	@Test
	public void testIsDisabled()
	{
		assertFalse(accordion.isDisabled());
		accordion.setDisabled(true);
		assertTrue(accordion.isDisabled());
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.ui.accordion.Accordion#isFillSpace()}.
	 */
	@Test
	public void testIsFillSpace()
	{
		assertFalse(accordion.isFillSpace());
		accordion.setFillSpace(true);
		assertTrue(accordion.isFillSpace());
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.ui.accordion.Accordion#isNavigation()}.
	 */
	@Test
	public void testIsNavigation()
	{
		assertFalse(accordion.isNavigation());
		accordion.setNavigation(true);
		assertTrue(accordion.isNavigation());
	}

	/**
	 * Test method for
	 * {@link org.odlabs.wiquery.ui.accordion.Accordion#setChangeEvent(org.odlabs.wiquery.ui.core.JsScopeUiEvent)}
	 * .
	 */
	@Test
	public void testSetChangeEvent()
	{
		assertEquals(accordion.statement().render().toString(), "$('#anId').accordion({});");
		accordion.setChangeEvent(JsScopeUiEvent.quickScope("alert('event');"));

		assertEquals(accordion.statement().render().toString(),
			"$('#anId').accordion({change: function(event, ui) {\n\talert('event');\n}});");
	}

	/**
	 * Test method for
	 * {@link org.odlabs.wiquery.ui.accordion.Accordion#setChangeStartEvent(org.odlabs.wiquery.ui.core.JsScopeUiEvent)}
	 * .
	 */
	@Test
	public void testSetChangeStartEvent()
	{
		assertEquals(accordion.statement().render().toString(), "$('#anId').accordion({});");
		accordion.setChangeStartEvent(JsScopeUiEvent.quickScope("alert('event');"));

		assertEquals(accordion.statement().render().toString(),
			"$('#anId').accordion({changestart: function(event, ui) {\n\talert('event');\n}});");
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.ui.accordion.Accordion#statement()}.
	 */
	@Test
	public void testStatement()
	{
		assertNotNull(accordion.statement());
		assertEquals(accordion.statement().render().toString(), "$('#anId').accordion({});");
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.ui.accordion.Accordion#widget()}.
	 */
	@Test
	public void testWidget()
	{
		assertNotNull(accordion.widget());
		assertEquals(accordion.widget().render().toString(), "$('#anId').accordion('widget');");
	}

	@Override
	protected Logger getLog()
	{
		return log;
	}
}

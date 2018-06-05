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
package org.wicketstuff.wiquery.ui.accordion;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.apache.wicket.markup.html.panel.Panel;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.wicketstuff.wiquery.core.options.LiteralOption;
import org.wicketstuff.wiquery.tester.WiQueryTestCase;
import org.wicketstuff.wiquery.ui.DivTestPanel;
import org.wicketstuff.wiquery.ui.accordion.Accordion.AccordionTriggerEvent;
import org.wicketstuff.wiquery.ui.core.JsScopeUiEvent;
import org.wicketstuff.wiquery.ui.options.HeightStyleEnum;

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

		Panel panel = new DivTestPanel("panelId");
		accordion = new Accordion("anId");
		accordion.setMarkupId(accordion.getId());
		panel.add(accordion);
		tester.startComponentInPage(panel);
	}

	/**
	 * Test method for {@link org.wicketstuff.wiquery.ui.accordion.Accordion#destroy()}.
	 */
	@Test
	public void testDestroy()
	{
		assertNotNull(accordion.destroy());
		assertEquals(accordion.destroy().render().toString(), "$('#anId').accordion('destroy');");
	}

	/**
	 * Test method for {@link org.wicketstuff.wiquery.ui.accordion.Accordion#disable()}.
	 */
	@Test
	public void testDisable()
	{
		assertNotNull(accordion.disable());
		assertEquals(accordion.disable().render().toString(), "$('#anId').accordion('disable');");
	}

	/**
	 * Test method for {@link org.wicketstuff.wiquery.ui.accordion.Accordion#enable()}.
	 */
	@Test
	public void testEnable()
	{
		assertNotNull(accordion.enable());
		assertEquals(accordion.enable().render().toString(), "$('#anId').accordion('enable');");
	}

	/**
	 * Test method for {@link org.wicketstuff.wiquery.ui.accordion.Accordion#refresh()}.
	 */
	@Test
	public void testRefresh()
	{
		assertNotNull(accordion.refresh());
		assertEquals(accordion.refresh().render().toString(), "$('#anId').accordion('refresh');");
	}

	/**
	 * Test method for {@link org.wicketstuff.wiquery.ui.accordion.Accordion#getActive()}.
	 */
	@Test
	public void testGetActive()
	{
		assertEquals(accordion.getActive(), 0);
		accordion.setActive(1);
		assertEquals(accordion.getActive(), 1);
		accordion.setActive(true);
		assertEquals(accordion.getActive(), 0);
	}

	/**
	 * Test method for {@link org.wicketstuff.wiquery.ui.accordion.Accordion#getAnimate()}.
	 */
	@Test
	public void testGetAnimate()
	{
		assertNotNull(accordion.getAnimate());
		assertEquals(accordion.getAnimate().getJavascriptOption().toString(), "{}");
		accordion.setAnimate(new AccordionAnimateOption(true));
		assertEquals(accordion.getAnimate().getJavascriptOption().toString(), "true");
		accordion.setAnimate(
			new AccordionAnimateOption(new AccordionEffectOptionObject().setDuration(200).setDown(
				new AccordionDownEffectOptionObject().setEasing("linear").setDuration(200))));
		assertEquals(accordion.getAnimate().getJavascriptOption().toString(),
			"{duration: 200, down: {easing: 'linear', duration: 200}}");
	}

	/**
	 * Test method for {@link org.wicketstuff.wiquery.ui.accordion.Accordion#getEvent()}.
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
	 * Test method for {@link org.wicketstuff.wiquery.ui.accordion.Accordion#getHeader()}.
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
	 * Test method for {@link org.wicketstuff.wiquery.ui.accordion.Accordion#getIcons()}.
	 */
	@Test
	public void testGetIcons()
	{
		assertNotNull(accordion.getIcons());

		assertEquals(accordion.getIcons().getJavascriptOption().toString(),
			"{'header': 'ui-icon-triangle-1-e', 'activeHeader': 'ui-icon-triangle-1-s'}");
		accordion.setIcons(new AccordionIcon("ui-icon-triangle-1-s", "ui-icon-triangle-1-e"));

		assertEquals(accordion.getIcons().getJavascriptOption().toString(),
			"{'header': 'ui-icon-triangle-1-s', 'activeHeader': 'ui-icon-triangle-1-e'}");
	}

	/**
	 * Test method for {@link org.wicketstuff.wiquery.ui.accordion.Accordion#getOptions()}.
	 */
	@Test
	public void testGetOptions()
	{
		assertNotNull(accordion.getOptions());
		assertEquals(accordion.getOptions().getJavaScriptOptions().toString(), "{}");
		accordion.setCollapsible(true);
		assertEquals(accordion.getOptions().getJavaScriptOptions().toString(),
			"{collapsible: true}");
	}

	/**
	 * Test method for {@link org.wicketstuff.wiquery.ui.accordion.Accordion#getHeightStyle()}.
	 */
	@Test
	public void testGetHeightStyle()
	{
		assertEquals(accordion.getHeightStyle(), HeightStyleEnum.CONTENT);
		accordion.setHeightStyle(HeightStyleEnum.AUTO);
		assertEquals(accordion.getHeightStyle(), HeightStyleEnum.AUTO);
	}

	/**
	 * Test method for {@link org.wicketstuff.wiquery.ui.accordion.Accordion#isCollapsible()}.
	 */
	@Test
	public void testIsCollapsible()
	{
		assertFalse(accordion.isCollapsible());
		accordion.setCollapsible(true);
		assertTrue(accordion.isCollapsible());
	}

	/**
	 * Test method for {@link org.wicketstuff.wiquery.ui.accordion.Accordion#isDisabled()}.
	 */
	@Test
	public void testIsDisabled()
	{
		assertFalse(accordion.isDisabled());
		accordion.setDisabled(true);
		assertTrue(accordion.isDisabled());
	}

	/**
	 * Test method for
	 * {@link org.wicketstuff.wiquery.ui.accordion.Accordion#setActivateEvent(org.wicketstuff.wiquery.ui.core.JsScopeUiEvent)}
	 * .
	 */
	@Test
	public void testSetActivateEvent()
	{
		assertEquals(accordion.statement().render().toString(), "$('#anId').accordion({});");
		accordion.setActivateEvent(JsScopeUiEvent.quickScope("alert('event');"));

		assertEquals(accordion.statement().render().toString(),
			"$('#anId').accordion({activate: function(event, ui) {\n\talert('event');\n}});");
	}

	/**
	 * Test method for
	 * {@link org.wicketstuff.wiquery.ui.accordion.Accordion#setBeforeActivateEvent(org.wicketstuff.wiquery.ui.core.JsScopeUiEvent)}
	 * .
	 */
	@Test
	public void testSetBeforeActivateEvent()
	{
		assertEquals(accordion.statement().render().toString(), "$('#anId').accordion({});");
		accordion.setBeforeActivateEvent(JsScopeUiEvent.quickScope("alert('event');"));

		assertEquals(accordion.statement().render().toString(),
			"$('#anId').accordion({beforeActivate: function(event, ui) {\n\talert('event');\n}});");
	}

	/**
	 * Test method for {@link org.wicketstuff.wiquery.ui.accordion.Accordion#statement()}.
	 */
	@Test
	public void testStatement()
	{
		assertNotNull(accordion.statement());
		assertEquals(accordion.statement().render().toString(), "$('#anId').accordion({});");
	}

	/**
	 * Test method for {@link org.wicketstuff.wiquery.ui.accordion.Accordion#widget()}.
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

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
package org.odlabs.wiquery.ui.tabs;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.apache.wicket.markup.html.panel.Panel;
import org.junit.Before;
import org.junit.Test;
import org.odlabs.wiquery.core.events.MouseEvent;
import org.odlabs.wiquery.core.options.ArrayItemOptions;
import org.odlabs.wiquery.core.options.EventLabelOptions;
import org.odlabs.wiquery.core.options.IntegerItemOptions;
import org.odlabs.wiquery.tester.WiQueryTestCase;
import org.odlabs.wiquery.ui.DivTestPanel;
import org.odlabs.wiquery.ui.core.JsScopeUiEvent;
import org.odlabs.wiquery.ui.tabs.Tabs.HeightStyleEnum;

/**
 * Test on {@link Tabs}
 * 
 * @author Julien Roche
 */
public class TabsTestCase extends WiQueryTestCase
{
	// Properties
	private Tabs tabs;

	@SuppressWarnings("deprecation")
	@Override
	@Before
	public void setUp()
	{
		super.setUp();

		Panel panel = new DivTestPanel("panelId");
		tabs = new Tabs("anId");
		tabs.setMarkupId(tabs.getId());
		panel.add(tabs);
		tester.startComponent(panel);
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.ui.tabs.Tabs#destroy()}.
	 */
	@Test
	public void testRefresh()
	{
		assertNotNull(tabs.refresh());
		assertEquals(tabs.refresh().render().toString(), "$('#anId').tabs('refresh');");
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.ui.tabs.Tabs#destroy()}.
	 */
	@Test
	public void testDestroy()
	{
		assertNotNull(tabs.destroy());
		assertEquals(tabs.destroy().render().toString(), "$('#anId').tabs('destroy');");
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.ui.tabs.Tabs#disable()}.
	 */
	@Test
	public void testDisable()
	{
		assertNotNull(tabs.disable());
		assertEquals(tabs.disable().render().toString(), "$('#anId').tabs('disable');");
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.ui.tabs.Tabs#disable(int)}.
	 */
	@Test
	public void testDisableInt()
	{
		assertNotNull(tabs.disable(5));
		assertEquals(tabs.disable(5).render().toString(), "$('#anId').tabs('disable', 5);");
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.ui.tabs.Tabs#enable()}.
	 */
	@Test
	public void testEnable()
	{
		assertNotNull(tabs.enable());
		assertEquals(tabs.enable().render().toString(), "$('#anId').tabs('enable');");
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.ui.tabs.Tabs#enable(int)}.
	 */
	@Test
	public void testEnableInt()
	{
		assertNotNull(tabs.enable(5));
		assertEquals(tabs.enable(5).render().toString(), "$('#anId').tabs('enable', 5);");
	}

	/**
	 * Test method for
	 * {@link org.odlabs.wiquery.ui.tabs.Tabs#getActive()}.
	 */
	@Test
	public void testGetActive()
	{
		assertEquals(tabs.getActive(), 0);
		tabs.setActive(1);
		assertEquals(tabs.getActive(), 1);
		tabs.setActive(true);
		assertEquals(tabs.getActive(), 0);
	}
	
	/**
	 * Test method for
	 * {@link org.odlabs.wiquery.ui.tabs.Tabs#getHeightStyle()}.
	 */
	@Test
	public void testGetHeightStyle()
	{
		assertEquals(tabs.getHeightStyle(), HeightStyleEnum.CONTENT);
		tabs.setHeightStyle(HeightStyleEnum.AUTO);
		assertEquals(tabs.getHeightStyle(), HeightStyleEnum.AUTO);
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.ui.tabs.Tabs#getDisabled()}.
	 */
	@Test
	public void testGetDisabled()
	{
		assertNull(tabs.getDisabled());
		ArrayItemOptions<IntegerItemOptions> array = new ArrayItemOptions<IntegerItemOptions>();
		array.add(new IntegerItemOptions(1));
		tabs.setDisabled(array);
		assertNotNull(tabs.getDisabled());
		assertEquals(tabs.getDisabled().getJavascriptOption().toString(), "[1]");
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.ui.tabs.Tabs#getEvent()}.
	 */
	@Test
	public void testGetEvent()
	{
		assertNotNull(tabs.getEvent());
		assertEquals(tabs.getEvent().getEventLabel().toString().toLowerCase(), MouseEvent.CLICK
			.getEventLabel().toString().toLowerCase());
		tabs.setEvent(new EventLabelOptions(MouseEvent.DBLCLICK));
		assertEquals(tabs.getEvent().getEventLabel().toString().toLowerCase(), MouseEvent.DBLCLICK
			.getEventLabel().toString().toLowerCase());
	}
	
	/**
	 * Test method for {@link org.odlabs.wiquery.ui.tabs.Tabs#getHide()}.
	 */
	@Test
	public void testGetHide()
	{
		assertNull(tabs.getHide());
		tabs.setHide(new TabsAnimateOption(new EffectOptions()
				.setEffect("anEffect")
				.setEasing("swing")
				.setDelay(50)
		));
		assertNotNull(tabs.getHide());
		assertEquals(tabs.getHide().getJavascriptOption().toString(),
				"{effect: 'anEffect', easing: 'swing', delay: 50}");
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.ui.tabs.Tabs#getShow()}.
	 */
	@Test
	public void testGetShow()
	{
		assertNull(tabs.getShow());
		tabs.setShow(new TabsAnimateOption(new EffectOptions()
				.setEffect("anEffect")
				.setDuration(200)
		));
		assertNotNull(tabs.getShow());
		assertEquals(tabs.getShow().getJavascriptOption().toString(),
				"{effect: 'anEffect', duration: 200}");
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.ui.tabs.Tabs#getOptions()}.
	 */
	@Test
	public void testGetOptions()
	{
		assertNotNull(tabs.getOptions());
		assertEquals(tabs.getOptions().getJavaScriptOptions().toString(), "{}");
		tabs.setDisabled(true);
		assertEquals(tabs.getOptions().getJavaScriptOptions().toString(), "{disabled: true}");
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.ui.tabs.Tabs#isCollapsible()}.
	 */
	@Test
	public void testIsCollapsible()
	{
		assertFalse(tabs.isCollapsible());
		tabs.setCollapsible(true);
		assertTrue(tabs.isCollapsible());
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.ui.slider.Slider#isDisabled()}.
	 */
	@Test
	public void testIsDisabled()
	{
		assertFalse(tabs.isDisabled());
		tabs.setDisabled(true);
		assertTrue(tabs.isDisabled());
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.ui.tabs.Tabs#load(int)}.
	 */
	@Test
	public void testLoadInt()
	{
		assertNotNull(tabs.load(5));
		assertEquals(tabs.load(5).render().toString(), "$('#anId').tabs('load', 5);");
	}

	/**
	 * Test method for
	 * {@link org.odlabs.wiquery.ui.tabs.Tabs#setBeforeLoadEvent(org.odlabs.wiquery.ui.core.JsScopeUiEvent)}
	 * .
	 */
	@Test
	public void testSetBeforeLoadEvent()
	{
		assertEquals(tabs.statement().render().toString(), "$('#anId').tabs({});");
		tabs.setBeforeLoadEvent(JsScopeUiEvent.quickScope("alert('event');"));
		assertEquals(tabs.statement().render().toString(),
			"$('#anId').tabs({beforeLoad: function(event, ui) {\n\talert('event');\n}});");
	}

	/**
	 * Test method for
	 * {@link org.odlabs.wiquery.ui.tabs.Tabs#setLoadEvent(org.odlabs.wiquery.ui.core.JsScopeUiEvent)}
	 * .
	 */
	@Test
	public void testSetLoadEvent()
	{
		assertEquals(tabs.statement().render().toString(), "$('#anId').tabs({});");
		tabs.setLoadEvent(JsScopeUiEvent.quickScope("alert('event');"));
		assertEquals(tabs.statement().render().toString(),
			"$('#anId').tabs({load: function(event, ui) {\n\talert('event');\n}});");
	}

	/**
	 * Test method for
	 * {@link org.odlabs.wiquery.ui.tabs.Tabs#setBeforeActivateEvent(org.odlabs.wiquery.ui.core.JsScopeUiEvent)}
	 * .
	 */
	@Test
	public void testSetBeforeActivateEvent()
	{
		assertEquals(tabs.statement().render().toString(), "$('#anId').tabs({});");
		tabs.setBeforeActivateEvent(JsScopeUiEvent.quickScope("alert('event');"));
		assertEquals(tabs.statement().render().toString(),
			"$('#anId').tabs({beforeActivate: function(event, ui) {\n\talert('event');\n}});");
	}

	/**
	 * Test method for
	 * {@link org.odlabs.wiquery.ui.tabs.Tabs#setActivateEvent(org.odlabs.wiquery.ui.core.JsScopeUiEvent)}
	 * .
	 */
	@Test
	public void testSetActivateEvent()
	{
		assertEquals(tabs.statement().render().toString(), "$('#anId').tabs({});");
		tabs.setActivateEvent(JsScopeUiEvent.quickScope("alert('event');"));
		assertEquals(tabs.statement().render().toString(),
			"$('#anId').tabs({activate: function(event, ui) {\n\talert('event');\n}});");
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.ui.tabs.Tabs#statement()}.
	 */
	@Test
	public void testStatement()
	{
		assertNotNull(tabs.statement());
		assertEquals(tabs.statement().render().toString(), "$('#anId').tabs({});");
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.ui.tabs.Tabs#widget()}.
	 */
	@Test
	public void testWidget()
	{
		assertNotNull(tabs.widget());
		assertEquals(tabs.widget().render().toString(), "$('#anId').tabs('widget');");
	}
}

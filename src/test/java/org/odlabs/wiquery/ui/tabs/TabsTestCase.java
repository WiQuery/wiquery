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

import junit.framework.TestCase;

import org.apache.wicket.Page;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.util.tester.WicketTester;
import org.odlabs.wiquery.core.ajax.JQueryAjaxOption;
import org.odlabs.wiquery.core.events.MouseEvent;
import org.odlabs.wiquery.core.jqueryplugins.JQueryCookieOption;
import org.odlabs.wiquery.core.options.ArrayItemOptions;
import org.odlabs.wiquery.core.options.EventLabelOptions;
import org.odlabs.wiquery.core.options.IListItemOption;
import org.odlabs.wiquery.core.options.IntegerItemOptions;
import org.odlabs.wiquery.core.options.ListItemOptions;
import org.odlabs.wiquery.ui.core.JsScopeUiEvent;
import org.junit.Assert;
import org.junit.Test;

/**
 * Test on {@link Tabs}
 * @author Julien Roche
 *
 */
public class TabsTestCase extends TestCase {
	// Properties
	private Tabs tabs;

	/**
	 * @throws java.lang.Exception
	 */
	public void setUp() throws Exception {
		new WicketTester(new WebApplication() {
			@Override
			public Class<? extends Page> getHomePage() {
				return null;
			}
		});
		
		tabs = new Tabs("anId");
		tabs.setMarkupId(tabs.getId());
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.ui.tabs.Tabs#abort()}.
	 */
	@Test
	public void testAbort() {
		Assert.assertNotNull(tabs.abort());
		Assert.assertEquals(tabs.abort().render().toString(), 
				"$('#anId').tabs('abort');");
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.ui.tabs.Tabs#add(int, java.lang.String, org.apache.wicket.Component)}.
	 */
	@Test
	public void testAddIntStringComponent() {
		WebMarkupContainer container = new WebMarkupContainer("tabToAdd");
		container.setMarkupId(container.getId());
		Assert.assertNotNull(tabs.add(5, "a title", container));
		Assert.assertEquals(tabs.add(5, "a title", container).render().toString(), 
				"$('#anId').tabs('add', '#tabToAdd', 'a title', 5);");
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.ui.tabs.Tabs#add(java.lang.String, org.apache.wicket.Component)}.
	 */
	@Test
	public void testAddStringComponent() {
		WebMarkupContainer container = new WebMarkupContainer("tabToAdd");
		container.setMarkupId(container.getId());
		Assert.assertNotNull(tabs.add("a title", container));
		Assert.assertEquals(tabs.add("a title", container).render().toString(), 
				"$('#anId').tabs('add', '#tabToAdd', 'a title');");
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.ui.tabs.Tabs#add(java.lang.String, java.lang.String)}.
	 */
	@Test
	public void testAddStringString() {
		Assert.assertNotNull(tabs.add("an url", "a label"));
		Assert.assertEquals(tabs.add("an url", "a label").render().toString(), 
				"$('#anId').tabs('add', 'an url', 'a label');");
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.ui.tabs.Tabs#add(java.lang.String, java.lang.String, int)}.
	 */
	@Test
	public void testAddStringStringInt() {
		Assert.assertNotNull(tabs.add("an url", "a label", 5));
		Assert.assertEquals(tabs.add("an url", "a label", 5).render().toString(), 
				"$('#anId').tabs('add', 'an url', 'a label', 5);");
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.ui.tabs.Tabs#destroy()}.
	 */
	@Test
	public void testDestroy() {
		Assert.assertNotNull(tabs.destroy());
		Assert.assertEquals(tabs.destroy().render().toString(), 
				"$('#anId').tabs('destroy');");
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.ui.tabs.Tabs#disable()}.
	 */
	@Test
	public void testDisable() {
		Assert.assertNotNull(tabs.disable());
		Assert.assertEquals(tabs.disable().render().toString(), 
				"$('#anId').tabs('disable');");
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.ui.tabs.Tabs#disable(int)}.
	 */
	@Test
	public void testDisableInt() {
		Assert.assertNotNull(tabs.disable(5));
		Assert.assertEquals(tabs.disable(5).render().toString(), 
				"$('#anId').tabs('disable', 5);");
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.ui.tabs.Tabs#enable()}.
	 */
	@Test
	public void testEnable() {
		Assert.assertNotNull(tabs.enable());
		Assert.assertEquals(tabs.enable().render().toString(), 
				"$('#anId').tabs('enable');");
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.ui.tabs.Tabs#enable(int)}.
	 */
	@Test
	public void testEnableInt() {
		Assert.assertNotNull(tabs.enable(5));
		Assert.assertEquals(tabs.enable(5).render().toString(), 
				"$('#anId').tabs('enable', 5);");
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.ui.tabs.Tabs#getAjaxOptions()}.
	 */
	@Test
	public void testGetAjaxOptions() {
		Assert.assertNull(tabs.getAjaxOptions());
		JQueryAjaxOption ajaxOption = new JQueryAjaxOption();
		ajaxOption.setAsync(true);
		tabs.setAjaxOptions(ajaxOption);
		Assert.assertNotNull(tabs.getAjaxOptions());
		Assert.assertEquals(tabs.getAjaxOptions().getJavascriptOption().toString(), "{async: true}");
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.ui.tabs.Tabs#getCookie()}.
	 */
	@Test
	public void testGetCookie() {
		Assert.assertNull(tabs.getCookie());
		JQueryCookieOption cookieOption = new JQueryCookieOption("aName");
		cookieOption.setSecure(true);
		tabs.setCookie(cookieOption);
		Assert.assertNotNull(tabs.getCookie());
		Assert.assertEquals(tabs.getCookie().getJavascriptOption().toString(), "{secure: true}");
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.ui.tabs.Tabs#getDefaultSelectedTabIndex()}.
	 */
	@Test
	public void testGetDefaultSelectedTabIndex() {
		Assert.assertEquals(tabs.getDefaultSelectedTabIndex(), 0);
		tabs.setDefaultSelectedTabIndex(1);
		Assert.assertEquals(tabs.getDefaultSelectedTabIndex(), 1);
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.ui.tabs.Tabs#getDisabled()}.
	 */
	@Test
	public void testGetDisabled() {
		Assert.assertNull(tabs.getDisabled());
		ArrayItemOptions<IntegerItemOptions> array = new ArrayItemOptions<IntegerItemOptions>();
		array.add(new IntegerItemOptions(1));
		tabs.setDisabled(array);
		Assert.assertNotNull(tabs.getDisabled());
		Assert.assertEquals(tabs.getDisabled().getJavascriptOption().toString(), "[1]");
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.ui.tabs.Tabs#getEvent()}.
	 */
	@Test
	public void testGetEvent() {
		Assert.assertNotNull(tabs.getEvent());
		Assert.assertEquals(tabs.getEvent().getEventLabel().toString().toLowerCase(), 
				MouseEvent.CLICK.getEventLabel().toString().toLowerCase());
		tabs.setEvent(new EventLabelOptions(MouseEvent.DBLCLICK));
		Assert.assertEquals(tabs.getEvent().getEventLabel().toString().toLowerCase(), 
				MouseEvent.DBLCLICK.getEventLabel().toString().toLowerCase());
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.ui.tabs.Tabs#getFx()}.
	 */
	@Test
	public void testGetFx() {
		Assert.assertNull(tabs.getFx());
		ListItemOptions<IListItemOption> fx = new ListItemOptions<IListItemOption>();
		fx.add(new IListItemOption() {
			private static final long serialVersionUID = 1L;

			public CharSequence getJavascriptOption() {
				return "opacity: 'toggle'";
			}
		});
		tabs.setFx(fx);
		Assert.assertNotNull(tabs.getFx());
		Assert.assertEquals(tabs.getFx().getJavascriptOption().toString(), "{opacity: 'toggle'}");
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.ui.tabs.Tabs#getIdPrefix()}.
	 */
	@Test
	public void testGetIdPrefix() {
		Assert.assertEquals(tabs.getIdPrefix(), "ui-tabs-");
		tabs.setIdPrefix("tabs-");
		Assert.assertEquals(tabs.getIdPrefix(), "tabs-");
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.ui.tabs.Tabs#getOptions()}.
	 */
	@Test
	public void testGetOptions() {
		Assert.assertNotNull(tabs.getOptions());
		Assert.assertEquals(tabs.getOptions().getJavaScriptOptions().toString(), "{}");
		tabs.setCache(true);
		Assert.assertEquals(tabs.getOptions().getJavaScriptOptions().toString(), "{cache: true}");
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.ui.tabs.Tabs#getPanelTemplate()}.
	 */
	@Test
	public void testGetPanelTemplate() {
		Assert.assertEquals(tabs.getPanelTemplate(), "<div></div>");
		tabs.setPanelTemplate("<span></span>");
		Assert.assertEquals(tabs.getPanelTemplate(), "<span></span>");
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.ui.tabs.Tabs#getSpinner()}.
	 */
	@Test
	public void testGetSpinner() {
		Assert.assertEquals(tabs.getSpinner(), "<em>Loading&#8230;</em>");
		tabs.setSpinner("<em>Load</em>");
		Assert.assertEquals(tabs.getSpinner(), "<em>Load</em>");
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.ui.tabs.Tabs#getTabTemplate()}.
	 */
	@Test
	public void testGetTabTemplate() {
		Assert.assertEquals(tabs.getTabTemplate(), "<li><a href=\"#{href}\"><span>#{label}</span></a></li>");
		tabs.setTabTemplate("<span>#{label}</span>");
		Assert.assertEquals(tabs.getTabTemplate(), "<span>#{label}</span>");
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.ui.tabs.Tabs#isCache()}.
	 */
	@Test
	public void testIsCache() {
		Assert.assertFalse(tabs.isCache());
		tabs.setCache(true);
		Assert.assertTrue(tabs.isCache());
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.ui.tabs.Tabs#isCollapsible()}.
	 */
	@Test
	public void testIsCollapsible() {
		Assert.assertFalse(tabs.isCollapsible());
		tabs.setCollapsible(true);
		Assert.assertTrue(tabs.isCollapsible());
	}
	
	/**
	 * Test method for {@link org.odlabs.wiquery.ui.slider.Slider#isDisabled()}.
	 */
	@Test
	public void testIsDisabled() {
		Assert.assertFalse(tabs.isDisabled());
		tabs.setDisabled(true);
		Assert.assertTrue(tabs.isDisabled());
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.ui.slider.Slider#length()}.
	 */
	@Test
	public void testLength() {
		Assert.assertNotNull(tabs.length());
		Assert.assertEquals(tabs.length().render().toString(), 
				"$('#anId').tabs('length');");
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.ui.tabs.Tabs#load(int)}.
	 */
	@Test
	public void testLoadInt() {
		Assert.assertNotNull(tabs.load(5));
		Assert.assertEquals(tabs.load(5).render().toString(), 
				"$('#anId').tabs('load', 5);");
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.ui.tabs.Tabs#remove(int)}.
	 */
	@Test
	public void testRemoveInt() {
		Assert.assertNotNull(tabs.remove(5));
		Assert.assertEquals(tabs.remove(5).render().toString(), 
				"$('#anId').tabs('remove', 5);");
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.ui.tabs.Tabs#rotate(int)}.
	 */
	@Test
	public void testRotateInt() {
		Assert.assertNotNull(tabs.rotate(5));
		Assert.assertEquals(tabs.rotate(5).render().toString(), 
				"$('#anId').tabs('rotate', 5);");
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.ui.tabs.Tabs#rotate(int, boolean)}.
	 */
	@Test
	public void testRotateIntBoolean() {
		Assert.assertNotNull(tabs.rotate(5, true));
		Assert.assertEquals(tabs.rotate(5, true).render().toString(), 
				"$('#anId').tabs('rotate', 5, true);");
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.ui.tabs.Tabs#select(int)}.
	 */
	@Test
	public void testSelectInt() {
		Assert.assertNotNull(tabs.select(5));
		Assert.assertEquals(tabs.select(5).render().toString(), 
				"$('#anId').tabs('select', 5);");
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.ui.tabs.Tabs#setAddEvent(org.odlabs.wiquery.ui.core.JsScopeUiEvent)}.
	 */
	@Test
	public void testSetAddEvent() {
		Assert.assertEquals(tabs.statement().render().toString(),
			"$('#anId').tabs({});");
		tabs.setAddEvent(JsScopeUiEvent.quickScope("alert('event');"));
		Assert.assertEquals(tabs.statement().render().toString(), 
			"$('#anId').tabs({add: function(event, ui) {\n\talert('event');\n}});");
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.ui.tabs.Tabs#setDisableEvent(org.odlabs.wiquery.ui.core.JsScopeUiEvent)}.
	 */
	@Test
	public void testSetDisableEvent() {
		Assert.assertEquals(tabs.statement().render().toString(),
			"$('#anId').tabs({});");
		tabs.setDisableEvent(JsScopeUiEvent.quickScope("alert('event');"));
		Assert.assertEquals(tabs.statement().render().toString(), 
			"$('#anId').tabs({disable: function(event, ui) {\n\talert('event');\n}});");
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.ui.tabs.Tabs#setEnableEvent(org.odlabs.wiquery.ui.core.JsScopeUiEvent)}.
	 */
	@Test
	public void testSetEnableEvent() {
		Assert.assertEquals(tabs.statement().render().toString(),
			"$('#anId').tabs({});");
		tabs.setEnableEvent(JsScopeUiEvent.quickScope("alert('event');"));
		Assert.assertEquals(tabs.statement().render().toString(), 
			"$('#anId').tabs({enable: function(event, ui) {\n\talert('event');\n}});");
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.ui.tabs.Tabs#setLoadEvent(org.odlabs.wiquery.ui.core.JsScopeUiEvent)}.
	 */
	@Test
	public void testSetLoadEvent() {
		Assert.assertEquals(tabs.statement().render().toString(),
			"$('#anId').tabs({});");
		tabs.setLoadEvent(JsScopeUiEvent.quickScope("alert('event');"));
		Assert.assertEquals(tabs.statement().render().toString(), 
			"$('#anId').tabs({load: function(event, ui) {\n\talert('event');\n}});");
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.ui.tabs.Tabs#setRemoveEvent(org.odlabs.wiquery.ui.core.JsScopeUiEvent)}.
	 */
	@Test
	public void testSetRemoveEvent() {
		Assert.assertEquals(tabs.statement().render().toString(),
			"$('#anId').tabs({});");
		tabs.setRemoveEvent(JsScopeUiEvent.quickScope("alert('event');"));
		Assert.assertEquals(tabs.statement().render().toString(), 
			"$('#anId').tabs({remove: function(event, ui) {\n\talert('event');\n}});");
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.ui.tabs.Tabs#setSelectEvent(org.odlabs.wiquery.ui.core.JsScopeUiEvent)}.
	 */
	@Test
	public void testSetSelectEvent() {
		Assert.assertEquals(tabs.statement().render().toString(),
			"$('#anId').tabs({});");
		tabs.setSelectEvent(JsScopeUiEvent.quickScope("alert('event');"));
		Assert.assertEquals(tabs.statement().render().toString(), 
			"$('#anId').tabs({select: function(event, ui) {\n\talert('event');\n}});");
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.ui.tabs.Tabs#setShowEvent(org.odlabs.wiquery.ui.core.JsScopeUiEvent)}.
	 */
	@Test
	public void testSetShowEvent() {
		Assert.assertEquals(tabs.statement().render().toString(),
			"$('#anId').tabs({});");
		tabs.setShowEvent(JsScopeUiEvent.quickScope("alert('event');"));
		Assert.assertEquals(tabs.statement().render().toString(), 
			"$('#anId').tabs({show: function(event, ui) {\n\talert('event');\n}});");
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.ui.tabs.Tabs#statement()}.
	 */
	@Test
	public void testStatement() {
		Assert.assertNotNull(tabs.statement());
		Assert.assertEquals(tabs.statement().render().toString(), "$('#anId').tabs({});");
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.ui.tabs.Tabs#url(int, java.lang.String)}.
	 */
	@Test
	public void testUrlIntString() {
		Assert.assertNotNull(tabs.url(5, "a label"));
		Assert.assertEquals(tabs.url(5, "a label").render().toString(), 
				"$('#anId').tabs('url', 5, 'a label');");
	}
	
	/**
	 * Test method for {@link org.odlabs.wiquery.ui.tabs.Tabs#widget()}.
	 */
	@Test
	public void testWidget() {
		Assert.assertNotNull(tabs.widget());
		Assert.assertEquals(tabs.widget().render().toString(), 
				"$('#anId').tabs('widget');");
	}
}

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

import static org.junit.Assert.*;

import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.util.tester.ITestPanelSource;
import org.junit.Before;
import org.junit.Test;
import org.odlabs.wiquery.tester.WiQueryTestCase;
import org.odlabs.wiquery.ui.InputTestPanel;
import org.odlabs.wiquery.ui.core.JsScopeUiEvent;
import org.odlabs.wiquery.ui.position.PositionOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Test of {@link Autocomplete}
 * 
 * @author Julien Roche
 */
public class AutocompleteTestCase extends WiQueryTestCase
{
	private static final Logger log = LoggerFactory.getLogger(AutocompleteTestCase.class);

	private Autocomplete<String> autocomplete;

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
				Panel panel = new InputTestPanel(panelId);
				autocomplete = new Autocomplete<String>("anId");
				autocomplete.setMarkupId(autocomplete.getId());
				panel.add(autocomplete);
				return panel;
			}
		});
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.ui.autocomplete.Autocomplete#close()}.
	 */
	@Test
	public void testClose()
	{
		assertNotNull(autocomplete.close());
		assertEquals(autocomplete.close().render().toString(), "$('#anId').autocomplete('close');");
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.ui.autocomplete.Autocomplete#destroy()}.
	 */
	@Test
	public void testDestroy()
	{
		assertNotNull(autocomplete.destroy());
		assertEquals(autocomplete.destroy().render().toString(),
			"$('#anId').autocomplete('destroy');");
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.ui.autocomplete.Autocomplete#disable()}.
	 */
	@Test
	public void testDisable()
	{
		assertNotNull(autocomplete.disable());
		assertEquals(autocomplete.disable().render().toString(),
			"$('#anId').autocomplete('disable');");
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.ui.autocomplete.Autocomplete#enable()}.
	 */
	@Test
	public void testEnable()
	{
		assertNotNull(autocomplete.enable());
		assertEquals(autocomplete.enable().render().toString(),
			"$('#anId').autocomplete('enable');");
	}

	/**
	 * Test method for
	 * {@link org.odlabs.wiquery.ui.autocomplete.Autocomplete#getAppendTo()}.
	 */
	@Test
	public void testGetAppendTo()
	{
		assertEquals(autocomplete.getAppendTo(), "body");
		autocomplete.setAppendTo("html > div.myClasss");
		assertEquals(autocomplete.getAppendTo(), "html > div.myClasss");
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.ui.autocomplete.Autocomplete#getDelay()}.
	 */
	@Test
	public void testGetDelay()
	{
		assertEquals(autocomplete.getDelay(), 300);
		autocomplete.setDelay(500);
		assertEquals(autocomplete.getDelay(), 500);
	}

	/**
	 * Test method for
	 * {@link org.odlabs.wiquery.ui.autocomplete.Autocomplete#getMinLength()}.
	 */
	@Test
	public void testGetMinLength()
	{
		assertEquals(autocomplete.getMinLength(), 1);
		autocomplete.setMinLength(2);
		assertEquals(autocomplete.getMinLength(), 2);
	}

	/**
	 * Test method for
	 * {@link org.odlabs.wiquery.ui.autocomplete.Autocomplete#getPosition()}.
	 */
	@Test
	public void testGetPosition()
	{
		assertNotNull(autocomplete.getPosition());
		assertEquals(autocomplete.getPosition().getJavascriptOption().toString(),
			"{at: 'left bottom', collision: 'none', my: 'left top'}");
		PositionOptions position = new PositionOptions();
		position.setBgiframe(true);
		autocomplete.setPosition(position);
		assertNotNull(autocomplete.getPosition());
		assertEquals(autocomplete.getPosition().getJavascriptOption().toString(),
			"{bgiframe: true}");
	}

	/**
	 * Test method for
	 * {@link org.odlabs.wiquery.ui.autocomplete.Autocomplete#getOptions()}.
	 */
	@Test
	public void testGetOptions()
	{
		assertNotNull(autocomplete.getOptions());
		assertEquals(autocomplete.getOptions().getJavaScriptOptions().toString(), "{}");
		autocomplete.setDelay(5);
		assertEquals(autocomplete.getOptions().getJavaScriptOptions().toString(), "{delay: 5}");
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.ui.autocomplete.Autocomplete#getSource()}
	 * .
	 */
	@Test
	public void testGetSource()
	{
		assertNull(autocomplete.getSource());
		autocomplete.setSource(new AutocompleteSource("http://localhost:8080/url.jsp"));
		assertNotNull(autocomplete.getSource());
		assertEquals(autocomplete.getSource().getJavascriptOption().toString(),
			"'http://localhost:8080/url.jsp'");
	}

	/**
	 * Test method for
	 * {@link org.odlabs.wiquery.ui.autocomplete.Autocomplete#isDisabled()}.
	 */
	@Test
	public void testIsDisabled()
	{
		assertFalse(autocomplete.isDisabled());
		autocomplete.setDisabled(true);
		assertTrue(autocomplete.isDisabled());
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.ui.autocomplete.Autocomplete#search()}.
	 */
	@Test
	public void testSearch()
	{
		assertNotNull(autocomplete.search());
		assertEquals(autocomplete.search().render().toString(),
			"$('#anId').autocomplete('search');");
	}

	/**
	 * Test method for
	 * {@link org.odlabs.wiquery.ui.autocomplete.Autocomplete#search(java.lang.String)} .
	 */
	@Test
	public void testSearchString()
	{
		assertNotNull(autocomplete.search());
		assertEquals(autocomplete.search("a").render().toString(),
			"$('#anId').autocomplete('search', 'a');");
	}

	/**
	 * Test method for
	 * {@link org.odlabs.wiquery.ui.autocomplete.Autocomplete#setChangeEvent(org.odlabs.wiquery.ui.core.JsScopeUiEvent)}
	 * .
	 */
	@Test
	public void testSetChangeEvent()
	{
		assertEquals(autocomplete.statement().render().toString(), "$('#anId').autocomplete({});");
		autocomplete.setChangeEvent(JsScopeUiEvent.quickScope("alert('event');"));
		assertEquals(autocomplete.statement().render().toString(),
			"$('#anId').autocomplete({change: function(event, ui) {\n\talert('event');\n}});");
	}

	/**
	 * Test method for
	 * {@link org.odlabs.wiquery.ui.autocomplete.Autocomplete#setCloseEvent(org.odlabs.wiquery.ui.core.JsScopeUiEvent)}
	 * .
	 */
	@Test
	public void testSetCloseEvent()
	{
		assertEquals(autocomplete.statement().render().toString(), "$('#anId').autocomplete({});");
		autocomplete.setCloseEvent(JsScopeUiEvent.quickScope("alert('event');"));
		assertEquals(autocomplete.statement().render().toString(),
			"$('#anId').autocomplete({close: function(event, ui) {\n\talert('event');\n}});");
	}

	/**
	 * Test method for
	 * {@link org.odlabs.wiquery.ui.autocomplete.Autocomplete#setFocusEvent(org.odlabs.wiquery.ui.core.JsScopeUiEvent)}
	 * .
	 */
	@Test
	public void testSetFocusEvent()
	{
		assertEquals(autocomplete.statement().render().toString(), "$('#anId').autocomplete({});");
		autocomplete.setFocusEvent(JsScopeUiEvent.quickScope("alert('event');"));
		assertEquals(autocomplete.statement().render().toString(),
			"$('#anId').autocomplete({focus: function(event, ui) {\n\talert('event');\n}});");
	}

	/**
	 * Test method for {@link
	 * org.odlabs.wiquery.ui.autocomplete.Autocomplete#(org.odlabs.wiquery.ui.
	 * core.JsScopeUiEvent)}.
	 */
	@Test
	public void testSetOpenEvent()
	{
		assertEquals(autocomplete.statement().render().toString(), "$('#anId').autocomplete({});");
		autocomplete.setOpenEvent(JsScopeUiEvent.quickScope("alert('event');"));
		assertEquals(autocomplete.statement().render().toString(),
			"$('#anId').autocomplete({open: function(event, ui) {\n\talert('event');\n}});");
	}

	/**
	 * Test method for
	 * {@link org.odlabs.wiquery.ui.autocomplete.Autocomplete#setSearchEvent(org.odlabs.wiquery.ui.core.JsScopeUiEvent)}
	 * .
	 */
	@Test
	public void testSetSearchEvent()
	{
		assertEquals(autocomplete.statement().render().toString(), "$('#anId').autocomplete({});");
		autocomplete.setSearchEvent(JsScopeUiEvent.quickScope("alert('event');"));
		assertEquals(autocomplete.statement().render().toString(),
			"$('#anId').autocomplete({search: function(event, ui) {\n\talert('event');\n}});");
	}

	/**
	 * Test method for
	 * {@link org.odlabs.wiquery.ui.autocomplete.Autocomplete#setSelectEvent(org.odlabs.wiquery.ui.core.JsScopeUiEvent)}
	 * .
	 */
	@Test
	public void testSetSelectEvent()
	{
		assertEquals(autocomplete.statement().render().toString(), "$('#anId').autocomplete({});");
		autocomplete.setSelectEvent(JsScopeUiEvent.quickScope("alert('event');"));
		assertEquals(autocomplete.statement().render().toString(),
			"$('#anId').autocomplete({select: function(event, ui) {\n\talert('event');\n}});");
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.ui.autocomplete.Autocomplete#statement()}
	 * .
	 */
	@Test
	public void testStatement()
	{
		assertNotNull(autocomplete.statement());
		assertEquals(autocomplete.statement().render().toString(), "$('#anId').autocomplete({});");
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.ui.autocomplete.Autocomplete#widget()}.
	 */
	@Test
	public void testWidget()
	{
		assertNotNull(autocomplete.widget());
		assertEquals(autocomplete.widget().render().toString(),
			"$('#anId').autocomplete('widget');");
	}

	@Override
	protected Logger getLog()
	{
		return log;
	}
}

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

import junit.framework.TestCase;

import org.apache.wicket.Page;
import org.apache.wicket.util.tester.WicketTester;
import org.odlabs.wiquery.ui.core.JsScopeUiEvent;
import org.odlabs.wiquery.ui.position.PositionOptions;
import org.odlabs.wiquery.utils.WiQueryWebApplication;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Test of {@link Autocomplete}
 * @author Julien Roche
 *
 */
public class AutocompleteTestCase extends TestCase {
	// Properties
	private Autocomplete<String> autocomplete;
	
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
		
		autocomplete = new Autocomplete<String>("anId");
		autocomplete.setMarkupId(autocomplete.getId());
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.ui.autocomplete.Autocomplete#close()}.
	 */
	@Test
	public void testClose() {
		Assert.assertNotNull(autocomplete.close());
		Assert.assertEquals(autocomplete.close().render().toString(), 
				"$('#anId').autocomplete('close');");
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.ui.autocomplete.Autocomplete#destroy()}.
	 */
	@Test
	public void testDestroy() {
		Assert.assertNotNull(autocomplete.destroy());
		Assert.assertEquals(autocomplete.destroy().render().toString(), 
				"$('#anId').autocomplete('destroy');");
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.ui.autocomplete.Autocomplete#disable()}.
	 */
	@Test
	public void testDisable() {
		Assert.assertNotNull(autocomplete.disable());
		Assert.assertEquals(autocomplete.disable().render().toString(), 
				"$('#anId').autocomplete('disable');");
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.ui.autocomplete.Autocomplete#enable()}.
	 */
	@Test
	public void testEnable() {
		Assert.assertNotNull(autocomplete.enable());
		Assert.assertEquals(autocomplete.enable().render().toString(), 
				"$('#anId').autocomplete('enable');");
	}
	
	/**
	 * Test method for {@link org.odlabs.wiquery.ui.autocomplete.Autocomplete#getAppendTo()}.
	 */
	@Test
	public void testGetAppendTo() {
		Assert.assertEquals(autocomplete.getAppendTo(), "body");
		autocomplete.setAppendTo("html > div.myClasss");
		Assert.assertEquals(autocomplete.getAppendTo(), "html > div.myClasss");
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.ui.autocomplete.Autocomplete#getDelay()}.
	 */
	@Test
	public void testGetDelay() {
		Assert.assertEquals(autocomplete.getDelay(), 300);
		autocomplete.setDelay(500);
		Assert.assertEquals(autocomplete.getDelay(), 500);
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.ui.autocomplete.Autocomplete#getMinLength()}.
	 */
	@Test
	public void testGetMinLength() {
		Assert.assertEquals(autocomplete.getMinLength(), 1);
		autocomplete.setMinLength(2);
		Assert.assertEquals(autocomplete.getMinLength(), 2);
	}
	
	/**
	 * Test method for {@link org.odlabs.wiquery.ui.autocomplete.Autocomplete#getPosition()}.
	 */
	@Test
	public void testGetPosition() {
		Assert.assertNotNull(autocomplete.getPosition());
		Assert.assertEquals(autocomplete.getPosition().getJavascriptOption().toString(), "{at: 'left bottom', collision: 'none', my: 'left top'}");
		PositionOptions position = new PositionOptions();
		position.setBgiframe(true);
		autocomplete.setPosition(position);
		Assert.assertNotNull(autocomplete.getPosition());
		Assert.assertEquals(autocomplete.getPosition().getJavascriptOption().toString(), "{bgiframe: true}");
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.ui.autocomplete.Autocomplete#getOptions()}.
	 */
	@Test
	public void testGetOptions() {
		Assert.assertNotNull(autocomplete.getOptions());
		Assert.assertEquals(autocomplete.getOptions().getJavaScriptOptions().toString(), "{}");
		autocomplete.setDelay(5);
		Assert.assertEquals(autocomplete.getOptions().getJavaScriptOptions().toString(), "{delay: 5}");
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.ui.autocomplete.Autocomplete#getSource()}.
	 */
	@Test
	public void testGetSource() {
		Assert.assertNull(autocomplete.getSource());
		autocomplete.setSource(new AutocompleteSource("http://localhost:8080/url.jsp"));
		Assert.assertNotNull(autocomplete.getSource());
		Assert.assertEquals(autocomplete.getSource().getJavascriptOption().toString(), "'http://localhost:8080/url.jsp'");
	}
	
	/**
	 * Test method for {@link org.odlabs.wiquery.ui.autocomplete.Autocomplete#isDisabled()}.
	 */
	@Test
	public void testIsDisabled() {
		Assert.assertFalse(autocomplete.isDisabled());
		autocomplete.setDisabled(true);
		Assert.assertTrue(autocomplete.isDisabled());
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.ui.autocomplete.Autocomplete#search()}.
	 */
	@Test
	public void testSearch() {
		Assert.assertNotNull(autocomplete.search());
		Assert.assertEquals(autocomplete.search().render().toString(), 
				"$('#anId').autocomplete('search');");
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.ui.autocomplete.Autocomplete#search(java.lang.String)}.
	 */
	@Test
	public void testSearchString() {
		Assert.assertNotNull(autocomplete.search());
		Assert.assertEquals(autocomplete.search("a").render().toString(), 
				"$('#anId').autocomplete('search', 'a');");
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.ui.autocomplete.Autocomplete#setChangeEvent(org.odlabs.wiquery.ui.core.JsScopeUiEvent)}.
	 */
	@Test
	public void testSetChangeEvent() {
		Assert.assertEquals(autocomplete.statement().render().toString(), 
			"$('#anId').autocomplete({});");
		autocomplete.setChangeEvent(JsScopeUiEvent.quickScope("alert('event');"));
		Assert.assertEquals(autocomplete.statement().render().toString(), 
			"$('#anId').autocomplete({change: function(event, ui) {\n\talert('event');\n}});");
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.ui.autocomplete.Autocomplete#setCloseEvent(org.odlabs.wiquery.ui.core.JsScopeUiEvent)}.
	 */
	@Test
	public void testSetCloseEvent() {
		Assert.assertEquals(autocomplete.statement().render().toString(), 
			"$('#anId').autocomplete({});");
		autocomplete.setCloseEvent(JsScopeUiEvent.quickScope("alert('event');"));
		Assert.assertEquals(autocomplete.statement().render().toString(), 
			"$('#anId').autocomplete({close: function(event, ui) {\n\talert('event');\n}});");
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.ui.autocomplete.Autocomplete#setFocusEvent(org.odlabs.wiquery.ui.core.JsScopeUiEvent)}.
	 */
	@Test
	public void testSetFocusEvent() {
		Assert.assertEquals(autocomplete.statement().render().toString(), 
			"$('#anId').autocomplete({});");
		autocomplete.setFocusEvent(JsScopeUiEvent.quickScope("alert('event');"));
		Assert.assertEquals(autocomplete.statement().render().toString(), 
			"$('#anId').autocomplete({focus: function(event, ui) {\n\talert('event');\n}});");
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.ui.autocomplete.Autocomplete#(org.odlabs.wiquery.ui.core.JsScopeUiEvent)}.
	 */
	@Test
	public void testSetOpenEvent() {
		Assert.assertEquals(autocomplete.statement().render().toString(), 
			"$('#anId').autocomplete({});");
		autocomplete.setOpenEvent(JsScopeUiEvent.quickScope("alert('event');"));
		Assert.assertEquals(autocomplete.statement().render().toString(), 
			"$('#anId').autocomplete({open: function(event, ui) {\n\talert('event');\n}});");
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.ui.autocomplete.Autocomplete#setSearchEvent(org.odlabs.wiquery.ui.core.JsScopeUiEvent)}.
	 */
	@Test
	public void testSetSearchEvent() {
		Assert.assertEquals(autocomplete.statement().render().toString(), 
			"$('#anId').autocomplete({});");
		autocomplete.setSearchEvent(JsScopeUiEvent.quickScope("alert('event');"));
		Assert.assertEquals(autocomplete.statement().render().toString(), 
			"$('#anId').autocomplete({search: function(event, ui) {\n\talert('event');\n}});");
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.ui.autocomplete.Autocomplete#setSelectEvent(org.odlabs.wiquery.ui.core.JsScopeUiEvent)}.
	 */
	@Test
	public void testSetSelectEvent() {
		Assert.assertEquals(autocomplete.statement().render().toString(), 
			"$('#anId').autocomplete({});");
		autocomplete.setSelectEvent(JsScopeUiEvent.quickScope("alert('event');"));
		Assert.assertEquals(autocomplete.statement().render().toString(), 
			"$('#anId').autocomplete({select: function(event, ui) {\n\talert('event');\n}});");
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.ui.autocomplete.Autocomplete#statement()}.
	 */
	@Test
	public void testStatement() {
		Assert.assertNotNull(autocomplete.statement());
		Assert.assertEquals(autocomplete.statement().render().toString(), "$('#anId').autocomplete({});");
	}
	
	/**
	 * Test method for {@link org.odlabs.wiquery.ui.autocomplete.Autocomplete#widget()}.
	 */
	@Test
	public void testWidget() {
		Assert.assertNotNull(autocomplete.widget());
		Assert.assertEquals(autocomplete.widget().render().toString(), 
				"$('#anId').autocomplete('widget');");
	}
}

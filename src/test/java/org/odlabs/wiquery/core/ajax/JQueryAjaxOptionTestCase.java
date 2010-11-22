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
package org.odlabs.wiquery.core.ajax;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.odlabs.wiquery.core.ajax.JQueryAjaxOption.AjaxType;
import org.odlabs.wiquery.tester.WiQueryTestCase;

/**
 * Test on {@link JQueryAjaxOption}
 * 
 * @author Julien Roche
 */
public class JQueryAjaxOptionTestCase extends WiQueryTestCase {
	// Properties
	private JQueryAjaxOption jQueryAjaxOption;

	@Override
	@Before
	public void setUp() {
		super.setUp();
		jQueryAjaxOption = new JQueryAjaxOption();
	}

	/**
	 * Test method for
	 * {@link org.odlabs.wiquery.core.ajax.JQueryAjaxOption#getContentType()}.
	 */
	@Test
	public void testGetContentType() {
		assertEquals(jQueryAjaxOption.getContentType(),
				"application/x-www-form-urlencoded");
		jQueryAjaxOption.setContentType("text/css");
		assertNotNull(jQueryAjaxOption.getContentType());
		assertEquals("text/css", jQueryAjaxOption.getContentType());
	}

	/**
	 * Test method for
	 * {@link org.odlabs.wiquery.core.ajax.JQueryAjaxOption#getData()}.
	 */
	@Test
	public void testGetData() {
		assertNull(jQueryAjaxOption.getData());
		jQueryAjaxOption.setData("{foo:['bar1', 'bar2']}");
		assertNotNull(jQueryAjaxOption.getData());
		assertEquals("{foo:['bar1', 'bar2']}", jQueryAjaxOption.getData());
	}

	/**
	 * Test method for
	 * {@link org.odlabs.wiquery.core.ajax.JQueryAjaxOption#getDataType()}.
	 */
	@Test
	public void testGetDataType() {
		assertNull(jQueryAjaxOption.getDataType());
		jQueryAjaxOption.setDataType("xml");
		assertNotNull(jQueryAjaxOption.getDataType());
		assertEquals("xml", jQueryAjaxOption.getDataType());
	}

	/**
	 * Test method for
	 * {@link org.odlabs.wiquery.core.ajax.JQueryAjaxOption#getJavascriptOption()}
	 * .
	 */
	@Test
	public void testGetJavascriptOption() {
		assertEquals("{}", jQueryAjaxOption.getJavascriptOption().toString());
		jQueryAjaxOption.setDataType("xml");
		assertEquals("{dataType: 'xml'}", jQueryAjaxOption
				.getJavascriptOption().toString());
	}

	/**
	 * Test method for
	 * {@link org.odlabs.wiquery.core.ajax.JQueryAjaxOption#getJsonp()}.
	 */
	@Test
	public void testGetJsonp() {
		assertNull(jQueryAjaxOption.getJsonp());
		jQueryAjaxOption.setJsonp("{jsonp:'onJsonPLoad'}");
		assertNotNull(jQueryAjaxOption.getJsonp());
		assertEquals(jQueryAjaxOption.getJsonp(), "{jsonp:'onJsonPLoad'}");
	}

	/**
	 * Test method for
	 * {@link org.odlabs.wiquery.core.ajax.JQueryAjaxOption#getPassword()}.
	 */
	@Test
	public void testGetPassword() {
		assertNull(jQueryAjaxOption.getPassword());
		jQueryAjaxOption.setPassword("a password");
		assertNotNull(jQueryAjaxOption.getPassword());
		assertEquals(jQueryAjaxOption.getPassword(), "a password");
	}

	/**
	 * Test method for
	 * {@link org.odlabs.wiquery.core.ajax.JQueryAjaxOption#getScriptCharset()}.
	 */
	@Test
	public void testGetScriptCharset() {
		assertNull(jQueryAjaxOption.getScriptCharset());
		jQueryAjaxOption.setScriptCharset("text/html");
		assertNotNull(jQueryAjaxOption.getScriptCharset());
		assertEquals(jQueryAjaxOption.getScriptCharset(), "text/html");
	}

	/**
	 * Test method for
	 * {@link org.odlabs.wiquery.core.ajax.JQueryAjaxOption#getTimeout()}.
	 */
	@Test
	public void testGetTimeout() {
		assertEquals(jQueryAjaxOption.getTimeout(), 0);
		jQueryAjaxOption.setTimeout(15);
		assertNotNull(jQueryAjaxOption.getTimeout());
		assertEquals(jQueryAjaxOption.getTimeout(), 15);
	}

	/**
	 * Test method for
	 * {@link org.odlabs.wiquery.core.ajax.JQueryAjaxOption#getType}.
	 */
	@Test
	public void testGetType() {
		assertEquals(jQueryAjaxOption.getType(), AjaxType.GET);
		jQueryAjaxOption.setType(AjaxType.POST);
		assertEquals(jQueryAjaxOption.getType(), AjaxType.POST);
	}

	/**
	 * Test method for
	 * {@link org.odlabs.wiquery.core.ajax.JQueryAjaxOption#getUrl()}.
	 */
	@Test
	public void testGetUrl() {
		assertNull(jQueryAjaxOption.getUrl());
		jQueryAjaxOption.setUrl("http://an.url");
		assertNotNull(jQueryAjaxOption.getUrl());
		assertEquals(jQueryAjaxOption.getUrl(), "http://an.url");
	}

	/**
	 * Test method for
	 * {@link org.odlabs.wiquery.core.ajax.JQueryAjaxOption#getUsername()}.
	 */
	@Test
	public void testGetUsername() {
		assertNull(jQueryAjaxOption.getUsername());
		jQueryAjaxOption.setUsername("user");
		assertNotNull(jQueryAjaxOption.getUsername());
		assertEquals(jQueryAjaxOption.getUsername(), "user");
	}

	/**
	 * Test method for
	 * {@link org.odlabs.wiquery.core.ajax.JQueryAjaxOption#isAsync()}.
	 */
	@Test
	public void testIsAsync() {
		assertTrue(jQueryAjaxOption.isAsync());
		jQueryAjaxOption.setAsync(false);
		assertFalse(jQueryAjaxOption.isAsync());
	}

	/**
	 * Test method for
	 * {@link org.odlabs.wiquery.core.ajax.JQueryAjaxOption#isCache()}.
	 */
	@Test
	public void testIsCache() {
		assertTrue(jQueryAjaxOption.isCache());
		jQueryAjaxOption.setCache(false);
		assertFalse(jQueryAjaxOption.isCache());
	}

	/**
	 * Test method for
	 * {@link org.odlabs.wiquery.core.ajax.JQueryAjaxOption#isGlobal()}.
	 */
	@Test
	public void testIsGlobal() {
		assertTrue(jQueryAjaxOption.isGlobal());
		jQueryAjaxOption.setGlobal(false);
		assertFalse(jQueryAjaxOption.isGlobal());
	}

	/**
	 * Test method for
	 * {@link org.odlabs.wiquery.core.ajax.JQueryAjaxOption#isIfModified()}.
	 */
	@Test
	public void testIsIfModified() {
		assertTrue(jQueryAjaxOption.isIfModified());
		jQueryAjaxOption.setIfModified(false);
		assertFalse(jQueryAjaxOption.isIfModified());
	}

	/**
	 * Test method for
	 * {@link org.odlabs.wiquery.core.ajax.JQueryAjaxOption#isProcessData()}.
	 */
	@Test
	public void testIsProcessData() {
		assertTrue(jQueryAjaxOption.isProcessData());
		jQueryAjaxOption.setProcessData(false);
		assertFalse(jQueryAjaxOption.isProcessData());
	}
}

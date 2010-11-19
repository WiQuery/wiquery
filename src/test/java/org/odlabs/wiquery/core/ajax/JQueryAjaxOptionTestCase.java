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

import junit.framework.Assert;
import junit.framework.TestCase;

import org.odlabs.wiquery.core.ajax.JQueryAjaxOption.AjaxType;
import org.junit.Test;

/**
 * Test on {@link JQueryAjaxOption}
 * @author Julien Roche
 *
 */
public class JQueryAjaxOptionTestCase extends TestCase {
	// Properties
	private JQueryAjaxOption jQueryAjaxOption;

	/**
	 * @throws java.lang.Exception
	 */
	@Override
	public void setUp() throws Exception {
		jQueryAjaxOption = new JQueryAjaxOption();
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.core.ajax.JQueryAjaxOption#getContentType()}.
	 */
	@Test
	public void testGetContentType() {
		Assert.assertEquals(jQueryAjaxOption.getContentType(), "application/x-www-form-urlencoded");
		jQueryAjaxOption.setContentType("text/css");
		Assert.assertNotNull(jQueryAjaxOption.getContentType());
		Assert.assertEquals("text/css", jQueryAjaxOption.getContentType());
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.core.ajax.JQueryAjaxOption#getData()}.
	 */
	@Test
	public void testGetData() {
		Assert.assertNull(jQueryAjaxOption.getData());
		jQueryAjaxOption.setData("{foo:['bar1', 'bar2']}");
		Assert.assertNotNull(jQueryAjaxOption.getData());
		Assert.assertEquals("{foo:['bar1', 'bar2']}", jQueryAjaxOption.getData());
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.core.ajax.JQueryAjaxOption#getDataType()}.
	 */
	@Test
	public void testGetDataType() {
		Assert.assertNull(jQueryAjaxOption.getDataType());
		jQueryAjaxOption.setDataType("xml");
		Assert.assertNotNull(jQueryAjaxOption.getDataType());
		Assert.assertEquals("xml", jQueryAjaxOption.getDataType());
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.core.ajax.JQueryAjaxOption#getJavascriptOption()}.
	 */
	@Test
	public void testGetJavascriptOption() {
		Assert.assertEquals("{}", jQueryAjaxOption.getJavascriptOption().toString());
		jQueryAjaxOption.setDataType("xml");
		Assert.assertEquals("{dataType: 'xml'}", jQueryAjaxOption.getJavascriptOption().toString());
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.core.ajax.JQueryAjaxOption#getJsonp()}.
	 */
	@Test
	public void testGetJsonp() {
		Assert.assertNull(jQueryAjaxOption.getJsonp());
		jQueryAjaxOption.setJsonp("{jsonp:'onJsonPLoad'}");
		Assert.assertNotNull(jQueryAjaxOption.getJsonp());
		Assert.assertEquals(jQueryAjaxOption.getJsonp(), "{jsonp:'onJsonPLoad'}");
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.core.ajax.JQueryAjaxOption#getPassword()}.
	 */
	@Test
	public void testGetPassword() {
		Assert.assertNull(jQueryAjaxOption.getPassword());
		jQueryAjaxOption.setPassword("a password");
		Assert.assertNotNull(jQueryAjaxOption.getPassword());
		Assert.assertEquals(jQueryAjaxOption.getPassword(), "a password");
	}
	
	/**
	 * Test method for {@link org.odlabs.wiquery.core.ajax.JQueryAjaxOption#getScriptCharset()}.
	 */
	@Test
	public void testGetScriptCharset() {
		Assert.assertNull(jQueryAjaxOption.getScriptCharset());
		jQueryAjaxOption.setScriptCharset("text/html");
		Assert.assertNotNull(jQueryAjaxOption.getScriptCharset());
		Assert.assertEquals(jQueryAjaxOption.getScriptCharset(), "text/html");
	}
	
	/**
	 * Test method for {@link org.odlabs.wiquery.core.ajax.JQueryAjaxOption#getTimeout()}.
	 */
	@Test
	public void testGetTimeout() {
		Assert.assertEquals(jQueryAjaxOption.getTimeout(), 0);
		jQueryAjaxOption.setTimeout(15);
		Assert.assertNotNull(jQueryAjaxOption.getTimeout());
		Assert.assertEquals(jQueryAjaxOption.getTimeout(), 15);
	}
	
	/**
	 * Test method for {@link org.odlabs.wiquery.core.ajax.JQueryAjaxOption#getType}.
	 */
	@Test
	public void testGetType() {
		Assert.assertEquals(jQueryAjaxOption.getType(), AjaxType.GET);
		jQueryAjaxOption.setType(AjaxType.POST);
		Assert.assertEquals(jQueryAjaxOption.getType(), AjaxType.POST);
	}
	
	/**
	 * Test method for {@link org.odlabs.wiquery.core.ajax.JQueryAjaxOption#getUrl()}.
	 */
	@Test
	public void testGetUrl() {
		Assert.assertNull(jQueryAjaxOption.getUrl());
		jQueryAjaxOption.setUrl("http://an.url");
		Assert.assertNotNull(jQueryAjaxOption.getUrl());
		Assert.assertEquals(jQueryAjaxOption.getUrl(), "http://an.url");
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.core.ajax.JQueryAjaxOption#getUsername()}.
	 */
	@Test
	public void testGetUsername() {
		Assert.assertNull(jQueryAjaxOption.getUsername());
		jQueryAjaxOption.setUsername("user");
		Assert.assertNotNull(jQueryAjaxOption.getUsername());
		Assert.assertEquals(jQueryAjaxOption.getUsername(), "user");
	}
	
	/**
	 * Test method for {@link org.odlabs.wiquery.core.ajax.JQueryAjaxOption#isAsync()}.
	 */
	@Test
	public void testIsAsync() {
		Assert.assertTrue(jQueryAjaxOption.isAsync());
		jQueryAjaxOption.setAsync(false);
		Assert.assertFalse(jQueryAjaxOption.isAsync());
	}
	
	/**
	 * Test method for {@link org.odlabs.wiquery.core.ajax.JQueryAjaxOption#isCache()}.
	 */
	@Test
	public void testIsCache() {
		Assert.assertTrue(jQueryAjaxOption.isCache());
		jQueryAjaxOption.setCache(false);
		Assert.assertFalse(jQueryAjaxOption.isCache());
	}
	
	/**
	 * Test method for {@link org.odlabs.wiquery.core.ajax.JQueryAjaxOption#isGlobal()}.
	 */
	@Test
	public void testIsGlobal() {
		Assert.assertTrue(jQueryAjaxOption.isGlobal());
		jQueryAjaxOption.setGlobal(false);
		Assert.assertFalse(jQueryAjaxOption.isGlobal());
	}
	
	/**
	 * Test method for {@link org.odlabs.wiquery.core.ajax.JQueryAjaxOption#isIfModified()}.
	 */
	@Test
	public void testIsIfModified() {
		Assert.assertTrue(jQueryAjaxOption.isIfModified());
		jQueryAjaxOption.setIfModified(false);
		Assert.assertFalse(jQueryAjaxOption.isIfModified());
	}
	
	/**
	 * Test method for {@link org.odlabs.wiquery.core.ajax.JQueryAjaxOption#isProcessData()}.
	 */
	@Test
	public void testIsProcessData() {
		Assert.assertTrue(jQueryAjaxOption.isProcessData());
		jQueryAjaxOption.setProcessData(false);
		Assert.assertFalse(jQueryAjaxOption.isProcessData());
	}
}

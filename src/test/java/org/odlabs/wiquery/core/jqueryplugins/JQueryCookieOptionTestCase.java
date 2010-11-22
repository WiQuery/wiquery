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
package org.odlabs.wiquery.core.jqueryplugins;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.apache.wicket.WicketRuntimeException;
import org.junit.Before;
import org.junit.Test;
import org.odlabs.wiquery.tester.WiQueryTestCase;
import org.odlabs.wiquery.ui.datepicker.DateOption;

/**
 * Test on {@link JQueryCookieOption}
 * 
 * @author Julien Roche
 */
public class JQueryCookieOptionTestCase extends WiQueryTestCase {
	// Properties
	private JQueryCookieOption jQueryCookieOption;

	@Override
	@Before
	public void setUp() {
		super.setUp();
		
		jQueryCookieOption = new JQueryCookieOption("a cookie name");
	}

	/**
	 * Test method for
	 * {@link org.odlabs.wiquery.core.jqueryplugins.JQueryCookieOption#deleteCookie()}
	 * .
	 */
	@Test
	public void testDeleteCookie() {
		assertNotNull(jQueryCookieOption.deleteCookie());
		assertEquals(jQueryCookieOption.deleteCookie().render().toString(),
				"$.cookie('a cookie name', null, {});");
	}

	/**
	 * Test method for
	 * {@link org.odlabs.wiquery.core.jqueryplugins.JQueryCookieOption#getCookie()}
	 * .
	 */
	@Test
	public void testGetCookie() {
		assertNotNull(jQueryCookieOption.getCookie());
		assertEquals(jQueryCookieOption.getCookie().render().toString(),
				"$.cookie('a cookie name');");
	}

	/**
	 * Test method for
	 * {@link org.odlabs.wiquery.core.jqueryplugins.JQueryCookieOption#getDomain()}
	 * .
	 */
	@Test
	public void testGetDomain() {
		assertNull(jQueryCookieOption.getDomain());
		jQueryCookieOption.setDomain("server.domain.net");
		assertNotNull(jQueryCookieOption.getDomain());
		assertEquals(jQueryCookieOption.getDomain(), "server.domain.net");
	}

	/**
	 * Test method for
	 * {@link org.odlabs.wiquery.core.jqueryplugins.JQueryCookieOption#getExpires()}
	 * .
	 */
	@Test
	public void testGetExpires() {
		assertNull(jQueryCookieOption.getExpires());
		jQueryCookieOption.setExpires(new DateOption("19/05/1982"));
		assertNotNull(jQueryCookieOption.getExpires());
		assertTrue(jQueryCookieOption.getExpires() instanceof DateOption);
		assertEquals(jQueryCookieOption.getExpires().getJavascriptOption(),
				"'19/05/1982'");
	}

	/**
	 * Test method for
	 * {@link org.odlabs.wiquery.core.jqueryplugins.JQueryCookieOption#getJavascriptOption()}
	 * .
	 */
	@Test
	public void testGetJavascriptOption() {
		assertEquals(jQueryCookieOption.getJavascriptOption().toString(), "{}");
		jQueryCookieOption.setPath("http://server.domain.net");
		assertEquals(jQueryCookieOption.getJavascriptOption().toString(),
				"{path: 'http://server.domain.net'}");
	}

	/**
	 * Test method for
	 * {@link org.odlabs.wiquery.core.jqueryplugins.JQueryCookieOption#getName()}
	 * .
	 */
	@Test
	public void testGetName() {
		assertNotNull(jQueryCookieOption.getName());
		assertEquals(jQueryCookieOption.getName(), "a cookie name");
	}

	/**
	 * Test method for
	 * {@link org.odlabs.wiquery.core.jqueryplugins.JQueryCookieOption#getPath()}
	 * .
	 */
	@Test
	public void testGetPath() {
		assertNull(jQueryCookieOption.getPath());
		jQueryCookieOption.setPath("http://server.domain.net");
		assertNotNull(jQueryCookieOption.getPath());
		assertEquals(jQueryCookieOption.getPath(), "http://server.domain.net");
	}

	/**
	 * Test method for
	 * {@link org.odlabs.wiquery.core.jqueryplugins.JQueryCookieOption#getValue()}
	 * .
	 */
	@Test
	public void testGetValue() {
		assertNull(jQueryCookieOption.getValue());
		jQueryCookieOption.setValue("a value");
		assertNotNull(jQueryCookieOption.getValue());
		assertEquals(jQueryCookieOption.getValue(), "a value");
	}

	/**
	 * Test method for
	 * {@link org.odlabs.wiquery.core.jqueryplugins.JQueryCookieOption#isSecure()}
	 * .
	 */
	@Test
	public void testIsSecure() {
		assertFalse(jQueryCookieOption.isSecure());
		jQueryCookieOption.setSecure(true);
		assertTrue(jQueryCookieOption.isSecure());
	}

	/**
	 * Test method for
	 * {@link org.odlabs.wiquery.core.jqueryplugins.JQueryCookieOption#JQueryCookieOption(java.lang.String)}
	 * .
	 */
	@Test
	public void testJQueryCookieOption() {
		try {
			new JQueryCookieOption(null);
			fail();

		} catch (WicketRuntimeException e) {
			assertTrue(true);
			assertEquals("name cannot be null or empty", e.getMessage());
		}
	}

	/**
	 * Test method for
	 * {@link org.odlabs.wiquery.core.jqueryplugins.JQueryCookieOption#setCookie()}
	 * .
	 */
	@Test
	public void testSetCookie() {
		jQueryCookieOption.setValue("a value");
		assertNotNull(jQueryCookieOption.setCookie());
		assertEquals(jQueryCookieOption.setCookie().render().toString(),
				"$.cookie('a cookie name', 'a value', {});");
	}
}

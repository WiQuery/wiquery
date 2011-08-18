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
package org.odlabs.wiquery.core.commons.merge;

import static org.junit.Assert.assertEquals;

import org.apache.wicket.ResourceReference;
import org.junit.Test;
import org.odlabs.wiquery.tester.WiQueryTestCase;

/**
 * $Id: WiQueryMergedStyleSheetResourceReferenceTest.java 562 2010-11-19
 * 14:10:10Z hielke.hoeve@gmail.com $
 * <p>
 * Tests for methods from the {@link WiQueryMergedStyleSheetResourceReference}
 * </p>
 * 
 * @author Julien
 * @since 1.1
 */
public class WiQueryMergedStyleSheetResourceReferenceTest extends
		WiQueryTestCase {
	/**
	 * Test the
	 * {@link WiQueryMergedStyleSheetResourceReference#fixCssUrls(String, String) }
	 */
	@Test
	public void testfixCssUrls() {
		ResourceReference baseReference = new ResourceReference("no_name");

		assertEquals(WiQueryMergedStyleSheetResourceReference.fixCssUrls(
				"url(img.png)", baseReference).trim(),
				"url(\"resources/org.apache.wicket.Application/img.png\")");

		assertEquals(WiQueryMergedStyleSheetResourceReference.fixCssUrls(
				"url(./img.png)", baseReference).trim(),
				"url(\"resources/org.apache.wicket.Application/.\\img.png\")");

		assertEquals(WiQueryMergedStyleSheetResourceReference.fixCssUrls(
				"url(../img.png)", baseReference).trim(),
				"url(\"resources/org.apache.wicket.Application/..\\img.png\")");

		assertEquals(WiQueryMergedStyleSheetResourceReference.fixCssUrls(
				"url(http://www.a.com/img.png)", baseReference).trim(),
				"url(\"http://www.a.com/img.png\")");

		assertEquals(WiQueryMergedStyleSheetResourceReference.fixCssUrls(
				"url(https://www.a.com/img.png)", baseReference).trim(),
				"url(\"https://www.a.com/img.png\")");

		assertEquals(WiQueryMergedStyleSheetResourceReference.fixCssUrls(
				"url(ftp://www.a.com/img.png)", baseReference).trim(),
				"url(\"ftp://www.a.com/img.png\")");

		assertEquals(WiQueryMergedStyleSheetResourceReference.fixCssUrls(
				"url(file://img.png)", baseReference).trim(), "url(\"file://img.png\")");

		assertEquals(WiQueryMergedStyleSheetResourceReference.fixCssUrls(
				"url('img.png')", baseReference).trim(),
				"url(\"resources/org.apache.wicket.Application/img.png\")");

		assertEquals(WiQueryMergedStyleSheetResourceReference.fixCssUrls(
				"url('./img.png')", baseReference).trim(),
				"url(\"resources/org.apache.wicket.Application/.\\img.png\")");

		assertEquals(WiQueryMergedStyleSheetResourceReference.fixCssUrls(
				"url('../img.png')", baseReference).trim(),
				"url(\"resources/org.apache.wicket.Application/..\\img.png\")");

		assertEquals(WiQueryMergedStyleSheetResourceReference.fixCssUrls(
				"url('http://www.a.com/img.png')", baseReference).trim(),
				"url(\"http://www.a.com/img.png\")");

		assertEquals(WiQueryMergedStyleSheetResourceReference.fixCssUrls(
				"url('https://www.a.com/img.png')", baseReference).trim(),
				"url(\"https://www.a.com/img.png\")");

		assertEquals(WiQueryMergedStyleSheetResourceReference.fixCssUrls(
				"url('ftp://www.a.com/img.png')", baseReference).trim(),
				"url(\"ftp://www.a.com/img.png\")");

		assertEquals(WiQueryMergedStyleSheetResourceReference.fixCssUrls(
				"url('file://img.png')", baseReference).trim(), "url(\"file://img.png\")");

		assertEquals(WiQueryMergedStyleSheetResourceReference.fixCssUrls(
				"url(\"img.png\")", baseReference).trim(),
				"url(\"resources/org.apache.wicket.Application/img.png\")");

		assertEquals(WiQueryMergedStyleSheetResourceReference.fixCssUrls(
				"url(\"./img.png\")", baseReference).trim(),
				"url(\"resources/org.apache.wicket.Application/.\\img.png\")");

		assertEquals(WiQueryMergedStyleSheetResourceReference.fixCssUrls(
				"url(\"../img.png\")", baseReference).trim(),
				"url(\"resources/org.apache.wicket.Application/..\\img.png\")");

		assertEquals(WiQueryMergedStyleSheetResourceReference.fixCssUrls(
				"url(\"http://www.a.com/img.png\")", baseReference).trim(),
				"url(\"http://www.a.com/img.png\")");

		assertEquals(WiQueryMergedStyleSheetResourceReference.fixCssUrls(
				"url(\"https://www.a.com/img.png\")", baseReference).trim(),
				"url(\"https://www.a.com/img.png\")");

		assertEquals(WiQueryMergedStyleSheetResourceReference.fixCssUrls(
				"url(\"ftp://www.a.com/img.png\")", baseReference).trim(),
				"url(\"ftp://www.a.com/img.png\")");

		assertEquals(WiQueryMergedStyleSheetResourceReference.fixCssUrls(
				"url(\"file://img.png\")", baseReference).trim(), "url(\"file://img.png\")");

		assertEquals(WiQueryMergedStyleSheetResourceReference.fixCssUrls(
				"   url(   \"   img.png   \"   )   ", baseReference).trim(),
				"url(\"resources/org.apache.wicket.Application/img.png\")");

		assertEquals(WiQueryMergedStyleSheetResourceReference.fixCssUrls(
				"   url(   \"./img.png\"   )  ", baseReference).trim(),
				"url(\"resources/org.apache.wicket.Application/.\\img.png\")");

		assertEquals(WiQueryMergedStyleSheetResourceReference.fixCssUrls(
				" url( \"../img.png\"  )  ", baseReference).trim(),
				"url(\"resources/org.apache.wicket.Application/..\\img.png\")");

		assertEquals(WiQueryMergedStyleSheetResourceReference.fixCssUrls(
				" url( \"http://www.a.com/img.png\"  )", baseReference).trim(),
				"url(\"http://www.a.com/img.png\")");

		assertEquals(WiQueryMergedStyleSheetResourceReference.fixCssUrls(
				"  url   (\"https://www.a.com/img.png\"  )  ", baseReference).trim(),
				"url(\"https://www.a.com/img.png\")");

		assertEquals(WiQueryMergedStyleSheetResourceReference.fixCssUrls(
				"   url (\"ftp://www.a.com/img.png\")", baseReference).trim(),
				"url(\"ftp://www.a.com/img.png\")");

		assertEquals(WiQueryMergedStyleSheetResourceReference.fixCssUrls(
				"url     (\"file://img.png\")", baseReference).trim(),
				"url(\"file://img.png\")");
	}
}

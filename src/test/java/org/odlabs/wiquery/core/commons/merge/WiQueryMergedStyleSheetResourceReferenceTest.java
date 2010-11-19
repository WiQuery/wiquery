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

import org.junit.Assert;
import org.junit.Test;

import junit.framework.TestCase;

/**
 * $Id$
 * 
 * <p>
 * 	Tests for methods from the {@link WiQueryMergedStyleSheetResourceReference}
 * </p>
 *
 * @author Julien
 * @since 1.1
 */
public class WiQueryMergedStyleSheetResourceReferenceTest extends TestCase {
	/**
	 * Test the {@link WiQueryMergedStyleSheetResourceReference#getCssUrl(String, String) }
	 */
	@Test
	public void testGetCssUrl() {
		String baseUrl = "http://localhost/test/";
		
		Assert.assertEquals(
				WiQueryMergedStyleSheetResourceReference.getCssUrl("url(img.png)", baseUrl), 
				"url(\"http://localhost/test/img.png\")");
		
		Assert.assertEquals(
				WiQueryMergedStyleSheetResourceReference.getCssUrl("url(./img.png)", baseUrl), 
				"url(\"http://localhost/test/./img.png\")");
		
		Assert.assertEquals(
				WiQueryMergedStyleSheetResourceReference.getCssUrl("url(../img.png)", baseUrl), 
				"url(\"http://localhost/test/../img.png\")");
		
		Assert.assertEquals(
				WiQueryMergedStyleSheetResourceReference.getCssUrl("url(http://www.a.com/img.png)", baseUrl), 
				"url(\"http://www.a.com/img.png\")");
		
		Assert.assertEquals(
				WiQueryMergedStyleSheetResourceReference.getCssUrl("url(https://www.a.com/img.png)", baseUrl), 
				"url(\"https://www.a.com/img.png\")");
		
		Assert.assertEquals(
				WiQueryMergedStyleSheetResourceReference.getCssUrl("url(ftp://www.a.com/img.png)", baseUrl), 
				"url(\"ftp://www.a.com/img.png\")");
		
		Assert.assertEquals(
				WiQueryMergedStyleSheetResourceReference.getCssUrl("url(file://img.png)", baseUrl), 
				"url(\"file://img.png\")");
		
		
		
		Assert.assertEquals(
				WiQueryMergedStyleSheetResourceReference.getCssUrl("url('img.png')", baseUrl), 
				"url(\"http://localhost/test/img.png\")");
		
		Assert.assertEquals(
				WiQueryMergedStyleSheetResourceReference.getCssUrl("url('./img.png')", baseUrl), 
				"url(\"http://localhost/test/./img.png\")");
		
		Assert.assertEquals(
				WiQueryMergedStyleSheetResourceReference.getCssUrl("url('../img.png')", baseUrl), 
				"url(\"http://localhost/test/../img.png\")");
		
		Assert.assertEquals(
				WiQueryMergedStyleSheetResourceReference.getCssUrl("url('http://www.a.com/img.png')", baseUrl), 
				"url(\"http://www.a.com/img.png\")");
		
		Assert.assertEquals(
				WiQueryMergedStyleSheetResourceReference.getCssUrl("url('https://www.a.com/img.png')", baseUrl), 
				"url(\"https://www.a.com/img.png\")");
		
		Assert.assertEquals(
				WiQueryMergedStyleSheetResourceReference.getCssUrl("url('ftp://www.a.com/img.png')", baseUrl), 
				"url(\"ftp://www.a.com/img.png\")");
		
		Assert.assertEquals(
				WiQueryMergedStyleSheetResourceReference.getCssUrl("url('file://img.png')", baseUrl), 
				"url(\"file://img.png\")");
		
		
		
		Assert.assertEquals(
				WiQueryMergedStyleSheetResourceReference.getCssUrl("url(\"img.png\")", baseUrl), 
				"url(\"http://localhost/test/img.png\")");
		
		Assert.assertEquals(
				WiQueryMergedStyleSheetResourceReference.getCssUrl("url(\"./img.png\")", baseUrl), 
				"url(\"http://localhost/test/./img.png\")");
		
		Assert.assertEquals(
				WiQueryMergedStyleSheetResourceReference.getCssUrl("url(\"../img.png\")", baseUrl), 
				"url(\"http://localhost/test/../img.png\")");
		
		Assert.assertEquals(
				WiQueryMergedStyleSheetResourceReference.getCssUrl("url(\"http://www.a.com/img.png\")", baseUrl), 
				"url(\"http://www.a.com/img.png\")");
		
		Assert.assertEquals(
				WiQueryMergedStyleSheetResourceReference.getCssUrl("url(\"https://www.a.com/img.png\")", baseUrl), 
				"url(\"https://www.a.com/img.png\")");
		
		Assert.assertEquals(
				WiQueryMergedStyleSheetResourceReference.getCssUrl("url(\"ftp://www.a.com/img.png\")", baseUrl), 
				"url(\"ftp://www.a.com/img.png\")");
		
		Assert.assertEquals(
				WiQueryMergedStyleSheetResourceReference.getCssUrl("url(\"file://img.png\")", baseUrl), 
				"url(\"file://img.png\")");
		
		
		
		
		Assert.assertEquals(
				WiQueryMergedStyleSheetResourceReference.getCssUrl("   url(   \"   img.png   \"   )   ", baseUrl), 
				"url(\"http://localhost/test/img.png\")");
		
		Assert.assertEquals(
				WiQueryMergedStyleSheetResourceReference.getCssUrl("   url(   \"./img.png\"   )  ", baseUrl), 
				"url(\"http://localhost/test/./img.png\")");
		
		Assert.assertEquals(
				WiQueryMergedStyleSheetResourceReference.getCssUrl(" url( \"../img.png\"  )  ", baseUrl), 
				"url(\"http://localhost/test/../img.png\")");
		
		Assert.assertEquals(
				WiQueryMergedStyleSheetResourceReference.getCssUrl(" url( \"http://www.a.com/img.png\"  )", baseUrl), 
				"url(\"http://www.a.com/img.png\")");
		
		Assert.assertEquals(
				WiQueryMergedStyleSheetResourceReference.getCssUrl("  url   (\"https://www.a.com/img.png\"  )  ", baseUrl), 
				"url(\"https://www.a.com/img.png\")");
		
		Assert.assertEquals(
				WiQueryMergedStyleSheetResourceReference.getCssUrl("   url (\"ftp://www.a.com/img.png\")", baseUrl), 
				"url(\"ftp://www.a.com/img.png\")");
		
		Assert.assertEquals(
				WiQueryMergedStyleSheetResourceReference.getCssUrl("url     (\"file://img.png\")", baseUrl), 
				"url(\"file://img.png\")");
	}
}

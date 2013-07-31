/*
 * Copyright (c) 2008 Objet Direct
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
package org.odlabs.wiquery.core.javascript.helper;

import org.odlabs.wiquery.core.javascript.ChainableStatement;
import org.odlabs.wiquery.core.javascript.DefaultChainableStatement;
import org.odlabs.wiquery.core.javascript.JsUtils;
import org.odlabs.wiquery.core.options.Options;

/**
 * $Id: CssHelper.java 1714M 2012-01-17 08:35:36Z (local) $
 * <p>
 * Helper to bind css functions.
 * </p>
 * 
 * @author Lionel Armanet
 * @see "http://docs.jquery.com/CSS"
 */
public final class CssHelper
{

	/**
	 * Binds the <code>css</code> statement.
	 */
	public static ChainableStatement css(Options options)
	{
		return new DefaultChainableStatement("css", options.getJavaScriptOptions());
	}

	/**
	 * Binds the <code>css</code> statement.
	 */
	public static ChainableStatement css(String name, String value)
	{
		return new DefaultChainableStatement("css", JsUtils.quotes(name), JsUtils.quotes(value));
	}
	
	private CssHelper()
	{
	}

}

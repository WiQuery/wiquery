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

/**
 * $Id$
 * <p>
 * 	Helper to bind manipulating functions.
 * </p>
 * @author Lionel Armanet
 * @since 0.7
 * @see http://docs.jquery.com/Manipulation
 */
public class ManipulatingHelper {

	/**
	 * Binds the <code>after</code> statement.
	 */
	public static ChainableStatement after(CharSequence html) {
		return new DefaultChainableStatement("after", JsUtils.quotes(html.toString()));
	}

	/**
	 * Binds the <code>insertAfter</code> statement.
	 */
	public static ChainableStatement insertAfter(String expression) {
		return new DefaultChainableStatement("insertAfter", JsUtils.quotes(expression));
	}
	
	/**
	 * Binds the <code>before</code> statement.
	 */
	public static ChainableStatement before(CharSequence html) {
		return new DefaultChainableStatement("before", JsUtils.quotes(html.toString()));
	}
	
	/**
	 * Binds the <code>insertBefore</code> statement.
	 */
	public static ChainableStatement insertBefore(String expression) {
		return new DefaultChainableStatement("insertBefore", JsUtils.quotes(expression));
	}


}

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
import org.odlabs.wiquery.core.javascript.JsScope;
import org.odlabs.wiquery.core.javascript.JsUtils;

/**
 * $Id$
 * <p>
 * 	Helper to bind attributes functions.
 * </p>
 * @author Lionel Armanet
 * @since 0.7
 * @see http://docs.jquery.com/Attributes
 */
public class AttributesHelper {

	/**
	 * Binds the <code>attr</code> statement.
	 */
	public static ChainableStatement attr(String key, String value) {
		return new DefaultChainableStatement("attr", JsUtils.quotes(key), JsUtils.quotes(value));
	}

	/**
	 * Binds the <code>attr</code> statement.
	 */
	public static ChainableStatement attr(String key, JsScope computedValue) {
		return new DefaultChainableStatement("attr", JsUtils.quotes(key), computedValue.render());
	}
	
	/**
	 * Binds the <code>removeAttr</code> statement.
	 */
	public static ChainableStatement removeAttr(String key) {
		return new DefaultChainableStatement("removeAttr", JsUtils.quotes(key));
	}
	
	/**
	 * Binds the <code>addClass</code> statement.
	 */
	public static ChainableStatement addClass(String className) {
		return new DefaultChainableStatement("addClass", JsUtils.quotes(className));
	}
	
	/**
	 * Binds the <code>removeClass</code> statement.
	 */
	public static ChainableStatement removeClass(String className) {
		return new DefaultChainableStatement("removeClass", JsUtils.quotes(className));
	}
	
	/**
	 * Binds the <code>toggleClass</code> statement.
	 */
	public static ChainableStatement toggleClass(String className) {
		return new DefaultChainableStatement("toggleClass", JsUtils.quotes(className));
	}
	
	/**
	 * Binds the <code>html</code> statement.
	 */
	public static ChainableStatement html(CharSequence htmlContents) {
		return new DefaultChainableStatement("html", JsUtils.quotes(htmlContents));
	}

}

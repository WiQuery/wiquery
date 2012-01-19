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
 * Helper to bind traversing functions.
 * </p>
 * 
 * @author Lionel Armanet
 * @see "http://docs.jquery.com/Traversing"
 */
public class TraversingHelper
{

	/**
	 * Binds the <code>eq</code> statement.
	 */
	public static ChainableStatement eq(int position)
	{
		return new DefaultChainableStatement("eq", JsUtils.string(position));
	}

	/**
	 * Binds the <code>filter</code> statement.
	 */
	public static ChainableStatement filter(String expression)
	{
		return new DefaultChainableStatement("filter", JsUtils.quotes(expression));
	}

	/**
	 * Binds the <code>not</code> statement.
	 */
	public static ChainableStatement not(String expression)
	{
		return new DefaultChainableStatement("not", JsUtils.quotes(expression));
	}

	/**
	 * Binds the <code>slice</code> statement.
	 */
	public static ChainableStatement slice(int start)
	{
		return new DefaultChainableStatement("slice", JsUtils.string(start));
	}

	/**
	 * Binds the <code>slice</code> statement.
	 */
	public static ChainableStatement slice(int start, int end)
	{
		return new DefaultChainableStatement("slice", JsUtils.string(start), JsUtils.string(end));
	}

	/**
	 * Binds the <code>add</code> statement.
	 */
	public static ChainableStatement add(String expression)
	{
		return new DefaultChainableStatement("add", JsUtils.quotes(expression));
	}

	/**
	 * Binds the <code>children</code> statement.
	 */
	public static ChainableStatement children(String expression)
	{
		return new DefaultChainableStatement("children", JsUtils.quotes(expression));
	}

	/**
	 * Binds the <code>contents</code> statement.
	 */
	public static ChainableStatement contents(String expression)
	{
		return new DefaultChainableStatement("contents", JsUtils.quotes(expression));
	}

	/**
	 * Binds the <code>find</code> statement.
	 */
	public static ChainableStatement find(String expression)
	{
		return new DefaultChainableStatement("find", JsUtils.quotes(expression));
	}

	/**
	 * Binds the <code>next</code> statement.
	 */
	public static ChainableStatement next(String expression)
	{
		return new DefaultChainableStatement("next", JsUtils.quotes(expression));
	}

	/**
	 * Binds the <code>nextAll</code> statement.
	 */
	public static ChainableStatement nextAll(String expression)
	{
		return new DefaultChainableStatement("nextAll", JsUtils.quotes(expression));
	}

	/**
	 * Binds the <code>parent</code> statement.
	 */
	public static ChainableStatement parent(String expression)
	{
		return new DefaultChainableStatement("parent", JsUtils.quotes(expression));
	}

	/**
	 * Binds the <code>parents</code> statement.
	 */
	public static ChainableStatement parents(String expression)
	{
		return new DefaultChainableStatement("parents", JsUtils.quotes(expression));
	}

	/**
	 * Binds the <code>prev</code> statement.
	 */
	public static ChainableStatement prev(String expression)
	{
		return new DefaultChainableStatement("prev", JsUtils.quotes(expression));
	}

	/**
	 * Binds the <code>prevAll</code> statement.
	 */
	public static ChainableStatement prevAll(String expression)
	{
		return new DefaultChainableStatement("prevAll", JsUtils.quotes(expression));
	}

	/**
	 * Binds the <code>siblings</code> statement.
	 */
	public static ChainableStatement siblings(String expression)
	{
		return new DefaultChainableStatement("siblings", JsUtils.quotes(expression));
	}

}

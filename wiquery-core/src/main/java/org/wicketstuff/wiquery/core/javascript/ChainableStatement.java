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
package org.wicketstuff.wiquery.core.javascript;

/**
 * $Id: ChainableStatement.java 1714 2011-09-22 20:38:30Z hielke.hoeve $
 * <p>
 * Common interface to ease jQuery integration in WickeXt. Defines common information needed by a
 * JavaScript statement to be append to a {@link JsStatement}.
 * </p>
 * 
 * @author Lionel Armanet
 * @since 0.7
 */
public interface ChainableStatement
{

	/**
	 * @return the statement label (like <code>each</code>, <code>css</code>...)
	 */
	String chainLabel();

	/**
	 * @return the list of arguments passed to the statement.
	 */
	CharSequence[] statementArgs();

}

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
package org.odlabs.wiquery.core.options;

import java.io.Serializable;
import org.odlabs.wiquery.core.javascript.JsUtils;

/**
 * $Id$
 * <p>
 * Wraps a {@link String} to be generated as a JavaScript string.
 * <p>
 * Example:
 * <p>
 * The {@link String} <code>foo</code> should be rendered as
 * <code>'foo'</code>
 * </p>
 * </p>
 * </p>
 * 
 * @author Lionel Armanet
 * @since 0.5
 */
public class LiteralOption implements Serializable, IListItemOption {
	// Constants
	/** Constant of serialization */
	private static final long serialVersionUID = 6999431516689050752L;

	// Properties
	/**
	 * Put Double quote ?
	 */
	private boolean doubleQuote;
	
	/**
	 * The wrapped {@link String}
	 */
	private String literal;
	
	/**
	 * <p>
	 * Builds a new instance of {@link LiteralOption}.
	 * </p>
	 * 
	 * @param literal
	 *            the wrapped {@link String}
	 */
	public LiteralOption(String literal) {
		this(literal, false);
	}

	/**
	 * <p>
	 * Builds a new instance of {@link LiteralOption}.
	 * </p>
	 * 
	 * @param literal
	 *            the wrapped {@link String}
	 * @param doubleQuote Must we insert double quote ?
	 */
	public LiteralOption(String literal, boolean doubleQuote) {
		super();
		this.literal = literal;
		this.doubleQuote = doubleQuote;
	}

	/**
	 * {@inheritDoc}
	 * @see org.odlabs.wiquery.core.options.IListItemOption#getJavascriptOption()
	 */
	public CharSequence getJavascriptOption() {
		return toString();
	}

	/**
	 * @return the wrapped {@link String}.
	 */
	public String getLiteral() {
		return literal;
	}
	
	/**
	 * {@inheritDoc}
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return doubleQuote ? JsUtils.doubleQuotes(literal) : JsUtils.quotes(literal);
	}
}

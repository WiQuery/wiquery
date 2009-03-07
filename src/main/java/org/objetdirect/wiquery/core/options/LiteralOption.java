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
package org.objetdirect.wiquery.core.options;

import java.io.Serializable;

/**
 * $Id$
 * <p>
 * 	Wraps a {@link String} to be generated as a JavaScript string.
 * 	<p>
 *  	Example:
 *  	<p>
 *  		The {@link String} <code>foo</code> should be rendered 
 *  		as <code>'foo'</code>
 *  	</p>
 *  </p>
 * </p>
 * @author Lionel Armanet
 * @since 0.5
 */
public class LiteralOption implements Serializable {

	private static final long serialVersionUID = 6999431516689050752L;
	
	/**
	 * The wrapped {@link String}
	 */
	private String literal;

	/**
	 * <p>
	 * 	Builds a new instance of {@link LiteralOption}.
	 * </p>
	 * @param literal the wrapped {@link String}
	 */
	public LiteralOption(String literal) {
		super();
		this.literal = literal;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "'" + literal +  "'";
	}

	/**
	 * @return the wrapped {@link String}.
	 */
	public String getLiteral() {
		return literal;
	}
	
}

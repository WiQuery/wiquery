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
package org.odlabs.wiquery.core.javascript;

/**
 * $Id$
 * <p>
 * Default implementation of ChainableStatement.
 * </p>
 * 
 * @author Lionel Armanet
 * @since 0.7
 */
public class DefaultChainableStatement implements ChainableStatement {

	/**
	 * The statement label.
	 */
	private String label;

	/**
	 * The statement args.
	 */
	private CharSequence[] args;

	/**
	 * Creates a new instance of {@link DefaultChainableStatement}.
	 */
	public DefaultChainableStatement(String label, CharSequence... args) {
		super();
		this.label = label;
		this.args = args;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.objetdirect.wickext.core.javascript.ChainableStatement#chainLabel()
	 */
	public String chainLabel() {
		return label;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.objetdirect.wickext.core.javascript.ChainableStatement#statementArgs()
	 */
	public CharSequence[] statementArgs() {
		return args;
	}

}

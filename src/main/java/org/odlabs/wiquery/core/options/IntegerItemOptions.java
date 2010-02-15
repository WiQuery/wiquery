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

/**
 * Representation of an integer into an ItemOptions
 * @author Julien Roche
 *
 */
public class IntegerItemOptions extends Object implements IListItemOption {
	// Constants
	/**	Constant of serialization */
	private static final long serialVersionUID = -1683433456056445577L;
	
	// Properties
	private int integer;
	
	/**Default constructor
	 * @param integer
	 */
	public IntegerItemOptions(int integer) {
		super();
		this.integer = integer;
	}
	
	/**Method retrieving the integer value
	 * @return the integer
	 */
	public int getInteger() {
		return integer;
	}

	/* (non-Javadoc)
	 * @see org.odlabs.wiquery.core.options.IListItemOption#getJavascriptOption()
	 */
	public CharSequence getJavascriptOption() {
		return Integer.toString(integer);
	}

	/**Methode setting the integer value
	 * @param integer Integer
	 */
	public void setInteger(int integer) {
		this.integer = integer;
	}
}

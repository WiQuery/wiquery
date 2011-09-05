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

/**
 * $Id$
 * <p>
 * Renders a set of options.
 * </p>
 * 
 * @author Lionel Armanet
 * @since 1.0
 */
public interface IOptionsRenderer extends Serializable
{

	/**
	 * Renders a JavaScript code before iterating on each key, value pair.
	 * 
	 * @param stringBuilder
	 *            The current JavaScript output
	 */
	void renderBefore(StringBuilder stringBuilder);

	/**
	 * Renders the current option (e.g. the current key,value pair).
	 * 
	 * @param name
	 *            The option name
	 * @param value
	 *            The option value
	 * @param isLast
	 *            true if this is the last option, false otherwise
	 * @return A {@link CharSequence} rendering the option JavaScript code.
	 */
	CharSequence renderOption(String name, Object value, boolean isLast);

	/**
	 * Renders a JavaScript code after iterating on each key, value pair.
	 * 
	 * @param stringBuilder
	 *            The current JavaScript output
	 */
	void renderAfter(StringBuilder stringBuilder);

}

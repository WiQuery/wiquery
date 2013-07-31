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

import java.util.ArrayList;

/**
 * Array of IListItemOptions
 * 
 * @author Julien Roche
 * 
 */
public class ArrayItemOptions<E extends IListItemOption> extends ArrayList<E> implements
		ICollectionItemOptions
{
	// Constants
	/** Constant of serialization */
	private static final long serialVersionUID = 1779802328333735627L;

	@Override
	public CharSequence getJavascriptOption()
	{
		StringBuilder javascript = new StringBuilder();
		javascript.append('[');

		if (!isEmpty())
		{
			for (IListItemOption itemOption : this)
			{
				javascript.append(itemOption.getJavascriptOption());
				javascript.append(',');
			}
			javascript.replace(javascript.length() - 1, javascript.length(), ""); // Remove
																					// the
																					// last
																					// ','
		}

		javascript.append(']');
		return javascript.toString();
	}

	@Override
	public IListItemOption[] values()
	{
		return this.toArray(new IListItemOption[this.size()]);
	}
}

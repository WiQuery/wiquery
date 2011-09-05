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
package org.odlabs.wiquery.ui.datepicker;

import org.odlabs.wiquery.core.options.ArrayItemOptions;
import org.odlabs.wiquery.core.options.IComplexOption;
import org.odlabs.wiquery.core.options.LiteralOption;

/**
 * $Id: ArrayOfNames.java
 * <p>
 * Abstract class to store a list of names (for daynames, daynamesMin, monthnames, ...)
 * </p>
 * 
 * @author Julien Roche
 * @since 1.0
 */
public abstract class AbstractArrayOfDateNames extends Object implements IComplexOption
{
	// Constants
	/** Constant of serialization */
	private static final long serialVersionUID = -9097637272858071731L;

	// Properties
	private ArrayItemOptions<LiteralOption> itemOptions;

	/**
	 * Constructor
	 * 
	 * @param names
	 *            List of names
	 */
	public AbstractArrayOfDateNames(String... names)
	{
		super();
		itemOptions = new ArrayItemOptions<LiteralOption>();

		for (String str : names)
		{
			itemOptions.add(new LiteralOption(str));
		}
	}

	public CharSequence getJavascriptOption()
	{
		if (itemOptions.isEmpty())
		{
			throw new IllegalArgumentException("We must have a list of names");
		}
		else if (!getNumberOfName().equals(itemOptions.size()))
		{
			throw new IllegalArgumentException(String.format("The list must have %d names",
				getNumberOfName()));
		}

		return itemOptions.getJavascriptOption();
	}

	/**
	 * @return the number of desired names
	 */
	public abstract Integer getNumberOfName();
}

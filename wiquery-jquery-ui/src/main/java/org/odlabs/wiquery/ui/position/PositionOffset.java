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
package org.odlabs.wiquery.ui.position;

import org.odlabs.wiquery.core.options.IComplexOption;
import org.odlabs.wiquery.core.options.LiteralOption;

/**
 * $Id$
 * <p>
 * Bean for the offset option for the Position behavior
 * </p>
 * 
 * @author Julien Roche
 * @since 1.1
 */
public class PositionOffset extends Object implements IComplexOption
{
	// Constants
	/** Constant of serialization */
	private static final long serialVersionUID = 1L;

	// Properties
	private int offsetLeft;

	private int offsetTop;

	/**
	 * Constructor
	 * 
	 * @param offsetLeft
	 *            offset Left
	 */
	public PositionOffset(int offsetLeft)
	{
		this(offsetLeft, offsetLeft);
	}

	/**
	 * Constructor
	 * 
	 * @param offsetLeft
	 *            offset Left
	 * @param offsetTop
	 *            Offset top
	 */
	public PositionOffset(int offsetLeft, int offsetTop)
	{
		super();
		this.offsetLeft = offsetLeft;
		this.offsetTop = offsetTop;
	}

	public CharSequence getJavascriptOption()
	{
		return new LiteralOption(offsetLeft + " " + offsetTop).toString();
	}

	/**
	 * @return the offset left
	 */
	public int getOffsetLeft()
	{
		return offsetLeft;
	}

	/**
	 * @return the offset top
	 */
	public int getOffsetTop()
	{
		return offsetTop;
	}

	/**
	 * Set the offset left
	 * 
	 * @param offsetLeft
	 */
	public void setOffsetLeft(int offsetLeft)
	{
		this.offsetLeft = offsetLeft;
	}

	/**
	 * Set the offset top
	 * 
	 * @param offserTop
	 */
	public void setOffsetTop(int offsetTop)
	{
		this.offsetTop = offsetTop;
	}
}

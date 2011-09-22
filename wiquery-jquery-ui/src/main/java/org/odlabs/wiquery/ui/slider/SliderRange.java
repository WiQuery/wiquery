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
package org.odlabs.wiquery.ui.slider;

import org.odlabs.wiquery.core.options.IComplexOption;
import org.odlabs.wiquery.core.options.LiteralOption;

/**
 * $Id: SliderRange.java
 * <p>
 * Bean for the range option into the Slider component
 * </p>
 * 
 * @author Julien Roche
 * @since 1.0
 */
public class SliderRange implements IComplexOption
{
	public enum RangeEnum
	{
		MIN(new LiteralOption("min")),
		MAX(new LiteralOption("max"));

		// Properties
		private LiteralOption literalParam;

		RangeEnum(LiteralOption literalParam)
		{
			this.literalParam = literalParam;
		}

		@Override
		public String toString()
		{
			return literalParam.toString();
		}
	}

	// Constants
	/** Constant of serialization */
	private static final long serialVersionUID = 3404088696595137949L;

	// Properties
	private Boolean booleanParam;

	private RangeEnum rangeEnumParam;

	/**
	 * Constructor
	 * 
	 * @param booleanParam
	 *            Boolean parameter
	 */
	public SliderRange(Boolean booleanParam)
	{
		this(booleanParam, null);
	}

	/**
	 * Constructor
	 * 
	 * @param rangeEnumParam
	 *            RangeEnum parameter
	 */
	public SliderRange(RangeEnum rangeEnumParam)
	{
		this(null, rangeEnumParam);
	}

	/**
	 * Constructor
	 * 
	 * @param booleanParam
	 *            Boolean parameter
	 * @param rangeEnumParam
	 *            RangeEnum parameter
	 */
	private SliderRange(Boolean booleanParam, RangeEnum rangeEnumParam)
	{
		super();
		setParam(booleanParam, rangeEnumParam);
	}

	/**
	 * @return the booleanParam
	 */
	public boolean isBooleanParam()
	{
		return booleanParam;
	}

	/**
	 * @return the rangeEnumParam
	 */
	public RangeEnum getRangeEnumParam()
	{
		return rangeEnumParam;
	}

	public CharSequence getJavascriptOption()
	{
		if (booleanParam == null && rangeEnumParam == null)
		{
			throw new IllegalArgumentException("The SliderRange must have one not null parameter");
		}

		CharSequence sequence = null;

		if (booleanParam != null)
		{
			sequence = booleanParam.toString();
		}
		else if (rangeEnumParam != null)
		{
			sequence = rangeEnumParam.toString();
		}
		else
		{
			throw new IllegalArgumentException("The SliderRange must have one not null parameter");
		}

		return sequence;
	}

	/**
	 * Set's the boolean parameter
	 * 
	 * @param booleanParam
	 *            the booleanParam to set
	 */
	public void setBooleanParam(boolean booleanParam)
	{
		setParam(booleanParam, null);
	}

	/**
	 * Set's the literal parameter
	 * 
	 * @param literalParam
	 *            the literal to set
	 */
	public void setRangeEnumParam(RangeEnum rangeEnumParam)
	{
		setParam(null, rangeEnumParam);
	}

	/**
	 * Method setting the right parameter
	 * 
	 * @param booleanParam
	 *            Boolean parameter
	 * @param rangeEnumParam
	 *            RangeEnum parameter
	 */
	private void setParam(Boolean booleanParam, RangeEnum rangeEnumParam)
	{
		this.booleanParam = booleanParam;
		this.rangeEnumParam = rangeEnumParam;
	}
}

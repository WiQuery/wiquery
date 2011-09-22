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
import org.odlabs.wiquery.core.options.IntegerItemOptions;

/**
 * $Id: DatePickerNumberOfMonths.java
 * <p>
 * Bean for the numberOfMonths option for the DatePicker component
 * </p>
 * 
 * @author Julien Roche
 * @since 1.0
 */
public class DatePickerNumberOfMonths implements IComplexOption
{
	// Constants
	/** Constant of serialization */
	private static final long serialVersionUID = 3404088696595137949L;

	// Properties
	private Short shortParam;

	private ArrayItemOptions<IntegerItemOptions> arrayParam;

	/**
	 * Constructor
	 * 
	 * @param shortParam
	 *            Short parameter
	 */
	public DatePickerNumberOfMonths(Short shortParam)
	{
		this(shortParam, null);
	}

	/**
	 * Constructor
	 * 
	 * @param arrayParam
	 *            Array parameter
	 */
	public DatePickerNumberOfMonths(ArrayItemOptions<IntegerItemOptions> arrayParam)
	{
		this(null, arrayParam);
	}

	/**
	 * Constructor
	 * 
	 * @param shortParam
	 *            Short parameter
	 * @param arrayParam
	 *            Array parameter
	 */
	private DatePickerNumberOfMonths(Short shortParam,
			ArrayItemOptions<IntegerItemOptions> arrayParam)
	{
		super();
		setParam(shortParam, arrayParam);
	}

	/**
	 * @return the arrayParam
	 */
	public ArrayItemOptions<IntegerItemOptions> getArrayParam()
	{
		return arrayParam;
	}

	/**
	 * @return the shortParam
	 */
	public Short getShortParam()
	{
		return shortParam;
	}

	public CharSequence getJavascriptOption()
	{
		if (shortParam == null && arrayParam == null)
		{
			throw new IllegalArgumentException(
				"The DatePickerNumberOfMonths must have one not null parameter");
		}

		CharSequence sequence = null;

		if (shortParam != null)
		{
			sequence = shortParam.toString();
		}
		else if (arrayParam != null)
		{
			if (arrayParam.size() != 2)
			{
				throw new IllegalArgumentException(
					"The 'arrayParam' in the DatePickerNumberOfMonths must have two values");
			}

			sequence = arrayParam.getJavascriptOption();
		}
		else
		{
			throw new IllegalArgumentException(
				"The DatePickerNumberOfMonths must have one not null parameter");
		}

		return sequence;
	}

	/**
	 * Set's the array parameter
	 * 
	 * @param arrayParam
	 *            the array to set
	 */
	public void setArrayParam(ArrayItemOptions<IntegerItemOptions> arrayParam)
	{
		setParam(null, arrayParam);
	}

	/**
	 * Set's the short parameter
	 * 
	 * @param shortParam
	 *            short parameter
	 */
	public void setShortParam(Short shortParam)
	{
		setParam(shortParam, null);
	}

	/**
	 * Method setting the right parameter
	 * 
	 * @param shortParam
	 *            Short parameter
	 * @param arrayParam
	 *            Array parameter
	 */
	private void setParam(Short shortParam, ArrayItemOptions<IntegerItemOptions> arrayParam)
	{
		this.shortParam = shortParam;
		this.arrayParam = arrayParam;
	}
}

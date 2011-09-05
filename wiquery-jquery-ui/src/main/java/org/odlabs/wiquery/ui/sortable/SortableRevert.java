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
package org.odlabs.wiquery.ui.sortable;

import org.odlabs.wiquery.core.options.IComplexOption;

/**
 * $Id: SortableRevert.java
 * <p>
 * Bean for the revert option for the Sortable behavior
 * </p>
 * 
 * @author Julien Roche
 * @since 1.0
 */
public class SortableRevert implements IComplexOption
{
	// Constants
	/** Constant of serialization */
	private static final long serialVersionUID = 3404088696595137949L;

	// Properties
	private Boolean booleanParam;

	private Integer intParameter;

	/**
	 * Constructor
	 * 
	 * @param booleanParam
	 *            Boolean parameter
	 */
	public SortableRevert(Boolean booleanParam)
	{
		this(booleanParam, null);
	}

	/**
	 * Constructor
	 * 
	 * @param intParameter
	 *            Integer parameter
	 */
	public SortableRevert(Integer intParameter)
	{
		this(null, intParameter);
	}

	/**
	 * Constructor
	 * 
	 * @param booleanParam
	 *            Boolean parameter
	 * @param intParameter
	 *            Integer parameter
	 */
	private SortableRevert(Boolean booleanParam, Integer intParameter)
	{
		super();
		setParam(booleanParam, intParameter);
	}

	/**
	 * @return the booleanParam
	 */
	public Boolean getBooleanParam()
	{
		return booleanParam;
	}

	/**
	 * @return the intParameter
	 */
	public Integer getIntParam()
	{
		return intParameter;
	}

	public CharSequence getJavascriptOption()
	{
		if (booleanParam == null && intParameter == null)
		{
			throw new IllegalArgumentException(
				"The SortableRevert must have one not null parameter");
		}

		CharSequence sequence = null;

		if (booleanParam != null)
		{
			sequence = booleanParam.toString();
		}
		else if (intParameter != null)
		{
			sequence = intParameter.toString();
		}
		else
		{
			throw new IllegalArgumentException(
				"The SortableRevert must have one not null parameter");
		}

		return sequence;
	}

	/**
	 * Set's the boolean parameter
	 * 
	 * @param booleanParam
	 *            the boolean to set
	 */
	public void setBooleanParam(Boolean booleanParam)
	{
		setParam(booleanParam, null);
	}

	/**
	 * Set's the Integer parameter
	 * 
	 * @param intParameter
	 *            the integer to set
	 */
	public void setIntParam(Integer intParameter)
	{
		setParam(null, intParameter);
	}

	/**
	 * Method setting the right parameter
	 * 
	 * @param booleanParam
	 *            Boolean parameter
	 * @param intParameter
	 *            Integer parameter
	 */
	private void setParam(Boolean booleanParam, Integer intParameter)
	{
		this.booleanParam = booleanParam;
		this.intParameter = intParameter;
	}
}

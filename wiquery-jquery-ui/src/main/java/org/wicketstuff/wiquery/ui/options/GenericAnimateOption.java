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
package org.wicketstuff.wiquery.ui.options;


import org.wicketstuff.wiquery.core.options.IComplexOption;
import org.wicketstuff.wiquery.core.options.LiteralOption;

/**
 * $Id: GenericAnimateOption.java
 * <p>
 * Bean for a generic animate option
 * </p>
 * 
 * @author Stephane Gleizes
 * @since 6.9.2
 */
public class GenericAnimateOption<T extends IComplexOption> implements IComplexOption
{
	// Constants
	/** Constant of serialization */
	private static final long serialVersionUID = -2210690093704208073L;

	// Properties
	private Boolean booleanParam;

	private Integer integerParam;
	
	private String literalParam;
	
	private T objectParam;

	/**
	 * Constructor
	 * 
	 * @param booleanParam
	 *            Short parameter
	 */
	public GenericAnimateOption(Boolean booleanParam)
	{
		this(booleanParam, null, null, null);
	}

	/**
	 * Constructor
	 * 
	 * @param integerParam
	 *            integer parameter
	 */
	public GenericAnimateOption(Integer integerParam)
	{
		this(null, integerParam, null, null);
	}

	/**
	 * Constructor
	 * 
	 * @param literalParam
	 *            literal parameter
	 */
	public GenericAnimateOption(String literalParam)
	{
		this(null, null, literalParam, null);
	}

	/**
	 * Constructor
	 * 
	 * @param objectParam
	 *            object parameter
	 */
	public GenericAnimateOption(T objectParam)
	{
		this(null, null, null, objectParam);
	}

	/**
	 * Constructor
	 * 
	 * @param booleanParam
	 *            boolean parameter
	 * @param integerParam
	 *            integer parameter
	 * @param literalParam
	 *            literal parameter
	 * @param objectParam
	 *            object parameter
	 */
	private GenericAnimateOption(Boolean booleanParam, Integer integerParam, String literalParam, T objectParam)
	{
		super();
		setParam(booleanParam, integerParam, literalParam, objectParam);
	}

	/**
	 * @return the booleanParam
	 */
	public Boolean getBooleanParam()
	{
		return booleanParam;
	}
	
	/**
	 * @return the integerParam
	 */
	public Integer getIntegerParam()
	{
		return integerParam;
	}
	
	/**
	 * @return the literalParam
	 */
	public String getLiteralParam()
	{
		return literalParam;
	}
	
	/**
	 * @return the objectParam
	 */
	public T getObjectParam()
	{
		return objectParam;
	}

	@Override
	public CharSequence getJavascriptOption()
	{
		if (booleanParam == null && integerParam == null && literalParam == null && objectParam == null)
		{
			throw new IllegalArgumentException("The GenericAnimateOption must have one not null parameter");
		}

		CharSequence sequence = null;

		if (booleanParam != null)
		{
			sequence = booleanParam.toString();
		}
		else if (integerParam != null)
		{
			sequence = integerParam.toString();
		}
		else if (literalParam != null)
		{
			sequence = new LiteralOption(literalParam).toString();
		}
		else if (objectParam != null)
		{
			sequence = objectParam.getJavascriptOption();
		}
		else
		{
			throw new IllegalArgumentException("The GenericAnimateOption must have one not null parameter");
		}

		return sequence;
	}
	
	/**
	 * Set's the boolean parameter
	 * 
	 * @param booleanParam
	 *            boolean parameter
	 */
	public void setBooleanParam(Boolean booleanParam)
	{
		setParam(booleanParam, null, null, null);
	}
	
	/**
	 * Set's the integer parameter
	 * 
	 * @param integerParam
	 *            integer parameter
	 */
	public void setIntegerParam(Integer integerParam)
	{
		setParam(null, integerParam, null, null);
	}

	/**
	 * Set's the literal parameter
	 * 
	 * @param literalParam
	 *            literal parameter
	 */
	public void setLiteralParam(String literalParam)
	{
		setParam(null, null, literalParam, null);
	}
	
	/**
	 * Set's the object parameter
	 * 
	 * @param objectParam
	 *            short parameter
	 */
	public void setObjectParam(T objectParam)
	{
		setParam(null, null, null, objectParam);
	}

	/**
	 * Method setting the right parameter
	 * 
	 * @param booleanParam
	 *            boolean parameter
	 * @param integerParam
	 *            integer parameter
	 * @param literalParam
	 *            literal parameter
	 * @param objectParam
	 *            object parameter
	 */
	private void setParam(Boolean booleanParam, Integer integerParam, String literalParam, T objectParam)
	{
		this.booleanParam = booleanParam;
		this.integerParam = integerParam;
		this.literalParam = literalParam;
		this.objectParam = objectParam;
	}
}

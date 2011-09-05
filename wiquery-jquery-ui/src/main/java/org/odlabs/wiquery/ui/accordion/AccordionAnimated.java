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
package org.odlabs.wiquery.ui.accordion;

import org.odlabs.wiquery.core.options.IComplexOption;
import org.odlabs.wiquery.core.options.LiteralOption;

/**
 * $Id: AccordionAnimated
 * <p>
 * Bean for the animated option for the Accordion component
 * </p>
 * 
 * @author Julien Roche
 * @since 1.0
 */
public class AccordionAnimated implements IComplexOption
{
	// Constants
	/** Constant of serialization */
	private static final long serialVersionUID = 3404088696595137949L;

	// Properties
	private Boolean booleanParam;

	private String effectParam;

	/**
	 * Constructor
	 * 
	 * @param booleanParam
	 *            Boolean parameter
	 */
	public AccordionAnimated(Boolean booleanParam)
	{
		this(booleanParam, null);
	}

	/**
	 * Constructor
	 * 
	 * @param effectParam
	 *            Effect parameter
	 */
	public AccordionAnimated(String effectParam)
	{
		this(null, effectParam);
	}

	/**
	 * Constructor
	 * 
	 * @param booleanParam
	 *            Boolean parameter
	 * @param effectParam
	 *            Effect parameter
	 */
	private AccordionAnimated(Boolean booleanParam, String effectParam)
	{
		super();
		setParam(booleanParam, effectParam);
	}

	/**
	 * @return the booleanParam
	 */
	public Boolean getBooleanParam()
	{
		return booleanParam;
	}

	/**
	 * @return the effectParam
	 */
	public String getEffectPAram()
	{
		return effectParam;
	}

	public CharSequence getJavascriptOption()
	{
		if (booleanParam == null && effectParam == null)
		{
			throw new IllegalArgumentException(
				"The AccordionAnimated must have one not null parameter");
		}

		CharSequence sequence = null;

		if (booleanParam != null)
		{
			sequence = booleanParam.toString();
		}
		else if (effectParam != null)
		{
			sequence = new LiteralOption(effectParam).toString();
		}
		else
		{
			throw new IllegalArgumentException(
				"The AccordionAnimated must have one not null parameter");
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
	 * Set's the effect parameter
	 * 
	 * @param effectParam
	 *            the effect to set
	 */
	public void setEffectParam(String effectParam)
	{
		setParam(null, effectParam);
	}

	/**
	 * Method setting the right parameter
	 * 
	 * @param booleanParam
	 *            Boolean parameter
	 * @param effectParam
	 *            Effect parameter
	 */
	private void setParam(Boolean booleanParam, String effectParam)
	{
		this.booleanParam = booleanParam;
		this.effectParam = effectParam;
	}
}

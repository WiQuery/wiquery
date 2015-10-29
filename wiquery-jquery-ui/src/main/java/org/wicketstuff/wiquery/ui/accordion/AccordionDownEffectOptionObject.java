/*
 * Copyright (c) 2010 WiQuery team
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
package org.wicketstuff.wiquery.ui.accordion;

import org.wicketstuff.wiquery.core.options.IComplexOption;
import org.wicketstuff.wiquery.core.options.Options;

/**
 * $Id EffectOptionObject.java$
 * 
 * <p>
 * Complex option to store the possible options for the effect.
 * </p>
 * 
 * @author Stephane Gleizes
 * @since 6.9.2
 */
public class AccordionDownEffectOptionObject implements IComplexOption
{
	// Constants
	/** Constant of serialization */
	private static final long serialVersionUID = 3577810973198202998L;

	// Properties
	private Options options;

	/**
	 * Default constructor
	 */
	public AccordionDownEffectOptionObject()
	{
		super();
		options = new Options();
	}

	@Override
	public CharSequence getJavascriptOption()
	{
		return options.getJavaScriptOptions();
	}

	/**
	 * Method retrieving the options
	 * 
	 * @return the options
	 */
	protected Options getOptions()
	{
		return options;
	}

	/*---- Options section ---*/

	/**
	 * The duration of the effect. If omitted, the default value will be used.
	 * 
	 * @param duration
	 * @return the instance
	 */
	public AccordionDownEffectOptionObject setDuration(Integer duration)
	{
		options.put("duration", duration);
		return this;
	}
	
	/**
	 * @return the duration option
	 */
	public Integer getDuration()
	{
		return options.getInt("duration");
	}
	
	/**
	 * The easing to use with the effect. If omitted, the default value will be used.
	 * 
	 * @param easing
	 * @return the instance
	 */
	public AccordionDownEffectOptionObject setEasing(String easing)
	{
		options.putLiteral("easing", easing);
		return this;
	}
	
	/**
	 * @return the easing option
	 */
	public String getEasing()
	{
		return options.getLiteral("easing");
	}
}

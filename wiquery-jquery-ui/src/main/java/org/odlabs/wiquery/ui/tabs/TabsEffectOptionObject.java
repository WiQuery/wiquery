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
package org.odlabs.wiquery.ui.tabs;

import org.odlabs.wiquery.core.options.IComplexOption;
import org.odlabs.wiquery.core.options.Options;

/**
 * $Id TabsEffectOptions.java$
 * 
 * <p>
 * Complex option to store the possible options for the effect. This is used too for the
 * option show/hide into the Tabs component.
 * </p>
 * 
 * @author Stephane Gleizes
 * @since 6.9.2
 */
public class TabsEffectOptionObject implements IComplexOption
{
	// Constants
	/** Constant of serialization */
	private static final long serialVersionUID = 3577810973198202998L;

	// Properties
	private Options options;

	/**
	 * Default constructor
	 */
	public TabsEffectOptionObject()
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
	 * If the effect property contains the name of a jQuery method, then that method will be used;
	 * otherwise it is assumed to be the name of a jQuery UI effect.
	 * If omitted, then "fadeIn" will be used.
	 * 
	 * @param effect
	 * @return the instance
	 */
	public TabsEffectOptionObject setEffect(String effect)
	{
		getOptions().putLiteral("effect", effect);
		return this;
	}
	
	/**
	 * @return the effect option
	 */
	public String getEffect()
	{
		return getOptions().getLiteral("effect");
	}
	
	/**
	 * The delay before rendering the effect. If omitted, no delay is used.
	 * 
	 * @param delay
	 * @return the instance
	 */
	public TabsEffectOptionObject setDelay(Integer delay)
	{
		options.put("delay", delay);
		return this;
	}
	
	/**
	 * @return the delay option
	 */
	public Integer getDelay()
	{
		return options.getInt("delay");
	}
	
	/**
	 * The duration of the effect. If omitted, the default value will be used.
	 * 
	 * @param duration
	 * @return the instance
	 */
	public TabsEffectOptionObject setDuration(Integer duration)
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
	public TabsEffectOptionObject setEasing(String easing)
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

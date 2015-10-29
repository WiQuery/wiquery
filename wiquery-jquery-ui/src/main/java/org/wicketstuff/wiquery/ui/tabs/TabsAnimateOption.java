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
package org.wicketstuff.wiquery.ui.tabs;


import org.wicketstuff.wiquery.ui.options.EffectOptionObject;
import org.wicketstuff.wiquery.ui.options.GenericAnimateOption;

/**
 * $Id: TabsAnimateOption.java
 * <p>
 * Bean for the show, hide options for the Tabs component
 * </p>
 * Multiple types supported:
 * <ul>
 * 	<li>Boolean: When set to false, no animation will be used and the panel will be shown immediately.
 * When set to true, the panel will fade in with the default duration and the default easing. </li>
 * 	<li>Number: The panel will fade in with the specified duration and the default easing. </li>
 * 	<li>String: The panel will be shown using the specified effect.
 * The value can either be the name of a built-in jQuery animation method, such as "slideDown", or the
 * name of a jQuery UI effect, such as "fold".
 * In either case the effect will be used with the default duration and the default easing. </li>
 * 	<li>Object: If the value is an object, then effect, delay, duration, and easing properties may be provided.
 * If the effect property contains the name of a jQuery method, then that method will be used;
 * otherwise it is assumed to be the name of a jQuery UI effect.
 * If duration or easing is omitted, then the default values will be used.
 * If effect is omitted, then "fadeIn" will be used. If delay is omitted, then no delay is used.
 * </ul>
 * 
 * @author Stephane Gleizes
 * @since 6.9.2
 */
public class TabsAnimateOption extends GenericAnimateOption<EffectOptionObject>
{
	// Constants
	/** Constant of serialization */
	private static final long serialVersionUID = -2210690093704208073L;

	/**
	 * Constructor
	 * 
	 * @param booleanParam
	 *            Short parameter
	 */
	public TabsAnimateOption(Boolean booleanParam)
	{
		super(booleanParam);
	}

	/**
	 * Constructor
	 * 
	 * @param integerParam
	 *            integer parameter
	 */
	public TabsAnimateOption(Integer integerParam)
	{
		super(integerParam);
	}

	/**
	 * Constructor
	 * 
	 * @param literalParam
	 *            literal parameter
	 */
	public TabsAnimateOption(String literalParam)
	{
		super(literalParam);
	}

	/**
	 * Constructor
	 * 
	 * @param objectParam
	 *            object parameter
	 */
	public TabsAnimateOption(EffectOptionObject objectParam)
	{
		super(objectParam);
	}
}

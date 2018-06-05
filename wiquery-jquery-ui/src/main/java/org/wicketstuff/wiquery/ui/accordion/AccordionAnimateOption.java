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
package org.wicketstuff.wiquery.ui.accordion;


import org.wicketstuff.wiquery.ui.options.GenericAnimateOption;

/**
 * $Id: AccordionAnimateOption.java
 * <p>
 * Bean for the animate option for the Accordion component
 * </p>
 * Multiple types supported:
 * <ul>
 * <li>Boolean: A value of false will disable animations.</li>
 * <li>Number: Duration in milliseconds with default easing.</li>
 * <li>String: Name of easing to use with default duration.</li>
 * <li>Object: Animation settings with easing and duration properties.</li>
 * <ul>
 * <li>Can also contain a down property with any of the above options.</li>
 * <li>"Down" animations occur when the panel being activated has a lower index than the currently
 * active panel.</li>
 * </ul>
 * </ul>
 * 
 * @author Stephane Gleizes
 * @since 6.9.2
 */
public class AccordionAnimateOption extends GenericAnimateOption<AccordionEffectOptionObject>
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
	public AccordionAnimateOption(Boolean booleanParam)
	{
		super(booleanParam);
	}

	/**
	 * Constructor
	 * 
	 * @param integerParam
	 *            integer parameter
	 */
	public AccordionAnimateOption(Integer integerParam)
	{
		super(integerParam);
	}

	/**
	 * Constructor
	 * 
	 * @param literalParam
	 *            literal parameter
	 */
	public AccordionAnimateOption(String literalParam)
	{
		super(literalParam);
	}

	/**
	 * Constructor
	 * 
	 * @param objectParam
	 *            object parameter
	 */
	public AccordionAnimateOption(AccordionEffectOptionObject objectParam)
	{
		super(objectParam);
	}
}

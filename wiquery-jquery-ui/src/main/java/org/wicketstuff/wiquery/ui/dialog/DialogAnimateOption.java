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
package org.wicketstuff.wiquery.ui.dialog;


import org.wicketstuff.wiquery.ui.options.EffectOptionObject;
import org.wicketstuff.wiquery.ui.options.GenericAnimateOption;

/**
 * $Id: DialogAnimateOption.java
 * <p>
 * Bean for the show, hide options for the Dialog component
 * </p>
 * Multiple types supported:
 * <ul>
 * <li>Number: The dialog will fade in while animating the height and width for the specified
 * duration.</li>
 * <li>String: The dialog will be shown using the specified jQuery UI effect.
 * <li>Object: If the value is an object, then effect, delay, duration, and easing properties may be
 * provided. The effect property must be the name of a jQuery UI effect. If duration or easing is
 * omitted, then the default values will be used. If effect is omitted, then "fadeIn" will be used.
 * If delay is omitted, then no delay is used.
 * </ul>
 * 
 * @author Stephane Gleizes
 * @since 6.9.2
 */
public class DialogAnimateOption extends GenericAnimateOption<EffectOptionObject>
{
	// Constants
	/** Constant of serialization */
	private static final long serialVersionUID = -2210690093704208073L;

	/**
	 * Constructor
	 * 
	 * @param integerParam
	 *            integer parameter
	 */
	public DialogAnimateOption(Integer integerParam)
	{
		super(integerParam);
	}

	/**
	 * Constructor
	 * 
	 * @param literalParam
	 *            literal parameter
	 */
	public DialogAnimateOption(String literalParam)
	{
		super(literalParam);
	}

	/**
	 * Constructor
	 * 
	 * @param objectParam
	 *            object parameter
	 */
	public DialogAnimateOption(EffectOptionObject objectParam)
	{
		super(objectParam);
	}
}

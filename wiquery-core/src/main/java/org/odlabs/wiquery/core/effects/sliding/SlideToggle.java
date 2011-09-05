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
package org.odlabs.wiquery.core.effects.sliding;

import org.odlabs.wiquery.core.effects.Effect;
import org.odlabs.wiquery.core.effects.EffectSpeed;

/**
 * $Id$
 * <p>
 * Defines the slideToggle {@link Effect}. A slide down effect consists to slide toggle
 * the visibility of all matched elements by adjusting their height
 * </p>
 * 
 * @author Julien Roche
 * @since 1.1
 */
public class SlideToggle extends Effect
{
	// Constants
	/** Constant of serialization */
	private static final long serialVersionUID = -5701176979001030721L;

	/**
	 * Creates this effect
	 */
	public SlideToggle()
	{
		super();
	}

	/**
	 * Creates this effect with the given {@link EffectSpeed}.
	 */
	public SlideToggle(EffectSpeed effectSpeed)
	{
		super(effectSpeed);
	}

	public String chainLabel()
	{
		return "slideToggle";
	}

}
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
package org.odlabs.wiquery.core.effects.fading;

import org.odlabs.wiquery.core.effects.Effect;
import org.odlabs.wiquery.core.effects.EffectSpeed;

/**
 * $Id: FadeTo.java 1714M 2012-01-17 08:29:01Z (local) $
 * <p>
 * Defines the fade to {@link Effect}. A fade to consists to fade a component to a
 * specified opacity value.
 * </p>
 * 
 * @author Lionel Armanet
 * @since 0.7
 */
public class FadeTo extends Effect
{
	// Constants
	/** Constant of serialization */
	private static final long serialVersionUID = 5145053114082342165L;

	/**
	 * Creates this effect
	 */
	public FadeTo()
	{
		super();
	}

	/**
	 * Constructs a new fadeto effect executing at the given {@link EffectSpeed} and
	 * fading to the given opacity.
	 * 
	 * @param effectSpeed
	 * @param opacity
	 */
	public FadeTo(EffectSpeed effectSpeed, float opacity)
	{
		super(effectSpeed, Float.toString(opacity));
	}

	@Override
	public String chainLabel()
	{
		return "fadeTo";
	}

}

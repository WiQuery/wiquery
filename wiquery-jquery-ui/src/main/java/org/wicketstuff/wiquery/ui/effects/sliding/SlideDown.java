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
package org.wicketstuff.wiquery.ui.effects.sliding;

import org.wicketstuff.wiquery.ui.effects.Effect;
import org.wicketstuff.wiquery.ui.effects.EffectSpeed;

/**
 * $Id: SlideDown.java 1714M 2012-01-17 08:29:10Z (local) $
 * <p>
 * Defines the slidedown {@link Effect}. A slide down effect consists to slide a component in a
 * top-bottom direction.
 * </p>
 * 
 * @author Lionel Armanet
 * @since 0.5
 */
public class SlideDown extends Effect
{
	// Constants
	/** Constant of serialization */
	private static final long serialVersionUID = -1708160228524287367L;

	/**
	 * Creates this effect
	 */
	public SlideDown()
	{
		super();
	}

	/**
	 * Creates this effect with the given {@link EffectSpeed}.
	 */
	public SlideDown(EffectSpeed effectSpeed)
	{
		super(effectSpeed);
	}

	@Override
	public String chainLabel()
	{
		return "slideDown";
	}

}

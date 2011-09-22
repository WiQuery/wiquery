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
package org.odlabs.wiquery.core.effects.basic;

import org.odlabs.wiquery.core.effects.Effect;
import org.odlabs.wiquery.core.effects.EffectSpeed;

/**
 * $Id$
 * <p>
 * Defines the toggle {@link Effect}. A toggle consists to show a component when it's
 * hidden, and to hide it when it's displayed.
 * </p>
 * 
 * @author Lionel Armanet, reiern70
 * @since 0.7
 */
public class Toggle extends Effect
{

	private static final long serialVersionUID = 235427840472391563L;

	/**
	 * Default constructor.
	 */
	public Toggle()
	{
		super();
	}

	/**
	 * Creates this effect with the given {@link EffectSpeed}.
	 * 
	 * @param effectSpeed
	 */
	public Toggle(EffectSpeed effectSpeed)
	{
		super(effectSpeed);
	}

	public String chainLabel()
	{
		return "toggle";
	}

}

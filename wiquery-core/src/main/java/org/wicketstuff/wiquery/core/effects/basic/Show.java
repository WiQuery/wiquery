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
package org.wicketstuff.wiquery.core.effects.basic;

import org.wicketstuff.wiquery.core.effects.Effect;
import org.wicketstuff.wiquery.core.effects.EffectSpeed;

/**
 * $Id: Show.java 1714M 2012-01-17 08:28:22Z (local) $
 * <p>
 * Defines the show {@link Effect}.
 * </p>
 * 
 * @author Lionel Armanet
 * @since 0.5
 */
public class Show extends Effect
{

	private static final long serialVersionUID = -6269041578524307191L;

	/**
	 * Default constructor.
	 */
	public Show()
	{
		super();
	}

	/**
	 * Creates this effect with the given {@link EffectSpeed}.
	 * 
	 * @param effectSpeed
	 */
	public Show(EffectSpeed effectSpeed)
	{
		super(effectSpeed);
	}

	@Override
	public String chainLabel()
	{
		return "show";
	}

}

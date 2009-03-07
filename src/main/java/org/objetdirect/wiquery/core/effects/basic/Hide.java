/*
 * Copyright (c) 2008 Objet Direct
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
package org.objetdirect.wiquery.core.effects.basic;

import org.objetdirect.wiquery.core.effects.Effect;
import org.objetdirect.wiquery.core.effects.EffectSpeed;

/**
 * $Id$
 * <p>
 * 	Defines the hide {@link Effect}.
 * </p>
 * @author Lionel Armanet
 * @since 0.5
 */
public class Hide extends Effect {

	/**
	 * Default constructor.
	 */
	public Hide() {
		super();
	}

	/**
	 * Creates this effect with the given {@link EffectSpeed}.
	 * @param effectSpeed
	 */
	public Hide(EffectSpeed effectSpeed) {
		super(effectSpeed);
	}
	
	/* (non-Javadoc)
	 * @see org.objetdirect.wickext.core.javascript.ChainableStatement#chainLabel()
	 */
	public String chainLabel() {
		return "hide";
	}

}

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
package org.odlabs.wiquery.ui.effects;

import org.odlabs.wiquery.core.effects.Effect;
import org.odlabs.wiquery.core.javascript.JsUtils;

/**
 * $Id$
 * Helper class to implement the shake effect as in 
 * 
 * http://docs.jquery.com/UI/Effects/Shake
 * 
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 * @since 1.0.2
 */
public class BounceEffect extends Effect {

	private static final long serialVersionUID = 1L;

	/**
	 * Shake direction.
	 * 
	 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
	 *
	 */
	public static enum BounceDirection {
		up, down, left, right;
	}
	
	/**
	 * The bounce mode.
	 * 
	 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
	 *
	 */
	public static enum BounceMode {
		show, hide, effect;
	}
	
	/**
	 * Constructor.
	 */
	public BounceEffect() {
		this(BounceDirection.up, BounceMode.effect, 5, 20, 200);		
	}
	
	/**
	 * Constructor.
	 * 
	 * @param direction The direction.
	 */
	public BounceEffect(BounceDirection direction) {
		this(direction, BounceMode.effect, 5, 20, 200);		
	}
	
	/**
	 * Constructor.
	 * 
	 * @param direction The direction used to shake it.
	 * @param times show many time to shake it.
	 * @param distance The distance 
	 * @param duration The duration
	 */
	public BounceEffect(BounceDirection direction, BounceMode mode, int times, int distance,  int duration) {
		super(JsUtils.quotes("bounce"),
				"{direction:"
				+ JsUtils.quotes(direction.name())
				+", times: "+ Integer.toString(times)
				
				+", mode: "+ JsUtils.quotes(mode.name())
				+", distance:" + Integer.toString(distance)				
				+"}", Integer.toString(duration));
	}
		

	/* (non-Javadoc)
	 * @see org.odlabs.wiquery.core.javascript.ChainableStatement#chainLabel()
	 */
	public String chainLabel() {
		return "effect";
	}

}

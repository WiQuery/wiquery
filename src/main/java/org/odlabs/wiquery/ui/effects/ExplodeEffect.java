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
 * Helper class to implement the explode effect as in 
 * 
 * http://docs.jquery.com/UI/Effects/Explode
 * 
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 * @since 1.0.2
 */
public class ExplodeEffect extends Effect {

	private static final long serialVersionUID = 1L;

	/**
	 * The explode mode.
	 * 
	 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com) 
	 *
	 */
	public static enum Mode {
		show,
		hide;
	}
	
	private Mode mode;
	
	/**
	 * Constructor.
	 * 
	 * @param mode The mode
	 */
	public ExplodeEffect(Mode mode) {
		this(mode, 1000);		
	}
	
	
	/**
	 * Constructor.
	 * 
	 * @param mode The mode
	 * @param duration the duration.
	 */
	public ExplodeEffect(Mode mode, int duration) {
		super(JsUtils.quotes("explode"), Integer.toString(duration));
		this.mode = mode;
	}
	
	/**
	 * Constructor.
	 * 
	 * @param mode The mede
	 * @param pieces How many pieces
	 * @param duration The duration of the effect.
	 */
	public ExplodeEffect(Mode mode, int pieces, int duration) {
		super(JsUtils.quotes("explode"), "{pieces: "+ Integer.toString(pieces)+"}",Integer.toString(duration));
		this.mode = mode;
	}
	

	/* (non-Javadoc)
	 * @see org.odlabs.wiquery.core.javascript.ChainableStatement#chainLabel()
	 */
	public String chainLabel() {
		return getMode().name();
	}

	/**
	 * Gets the mode.
	 * @return
	 */
	public Mode getMode() {
		return mode;
	}


}

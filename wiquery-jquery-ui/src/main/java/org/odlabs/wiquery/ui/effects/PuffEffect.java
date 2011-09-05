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
 * $Id$ Helper class to
 * implement the puff effect as in
 * 
 * http://docs.jquery.com/UI/Effects/Puff
 * 
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 * @since 1.0.2
 */
public class PuffEffect extends Effect
{

	private static final long serialVersionUID = 1L;

	/**
	 * The explode mode.
	 * 
	 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
	 * 
	 */
	public static enum Mode
	{
		show,
		hide;
	}

	private Mode mode;

	/*
	 * 
	 */
	public PuffEffect()
	{
		this(Mode.hide, 1000);
	}

	/**
	 * 
	 * @param mode
	 */
	public PuffEffect(Mode mode)
	{
		this(mode, 1000);
	}

	/**
	 * 
	 * @param mode
	 * @param duration
	 */
	public PuffEffect(Mode mode, int duration)
	{
		super(JsUtils.quotes("explode"), Integer.toString(duration));
		this.mode = mode;
	}

	/**
	 * 
	 * @param mode
	 * @param pieces
	 * @param duration
	 */
	public PuffEffect(Mode mode, int percent, int duration)
	{
		super(JsUtils.quotes("explode"), "{percent: " + Integer.toString(percent) + "}", Integer
			.toString(duration));
		this.mode = mode;
	}

	public String chainLabel()
	{
		return getMode().name();
	}

	public Mode getMode()
	{
		return mode;
	}

	public void setMode(Mode mode)
	{
		this.mode = mode;
	}

}

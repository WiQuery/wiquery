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
package org.wicketstuff.wiquery.ui.effects;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.wicketstuff.wiquery.core.javascript.ChainableStatement;
import org.wicketstuff.wiquery.core.javascript.JsScope;
import org.wicketstuff.wiquery.core.javascript.JsUtils;

/**
 * $Id: Effect.java 1714M 2012-01-17 08:26:19Z (local) $
 * <p>
 * Defines an abstract effect. Any jQuery effect has the same format:
 * <code>.effectName(speed, [callback])</code>
 * </p>
 * <p>
 * An effect is a {@link ChainableStatement}, so all subclasses will have to implement
 * {@link ChainableStatement#chainLabel()} to indicate the effect name.
 * </p>
 * 
 * @author Lionel Armanet
 * @since 0.5
 * @see EffectSpeed
 * @see ChainableStatement
 */
public abstract class Effect implements ChainableStatement, Serializable
{
	// Constants
	/** Constant of serialization */
	private static final long serialVersionUID = 6498661896790365888L;

	// Properties
	/**
	 * The list of parameters to apply to the effect.
	 */
	private List<CharSequence> parameters;

	/** Callback on the effect */
	private JsScope callback;

	/**
	 * Creates a new effect.
	 * 
	 * @param parameters
	 *            the list of parameters to apply to the effect.
	 */
	public Effect(CharSequence... parameters)
	{
		this.parameters = new ArrayList<>(Arrays.asList(parameters));
	}

	/**
	 * Creates a new effect.
	 * 
	 * @param effectSpeed
	 *            the speed to display the effect.
	 * @param parameters
	 *            the list of parameters to apply to the effect.
	 */
	public Effect(EffectSpeed effectSpeed, CharSequence... parameters)
	{
		this(parameters);
		this.parameters.add(0, JsUtils.quotes(effectSpeed.getJavaScriptStatement()));
	}

	/**
	 * Creates a new effect.
	 * 
	 * @param effectSpeed
	 *            the speed to display the effect.
	 * @param callback
	 *            Callback on the effect
	 * @param parameters
	 *            the list of parameters to apply to the effect.
	 */
	public Effect(EffectSpeed effectSpeed, JsScope callback, CharSequence... parameters)
	{
		this(parameters);
		this.parameters.add(0, JsUtils.quotes(effectSpeed.getJavaScriptStatement()));
		this.callback = callback;
	}

	@Override
	public CharSequence[] statementArgs()
	{
		List<CharSequence> ret = new ArrayList<>(this.parameters);
		if (this.effectCallback() != null)
		{
			ret.add(this.effectCallback().render());
		}
		return ret.toArray(new CharSequence[ret.size()]);
	}

	/**
	 * The JavaScript code to execute after the effect is over.
	 * 
	 * @return the {@link JsScope} to execute.
	 */
	public JsScope effectCallback()
	{
		return this.callback;
	}

	/**
	 * Sets the callback of this effect.
	 * 
	 * @param callback
	 *            A {@link JsScope} defining the callback
	 */
	public void setCallback(JsScope callback)
	{
		this.callback = callback;
	}

}

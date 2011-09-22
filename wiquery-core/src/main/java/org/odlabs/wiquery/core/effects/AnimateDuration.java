/*
 * Copyright (c) 2010 WiQuery team
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
package org.odlabs.wiquery.core.effects;

import java.io.Serializable;

import org.apache.wicket.WicketRuntimeException;
import org.odlabs.wiquery.core.javascript.JsUtils;
import org.odlabs.wiquery.core.options.IComplexOption;

/**
 * $Id$
 * 
 * <p>
 * Animate duration
 * </p>
 * 
 * @author Julien Roche
 * @since 1.2
 */
public class AnimateDuration implements Serializable, IComplexOption
{
	// Constants
	/** Constant of serialization */
	private static final long serialVersionUID = -110965393283295820L;

	// Properties
	private EffectSpeed effectSpeed;

	private Integer speed;

	/**
	 * Constructor
	 * 
	 * @param effectSpeed
	 *            {@link EffectSpeed}
	 */
	public AnimateDuration(EffectSpeed effectSpeed)
	{
		setSpeed(effectSpeed, null);
	}

	/**
	 * Constructor
	 * 
	 * @param speed
	 *            Numerical speed
	 */
	public AnimateDuration(Integer speed)
	{
		setSpeed(null, speed);
	}

	/**
	 * @return the {@link EffectSpeed}
	 */
	public EffectSpeed getEffectSpeed()
	{
		return effectSpeed;
	}

	public CharSequence getJavascriptOption()
	{
		if (speed == null && effectSpeed == null)
		{
			throw new WicketRuntimeException("AnimateDuration: you must specify a speed !!");
		}

		if (speed != null)
		{
			return speed.toString();
		}

		return JsUtils.quotes(effectSpeed.getJavaScriptStatement());
	}

	/**
	 * @return the speed
	 */
	public Integer getSpeed()
	{
		return speed;
	}

	/**
	 * Set the speed with {@link EffectSpeed}
	 */
	public void setEffectSpeed(EffectSpeed effectSpeed)
	{
		setSpeed(effectSpeed, null);
	}

	/**
	 * Set the speed
	 */
	private void setSpeed(EffectSpeed effectSpeed, Integer speed)
	{
		this.effectSpeed = effectSpeed;
		this.speed = speed;
	}

	/**
	 * Set the speed with a numerical
	 */
	public void setSpeed(Integer speed)
	{
		setSpeed(null, speed);
	}
}

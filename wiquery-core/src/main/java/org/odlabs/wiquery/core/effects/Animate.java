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
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.wicket.WicketRuntimeException;
import org.odlabs.wiquery.core.javascript.ChainableStatement;
import org.odlabs.wiquery.core.javascript.JsScope;
import org.odlabs.wiquery.core.options.Options;

/**
 * $Id$
 * 
 * <p>
 * {@link ChainableStatement} to bind the animate method on the wicket elements with
 * jQuery
 * </p>
 * 
 * <p>
 * See http://api.jquery.com/animate/
 * </p>
 * 
 * @author Julien Roche
 * @since 1.2
 */
public class Animate implements ChainableStatement, Serializable
{
	// Constants
	/** Constant of serialization */
	private static final long serialVersionUID = 84879219828856512L;

	// Properties
	private Map<String, String> properties;

	private Map<String, Serializable> options;

	/**
	 * Constructor
	 * 
	 * @param properties
	 *            Map of CSS properties
	 * @param duration
	 *            Duration of the effect
	 */
	public Animate(Map<String, String> properties, AnimateDuration duration)
	{
		this(properties, duration, null, null);
	}

	/**
	 * Constructor
	 * 
	 * @param properties
	 *            Map of CSS properties
	 * @param duration
	 *            Duration of the effect
	 * @param easing
	 *            Name of the easing method (linear, swing ...)
	 */
	public Animate(Map<String, String> properties, AnimateDuration duration, String easing)
	{
		this(properties, duration, easing, null);
	}

	/**
	 * Constructor
	 * 
	 * @param properties
	 *            Map of CSS properties
	 * @param duration
	 *            Duration of the effect
	 * @param easing
	 *            Name of the easing method (linear, swing ...)
	 * @param callback
	 *            Callback for the end of the animation
	 */
	public Animate(Map<String, String> properties, AnimateDuration duration, String easing,
			JsScope callback)
	{
		if (properties == null)
		{
			throw new WicketRuntimeException("properties cannot be null");
		}

		this.properties = properties;
		this.options = new HashMap<String, Serializable>();
		this.options.put("duration", duration);
		this.options.put("easing", easing);
		this.options.put("complete", callback);
	}

	/**
	 * Constructor
	 * 
	 * @param properties
	 *            Map of CSS properties
	 * @param options
	 *            Map of options (duration, easing, complete, step, queue, specialEasing)
	 * 
	 *            # duration: A string or number determining how long the animation will
	 *            run. # easing: A string indicating which easing function to use for the
	 *            transition. # complete: A function to call once the animation is
	 *            complete. # step: A function to be called after each step of the
	 *            animation. # queue: A Boolean indicating whether to place the animation
	 *            in the effects queue. If false, the animation will begin immediately. #
	 *            specialEasing: A map of one or more of the CSS properties defined by the
	 *            properties argument and their corresponding easing functions
	 */
	public Animate(Map<String, String> properties, Map<String, Serializable> options)
	{
		if (properties == null)
		{
			throw new WicketRuntimeException("properties cannot be null");
		}

		this.properties = properties;
		this.options = options == null ? new HashMap<String, Serializable>() : options;
	}

	/**
	 * {@inheritDooc}
	 * 
	 * @see org.odlabs.wiquery.core.javascript.ChainableStatement#chainLabel()
	 */
	public String chainLabel()
	{
		return "animate";
	}

	/**
	 * @return the complete option
	 */
	public JsScope getComplete()
	{
		Object object = this.options.get("complete");
		return object instanceof JsScope ? (JsScope) object : null;
	}

	/**
	 * @return the duration option
	 */
	public AnimateDuration getDuration()
	{
		Object object = this.options.get("duration");
		return object instanceof AnimateDuration ? (AnimateDuration) object : null;
	}

	/**
	 * @return the duration option
	 */
	public String getEasing()
	{
		Object object = this.options.get("easing");
		return object instanceof String ? (String) object : null;
	}

	/**
	 * @return the specialEasing
	 */
	@SuppressWarnings("unchecked")
	public Map<String, String> getSpecialEasing()
	{
		Object object = this.options.get("specialEasing");
		return object instanceof Map< ? , ? > ? (Map<String, String>) object : null;
	}

	/**
	 * @return the step option
	 */
	public JsScope getStep()
	{
		Object object = this.options.get("step");
		return object instanceof JsScope ? (JsScope) object : null;
	}

	/**
	 * @return the queue option
	 */
	public Boolean isQueue()
	{
		Object object = this.options.get("queue");
		return object instanceof Boolean ? (Boolean) object : null;
	}

	/**
	 * Set the complete option
	 * 
	 * @param complete
	 *            Complete
	 * 
	 * @return the current instance of the animation
	 */
	public Animate setComplete(JsScope complete)
	{
		this.options.put("complete", complete);
		return this;
	}

	/**
	 * Set the duration option
	 * 
	 * @param duration
	 *            Duration
	 * 
	 * @return the current instance of the animation
	 */
	public Animate setDuration(AnimateDuration duration)
	{
		this.options.put("duration", duration);
		return this;
	}

	/**
	 * Set the easing option
	 * 
	 * @param easing
	 *            Easing
	 * 
	 * @return the current instance of the animation
	 */
	public Animate setEasing(String easing)
	{
		this.options.put("easing", easing);
		return this;
	}

	/**
	 * Set the specialEasing option
	 * 
	 * @param specialEasing
	 *            Queue
	 * 
	 * @return the current instance of the animation
	 */
	public Animate setSpecialEasing(HashMap<String, String> specialEasing)
	{
		this.options.put("specialEasing", specialEasing);
		return this;
	}

	/**
	 * Set the queue option
	 * 
	 * @param queue
	 *            Queue
	 * 
	 * @return the current instance of the animation
	 */
	public Animate setStep(Boolean queue)
	{
		this.options.put("queue", queue);
		return this;
	}

	/**
	 * Set the step option
	 * 
	 * @param step
	 *            Step
	 * 
	 * @return the current instance of the animation
	 */
	public Animate setStep(JsScope step)
	{
		this.options.put("step", step);
		return this;
	}

	public CharSequence[] statementArgs()
	{
		CharSequence[] argsArray = new CharSequence[2];

		// First css properties
		Options opts = new Options();
		for (Entry<String, String> entry : this.properties.entrySet())
		{
			opts.putLiteral(entry.getKey(), entry.getValue());
		}
		argsArray[0] = opts.getJavaScriptOptions();

		// Now, the option properties
		opts = new Options(); // Flush

		if (getComplete() != null)
		{
			opts.put("complete", getComplete());
		}

		if (getDuration() != null)
		{
			opts.put("duration", getDuration().getJavascriptOption().toString());
		}

		if (getEasing() != null)
		{
			opts.putLiteral("easing", getEasing());
		}

		if (getStep() != null)
		{
			opts.put("step", getStep());
		}

		if (isQueue() != null)
		{
			opts.put("queue", isQueue());
		}

		if (getSpecialEasing() != null)
		{
			Options opts2 = new Options();
			for (Entry<String, String> entry : getSpecialEasing().entrySet())
			{
				opts2.putLiteral(entry.getKey(), entry.getValue());
			}
			opts.put("specialEasing", opts2.getJavaScriptOptions().toString());
		}

		argsArray[1] = opts.getJavaScriptOptions();

		return argsArray;
	}
}

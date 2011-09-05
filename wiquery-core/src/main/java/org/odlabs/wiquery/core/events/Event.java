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
package org.odlabs.wiquery.core.events;

import java.io.Serializable;

import org.odlabs.wiquery.core.javascript.ChainableStatement;
import org.odlabs.wiquery.core.javascript.JsScope;
import org.odlabs.wiquery.core.javascript.JsUtils;

/**
 * $Id$
 * <p>
 * Executes the given {@link JsScope} statement when a given list of {@link EventLabel}
 * happened.
 * </p>
 * 
 * @author Lionel Armanet
 * @since 0.5
 */
public abstract class Event implements ChainableStatement, Serializable
{
	// Constants
	/** Constant of serialization */
	private static final long serialVersionUID = 6498129892490365888L;

	/**
	 * The list of events triggering the {@link JsScope}.
	 */
	protected EventLabel[] eventLabels;

	/**
	 * Creates a new event binding a {@link JsScope} to the given {@link EventLabel}.
	 */
	public Event(EventLabel... eventLabels)
	{
		super();
		this.eventLabels = eventLabels;
	}

	public String chainLabel()
	{
		// all effects are bound with the bind method
		return "bind";
	}

	public CharSequence[] statementArgs()
	{
		String firstArg = JsUtils.implode(eventLabels);
		String callback = this.callback().render().toString();
		return new String[] {firstArg, callback};
	}

	/**
	 * Returns an non null executable {@link JsScope}. This {@link JsScope} will be called
	 * back when the event triggers.
	 */
	public abstract JsScope callback();

}

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

import org.odlabs.wiquery.core.behavior.WiQueryAbstractAjaxBehavior;
import org.odlabs.wiquery.core.javascript.JsQuery;
import org.odlabs.wiquery.core.javascript.JsStatement;

/**
 * $Id: WiQueryEventBehavior.java 1714M 2012-01-19 16:56:55Z (local) $
 * <p>
 * Calls a JavaScript statement when the given {@link Event} is triggered.
 * </p>
 * 
 * @author Lionel Armanet
 * @since 0.5
 */
public class WiQueryEventBehavior extends WiQueryAbstractAjaxBehavior
{

	private static final long serialVersionUID = -5984090566307323188L;

	/**
	 * The trigger event.
	 */
	private final Event event;

	/**
	 * Builds a new instance of {@link WiQueryEventBehavior}.
	 * 
	 * @param event
	 *            the {@link Event} triggering the JavaScript statement.
	 */
	public WiQueryEventBehavior(Event event)
	{
		super();
		this.event = event;
	}

	@Deprecated
	@Override
	public JsStatement statement()
	{
		return new JsQuery(this.getComponent()).$().chain(event);
	}

}

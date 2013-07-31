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

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.attributes.AjaxRequestAttributes;
import org.odlabs.wiquery.core.behavior.WiQueryAbstractAjaxBehavior;
import org.odlabs.wiquery.core.javascript.JsScope;
import org.odlabs.wiquery.core.javascript.JsScopeContext;

/**
 * $Id: WiQueryAjaxEventBehavior.java 1714M 2012-01-17 08:32:44Z (local) $
 * <p>
 * Binds the given {@link Event} to an Ajax request.
 * </p>
 * 
 * @author Lionel Armanet
 * @since 0.5
 */
public abstract class WiQueryAjaxEventBehavior extends WiQueryAbstractAjaxBehavior
{
	// Constants
	/** Constant of serialization */
	private static final long serialVersionUID = 6498661892490377987L;

	/**
	 * The event tiggering the Ajax call.
	 */
	private EventLabel[] events;

	public WiQueryAjaxEventBehavior(EventLabel... events)
	{
		super();
		this.events = events;
	}

	@Override
	protected void updateAjaxAttributes(AjaxRequestAttributes attributes)
	{
		// attributes.getPreconditions().add(new JavaScriptPrecondition("return true"));
	}

	@Override
	protected void onBind()
	{
		super.onBind();

		this.getComponent().add(new WiQueryEventBehavior(new Event(this.events)
		{

			private static final long serialVersionUID = 1L;

			@Override
			public JsScope callback()
			{
				return new JsScope("event")
				{

					private static final long serialVersionUID = 1L;

					@Override
					protected void execute(JsScopeContext scopeContext)
					{
						StringBuilder callback = new StringBuilder();
						callback.append("if (").append(getPrecondition()).append(") {");
						callback.append(WiQueryAjaxEventBehavior.this.getCallbackScript());
						callback.append('}');
						scopeContext.append(callback);
					}
				};

			}

		}));
	}

	@Override
	protected void respond(AjaxRequestTarget target)
	{
		// just a rename, not to depend on Wicket's refactoring
		this.onEvent(target);
	}

	/**
	 * onEvent is called back when the ajax request is made.
	 * 
	 * @param target
	 *            The Ajax request target
	 */
	protected abstract void onEvent(AjaxRequestTarget target);

	protected String getPrecondition()
	{
		return "true";
	}

}

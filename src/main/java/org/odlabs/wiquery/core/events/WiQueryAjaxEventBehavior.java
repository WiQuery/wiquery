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
package org.odlabs.wiquery.core.events;

import org.apache.wicket.ajax.AbstractDefaultAjaxBehavior;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.odlabs.wiquery.core.javascript.JsScope;

/**
 * $Id$
 * <p>
 * Binds the given {@link Event} to an Ajax request.
 * </p>
 * 
 * @author Lionel Armanet
 * @since 0.5
 */
public abstract class WiQueryAjaxEventBehavior extends
		AbstractDefaultAjaxBehavior {

	/**
	 * The event tiggering the Ajax call.
	 */
	private EventLabel[] events;

	public WiQueryAjaxEventBehavior(EventLabel...events) {
		super();
		this.events = events;
	}
	
	@Override
	protected CharSequence getPreconditionScript() {
		return "return true";
	}

	@Override
	protected void onBind() {
		this.getComponent().add(new WiQueryEventBehavior(new Event(this.events) {

			private static final long serialVersionUID = 1L;

			@Override
			public JsScope callback() {
				return JsScope.quickScope(WiQueryAjaxEventBehavior.this.getCallbackScript());
			}
		
		}));
		super.onBind();
	}



	@Override
	protected void respond(AjaxRequestTarget target) {
		// just a rename, not to depend on Wicket's refactoring
		this.onEvent(target);
	}



	protected abstract void onEvent(AjaxRequestTarget target);
	
}

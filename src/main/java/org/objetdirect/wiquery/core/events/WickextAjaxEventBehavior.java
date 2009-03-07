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
package org.objetdirect.wiquery.core.events;

import org.apache.wicket.ajax.AbstractDefaultAjaxBehavior;
import org.apache.wicket.behavior.HeaderContributor;
import org.apache.wicket.markup.html.IHeaderResponse;
import org.objetdirect.wiquery.core.commons.CoreJavaScriptHeaderContributor;
import org.objetdirect.wiquery.core.javascript.JsQuery;
import org.objetdirect.wiquery.core.javascript.JsScope;

/**
 * $Id$
 * <p>
 * Binds the given {@link Event} to an Ajax request.
 * </p>
 * 
 * @author Lionel Armanet
 * @since 0.5
 */
public abstract class WickextAjaxEventBehavior extends
		AbstractDefaultAjaxBehavior {

	/**
	 * The event tiggering the Ajax call.
	 */
	private EventLabel[] events;

	/**
	 * Builds a new instance of {@link WickextAjaxEventBehavior} triggering an
	 * Ajax call for the given {@link Event}.
	 * 
	 * @param events
	 *            the array of {@link Event} that triggers the Ajax call.
	 */
	public WickextAjaxEventBehavior(EventLabel...events) {
		super();
		this.events = events;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.apache.wicket.ajax.AbstractDefaultAjaxBehavior#onBind()
	 */
	@Override
	protected void onBind() {
		this.getComponent().getPage().add(
				new HeaderContributor(new CoreJavaScriptHeaderContributor()));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.apache.wicket.ajax.AbstractDefaultAjaxBehavior#getCallbackScript()
	 */
	@Override
	protected CharSequence getCallbackScript() {
		// binds the ajax call to the wrapped event
		JsQuery query = new JsQuery(this.getComponent());
		Event event = new Event(this.events) {
		
			@Override
			public JsScope callback() {
				return JsScope.quickScope(getHandlerScript());
			}
		
		};
		return query.$().chain(event).render();
	}

	protected CharSequence getHandlerScript() {
		return super.getCallbackScript();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.apache.wicket.ajax.AbstractDefaultAjaxBehavior#getPreconditionScript()
	 */
	@Override
	protected CharSequence getPreconditionScript() {
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.apache.wicket.ajax.AbstractDefaultAjaxBehavior#renderHead(org.apache.wicket.markup.html.IHeaderResponse)
	 */
	@Override
	public void renderHead(IHeaderResponse response) {
		super.renderHead(response);
		// renders the JavaScript statement
		JsQuery query = new JsQuery(this.getComponent());
		Event event = new Event(this.events) {
		
			@Override
			public JsScope callback() {
				return JsScope.quickScope(getHandlerScript());
			}
		
		};
		query.$().chain(event);
		query.renderHead(response);
	}
}

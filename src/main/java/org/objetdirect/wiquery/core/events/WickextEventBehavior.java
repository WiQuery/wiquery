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

import org.apache.wicket.Component;
import org.apache.wicket.behavior.AbstractBehavior;
import org.apache.wicket.behavior.HeaderContributor;
import org.apache.wicket.markup.html.IHeaderResponse;
import org.objetdirect.wiquery.core.commons.CoreJavaScriptHeaderContributor;
import org.objetdirect.wiquery.core.javascript.JsQuery;

/**
 * $Id$
 * <p>
 * Calls a JavaScript statement when the given {@link Event} is triggered.
 * </p>
 * 
 * @author Lionel Armanet
 * @since 0.5
 */
public class WickextEventBehavior extends AbstractBehavior {

	private static final long serialVersionUID = -5984090566307323188L;

	/**
	 * The trigger event.
	 */
	private Event event;

	/**
	 * The event is attached to this component.
	 */
	private Component component;

	/**
	 * Builds a new instance of {@link WickextEventBehavior}.
	 * 
	 * @param event
	 *            the {@link Event} triggering the JavaScript statement.
	 */
	public WickextEventBehavior(Event event) {
		super();
		this.event = event;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.apache.wicket.behavior.AbstractBehavior#bind(
	 *      org.apache.wicket.Component)
	 */
	@Override
	public void bind(Component component) {
		super.bind(component);
		this.component = component;
		component.getPage().add(new HeaderContributor(new CoreJavaScriptHeaderContributor()));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.apache.wicket.behavior.AbstractBehavior#renderHead(
	 *      org.apache.wicket.markup.html.IHeaderResponse)
	 */
	@Override
	public void renderHead(IHeaderResponse response) {
		// builds a jQuery statement to fire this event. The JavaScript
		// code to trigger after that is given by #callbackStatement()
		JsQuery query = new JsQuery(component);
		query.$().chain(event);
		query.renderHead(response);
	}

}

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
package org.wicketstuff.wiquery.core.javascript;

import java.io.Serializable;

import org.apache.wicket.Component;
import org.apache.wicket.behavior.Behavior;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.JavaScriptHeaderItem;
import org.apache.wicket.markup.head.OnDomReadyHeaderItem;
import org.apache.wicket.markup.html.IHeaderContributor;
import org.apache.wicket.request.IRequestHandler;
import org.wicketstuff.wiquery.core.util.WiQueryUtil;

/**
 * $Id: JsQuery.java 1721M 2012-01-17 17:43:37Z (local) $
 * <p>
 * {@link JsQuery} is the main entry point of WickeXt's JavaScript integration. This class
 * is used to link JavaScript to a component and to render it.
 * </p>
 * <p>
 * This class implements the {@link IHeaderContributor} interface, so if you want to
 * append the generated JavaScript, just do this:
 * 
 * <pre>
 *  	<code>
 * JsQuery jsq = new JsQuery(myComponent);
 * jsq.$().chain(&quot;css&quot;, &quot;border&quot;, &quot;1px solid red&quot;);
 * myComponent.add(new HeaderContributor(jsq));
 * </code>
 * </pre>
 * 
 * </p>
 * <p>
 * If you want to generate a statement concerning a component, do it as below:
 * <p>
 * <code>new JsQuery(yourComponent).$().chain("css", "border", "1px solid red"));</code>
 * </p>
 * </p>
 * 
 * @author Lionel Armanet
 * @since 0.7
 */
public class JsQuery extends Behavior implements Serializable
{

	private static final long serialVersionUID = -5351600688981395614L;

	/**
	 * The component attached to the statement.
	 */
	private Component component;

	/**
	 * The built statement.
	 */
	private JsStatement statement;

	/**
	 * Creates a new {@link JsQuery} linked to a {@link Component}.
	 */
	public JsQuery(Component component)
	{
		super();
		this.component = component;
		this.component.setOutputMarkupId(true);
	}

	/**
	 * Creates a new {@link JsQuery}.
	 */
	public JsQuery()
	{

	}

	/**
	 * @return a new {@link JsStatement} initialized with the <code>$</code> statement.
	 */
	public JsStatement $()
	{
		return statement = new JsStatement().$(component);
	}

	/**
	 * Same as {@link #$()} but with a specified CSS selector. If this {@link JsQuery} is
	 * linked to a component, the resulting JavaScript code will be
	 * <p>
	 * <code>$("#yourComponentId cssSelector")</code>
	 * </p>
	 * 
	 * @param selector
	 * @return
	 */
	public JsStatement $(String selector)
	{
		return statement = new JsStatement().$(component, selector);
	}

	/**
	 * @return a new {@link JsStatement} initialized with the <code>document</code>
	 *         statement.
	 */
	public JsStatement document()
	{
		return statement = new JsStatement().document();
	}

	@Override
	public void renderHead(Component component, IHeaderResponse response)
	{
		final String js = statement == null ? null : statement.render().toString();

		if (js != null && js.trim().length() > 0)
		{
			response.render(JavaScriptHeaderItem.forReference(WiQueryUtil
				.getJQueryResourceReference()));

			response.render(OnDomReadyHeaderItem.forScript(js));
		}
	}

	@Override
	public final void bind(Component hostComponent)
	{
		if (hostComponent == null)
		{
			throw new IllegalArgumentException("Argument hostComponent must be not null");
		}

		if (component != null && component != hostComponent)
		{
			throw new IllegalStateException("this kind of handler cannot be attached to "
				+ "multiple components; it is already attached to component " + component
				+ ", but component " + hostComponent + " wants to be attached too");
		}

		component = hostComponent;

		// call the callback
		onBind();
	}

	/**
	 * Called when the component was bound to it's host component. You can get the bound
	 * host component by calling getComponent.
	 */
	protected void onBind()
	{
		component.setOutputMarkupId(true);
	}

	/**
	 * @return the statement
	 */
	public JsStatement getStatement()
	{
		return statement;
	}

	/**
	 * FOR FRAMEWORK'S INTERNAL USE ONLY
	 */
	public void setStatement(JsStatement jsStatement)
	{
		this.statement = jsStatement;
	}

	/**
	 * Adds this statement as Behavior to the component. When the page or ajax request is
	 * rendered this statement will be added.
	 * 
	 * @deprecated use {@link Component#add(Behavior...)}.
	 */
	@Deprecated
	public void contribute(Component component)
	{
		component.add(this);
	}

	/**
	 * FOR FRAMEWORK'S INTERNAL USE ONLY
	 */
	public void renderHead(IHeaderResponse response, IRequestHandler requestHandler)
	{
		final String js = statement == null ? null : statement.render().toString();

		if (js != null && js.trim().length() > 0)
		{
			response.render(OnDomReadyHeaderItem.forScript(js));
		}
	}
}

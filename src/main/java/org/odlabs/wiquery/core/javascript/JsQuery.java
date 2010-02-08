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
package org.odlabs.wiquery.core.javascript;

import java.io.Serializable;

import org.apache.wicket.Component;
import org.apache.wicket.IRequestTarget;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.behavior.HeaderContributor;
import org.apache.wicket.markup.html.IHeaderContributor;
import org.apache.wicket.markup.html.IHeaderResponse;
import org.odlabs.wiquery.core.commons.CoreJavaScriptResourceReference;
import org.odlabs.wiquery.core.commons.WiqueryGeneratedJavaScriptResourceReference;

/**
 * $Id$
 * <p>
 * {@link JsQuery} is the main entry point of WickeXt's JavaScript integration.
 * This class is used to link JavaScript to a component and to render it.
 * </p>
 * <p>
 * This class implements the {@link IHeaderContributor} interface, so if you
 * want to append the generated JavaScript, just do this:
 * 
 * <pre>
 *  	<code>
 * JsQuery jsq = new JsQuery(myComponent);
 * jsq.$().chain(&quot;css&quot;, &quot;border&quot;, &quot;1px solid red&quot;);
 * myComponent.add(new HeaderContributor(jsq));
 * </code>
 *  </pre>
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
public class JsQuery implements Serializable, IHeaderContributor {

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
	public JsQuery(Component component) {
		super();
		this.component = component;
		this.component.setOutputMarkupId(true);
	}

	/**
	 * Creates a new {@link JsQuery}.
	 */
	public JsQuery() {

	}

	/**
	 * @return a new {@link JsStatement} initialized with the <code>$</code>
	 *         statement.
	 */
	public JsStatement $() {
		return statement = new JsStatement().$(component);
	}

	/**
	 * Same as {@link #$()} but with a specified CSS selector. If this
	 * {@link JsQuery} is linked to a component, the resulting JavaScript code
	 * will be
	 * <p>
	 * <code>$("#yourComponentId cssSelector")</code>
	 * </p>
	 * 
	 * @param selector
	 * @return
	 */
	public JsStatement $(String selector) {
		return statement = new JsStatement().$(component, selector);
	}

	/**
	 * @return a new {@link JsStatement} initialized with the
	 *         <code>document</code> statement.
	 */
	public JsStatement document() {
		return statement = new JsStatement().document();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.apache.wicket.markup.html.IHeaderContributor#renderHead(org.apache.wicket.markup.html.IHeaderResponse)
	 */
	public void renderHead(IHeaderResponse response) {
		response.renderJavascriptReference(CoreJavaScriptResourceReference
				.get());
		IRequestTarget requestTarget = component.getRequestCycle()
				.getRequestTarget();
		if (requestTarget == null
				|| !(requestTarget instanceof AjaxRequestTarget)) {
			// appending component statement
			// on dom ready, the code is executed.
			JsStatement onreadyStatement = new JsStatement();
			onreadyStatement.document().ready(
					JsScope.quickScope(JsQuery.this.statement.render()));
			response.renderString("<script type=\"text/javascript\">"
					+ onreadyStatement.render() + "</script>");
		} else {
			AjaxRequestTarget ajaxRequestTarget = (AjaxRequestTarget) requestTarget;
			ajaxRequestTarget.appendJavascript(statement.render().toString());
		}
	}

	/**
	 * Adds this statement as a {@link HeaderContributor} for the given
	 * component.
	 */
	public void contribute(Component component) {
		this.component = component;
		component.add(new HeaderContributor(this));
	}

	/**
	 * @return the statement
	 */
	public JsStatement getStatement() {
		return statement;
	}

	/**
	 * FOR FRAMEWORK'S INTERNAL USE ONLY
	 */
	public void setStatement(JsStatement jsStatement) {
		this.statement = jsStatement;
	}

	/**
	 * FOR FRAMEWORK'S INTERNAL USE ONLY
	 */
	public void renderHead(IHeaderResponse response,
			IRequestTarget requestTarget) {
		response.renderJavascriptReference(
				CoreJavaScriptResourceReference.get());
		
		String js = statement == null ? null : statement.render().toString();
		
		if (js != null && js.trim().length() > 0) {
			if (requestTarget == null
					|| !(requestTarget instanceof AjaxRequestTarget)) {
				// appending component statement
				// on dom ready, the code is executed.
				JsStatement onreadyStatement = new JsStatement();
				onreadyStatement.document().ready(JsScope.quickScope(js));
//				response.renderJavascriptReference(
//						new WiqueryGeneratedJavaScriptResourceReference(
//								onreadyStatement.render()));
				// We don't use WiqueryGeneratedJavaScriptResourceReference because :
				// 1) it could cause some memory leak with the SharedApplication
				// 2) with ajax update, we will have the execution of the first generation of the page
				// and next the new generation
				response.renderJavascript(
						WiqueryGeneratedJavaScriptResourceReference.wiqueryGeneratedJavascriptCode(
								onreadyStatement.render()), 
						"wiquery-gen" + System.currentTimeMillis());
			
			} else {
				AjaxRequestTarget ajaxRequestTarget = (AjaxRequestTarget) requestTarget;
				ajaxRequestTarget.appendJavascript(js);
			}
		}
	}

}

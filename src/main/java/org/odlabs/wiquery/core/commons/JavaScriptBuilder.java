/*
 * Copyright (c) 2008 Lionel Armanet
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
package org.odlabs.wiquery.core.commons;

import java.io.Serializable;

import org.apache.wicket.Component;
import org.apache.wicket.IRequestTarget;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.markup.html.IHeaderResponse;

/**
 * $Id$
 * <p>
 * 	Builds jQuery oriented JavaScript source code and renders it.
 * </p>
 * <p>
 * 	this.$(".class1").call(new ForEach() {
 * 
 * 		public void each() {
 * 			element().call(new WickextEventBehavior(Event.CLICK) {
 * 				
 * 				public CharSequence callbackStatement() {
 * 					
 * 					return element().call(toggle()).statement();
 * 
 * 				}
 * 
 * 			});
 *      }
 * 
 *  });
 *  
 *  $(".class1").each(function(i) {
 *  	i.click(function() {
 *  		i.toggle();
 *  	});
 *  });
 * </p>
 * @author Lionel Armanet
 * @since 0.5
 */
public class JavaScriptBuilder implements Serializable {
	
	private static final long serialVersionUID = -3816412033699069800L;

	/**
	 * Defines the DOM ready open statement in jquery
	 */
	private static final String ON_DOM_READY_OPEN = "$(document).ready(function (){\n";
	
	/**
	 * Defines the DOM ready close statement in jquery
	 */
	private static final String ON_DOM_READY_CLOSE = "\n})";
	
	/**
	 * This {@link StringBuilder} contains the built JavaScript statement.
	 */
	private StringBuilder stringBuilder;
	
	/**
	 * This statements applies to a {@link Component}.
	 */
	private Component component;
	
	/**
	 * Defines the response to append JavaScript.
	 */
	private IHeaderResponse response;
	
	/**
	 * Builds a new instance of {@link JavaScriptBuilder} with the given 
	 * {@link Component}.
	 * @param component the component on which this statement applies.
	 */
	public JavaScriptBuilder(Component component) {
		this();
		this.component = component;
		// we force to output the markup id 
		// (very useful to retreive it in JavaScript)
		component.setOutputMarkupId(true);
	}
	
	public JavaScriptBuilder() {
		super();
		this.stringBuilder = new StringBuilder();
	}
	
	/**
	 * Binds the given {@link IHeaderResponse} to this statement. This response
	 * will be used to append JavaScript.
	 * @param response the  {@link IHeaderResponse} instance to bind.
	 */
	public void bindResponse(IHeaderResponse response) {
		this.response = response;
	}
	
	/**
	 * Generates a JavaScript sequence to retreive the component with its
	 * markup id.
	 * <p>
	 * 	It appends a <code>$('#markupId')</code> JavaScript sequence to the
	 *  statement.
	 * </p>
	 * @return this instance to allow chained calls
	 */
	public JavaScriptBuilder $() {
		stringBuilder.append("$(");
		if (component != null) {
			stringBuilder.append("'");
			stringBuilder.append("#");
			stringBuilder.append(component.getMarkupId(true));
			stringBuilder.append("'");		
		}
		stringBuilder.append(")");
		return this;
	}
	
	public JavaScriptBuilder $(String cssSelector) {
		if (cssSelector == null || "".equals(cssSelector)) {
			return $();
		}
		stringBuilder.append("$('");
		if (component != null) {
			stringBuilder.append("#");
			stringBuilder.append(component.getMarkupId(true));
			stringBuilder.append(" ");
		}
		stringBuilder.append(cssSelector);
		stringBuilder.append("')");
		return this;
	}
	
	/**
	 * Appends the given {@link JavaScriptCallable}'s statement to this 
	 * statement.
	 * <p>
	 * 	It appends a <code>.{@link JavaScriptCallable#statement()}</code>
	 *  JavaScript sequence to this statement.
	 * </p>
	 * @param queryCallable the given {@link JavaScriptCallable} to append.
	 * @return this instance to allow chained calls
	 */
	public JavaScriptBuilder call(JavaScriptCallable queryCallable) {
		stringBuilder.append(".");
		stringBuilder.append(queryCallable.statement());
		return this;
	}
	
	public JavaScriptBuilder addClass(String cssClass) {
		stringBuilder.append("addClass('");
		stringBuilder.append(cssClass);
		stringBuilder.append("');");
		return this;
	}
	
	public JavaScriptBuilder removeClass(String cssClass) {
		stringBuilder.append("removeClass('");
		stringBuilder.append(cssClass);
		stringBuilder.append("');");
		return this;
	}
	
	public JavaScriptBuilder toggleClass(String cssClass) {
		stringBuilder.append("toggleClass('");
		stringBuilder.append(cssClass);
		stringBuilder.append("');");
		return this;
	}

	public JavaScriptBuilder attr(String attributeKey, String attributeValue) {
		stringBuilder.append("attr('");
		stringBuilder.append(attributeKey);
		stringBuilder.append(", ");
		stringBuilder.append(attributeValue);
		stringBuilder.append("');");
		return this;
	}

	public JavaScriptBuilder removeAttr(String attributeKey) {
		stringBuilder.append("removeAttr('");
		stringBuilder.append(attributeKey);
		stringBuilder.append("');");
		return this;
	}

	
	/**
	 * Returns the build statement as a {@link String}.
	 * <p>
	 * 	It will append a <code>;\n</code> to the whole statement.
	 * </p>
	 */
	public String getStatement() {
		stringBuilder.append(";\n");
		return stringBuilder.toString();
	}
	
	/**
	 * Returns the build DOM Ready statement as a {@link String}.
	 */
	public String getDomReadyStatement() {
		StringBuilder domReadyStringBuilder = new StringBuilder();
		domReadyStringBuilder.append(ON_DOM_READY_OPEN);
		domReadyStringBuilder.append(this.stringBuilder);
		domReadyStringBuilder.append(ON_DOM_READY_CLOSE);
		domReadyStringBuilder.append(";\n");
		return domReadyStringBuilder.toString();
	}	

	/**
	 * Appends the build JavaScript statement to the final response. Works
	 * for both non-Ajax and Ajax requests.
	 * <p>
	 * 	For non-Ajax purposes, the built JavaScript statement is wrapped
	 *  in a jQuery "DOM ready" statement.
	 * </p>
	 */
	public void appendJavaScript() {
		// are we in an Ajax context ? To know that, we ask the request cycle
		// to get the current target. If it's null or not an instance of
		// AjaxRequestTarget, the context in non ajax.
		IRequestTarget requestTarget = component.getRequestCycle().getRequestTarget();
		if (requestTarget == null || !(requestTarget instanceof AjaxRequestTarget)) {
			// non ajax context ==> wrapping statement in a DOM ready statement
			StringBuilder domReadyStringBuilder = new StringBuilder();
			domReadyStringBuilder.append(ON_DOM_READY_OPEN);
			domReadyStringBuilder.append(this.stringBuilder);
			domReadyStringBuilder.append(ON_DOM_READY_CLOSE);
			domReadyStringBuilder.append(";\n");
			response.renderString("<script type=\"text/javascript\">" + domReadyStringBuilder.toString() + "</script>");
		} else {
			// Ajax case, it should be rendered after the component rendered
			// ==> We use directly the request target obtained from the
			// request cycle
			AjaxRequestTarget ajaxRequestTarget = (AjaxRequestTarget) requestTarget;
			ajaxRequestTarget.appendJavascript(this.getStatement());
		}
	}
	
}

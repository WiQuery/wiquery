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
import org.apache.wicket.markup.html.WebPage;

/**
 * $Id$
 * <p>
 * A {@link JsStatement} defines a whole executable JavaScript statement. The
 * generator eases "jQuery oriented" code, but you can still use another
 * JavaScript coding style with the {@link #append(CharSequence)} method.
 * </p>
 * <p>
 * To get a valid instance and to ease JavaScript and Wicket integration, you
 * should use the {@link JsQuery} class (example below). This class will
 * initialize a {@link JsStatement} and contribute to a {@link WebPage}.
 * </p>
 * <p>
 * The JavaScript generating by a JsStatement is jQuery oriented. Example of
 * generated string:
 * <p>
 * <code>$(".aClassName").each(function() {alert('foo');});</code>
 * </p>
 * </p>
 * <p>
 * To generate the script above, you can write this:
 * <p>
 * <code>new JsQuery().$().each(JsScope.quickScope("alert('foo')"));</code>
 * </p>
 * </p>
 * <p>
 * Any {@link JsStatement} should be returned by a {@link JsQuery} within the
 * <code>$</code> methods.
 * </p>
 * 
 * @author Lionel Armanet
 * @since 0.7
 * @see JsQuery
 * @see JsScope
 */
public class JsStatement implements Serializable {

	private static final long serialVersionUID = 3254345351404601200L;

	/**
	 * This whole statement.
	 */
	private StringBuilder statement;

	/**
	 * Creates a new {@link JsStatement}.
	 */
	public JsStatement() {
		statement = new StringBuilder();
	}

	/**
	 * Appends the jQuery's <code>$</code> function to the current
	 * {@link JsStatement}. So, we can call some special jquery fonction, like
	 * <code>$.browser</code> ...
	 * @return {@link JsStatement} this instance.
	 */
	public JsStatement $() {
		statement.append("$");
		return this;
	}
	
	/**
	 * Same method as {@link #$(Component, String)} with an empty selector.
	 * 
	 * @return {@link JsStatement} this instance.
	 */
	public JsStatement $(Component context) {
		String dollarSelector = context == null ? "" : ("'#"
				+ context.getMarkupId() + "'");
		statement.append("$(");
		statement.append(dollarSelector);
		statement.append(")");
		return this;
	}

	/**
	 * Appends the jQuery's <code>$</code> function to the current
	 * {@link JsStatement}. If context is not null, the generated JavaScript
	 * code will be:
	 * <p>
	 * <code>$("#component.getMarkupId selector")</code>
	 * </p>
	 * If context is null, the generated JavaScript code will be:
	 * <p>
	 * <code>$("selector")</code>
	 * </p>
	 * 
	 * @return {@link JsStatement} this instance.
	 */
	public JsStatement $(Component context, String selector) {
		String dollarSelector = context == null ? selector : ("#"
				+ context.getMarkupId() + " " + selector);
		statement.append("$('");
		statement.append(dollarSelector);
		statement.append("')");
		return this;
	}
	
	/**
	 * Appends the given {@link CharSequence} to this statement.
	 * 
	 * @return {@link JsStatement} this instance.
	 */
	public JsStatement append(CharSequence charSequence) {
		statement.append(charSequence);
		return this;
	}

	/**
	 * Chains a function call to this {@link JsStatement}. The function call is
	 * wrapped in a ChainableStatement. (eases JavaScript integration).
	 * 
	 * @return {@link JsStatement} this instance.
	 * @see #chain(CharSequence, CharSequence...).
	 */
	public JsStatement chain(ChainableStatement chainableStatement) {
		this.chain(chainableStatement.chainLabel(), chainableStatement
				.statementArgs());
		return this;
	}

	/**
	 * Chains a function call to this {@link JsStatement}. This will append
	 * statementLabel(statementArgs) to this {@link JsStatement}.
	 * 
	 * @param statementLabel
	 *            the statement label to append.
	 * @param statementArgs
	 *            the statement args.
	 * @return {@link JsStatement} this instance.
	 */
	public JsStatement chain(CharSequence statementLabel,
			CharSequence... statementArgs) {
		// appending statement label
		statement.append(".").append(statementLabel).append("(");

		// appending arguments
		if (statementArgs.length > 0) {
			statement.append(statementArgs[0]);
			for (int i = 1; i < statementArgs.length; i++) {
				CharSequence charSequence = statementArgs[i];
				statement.append(", ").append(charSequence);
			}
		}
		statement.append(")");
		return this;
	}

	/**
	 * Appends $(document) to the statement.
	 * 
	 * @return {@link JsStatement} this instance.
	 */
	public JsStatement document() {
		statement.append("$(document)");
		return this;
	}

	/**
	 * Appends the <strong>each</strong> jQuery statement.
	 * 
	 * @param scope
	 *            the {@link JsScope} to execute within every matched element.
	 */
	public JsStatement each(JsScope scope) {
		return this.chain("each", scope.render());
	}

	/**
	 * @return the statement of the JsStatement
	 */
	public StringBuilder getStatement() {
		return statement;
	}

	/* utility methods */

	/**
	 * Appends the <strong>ready</strong> jQuery statement.
	 * 
	 * @param scope
	 *            the {@link JsScope} to execute when jQuery is ready.
	 * @return
	 */
	public JsStatement ready(JsScope scope) {
		return this.chain("ready", scope.render());
	}

	/**
	 * Renders this statement.
	 * 
	 * @return the renderable JavaScript statement as a {@link CharSequence}.
	 */
	public CharSequence render() {
		return render(true);
	}

	/**
	 * Renders this statement.
	 * @param semicolon If true, a semicolon is automatically added
	 * @return the renderable JavaScript statement as a {@link CharSequence}.
	 */
	public CharSequence render(boolean semicolon) {
		String render = this.statement.toString();
		
		if(semicolon){
			String trimRendering = render.trim();
			
			if (trimRendering.length() > 0) {
				char last = trimRendering.charAt(trimRendering.length() - 1);
				
				if (last != '}' && last != ';') {
					render += ";";
				}
			}
		}
		return render;
	}

	/**
	 * Appends the <code>this</code> keyword to this statement.
	 * 
	 * @return {@link JsStatement} this instance.
	 */
	public JsStatement self() {
		statement.append("$(this)");
		return this;
	}
}

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

import com.fasterxml.jackson.annotation.JsonValue;

/**
 * $Id: JsScope.java 1714 2011-09-22 20:38:30Z hielke.hoeve $
 * <p>
 * {@link JsScope} are used to define anonymous JavaScript functions. For example, let's
 * take the <code>each</code> statement:
 * </p>
 * 
 * <pre>
 * 	<code>
 * new JsQuery().$(&quot;.foo&quot;).each(new JsScope() {
 * 
 * 	public void execute(JsScopeContext scopeContext) {
 * 		scopeContext.self().chain(&quot;css&quot;, &quot;border&quot;, &quot;1px solid red&quot;);
 * 	}
 * 
 * });
 * </code>
 * </pre>
 * 
 * <p>
 * You can either pass arguments to the JsScope (like in JavaScript functions) (see
 * constructor args).
 * </p>
 * 
 * @author Lionel Armanet
 * @since 0.7
 * @see JsStatement
 * @see JsScopeContext
 */
public abstract class JsScope implements Serializable
{
	// Constants
	/** Constant of serialization */
	private static final long serialVersionUID = 1L;

	/**
	 * The function statement.
	 */
	private StringBuilder statement;

	/**
	 * The scope used by the anonymous function.
	 */
	private JsScopeContext scopeContext;

	/**
	 * Creates a default {@link JsScope} to execute the given statement.
	 * 
	 * @param javascriptCode
	 *            the JavaScript statement to execute with the scope.
	 * @return the created {@link JsScope}.
	 */
	public static JsScope quickScope(final CharSequence javascriptCode)
	{
		return new JsScope()
		{

			private static final long serialVersionUID = 1L;

			@Override
			protected void execute(JsScopeContext scopeContext)
			{
				scopeContext.append(javascriptCode);
			}

		};
	}

	/**
	 * Creates a default {@link JsScope} to execute the given statement.
	 * 
	 * @param jsStatement
	 *            the JavaScript statement to execute with the scope.
	 * @return the created {@link JsScope}.
	 */
	public static JsScope quickScope(final JsStatement jsStatement)
	{
		return new JsScope()
		{

			private static final long serialVersionUID = 1L;

			@Override
			protected void execute(JsScopeContext scopeContext)
			{
				scopeContext.append(jsStatement == null ? "" : jsStatement.render());
			}

		};
	}

	/**
	 * Constructs a new {@link JsScope} instance with the given args. This is the
	 * equivalent of the JavaScript statement: <code>function(arg1, arg2, arg3) {</code>
	 * 
	 * @param scopeParameters
	 */
	public JsScope(String... scopeParameters)
	{
		super();
		scopeContext = new JsScopeContext(scopeParameters);
		statement = null;
	}

	/**
	 * Executes the {@link JsScope}.
	 * 
	 * @param scopeContext
	 *            the given scope.
	 */
	protected abstract void execute(JsScopeContext scopeContext);

	/**
	 * Ends the scope JavaScript declaration.
	 */
	private JsScope closeScope()
	{
		statement.append('}');
		return this;
	}

	/**
	 * Renders the scope.
	 */
	@JsonValue
	public CharSequence render()
	{
		if (statement == null)
		{
			statement = new StringBuilder();
			statement.append("function(");
			statement.append(scopeContext.scopeDeclaration());
			statement.append(") {\n");
			execute(scopeContext);
			statement.append(scopeContext.render());
			closeScope();
		}
		return statement.toString();
	}

}

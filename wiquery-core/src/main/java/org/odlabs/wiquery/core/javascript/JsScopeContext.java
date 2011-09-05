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
import java.util.ArrayList;
import java.util.List;

/**
 * $Id$
 * <p>
 * {@link JsScopeContext} defines the following {@link JsScope} contents:
 * <ul>
 * <li>all accessible variables from a {@link JsScope}</li>
 * <li>all JavaScript statements executed in the {@link JsScope}</li>
 * </ul>
 * 
 * </p>
 * 
 * @author Lionel Armanet
 * @since 0.7
 * @see JsScope
 * @see JsStatement
 */
public class JsScopeContext implements Serializable
{

	private static final long serialVersionUID = -4223292444618777899L;

	/**
	 * The defined scope variables.
	 */
	private String[] scopeVariables;

	/**
	 * The list of statements executed in the {@link JsScope}.
	 */
	private List<JsStatement> statements;

	/**
	 * Creates a new {@link JsScopeContext} with the given arguments.
	 */
	public JsScopeContext(String... scopeVariables)
	{
		this.scopeVariables = scopeVariables;
		statements = new ArrayList<JsStatement>();
	}

	/**
	 * Creates a new {@link JsStatement} and append the "this" keyword in this statement.
	 * 
	 * @return the created {@link JsStatement}.
	 */
	public JsStatement self()
	{
		JsStatement statement = new JsStatement();
		statements.add(statement);
		return statement.self();
	}

	/**
	 * Creates a new {@link JsStatement} and append the given variable name in this
	 * statement.
	 * 
	 * @return the created {@link JsStatement}.
	 */
	public JsStatement var(String variable)
	{
		JsStatement statement = new JsStatement();
		statements.add(statement);
		return statement.append(variable);
	}

	/**
	 * Creates a new {@link JsStatement} and append the given JavaScript code in this
	 * statement.
	 * 
	 * @return the created {@link JsStatement}.
	 */
	public JsStatement append(CharSequence javascriptCode)
	{
		JsStatement statement = new JsStatement();
		statements.add(statement);
		return statement.append(javascriptCode);
	}

	/**
	 * Returns the JavaScript statement to declare the scope declaration (eg. the list of
	 * variables declared in the JavaScript function).
	 */
	CharSequence scopeDeclaration()
	{
		if (scopeVariables.length == 0)
		{
			return "";
		}
		StringBuilder scopeDeclaration = new StringBuilder();
		scopeDeclaration.append(scopeVariables[0]);
		for (int i = 1; i < scopeVariables.length; i++)
		{
			scopeDeclaration.append(", ");
			scopeDeclaration.append(scopeVariables[i]);
		}
		return scopeDeclaration;
	}

	/**
	 * Renders the list of statements of this {@link JsScopeContext}.
	 */
	CharSequence render()
	{
		StringBuilder stringBuilder = new StringBuilder();
		for (JsStatement statement : statements)
		{
			stringBuilder.append("\t");
			stringBuilder.append(statement.render());
			stringBuilder.append("\n");
		}
		return stringBuilder;
	}

}

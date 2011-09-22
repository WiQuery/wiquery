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
package org.odlabs.wiquery.core.options;

import org.apache.wicket.Component;
import org.apache.wicket.model.IComponentAssignedModel;
import org.apache.wicket.model.IModel;
import org.odlabs.wiquery.core.javascript.JsUtils;

/**
 * $Id$
 * <p>
 * Wraps a {@link String} to be generated as a JavaScript string.
 * <p>
 * Example:
 * <p>
 * The {@link String} <code>true</code> should be rendered as <code>true</code>
 * </p>
 * </p> </p>
 * 
 * @author Lionel Armanet
 * @author Ernesto Reinaldo Barreiro
 * @since 0.5
 */
public class LiteralOption extends AbstractOption<String>
{
	private static final long serialVersionUID = -5938430089917100476L;

	private boolean doubleQuote;

	/**
	 * Builds a new instance of {@link LiteralOption}.
	 * 
	 * @param literal
	 *            the wrapped {@link String}
	 */
	public LiteralOption(String value)
	{
		this(value, false);
	}

	/**
	 * Builds a new instance of {@link LiteralOption}.
	 * 
	 * @param literal
	 *            the wrapped {@link String}
	 */
	public LiteralOption(IModel<String> value)
	{
		this(value, false);
	}

	/**
	 * Builds a new instance of {@link LiteralOption}.
	 * 
	 * @param literal
	 *            the wrapped {@link String}
	 */
	public LiteralOption(String value, boolean doubleQuote)
	{
		super(value);
		this.doubleQuote = doubleQuote;
	}

	/**
	 * Builds a new instance of {@link LiteralOption}.
	 * 
	 * @param literal
	 *            the wrapped {@link String}
	 */
	public LiteralOption(IModel<String> value, boolean doubleQuote)
	{
		super(value);
		this.doubleQuote = doubleQuote;
	}

	/**
	 * @deprecated Use getValue()
	 */
	@Deprecated
	public String getLiteral()
	{
		return getValue();
	}

	@Override
	public String toString()
	{
		return doubleQuote ? JsUtils.doubleQuotes(getValue()) : JsUtils.quotes(getValue());
	}

	public IModelOption<String> wrapOnAssignment(Component component)
	{
		if (getModel() instanceof IComponentAssignedModel< ? >)
			return new LiteralOption(
				((IComponentAssignedModel<String>) getModel()).wrapOnAssignment(component),
				doubleQuote);
		return this;
	}
}

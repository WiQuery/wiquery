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
package org.odlabs.wiquery.ui.sortable;

import org.odlabs.wiquery.core.javascript.JsScope;
import org.odlabs.wiquery.core.options.IComplexOption;
import org.odlabs.wiquery.core.options.LiteralOption;

/**
 * $Id$
 * <p>
 * Bean for the helper option for the Sortable behavior
 * </p>
 * 
 * @author Julien Roche
 * @since 1.1
 */
public class SortableHelper implements IComplexOption
{
	public enum HelperEnum
	{
		CLONE(new LiteralOption("clone")),
		ORIGINAL(new LiteralOption("original"));

		// Properties
		private LiteralOption literalParam;

		HelperEnum(LiteralOption literalParam)
		{
			this.literalParam = literalParam;
		}

		/*
		 * (non-Javadoc)
		 * @see java.lang.Enum#toString()
		 */
		@Override
		public String toString()
		{
			return literalParam.toString();
		}
	}

	// Constants
	/** Constant of serialization */
	private static final long serialVersionUID = 3404088696595137949L;

	// Properties
	private JsScope functionParam;

	private HelperEnum helperEnumParam;

	/**
	 * Constructor
	 * 
	 * @param helperEnumParam
	 *            HelperEnum parameter
	 */
	public SortableHelper(HelperEnum helperEnumParam)
	{
		this(null, helperEnumParam);
	}

	/**
	 * Constructor
	 * 
	 * @param functionParam
	 *            Function parameter
	 */
	public SortableHelper(JsScope functionParam)
	{
		this(functionParam, null);
	}

	/**
	 * Constructor
	 * 
	 * @param functionParam
	 *            Function parameter
	 * @param helperEnumParam
	 *            HelperEnum parameter
	 */
	private SortableHelper(JsScope functionParam, HelperEnum helperEnumParam)
	{
		super();
		setParam(functionParam, helperEnumParam);
	}

	/**
	 * @return the helperEnumParam
	 */
	public HelperEnum getHelperEnumParam()
	{
		return helperEnumParam;
	}

	/**
	 * @return the functionParam
	 */
	public JsScope getFunctionParam()
	{
		return functionParam;
	}

	public CharSequence getJavascriptOption()
	{
		if (helperEnumParam == null && functionParam == null)
		{
			throw new IllegalArgumentException(
				"The SortableHelper must have one not null parameter");
		}

		CharSequence sequence = null;

		if (helperEnumParam != null)
		{
			sequence = helperEnumParam.toString();
		}
		else if (functionParam != null)
		{
			sequence = functionParam.render();
		}
		else
		{
			throw new IllegalArgumentException(
				"The SortableHelper must have one not null parameter");
		}

		return sequence;
	}

	/**
	 * Set's the HelperEnum parameter
	 * 
	 * @param helperEnumParam
	 *            the HelperEnum to set
	 */
	public void setHelperEnumParam(HelperEnum helperEnumParam)
	{
		setParam(null, helperEnumParam);
	}

	/**
	 * Set's the function parameter
	 * 
	 * @param functionParam
	 *            the JsScope to set
	 */
	public void setFunctionParam(JsScope functionParam)
	{
		setParam(functionParam, null);
	}

	/**
	 * Method setting the right parameter
	 * 
	 * @param functionParam
	 *            Function parameter
	 * @param helperEnumParam
	 *            HelperEnum parameter
	 */
	private void setParam(JsScope functionParam, HelperEnum helperEnumParam)
	{
		this.functionParam = functionParam;
		this.helperEnumParam = helperEnumParam;
	}
}

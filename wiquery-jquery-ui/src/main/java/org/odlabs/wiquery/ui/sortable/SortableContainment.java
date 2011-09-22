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

import org.odlabs.wiquery.core.javascript.JsUtils;
import org.odlabs.wiquery.core.options.IComplexOption;
import org.odlabs.wiquery.core.options.LiteralOption;

/**
 * $Id: SortableContainment.java
 * <p>
 * Bean for the containment option for the Sortable behavior
 * </p>
 * 
 * @author Julien Roche
 * @since 1.1
 */
public class SortableContainment implements IComplexOption
{
	public enum ElementEnum
	{
		PARENT(new LiteralOption("parent")),
		DOCUMENT(new LiteralOption("document")),
		WINDOW(new LiteralOption("windo"));

		// Properties
		private LiteralOption literalParam;

		ElementEnum(LiteralOption literalParam)
		{
			this.literalParam = literalParam;
		}

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
	private ElementEnum elementEnumParam;

	private String objectParam;

	private LiteralOption selector;

	/**
	 * Constructor
	 * 
	 * @param elementEnumParam
	 *            elementEnum parameter
	 */
	public SortableContainment(ElementEnum elementEnumParam)
	{
		this(elementEnumParam, null, null);
	}

	/**
	 * Constructor
	 * 
	 * @param objectParam
	 *            object parameter (Element or Selector)
	 */
	public SortableContainment(String objectParam)
	{
		this(null, objectParam, null);
	}

	/**
	 * Constructor
	 * 
	 * @param selector
	 *            Selector
	 */
	public SortableContainment(LiteralOption selector)
	{
		this(null, null, selector);
	}

	/**
	 * Constructor
	 * 
	 * @param elementEnumParam
	 *            elementEnul parameter
	 * @param objectParam
	 *            object parameter
	 * @param selector
	 *            Selector
	 */
	private SortableContainment(ElementEnum elementEnumParam, String objectParam,
			LiteralOption selector)
	{
		super();
		setParam(elementEnumParam, objectParam, selector);
	}

	/**
	 * @return the elementEnumParam
	 */
	public ElementEnum getElementEnumParamParam()
	{
		return elementEnumParam;
	}

	/**
	 * @return the objectParam
	 */
	public String getElementOrSelectorParam()
	{
		return objectParam;
	}

	/**
	 * @return the Selector
	 */
	public LiteralOption getSelector()
	{
		return selector;
	}

	public CharSequence getJavascriptOption()
	{
		if (objectParam == null && elementEnumParam == null && selector == null)
		{
			throw new IllegalArgumentException(
				"The SortableContainment must have one not null parameter");
		}

		CharSequence sequence = null;

		if (objectParam != null)
		{
			sequence = JsUtils.quotes(objectParam);
		}
		else if (elementEnumParam != null)
		{
			sequence = elementEnumParam.toString();
		}
		else if (selector != null)
		{
			sequence = selector.getJavascriptOption();
		}
		else
		{
			throw new IllegalArgumentException(
				"The SortableContainment must have one not null parameter");
		}

		return sequence;
	}

	/**
	 * Set's the literal parameter
	 * 
	 * @param literalParam
	 *            the literal to set
	 */
	public void setElementEnumParam(ElementEnum elementEnumParam)
	{
		setParam(elementEnumParam, null, null);
	}

	/**
	 * Set's the object (Element or Selector) parameter
	 * 
	 * @param objectParam
	 *            the literal to set
	 */
	public void setElementOrSelectorParam(String objectParam)
	{
		setParam(null, objectParam, null);
	}

	/**
	 * Set's the Selector
	 * 
	 * @param selector
	 *            Selector
	 */
	public void setSelector(LiteralOption selector)
	{
		setParam(null, null, selector);
	}

	/**
	 * Method setting the right parameter
	 * 
	 * @param elementEnumParam
	 *            elementEnum parameter
	 * @param objectParam
	 *            object parameter
	 * @param selector
	 *            Selector
	 */
	private void setParam(ElementEnum elementEnumParam, String objectParam, LiteralOption selector)
	{
		this.elementEnumParam = elementEnumParam;
		this.objectParam = objectParam;
		this.selector = selector;
	}
}

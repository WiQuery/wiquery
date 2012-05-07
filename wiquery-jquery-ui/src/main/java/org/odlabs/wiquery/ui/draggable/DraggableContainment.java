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
package org.odlabs.wiquery.ui.draggable;

import org.odlabs.wiquery.core.options.ArrayItemOptions;
import org.odlabs.wiquery.core.options.ICollectionItemOptions;
import org.odlabs.wiquery.core.options.IComplexOption;
import org.odlabs.wiquery.core.options.IntegerItemOptions;
import org.odlabs.wiquery.core.options.LiteralOption;

/**
 * $Id: DraggableContainment.java
 * <p>
 * Bean for the containment option for the Draggable behavior
 * </p>
 * 
 * @author Julien Roche
 * @since 1.0
 */
public class DraggableContainment implements IComplexOption
{
	/**
	 * Containment to parent
	 */
	public static final DraggableContainment PARENT = new DraggableContainment(
		ContainmentEnum.PARENT);

	/**
	 * Containment to document
	 */
	public static final DraggableContainment DOCUMENT = new DraggableContainment(
		ContainmentEnum.DOCUMENT);

	/**
	 * Containment to window
	 */
	public static final DraggableContainment WINDOW = new DraggableContainment(
		ContainmentEnum.WINDOW);

	/**
	 * Enumeration of possible values for the containment option
	 * 
	 * @author Julien
	 * 
	 */
	public enum ContainmentEnum
	{
		PARENT(new LiteralOption("parent")),
		DOCUMENT(new LiteralOption("document")),
		WINDOW(new LiteralOption("window"));

		// Properties
		private LiteralOption literalParam;

		ContainmentEnum(LiteralOption literalParam)
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
	private static final long serialVersionUID = 3404088696595137952L;

	// Properties
	private ArrayItemOptions<IntegerItemOptions> arrayParam;

	private ContainmentEnum containmentEnumParam;

	private LiteralOption selector;

	private String stringParam;

	/**
	 * Constructor
	 * 
	 * @param containmentEnumParam
	 *            ContainmentEnum parameter
	 */
	public DraggableContainment(ContainmentEnum containmentEnumParam)
	{
		this(null, containmentEnumParam, null, null);
	}

	/**
	 * Constructor
	 * 
	 * @param x1
	 *            First x coordinate
	 * @param y1
	 *            First y coordinate
	 * @param x2
	 *            Second x coordinate
	 * @param y2
	 *            Second y coordinate
	 */
	public DraggableContainment(Integer x1, Integer y1, Integer x2, Integer y2)
	{
		ArrayItemOptions<IntegerItemOptions> tempArrayParam =
			new ArrayItemOptions<IntegerItemOptions>();
		tempArrayParam.add(new IntegerItemOptions(x1));
		tempArrayParam.add(new IntegerItemOptions(y1));
		tempArrayParam.add(new IntegerItemOptions(x2));
		tempArrayParam.add(new IntegerItemOptions(y2));

		setParam(tempArrayParam, null, null, null);
	}

	/**
	 * Constructor
	 * 
	 * @param stringParam
	 *            Selector or element parameter
	 */
	public DraggableContainment(String stringParam)
	{
		this(null, null, stringParam, null);
	}

	/**
	 * Constructor
	 * 
	 * @param selector
	 *            Selector
	 */
	public DraggableContainment(LiteralOption selector)
	{
		this(null, null, null, selector);
	}

	/**
	 * Constructor
	 * 
	 * @param arrayParam
	 *            Array parameter
	 * @param containmentEnumParam
	 *            ContainmentEnum parameter
	 * @param stringParam
	 *            Literal parameter
	 * @param selector
	 *            Selector
	 */
	private DraggableContainment(ArrayItemOptions<IntegerItemOptions> arrayParam,
			ContainmentEnum containmentEnumParam, String stringParam, LiteralOption selector)
	{
		super();
		setParam(arrayParam, containmentEnumParam, stringParam, selector);
	}

	/**
	 * @return the arrayParam
	 */
	public ICollectionItemOptions getArrayParam()
	{
		return arrayParam;
	}

	/**
	 * @return the containmentEnumParam
	 */
	public ContainmentEnum getContainmentEnumParam()
	{
		return containmentEnumParam;
	}

	/**
	 * @return the Selector
	 */
	public LiteralOption getSelector()
	{
		return selector;
	}

	/**
	 * @return the Selector or Element param
	 */
	public String getStringParam()
	{
		return stringParam;
	}

	@Override
	public CharSequence getJavascriptOption()
	{
		if (containmentEnumParam == null && arrayParam == null && stringParam == null
			&& selector == null)
		{
			throw new IllegalArgumentException(
				"The DraggableContainment must have one not null parameter");
		}

		CharSequence sequence = null;

		if (containmentEnumParam != null)
		{
			sequence = containmentEnumParam.toString();
		}
		else if (arrayParam != null)
		{
			sequence = arrayParam.getJavascriptOption();
		}
		else if (selector != null)
		{
			sequence = selector.getJavascriptOption();
		}
		else if (stringParam != null)
		{
			sequence = stringParam;
		}
		else
		{
			throw new IllegalArgumentException(
				"The DraggableContainment must have one not null parameter");
		}

		return sequence;
	}

	/**
	 * Set's the array parameter
	 * 
	 * @param x1
	 *            First x coordinate
	 * @param y1
	 *            First y coordinate
	 * @param x2
	 *            Second x coordinate
	 * @param y2
	 *            Second y coordinate
	 */
	public void setArrayParam(Integer x1, Integer y1, Integer x2, Integer y2)
	{
		ArrayItemOptions<IntegerItemOptions> tempArrayParam =
			new ArrayItemOptions<IntegerItemOptions>();
		tempArrayParam.add(new IntegerItemOptions(x1));
		tempArrayParam.add(new IntegerItemOptions(y1));
		tempArrayParam.add(new IntegerItemOptions(x2));
		tempArrayParam.add(new IntegerItemOptions(y2));

		setParam(tempArrayParam, containmentEnumParam, null, null);
	}

	/**
	 * Set's the ContainmentEnum parameter
	 * 
	 * @param containmentEnumParam
	 *            the ContainmentEnum to set
	 */
	public void setContainmentEnumParam(ContainmentEnum containmentEnumParam)
	{
		setParam(null, containmentEnumParam, null, null);
	}

	/**
	 * Set's the Selector
	 * 
	 * @param selector
	 *            Selector
	 */
	public void setSelector(LiteralOption selector)
	{
		setParam(null, null, null, selector);
	}

	/**
	 * Set's the Selector or Element parameter
	 * 
	 * @param stringParam
	 *            Selector or Element param
	 */
	public void setStringParam(String stringParam)
	{
		setParam(null, null, stringParam, null);
	}

	/**
	 * Method setting the right parameter
	 * 
	 * @param arrayParam
	 *            Array parameter
	 * @param helperEnumParam
	 *            ContainmentEnum parameter
	 * @param stringParam
	 *            Selector or Element
	 * @param selector
	 *            Selector
	 */
	private void setParam(ArrayItemOptions<IntegerItemOptions> arrayParam,
			ContainmentEnum containmentEnumParam, String stringParam, LiteralOption selector)
	{
		this.arrayParam = arrayParam;
		this.containmentEnumParam = containmentEnumParam;
		this.stringParam = stringParam;
		this.selector = selector;
	}
}

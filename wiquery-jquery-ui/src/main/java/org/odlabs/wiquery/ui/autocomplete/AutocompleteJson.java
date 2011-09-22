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
package org.odlabs.wiquery.ui.autocomplete;

import java.io.Serializable;

/**
 * $Id$
 * <p>
 * Value for the {@link AutocompleteComponent}
 * </p>
 * 
 * @author Julien Roche
 * @since 1.1
 * 
 */
public class AutocompleteJson implements Serializable
{
	// Constants
	/** Constant of serialization */
	private static final long serialVersionUID = -2709458515370697155L;

	// Properties
	private String valueId;

	private String label;

	/**
	 * Default constructor
	 * 
	 * @param valueId
	 *            Identifiant of the model
	 * @param label
	 *            Label to display
	 */
	public AutocompleteJson(String valueId, String label)
	{
		super();
		this.valueId = valueId;
		this.label = label;
	}

	/**
	 * @return the label to display
	 */
	public String getLabel()
	{
		return label;
	}

	/**
	 * @return the identifiant of the model
	 */
	public String getValueId()
	{
		return valueId;
	}

	/**
	 * Set the label to display
	 * 
	 * @param label
	 */
	public void setLabel(String label)
	{
		this.label = label;
	}

	/**
	 * Set the identifiant of the model
	 * 
	 * @param valueId
	 */
	public void setValueId(String valueId)
	{
		this.valueId = valueId;
	}
}

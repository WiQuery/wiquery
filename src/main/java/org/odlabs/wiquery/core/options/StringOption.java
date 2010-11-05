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

import org.apache.wicket.model.IDetachable;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

/**
 * $Id: $
 * <p>
 * Wraps a {@link String} to be generated as a JavaScript string.
 * <p>
 * Example:
 * <p>
 * The {@link String} <code>hi there!</code> should be rendered as
 * <code>hi there!</code>
 * </p>
 * </p>
 * </p>
 * 
 * @author Lionel Armanet
 * @author Ernesto Reinaldo Barreiro 
 * @since 0.5
 */
public class StringOption implements IDetachable, ITypedOption<String>, IModelOption<String> {
	// Constants
	/** Constant of serialization */
	private static final long serialVersionUID = 6999431516689050752L;
	
	/**
	 * The wrapped {@link String}
	 */
	private IModel<String> value;
	
	/**
	 * <p>
	 * Builds a new instance of {@link StringOption}.
	 * </p>
	 * 
	 * @param literal
	 *            the wrapped {@link String}
	 */
	public StringOption(String value) {
		this(new Model<String>(value));
	}
	
	/**
	 * <p>
	 * Builds a new instance of {@link StringOption}.
	 * </p>
	 * 
	 * @param literal
	 *            the wrapped {@link String}
	 */
	public StringOption(IModel<String> value) {
		this.value = value;
	}

	/**
	 * {@inheritDoc}
	 * @see org.odlabs.wiquery.core.options.IListItemOption#getJavascriptOption()
	 */
	public CharSequence getJavascriptOption() {
		return toString();
	}
	
	/**
	 * {@inheritDoc}
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		String value = this.value.getObject();
		return value != null?this.value.getObject(): null;
	}
	
	
	/*
	 * (non-Javadoc)
	 * @see org.apache.wicket.model.IDetachable#detach()
	 */
	public void detach() {
		if(value != null) {
			value.detach();
		}
	} 
	
	/*
	 * (non-Javadoc)
	 * @see org.odlabs.wiquery.core.options.IModelOption#getModel()
	 */
	public IModel<String> getModel() {
		return value;
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.odlabs.wiquery.core.options.IModelOption#setModel(org.apache.wicket.model.IModel)
	 */
	public void setModel(IModel<String> model) {
		this.value = model;
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.odlabs.wiquery.core.options.ITypedOption#getValue()
	 */
	public String getValue() {
		if(value != null) {
			return value.getObject();
		}
		return null;
	}
}

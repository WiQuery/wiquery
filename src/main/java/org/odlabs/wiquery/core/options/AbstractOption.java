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

import java.io.Serializable;

import org.apache.wicket.model.IDetachable;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

/**
 * Wraps a value to be generated as a JavaScript string.
 * 
 * @author Emond Papegaaij
 * @since 1.2
 */
public abstract class AbstractOption<T extends Serializable> implements IDetachable, ITypedOption<T>,
		IModelOption<T> {
	private static final long serialVersionUID = 1L;

	private IModel<T> value;

	/**
	 * Builds a new instance of {@link AbstractOption}.
	 * 
	 * @param literal
	 *            the wrapped value
	 */
	public AbstractOption(T value) {
		this(new Model<T>(value));
	}

	/**
	 * Builds a new instance of {@link AbstractOption}.
	 * 
	 * @param literal
	 *            the wrapped value
	 */
	public AbstractOption(IModel<T> value) {
		this.value = value;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.odlabs.wiquery.core.options.IListItemOption#getJavascriptOption()
	 */
	public CharSequence getJavascriptOption() {
		return toString();
	}

	@Override
	abstract public String toString();

	public void detach() {
		if (value != null) {
			value.detach();
		}
	}

	public IModel<T> getModel() {
		return value;
	}

	public void setModel(IModel<T> model) {
		this.value = model;
	}

	public T getValue() {
		if (value != null) {
			return value.getObject();
		}
		return null;
	}
}

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
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.wicket.Component;
import org.odlabs.wiquery.core.javascript.JsScope;

/**
 * $Id$
 * <p>
 * Wraps a set of options possibly defined for a WiQuery {@link Component}.
 * </p>
 * 
 * <p>
 * By default, Options are rendered as a JavaScript object like this:
 * 
 * <pre>
 *  {
 *  	option1: 'value1',
 *  	option2: 'value2
 *  }
 * </pre>
 * 
 * This rendering can be customized by creating a {@link IOptionsRenderer}.
 * </p>
 * 
 * @author Lionel Armanet
 * @since 0.5
 */
public class Options implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * The internal structure is a map associating each option label with each
	 * option value.
	 */
	protected Map<String, Object> options = new HashMap<String, Object>();

	/**
	 * The {@link IOptionsRenderer} to use.
	 */
	private IOptionsRenderer optionsRenderer;

	/**
	 * <p>
	 * Build a new empty {@link Options} instance.
	 * </p>
	 */
	public Options() {
		super();
		this.optionsRenderer = DefaultOptionsRenderer.get();
	}

	/**
	 * @return true if no options are defined, false otherwise.
	 */
	public boolean isEmpty() {
		return this.options.isEmpty();
	}

	/**
	 * <p>
	 * Returns the given option value as a String.
	 * </p>
	 * 
	 * @param key
	 *            the option name.
	 */
	public String get(String key) {
		Object object = options.get(key);
		return object == null ? null : object.toString();
	}

	/**
	 * <p>
	 * Returns the given option value.
	 * </p>
	 * 
	 * @param key
	 *            the option name.
	 */
	public int getInt(String key) {
		Object object = this.options.get(key);
		assert (object instanceof Integer);
		return ((Integer) object).intValue();
	}

	/**
	 * <p>
	 * Returns the given option value.
	 * </p>
	 * 
	 * @param key
	 *            the option name.
	 */
	public short getShort(String key) {
		Object object = this.options.get(key);
		assert (object instanceof Short);
		return ((Short) object).shortValue();
	}
	
	/**
	 * <p>
	 * Returns the given option value.
	 * </p>
	 * 
	 * @param key
	 *            the option name.
	 */
	public double getDouble(String key) {
		Object object = this.options.get(key);
		assert (object instanceof Double);
		return ((Double) this.options.get(key)).doubleValue();
	}

	/**
	 * <p>
	 * Returns the given option value.
	 * </p>
	 * 
	 * @param key
	 *            the option name.
	 */
	public float getFloat(String key) {
		Object object = this.options.get(key);
		assert (object instanceof Float);
		return ((Float) this.options.get(key)).floatValue();
	}

	/**
	 * <p>
	 * Returns the given option value.
	 * </p>
	 * 
	 * @param key
	 *            the option name.
	 */
	public boolean getBoolean(String key) {
		Object object = this.options.get(key);
		assert (object instanceof Boolean);
		return ((Boolean) this.options.get(key)).booleanValue();
	}

	/**
	 * <p>
	 * Returns the given option value.
	 * </p>
	 * 
	 * @param key
	 *            the option name.
	 */
	public String getLiteral(String key) {
		Object object = this.options.get(key);
		if (object == null) {
			return null;
		}
		assert (object instanceof LiteralOption);
		return ((LiteralOption) object).getLiteral();
	}
	
	/**
	 * <p>
	 * Puts a complex option value for the given option name.
	 * </p>
	 * 
	 * @param key
	 *            the option name.
	 * @param value
	 *            the IComplexOption.
	 */
	public Options put(String key, IComplexOption value){
		options.put(key, value);
		return this;
	}
	
	/**
	 * <p>
	 * Puts a list of IListItemOption value for the given option name.
	 * </p>
	 * 
	 * @param key
	 *            the option name.
	 * @param value
	 *            the IListItemOption list.
	 */
	public Options put(String key, ICollectionItemOptions value){
		options.put(key, value);
		return this;
	}

	/**
	 * <p>
	 * Puts a {@link String} value for the given option name.
	 * </p>
	 * 
	 * @param key
	 *            the option name.
	 * @param value
	 *            the {@link String} value.
	 */
	public Options put(String key, String value) {
		options.put(key, value);
		return this;
	}

	/**
	 * <p>
	 * Puts a {@link JsScope} value for the given option name.
	 * </p>
	 * 
	 * @param key
	 *            the option name.
	 * @param value
	 *            the {@link JsScope} value.
	 */
	public Options put(String key, JsScope value) {
		options.put(key, value);
		return this;
	}

	/**
	 * <p>
	 * Puts a {@link String} value as a JavaScript literal for the given name.
	 * <p>
	 * Note that the JavaScript resulting from this options will be
	 * <code>'value'</code>
	 * </p>
	 * </p>
	 * 
	 * @param key
	 *            the option name.
	 * @param value
	 *            the {@link LiteralOption} value.
	 */
	public Options putLiteral(String key, String value) {
		options.put(key, new LiteralOption(value));
		return this;
	}

	/**
	 * <p>
	 * Puts an int value for the given option name.
	 * </p>
	 * 
	 * @param key
	 *            the option name.
	 * @param value
	 *            the int value.
	 */
	public Options put(String key, int value) {
		options.put(key, value);
		return this;
	}
	
	/**
	 * <p>
	 * Puts an double value for the given option name.
	 * </p>
	 * 
	 * @param key
	 *            the option name.
	 * @param value
	 *            the float double.
	 */
	public Options put(String key, double value) {
		options.put(key, value);
		return this;
	}

	public Options put(String key, float value) {
		options.put(key, value);
		return this;
	}

	/**
	 * <p>
	 * Put an boolean value for the given option name.
	 * </p>
	 * 
	 * @param key
	 *            the option name.
	 * @param value
	 *            the boolean value.
	 */
	public Options put(String key, boolean value) {
		options.put(key, value);
		return this;
	}

	/**
	 * <p>
	 * Removes an option for a given name.
	 * </p>
	 * 
	 * @param key
	 *            the option's key to remove.
	 */
	public void removeOption(String key) {
		this.options.remove(key);
	}

	/**
	 * Returns the JavaScript statement corresponding to options.
	 */
	public CharSequence getJavaScriptOptions() {
		StringBuilder sb = new StringBuilder();
		this.optionsRenderer.renderBefore(sb);
		int count = 0;
		for (Entry<String, Object> entry : options.entrySet()) {
			String key = entry.getKey();
			Object value = entry.getValue();
			boolean isLast = !(count < options.size() - 1);
			if (value instanceof JsScope) {
				// Case of a JsScope
				sb.append(this.optionsRenderer.renderOption(key,
						((JsScope) value).render(), isLast));
			} 
			else if (value instanceof ICollectionItemOptions) {
				// Case of an ICollectionItemOptions
				sb.append(this.optionsRenderer.renderOption(key,
						((ICollectionItemOptions)value).getJavascriptOption(), isLast));
			} 
			else if (value instanceof IComplexOption) {
				// Case of an IComplexOption
				sb.append(this.optionsRenderer.renderOption(key,
						((IComplexOption)value).getJavascriptOption(), isLast));
			} 
			else {
				// Other cases
				sb.append(this.optionsRenderer.renderOption(key, value,	isLast));
			}
			count++;
		}
		this.optionsRenderer.renderAfter(sb);
		return sb;
	}
	
	/**
	 * <p>
	 * Returns the given option value.
	 * </p>
	 * 
	 * @param key the option name.
	 * @return the complex option
	 */
	public IComplexOption getComplexOption(String key) {
		Object object = this.options.get(key);
		if (object == null) {
			return null;
		}
		assert (object instanceof IComplexOption);
		return (IComplexOption) object;
	}
	
	/**
	 * <p>
	 * Returns the given option value.
	 * </p>
	 * 
	 * @param key the option name.
	 * @return the list
	 */
	public ICollectionItemOptions getListItemOptions(String key) {
		Object object = this.options.get(key);
		if (object == null) {
			return null;
		}
		assert (object instanceof ICollectionItemOptions);
		return (ICollectionItemOptions) object;
	}

	/**
	 * <p>
	 * Returns if the given option is defined or not.
	 * </p>
	 * 
	 * @param key
	 *            the option name.
	 */
	public boolean containsKey(Object key) {
		return options.containsKey(key);
	}

	/**
	 * Sets the renderer to use.
	 */
	public void setRenderer(IOptionsRenderer optionsRenderer) {
		this.optionsRenderer = optionsRenderer;
	}

}

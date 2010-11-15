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

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.wicket.Component;
import org.apache.wicket.model.IDetachable;
import org.apache.wicket.model.IModel;
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
 * @author Hielke Hoeve
 * @author Ernesto Reinaldo Barreiro
 * @since 0.5
 */
public class Options implements IModel<Options> {

	private static final long serialVersionUID = 1L;

	private Component owner;

	/**
	 * The internal structure is a map associating each option label with each
	 * option value.
	 */
	private Map<String, Object> options = new LinkedHashMap<String, Object>();

	/**
	 * The {@link IOptionsRenderer} to use.
	 */
	private IOptionsRenderer optionsRenderer;

	/**
	 * Build a new empty {@link Options} instance that does not bind to a
	 * component. This does not allow the usage of IComponentAssignedModels as
	 * option values.
	 */
	public Options() {
		this(null);
	}

	/**
	 * Build a new empty {@link Options} instance that binds to a component
	 */
	public Options(Component owner) {
		this.owner = owner;
		this.optionsRenderer = DefaultOptionsRenderer.get();
	}

	public void setOwner(Component owner) {
		if (this.owner != null && this.owner != owner)
			throw new IllegalArgumentException(
					"Cannot use the same Options for multiple components");
		this.owner = owner;
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
	 * <p>
	 * Returns the given option value as a String.
	 * </p>
	 * 
	 * @param key
	 *            the option name.
	 */
	public String get(String key) {
		String ret = getValueFromOptions(key, StringOption.class);
		if (ret == null && options.containsKey(ret))
			ret = options.get(key).toString();
		return ret;
	}

	/**
	 * <p>
	 * Returns the given option value.
	 * </p>
	 * 
	 * @param key
	 *            the option name.
	 */
	public Boolean getBoolean(String key) {
		return getValueFromOptions(key, BooleanOption.class);
	}

	/**
	 * <p>
	 * Returns the given option value.
	 * </p>
	 * 
	 * @param key
	 *            the option name.
	 * @return the complex option
	 */
	public IComplexOption getComplexOption(String key) {
		Object object = this.options.get(key);
		if (object instanceof IComplexOption)
			return (IComplexOption) object;
		return null;
	}

	/**
	 * <p>
	 * Returns the given option value.
	 * </p>
	 * 
	 * @param key
	 *            the option name.
	 */
	public Double getDouble(String key) {
		return getValueFromOptions(key, DoubleOption.class);
	}

	/**
	 * <p>
	 * Returns the given option value.
	 * </p>
	 * 
	 * @param key
	 *            the option name.
	 */
	public Float getFloat(String key) {
		return getValueFromOptions(key, FloatOption.class);
	}

	/**
	 * <p>
	 * Returns the given option value.
	 * </p>
	 * 
	 * @param key
	 *            the option name.
	 */
	public Integer getInt(String key) {
		return getValueFromOptions(key, IntegerOption.class);
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
			if (value instanceof IModelOption<?>)
				value = ((IModelOption<?>) value).wrapOnAssignment(owner);
			boolean isLast = !(count < options.size() - 1);
			if (value instanceof JsScope) {
				// Case of a JsScope
				sb.append(this.optionsRenderer.renderOption(key,
						((JsScope) value).render(), isLast));
			} else if (value instanceof ICollectionItemOptions) {
				// Case of an ICollectionItemOptions
				sb.append(this.optionsRenderer.renderOption(key,
						((ICollectionItemOptions) value).getJavascriptOption(),
						isLast));
			} else if (value instanceof IComplexOption) {
				// Case of an IComplexOption
				sb
						.append(this.optionsRenderer.renderOption(key,
								((IComplexOption) value).getJavascriptOption(),
								isLast));
			} else if (value instanceof ITypedOption<?>) {
				// Case of an ITypedOption
				sb.append(this.optionsRenderer
						.renderOption(key, ((ITypedOption<?>) value)
								.getJavascriptOption(), isLast));
			} else {
				// Other cases
				sb
						.append(this.optionsRenderer.renderOption(key, value,
								isLast));
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
	 * @param key
	 *            the option name.
	 * @return the list
	 */
	public ICollectionItemOptions getListItemOptions(String key) {
		Object object = this.options.get(key);
		if (object instanceof ICollectionItemOptions)
			return (ICollectionItemOptions) object;
		return null;
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
		return getValueFromOptions(key, LiteralOption.class);
	}

	/**
	 * <p>
	 * Returns the given option value.
	 * </p>
	 * 
	 * @param key
	 *            the option name.
	 */
	public Short getShort(String key) {
		return getValueFromOptions(key, ShortOption.class);
	}

	/**
	 * @return true if no options are defined, false otherwise.
	 */
	public boolean isEmpty() {
		return this.options.isEmpty();
	}

	private <T, O extends IModelOption<T>> T getValueFromOptions(String key,
			Class<O> optionClass) {
		Object object = this.options.get(key);
		if (optionClass.isInstance(object)) {
			O option = optionClass.cast(object);
			return option.wrapOnAssignment(owner).getValue();
		}
		return null;
	}

	private void putOption(String key, IModelOption<?> option) {
		options.put(key, option);
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
		putOption(key, new BooleanOption(value));
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
	public Options putBoolean(String key, IModel<Boolean> value) {
		putOption(key, new BooleanOption(value));
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
		putOption(key, new DoubleOption(value));
		return this;
	}

	/**
	 * <p>
	 * Puts an IModel &lt;Double&gt; value for the given option name.
	 * </p>
	 * 
	 * @param key
	 *            the option name.
	 * @param value
	 *            the float value.
	 */
	public Options putDouble(String key, IModel<Double> value) {
		putOption(key, new DoubleOption(value));
		return this;
	}

	/**
	 * <p>
	 * Puts an float value for the given option name.
	 * </p>
	 * 
	 * @param key
	 *            the option name
	 * @param value
	 *            The float value
	 * @return
	 */
	public Options put(String key, float value) {
		putOption(key, new FloatOption(value));
		return this;
	}

	/**
	 * <p>
	 * Puts an IModel &lt;Double&gt; value for the given option name.
	 * </p>
	 * 
	 * @param key
	 *            the option name.
	 * @param value
	 *            the float double.
	 */
	public Options putFloat(String key, IModel<Float> value) {
		putOption(key, new FloatOption(value));
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
	public Options put(String key, ICollectionItemOptions value) {
		options.put(key, value);
		return this;
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
	public Options put(String key, IComplexOption value) {
		options.put(key, value);
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
		putOption(key, new IntegerOption(value));
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
	public Options putInteger(String key, IModel<Integer> value) {
		putOption(key, new IntegerOption(value));
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
	 * Puts an short value for the given option name.
	 * </p>
	 * 
	 * @param key
	 *            the option name.
	 * @param value
	 *            the short value.
	 */
	public Options put(String key, short value) {
		putOption(key, new ShortOption(value));
		return this;
	}

	/**
	 * <p>
	 * Puts an short value for the given option name.
	 * </p>
	 * 
	 * @param key
	 *            the option name.
	 * @param value
	 *            the short value.
	 */
	public Options putShort(String key, IModel<Short> value) {
		putOption(key, new ShortOption(value));
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
		putOption(key, new StringOption(value));
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
	public Options putString(String key, IModel<String> value) {
		putOption(key, new StringOption(value));
		return this;
	}

	/**
	 * <p>
	 * Puts a {@link Long} value for the given option name.
	 * </p>
	 * 
	 * @param key
	 *            the option name.
	 * @param value
	 *            the {@link Long} value.
	 */
	public Options put(String key, long value) {
		putOption(key, new LongOption(value));
		return this;
	}

	/**
	 * <p>
	 * Puts a {@link Long} value for the given option name.
	 * </p>
	 * 
	 * @param key
	 *            the option name.
	 * @param value
	 *            the {@link Long} value.
	 */
	public Options putLong(String key, IModel<Long> value) {
		putOption(key, new LongOption(value));
		return this;
	}

	/**
	 * <p>
	 * Puts a {@link String} value as a JavaScript literal for the given name.
	 * <p>
	 * Note that the JavaScript resulting from this options will be <code>'value'</code>
	 * </p>
	 * </p>
	 * 
	 * @param key
	 *            the option name.
	 * @param value
	 *            the {@link LiteralOption} value.
	 */
	public Options putLiteral(String key, String value) {
		putOption(key, new LiteralOption(value));
		return this;
	}

	/**
	 * <p>
	 * Puts a {@link String} value as a JavaScript literal for the given name.
	 * <p>
	 * Note that the JavaScript resulting from this options will be <code>'value'</code>
	 * </p>
	 * </p>
	 * 
	 * @param key
	 *            the option name.
	 * @param value
	 *            the {@link LiteralOption} value.
	 */
	public Options putLiteral(String key, IModel<String> value) {
		putOption(key, new LiteralOption(value));
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
	 * Sets the renderer to use.
	 */
	public void setRenderer(IOptionsRenderer optionsRenderer) {
		this.optionsRenderer = optionsRenderer;
	}

	public Options getObject() {
		return this;
	}

	public void setObject(Options object) {
		throw new UnsupportedOperationException(
				"The setObject() function is not supported for object Options.");
	}

	public void detach() {
		onDetach(this.options);
	}

	@SuppressWarnings("unchecked")
	private void onDetach(Object detachable) {
		if (detachable instanceof Component)
			((Component) detachable).detach();
		else if (detachable instanceof IDetachable)
			((IDetachable) detachable).detach();
		else if (detachable instanceof Map<?, ?>) {
			for (Map.Entry<?, ?> entry : ((Map<?, ?>) detachable).entrySet()) {
				onDetach(entry.getKey());
				onDetach(entry.getValue());
			}
		} else if (detachable instanceof Iterable<?>) {
			Iterator<Object> iter = ((Iterable<Object>) detachable).iterator();
			while (iter.hasNext()) {
				onDetach(iter.next());
			}
		}
	}
}

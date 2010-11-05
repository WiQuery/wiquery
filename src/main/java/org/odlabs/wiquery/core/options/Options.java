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
import org.apache.wicket.model.IComponentAssignedModel;
import org.apache.wicket.model.IDetachable;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.IWrapModel;
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
public class Options implements IModel<Options>, IComponentAssignedModel<Options>, IWrapModel<Options>  {

	private static final long serialVersionUID = 1L;

	/**
	 * The internal structure is a map associating each option label with each
	 * option value.
	 */
	protected Map<String, Object> options = new LinkedHashMap<String, Object>();

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
		Object object = options.get(key);
		if(object instanceof StringOption) {
			return ((StringOption)object).getValue();
		} else if(object   != null) {
			return object.toString();
		}
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
	public Boolean getBoolean(String key) {
		Object object = this.options.get(key);
		if(object instanceof BooleanOption) {
			BooleanOption option = ((BooleanOption)object);
			return option.getValue();
		}
		return null;
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
		if(object instanceof IComplexOption)
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
		Object object = this.options.get(key);
		if(object instanceof DoubleOption) {
			DoubleOption option = ((DoubleOption)object);
			return option.getValue();
		}
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
	public Float getFloat(String key) {
		Object object = this.options.get(key);
		if(object instanceof FloatOption) {
			FloatOption option = ((FloatOption)object);
			return option.getValue();
		}
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
	public Integer getInt(String key) {
		Object object = this.options.get(key);
		if(object instanceof IntegerOption) {
			IntegerOption option = ((IntegerOption)object);
			return option.getValue();
		}
		return null;
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
			} else if (value instanceof ITypedOption<?>) {
				// Case of an ITypedOption
				sb.append(this.optionsRenderer.renderOption(key,((ITypedOption<?>)value).getJavascriptOption(), isLast));
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
	 * @return the list
	 */
	public ICollectionItemOptions getListItemOptions(String key) {
		Object object = this.options.get(key);		
		if(object instanceof ICollectionItemOptions)
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
		Object object = this.options.get(key);		
		if(object instanceof LiteralOption)
			return ((LiteralOption) object).getLiteral();
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
	public Short getShort(String key) {
		Object object = this.options.get(key);
		if(object instanceof ShortOption) {
			ShortOption option = ((ShortOption)object);
			return option.getValue();
		}
		return null;
	}

	/**
	 * @return true if no options are defined, false otherwise.
	 */
	public boolean isEmpty() {
		return this.options.isEmpty();
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
		options.put(key, new BooleanOption(value));
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
		options.put(key, new BooleanOption(value));
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
		options.put(key, new DoubleOption(value));
		return this;
	}

	/**
	 * <p>
	 * 	Puts an IModel &lt;Double&gt; value for the given option name.
	 * </p>
	 * 
	 * @param key
	 *            the option name.
	 * @param value
	 *            the float value.
	 */
	public Options putDouble(String key, IModel<Double> value) {
		options.put(key, new DoubleOption(value));
		return this;
	}
	
	/**
	 * <p>
	 * 	Puts an float value for the given option name.
	 * </p>
	 * 
	 * @param key the option name
	 * @param value The float value
	 * @return
	 */
	public Options put(String key, float value) {
		options.put(key, new FloatOption(value));
		return this;
	}

	/**
	 * <p>
	 * 	Puts an IModel &lt;Double&gt; value for the given option name.
	 * </p>
	 * 
	 * @param key
	 *            the option name.
	 * @param value
	 *            the float double.
	 */
	public Options putFloat(String key, IModel<Float> value) {
		options.put(key, new FloatOption(value));
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
		options.put(key, new IntegerOption(value));
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
		options.put(key, new IntegerOption(value));
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
		options.put(key, new ShortOption(value));
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
		options.put(key, new ShortOption(value));
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
		options.put(key, new StringOption(value));
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
		options.put(key, new StringOption(value));
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
		options.put(key, new LiteralOption(value));
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
		options.put(key, new LiteralOption(value));
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

	/*
	 * (non-Javadoc)
	 * @see org.apache.wicket.model.IComponentAssignedModel#wrapOnAssignment(org.apache.wicket.Component)
	 */
	public IWrapModel<Options> wrapOnAssignment(Component component) {
		onWrap(this, component);
		return this;
	}
	
	/*
	 * 
	 * Returns a wrapped model or null 
	 */
	@SuppressWarnings("unchecked")
	private Object onWrap(Object object, Component component) {
		if (object instanceof Options) {
			Options options = (Options)object;
			Map<String, Object> temp = new LinkedHashMap<String, Object>();
			for (Map.Entry<String, Object> entry : options.options.entrySet()) {
				String key = entry.getKey();
				Object value = entry.getValue();
				// try to wrap the value.
				Object wrapped = onWrap(value, component);
				if(wrapped != null) {
					// if value has been wrapped the we add it.
					temp.put(key, wrapped);
				} else {
					temp.put(key, value);
				}
			}
			this.options = temp;
		} else if(object instanceof IComponentAssignedModel<?>) {
			IComponentAssignedModel<?> assignedModel = (IComponentAssignedModel<?>)object;
			return assignedModel.wrapOnAssignment(component);
		} else if(object instanceof IModelOption<?>) {
			IModelOption<?> modelOption = (IModelOption<?>)object;
			Object wrapped = onWrap(modelOption.getModel(), component);
			if(wrapped != null) {
				// if inner model has been wrapped
				// then reset model
				modelOption.setModel((IModel)wrapped);
				return modelOption;
			}
			
		}
		return null;	 
	}
	

	public IModel<?> getWrappedModel() {
		return this;
	}

}

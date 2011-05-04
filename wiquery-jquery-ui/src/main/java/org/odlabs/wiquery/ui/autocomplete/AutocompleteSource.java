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

import org.odlabs.wiquery.core.javascript.JsScope;
import org.odlabs.wiquery.core.javascript.JsUtils;
import org.odlabs.wiquery.core.options.ArrayItemOptions;
import org.odlabs.wiquery.core.options.IComplexOption;
import org.odlabs.wiquery.core.options.IListItemOption;

/**
 * $Id$
 * <p>
 * Bean for the source option into the Autocomplete component
 * </p>
 * 
 * @author Julien Roche
 * @since 1.1
 */
public class AutocompleteSource implements IComplexOption {
	// Constants
	/**	Constant of serialization */
	private static final long serialVersionUID = 3404088696595137949L;
	
	// Properties
	private ArrayItemOptions<? extends IListItemOption> array;
	private JsScope jsScope;
	private String string;
	
	/**Constructor
	 * @param array Array parameter
	 */
	public AutocompleteSource(ArrayItemOptions<? extends IListItemOption> array) {
		this(array, null, null);
	}
	
	/**Constructor
	 * @param array Array parameter
	 * @param jsScope Scope parameter
	 * @param string String parameter
	 */
	private AutocompleteSource(ArrayItemOptions<? extends IListItemOption> array,
			JsScope jsScope, String string) {
		super();
		setParam(array, jsScope, string);
	}

	/**Constructor
	 * @param jsScope Scope parameter
	 */
	public AutocompleteSource(JsScope jsScope) {
		this(null, jsScope, null);
	}
	
	/**Constructor
	 * @param string String parameter
	 */
	public AutocompleteSource(String string) {
		this(null, null, string);
	}
	
	/**
	 * @return the array param
	 */
	public ArrayItemOptions<? extends IListItemOption> getArray() {
		return array;
	}
	
	/**
	 * {@inheritDoc}
	 * @see org.odlabs.wiquery.core.options.IComplexOption#getJavascriptOption()
	 */
	public CharSequence getJavascriptOption() {
		if(array == null && jsScope == null && string == null){
			throw new IllegalArgumentException("The AutocompleteSource must have one not null parameter");
		}
		
		CharSequence sequence = null;
		
		if(array != null){
			sequence = array.getJavascriptOption();
		}
		else if(jsScope != null){
			sequence = jsScope.render();
		}
		else if(string != null){
			sequence = JsUtils.quotes(string);
		}
		else{
			throw new IllegalArgumentException("The AutocompleteSource must have one not null parameter");
		}
		
		return sequence;
	}

	/**
	 * @return the scope param
	 */
	public JsScope getJsScope() {
		return jsScope;
	}

	/**
	 * @return the string param
	 */
	public String getString() {
		return string;
	}

	/**
	 * Set the array param
	 * @param array
	 */
	public void setArray(ArrayItemOptions<? extends IListItemOption> array) {
		setParam(array, null, null);
	}

	/**
	 * Set the scope param
	 * @param jsScope
	 */
	public void setJsScope(JsScope jsScope) {
		setParam(null, jsScope, null);
	}

	/**Method setting the right parameter
	 * @param array Array parameter
	 * @param jsScope Scope parameter
	 * @param string String parameter
	 */
	private void setParam(ArrayItemOptions<? extends IListItemOption> array,
			JsScope jsScope, String string) {
		this.array = array;
		this.jsScope = jsScope;
		this.string = string;
	}

	/**
	 * Set the string param
	 * @param string
	 */
	public void setString(String string) {
		setParam(null, null, string);
	}
}

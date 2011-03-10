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
package org.odlabs.wiquery.ui.accordion;

import org.codehaus.jackson.annotate.JsonValue;
import org.odlabs.wiquery.core.options.IComplexOption;
import org.odlabs.wiquery.core.options.LiteralOption;

/**
 * $Id: AccordionActive.java
 * <p>
 * Bean for the active option into the Accordion component
 * </p>
 * 
 * @author Julien Roche
 * @since 1.0
 */
public class AccordionActive implements IComplexOption {
	// Constants
	/**	Constant of serialization */
	private static final long serialVersionUID = 3404088696595137949L;
	
	// Properties
	private Boolean booleanParam;
	private Integer intParam;
	private LiteralOption selector;
	private String jQueryParam;
	
	/**Constructor
	 * @param booleanParam Boolean parameter
	 */
	public AccordionActive(Boolean booleanParam) {
		this(booleanParam, null, null, null);
	}
	
	/**Constructor
	 * @param intParam Integer parameter
	 */
	public AccordionActive(Integer intParam) {
		this(null, intParam, null, null);
	}

	/**Constructor
	 * @param selector Selector parameter
	 */
	public AccordionActive(LiteralOption selector) {
		this(null, null, selector, null);
	}
	
	/**Constructor
	 * @param jQueryParam jQuery parameter
	 */
	public AccordionActive(String jQueryParam) {
		this(null, null, null, jQueryParam);
	}
	
	/**Constructor
	 * @param booleanParam Boolean parameter
	 * @param intParam Integer parameter
	 * @param selector Selector parameter
	 * @param jQueryParam jQuery parameter
	 */
	private AccordionActive(Boolean booleanParam, Integer intParam,
			LiteralOption selector, String jQueryParam) {
		super();
		setParam(booleanParam, intParam, selector, jQueryParam);
	}
	
	/**
	 * @return the booleanParam
	 */
	public boolean isBooleanParam() {
		return booleanParam;
	}

	/**
	 * @return the intParam
	 */
	public int getIntParam() {
		return intParam;
	}

	/**
	 * @return the selectorOrElementParam
	 * @deprecated will be removed in 1.4
	 */
	public LiteralOption getSelectorOrElementParam() {
		return selector;
	}
	
	/**
	 * @return the Selector
	 */
	public LiteralOption getSelector() {
		return selector;
	}
	
	@JsonValue
	public CharSequence getJavascriptOption() {
		if(booleanParam == null && intParam == null && jQueryParam == null &&
				selector == null){
			throw new IllegalArgumentException("The AccordionActive must have one not null parameter");
		}
		
		CharSequence sequence = null;
		
		if(booleanParam != null){
			sequence = booleanParam.toString();
		}
		else if(intParam != null){
			sequence = intParam.toString();
		}
		else if(jQueryParam != null){
			sequence = jQueryParam;
		}
		else if(selector != null){
			sequence = selector.toString();
		}
		else{
			throw new IllegalArgumentException("The AccordionActive must have one not null parameter");
		}
		
		return sequence;
	}

	/**
	 * @return the jQueryParam
	 */
	public String getJQueryParam() {
		return jQueryParam;
	}

	/**Set's the boolean parameter
	 * @param booleanParam the booleanParam to set
	 */
	public void setBooleanParam(boolean booleanParam) {
		setParam(booleanParam, null, null, null);
	}

	/**Set's the int parameter
	 * @param intParam the intParam to set
	 */
	public void setIntParam(int intParam) {
		setParam(null, intParam, null, null);
	}
	
	/**Set's the jQuery parameter
	 * @param queryParam the jQueryParam to set
	 */
	public void setJQueryParam(String queryParam) {
		setParam(null, null, null, queryParam);
	}
	
	/**Method setting the right parameter
	 * @param booleanParam Boolean parameter
	 * @param intParam Integer parameter
	 * @param selectorOrElementParam Selector or element parameter
	 * @param jQueryParam JQuery parameter
	 */
	private void setParam(Boolean booleanParam, Integer intParam,
			LiteralOption selectorOrElementParam, String jQueryParam) {
		this.booleanParam = booleanParam;
		this.intParam = intParam;
		this.selector = selectorOrElementParam;
		this.jQueryParam = jQueryParam;
	}

	/**Set's the selector or element parameter
	 * @param selectorOrElementParam the selectorOrElementParam to set
	 * @deprecated will be removed in 1.4
	 */
	public void setSelectorOrElementParam(LiteralOption selectorOrElementParam) {
		setParam(null, null, selectorOrElementParam, null);
	}
	
	/**Set's the selector
	 * @param selector the selector to set
	 */
	public void setSelector(LiteralOption selector) {
		setParam(null, null, selector, null);
	}
}

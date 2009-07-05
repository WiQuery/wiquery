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
package org.odlabs.wiquery.ui.datepicker;

import org.odlabs.wiquery.core.options.IComplexOption;
import org.odlabs.wiquery.core.options.LiteralOption;

/**
 * $Id: DatePickerShortYearCutOff.java
 * <p>
 * Bean for the shortYearCutOff option for the DatePicker component
 * </p>
 * 
 * @author Julien Roche
 * @since 1.0
 */
public class DatePickerShortYearCutOff implements IComplexOption {	
	// Constants
	/**	Constant of serialization */
	private static final long serialVersionUID = 3404088696595137949L;
	
	// Properties
	private Short shortParam;
	private String literalParam;
	
	/**Constructor
	 * @param shortParam Short parameter
	 */
	public DatePickerShortYearCutOff(Short shortParam) {
		this(shortParam, null);
	}

	/**Constructor
	 * @param literalParam Literal parameter
	 */
	public DatePickerShortYearCutOff(String literalParam) {
		this(null, literalParam);
	}
	
	/**Constructor
	 * @param shortParam Short parameter
	 * @param literalParam Literal parameter
	 */
	private DatePickerShortYearCutOff(Short shortParam, String literalParam) {
		super();
		setParam(shortParam, literalParam);
	}

	/**
	 * @return the literalParam
	 */
	public String getLiteralParam() {
		return literalParam;
	}
	
	/**
	 * @return the shortParam
	 */
	public Short getShortParam() {
		return shortParam;
	}
	
	/* (non-Javadoc)
	 * @see org.odlabs.wiquery.core.options.IComplexOption#getJavascriptItemOptions()
	 */
	public CharSequence getJavascriptOption() {
		if(shortParam == null && literalParam == null){
			throw new IllegalArgumentException("The DatePickerShortYearCutOff must have one not null parameter");
		}
		
		CharSequence sequence = null;
		
		if(shortParam != null){
			sequence = shortParam.toString();
		}
		else if(literalParam != null){
			sequence = new LiteralOption(literalParam).toString();
		}
		else{
			throw new IllegalArgumentException("The DatePickerShortYearCutOff must have one not null parameter");
		}
		
		return sequence;
	}
	
	/**Set's the literal parameter
	 * @param literalParam the literal to set
	 */
	public void setLiteralParam(String literalParam) {
		setParam(null, literalParam);
	}
	
	/**Set's the short parameter
	 * @param shortParam short parameter
	 */
	public void setShortParam(Short shortParam) {
		setParam(shortParam, null);
	}
	
	/**Method setting the right parameter
	 * @param shortParam Short parameter
	 * @param literalParam Literal parameter
	 */
	private void setParam(Short shortParam, String literalParam) {
		this.shortParam = shortParam;
		this.literalParam = literalParam;
	}
}

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

import java.util.Date;

import org.odlabs.wiquery.core.javascript.helper.DateHelper;
import org.odlabs.wiquery.core.options.IComplexOption;
import org.odlabs.wiquery.core.options.LiteralOption;

/**
 * $Id: DateOption.java
 * <p>
 * Bean for the defaultDate, maxDate, minDate option for the DatePicker component
 * </p>
 * 
 * @author Julien Roche
 * @since 1.0
 */
public class DateOption implements IComplexOption {
	// Constants
	/**	Constant of serialization */
	private static final long serialVersionUID = 3404088696595137949L;
	
	// Properties
	private Date dateParam;
	private Short shortParam;
	private String literalParam;
	
	/**Constructor
	 * @param shortParam Short parameter
	 */
	public DateOption(Short shortParam) {
		this(shortParam, null, null);
	}
	
	/**Constructor
	 * @param literalParam literal parameter
	 */
	public DateOption(String literalParam) {
		this(null, literalParam, null);
	}
	
	/**Constructor
	 * @param dateParam short parameter
	 */
	public DateOption(Date dateParam) {
		this(null, null, dateParam);
	}
	
	/**Constructor
	 * @param shortParam Short parameter
	 * @param literalParam literal parameter
	 * @param dateParam short parameter
	 */
	private DateOption(Short shortParam, String literalParam, Date dateParam) {
		super();
		setParam(shortParam, literalParam, dateParam);
	}
	
	/**
	 * @return the dateParam
	 */
	public Date getDateParam() {
		return dateParam;
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
		if(shortParam == null && literalParam == null && dateParam == null){
			throw new IllegalArgumentException("The DateOption must have one not null parameter");
		}
		
		CharSequence sequence = null;
		
		if(shortParam != null){
			sequence = shortParam.toString();
		}
		else if(literalParam != null){
			sequence = new LiteralOption(literalParam).toString();
		}
		else if(dateParam != null){			
			sequence = DateHelper.getJSDate(dateParam);
		}
		else{
			throw new IllegalArgumentException("The DateOption must have one not null parameter");
		}
		
		return sequence;
	}
	
	/**Set's the date parameter
	 * @param dateParam short parameter
	 */
	public void setDateParam(Date dateParam) {
		setParam(null, null, dateParam);
	}
	
	/**Set's the literal parameter
	 * @param literalParam literal parameter
	 */
	public void setLiteralParam(String literalParam) {
		setParam(null, literalParam, null);
	}
	
	/**Set's the short parameter
	 * @param shortParam short parameter
	 */
	public void setShortParam(Short shortParam) {
		setParam(shortParam, null, null);
	}
	
	/**Method setting the right parameter
	 * @param shortParam Short parameter
	 * @param literalParam literal parameter
	 * @param dateParam short parameter
	 */
	private void setParam(Short shortParam, String literalParam, Date dateParam) {
		this.shortParam = shortParam;
		this.literalParam = literalParam;
		this.dateParam = dateParam;
	}
}

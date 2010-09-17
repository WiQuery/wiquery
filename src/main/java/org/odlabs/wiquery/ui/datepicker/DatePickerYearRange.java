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
 * $Id: DatePickerYearRange.java
 * <p>
 * Bean for the yearRange option for the DatePicker component
 * </p>
 * 
 * @author Julien Roche
 * @since 1.0
 */
public class DatePickerYearRange extends Object implements IComplexOption {
	// Constants
	/**	Constant of serialization */
	private static final long serialVersionUID = 1L;
	
	// Properties
	private short yearFrom;
	private short yearTo;
	
	/**
	 * Constructor
	 * @param yearFrom the range's start
	 * @param yearTo the range's end
	 */
	public DatePickerYearRange(short yearFrom, short yearTo) {
		super();
		this.yearFrom = yearFrom;
		this.yearTo = yearTo;
	}

	/* (non-Javadoc)
	 * @see org.odlabs.wiquery.core.options.IComplexOption#getJavascriptOption()
	 */
	public CharSequence getJavascriptOption() {
		if (yearFrom > yearTo) {
			throw new IllegalArgumentException("Invalid year range");
		}
		
		String yearFromStr = (this.yearFrom > 0 ? "+" : "") + Short.toString(this.yearFrom);
		String yearToStr = (this.yearTo > 0 ? "+" : "") + Short.toString(this.yearTo);
		
		return new LiteralOption(yearFromStr + ":" + yearToStr).toString();
	}
	
	/**
	 * @return the yearFrom
	 */
	public short getYearFrom() {
		return yearFrom;
	}

	/**
	 * @return the yearTo
	 */
	public short getYearTo() {
		return yearTo;
	}

	/**
	 * @param yearFrom the yearFrom to set
	 */
	public void setYearFrom(short yearFrom) {
		this.yearFrom = yearFrom;
	}

	/**
	 * @param yearTo the yearTo to set
	 */
	public void setYearTo(short yearTo) {
		this.yearTo = yearTo;
	}
}

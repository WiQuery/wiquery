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
	/** Constant of serialization */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 
	 * <p>
	 * 	Control for the DatePickerYearRange
	 * </p>
	 *
	 * @author Julien Roche
	 * @since 1.2.2
	 */
	public enum DatePickerYearRangeControl {
		ABSOLUTE,
		RELATIVE_SELECTED_YEAR,
		RELATIVE_TODAY;
	}

	// Properties
	private Short yearFrom;
	private Short yearTo;
	private DatePickerYearRangeControl controlTo;
	private DatePickerYearRangeControl controlFrom;

	/**
	 * Constructor which sets absolute yearFrom and yearTo, eg: 2000 and 2020.
	 * 
	 * @param yearFrom
	 *            the range's start
	 * @param yearTo
	 *            the range's end
	 */
	public DatePickerYearRange(short yearFrom, short yearTo) {
		super();
		this.yearFrom = yearFrom;
		this.yearTo = yearTo;
		this.controlTo = DatePickerYearRangeControl.ABSOLUTE;
		this.controlFrom = DatePickerYearRangeControl.ABSOLUTE;
	}

	/**
	 * Constructor which sets relative yearFrom and yearTo, eh: 2010 and -10.
	 * 
	 * @param yearFrom
	 *            the range's start
	 * @param yearTo
	 *            the range's end
	 * @param controlFrom
	 *            Control for the range's start
	 * @param controlTo
	 *            Control for the range's end
	 */
	public DatePickerYearRange(short yearFrom, short yearTo,
			DatePickerYearRangeControl controlFrom, DatePickerYearRangeControl controlTo) {
		super();
		this.yearFrom = yearFrom;
		this.yearTo = yearTo;
		this.controlTo = controlTo == null ? DatePickerYearRangeControl.ABSOLUTE : controlTo;
		this.controlFrom = controlFrom == null ? DatePickerYearRangeControl.ABSOLUTE : controlFrom;
	}
	
	/**
	 * Constructor which mixed raltive and absolute. eh: c-10 and
	 * 
	 * @param yearFrom
	 *            the range's start
	 * @param yearTo
	 *            the range's end
	 * @param yearRelativeToToday
	 *            determines whether to count from today's year or the currently
	 *            selected year.
	 */
	public DatePickerYearRange(short yearFrom, short yearTo,
			boolean yearRelativeToToday) {
		super();
		this.yearFrom = yearFrom;
		this.yearTo = yearTo;
		this.controlTo = yearRelativeToToday ? DatePickerYearRangeControl.RELATIVE_TODAY : DatePickerYearRangeControl.RELATIVE_SELECTED_YEAR;
		this.controlFrom = yearRelativeToToday ? DatePickerYearRangeControl.RELATIVE_TODAY : DatePickerYearRangeControl.RELATIVE_SELECTED_YEAR;
	}
	
	/**
	 * {@inheritDoc}
	 * @see org.odlabs.wiquery.core.options.IComplexOption#getJavascriptOption()
	 */
	public CharSequence getJavascriptOption() {
		if (yearFrom == null || yearTo == null || controlTo == null || controlFrom == null) {
			throw new IllegalArgumentException("The DatePickerYearRange needs all arguments !!");
		}
		
		return new LiteralOption(
			generateRangeFormat(yearFrom, controlFrom) 
			+ ":" 
			+ generateRangeFormat(yearTo, controlTo) ).toString();
	}
	
	/**
	 * @return the format for the specified range part
	 */
	private String generateRangeFormat(Short value, DatePickerYearRangeControl control) {
		if(control == DatePickerYearRangeControl.ABSOLUTE){
			return value.toString();
			
		}
		
		String preStr = value > 0 ? "+" : "";
		
		if(control == DatePickerYearRangeControl.RELATIVE_SELECTED_YEAR){
			preStr = "c" + preStr;
		}
		
		return preStr + value.toString();
	}

	public void setAbsoluteRange(short yearFrom, short yearTo) {
		this.yearFrom = yearFrom;
		this.yearTo = yearTo;
		this.controlTo = DatePickerYearRangeControl.ABSOLUTE;
		this.controlFrom = DatePickerYearRangeControl.ABSOLUTE;
	}

	public void setRelativeRange(short yearFrom, short yearTo,
			boolean yearRelativeToToday) {
		this.yearFrom = yearFrom;
		this.yearTo = yearTo;
		this.controlTo = yearRelativeToToday ? DatePickerYearRangeControl.RELATIVE_TODAY : DatePickerYearRangeControl.RELATIVE_SELECTED_YEAR;
		this.controlFrom = yearRelativeToToday ? DatePickerYearRangeControl.RELATIVE_TODAY : DatePickerYearRangeControl.RELATIVE_SELECTED_YEAR;
	}
	
	public void setRange(short yearFrom, short yearTo,
			DatePickerYearRangeControl controlFrom, DatePickerYearRangeControl controlTo) {
		this.yearFrom = yearFrom;
		this.yearTo = yearTo;
		this.controlTo = controlTo == null ? DatePickerYearRangeControl.ABSOLUTE : controlTo;
		this.controlFrom = controlFrom == null ? DatePickerYearRangeControl.ABSOLUTE : controlFrom;
	}
}

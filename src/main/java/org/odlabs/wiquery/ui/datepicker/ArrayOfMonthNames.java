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

/**
 * $Id: ArrayOfMonthNames.java
 * <p>
 * List of day names for the monthnames, monthnamesShort into the 
 * DatePicker component
 * </p>
 * 
 * @author Julien Roche
 * @since 1.0
 */
public class ArrayOfMonthNames extends AbstractArrayOfDateNames {
	// Constants
	/** Constant of serialization */
	private static final long serialVersionUID = -9097637272858071731L;
	
	/** Number of names */
	public static final Integer NUMBER_OF_NAMES = 12;
	
	/**
	 * @param january		Name for January
	 * @param february		Name for February
	 * @param march			Name for March
	 * @param april			Name for April
	 * @param may			Name for May
	 * @param june			Name for June
	 * @param july			Name for July
	 * @param august		Name for August
	 * @param september		Name for September
	 * @param october		Name for October
	 * @param november		Name for November
	 * @param december		Name for December
	 */
	public ArrayOfMonthNames(String january, String february, String march, 
			String april, String may, String june, String july, String august,
			String september, String october, String november, String december) {
		super(new String[]{
				january, 
				february, 
				march,
				april,
				may,
				june,
				july,
				august,
				september,
				october,
				november,
				december});
	}

	/* (non-Javadoc)
	 * @see org.odlabs.wiquery.ui.datepicker.AbstractArrayOfDateNames#getNumberOfName()
	 */
	@Override
	public Integer getNumberOfName() {
		return NUMBER_OF_NAMES;
	}
}

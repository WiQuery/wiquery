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
package org.wicketstuff.wiquery.ui.datepicker;

/**
 * $Id: ArrayOfDayNames.java
 * <p>
 * List of day names for the daynames, daynamesMin, daynamesShort into the DatePicker component
 * </p>
 * 
 * @author Julien Roche
 * @since 1.0
 */
public class ArrayOfDayNames extends AbstractArrayOfDateNames
{
	// Constants
	/** Constant of serialization */
	private static final long serialVersionUID = -9097637272858071731L;

	/** Number of names */
	public static final Integer NUMBER_OF_NAMES = 7;

	/**
	 * Constructor
	 * 
	 * @param sunday
	 *            Name for Sunday
	 * @param monday
	 *            Name for Monday
	 * @param tuesday
	 *            Name for Tuesday
	 * @param wednesday
	 *            Name for Wednesday
	 * @param thursday
	 *            Name for Thursday
	 * @param friday
	 *            Name for Friday
	 * @param saturday
	 *            Name for Saturday
	 */
	public ArrayOfDayNames(String sunday, String monday, String tuesday, String wednesday,
		String thursday, String friday, String saturday)
	{
		super(new String[] { sunday, monday, tuesday, wednesday, thursday, friday, saturday });
	}

	@Override
	public Integer getNumberOfName()
	{
		return NUMBER_OF_NAMES;
	}
}

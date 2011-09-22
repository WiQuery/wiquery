/*
 * Copyright (c) 2008 Objet Direct
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
package org.odlabs.wiquery.core.javascript.helper;

import java.util.Calendar;
import java.util.Date;

/**
 * $Id$
 * <p>
 * Helper to create javascript Date instance.
 * </p>
 * 
 * @author Julien Roche
 * @since 1.0
 */
public class DateHelper
{
	/**
	 * Method to generate a javascript sequence to create an instance of date
	 * 
	 * @return the javascript charsequence
	 */
	public static CharSequence getJSDate()
	{
		return "new Date()";
	}

	/**
	 * Method to generate a javascript sequence to create an instance of date
	 * 
	 * @param calendar
	 *            Date to generate
	 * @return the javascript charsequence
	 */
	public static CharSequence getJSDate(Calendar calendar)
	{
		StringBuffer dateJavascript = new StringBuffer();
		dateJavascript.append("new Date(");
		dateJavascript.append(calendar.get(Calendar.YEAR)).append(",");
		dateJavascript.append(calendar.get(Calendar.MONTH)).append(",");
		dateJavascript.append(calendar.get(Calendar.DAY_OF_MONTH)).append(",");
		dateJavascript.append(calendar.get(Calendar.HOUR_OF_DAY)).append(",");
		dateJavascript.append(calendar.get(Calendar.MINUTE)).append(",");
		dateJavascript.append(calendar.get(Calendar.SECOND)).append(",");
		dateJavascript.append(calendar.get(Calendar.MILLISECOND));
		dateJavascript.append(")");

		return dateJavascript;
	}

	/**
	 * Method to generate a javascript sequence to create an instance of date
	 * 
	 * @param date
	 *            Date to generate
	 * @return the javascript charsequence
	 */
	public static CharSequence getJSDate(Date date)
	{
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);

		return getJSDate(calendar);
	}
}

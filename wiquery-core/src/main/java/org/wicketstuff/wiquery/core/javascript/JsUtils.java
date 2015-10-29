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
package org.wicketstuff.wiquery.core.javascript;

import org.wicketstuff.wiquery.core.events.EventLabel;

/**
 * $Id: JsUtils.java 1714 2011-09-22 20:38:30Z hielke.hoeve $
 * <p>
 * Helper class to ease JavaScript integration.
 * </p>
 * 
 * @author Lionel Armanet
 * @since 0.7
 */
public final class JsUtils
{

	/**
	 * @return a javascript representation of the array
	 */
	public static CharSequence array(CharSequence... args)
	{
		StringBuilder array = new StringBuilder();
		array.append('[');

		if (args.length > 0)
		{
			array.append(args[0]);

			for (int i = 1; i < args.length; i++)
			{
				array.append(", ").append(args[i]);
			}
		}

		array.append(']');
		return array.toString();
	}

	/**
	 * Double quotes the given string (eg. makes a JavaScript String).
	 */
	public static String doubleQuotes(CharSequence stringToDoubleQuote)
	{
		return doubleQuotes(stringToDoubleQuote, false);
	}

	/**
	 * Double quotes the given string (eg. makes a JavaScript String).
	 */
	public static String doubleQuotes(CharSequence stringToDoubleQuote, boolean escapeDoubleQuote)
	{
		return "\""
			+ (escapeDoubleQuote ? escapeDoubleQuote(stringToDoubleQuote) : stringToDoubleQuote)
			+ "\"";
	}

	/**
	 * @return the sequence with escaped double quotes
	 */
	public static String escapeDoubleQuote(CharSequence stringToDoubleQuote)
	{
		return stringToDoubleQuote == null ? null : stringToDoubleQuote.toString().replace("\"",
			"\\\"");
	}

	/**
	 * @return the sequence with escaped quotes
	 */
	public static String escapeQuote(CharSequence stringToQuote)
	{
		return stringToQuote == null ? null : stringToQuote.toString().replace("'", "\\'");
	}

	/**
	 * Converts the given array of {@link EventLabel} to a {@link String}.
	 */
	public static String implode(EventLabel... eventLabels)
	{
		if (eventLabels.length == 0)
		{
			return "''";
		}
		StringBuilder output = new StringBuilder();
		output.append('\'').append(eventLabels[0].getEventLabel());
		for (int i = 1; i < eventLabels.length; i++)
		{
			EventLabel eventLabel = eventLabels[i];
			output.append(' ').append(eventLabel.getEventLabel());
		}
		output.append('\'');
		return output.toString();
	}

	/**
	 * Quotes the given string (eg. makes a JavaScript String).
	 */
	public static String quotes(CharSequence stringToQuote)
	{
		return quotes(stringToQuote, false);
	}

	/**
	 * Quotes the given string (eg. makes a JavaScript String).
	 */
	public static String quotes(CharSequence stringToQuote, boolean escapeQuote)
	{
		return "'" + (escapeQuote ? escapeQuote(stringToQuote) : stringToQuote) + "'";
	}

	/**
	 * Converts an int to a String. (common alias of valueOf).
	 */
	public static String string(int value)
	{
		return String.valueOf(value);
	}
	
	private JsUtils()
	{
	}
}

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
package org.odlabs.wiquery.core.options;

/**
 * $Id$
 * <p>
 * Renders the a {@link Options} object as a JavaScript object, e.g.: *
 * 
 * <pre>
 *  {
 *  	option1: 'value1',
 *  	option2: 'value2
 *  }
 * </pre>
 * 
 * </p>
 * 
 * <p>
 * This renderer has no state, it's accessed with the singleton pattern:
 * <code>DefaultOptionsRenderer.get()</code>
 * </p>
 * 
 * @author Lionel Armanet
 * @since 1.0
 */
public class DefaultOptionsRenderer implements IOptionsRenderer
{

	private static final long serialVersionUID = 6303118317934309154L;

	private static IOptionsRenderer instance = new DefaultOptionsRenderer();;

	/**
	 * @return the {@link IOptionsRenderer} instance.
	 */
	public static IOptionsRenderer get()
	{
		return instance;
	}

	/**
	 * 
	 */
	private DefaultOptionsRenderer()
	{

	}

	public void renderAfter(StringBuilder stringBuilder)
	{
		stringBuilder.append("}");
	}

	public void renderBefore(StringBuilder stringBuilder)
	{
		stringBuilder.append("{");
	}

	public CharSequence renderOption(String name, Object value, boolean isLast)
	{
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(name).append(": ").append(value);
		if (!isLast)
		{
			stringBuilder.append(", ");
		}
		return stringBuilder;
	}

}

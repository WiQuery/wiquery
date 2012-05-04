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
package org.odlabs.wiquery.ui.core;

import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.markup.head.CssHeaderItem;
import org.apache.wicket.markup.head.HeaderItem;
import org.apache.wicket.resource.JQueryPluginResourceReference;
import org.odlabs.wiquery.ui.themes.WiQueryCoreThemeResourceReference;

/**
 * $Id: CoreUIJavaScriptResourceReference.java 1145 2011-08-02 08:57:23Z
 * hielke.hoeve@gmail.com $
 * <p>
 * References the core jQuery UI library.
 * </p>
 * 
 * @author Lionel Armanet
 * @since 0.5
 */
public class CoreUIJavaScriptResourceReference extends JQueryPluginResourceReference
{
	private static final long serialVersionUID = 4585057795574929263L;

	private static CoreUIJavaScriptResourceReference instance =
		new CoreUIJavaScriptResourceReference();

	public static CoreUIJavaScriptResourceReference get()
	{
		return instance;
	}

	/**
	 * Builds a new instance of {@link CoreUIJavaScriptResourceReference}.
	 */
	private CoreUIJavaScriptResourceReference()
	{
		super(CoreUIJavaScriptResourceReference.class, "jquery.ui.core.js");
	}

	@Override
	public Iterable< ? extends HeaderItem> getDependencies()
	{
		Iterable< ? extends HeaderItem> jquery = super.getDependencies();
		List<HeaderItem> ret = new ArrayList<HeaderItem>();
		for (HeaderItem curItem : jquery)
			ret.add(curItem);
		ret.add(CssHeaderItem.forReference(WiQueryCoreThemeResourceReference.get()));
		return ret;
	}
}

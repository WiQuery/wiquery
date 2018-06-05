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
package org.wicketstuff.wiquery.ui;

import java.util.List;

import org.apache.wicket.markup.head.CssHeaderItem;
import org.apache.wicket.markup.head.HeaderItem;
import org.apache.wicket.resource.JQueryPluginResourceReference;
import org.wicketstuff.wiquery.ui.themes.WiQueryCoreThemeResourceReference;

/**
 * References the full jQuery UI library.
 */
public class JQueryUIJavaScriptResourceReference extends JQueryPluginResourceReference
{
	private static final long serialVersionUID = 4585057795574929263L;

	private static final JQueryUIJavaScriptResourceReference INSTANCE = new JQueryUIJavaScriptResourceReference();

	public static JQueryUIJavaScriptResourceReference get()
	{
		return INSTANCE;
	}

	/**
	 * Builds a new instance of {@link JQueryUIJavaScriptResourceReference}.
	 */
	protected JQueryUIJavaScriptResourceReference()
	{
		super(JQueryUIJavaScriptResourceReference.class, "jquery-ui.js");
	}

	@Override
	public List<HeaderItem> getDependencies()
	{
		List<HeaderItem> jquery = super.getDependencies();
		jquery.add(CssHeaderItem.forReference(WiQueryCoreThemeResourceReference.get()));
		return jquery;
	}
}

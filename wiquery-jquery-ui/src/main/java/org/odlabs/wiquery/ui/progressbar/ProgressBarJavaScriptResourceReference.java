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
package org.odlabs.wiquery.ui.progressbar;

import java.util.List;

import org.apache.wicket.markup.head.HeaderItem;
import org.apache.wicket.request.resource.JavaScriptResourceReference;
import org.odlabs.wiquery.core.resources.JavaScriptHeaderItems;
import org.odlabs.wiquery.ui.core.CoreUIJavaScriptResourceReference;
import org.odlabs.wiquery.ui.widget.WidgetJavaScriptResourceReference;

/**
 * $Id: ProgressBarJavaScriptResourceReference.java 1714 2011-09-22 20:38:30Z hielke.hoeve
 * $
 * <p>
 * References the JavaScript resource to get the ProgressBar component.
 * </p>
 * 
 * @author Lionel Armanet
 * @since 0.5
 */
public final class ProgressBarJavaScriptResourceReference extends JavaScriptResourceReference
{
	// Constants
	/** Constant of serialization */
	private static final long serialVersionUID = 3423205998397680042L;

	/**
	 * Singleton instance.
	 */
	private static final ProgressBarJavaScriptResourceReference INSTANCE =
		new ProgressBarJavaScriptResourceReference();

	/**
	 * Builds a new instance of {@link ProgressBarJavaScriptResourceReference}.
	 */
	private ProgressBarJavaScriptResourceReference()
	{
		super(ProgressBarJavaScriptResourceReference.class, "jquery.ui.progressbar.js");
	}

	/**
	 * Returns the {@link ProgressBarJavaScriptResourceReference} instance.
	 */
	public static ProgressBarJavaScriptResourceReference get()
	{
		return INSTANCE;
	}

	@Override
	public List<HeaderItem> getDependencies()
	{
		return JavaScriptHeaderItems.forReferences(CoreUIJavaScriptResourceReference.get(),
			WidgetJavaScriptResourceReference.get());
	}
}

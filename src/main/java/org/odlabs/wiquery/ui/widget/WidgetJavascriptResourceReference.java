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
package org.odlabs.wiquery.ui.widget;

import org.apache.wicket.markup.html.resources.JavascriptResourceReference;

/**
 * $Id$
 * <p>
 * 	References the JavaScript resource to get the widget utilities.
 * </p>
 * @author Julien Roche
 * @since 1.1
 */
public class WidgetJavascriptResourceReference extends
		JavascriptResourceReference {
	private static final long serialVersionUID = -4771815414204892357L;
	
	/**
	 * Singleton instance.
	 */
	private static WidgetJavascriptResourceReference instance = new WidgetJavascriptResourceReference();;

	/**
	 * Builds a new instance of {@link WidgetJavascriptResourceReference}.
	 */
	private WidgetJavascriptResourceReference() {
		super(WidgetJavascriptResourceReference.class, "jquery.ui.widget.min.js");
	}

	/**
	 * Returns the {@link WidgetJavascriptResourceReference} instance.
	 */
	public static WidgetJavascriptResourceReference get() {
		return instance;
	}
}

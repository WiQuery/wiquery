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
package org.odlabs.wiquery.core.jqueryplugins;

import org.apache.wicket.markup.html.resources.JavascriptResourceReference;

/**
 * $Id: CookieJavaScriptResourceReference 
 * <p>
 * References the JavaScript resource to manipulate cookies with jQuery.
 * </p>
 * 
 * @author Julien Roche
 * @since 1.0
 */
public class CookieJavaScriptResourceReference extends
		JavascriptResourceReference {
	// Constants
	/** Constant of serialization */
	private static final long serialVersionUID = 3423205998397680042L;
	
	/**
	 * Singleton instance.
	 */
	private static CookieJavaScriptResourceReference instance = new CookieJavaScriptResourceReference();;

	/**
	 * Builds a new instance of {@link CookieJavaScriptResourceReference}.
	 */
	private CookieJavaScriptResourceReference() {
		super(CookieJavaScriptResourceReference.class, "jquery.cookie.js");
	}

	/**
	 * Returns the {@link CookieJavaScriptResourceReference} instance.
	 */
	public static CookieJavaScriptResourceReference get() {
		return instance;
	}
}

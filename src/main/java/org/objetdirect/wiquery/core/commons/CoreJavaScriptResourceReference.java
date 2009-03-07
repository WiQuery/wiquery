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
package org.objetdirect.wiquery.core.commons;

import org.apache.wicket.Application;
import org.apache.wicket.markup.html.resources.JavascriptResourceReference;

/**
 * $Id$
 * <p>
 * 	References the core jQuery library. Any WickeXt component should import
 *  this reference to be able to work.
 * </p>
 * @author Lionel Armanet
 * @since 0.5
 */
public class CoreJavaScriptResourceReference extends
		JavascriptResourceReference {

	private static final long serialVersionUID = -2918665261694523156L;

	/**
	 * Singleton instance.
	 */
	private static CoreJavaScriptResourceReference instance;
	
	/**
	 * Returns the {@link CoreJavaScriptResourceReference} instance.
	 */
	public static CoreJavaScriptResourceReference get() {
		if (instance == null) {
			instance = new CoreJavaScriptResourceReference();
		}
		return instance;
	}
	
	/**
	 * Returns the library name regarding the configuration type setting,
	 * e.g. loads the jQuery packed version in production mode and 
	 * loads the jQuery full version in development mode.
	 */
	private static String resolveLibrary() {
		String configurationType = Application.get().getConfigurationType();
		if (configurationType.equals(Application.DEVELOPMENT)) {
			return "jquery/jquery.js";
		}
		return "jquery/jquery.pack.js";

	}

	/**
	 * Builds a new instance of {@link CoreJavaScriptResourceReference}.
	 */
	private CoreJavaScriptResourceReference() {
		super(CoreJavaScriptResourceReference.class, resolveLibrary());
	}

}

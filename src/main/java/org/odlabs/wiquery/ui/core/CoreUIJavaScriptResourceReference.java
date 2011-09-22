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

import org.odlabs.wiquery.core.commons.WiQueryJavaScriptResourceReference;

/**
 * $Id: CoreUIJavaScriptResourceReference.java 81 2009-05-28 20:05:12Z
 * lionel.armanet $
 * <p>
 * References the core jQuery UI library.
 * </p>
 * 
 * @author Lionel Armanet
 * @since 0.5
 */
public class CoreUIJavaScriptResourceReference extends
		WiQueryJavaScriptResourceReference {

	private static final long serialVersionUID = 4585057795574929263L;

	private static CoreUIJavaScriptResourceReference instance;

	public static CoreUIJavaScriptResourceReference get() {
		if (instance == null)
			instance = new CoreUIJavaScriptResourceReference();
		return instance;
	}

	/**
	 * Builds a new instance of {@link CoreUIJavaScriptResourceReference}.
	 */
	private CoreUIJavaScriptResourceReference() {
		super(CoreUIJavaScriptResourceReference.class, "jquery.ui.core.js");
	}

}

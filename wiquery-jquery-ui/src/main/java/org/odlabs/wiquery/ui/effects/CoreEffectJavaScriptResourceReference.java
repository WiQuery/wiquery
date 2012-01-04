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
package org.odlabs.wiquery.ui.effects;

import org.odlabs.wiquery.core.resources.WiQueryJavaScriptResourceReference;
import org.odlabs.wiquery.core.ui.ICoreUIJavaScriptResourceReference;

/**
 * $Id: CoreEffectJavaScriptResourceReference.java 1143 2011-07-29 11:51:49Z
 * hielke.hoeve@gmail.com $
 * <p>
 * References the JavaScript resource to import the core for jQuery UI effects.
 * </p>
 * 
 * @author Julien Roche
 * @since 1.0
 */
public class CoreEffectJavaScriptResourceReference extends WiQueryJavaScriptResourceReference
		implements ICoreUIJavaScriptResourceReference
{
	// Constants
	/** Constant of serialization */
	private static final long serialVersionUID = 308143135598505224L;

	/**
	 * Singleton instance.
	 */
	private static CoreEffectJavaScriptResourceReference instance =
		new CoreEffectJavaScriptResourceReference();

	/**
	 * Default constructor
	 */
	private CoreEffectJavaScriptResourceReference()
	{
		super(CoreEffectJavaScriptResourceReference.class, "jquery.effects.core.js");
	}

	/**
	 * Returns the {@link CoreEffectJavaScriptResourceReference} instance.
	 */
	public static CoreEffectJavaScriptResourceReference get()
	{
		return instance;
	}
}

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

import org.apache.wicket.resource.dependencies.AbstractResourceDependentResourceReference;
import org.odlabs.wiquery.core.resources.WiQueryJavaScriptResourceReference;
import org.odlabs.wiquery.core.ui.ICoreUIJavaScriptResourceReference;

/**
 * $Id: DropEffectJavaScriptResourceReference.java 869 2011-05-04 12:26:32Z
 * hielke.hoeve@gmail.com $
 * <p>
 * References the JavaScript resource to import the Drop jQuery UI effect.
 * </p>
 * 
 * @author Julien Roche
 * @since 1.0
 */
public class DropEffectJavaScriptResourceReference extends WiQueryJavaScriptResourceReference
		implements ICoreUIJavaScriptResourceReference
{
	// Constants
	/** Constant of serialization */
	private static final long serialVersionUID = -93284559079596805L;

	/**
	 * Singleton instance.
	 */
	private static DropEffectJavaScriptResourceReference instance =
		new DropEffectJavaScriptResourceReference();

	/**
	 * Default constructor
	 */
	private DropEffectJavaScriptResourceReference()
	{
		super(CoreEffectJavaScriptResourceReference.class, "jquery.effects.drop.js");
	}

	/**
	 * Returns the {@link DropEffectJavaScriptResourceReference} instance.
	 */
	public static DropEffectJavaScriptResourceReference get()
	{
		return instance;
	}

	@Override
	public AbstractResourceDependentResourceReference[] getDependentResourceReferences()
	{
		AbstractResourceDependentResourceReference[] list =
			new AbstractResourceDependentResourceReference[1];
		list[0] = CoreEffectJavaScriptResourceReference.get();

		return list;
	}
}

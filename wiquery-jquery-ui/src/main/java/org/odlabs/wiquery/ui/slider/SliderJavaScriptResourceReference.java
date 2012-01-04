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
package org.odlabs.wiquery.ui.slider;

import org.apache.wicket.resource.dependencies.AbstractResourceDependentResourceReference;
import org.odlabs.wiquery.core.resources.WiQueryJavaScriptResourceReference;
import org.odlabs.wiquery.core.ui.ICoreUIJavaScriptResourceReference;
import org.odlabs.wiquery.ui.core.CoreUIJavaScriptResourceReference;
import org.odlabs.wiquery.ui.mouse.MouseJavaScriptResourceReference;

/**
 * $Id: SliderJavaScriptResourceReference.java 1143 2011-07-29 11:51:49Z
 * hielke.hoeve@gmail.com $
 * <p>
 * References the JavaScript resource to get the Slider component.
 * </p>
 * 
 * @author Julien Roche
 * @since 1.0
 */
public class SliderJavaScriptResourceReference extends WiQueryJavaScriptResourceReference implements
		ICoreUIJavaScriptResourceReference
{
	private static final long serialVersionUID = -4771815414204892357L;

	/**
	 * Singleton instance.
	 */
	private static SliderJavaScriptResourceReference instance =
		new SliderJavaScriptResourceReference();

	/**
	 * Builds a new instance of {@link SliderJavaScriptResourceReference}.
	 */
	private SliderJavaScriptResourceReference()
	{
		super(SliderJavaScriptResourceReference.class, "jquery.ui.slider.js");
	}

	/**
	 * Returns the {@link SliderJavaScriptResourceReference} instance.
	 */
	public static SliderJavaScriptResourceReference get()
	{
		return instance;
	}

	@Override
	public AbstractResourceDependentResourceReference[] getDependentResourceReferences()
	{
		AbstractResourceDependentResourceReference[] list =
			new AbstractResourceDependentResourceReference[2];
		list[0] = CoreUIJavaScriptResourceReference.get();
		list[1] = MouseJavaScriptResourceReference.get();

		return list;
	}
}

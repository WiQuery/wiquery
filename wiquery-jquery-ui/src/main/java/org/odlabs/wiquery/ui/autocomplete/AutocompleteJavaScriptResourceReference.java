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
package org.odlabs.wiquery.ui.autocomplete;

import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.resource.dependencies.AbstractResourceDependentResourceReference;
import org.odlabs.wiquery.core.resources.WiQueryJavaScriptResourceReference;
import org.odlabs.wiquery.core.ui.ICoreUIJavaScriptResourceReference;
import org.odlabs.wiquery.ui.core.CoreUIJavaScriptResourceReference;
import org.odlabs.wiquery.ui.position.PositionJavaScriptResourceReference;
import org.odlabs.wiquery.ui.widget.WidgetJavaScriptResourceReference;

/**
 * $Id: AutocompleteJavascriptResourceReference.java 869 2011-05-04 12:26:32Z
 * hielke.hoeve@gmail.com $
 * <p>
 * References the JavaScript resource to get the Autocomplete component.
 * </p>
 * 
 * @author Julien Roche
 * @since 1.1
 */
public class AutocompleteJavaScriptResourceReference extends WiQueryJavaScriptResourceReference
		implements ICoreUIJavaScriptResourceReference
{
	private static final long serialVersionUID = -4771815414204892357L;

	/**
	 * Singleton instance.
	 */
	private static AutocompleteJavaScriptResourceReference instance =
		new AutocompleteJavaScriptResourceReference();

	/**
	 * Builds a new instance of {@link AutocompleteJavaScriptResourceReference}.
	 */
	private AutocompleteJavaScriptResourceReference()
	{
		super(AutocompleteJavaScriptResourceReference.class, "jquery.ui.autocomplete.js");
	}

	/**
	 * Returns the {@link AutocompleteJavaScriptResourceReference} instance.
	 */
	public static AutocompleteJavaScriptResourceReference get()
	{
		return instance;
	}

	@Override
	public AbstractResourceDependentResourceReference[] getDependentResourceReferences()
	{
		List<AbstractResourceDependentResourceReference> list =
			new ArrayList<AbstractResourceDependentResourceReference>();
		list.add(CoreUIJavaScriptResourceReference.get());
		list.add(WidgetJavaScriptResourceReference.get());
		list.add(PositionJavaScriptResourceReference.get());

		return list.toArray(new AbstractResourceDependentResourceReference[0]);
	}
}
